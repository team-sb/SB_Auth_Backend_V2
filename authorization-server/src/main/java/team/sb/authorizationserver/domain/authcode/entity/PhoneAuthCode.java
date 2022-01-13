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
public class PhoneAuthCode {

    @Id
    private String phoneNumber;

    private String code;

    @TimeToLive
    private Long codeExp;

    public PhoneAuthCode update(String code) {
        this.code = code;
        this.codeExp = 180L;
        return this;
    }

    public PhoneAuthCode(String phoneNumber, String code) {
        this.phoneNumber = phoneNumber;
        this.code = code;
        this.codeExp = 180L;
    }

}
