package team.sb.authorizationserver.domain.oauth.service;

import team.sb.authorizationserver.domain.oauth.api.dto.ClientDto;
import team.sb.authorizationserver.global.security.jwt.dto.TokenResponse;

public interface OauthService {
    ClientDto registerClient(String authorizedType, String scope);
    TokenResponse getToken(String code, ClientDto clientDto);
    TokenResponse reissue(String refreshToken);
}
