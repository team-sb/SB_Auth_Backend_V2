package team.sb.authorizationserver.domain.user.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import team.sb.authorizationserver.domain.user.api.dto.SignupRequest;
import team.sb.authorizationserver.domain.user.service.UserService;

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

}
