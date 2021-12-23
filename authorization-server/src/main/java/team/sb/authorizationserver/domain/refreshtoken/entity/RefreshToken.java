package team.sb.authorizationserver.domain.refreshtoken.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash
public class RefreshToken {

    @Id
    private String clientId;

    private String refreshToken;

    @TimeToLive
    private Long ttl;

}
