package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.EmployerService;
import kodlamaio.HumanResourceManagementSystem.business.abstracts.UserService;
import kodlamaio.HumanResourceManagementSystem.business.constants.BusinessMessage;
import kodlamaio.HumanResourceManagementSystem.core.enums.activationEnums.UserActivationStatus;
import kodlamaio.HumanResourceManagementSystem.core.enums.userEnums.UserStatus;
import kodlamaio.HumanResourceManagementSystem.core.utils.TextEditOperation;
import kodlamaio.HumanResourceManagementSystem.core.utils.activationUtils.ActivationNumberGenerator;
import kodlamaio.HumanResourceManagementSystem.core.utils.emailSender.abstracts.EmailSenderService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.*;
import kodlamaio.HumanResourceManagementSystem.core.utils.validations.abstracts.RuleValidationService;
import kodlamaio.HumanResourceManagementSystem.core.utils.validations.abstracts.UserValidationService;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.EmployerActivationDao;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.EmployerDao;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.EmployerUpdateDao;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Employer;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.EmployerActivation;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.EmployerUpdate;
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
    private EmployerUpdateDao _employerUpdateDao;
    private EmailSenderService _emailSenderService;
    private UserService _userService;

    @Autowired
    public EmployerManager(EmployerDao _employerDao, EmployerActivationDao _employerActivationDao, UserValidationService _userValidationService, EmployerUpdateDao _employerUpdateDao, EmailSenderService _emailSenderService, UserService _userService, RuleValidationService _ruleValidationService) {
        this._employerDao = _employerDao;
        this._employerActivationDao = _employerActivationDao;
        this._userValidationService = _userValidationService;
        this._employerUpdateDao = _employerUpdateDao;
        this._emailSenderService = _emailSenderService;
        this._userService = _userService;
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
            } else if (_employerDao.existsByEmail(employerAddDto.getEmail())){
                return new ErrorResult("Bu kullanıcı sistemde mevcuttur.");
            }else if (!_ruleValidationService.isMailRuleOk(employerAddDto.getEmail())||!_ruleValidationService.isPasswordRuleOk(employerAddDto.getPassword())){
                return new ErrorResult("Mail adresiniz veya şifreniz kurallara uygun değil");
            } else if (!_ruleValidationService.isEmployerMailRuleOk(employerAddDto.getEmail(),employerAddDto.getWebsite())){
                return new ErrorResult("Websiteniz ve email domaininiz aynı olmalıdır.");
            }else{
                Employer employer = new Employer();
                employer.setCompanyName(TextEditOperation.makeAllWordsCapitalLetter(employerAddDto.getCompanyName()));
                employer.setWebsite(employerAddDto.getWebsite());
                employer.setPhoneNumber(employerAddDto.getPhoneNumber());
                employer.setEmail(employerAddDto.getEmail());
                employer.setPassword(employerAddDto.getPassword());
                employer.setPasswordRepeat(employerAddDto.getConfirmPassword());
                employer.setUserStatus(UserStatus.Passive);
                EmployerActivation employerActivation = new EmployerActivation();
                employerActivation.setEmployer(employer);
                employerActivation.setActivationNumber(ActivationNumberGenerator.generateActivationNumber());
                employerActivation.setActivationDate(LocalDate.now());
                employerActivation.setUserActivationStatus(UserActivationStatus.Inactivate);
                _employerDao.save(employer);
                _employerActivationDao.save(employerActivation);
                _emailSenderService.sendMail(employer.getEmail());
                return new SuccessResult(BusinessMessage.addOperationSuccess);
            }

        }catch (Exception ex){
            return new ErrorResult(BusinessMessage.addOperationFailed+ex);
        }

    }

    @Override
    public Result update(Long employerId,EmployerAddDto employerAddDto) {
        try{
            if (!_employerDao.existsById(employerId)) {
                return new ErrorResult("İş veren bulunamadı.");
            }
            Employer tempEmployer = _employerDao.getById(employerId);
            if (employerAddDto.getCompanyName().length()<2){
                return new ErrorResult("Şirket adınız 2 karakterden fazla olmalıdır.");
            }else if(employerAddDto.getPhoneNumber().length()>15 || employerAddDto.getPhoneNumber().length()<2){
                return new ErrorResult("Telefon numaranız en az 3 karakter en fazla 15 karakter olmalıdır.");
            } else if (_userValidationService.isMailAddressExists(employerAddDto.getEmail())){
                return new ErrorResult("Bu kullanıcı sistemde mevcut değil.");
            }else if (!_ruleValidationService.isEmployerMailRuleOk(employerAddDto.getEmail(),employerAddDto.getWebsite())){
                return new ErrorResult("Websiteniz ve email domaininiz aynı olmalıdır.");
            }
            else if (!_ruleValidationService.isMailRuleOk(employerAddDto.getEmail()) || !_ruleValidationService.isPasswordRuleOk(employerAddDto.getPassword())){
                return new ErrorResult("Mail adresiniz veya şifreniz kurallara uygun değil");
            }else if (!tempEmployer.getUserStatus().equals(UserStatus.Active)){
                return new ErrorResult("Aktivasyon işlemi yapılmadan güncelleme yapamassınız.");
            }
            else {



                /*
                tempEmployer.setCompanyName(employerAddDto.getCompanyName());
                tempEmployer.setPhoneNumber(employerAddDto.getPhoneNumber());
                tempEmployer.setWebsite(employerAddDto.getWebsite());
                tempEmployer.setEmail(employerAddDto.getEmail());
                tempEmployer.setPassword(employerAddDto.getPassword());
                tempEmployer.setPasswordRepeat(employerAddDto.getConfirmPassword());
                tempEmployer.setUserStatus(UserStatus.Passive);
*/
                EmployerUpdate employerUpdate = new EmployerUpdate();
                employerUpdate.setEmployerId(employerId);
                employerUpdate.setCompanyName(TextEditOperation.makeAllWordsCapitalLetter(employerAddDto.getCompanyName()));
                employerUpdate.setWebsite(employerAddDto.getWebsite());
                employerUpdate.setPhoneNumber(employerAddDto.getPhoneNumber());
                tempEmployer.setUserStatus(UserStatus.WaitingUpdateConfirmation);
                _employerUpdateDao.save(employerUpdate);
                _employerDao.save(tempEmployer);
                return new SuccessResult(BusinessMessage.updateOperationSuccess);
            }
        }catch (Exception ex){
            return new ErrorResult(BusinessMessage.updateOperationFailed+ex);
        }
    }

    @Override
    public Result delete(Long id) {
      try{
          if (!_employerDao.existsById(id)){
              return new ErrorResult("iş veren bulunamadı");
          }else {
              _employerDao.deleteById(id);
              return new SuccessResult(BusinessMessage.deleteOperationSuccess);
          }
      }catch (Exception ex){
          return new ErrorResult(BusinessMessage.deleteOperationFailed+ex);
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
    public DataResult<Employer> getById(Long id) {
        try{
           return new SuccessDataResult<Employer>(_employerDao.getById(id),"İşveren getirildi.");
        }catch (Exception ex){

            return new ErrorDataResult<>("Veri getirilemedi veya veri mevcut değil.");
        }
    }
}
