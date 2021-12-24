package team.sb.resourceserver.domain.user.service;

import org.springframework.web.multipart.MultipartFile;
import team.sb.resourceserver.domain.user.api.dto.UserInfoResponse;

public interface UserService {
    void updateProfileImage(String email, MultipartFile multipartFile);
    UserInfoResponse getUserInfo();
}
