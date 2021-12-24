package team.sb.resourceserver.domain.user.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.sb.resourceserver.domain.user.entity.User;
import team.sb.resourceserver.domain.user.exception.UserNotFoundException;
import team.sb.resourceserver.domain.user.repository.UserRepository;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserRepository userRepository;

    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

}
