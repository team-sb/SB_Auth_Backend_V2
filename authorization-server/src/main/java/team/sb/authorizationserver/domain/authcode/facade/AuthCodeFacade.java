package team.sb.authorizationserver.domain.authcode.facade;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import team.sb.authorizationserver.domain.authcode.entity.AuthCode;
import team.sb.authorizationserver.domain.authcode.repository.AuthCodeRepository;
import team.sb.authorizationserver.global.util.SesUtil;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class AuthCodeFacade {

    private final SesUtil sesUtil;
    private final AuthCodeRepository authCodeRepository;

    public void sendEmail(String email) {
        String code = getRandomCode();

        authCodeRepository.findById(email)
                .map(authCode -> authCode.update(code))
                .or(() ->
                        Optional.of(
                                new AuthCode(email, code)
                        )
                );

        sesUtil.sendEmail(email, code);
    }

    private String getRandomCode() {
        return RandomStringUtils.random(6);
    }

}
