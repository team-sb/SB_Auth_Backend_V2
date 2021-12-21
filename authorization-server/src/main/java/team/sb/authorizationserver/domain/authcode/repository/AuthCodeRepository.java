package team.sb.authorizationserver.domain.authcode.repository;

import org.springframework.data.repository.CrudRepository;
import team.sb.authorizationserver.domain.authcode.entity.AuthCode;

public interface AuthCodeRepository extends CrudRepository<AuthCode, String> {
}
