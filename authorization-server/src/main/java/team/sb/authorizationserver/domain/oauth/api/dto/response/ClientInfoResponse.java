package team.sb.authorizationserver.domain.oauth.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClientInfoResponse {

    private final String clientId;

    private final String redirectUri;

}
