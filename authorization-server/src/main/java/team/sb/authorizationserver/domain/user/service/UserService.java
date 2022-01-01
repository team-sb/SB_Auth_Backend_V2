package team.sb.authorizationserver.domain.user.service;

import org.springframework.web.multipart.MultipartFile;
import team.sb.authorizationserver.domain.user.api.dto.EmailDto;
import team.sb.authorizationserver.domain.user.api.dto.request.FindEmailRequest;
import team.sb.authorizationserver.domain.user.api.dto.request.SignupRequest;

public interface UserService {
    void signup(MultipartFile profile, SignupRequest signUpRequest);
    void sendEmail(EmailDto emailDto);
    EmailDto findEmail(FindEmailRequest findEmailRequest);
}
