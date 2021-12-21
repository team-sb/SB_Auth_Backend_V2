package team.sb.authorizationserver.domain.user.service;

import team.sb.authorizationserver.domain.user.api.dto.SignupRequest;

public interface UserService {
    void signup(SignupRequest signUpRequest);
}
