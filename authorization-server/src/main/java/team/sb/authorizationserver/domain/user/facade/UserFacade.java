package team.sb.authorizationserver.domain.user.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import team.sb.authorizationserver.domain.user.api.dto.SignupRequest;
import team.sb.authorizationserver.domain.user.entity.User;
import team.sb.authorizationserver.domain.user.exception.UserAlreadyExistsException;
import team.sb.authorizationserver.domain.user.repository.UserRepository;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(SignupRequest signUpRequest) {
        isAlreadyExists(signUpRequest.getEmail(), signUpRequest.getPhoneNumber());


        userRepository.save(
                new User(
                        signUpRequest.encodePassword(
                                passwordEncoder.encode(signUpRequest.getPassword())
                        )
                )
        );
    }

    public void isAlreadyExists(String email, String phoneNumber) {
        if(userRepository.findByEmailOrPhoneNumber(email, phoneNumber).isPresent()) {
            throw UserAlreadyExistsException.EXCEPTION;
        }
    }

}
