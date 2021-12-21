package team.sb.authorizationserver.global.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team.sb.authorizationserver.domain.user.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class AuthDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new AuthDetails(
                userRepository.findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("user not found"))
        );
    }

}
