package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.SkillService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.*;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.SkillDao;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillManager implements SkillService {

    private SkillDao _skillDao;

    @Autowired
    public SkillManager(SkillDao _skillDao) {
        this._skillDao = _skillDao;
    }

    @Override
    public Result add(Skill skill) {
       try{
           var temp = _skillDao.getById(skill.getId());
           if (temp.getId() == skill.getId()){
               return new ErrorResult("Veri sistemde mevcut");
           }else{
               _skillDao.save(skill);
               return new SuccessResult("Veri sisteme eklendi.");
           }
       }catch (Exception ex){
           return new ErrorResult("Veri ekleme hatası");
       }
    }

    @Override
    public Result update(Skill skill) {
        try{
            var temp = _skillDao.getById(skill.getId());
            if (temp.getId() != skill.getId()){
                return new ErrorResult("Güncellenecek veri bulunamadı.");
            }else {
                temp.setSkillName(skill.getSkillName());
                temp.setSkillRate(skill.getSkillRate());
                temp.setCurriculumVitae(skill.getCurriculumVitae());
                _skillDao.save(temp);
                return new SuccessResult("Veri güncellendi.");
            }
        }catch (Exception ex){
            return new ErrorResult("Veri güncelleme hatası");
        }
    }

    @Override
    public Result delete(int id) {
        try{
            var temp = _skillDao.getById(id);
            if (temp.getId() != id) {
                return new ErrorResult("Silinecek veri bulunamadı.");
            }else{
                _skillDao.deleteById(id);
                return new SuccessResult("Veri silindi.");
            }
        }catch (Exception ex){

            return new ErrorResult("Veri silme hatası");
        }
    }

    @Override
    public DataResult<Skill> getOne(int id) {
        try{
            return new SuccessDataResult<Skill>(_skillDao.getById(id),"Veri getirildi.");
        }catch (Exception ex){
            return new ErrorDataResult<>("Veri getirme hatası");
        }
    }

    @Override
    public DataResult<List<Skill>> getAll() {
       try{
           return new SuccessDataResult<List<Skill>>(_skillDao.findAll(),"Veriler getirildi.");
       }catch (Exception ex){
           return new ErrorDataResult<>("veri getirme hatası");
       }
    }
}
