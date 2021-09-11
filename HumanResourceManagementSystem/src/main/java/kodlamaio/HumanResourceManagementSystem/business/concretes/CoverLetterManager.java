package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.CovertLetterService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.*;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.CoverLetterDao;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.CoverLetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
 @Service
public class CoverLetterManager implements CovertLetterService {

     private CoverLetterDao _coverLetterDao;

     @Autowired
     public CoverLetterManager(CoverLetterDao _coverLetterDao) {
         this._coverLetterDao = _coverLetterDao;
     }

     @Override
    public Result add(CoverLetter coverLetter) {
         try{
             var temp = _coverLetterDao.getById(coverLetter.getId());
             if (temp.getId()== coverLetter.getId()) {
                 return new ErrorResult("Coverletter mevcut");

             }else {
                 _coverLetterDao.save(coverLetter);
                 return new SuccessResult("Veri eklendi.");
             }

         }catch (Exception ex){
             return new ErrorResult("Veri ekleme Hatası" + ex);
         }
    }

    @Override
    public Result update(CoverLetter coverLetter) {
         var temp = _coverLetterDao.getById(coverLetter.getId());
         if (temp.getId() != coverLetter.getId()){

             return new ErrorResult("Veri bulunamadı.");
         }else{
             temp.setCoverLetter(coverLetter.getCoverLetter());
             temp.setCurriculumVitae(coverLetter.getCurriculumVitae());
             _coverLetterDao.save(temp);
             return new SuccessResult("Veri güncellendi.");
         }
    }

    @Override
    public Result delete(int id) {
       var temp =  _coverLetterDao.getById(id);
       if (temp.getId()!=id){
           return new ErrorResult("Veri bulunamadı.");
       }else{
           _coverLetterDao.deleteById(id);
           return new SuccessResult("Veri silindi.");
       }
    }

    @Override
    public DataResult<List<CoverLetter>> getAll() {
         try{
             return new SuccessDataResult<List<CoverLetter>>(_coverLetterDao.findAll(),"Veriler getirildi.");
         }catch (Exception ex){
             return new ErrorDataResult<>("Veriler getirilemedi. " + ex);
         }
    }

    @Override
    public DataResult<CoverLetter> getOne(int id) {
        try{
            return new SuccessDataResult<CoverLetter>(_coverLetterDao.getById(id),"Veri getirildi.");
        }catch (Exception ex){
            return new ErrorDataResult<>("Veri getirilemedi. " + ex);
        }
    }
}
