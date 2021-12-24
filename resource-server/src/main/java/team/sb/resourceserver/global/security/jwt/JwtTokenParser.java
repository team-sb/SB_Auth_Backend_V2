package team.sb.resourceserver.global.security.jwt;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import team.sb.resourceserver.global.exception.ExpiredTokenException;
import team.sb.resourceserver.global.exception.InvalidTokenException;
import team.sb.resourceserver.global.properties.JwtProperties;
import team.sb.resourceserver.global.security.auth.AuthDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenParser {

    private final JwtProperties jwtProperties;
    private final AuthDetailsService authDetailsService;

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(jwtProperties.getHeader());

        if(bearerToken != null && bearerToken.startsWith(jwtProperties.getPrefix())
                && bearerToken.length() > 7) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public Authentication getAuthentication(String token) {
        Claims body = getBody(token);

        if(body.getExpiration().before(new Date())) {
            throw ExpiredTokenException.EXCEPTION;
        }

        UserDetails userDetails = getDetails(body);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private Claims getBody(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (MalformedJwtException | SignatureException e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    @Nullable
    private UserDetails getDetails(Claims body) {
        return authDetailsService.loadUserByUsername(body.getSubject());
    }

}
