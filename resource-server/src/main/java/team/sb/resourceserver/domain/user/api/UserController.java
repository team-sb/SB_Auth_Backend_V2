package team.sb.resourceserver.domain.user.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.sb.resourceserver.domain.user.api.dto.EmailRequest;
import team.sb.resourceserver.domain.user.api.dto.UserInfoResponse;
import team.sb.resourceserver.domain.user.service.UserService;

import javax.validation.Valid;

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
    public void updateProfileImage(@RequestPart @Valid EmailRequest emailRequest,
                                   @RequestPart MultipartFile multipartFile) {
        userService.updateProfileImage(emailRequest.getEmail(), multipartFile);
    }

}
