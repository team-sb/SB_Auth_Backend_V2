package team.sb.authorizationserver.domain.user.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import team.sb.authorizationserver.domain.user.api.dto.request.EmailRequest;
import team.sb.authorizationserver.domain.user.api.dto.request.LoginRequest;
import team.sb.authorizationserver.domain.user.api.dto.request.SignupRequest;
import team.sb.authorizationserver.domain.user.service.UserService;
import team.sb.authorizationserver.global.security.jwt.dto.TokenResponse;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void signup(@RequestBody @Valid SignupRequest signUpRequest) {
        userService.signup(signUpRequest);
    }

    @PostMapping("/email")
    public void sendEmail(@RequestBody @Valid EmailRequest emailRequest) {
        userService.sendEmail(emailRequest);
    }

    @PostMapping("/auth")
    public String login(@RequestBody @Valid LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @GetMapping("/callback")
    public TokenResponse callbackSocial(@RequestParam String code) {
        return userService.callbackSocial(code);
    }

}
