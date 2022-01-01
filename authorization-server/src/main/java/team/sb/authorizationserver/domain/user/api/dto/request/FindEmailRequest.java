package team.sb.authorizationserver.domain.user.api.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class FindEmailRequest {

    @NotBlank(message = "phoneNumber는 Null, 공백을 허용하지 않습니다.")
    @Length(min = 11, max = 11, message = "phoneNumber는 11자여야합니다.")
    private String phoneNumber;

    @NotBlank(message = "code는 Null, 공백을 허용하지 않습니다.")
    private String code;

}
