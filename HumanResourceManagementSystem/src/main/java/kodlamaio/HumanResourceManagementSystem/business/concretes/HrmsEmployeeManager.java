package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.HrmsEmployeeService;
import kodlamaio.HumanResourceManagementSystem.core.enums.userEnums.UserStatus;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.*;
import kodlamaio.HumanResourceManagementSystem.core.utils.validations.abstracts.RuleValidationService;
import kodlamaio.HumanResourceManagementSystem.core.utils.validations.abstracts.UserValidationService;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.HrmsEmployeeDao;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.HrmsEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HrmsEmployeeManager implements HrmsEmployeeService {

    private HrmsEmployeeDao _hrmsEmployeeDao;
    private UserValidationService _userValidationService;
    private RuleValidationService _ruleValidationService;


    @Autowired
    public HrmsEmployeeManager(HrmsEmployeeDao _hrmsEmployeeDao, UserValidationService _userValidationService, RuleValidationService _ruleValidationService) {
        this._hrmsEmployeeDao = _hrmsEmployeeDao;
        this._userValidationService = _userValidationService;
        this._ruleValidationService = _ruleValidationService;
    }

    @Override
    public Result add(HrmsEmployee employee) {
        try{
            if(employee.getFirstName().length()<2&&employee.getLastName().length()<2){
                return new ErrorResult("Adınız ve soyadınız 2 karakterden fazla olmalıdır.");
            }
           else if (_userValidationService.isMailAddressExists(employee.getEmail())){
                return new ErrorResult("Kullanıcı sistemde mevcut");
            }
            else if(!_ruleValidationService.isMailRuleOk(employee.getEmail())&&!_ruleValidationService.isPasswordRuleOk(employee.getPassword())){
                return new ErrorResult("Mail adresiniz veya şifreniz kurallara uygun değil");
            }
            else if(!employee.getPassword().equals(employee.getPasswordRepeat())){
                return new ErrorResult("Girdiğiniz şifreler uyuşmuyor.");
            }
            else{
                employee.setUserStatus(UserStatus.Active);
                _hrmsEmployeeDao.save(employee);
                return new SuccessResult("Personel sisteme eklendi ve aktif hale getirildi.");
            }

        }catch (Exception ex){
            return new ErrorResult("Ekleme işlemi hatalı");
        }
    }

    @Override
    public Result update(HrmsEmployee employee) {
        try{
            var temp = _hrmsEmployeeDao.getById(employee.getId());
            if (!_userValidationService.isUserExists(temp.getId())){
                return new ErrorResult("Kullanıcı sistemde mevcut değil");
            } else if(employee.getFirstName().length()<2&&employee.getLastName().length()<2){
                return new ErrorResult("Adınız ve soyadınız 2 karakterden fazla olmalıdır.");
            } else if(!_ruleValidationService.isMailRuleOk(employee.getEmail())&&!_ruleValidationService.isPasswordRuleOk(employee.getPassword())){
                return new ErrorResult("Mail adresiniz veya şifreniz kurallara uygun değil");
            } else if(!employee.getPassword().equals(employee.getPasswordRepeat())){
                return new ErrorResult("Girdiğiniz şifreler uyuşmuyor.");
            }
            else{
                temp.setEmail(employee.getEmail());
                temp.setPassword(employee.getPassword());
                temp.setPasswordRepeat(employee.getPasswordRepeat());
                temp.setFirstName(employee.getFirstName());
                temp.setLastName(employee.getLastName());
                temp.setDepartment(employee.getDepartment());
                temp.setBirthDate(employee.getBirthDate());
                temp.setUserStatus(UserStatus.Active);
                _hrmsEmployeeDao.save(temp);
                return new SuccessResult("Personel güncellendi.");
            }

        }catch (Exception ex){
            return new ErrorResult("Güncelleme işlemi hatalı");
        }
    }

    @Override
    public Result delete(int id) {
        try{
            var tempEmployee = _hrmsEmployeeDao.getById(id);
            if (_userValidationService.isUserExists(tempEmployee.getId())){
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

            return new SuccessDataResult<List<HrmsEmployee>>(_hrmsEmployeeDao.findAll(),"Veriler getirildi.");
        }catch (Exception ex){

            return new ErrorDataResult<>("Veriler getirilemedi.");
        }
    }

    @Override
    public DataResult<HrmsEmployee> getById(int id) {
        try {
            return new SuccessDataResult<HrmsEmployee>( _hrmsEmployeeDao.getById(id),"Veri getirildi.");
        }catch (Exception ex){

            return new ErrorDataResult<>("Veriler getirilemedi.");
        }
    }
}
