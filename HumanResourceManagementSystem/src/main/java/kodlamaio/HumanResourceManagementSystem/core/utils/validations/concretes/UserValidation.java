package kodlamaio.HumanResourceManagementSystem.core.utils.validations.concretes;

import kodlamaio.HumanResourceManagementSystem.core.utils.validations.abstracts.UserValidationService;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.CandidateDao;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.UserDao;
import kodlamaio.HumanResourceManagementSystem.entities.abstracts.User;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidation implements UserValidationService {

    private CandidateDao _candidateDao;
    private UserDao _userDao;

    @Autowired
    public UserValidation(UserDao userDao ,CandidateDao _candidateDao) {
       this._userDao = userDao;
        this._candidateDao = _candidateDao;
    }


    @Override
    public boolean isUserExists(int id) {
            if (_userDao.findById(id).isEmpty()){
                return true;
            }
            return false;


    }

    @Override
    public boolean isMailAddressExists(String emailAddress){

            if (_userDao.findUserByEmailEquals(emailAddress) != null){
                return true;
            }
            else{
                return false;
            }



    }
}
