package team.sb.resourceserver.global.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team.sb.resourceserver.domain.user.exception.UserNotFoundException;
import team.sb.resourceserver.domain.user.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class AuthDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new AuthDetails(
                userRepository.findByEmail(email)
                        .orElseThrow(() -> UserNotFoundException.EXCEPTION)
        );
    }

}
