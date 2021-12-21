package team.sb.authorizationserver.domain.user.api.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import team.sb.authorizationserver.domain.user.entity.Gender;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class SignupRequest {

    @NotBlank(message = "email은 Null, 공백을 허용하지 않습니다.")
    @Email(message = "email 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "code는 Null, 공백을 허용하지 않습니다.")
    private String code;

    @Pattern(regexp="(?=.*[a-z])(?=.*[!#$%&'()*+,-./:;<=>?@＼^_`{|}~])(?=.*[0-9])(?=\\S+$).{8,32}$",
            message = "password는 띄어쓰기를 제외한 영문 소문자와 숫자, 특수문자가 적어도 1개 이상씩 포함된 8자이상 32글자 이하의 비밀번호여야 합니다.")
    @NotBlank(message = "password는 Null, 공백을 허용하지 않습니다.")
    private String password;

    @NotBlank(message = "name은 Null, 공백을 허용하지 않습니다.")
    @Pattern(regexp = "^[\\S]+$", message = "name은 띄어쓰기를 허용하지 않습니다.")
    @Length(max = 5, message = "name은 5글자 아래여야 합니다.")
    private String name;

    @NotBlank(message = "phoneNumber는 Null, 공백을 허용하지 않습니다.")
    @Length(min = 11, max = 11, message = "phoneNumber는 11자여야합니다.")
    private String phoneNumber;

    @NotNull(message = "gender는 Null, 공백을 허용하지 않습니다.")
    private Gender gender;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotNull(message = "birthDay는 Null, 공백을 허용하지 않습니다.")
    private LocalDate birthDay;

    public SignupRequest encodePassword(String password) {
        this.password = password;
        return this;
    }

}
