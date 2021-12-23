package team.sb.authorizationserver.global.security.jwt;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import team.sb.authorizationserver.domain.refreshtoken.entity.RefreshToken;
import team.sb.authorizationserver.domain.refreshtoken.repository.RefreshTokenRepository;
import team.sb.authorizationserver.global.exception.ExpiredTokenException;
import team.sb.authorizationserver.global.exception.InvalidTokenException;
import team.sb.authorizationserver.global.properties.JwtProperties;
import team.sb.authorizationserver.global.security.auth.AuthDetailsService;
import team.sb.authorizationserver.global.security.jwt.dto.TokenResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final AuthDetailsService authDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenResponse generateToken(String clientId) {
        String access = generateAccessToken(clientId);
        String refresh = generateRefreshToken(clientId);

        refreshTokenRepository.save(
                RefreshToken.builder()
                        .clientId(clientId)
                        .refreshToken(refresh)
                        .ttl(jwtProperties.getRefreshExp())
                        .build()
        );

        return new TokenResponse(access, refresh);
    }

    public String generateAccessToken(String clientId) {
        return Jwts.builder()
                .setSubject(clientId)
                .claim("type", "access")
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .setExpiration(
                        new Date(System.currentTimeMillis() + jwtProperties.getAccessExp() * 1000)
                )
                .setIssuedAt(new Date())
                .compact();
    }

    public String generateRefreshToken(String clientId) {
        return Jwts.builder()
                .setSubject(clientId)
                .claim("type", "refresh")
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .setExpiration(
                        new Date(System.currentTimeMillis() + jwtProperties.getRefreshExp() * 1000)
                )
                .setIssuedAt(new Date())
                .compact();
    }

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

    public boolean isRefresh(String token) {
        return getBody(token).get("type").equals("refresh");
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
