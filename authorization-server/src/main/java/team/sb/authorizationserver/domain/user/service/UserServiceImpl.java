package team.sb.authorizationserver.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import team.sb.authorizationserver.domain.authcode.facade.AuthCodeFacade;
import team.sb.authorizationserver.domain.oauth.entity.OauthDetails;
import team.sb.authorizationserver.domain.oauth.facade.OauthFacade;
import team.sb.authorizationserver.domain.user.api.dto.request.EmailRequest;
import team.sb.authorizationserver.domain.user.api.dto.request.LoginRequest;
import team.sb.authorizationserver.domain.user.api.dto.request.SignupRequest;
import team.sb.authorizationserver.domain.user.api.dto.response.LoginResponse;
import team.sb.authorizationserver.domain.user.entity.User;
import team.sb.authorizationserver.domain.user.exception.InvalidPasswordException;
import team.sb.authorizationserver.domain.user.facade.UserFacade;
import team.sb.authorizationserver.domain.user.facade.UserProfileFacade;
import team.sb.authorizationserver.global.util.AuthUtil;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final AuthUtil authUtil;
    private final UserFacade userFacade;
    private final OauthFacade oauthFacade;
    private final AuthCodeFacade authCodeFacade;
    private final PasswordEncoder passwordEncoder;
    private final UserProfileFacade userProfileFacade;

    @Transactional
    @Override
    public void signup(MultipartFile profile, SignupRequest signUpRequest) {
        User user = userFacade.registerUser(signUpRequest);

        if(profile != null) {
            userProfileFacade.addProfileImage(profile, user);
        }

    }

    @Async
    @Transactional
    @Override
    public void sendEmail(EmailRequest emailRequest) {
        authCodeFacade.sendEmail(emailRequest.getEmail());
    }

    @Transactional
    @Override
    public LoginResponse login(LoginRequest loginRequest, String clientId, String redirectUri) {
        User user = userFacade.getByEmail(loginRequest.getEmail());
        OauthDetails oauthDetails = oauthFacade.getDetailsByClientId(clientId);
        oauthDetails.updateRedirectUri(redirectUri);

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw InvalidPasswordException.EXCEPTION;
        }

        String code = authUtil.getRandomCode(7);
        oauthFacade.newOauthCode(clientId, code, loginRequest.getEmail());

        return new LoginResponse(code);
    }

}
