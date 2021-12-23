package team.sb.authorizationserver.domain.oauth.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash
public class OauthCode {

    @Id
    private String clientId;

    private String code;

    private Long ttl;

    public OauthCode(String clientId, String code) {
        this.clientId = clientId;
        this.code = code;
        this.ttl = 180L;
    }

}
