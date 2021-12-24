package team.sb.resourceserver.domain.user.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.sb.resourceserver.domain.user.api.dto.UserInfoResponse;
import team.sb.resourceserver.domain.user.service.UserService;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping
    public UserInfoResponse getUserInfo() {
        return userService.getUserInfo();
    }

    @PatchMapping("/image")
    public void updateProfileImage(@RequestPart MultipartFile multipartFile) {
        userService.updateProfileImage(multipartFile);
    }

}
