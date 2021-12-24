package team.sb.authorizationserver.domain.oauth.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.sb.authorizationserver.domain.oauth.api.dto.ClientDto;
import team.sb.authorizationserver.domain.oauth.entity.OauthCode;
import team.sb.authorizationserver.domain.oauth.entity.OauthDetails;
import team.sb.authorizationserver.domain.oauth.exception.ClientNotFoundException;
import team.sb.authorizationserver.domain.oauth.repository.OauthCodeRepository;
import team.sb.authorizationserver.domain.oauth.repository.OauthDetailsRepository;
import team.sb.authorizationserver.global.util.AuthUtil;

@RequiredArgsConstructor
@Component
public class OauthFacade {

    private final AuthUtil authUtil;
    private final OauthCodeRepository oauthCodeRepository;
    private final OauthDetailsRepository oauthDetailsRepository;

    public ClientDto getClientDetails() {
        String clientId = authUtil.getRandomCode(11);
        String clientSecret = authUtil.getRandomCode(11);

        return new ClientDto(clientId, clientSecret);
    }

    public OauthDetails getDetailsByClientId(String clientId) {
        return oauthDetailsRepository.findById(clientId)
                .orElseThrow(() -> ClientNotFoundException.EXCEPTION);
    }

    public OauthCode getCodeByClientId(String clientId) {
        return oauthCodeRepository.findById(clientId)
                .orElseThrow(() -> ClientNotFoundException.EXCEPTION);
    }

    public void newOauthCode(String clientId, String code, String email) {
        oauthCodeRepository.save(
                new OauthCode(
                        clientId, code, email
                )
        );
    }

}
