package team.sb.authorizationserver.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.sb.authorizationserver.domain.user.api.dto.request.EmailRequest;
import team.sb.authorizationserver.domain.user.api.dto.request.SignupRequest;
import team.sb.authorizationserver.domain.authcode.facade.AuthCodeFacade;
import team.sb.authorizationserver.domain.user.facade.UserFacade;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserFacade userFacade;
    private final AuthCodeFacade authCodeFacade;

    @Transactional
    @Override
    public void signup(SignupRequest signUpRequest) {
        userFacade.registerUser(signUpRequest);
    }

    @Async
    @Transactional
    @Override
    public void sendEmail(EmailRequest emailRequest) {
        authCodeFacade.sendEmail(emailRequest.getEmail());
    }

}
