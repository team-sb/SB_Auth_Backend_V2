package team.sb.authorizationserver.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import team.sb.authorizationserver.domain.authcode.facade.AuthCodeFacade;
import team.sb.authorizationserver.domain.user.api.dto.EmailDto;
import team.sb.authorizationserver.domain.user.api.dto.request.ChangePasswordRequest;
import team.sb.authorizationserver.domain.user.api.dto.request.FindEmailRequest;
import team.sb.authorizationserver.domain.user.api.dto.request.SignupRequest;
import team.sb.authorizationserver.domain.user.entity.User;
import team.sb.authorizationserver.domain.user.facade.UserFacade;
import team.sb.authorizationserver.domain.user.facade.UserProfileFacade;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserFacade userFacade;
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
    public void sendEmail(EmailDto emailDto) {
        authCodeFacade.sendEmail(emailDto.getEmail());
    }

    @Override
    public EmailDto findEmail(FindEmailRequest findEmailRequest) {
        User user = userFacade.getByPhoneNumber(findEmailRequest.getPhoneNumber());

        // code 인증 로직 추가

        return new EmailDto(user.getEmail());
    }

    @Transactional
    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        User user = userFacade.getByEmail(changePasswordRequest.getEmail());

        // code 인증 로직 추가

        user.updatePassword(passwordEncoder.encode(changePasswordRequest.getPassword()));
    }

}
