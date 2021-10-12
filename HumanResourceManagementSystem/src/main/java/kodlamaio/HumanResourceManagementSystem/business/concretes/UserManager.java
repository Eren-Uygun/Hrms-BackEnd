package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.UserService;
import kodlamaio.HumanResourceManagementSystem.core.utils.TextEditOperation;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.ErrorResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.SuccessResult;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.RoleDao;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.UserDao;
import kodlamaio.HumanResourceManagementSystem.entities.abstracts.Role;
import kodlamaio.HumanResourceManagementSystem.entities.abstracts.User;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.LoginDto;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.RoleAddDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserManager implements UserService , UserDetailsService  {
    private final UserDao _userDao;
    private final RoleDao _roleDao;


    @Autowired
    public UserManager(UserDao _userDao, RoleDao _roleDao) {
        this._userDao = _userDao;
        this._roleDao = _roleDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = _userDao.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("User Not Found !!!");
        }else {
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            });
            return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
        }
    }

    @Override
    public Result saveRole(Role role) {
        if (_roleDao.existsByRoleName(TextEditOperation.makeAllWordsCapitalLetter(role.getRoleName()))){
            return new ErrorResult("Bu rol sistemde mevcut");
        }
        var  tempObj = TextEditOperation.makeAllWordsCapitalLetter(role.getRoleName());
        role.setRoleName(tempObj);
      _roleDao.save(role);
        return new SuccessResult("Rol eklendi");
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        User user = _userDao.findByEmail(email);
        Role role = _roleDao.findByRoleName(TextEditOperation.makeAllWordsCapitalLetter(roleName));
        user.getRoles().add(role);

    }

    @Override
    public Result login(LoginDto loginDto) {
        return null;
    }

    @Override
    public User getUser(String userName) {
        return _userDao.findByEmail(userName);
    }

    @Override
    public List<User> getUsers() {
        return _userDao.findAll();
    }


}
