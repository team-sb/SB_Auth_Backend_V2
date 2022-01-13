package team.sb.authorizationserver.domain.user.api.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class SendSmsRequest {

    @NotBlank(message = "phoneNumber는 Null, 공백을 허용하지 않습니다.")
    @Size(min = 11, max = 11, message = "phoneNumber는 11자여야합니다.")
    private String phoneNumber;

}
