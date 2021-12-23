package team.sb.authorizationserver.domain.oauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.sb.authorizationserver.domain.oauth.entity.OauthDetails;

public interface OauthDetailsRepository extends JpaRepository<OauthDetails, String> {
}
