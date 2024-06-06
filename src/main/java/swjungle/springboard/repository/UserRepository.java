package swjungle.springboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swjungle.springboard.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);
}
