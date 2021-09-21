package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.SkillService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.*;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.CurriculumVitaeDao;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.SkillDao;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Skill;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.SkillDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillManager implements SkillService {

    private SkillDao _skillDao;
    private CurriculumVitaeDao _cvDao;

    @Autowired
    public SkillManager(SkillDao _skillDao, CurriculumVitaeDao _cvDao) {
        this._skillDao = _skillDao;
        this._cvDao = _cvDao;
    }

    @Override
    public Result add(SkillDto skillDto,int cvId) {
       try{

           if (!_cvDao.existsById(cvId)){
               return new ErrorResult("Cv bulunamadı.");
           }else if(skillDto.getSkillName().length()<2){
               return new ErrorResult("Yetenek adı 2 karakterden fazla olmalıdır.");
           }else if(skillDto.getSkillRate()<1 || skillDto.getSkillRate()>5){
               return new ErrorResult("Yetenek seviyeniz 1-5 arasında olmalıdır.");
           }

           Skill skill = new Skill();
           skill.setCurriculumVitae(_cvDao.getById(cvId));
           skill.setSkillName(skillDto.getSkillName());
           skill.setSkillRate(skillDto.getSkillRate());

           _skillDao.save(skill);
           return new SuccessResult("Yetenek eklendi.");


       }catch (Exception ex){
           return new ErrorResult("Veri ekleme hatası");
       }
    }

    @Override
    public Result update(SkillDto skillDto,int cvId,int skillId) {
        if (!_cvDao.existsById(cvId)){
            return new ErrorResult("Cv bulunamadı.");
        }else if(skillDto.getSkillName().length()<2){
            return new ErrorResult("Yetenek adı 2 karakterden fazla olmalıdır.");
        }else if(skillDto.getSkillRate()<1 || skillDto.getSkillRate()>5){
            return new ErrorResult("Yetenek seviyeniz 1-5 arasında olmalıdır.");
        }
        Skill skill = _skillDao.getById(skillId);
        skill.setCurriculumVitae(_cvDao.getById(cvId));
        skill.setSkillName(skillDto.getSkillName());
        skill.setSkillRate(skillDto.getSkillRate());
        _skillDao.save(skill);
        return new SuccessResult("Yetenek güncellendi.");
    }


    @Override
    public Result delete(int skillId,int cvId) {
        try{
            if (!_cvDao.existsById(cvId)) {
                return new ErrorResult("Cv bulununamadı.");
            }else if (!_skillDao.existsById(skillId)){
                return new ErrorResult("Yetenek bulunamadı.");
            }
            else{
                _skillDao.deleteById(skillId);
                return new SuccessResult("Veri silindi.");
            }
        }catch (Exception ex){

            return new ErrorResult("Veri silme hatası");
        }
    }

    /*
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

    */
}
