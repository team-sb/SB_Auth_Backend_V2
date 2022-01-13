package team.sb.authorizationserver.domain.authcode.repository;

import org.springframework.data.repository.CrudRepository;
import team.sb.authorizationserver.domain.authcode.entity.PhoneAuthCode;

public interface PhoneAuthCodeRepository extends CrudRepository<PhoneAuthCode, String> {
}
