package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.HrmsEmployeeService;
import kodlamaio.HumanResourceManagementSystem.business.abstracts.RoleService;
import kodlamaio.HumanResourceManagementSystem.business.abstracts.UserService;
import kodlamaio.HumanResourceManagementSystem.core.enums.activationEnums.UserActivationStatus;
import kodlamaio.HumanResourceManagementSystem.core.enums.userEnums.UserStatus;
import kodlamaio.HumanResourceManagementSystem.core.utils.TextEditOperation;
import kodlamaio.HumanResourceManagementSystem.core.utils.activationUtils.ActivationNumberGenerator;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.*;
import kodlamaio.HumanResourceManagementSystem.core.utils.validations.abstracts.RuleValidationService;
import kodlamaio.HumanResourceManagementSystem.core.utils.validations.abstracts.UserValidationService;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.HrmsEmployeeDao;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.HrmsEmployee;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.HrmsEmployeeActivation;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.HrmsEmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HrmsEmployeeManager implements HrmsEmployeeService {

    private HrmsEmployeeDao _hrmsEmployeeDao;
    private UserValidationService _userValidationService;
    private RuleValidationService _ruleValidationService;
    private RoleService _roleService;
    private PasswordEncoder _passwordEncoder;

     @Autowired
    public HrmsEmployeeManager(HrmsEmployeeDao _hrmsEmployeeDao, UserValidationService _userValidationService, RuleValidationService _ruleValidationService, RoleService _roleService, PasswordEncoder _passwordEncoder) {
        this._hrmsEmployeeDao = _hrmsEmployeeDao;
        this._userValidationService = _userValidationService;
        this._ruleValidationService = _ruleValidationService;
        this._roleService = _roleService;
        this._passwordEncoder = _passwordEncoder;
    }

    @Override
    public Result add(HrmsEmployeeDto employeeDto) {
        try{
            if(employeeDto.getFirstName().length()<2 && employeeDto.getLastName().length()<2){
                return new ErrorResult("Ad??n??z ve soyad??n??z 2 karakterden fazla olmal??d??r.");
            }
           else if (_hrmsEmployeeDao.existsByEmail(employeeDto.getEmail())){
                return new ErrorResult("Kullan??c?? sistemde mevcut");
            }
            else if(!_ruleValidationService.isMailRuleOk(employeeDto.getEmail())&&!_ruleValidationService.isPasswordRuleOk(employeeDto.getPassword())){
                return new ErrorResult("Mail adresiniz veya ??ifreniz kurallara uygun de??il");
            }
            else if(!employeeDto.getPassword().equals(employeeDto.getPasswordConfirm())){
                return new ErrorResult("Girdi??iniz ??ifreler uyu??muyor.");
            }
            else{
                HrmsEmployee employee = new HrmsEmployee();
                employee.setFirstName(TextEditOperation.makeAllWordsCapitalLetter(employeeDto.getFirstName()));
                employee.setLastName(TextEditOperation.makeAllWordsCapitalLetter(employeeDto.getLastName()));
                employee.setBirthDate(employeeDto.getBirthDate());
                employee.setEmail(employeeDto.getEmail());
                employee.setPassword(_passwordEncoder.encode(employeeDto.getPassword()));
                employee.setDepartment(TextEditOperation.makeAllWordsCapitalLetter(employeeDto.getDepartment()));
                employee.setPasswordRepeat(_passwordEncoder.encode(employeeDto.getPasswordConfirm()));
                employee.setUserStatus(UserStatus.Active);
                HrmsEmployeeActivation hrmsEmployeeActivation = new HrmsEmployeeActivation();
                hrmsEmployeeActivation.setEmployee(employee);
                hrmsEmployeeActivation.setActivationDate(LocalDate.now());
                hrmsEmployeeActivation.setActivationNumber(ActivationNumberGenerator.generateActivationNumber());
                hrmsEmployeeActivation.setUserActivationStatus(UserActivationStatus.ManualActivation);


                _hrmsEmployeeDao.save(employee);
                _roleService.addRoleToUser(employeeDto.getEmail(), "ROLE_ADMIN");
                return new SuccessResult("Personel sisteme eklendi ve aktif hale getirildi.");
            }

        }catch (Exception ex){
            return new ErrorResult("Ekleme i??lemi hatal??" +ex);
        }
    }

    @Override
    public Result update(Long id,HrmsEmployeeDto employeeDto) {
        try{

            if (!_hrmsEmployeeDao.existsById(id)){
                return new ErrorResult("Kullan??c?? sistemde mevcut de??il");
            } else if(employeeDto.getFirstName().length()<2||employeeDto.getLastName().length()<2){
                return new ErrorResult("Ad??n??z ve soyad??n??z 2 karakterden fazla olmal??d??r.");
            } else if(!_ruleValidationService.isMailRuleOk(employeeDto.getEmail())&&!_ruleValidationService.isPasswordRuleOk(employeeDto.getPassword())){
                return new ErrorResult("Mail adresiniz veya ??ifreniz kurallara uygun de??il");
            } else if(!employeeDto.getPassword().equals(employeeDto.getPasswordConfirm())){
                return new ErrorResult("Girdi??iniz ??ifreler uyu??muyor.");
            }
            else{
                HrmsEmployee temp = _hrmsEmployeeDao.getById(id);
                temp.setEmail(employeeDto.getEmail());
                temp.setPassword(employeeDto.getPassword());
                temp.setPasswordRepeat(employeeDto.getPasswordConfirm());
                temp.setFirstName(TextEditOperation.makeAllWordsCapitalLetter(employeeDto.getFirstName()));
                temp.setLastName(TextEditOperation.makeAllWordsCapitalLetter(employeeDto.getLastName()));
                temp.setDepartment(TextEditOperation.makeAllWordsCapitalLetter(employeeDto.getDepartment()));
                temp.setBirthDate(employeeDto.getBirthDate());
                temp.setUserStatus(UserStatus.Active);
                _hrmsEmployeeDao.save(temp);
                return new SuccessResult("Personel g??ncellendi.");
            }

        }catch (Exception ex){
            return new ErrorResult("G??ncelleme i??lemi hatal??");
        }
    }

    @Override
    public Result delete(Long id) {
        try{
            if (_hrmsEmployeeDao.existsById(id)){
                _hrmsEmployeeDao.deleteById(id);
                return new SuccessResult("silme i??lemi ger??ekle??ti.");
            }else {
                return new ErrorResult("Kullan??c?? bulunamad??.");
            }

        }catch (Exception ex){
            return new ErrorResult("Silme i??lemi hatal??");
        }

    }

    @Override
    public DataResult<List<HrmsEmployee>> getAll() {
        try {
            return new SuccessDataResult<>(_hrmsEmployeeDao.findAll(), "Veriler getirildi.");
        }catch (Exception ex){
            return new ErrorDataResult<>("Veriler getirilemedi."+ex);
        }
    }

    @Override
    public DataResult<HrmsEmployee> getById(Long id) {
        try {
            return new SuccessDataResult<HrmsEmployee>( _hrmsEmployeeDao.getById(id),"Veri getirildi.");
        }catch (Exception ex){

            return new ErrorDataResult<>("Veriler getirilemedi.");
        }
    }
}
