package team.sb.authorizationserver.domain.user.service;

import team.sb.authorizationserver.domain.user.api.dto.request.EmailRequest;
import team.sb.authorizationserver.domain.user.api.dto.request.LoginRequest;
import team.sb.authorizationserver.domain.user.api.dto.request.SignupRequest;
import team.sb.authorizationserver.global.security.jwt.dto.TokenResponse;

public interface UserService {
    void signup(SignupRequest signUpRequest);
    void sendEmail(EmailRequest emailRequest);
    String login(LoginRequest loginRequest);
    TokenResponse callbackSocial(String code);
}
