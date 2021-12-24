package team.sb.resourceserver.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.sb.resourceserver.domain.user.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
