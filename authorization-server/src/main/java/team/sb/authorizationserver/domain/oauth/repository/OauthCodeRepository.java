package team.sb.authorizationserver.domain.oauth.repository;

import org.springframework.data.repository.CrudRepository;
import team.sb.authorizationserver.domain.oauth.entity.OauthCode;

public interface OauthCodeRepository extends CrudRepository<OauthCode, String> {
}
