package team.sb.authorizationserver.domain.oauth.service;

import team.sb.authorizationserver.domain.oauth.api.dto.ClientDto;
import team.sb.authorizationserver.domain.user.api.dto.request.LoginRequest;
import team.sb.authorizationserver.global.security.jwt.dto.TokenResponse;

public interface OauthService {
    ClientDto registerClient(String redirectUri, String scope);
    String login(LoginRequest loginRequest, String clientId, String redirectUri, String authorizedType);
    TokenResponse getToken(String code, ClientDto clientDto);
    TokenResponse reissue(String refreshToken);
}
