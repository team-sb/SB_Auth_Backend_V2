package team.sb.authorizationserver.domain.oauth.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    private String clientId;
    private String clientSecret;

}
