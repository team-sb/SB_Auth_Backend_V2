package team.sb.authorizationserver.domain.authcode.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.sb.authorizationserver.domain.authcode.entity.AuthCode;
import team.sb.authorizationserver.domain.authcode.repository.AuthCodeRepository;
import team.sb.authorizationserver.global.util.AuthUtil;
import team.sb.authorizationserver.global.util.SesUtil;

import javax.swing.text.html.Option;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class AuthCodeFacade {

    private final SesUtil sesUtil;
    private final AuthUtil authUtil;
    private final AuthCodeRepository authCodeRepository;

    public void sendEmail(String email) {
        String code = authUtil.getRandomCode(6);

        authCodeRepository.findById(email)
                .map(authCode -> authCode.update(code))
                .or(() ->
                        Optional.of(
                                authCodeRepository.save(
                                        new AuthCode(email, code)
                                )
                        )
                );

        sesUtil.sendEmail(email, code);
    }

}
