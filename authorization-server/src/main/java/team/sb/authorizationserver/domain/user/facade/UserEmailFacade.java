package team.sb.authorizationserver.domain.user.facade;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import team.sb.authorizationserver.global.util.SesUtil;

@RequiredArgsConstructor
@Component
public class UserEmailFacade {

    private final SesUtil sesUtil;

    public void sendEmail(String email) {
        String code = getRandomCode();
        sesUtil.sendEmail(email, code);
    }

    public static String getRandomCode() {
        return RandomStringUtils.random(6);
    }

}
