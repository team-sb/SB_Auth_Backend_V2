package team.sb.resourceserver.domain.user.service;

import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    void updateProfileImage(String email, MultipartFile multipartFile);
}
