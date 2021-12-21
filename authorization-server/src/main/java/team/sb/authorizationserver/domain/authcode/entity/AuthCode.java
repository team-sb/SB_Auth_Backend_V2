package team.sb.authorizationserver.domain.authcode.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash
public class AuthCode {

    @Id
    private String email;

    private String code;

    @TimeToLive
    private Long codeExp;

    public AuthCode update(String code) {
        this.code = code;
        this.codeExp = 180L;
        return this;
    }

    public AuthCode(String email, String code) {
        this.email = email;
        this.code = code;
        this.codeExp = 180L;
    }

}
