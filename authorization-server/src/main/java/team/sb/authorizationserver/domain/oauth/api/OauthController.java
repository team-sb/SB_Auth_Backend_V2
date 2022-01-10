package team.sb.authorizationserver.domain.oauth.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import team.sb.authorizationserver.domain.oauth.api.dto.ClientDto;
import team.sb.authorizationserver.domain.oauth.api.dto.request.RegisterClientRequest;
import team.sb.authorizationserver.domain.oauth.service.OauthService;
import team.sb.authorizationserver.domain.user.api.dto.request.LoginRequest;
import team.sb.authorizationserver.global.security.jwt.dto.TokenResponse;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/sbauth")
@RestController
public class OauthController {

    private final OauthService oauthService;

    @PostMapping("/client")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDto registerClient(@RequestBody @Valid RegisterClientRequest request) {
        return oauthService.registerClient(request);
    }

    @PostMapping("/authorize")
    @ResponseStatus(HttpStatus.FOUND)
    public String login(@RequestParam(name = "client_id") String clientId,
                        @RequestParam(name = "redirect_uri") String redirectUri,
                        @RequestParam(name = "authorized_type") String authorizedType,
                        @RequestBody @Valid LoginRequest loginRequest) {
        String code = oauthService.login(loginRequest, clientId, redirectUri, authorizedType);
//        return "redirect:" + redirectUri + "?code=" + code;
        return code;
    }

    @PostMapping("/token")
    public TokenResponse getToken(@RequestParam String code,
                                  @RequestBody ClientDto clientDto) {
        return oauthService.getToken(code, clientDto);
    }

    @PutMapping("/authorize")
    public TokenResponse reissue(@RequestHeader("X-Refresh-Token") String refreshToken) {
        return oauthService.reissue(refreshToken);
    }

}
