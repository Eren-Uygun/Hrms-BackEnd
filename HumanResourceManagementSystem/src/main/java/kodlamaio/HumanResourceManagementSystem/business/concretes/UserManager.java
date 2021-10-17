package kodlamaio.HumanResourceManagementSystem.business.concretes;

import javassist.NotFoundException;
import kodlamaio.HumanResourceManagementSystem.business.abstracts.UserService;
import kodlamaio.HumanResourceManagementSystem.core.utils.TextEditOperation;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.*;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.UserDao;
import kodlamaio.HumanResourceManagementSystem.entities.abstracts.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserManager implements UserService {

    private final UserDao _userDao;

    private Set getAuthority(User user) {
        Set authorities = new HashSet();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return authorities;
    }



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = _userDao.findUserByEmailEquals(email).orElseThrow(() ->
                new UsernameNotFoundException("User bulunamadı."));

            return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),getAuthority(user));

    }


    @Override
    public User findUserBEmail(String email) throws NotFoundException {
        return _userDao.findUserByEmailEquals(email).orElseThrow(() ->
                new NotFoundException("User bulunamadı."));
    }

    @Override
    public List<User> getUsers() {
        return _userDao.findAll();
    }


}
