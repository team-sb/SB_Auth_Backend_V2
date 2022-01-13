package team.sb.authorizationserver.global.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "coolsms")
public class SmsProperties {

    private final String key;
    private final String secret;
    private final String phoneNumber;

}
