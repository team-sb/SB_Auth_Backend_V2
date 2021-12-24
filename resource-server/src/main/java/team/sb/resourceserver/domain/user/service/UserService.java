package team.sb.resourceserver.domain.user.service;

import org.springframework.web.multipart.MultipartFile;
import team.sb.resourceserver.domain.user.api.dto.UserInfoResponse;

public interface UserService {
    void updateProfileImage(MultipartFile multipartFile);
    UserInfoResponse getUserInfo();
}
