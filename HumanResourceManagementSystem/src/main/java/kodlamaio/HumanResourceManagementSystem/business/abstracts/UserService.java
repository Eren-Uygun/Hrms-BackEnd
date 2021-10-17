package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import javassist.NotFoundException;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.abstracts.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findUserBEmail(String email) throws NotFoundException;
    List<User> getUsers();






}
