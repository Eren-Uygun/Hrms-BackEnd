package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.EducationService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.*;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.EducationDao;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Education;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationManager implements EducationService {

    private EducationDao _educationDao;

    @Autowired
    public EducationManager(EducationDao _educationDao) {
        this._educationDao = _educationDao;
    }

    @Override
    public Result add(Education education) {
       try{
           var temp = _educationDao.getById(education.getId());
           if (temp.getId() == education.getId()){
               return new ErrorResult("Veri sistemde mevcut");
           }else{
               _educationDao.save(education);
               return new SuccessResult("Veri eklendi.");
           }
       }catch (Exception ex){
           return new ErrorResult("Veri ekleme hatası");
       }
    }

    @Override
    public Result update(Education education) {
        try{
            var temp = _educationDao.getById(education.getId());
            if (temp.getId()!=education.getId()){
                return new ErrorResult("Güncellenecek veri bulunamadı.");
            }else{
                temp.setSchoolName(education.getSchoolName());
                temp.setDepartmentName(education.getDepartmentName());
                temp.setStartingDate(education.getStartingDate());
                if (temp.getGraduateDate()==null){
                    temp.setGradudated(true);
                }else {
                    temp.setGradudated(false);
                }
                temp.setCurriculumVitae(education.getCurriculumVitae());
                _educationDao.save(temp);
                return new SuccessResult("Veri güncellendi.");
            }
        }catch (Exception ex){
            return new ErrorResult("Veri güncelleme hatası");
        }
    }

    @Override
    public Result delete(int id) {
        try{
            var temp = _educationDao.getById(id);
if (temp.getId()!=id){
    return new ErrorResult("Silenecek veri bulunamadı.");
        }else {

    _educationDao.getById(id);
    return new SuccessResult("Veri silindi");
        }

    }catch (Exception ex) {
            return new ErrorResult("Veri silme hatası" + ex);
        }
        }

    @Override
    public DataResult<List<Education>> getAll() {
        try{
            return new SuccessDataResult<List<Education>>(_educationDao.findAll(),"Veriler getirildi.");
        }catch (Exception ex)
        {
         return new ErrorDataResult<>("Veri getirme hatası");
        }
    }

    @Override
    public DataResult<Education> getOne(int id) {
        try{
            return new SuccessDataResult<Education>(_educationDao.getById(id),"Veri getirildi.");
        }catch (Exception ex)
        {
            return new ErrorDataResult<>("Veri getirme hatası");
        }
    }
}
