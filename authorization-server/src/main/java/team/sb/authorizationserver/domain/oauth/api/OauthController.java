package team.sb.authorizationserver.domain.oauth.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team.sb.authorizationserver.domain.oauth.api.dto.ClientDto;
import team.sb.authorizationserver.domain.oauth.service.OauthService;
import team.sb.authorizationserver.global.security.jwt.dto.TokenResponse;

@RequiredArgsConstructor
@RequestMapping("/oauth")
@RestController
public class OauthController {

    private final OauthService oauthService;

    @PostMapping("/client")
    public ClientDto registerClient(@RequestParam(value = "authorized_type") String authorizedType,
                                    @RequestParam String scope) {
        return oauthService.registerClient(authorizedType, scope);
    }

    @GetMapping("/token")
    public TokenResponse getToken(@RequestParam String code,
                                  @RequestBody ClientDto clientDto) {
        return oauthService.getToken(code, clientDto);
    }

}
