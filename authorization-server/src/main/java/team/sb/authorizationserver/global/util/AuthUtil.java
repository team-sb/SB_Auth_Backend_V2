package team.sb.authorizationserver.global.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {

    public String getRandomCode(Integer num) {
        return RandomStringUtils.randomAlphanumeric(num);
    }

}
