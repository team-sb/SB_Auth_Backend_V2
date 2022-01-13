package team.sb.authorizationserver.domain.user.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.sb.authorizationserver.domain.user.api.dto.EmailDto;
import team.sb.authorizationserver.domain.user.api.dto.request.ChangePasswordRequest;
import team.sb.authorizationserver.domain.user.api.dto.request.FindEmailRequest;
import team.sb.authorizationserver.domain.user.api.dto.request.SendSmsRequest;
import team.sb.authorizationserver.domain.user.api.dto.request.SignupRequest;
import team.sb.authorizationserver.domain.user.service.UserService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(@RequestPart(required = false) MultipartFile profile,
                       @RequestPart @Valid SignupRequest signUpRequest) {
        userService.signup(profile, signUpRequest);
    }

    @PostMapping("/email")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendEmail(@RequestBody @Valid EmailDto emailDto) {
        userService.sendEmail(emailDto);
    }

    @PostMapping("/phone")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendSms(@RequestBody @Valid SendSmsRequest sendSmsRequest) {
        userService.sendSms(sendSmsRequest);
    }

    @PostMapping("/find/email")
    public EmailDto findEmail(@RequestBody @Valid FindEmailRequest findEmailRequest) {
        return userService.findEmail(findEmailRequest);
    }

    @PatchMapping("/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@RequestBody @Valid ChangePasswordRequest changePasswordRequest) {
        userService.changePassword(changePasswordRequest);
    }

}
