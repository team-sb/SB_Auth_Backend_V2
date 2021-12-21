package team.sb.authorizationserver.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.sb.authorizationserver.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
