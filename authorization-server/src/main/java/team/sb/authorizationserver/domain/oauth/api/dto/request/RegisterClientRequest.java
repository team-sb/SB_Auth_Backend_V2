package team.sb.authorizationserver.domain.oauth.api.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class RegisterClientRequest {

    @NotBlank(message = "redirectUri는 Null, 공백을 허용하지 않습니다.")
    private String redirectUri;

    @NotBlank(message = "applicationName은 Null, 공백을 허용하지 않습니다.")
    @Size(max = 20, message = "applicationName은 20글자 이하여야 합니다.")
    private String applicationName;

    @NotBlank(message = "applicationIntroduce는 Null, 공백을 허용하지 않습니다.")
    @Size(max = 100, message = "applicationIntroduce는 100글자 이하여야 합니다.")
    private String applicationIntroduce;

}
