package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.EmployerService;
import kodlamaio.HumanResourceManagementSystem.core.enums.activationEnums.UserActivationStatus;
import kodlamaio.HumanResourceManagementSystem.core.enums.userEnums.UserStatus;
import kodlamaio.HumanResourceManagementSystem.core.utils.activationUtils.ActivationNumberGenerator;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.*;
import kodlamaio.HumanResourceManagementSystem.core.utils.validations.abstracts.RuleValidationService;
import kodlamaio.HumanResourceManagementSystem.core.utils.validations.abstracts.UserValidationService;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.EmployerActivationDao;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.EmployerDao;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Employer;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.EmployerActivation;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.EmployerAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployerManager implements EmployerService {

    private EmployerDao _employerDao;
    private EmployerActivationDao _employerActivationDao;
    private UserValidationService _userValidationService;

    @Autowired
    public EmployerManager(EmployerDao _employerDao, EmployerActivationDao _employerActivationDao, UserValidationService _userValidationService, RuleValidationService _ruleValidationService) {
        this._employerDao = _employerDao;
        this._employerActivationDao = _employerActivationDao;
        this._userValidationService = _userValidationService;
        this._ruleValidationService = _ruleValidationService;
    }

    private RuleValidationService _ruleValidationService;


    @Override
    public Result add(EmployerAddDto employerAddDto) {
        try{
            if (employerAddDto.getCompanyName().length()<2){
                return new ErrorResult("Şirket adınız 2 karakterden fazla olmalıdır.");
            }else if(employerAddDto.getPhoneNumber().length()>15 && employerAddDto.getPhoneNumber().length()<2){
                return new ErrorResult("Telefon numaranız en az 3 karakter en fazla 15 karakter olmalıdır.");
            } else if (_userValidationService.isMailAddressExists(employerAddDto.getEmail())){
                return new ErrorResult("Bu kullanıcı sistemde mevcuttur.");
            }else if (!_ruleValidationService.isMailRuleOk(employerAddDto.getEmail())&&!_ruleValidationService.isPasswordRuleOk(employerAddDto.getPassword())){
                return new ErrorResult("Mail adresiniz veya şifreniz kurallara uygun değil");
            }else{
                EmployerActivation employerActivation = new EmployerActivation();
                Employer employer = new Employer();
                employer.setCompanyName(employerAddDto.getCompanyName());
                employer.setWebsite(employerAddDto.getWebsite());
                employer.setPhoneNumber(employerAddDto.getPhoneNumber());
                employer.setEmail(employerAddDto.getEmail());
                employer.setPassword(employerAddDto.getPassword());
                employer.setPasswordRepeat(employerAddDto.getConfirmPassword());
                employer.setUserStatus(UserStatus.Passive);
                employerActivation.setEmployer(employer);
                employerActivation.setActivationNumber(ActivationNumberGenerator.generateActivationNumber());
                employerActivation.setActivationDate(LocalDate.now());
                employerActivation.setUserActivationStatus(UserActivationStatus.Inactivate);
                _employerActivationDao.save(employerActivation);
                _employerDao.save(employer);
                return new SuccessResult("Kayıt başarılı");
            }

        }catch (Exception ex){
            return new ErrorResult("Kayıt yapılamadı.");
        }

    }

    @Override
    public Result update(Employer employer) {
        try{
            var tempEmployer = _employerDao.getById(employer.getId());
            if (tempEmployer.getCompanyName() == null){
                return new ErrorResult("İşveren bulunamadı.");
            } if (employer.getCompanyName().length()<2){
                return new ErrorResult("Şirket adınız 2 karakterden fazla olmalıdır.");
            }else if(employer.getPhoneNumber().length()>15 && employer.getPhoneNumber().length()<2){
                return new ErrorResult("Telefon numaranız en az 3 karakter en fazla 15 karakter olmalıdır.");
            } else if (!_userValidationService.isMailAddressExists(employer.getEmail())){
                return new ErrorResult("Bu kullanıcı sistemde mevcuttur.");

            }else if (!_ruleValidationService.isMailRuleOk(employer.getEmail())&&!_ruleValidationService.isPasswordRuleOk(employer.getPassword())){
                return new ErrorResult("Mail adresiniz veya şifreniz kurallara uygun değil");
            }
            else {
                tempEmployer.setCompanyName(employer.getCompanyName());
                tempEmployer.setPhoneNumber(employer.getPhoneNumber());
                tempEmployer.setWebsite(employer.getWebsite());
                tempEmployer.setEmail(employer.getEmail());
                tempEmployer.setPassword(employer.getPassword());
                tempEmployer.setPasswordRepeat(employer.getPasswordRepeat());
                _employerDao.save(tempEmployer);
                return new SuccessResult("Güncelleme işlemi yapıldı.");
            }
        }catch (Exception ex){
            return new ErrorResult("Güncelleme işlemi yapılamadı.");
        }
    }

    @Override
    public Result delete(int id) {
      try{
          _employerDao.deleteById(id);
          return new SuccessResult("Veri silindi.");

      }catch (Exception ex){
          return new ErrorResult("Veri silme hatası");
      }
    }

    @Override
    public DataResult<List<Employer>> getAll() {
       try{
           return new SuccessDataResult<List<Employer>>(_employerDao.findAll(),"Veriler getirildi.");
       }catch (Exception ex){
           return new ErrorDataResult<>("Veriler getirilemedi.");
       }
    }

    @Override
    public DataResult<Employer> getById(int id) {
        try{
           return new SuccessDataResult<Employer>(_employerDao.getById(id),"İşveren getirildi.");
        }catch (Exception ex){

            return new ErrorDataResult<>("Veri getirilemedi veya veri mevcut değil.");
        }
    }
}
