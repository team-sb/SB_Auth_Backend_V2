package team.sb.authorizationserver.domain.refreshtoken.repository;

import org.springframework.data.repository.CrudRepository;
import team.sb.authorizationserver.domain.refreshtoken.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
