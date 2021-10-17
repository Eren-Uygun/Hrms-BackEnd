package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.HrmsEmployeeService;
import kodlamaio.HumanResourceManagementSystem.business.abstracts.RoleService;
import kodlamaio.HumanResourceManagementSystem.business.abstracts.UserService;
import kodlamaio.HumanResourceManagementSystem.core.enums.userEnums.UserStatus;
import kodlamaio.HumanResourceManagementSystem.core.utils.TextEditOperation;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.*;
import kodlamaio.HumanResourceManagementSystem.core.utils.validations.abstracts.RuleValidationService;
import kodlamaio.HumanResourceManagementSystem.core.utils.validations.abstracts.UserValidationService;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.HrmsEmployeeDao;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.HrmsEmployee;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.HrmsEmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HrmsEmployeeManager implements HrmsEmployeeService {

    private HrmsEmployeeDao _hrmsEmployeeDao;
    private UserValidationService _userValidationService;
    private RuleValidationService _ruleValidationService;
    private RoleService _roleService;

    @Autowired
    public HrmsEmployeeManager(HrmsEmployeeDao _hrmsEmployeeDao, UserValidationService _userValidationService, RuleValidationService _ruleValidationService, RoleService _roleService) {
        this._hrmsEmployeeDao = _hrmsEmployeeDao;
        this._userValidationService = _userValidationService;
        this._ruleValidationService = _ruleValidationService;
        this._roleService = _roleService;
    }

    @Override
    public Result add(HrmsEmployeeDto employeeDto) {
        try{
            if(employeeDto.getFirstName().length()<2||employeeDto.getLastName().length()<2){
                return new ErrorResult("Adınız ve soyadınız 2 karakterden fazla olmalıdır.");
            }
           else if (_userValidationService.isMailAddressExists(employeeDto.getEmail())){
                return new ErrorResult("Kullanıcı sistemde mevcut");
            }
            else if(!_ruleValidationService.isMailRuleOk(employeeDto.getEmail())&&!_ruleValidationService.isPasswordRuleOk(employeeDto.getPassword())){
                return new ErrorResult("Mail adresiniz veya şifreniz kurallara uygun değil");
            }
            else if(!employeeDto.getPassword().equals(employeeDto.getPasswordConfirm())){
                return new ErrorResult("Girdiğiniz şifreler uyuşmuyor.");
            }
            else{
                HrmsEmployee employee = new HrmsEmployee();
                employee.setFirstName(TextEditOperation.makeAllWordsCapitalLetter(employeeDto.getFirstName()));
                employee.setLastName(TextEditOperation.makeAllWordsCapitalLetter(employeeDto.getLastName()));
                employee.setBirthDate(employeeDto.getBirthDate());
                employee.setEmail(employeeDto.getEmail());
                employee.setPassword(employeeDto.getPassword());
                employee.setDepartment(TextEditOperation.makeAllWordsCapitalLetter(employeeDto.getDepartment()));
                employee.setPasswordRepeat(employeeDto.getPasswordConfirm());
                employee.setUserStatus(UserStatus.Active);
                _hrmsEmployeeDao.save(employee);
                _roleService.addRoleToUser(employeeDto.getEmail(), "ROLE_ADMIN");
                return new SuccessResult("Personel sisteme eklendi ve aktif hale getirildi.");
            }

        }catch (Exception ex){
            return new ErrorResult("Ekleme işlemi hatalı");
        }
    }

    @Override
    public Result update(Long id,HrmsEmployeeDto employeeDto) {
        try{

            if (!_hrmsEmployeeDao.existsById(id)){
                return new ErrorResult("Kullanıcı sistemde mevcut değil");
            } else if(employeeDto.getFirstName().length()<2||employeeDto.getLastName().length()<2){
                return new ErrorResult("Adınız ve soyadınız 2 karakterden fazla olmalıdır.");
            } else if(!_ruleValidationService.isMailRuleOk(employeeDto.getEmail())&&!_ruleValidationService.isPasswordRuleOk(employeeDto.getPassword())){
                return new ErrorResult("Mail adresiniz veya şifreniz kurallara uygun değil");
            } else if(!employeeDto.getPassword().equals(employeeDto.getPasswordConfirm())){
                return new ErrorResult("Girdiğiniz şifreler uyuşmuyor.");
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
                return new SuccessResult("Personel güncellendi.");
            }

        }catch (Exception ex){
            return new ErrorResult("Güncelleme işlemi hatalı");
        }
    }

    @Override
    public Result delete(Long id) {
        try{
            if (_hrmsEmployeeDao.existsById(id)){
                _hrmsEmployeeDao.deleteById(id);
                return new SuccessResult("silme işlemi gerçekleşti.");
            }else {
                return new ErrorResult("Kullanıcı bulunamadı.");
            }

        }catch (Exception ex){
            return new ErrorResult("Silme işlemi hatalı");
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
