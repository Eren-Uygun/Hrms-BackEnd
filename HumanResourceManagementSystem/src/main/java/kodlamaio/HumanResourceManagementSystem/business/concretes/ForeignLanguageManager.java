package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.ForeignLanguageService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.*;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.ForeignLanguageDao;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.ForeignLanguage;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobExperience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForeignLanguageManager implements ForeignLanguageService {

    private ForeignLanguageDao _foreignLanguageDao;

    @Autowired
    public ForeignLanguageManager(ForeignLanguageDao _foreignLanguageDao) {
        this._foreignLanguageDao = _foreignLanguageDao;
    }

    @Override
    public Result add(ForeignLanguage foreignLanguage) {
        try{
            var temp = _foreignLanguageDao.getById(foreignLanguage.getId());
            if (temp.getId() == foreignLanguage.getId()){
                return new ErrorResult("Veri sistemde kayıtlı");
            }else{
                _foreignLanguageDao.save(foreignLanguage);
                return new SuccessResult("Veri sisteme eklendi.");
            }

        }catch (Exception ex){
            return new ErrorResult("Veri ekleme hatası"+ex);
        }
    }

    @Override
    public Result update(ForeignLanguage foreignLanguage) {
        try{
            var temp = _foreignLanguageDao.getById(foreignLanguage.getId());
            if (temp.getId() != foreignLanguage.getId()){
                return new ErrorResult("Veri bulunamadı.");
            }else{
                temp.setLanguageName(foreignLanguage.getLanguageName());
                temp.setLanguageLevel(foreignLanguage.getLanguageLevel());
                temp.setCurriculumVitae(foreignLanguage.getCurriculumVitae());
                _foreignLanguageDao.save(temp);
                return new SuccessResult("Veri güncellendi.");
            }

        }catch (Exception ex){
            return new ErrorResult("Veri güncelleme hatası"+ex);
        }
    }

    @Override
    public Result delete(int id) {
        try{
            var temp = _foreignLanguageDao.getById(id);
            if (temp.getId() != id){
                return new ErrorResult("Silinecek veri bulunamadı.");
            }else{
                _foreignLanguageDao.deleteById(id);
                return new SuccessResult("Veri silindi.");
            }
        }catch (Exception ex){
            return new ErrorResult("Veri silme hatası"+ex);
        }


    }

    @Override
    public DataResult<ForeignLanguage> getOne(int id) {
       try{
           return new SuccessDataResult<ForeignLanguage>(_foreignLanguageDao.getById(id),"Veri getirildi.");
       }catch (Exception ex){
           return new ErrorDataResult<>("Veri getirme hatası");
       }
    }

    @Override
    public DataResult<List<ForeignLanguage>> getAll() {
        try{
            return new SuccessDataResult<List<ForeignLanguage>>(_foreignLanguageDao.findAll(),"Veriler getirildi.");
        }catch (Exception ex){
            return new ErrorDataResult<>("Veri getirme hatası");
        }
    }
}
