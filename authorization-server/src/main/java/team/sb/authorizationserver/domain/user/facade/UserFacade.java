package team.sb.authorizationserver.domain.user.facade;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import team.sb.authorizationserver.domain.authcode.entity.AuthCode;
import team.sb.authorizationserver.domain.authcode.exception.InvalidAuthCodeException;
import team.sb.authorizationserver.domain.authcode.repository.AuthCodeRepository;
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
    private final AuthCodeRepository authCodeRepository;

    public void registerUser(SignupRequest signUpRequest) {
        isAlreadyExists(signUpRequest.getEmail(), signUpRequest.getPhoneNumber());
        isValidCode(signUpRequest.getEmail(), signUpRequest.getCode());

        userRepository.save(
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

    public String getRandomCode() {
        return RandomStringUtils.random(6);
    }

    private void isAlreadyExists(String email, String phoneNumber) {
        if(userRepository.findByEmailOrPhoneNumber(email, phoneNumber).isPresent()) {
            throw UserAlreadyExistsException.EXCEPTION;
        }
    }

    private void isValidCode(String email, String code) {
        authCodeRepository.findById(email)
                .map(AuthCode::getCode)
                .filter(s -> s.equals(code))
                .orElseThrow(() -> InvalidAuthCodeException.EXCEPTION);
    }

}
