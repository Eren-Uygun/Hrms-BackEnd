package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.ActivationService;
import kodlamaio.HumanResourceManagementSystem.core.enums.activationEnums.UserActivationStatus;
import kodlamaio.HumanResourceManagementSystem.core.enums.jobAdvertisementEnums.JobAdvertisementActivationStatus;
import kodlamaio.HumanResourceManagementSystem.core.enums.jobAdvertisementEnums.JobAdvertisementStatus;
import kodlamaio.HumanResourceManagementSystem.core.enums.userEnums.UserStatus;
import kodlamaio.HumanResourceManagementSystem.core.utils.activationUtils.ActivationNumberGenerator;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.ErrorResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.SuccessResult;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.*;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.*;
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
    private JobAdvertisementActivationWithEmployeeDao _JobAdvertisementActivationWithEmployeeDao;
    private JobAdvertisementDao _JobAdvertisementDao;
    private EmployerUpdateDao _employerUpdateDao;

    @Autowired
    public ActivationManager(ActivationDao _activationDao, UserDao _userDao, EmployerActivationByEmployeeDao _employerActivationByEmployeeDao, EmployerDao _employerDao, HrmsEmployeeDao _hrmsEmployeeDao, EmployerActivationDao _employerActivationDao, JobAdvertisementActivationWithEmployeeDao _JobAdvertisementActivationWithEmployeeDao, JobAdvertisementDao _JobAdvertisementDao, EmployerUpdateDao _employerUpdateDao) {
        this._activationDao = _activationDao;
        this._userDao = _userDao;
        this._employerActivationByEmployeeDao = _employerActivationByEmployeeDao;
        this._employerDao = _employerDao;
        this._hrmsEmployeeDao = _hrmsEmployeeDao;
        this._employerActivationDao = _employerActivationDao;
        this._JobAdvertisementActivationWithEmployeeDao = _JobAdvertisementActivationWithEmployeeDao;
        this._JobAdvertisementDao = _JobAdvertisementDao;
        this._employerUpdateDao = _employerUpdateDao;
    }

    @Override
    public Result activateByActivationCode(String activationCode) {
        try{
        var emailActivation = _activationDao.findByActivationNumber(activationCode);
        var activatedUser1 = _userDao.getById(emailActivation.getId());

        if (emailActivation.getActivationNumber() == null || activatedUser1.getEmail() == null){
            return new ErrorResult("Aktivasyon kodu hatal?? veya Aktivasyonu yap??lacak kullan??c?? bulunamad??.");
        }else{
        emailActivation.setUserActivationStatus(UserActivationStatus.EmailActivation);
        activatedUser1.setUserStatus(UserStatus.Active);
        _userDao.save(activatedUser1);
        _activationDao.save(emailActivation);
        return new SuccessResult("Email aktivasyonu yap??ld??.");
        }
        }catch (Exception ex){
            return new ErrorResult("Aktivasyon yap??lamad??.");
        }
    }

    @Override
    public Result manualEmployerActivation(Long employerId, Long employeeId) {
        try{
            var employerActivation = _employerDao.getById(employerId);
            var employee = _hrmsEmployeeDao.getById(employeeId);
            if (employerActivation.getId() != employerId && employee.getId() != employeeId){
                return new ErrorResult("????veren veya personel bulunamad??.");
            }else if(employerActivation.getUserStatus()==UserStatus.Active){
                return new ErrorResult("Kullan??c?? aktif");
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
            return new SuccessResult(employerId+"' id No'lu kullan??c??n??n "+employeeId+"' id No'lu sistem personeli taraf??ndan aktivasyonu yap??lm????t??r.");
            }
        }catch (Exception ex){
            return new ErrorResult("Manuel aktivasyon yap??lamad??.");
        }

    }

    @Override
    public Result activateEmployerByActivationCode(String activationCode) {
        try{
            var tempActivation = _employerActivationDao.findByActivationNumber(activationCode);
            var activation = _employerDao.getById(tempActivation.getEmployer().getId());
            if (!tempActivation.getActivationNumber().equals(activationCode)){
                return new ErrorResult("Aktivasyon yap??lamad?? veya aktivasyon zaten yap??ld??.");
            }else{
                tempActivation.setUserActivationStatus(UserActivationStatus.EmailActivation);
                tempActivation.setActivationDate(LocalDate.now());
                activation.setUserStatus(UserStatus.Active);
                _employerDao.save(activation);
                _employerActivationDao.save(tempActivation);

                return new SuccessResult("Email aktivasyon i??lemi yap??ld??.");
            }

        }catch (Exception ex){
            return new ErrorResult("Aktivasyon Hatas?? "+ex);
        }
    }

    @Override
    public Result activateHrmsEmployeeByActivationCode(String activationCode) {

        //Planned , but later !!!
        return null;
    }

    @Override
    public Result jobAdvertisementActivation(Long employeeId, Long jobAdvertisementId) {
       try{
           if (!_hrmsEmployeeDao.existsById(employeeId)){
               return new ErrorResult("Personel bulunamad??.");
           }else if (!_JobAdvertisementDao.existsById(jobAdvertisementId)){
               return new ErrorResult("??lan bulunamad??.");
           }else{
               JobAdvertisementActivationByEmployee jobAdvertisementActivationByEmployee = _JobAdvertisementActivationWithEmployeeDao.getById(jobAdvertisementId);
               jobAdvertisementActivationByEmployee.setHrmsEmployee(_hrmsEmployeeDao.getById(employeeId));
               jobAdvertisementActivationByEmployee.setAdvertisementActivationDate(LocalDate.now());
               JobAdvertisement jobAdvertisement = _JobAdvertisementDao.getById(jobAdvertisementId);
               jobAdvertisement.setIsJobAdvertisementStatusActive(true);
               _JobAdvertisementDao.save(jobAdvertisement);
               _JobAdvertisementActivationWithEmployeeDao.save(jobAdvertisementActivationByEmployee);

               return new SuccessResult("Aktivasyon yap??ld??.");

           }
       }catch (Exception ex){
           return new ErrorResult("???? ilan?? aktivasyon hatas?? "+ex);
       }
    }

    @Override
    public Result employerUpdateConfirmation(Long employeeId, Long employerId) {
        if (!_employerUpdateDao.existsByEmployerId(employerId)){
            return new ErrorResult("G??ncellenecek firma bulunamad??.");
        }else if (!_hrmsEmployeeDao.existsById(employeeId)){
            return new ErrorResult("Personel bulunamad??.");
        }
        var tempEmployerUpdate = _employerUpdateDao.getByEmployerId(employerId);
        var updatedEmployer = _employerDao.getById(employerId);

        updatedEmployer.setCompanyName(tempEmployerUpdate.getCompanyName());
        updatedEmployer.setPhoneNumber(tempEmployerUpdate.getPhoneNumber());
        updatedEmployer.setWebsite(tempEmployerUpdate.getWebsite());
        updatedEmployer.setUserStatus(UserStatus.Active);
        _employerDao.save(updatedEmployer);
        _employerUpdateDao.delete(tempEmployerUpdate);
        return new SuccessResult("??irket bilgilerinin g??ncelleme onaylama i??lemi "+employeeId+" No'lu personel taraf??ndan yap??lm????t??r.");
    }

    /*
    @Override
    public Result jobAdvertisementActivationByHrmsEmployee(int hrmsEmployeeId,String activationNumber,int advertisementNumber) {
    if (!_hrmsEmployeeDao.existsById(hrmsEmployeeId)){
        return new ErrorResult("Personel bulunamad??.");
    }
    JobAdvertisementActivationByEmployee  jobAdvertisementActivationByEmployee = _JobAdvertisementActivationWithEmployeeDao.findByActivationNumber(activationNumber);
    jobAdvertisementActivationByEmployee.setHrmsEmployee(_hrmsEmployeeDao.getById(hrmsEmployeeId));
    jobAdvertisementActivationByEmployee.setJobAdvertisementActivationStatus(JobAdvertisementActivationStatus.Active);
    jobAdvertisementActivationByEmployee.setAdvertisementActivationDate(LocalDate.now());
    _JobAdvertisementActivationWithEmployeeDao.save(jobAdvertisementActivationByEmployee);
    JobAdvertisement jobAdvertisement = _JobAdvertisementDao.getById(advertisementNumber);
    jobAdvertisement.setJobAdvertisementStatus(JobAdvertisementStatus.Active);
    _JobAdvertisementDao.save(jobAdvertisement);
    return new  SuccessResult("??lan aktivasyonu yap??ld??.");
    }
    */

}
