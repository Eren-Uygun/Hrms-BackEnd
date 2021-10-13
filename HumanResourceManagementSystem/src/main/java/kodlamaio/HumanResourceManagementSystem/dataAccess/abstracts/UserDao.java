package kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts;

import kodlamaio.HumanResourceManagementSystem.entities.abstracts.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {

    Optional<User> findUserByEmailEquals(String email);
    User findByEmail(String email);
    User getByEmail(String email);
    boolean existsByEmailEqualsAndPasswordEquals(String email,String password);
    boolean existsByEmail(String email);
}
