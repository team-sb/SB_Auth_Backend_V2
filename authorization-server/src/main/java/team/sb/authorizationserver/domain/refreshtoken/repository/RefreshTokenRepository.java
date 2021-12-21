package team.sb.authorizationserver.domain.refreshtoken.repository;

import org.springframework.data.repository.CrudRepository;
import team.sb.authorizationserver.domain.refreshtoken.entity.RefreshToken;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
