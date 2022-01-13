package team.sb.authorizationserver.global.util;

import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.stereotype.Component;
import team.sb.authorizationserver.global.properties.SmsProperties;

import java.util.HashMap;

@RequiredArgsConstructor
@Component
public class SmsUtil {

    private final SmsProperties smsProperties;

    public void sendCode(String phoneNumber, String code) {
        Message message = new Message(smsProperties.getKey(), smsProperties.getSecret());

        HashMap<String, String> params = new HashMap<>();
        params.put("to", phoneNumber);
        params.put("from", smsProperties.getPhoneNumber());
        params.put("type", "SMS");
        params.put("text", getBody(code));
        params.put("app_version", "app 1.0");

        try {
            message.send(params);
        } catch (CoolsmsException e) {
            e.getStackTrace();
        }

    }

    private String getBody(String code) {
        return "[TEAM_SB] 인증번호 " + code + "를 입력하세요.";
    }

}
