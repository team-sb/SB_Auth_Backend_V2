package team.sb.authorizationserver.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.sb.authorizationserver.domain.user.api.dto.request.EmailRequest;
import team.sb.authorizationserver.domain.user.api.dto.request.LoginRequest;
import team.sb.authorizationserver.domain.user.api.dto.request.SignupRequest;
import team.sb.authorizationserver.domain.authcode.facade.AuthCodeFacade;
import team.sb.authorizationserver.domain.user.entity.User;
import team.sb.authorizationserver.domain.user.exception.InvalidPasswordException;
import team.sb.authorizationserver.domain.user.facade.UserFacade;
import team.sb.authorizationserver.global.security.jwt.JwtTokenProvider;
import team.sb.authorizationserver.global.security.jwt.dto.TokenResponse;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserFacade userFacade;
    private final AuthCodeFacade authCodeFacade;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

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

    @Override
    public String login(LoginRequest loginRequest) {
        User user = userFacade.getByEmail(loginRequest.getEmail());

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw InvalidPasswordException.EXCEPTION;
        }

        return userFacade.getRandomCode();
    }

    @Override
    public TokenResponse callbackSocial(String code) {


        return jwtTokenProvider.generateToken(code);
    }

}
