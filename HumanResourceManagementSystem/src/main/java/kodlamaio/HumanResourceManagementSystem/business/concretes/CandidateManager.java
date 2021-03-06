package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.CandidateService;
import kodlamaio.HumanResourceManagementSystem.business.abstracts.RoleService;
import kodlamaio.HumanResourceManagementSystem.business.abstracts.UserService;
import kodlamaio.HumanResourceManagementSystem.business.constants.BusinessMessage;
import kodlamaio.HumanResourceManagementSystem.core.enums.activationEnums.UserActivationStatus;
import kodlamaio.HumanResourceManagementSystem.core.enums.userEnums.UserStatus;
import kodlamaio.HumanResourceManagementSystem.core.utils.TextEditOperation;
import kodlamaio.HumanResourceManagementSystem.core.utils.activationUtils.ActivationNumberGenerator;
import kodlamaio.HumanResourceManagementSystem.core.utils.emailSender.abstracts.EmailSenderService;
import kodlamaio.HumanResourceManagementSystem.core.utils.mernisPersonValidations.abstracts.CandidateValidationService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.*;
import kodlamaio.HumanResourceManagementSystem.core.utils.validations.abstracts.RuleValidationService;
import kodlamaio.HumanResourceManagementSystem.core.utils.validations.abstracts.UserValidationService;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.CandidateActivationDao;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.CandidateDao;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Candidate;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.CandidateActivation;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.CandidateAddDto;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.CandidateUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private EmailSenderService _emailSenderService;
    private PasswordEncoder _passwordEncoder;
    private RoleService _roleService;

    @Autowired
    public CandidateManager(CandidateDao _candidateDao, CandidateActivationDao _candidateActivationDao, CandidateValidationService _candidateValidationService, UserValidationService _userValidationService, RuleValidationService _ruleValidationService, EmailSenderService _emailSenderService, PasswordEncoder _passwordEncoder, RoleService _roleService) {
        this._candidateDao = _candidateDao;
        this._candidateActivationDao = _candidateActivationDao;
        this._candidateValidationService = _candidateValidationService;
        this._userValidationService = _userValidationService;
        this._ruleValidationService = _ruleValidationService;
        this._emailSenderService = _emailSenderService;
        this._passwordEncoder = _passwordEncoder;
        this._roleService = _roleService;
    }

    @Override
    public Result add(CandidateAddDto candidateAddDto) {
        try {
            if (candidateAddDto.getNationalIdentityNumber().length()!=11){
                return new ErrorResult("11 Haneli T??rkiye Cumhuriyeti Kimlik Numaran??z?? giriniz.");
            } else if (!_candidateValidationService.isRealPerson(candidateAddDto.getBirthDate().getYear(),candidateAddDto.getFirstName(),candidateAddDto.getLastName(),candidateAddDto.getNationalIdentityNumber())) {
                return new ErrorResult("Kimlik do??rulanamad??.");
            }else if(_candidateDao.existsCandidateByEmail(candidateAddDto.getEmail())){
                return new ErrorResult("Bu mail adresi sistemde kay??tl??.");
            } else if(!_ruleValidationService.isMailRuleOk(candidateAddDto.getEmail())&&!_ruleValidationService.isPasswordRuleOk(candidateAddDto.getPassword())){
                return new ErrorResult("Mail adresiniz veya ??ifreniz kurallara uygun de??il");
            }else if (candidateAddDto.getFirstName().length()<=2&&candidateAddDto.getLastName().length()<=2){
                return new ErrorResult("Ad??n??z ve soyad??n??z 2 karakterden fazla olmal??d??r.");
            }else if(!candidateAddDto.getPassword().equals(candidateAddDto.getPasswordConfirm())){
                return new ErrorResult("??ifreniz uyu??muyor.");
           }
            else {
                Candidate candidate = new Candidate();
                candidate.setFirstName(TextEditOperation.makeAllWordsCapitalLetter(candidateAddDto.getFirstName()));
                candidate.setLastName(TextEditOperation.makeAllWordsCapitalLetter(candidateAddDto.getLastName()));
                candidate.setBirthDate(candidateAddDto.getBirthDate());
                candidate.setNationalIdentityNumber(candidateAddDto.getNationalIdentityNumber());
                candidate.setEmail(candidateAddDto.getEmail());
                candidate.setPassword(_passwordEncoder.encode(candidateAddDto.getPassword()));
                candidate.setPasswordRepeat(_passwordEncoder.encode(candidateAddDto.getPasswordConfirm()));
                CandidateActivation activation = new CandidateActivation();
                candidate.setUserStatus(UserStatus.Passive);
                activation.setCandidate(candidate);
                activation.setActivationDate(LocalDate.now());
                activation.setActivationNumber(ActivationNumberGenerator.generateActivationNumber());
                activation.setUserActivationStatus(UserActivationStatus.Inactivate);
                    _candidateActivationDao.save(activation);
                    _candidateDao.save(candidate);
                    _emailSenderService.sendMail(candidate.getEmail());
                _roleService.addRoleToUser(candidate.getEmail(), "ROLE_CANDIDATE");



                return new SuccessResult(BusinessMessage.addOperationSuccess);
            }


        }catch (Exception ex){
            return new ErrorResult(BusinessMessage.addOperationFailed +" "+ ex);
        }
    }

    @Override
    public Result update(Long id,CandidateUpdateDto candidateUpdateDto) {
        try{
            var tempCandidate = _candidateDao.getById(id);
            if (!_candidateDao.existsById(tempCandidate.getId())){
                return new ErrorResult("Kullan??c?? bulunamad??.");
            }
            else {
                if (candidateUpdateDto.getNationalIdentityNumber().length()!=11){
                    return new ErrorResult("11 Haneli T??rkiye Cumhuriyeti Kimlik Numaran??z?? giriniz.");
                } else if (!_candidateValidationService.isRealPerson(candidateUpdateDto.getBirthDate().getYear(),candidateUpdateDto.getFirstName(),candidateUpdateDto.getLastName(),candidateUpdateDto.getNationalIdentityNumber())) {
                    return new ErrorResult("Kimlik do??rulanamad??.");
                }else if(_userValidationService.isMailAddressExists(candidateUpdateDto.getEmail())){
                    return new ErrorResult("Bu mail adresi sistemde kay??tl??.");
                } else if(!_ruleValidationService.isMailRuleOk(candidateUpdateDto.getEmail())&&!_ruleValidationService.isPasswordRuleOk(candidateUpdateDto.getPassword())){
                    return new ErrorResult("Mail adresiniz veya ??ifreniz kurallara uygun de??il");
                }else if (candidateUpdateDto.getFirstName().length()<=2&&candidateUpdateDto.getLastName().length()<=2){
                    return new ErrorResult("Ad??n??z ve soyad??n??z 2 karakterden fazla olmal??d??r.");
                }else if(!candidateUpdateDto.getPassword().equals(candidateUpdateDto.getPasswordConfirm())){
                    return new ErrorResult("??ifreniz uyu??muyor.");
                }

                tempCandidate.setEmail(candidateUpdateDto.getEmail());
                tempCandidate.setPassword(candidateUpdateDto.getPassword());
                tempCandidate.setPasswordRepeat(candidateUpdateDto.getPasswordConfirm());
                tempCandidate.setFirstName(TextEditOperation.makeCapitalLetter(candidateUpdateDto.getFirstName()));
                tempCandidate.setLastName(TextEditOperation.makeCapitalLetter(candidateUpdateDto.getLastName()));
                tempCandidate.setBirthDate(candidateUpdateDto.getBirthDate());
                tempCandidate.setNationalIdentityNumber(candidateUpdateDto.getNationalIdentityNumber());
                _candidateDao.save(tempCandidate);
                return new SuccessResult("G??ncelleme i??lemi yap??ld??.");
            }
        }catch (Exception ex){
            return new ErrorResult("G??ncelleme i??lemi yap??lamad??.");
        }

    }

    @Override
    public Result delete(Long id) {
        try{
            var temp = _candidateDao.getById(id);

            //Tc kimlik benzersiz ve girilmek zorunda oldu??u i??in daima kay??tl?? olmak zorunda veri silirken kontrol amac??yla kullan??labilir.
            if (_candidateDao.existsById(temp.getId()))
            {
                _candidateDao.delete(temp);
                return new SuccessResult("Silme i??lemi yap??ld??.");
            }
            else{
                return new ErrorResult("Kullan??c?? bulunamad??.");
            }

        }catch (Exception ex){
            return new ErrorResult("Silme i??lemi yap??lamad??");
        }

    }

    @Override
    public Result updateTest(Candidate candidate) {
       try{
           if (!_candidateDao.existsById(candidate.getId())){
               return new ErrorResult("Kullan??c?? bulunamad??.");
           }else{
               if (candidate.getNationalIdentityNumber().length()!=11){
                   return new ErrorResult("11 Haneli T??rkiye Cumhuriyeti Kimlik Numaran??z?? giriniz.");
               } else if (!_candidateValidationService.isRealPerson(candidate.getBirthDate().getYear(),candidate.getFirstName(),candidate.getLastName(),candidate.getNationalIdentityNumber())) {
                   return new ErrorResult("Kimlik do??rulanamad??.");
               }else if(_userValidationService.isMailAddressExists(candidate.getEmail())){
                   return new ErrorResult("Bu mail adresi sistemde kay??tl??.");
               } else if(!_ruleValidationService.isMailRuleOk(candidate.getEmail())&&!_ruleValidationService.isPasswordRuleOk(candidate.getPassword())){
                   return new ErrorResult("Mail adresiniz veya ??ifreniz kurallara uygun de??il");
               }else if (candidate.getFirstName().length()<=2&&candidate.getLastName().length()<=2){
                   return new ErrorResult("Ad??n??z ve soyad??n??z 2 karakterden fazla olmal??d??r.");
               }/*else if(!candidate.getPassword().equals(candidate.getPasswordRepeat())){
                   return new ErrorResult("??ifreniz uyu??muyor.");
               }
               */
               var candidateTest = _candidateDao.getById(candidate.getId());
               candidateTest.setFirstName(candidate.getFirstName());
               candidateTest.setLastName(candidate.getLastName());
               candidateTest.setEmail(candidate.getEmail());
               candidateTest.setPassword(candidate.getPassword());
               candidateTest.setPasswordRepeat(candidate.getPasswordRepeat());
               candidateTest.setBirthDate(candidate.getBirthDate());
               candidateTest.setNationalIdentityNumber(candidate.getNationalIdentityNumber());
               _candidateDao.save(candidateTest);
               return new SuccessResult(BusinessMessage.updateOperationSuccess);
           }
       }catch (Exception ex){
           return new ErrorResult(BusinessMessage.updateOperationFailed+ex);
       }
    }

    @Override
    public DataResult<List<Candidate>> getAll() {
        try{
            return new SuccessDataResult<List<Candidate>>(_candidateDao.findAll(),"Veriler getirildi.");
        }catch (Exception ex){
            return new ErrorDataResult<List<Candidate>>("Veri getirme hatas??");
        }

    }

    @Override
    public DataResult<Candidate> getById(Long id) {
       try{
          return new SuccessDataResult<Candidate>(_candidateDao.getById(id),"Veri getirildi.");
       }catch (Exception ex){
           return new ErrorDataResult<Candidate>("Veri getirme hatas??");
       }
    }

    @Override
    public DataResult<Candidate> getByNationalIdentityNumber(String nationalIdentityNumber) {
        try{
            return new SuccessDataResult<Candidate>(_candidateDao.findByNationalIdentityNumber(nationalIdentityNumber),"Kullan??c?? getirildi.");
        }catch (Exception ex){
            return new ErrorDataResult<>("Kay??t bulunamad?? yada kay??t getirme hatas??");
        }
    }

}
