package team.sb.authorizationserver.domain.authcode.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.sb.authorizationserver.domain.authcode.entity.EmailAuthCode;
import team.sb.authorizationserver.domain.authcode.repository.EmailAuthCodeRepository;
import team.sb.authorizationserver.global.util.AuthUtil;
import team.sb.authorizationserver.global.util.SesUtil;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class AuthCodeFacade {

    private final SesUtil sesUtil;
    private final AuthUtil authUtil;
    private final EmailAuthCodeRepository emailAuthCodeRepository;

    public void sendEmail(String email) {
        String code = authUtil.getRandomCode(6);

        emailAuthCodeRepository.findById(email)
                .map(emailAuthCode -> emailAuthCode.update(code))
                .or(() ->
                        Optional.of(
                                emailAuthCodeRepository.save(
                                        new EmailAuthCode(email, code)
                                )
                        )
                );

        sesUtil.sendEmail(email, code);
    }

}
