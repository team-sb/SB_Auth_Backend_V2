package team.sb.authorizationserver.domain.authcode.repository;

import org.springframework.data.repository.CrudRepository;
import team.sb.authorizationserver.domain.authcode.entity.EmailAuthCode;

public interface EmailAuthCodeRepository extends CrudRepository<EmailAuthCode, String> {
}
