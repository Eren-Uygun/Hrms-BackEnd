package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.RoleService;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.RoleDao;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.UserDao;
import kodlamaio.HumanResourceManagementSystem.entities.abstracts.User;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class RoleManager implements RoleService {

    private final UserDao _userDao;
    private final RoleDao _roleDao;

    @Autowired
    public RoleManager(UserDao _userDao, RoleDao _roleDao) {
        this._userDao = _userDao;
        this._roleDao = _roleDao;
    }

    @Override
    public void addRoleToUser(String email, String roleName) {


           if (_userDao.existsByEmail(email)) {
               User user = _userDao.findByEmail(email.toLowerCase(Locale.ROOT));
               if (_roleDao.existsByName(roleName)) {
                   Role role = _roleDao.findByName(roleName.toUpperCase(Locale.ROOT));
                   user.getRoles().add(role);
                   _userDao.save(user);
               } else {
                   Role role = new Role();
                   role.setName(roleName.toUpperCase(Locale.ROOT));
                   _roleDao.save(role);
                   user.getRoles().add(role);
                   _userDao.save(user);
               }
           }

    }
}
