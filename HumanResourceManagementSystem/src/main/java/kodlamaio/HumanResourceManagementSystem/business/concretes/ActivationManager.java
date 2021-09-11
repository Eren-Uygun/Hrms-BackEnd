package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.ActivationService;
import kodlamaio.HumanResourceManagementSystem.core.enums.activationEnums.UserActivationStatus;
import kodlamaio.HumanResourceManagementSystem.core.enums.userEnums.UserStatus;
import kodlamaio.HumanResourceManagementSystem.core.utils.activationUtils.ActivationNumberGenerator;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.ErrorResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.SuccessResult;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.*;
import kodlamaio.HumanResourceManagementSystem.entities.abstracts.Activation;
import kodlamaio.HumanResourceManagementSystem.entities.abstracts.User;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Employer;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.EmployerActivation;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.EmployerActivationByEmployee;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.HrmsEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ActivationManager implements ActivationService {

    private ActivationDao _activationDao;
    private UserDao _userDao;
    private EmployerActivationByEmployeeDao _employerActivationByEmployeeDao;
    private EmployerDao _employerDao;
    private HrmsEmployeeDao _hrmsEmployeeDao;
    private EmployerActivationDao _employerActivationDao;

@Autowired
    public ActivationManager(ActivationDao _activationDao, UserDao _userDao, EmployerActivationByEmployeeDao _employerActivationByEmployeeDao, EmployerDao _employerDao, HrmsEmployeeDao _hrmsEmployeeDao, EmployerActivationDao _employerActivationDao) {
        this._activationDao = _activationDao;
        this._userDao = _userDao;
        this._employerActivationByEmployeeDao = _employerActivationByEmployeeDao;
        this._employerDao = _employerDao;
        this._hrmsEmployeeDao = _hrmsEmployeeDao;
        this._employerActivationDao = _employerActivationDao;
    }

    @Override
    public Result activateByActivationCode(String activationCode) {
        try{
        var emailActivation = _activationDao.findByActivationNumber(activationCode);
        var activatedUser1 = _userDao.getById(emailActivation.getId());

        if (emailActivation.getActivationNumber() == null || activatedUser1.getEmail() == null){
            return new ErrorResult("Aktivasyon kodu hatalı veya Aktivasyonu yapılacak kullanıcı bulunamadı.");
        }else{
        emailActivation.setUserActivationStatus(UserActivationStatus.EmailActivation);
        activatedUser1.setUserStatus(UserStatus.Active);
        _userDao.save(activatedUser1);
        _activationDao.save(emailActivation);
        return new SuccessResult("Email aktivasyonu yapıldı.");
        }
        }catch (Exception ex){
            return new ErrorResult("Aktivasyon yapılamadı.");
        }
    }

    @Override
    public Result manualEmployerActivation(int employerId, int employeeId) {
        try{
            var employerActivation = _employerDao.getById(employerId);
            var employee = _hrmsEmployeeDao.getById(employeeId);
            if (employerActivation.getId() != employerId && employee.getId() != employeeId){
                return new ErrorResult("İşveren veya personel bulunamadı.");
            }else if(employerActivation.getUserStatus()==UserStatus.Active){
                return new ErrorResult("Kullanıcı aktif");
            }
            else{
            employerActivation.setUserStatus(UserStatus.Active);
            EmployerActivationByEmployee activation = new EmployerActivationByEmployee();
            activation.setActivationNumber(ActivationNumberGenerator.generateActivationNumber());
            activation.setActivationDate(LocalDate.now());
            activation.setEmployer(employerActivation);
            activation.setEmployee(employee);
            activation.setUserActivationStatus(UserActivationStatus.ManualActivation);
            _employerDao.save(employerActivation);
            _employerActivationByEmployeeDao.save(activation);
            return new SuccessResult(employerId+"' id No'lu kullanıcının "+employeeId+"' id No'lu sistem personeli tarafından aktivasyonu yapılmıştır.");
            }
        }catch (Exception ex){
            return new ErrorResult("Manuel aktivasyon yapılamadı.");
        }

    }

    @Override
    public Result activateEmployerByActivationCode(String activationCode) {
        try{
            var tempActivation = _employerActivationDao.findByActivationNumber(activationCode);
            var activation = _employerDao.getById(tempActivation.getEmployer().getId());
            if (!tempActivation.getActivationNumber().equals(activationCode)){
                return new ErrorResult("Aktivasyon yapılamadı veya aktivasyon zaten yapıldı.");
            }else{
                tempActivation.setUserActivationStatus(UserActivationStatus.EmailActivation);
                tempActivation.setActivationDate(LocalDate.now());
                activation.setUserStatus(UserStatus.Active);
                _employerDao.save(activation);
                _employerActivationDao.save(tempActivation);

                return new SuccessResult("Email aktivasyon işlemi yapıldı.");
            }

        }catch (Exception ex){
            return new ErrorResult("Aktivasyon Hatası "+ex);
        }
    }

    @Override
    public Result activateHrmsEmployeeByActivationCode(String activationCode) {
        return null;
    }
}
