package team.sb.authorizationserver.domain.user.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import team.sb.authorizationserver.domain.authcode.entity.EmailAuthCode;
import team.sb.authorizationserver.domain.authcode.exception.InvalidAuthCodeException;
import team.sb.authorizationserver.domain.authcode.repository.EmailAuthCodeRepository;
import team.sb.authorizationserver.domain.user.api.dto.request.SignupRequest;
import team.sb.authorizationserver.domain.user.entity.User;
import team.sb.authorizationserver.domain.user.exception.UserAlreadyExistsException;
import team.sb.authorizationserver.domain.user.exception.UserNotFoundException;
import team.sb.authorizationserver.domain.user.repository.UserRepository;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailAuthCodeRepository emailAuthCodeRepository;

    public User registerUser(SignupRequest signUpRequest) {
        isAlreadyExists(signUpRequest.getEmail(), signUpRequest.getPhoneNumber());
        isValidCode(signUpRequest.getEmail(), signUpRequest.getCode());
        // 전화번호 인증로직 추가

        return userRepository.save(
                new User(
                        signUpRequest.encodePassword(
                                passwordEncoder.encode(signUpRequest.getPassword())
                        )
                )
        );
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public User getByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    private void isAlreadyExists(String email, String phoneNumber) {
        if(userRepository.findByEmailOrPhoneNumber(email, phoneNumber).isPresent()) {
            throw UserAlreadyExistsException.EXCEPTION;
        }
    }

    private void isValidCode(String email, String code) {
        emailAuthCodeRepository.findById(email)
                .map(EmailAuthCode::getCode)
                .filter(s -> s.equals(code))
                .orElseThrow(() -> InvalidAuthCodeException.EXCEPTION);
    }

}
