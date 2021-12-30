package team.sb.authorizationserver.domain.user.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.sb.authorizationserver.domain.user.api.dto.request.EmailRequest;
import team.sb.authorizationserver.domain.user.api.dto.request.LoginRequest;
import team.sb.authorizationserver.domain.user.api.dto.request.SignupRequest;
import team.sb.authorizationserver.domain.user.service.UserService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void signup(@RequestPart(required = false) MultipartFile profile,
                       @RequestPart @Valid SignupRequest signUpRequest) {
        userService.signup(profile, signUpRequest);
    }

    @PostMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    public void sendEmail(@RequestBody @Valid EmailRequest emailRequest) {
        userService.sendEmail(emailRequest);
    }

    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestParam(name = "client_id") String clientId,
                        @RequestParam(name = "redirect_uri") String redirectUri,
                        @RequestParam(name = "authorized_type") String authorizedType,
                        @RequestBody @Valid LoginRequest loginRequest) {
        String code = userService.login(loginRequest, clientId, redirectUri, authorizedType);
        return "redirect:" + redirectUri + "?code=" + code;
    }

}
