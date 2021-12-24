package team.sb.resourceserver.global.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private final String header;
    private final String prefix;
    private final String secretKey;
    private final Long accessExp;
    private final Long refreshExp;

    public JwtProperties(String header, String prefix, String secretKey, Long accessExp, Long refreshExp) {
        this.header = header;
        this.prefix = prefix;
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
        this.accessExp = accessExp;
        this.refreshExp = refreshExp;
    }

}
