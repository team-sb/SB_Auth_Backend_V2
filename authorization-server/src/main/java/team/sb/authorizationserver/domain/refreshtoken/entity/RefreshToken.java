package team.sb.authorizationserver.domain.refreshtoken.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash
public class RefreshToken {

    @Id
    private String clientId;

    @Indexed
    private String refreshToken;

    @TimeToLive
    private Long ttl;

    public RefreshToken(String clientId, String refreshToken) {
        this.clientId = clientId;
        this.refreshToken = refreshToken;
        this.ttl = 300L;
    }

}
