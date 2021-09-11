package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.UserService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.ErrorResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.SuccessResult;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements UserService {
    private UserDao _userDao;

    @Autowired
    public UserManager(UserDao _userDao) {
        this._userDao = _userDao;
    }

    @Override
    public Result login(String email, String password) {
        if (_userDao.existsByEmailEqualsAndPasswordEquals(email,password)){
            return new SuccessResult("Giriş yapıldı.");
        }
        return new ErrorResult("Kullanıcı bulunamadı.");
    }
}
