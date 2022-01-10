package team.sb.authorizationserver.domain.user.api.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class ChangePasswordRequest {

    @NotBlank(message = "email은 Null, 공백을 허용하지 않습니다.")
    private String email;

    @NotBlank(message = "code는 Null, 공백을 허용하지 않습니다.")
    private String code;

    @Pattern(regexp="(?=.*[a-z])(?=.*[!#$%&'()*+,-./:;<=>?@＼^_`{|}~])(?=.*[0-9])(?=\\S+$).{8,32}$",
            message = "password는 띄어쓰기를 제외한 영문 소문자와 숫자, 특수문자가 적어도 1개 이상씩 포함된 8자이상 32글자 이하의 비밀번호여야 합니다.")
    @NotBlank(message = "password는 Null, 공백을 허용하지 않습니다.")
    private String password;

}
