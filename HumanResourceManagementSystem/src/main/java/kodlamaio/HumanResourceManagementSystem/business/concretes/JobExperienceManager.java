package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.JobExperienceService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.*;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.JobExperienceDao;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobExperience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobExperienceManager implements JobExperienceService {

    private JobExperienceDao _jobExperienceDao;

    @Autowired
    public JobExperienceManager(JobExperienceDao _jobExperienceDao) {
        this._jobExperienceDao = _jobExperienceDao;
    }

    @Override
    public Result add(JobExperience jobExperience) {
        try{
            var temp = _jobExperienceDao.getById(jobExperience.getId());
            if (temp.getId()== jobExperience.getId()){
                return new ErrorResult("Veri sistemde kayıtlı");
            }else {
                _jobExperienceDao.save(jobExperience);
                return new SuccessResult("Veri eklendi.");
            }
        }catch (Exception ex){
            return new ErrorResult("Veri ekleme hatası "+ex);
        }

    }

    @Override
    public Result update(JobExperience jobExperience) {
        try{
            var temp = _jobExperienceDao.getById(jobExperience.getId());
            if (temp.getId()!= jobExperience.getId()){
                return new ErrorResult("Güncellenecek veri bulunamadı.");
            }else{
                temp.setCompanyName(jobExperience.getCompanyName());
                temp.setPositionName(jobExperience.getPositionName());
                temp.setStartDate(jobExperience.getStartDate());
                temp.setEndDate(jobExperience.getEndDate());
                temp.setJobInformation(jobExperience.getJobInformation());
                temp.setCurriculumVitae(jobExperience.getCurriculumVitae());
                _jobExperienceDao.save(temp);
                return new SuccessResult("Veri güncellendi.");
            }

        }catch (Exception ex){
return new ErrorResult("Veri güncelleme hatası");
        }
    }

    @Override
    public Result delete(int id) {
        try{
            var temp = _jobExperienceDao.getById(id);
            if (temp.getId() != id){
                return new ErrorResult("Silinecek veri bulunamadı.");
            }else {
                _jobExperienceDao.deleteById(id);
                return new SuccessResult("Veri silindi.");
            }

        }catch (Exception ex){
            return new ErrorResult("Veri silme hatası");
        }
    }

    @Override
    public DataResult<JobExperience> getOne(int id) {

        try{
            return new SuccessDataResult<JobExperience>(_jobExperienceDao.getById(id),"Veri getirildi.");
        }catch (Exception ex){
            return new ErrorDataResult<>("Veri getirme hatası");
        }
    }

    @Override
    public DataResult<List<JobExperience>> getAll() {
        try{
            return new SuccessDataResult<List<JobExperience>>(_jobExperienceDao.findAll(),"Veriler getirildi.");
        }catch (Exception ex){
            return new ErrorDataResult<>("Veri getirme hatası");
        }
    }
}
