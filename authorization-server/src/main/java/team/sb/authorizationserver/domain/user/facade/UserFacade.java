package team.sb.authorizationserver.domain.user.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import team.sb.authorizationserver.domain.authcode.entity.EmailAuthCode;
import team.sb.authorizationserver.domain.authcode.entity.PhoneAuthCode;
import team.sb.authorizationserver.domain.authcode.exception.InvalidEmailCodeException;
import team.sb.authorizationserver.domain.authcode.exception.InvalidPhoneCodeException;
import team.sb.authorizationserver.domain.authcode.repository.EmailAuthCodeRepository;
import team.sb.authorizationserver.domain.authcode.repository.PhoneAuthCodeRepository;
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
    private final PhoneAuthCodeRepository phoneAuthCodeRepository;

    public User registerUser(SignupRequest signUpRequest) {
        isAlreadyExists(signUpRequest.getEmail(), signUpRequest.getPhoneNumber());
        isValidEmail(signUpRequest.getEmail(), signUpRequest.getEmailCode());
        isValidPhoneNumber(signUpRequest.getPhoneNumber(), signUpRequest.getPhoneCode());

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

    public void isValidEmail(String email, String code) {
        emailAuthCodeRepository.findById(email)
                .map(EmailAuthCode::getCode)
                .filter(s -> s.equals(code))
                .orElseThrow(() -> InvalidEmailCodeException.EXCEPTION);
    }

    public void isValidPhoneNumber(String phoneNumber, String code) {
        phoneAuthCodeRepository.findById(phoneNumber)
                .map(PhoneAuthCode::getCode)
                .filter(s -> s.equals(code))
                .orElseThrow(() -> InvalidPhoneCodeException.EXCEPTION);
    }

}
