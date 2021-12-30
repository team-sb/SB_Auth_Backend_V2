package team.sb.authorizationserver.domain.oauth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.sb.authorizationserver.domain.oauth.api.dto.ClientDto;
import team.sb.authorizationserver.domain.oauth.entity.OauthCode;
import team.sb.authorizationserver.domain.oauth.entity.OauthDetails;
import team.sb.authorizationserver.domain.oauth.exception.InvalidClientSecret;
import team.sb.authorizationserver.domain.oauth.exception.InvalidOauthCodeException;
import team.sb.authorizationserver.domain.oauth.facade.OauthFacade;
import team.sb.authorizationserver.domain.oauth.repository.OauthDetailsRepository;
import team.sb.authorizationserver.domain.refreshtoken.entity.RefreshToken;
import team.sb.authorizationserver.domain.refreshtoken.repository.RefreshTokenRepository;
import team.sb.authorizationserver.global.exception.InvalidTokenException;
import team.sb.authorizationserver.global.security.jwt.JwtTokenProvider;
import team.sb.authorizationserver.global.security.jwt.dto.TokenResponse;

@RequiredArgsConstructor
@Service
public class OauthServiceImpl implements OauthService {

    private final OauthFacade oauthFacade;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final OauthDetailsRepository oauthDetailsRepository;

    @Transactional
    @Override
    public ClientDto registerClient(String redirectUri, String scope) { // scope : READ, WRITE, ALL
        ClientDto response = oauthFacade.getClientDetails();

        oauthDetailsRepository.save(
                new OauthDetails(
                        response.getClientId(),
                        response.getClientSecret(),
                        redirectUri,
                        scope
                )
        );

        return response;
    }

    @Transactional
    @Override
    public TokenResponse getToken(String code, ClientDto clientDto) {
        String clientId = clientDto.getClientId();
        OauthDetails oauthDetails = oauthFacade.getDetailsByClientId(clientId);
        OauthCode oauthCode = oauthFacade.getCodeByClientId(clientId);

        if(!oauthDetails.getClientSecret().equals(clientDto.getClientSecret())) {
            throw InvalidClientSecret.EXCEPTION;
        }

        if(!oauthCode.getCode().equals(code)) {
            throw InvalidOauthCodeException.EXCEPTION;
        }

        TokenResponse tokenResponse = jwtTokenProvider.generateToken(oauthCode.getUserEmail());
        refreshTokenRepository.save(
                new RefreshToken(
                        clientId, tokenResponse.getRefreshToken()
                )
        );

        return tokenResponse;
    }

    @Transactional
    @Override
    public TokenResponse reissue(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .filter(token -> jwtTokenProvider.isRefresh(refreshToken))
                .map(token -> {
                    String email = token.getUserEmail();
                    return jwtTokenProvider.generateToken(email);
                })
                .orElseThrow(() -> InvalidTokenException.EXCEPTION);
    }

}
