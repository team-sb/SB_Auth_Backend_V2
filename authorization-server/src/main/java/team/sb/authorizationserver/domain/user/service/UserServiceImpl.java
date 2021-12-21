package team.sb.authorizationserver.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.sb.authorizationserver.domain.user.api.dto.SignupRequest;
import team.sb.authorizationserver.domain.user.facade.UserFacade;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserFacade userFacade;

    @Transactional
    @Override
    public void signup(SignupRequest signUpRequest) {
        userFacade.registerUser(signUpRequest);
    }

}
