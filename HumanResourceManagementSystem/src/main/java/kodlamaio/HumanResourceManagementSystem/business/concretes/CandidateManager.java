package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.CandidateService;
import kodlamaio.HumanResourceManagementSystem.core.enums.activationEnums.UserActivationStatus;
import kodlamaio.HumanResourceManagementSystem.core.enums.userEnums.UserStatus;
import kodlamaio.HumanResourceManagementSystem.core.utils.activationUtils.ActivationNumberGenerator;
import kodlamaio.HumanResourceManagementSystem.core.utils.mernisPersonValidations.abstracts.CandidateValidationService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.*;
import kodlamaio.HumanResourceManagementSystem.core.utils.validations.abstracts.RuleValidationService;
import kodlamaio.HumanResourceManagementSystem.core.utils.validations.abstracts.UserValidationService;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.CandidateActivationDao;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.CandidateDao;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Candidate;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.CandidateActivation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CandidateManager implements CandidateService {

    private CandidateDao _candidateDao;
    private CandidateActivationDao _candidateActivationDao;
    private CandidateValidationService _candidateValidationService;
    private UserValidationService _userValidationService;
    private RuleValidationService _ruleValidationService;

    @Autowired
    public CandidateManager(CandidateDao _candidateDao, CandidateActivationDao _candidateActivationDao, CandidateValidationService _candidateValidationService, UserValidationService _userValidationService, RuleValidationService _ruleValidationService) {
        this._candidateDao = _candidateDao;
        this._candidateActivationDao = _candidateActivationDao;
        this._candidateValidationService = _candidateValidationService;
        this._userValidationService = _userValidationService;
        this._ruleValidationService = _ruleValidationService;
    }

    @Override
    public Result add(Candidate candidate) {
        try {
            if (candidate.getNationalIdentityNumber().length()!=11){
                return new ErrorResult("11 Haneli Türkiye Cumhuriyeti Kimlik Numaranızı giriniz.");
            } else if (!_candidateValidationService.isRealPerson(candidate.getBirthDate().getYear(),candidate.getFirstName(),candidate.getLastName(),candidate.getNationalIdentityNumber())) {
                return new ErrorResult("Kimlik doğrulanamadı.");
            }else if(_userValidationService.isMailAddressExists(candidate.getEmail())){
                return new ErrorResult("Bu mail adresi sistemde kayıtlı.");
            } else if(!_ruleValidationService.isMailRuleOk(candidate.getEmail())&&!_ruleValidationService.isPasswordRuleOk(candidate.getPassword())){
                return new ErrorResult("Mail adresiniz veya şifreniz kurallara uygun değil");
            }else if (candidate.getFirstName().length()<=2&&candidate.getLastName().length()<=2){
                return new ErrorResult("Adınız ve soyadınız 2 karakterden fazla olmalıdır.");
            }else if(!candidate.getPassword().equals(candidate.getPasswordRepeat())){
                return new ErrorResult("Şifreniz uyuşmuyor.");
           }
            else {
                CandidateActivation activation = new CandidateActivation();
                candidate.setUserStatus(UserStatus.Passive);
                activation.setCandidate(candidate);
                activation.setActivationDate(LocalDate.now());
                activation.setActivationNumber(ActivationNumberGenerator.generateActivationNumber());
                activation.setUserActivationStatus(UserActivationStatus.Inactivate);
                _candidateActivationDao.save(activation);
                _candidateDao.save(candidate);
                return new SuccessResult("Kayıt başarılı "+" Aktivasyon kodunuz "+candidate.getEmail()+" adresinize gönderilmiştir.");
            }


        }catch (Exception ex){
            return new ErrorResult("Kayıt yapılamadı. "+ex);
        }
    }

    @Override
    public Result update(Candidate candidate) {
        try{
            var tempCandidate = _candidateDao.getById(candidate.getId());
            if (!_userValidationService.isUserExists(tempCandidate.getId())){
                return new ErrorResult("Kullanıcı bulunamadı.");
            }
            else {

                tempCandidate.setEmail(candidate.getEmail());
                tempCandidate.setPassword(candidate.getPassword());
                tempCandidate.setPasswordRepeat(candidate.getPasswordRepeat());
                tempCandidate.setFirstName(candidate.getFirstName());
                tempCandidate.setLastName(candidate.getLastName());
                tempCandidate.setBirthDate(candidate.getBirthDate());
                tempCandidate.setNationalIdentityNumber(candidate.getNationalIdentityNumber());
                _candidateDao.save(tempCandidate);
                return new SuccessResult("Güncelleme işlemi yapıldı.");
            }
        }catch (Exception ex){
            return new ErrorResult("Güncelleme işlemi yapılamadı.");
        }

    }

    @Override
    public Result delete(int id) {
        try{
            var temp = _candidateDao.getById(id);

            //Tc kimlik benzersiz ve girilmek zorunda olduğu için daima kayıtlı olmak zorunda veri silirken kontrol amacıyla kullanılabilir.
            if (temp.getNationalIdentityNumber() != null)
            {
                _candidateDao.delete(temp);
                return new SuccessResult("Silme işlemi yapıldı.");
            }
            else{
                return new ErrorResult("Kullanıcı bulunamadı.");
            }

        }catch (Exception ex){
            return new ErrorResult("Silme işlemi yapılamadı");
        }

    }

    @Override
    public DataResult<List<Candidate>> getAll() {
        try{
            return new SuccessDataResult<List<Candidate>>(_candidateDao.findAll(),"Veriler getirildi.");
        }catch (Exception ex){
            return new ErrorDataResult<List<Candidate>>("Veri getirme hatası");
        }

    }

    @Override
    public DataResult<Candidate> getById(int id) {
       try{
          return new SuccessDataResult<Candidate>(_candidateDao.getById(id),"Veri getirildi.");
       }catch (Exception ex){
           return new ErrorDataResult<Candidate>("Veri getirme hatası");
       }
    }

    @Override
    public DataResult<Candidate> getByNationalIdentityNumber(String nationalIdentityNumber) {
        try{
            return new SuccessDataResult<Candidate>(_candidateDao.findByNationalIdentityNumber(nationalIdentityNumber),"Kullanıcı getirildi.");
        }catch (Exception ex){
            return new ErrorDataResult<>("Kayıt bulunamadı yada kayıt getirme hatası");
        }
    }

}
