package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.ForeignLanguageService;
import kodlamaio.HumanResourceManagementSystem.core.utils.TextEditOperation;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.*;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.CurriculumVitaeDao;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.ForeignLanguageDao;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.ForeignLanguage;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobExperience;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.ForeignLanguageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForeignLanguageManager implements ForeignLanguageService {

    private ForeignLanguageDao _foreignLanguageDao;
    private CurriculumVitaeDao _cvDao;

    @Autowired
    public ForeignLanguageManager(ForeignLanguageDao _foreignLanguageDao, CurriculumVitaeDao _cvDao) {
        this._foreignLanguageDao = _foreignLanguageDao;
        this._cvDao = _cvDao;
    }

    @Override
    public Result add(ForeignLanguageDto foreignLanguageDto,int cvId) {
        try{
            if (!_cvDao.existsById(cvId)){
                return new ErrorResult("Cv mevcut değil");
            }else if(foreignLanguageDto.getLanguageName().length()<=2){
                return new ErrorResult("Yabancı dil adı 2 karakterden fazla olmalıdır.");
            }else if(foreignLanguageDto.getLanguageLevel()<1 || foreignLanguageDto.getLanguageLevel()>5){
                return new ErrorResult("Seviyeniz 1-5 arasında olmalıdır.");
            }
            ForeignLanguage foreignLanguage = new ForeignLanguage();
            foreignLanguage.setCurriculumVitae(_cvDao.getById(cvId));
            foreignLanguage.setLanguageName(TextEditOperation.makeAllWordsCapitalLetter(foreignLanguageDto.getLanguageName()));
            foreignLanguage.setLanguageLevel(foreignLanguageDto.getLanguageLevel());
            _foreignLanguageDao.save(foreignLanguage);
            return new SuccessResult("Yabancı dil eklendi.");

        }catch (Exception ex){
            return new ErrorResult("Veri ekleme hatası"+ex);
        }
    }

    @Override
    public Result delete(int id,int cvId) {
        try{
            if (!_cvDao.existsById(cvId)){
                return new ErrorResult("Cv bulunamadı.");
            }else if (!_foreignLanguageDao.existsById(id)){
                return new ErrorResult("Yabancı dil bilgisi bulunamadı.");
            }
            else{
                _foreignLanguageDao.deleteById(id);
                return new SuccessResult("Veri silindi.");
            }
        }catch (Exception ex){
            return new ErrorResult("Veri silme hatası"+ex);
        }


    }

    @Override
    public Result update(ForeignLanguageDto foreignLanguageDto, int cvId, int foreignLanguageId) {
        try{
            if (!_cvDao.existsById(cvId)){
                return new ErrorResult("Cv mevcut değil");
            }else if(foreignLanguageDto.getLanguageName().length()<=2){
                return new ErrorResult("Yabancı dil adı 2 karakterden fazla olmalıdır.");
            }else if(foreignLanguageDto.getLanguageLevel()<1 || foreignLanguageDto.getLanguageLevel()>5){
                return new ErrorResult("Seviyeniz 1-5 arasında olmalıdır.");
            }
            ForeignLanguage foreignLanguage = _foreignLanguageDao.getById(foreignLanguageId);
            foreignLanguage.setLanguageName(TextEditOperation.makeAllWordsCapitalLetter(foreignLanguageDto.getLanguageName()));
            foreignLanguage.setLanguageLevel(foreignLanguageDto.getLanguageLevel());
            _foreignLanguageDao.save(foreignLanguage);
            return new SuccessResult("Yabancı dil güncellendi.");
        }catch (Exception ex)
        {
            return new ErrorResult("Veri güncelleme hatası");
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
