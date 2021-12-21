package team.sb.authorizationserver.domain.user.api.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class EmailRequest {

    @NotBlank(message = "email은 Null, 공백을 허용하지 않습니다.")
    @Email(message = "email 형식이 올바르지 않습니다.")
    private String email;

}
