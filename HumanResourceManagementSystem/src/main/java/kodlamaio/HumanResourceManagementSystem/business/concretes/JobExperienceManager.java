package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.JobExperienceService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.*;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.CurriculumVitaeDao;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.JobExperienceDao;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobExperience;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.JobExperienceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobExperienceManager implements JobExperienceService {

    private JobExperienceDao _jobExperienceDao;
    private CurriculumVitaeDao _cvDao;

    @Autowired
    public JobExperienceManager(JobExperienceDao _jobExperienceDao, CurriculumVitaeDao _cvDao) {
        this._jobExperienceDao = _jobExperienceDao;
        this._cvDao = _cvDao;
    }

    @Override
    public Result add(JobExperienceDto jobExperienceDto,int cvId) {
        try{

            if (!_cvDao.existsById(cvId)){
                return new ErrorResult("Cv bulunamadı.");
            }else if(jobExperienceDto.getCompanyName().length()<=2) {
                return new ErrorResult("Şirket Adı en az 2 karakter olmalıdır.");
            }else  if(jobExperienceDto.getJobInformation().length()<=5){
                return new ErrorResult("Çalıştığınız yer hakkında verdiğiniz bilgi en az 5 karakter olmalıdır.");
            }else  if(jobExperienceDto.getStartDate() == null){
                return new ErrorResult("İşe başlangıç tarihiniz boş bırakılamaz.");
            }else  if(jobExperienceDto.getEndDate() == null){
                return new ErrorResult("İş'den ayrılış tarihiniz boş bırakılamaz.");
            }else  if(jobExperienceDto.getPositionName().length()<= 2){
                return new ErrorResult("Çalıştığınız departman adı en az 2 karakter olmalıdır.");
            }
            JobExperience jobExperience = new JobExperience();
            jobExperience.setCurriculumVitae(_cvDao.getById(cvId));
            jobExperience.setCompanyName(jobExperienceDto.getCompanyName());
            jobExperience.setPositionName(jobExperienceDto.getPositionName());
            jobExperience.setStartDate(jobExperienceDto.getStartDate());
            if (jobExperienceDto.getEndDate() != null){
                jobExperience.setEndDate(jobExperienceDto.getEndDate());
                jobExperience.setStillWorking(true);
            }else {
                jobExperience.setStillWorking(false);
                jobExperience.setEndDate(null);
            }
            jobExperience.setJobInformation(jobExperienceDto.getJobInformation());

            _jobExperienceDao.save(jobExperience);
            return new SuccessResult("İş tecrübesi eklendi.");


        }catch (Exception ex){
            return new ErrorResult("Veri ekleme hatası "+ex);
        }

    }

    @Override
    public Result delete(int cvId,int jobExperienceId) {
        try{

            if (!_cvDao.existsById(cvId)){
                return new ErrorResult("Cv bulunamadı.");
            }else if (!_jobExperienceDao.existsById(jobExperienceId)){
                return new ErrorResult("İş tecrübe bilgisi bulunamadı.");
            }
            else {
                _jobExperienceDao.deleteById(jobExperienceId);
                return new SuccessResult("Veri silindi.");
            }

        }catch (Exception ex){
            return new ErrorResult("Veri silme hatası");
        }
    }

    @Override
    public Result update(JobExperienceDto jobExperienceDto, int cvId, int jobExperienceId) {
        if (!_cvDao.existsById(cvId)){
            return new ErrorResult("Cv bulunamadı.");
        }else if (!_jobExperienceDao.existsById(jobExperienceId)){
            return new ErrorResult("Veri bulunamadı.");
        }
        JobExperience jobExperience = _jobExperienceDao.getById(jobExperienceId);

        jobExperience.setCompanyName(jobExperienceDto.getCompanyName());
        jobExperience.setJobInformation(jobExperienceDto.getJobInformation());
        jobExperience.setStartDate(jobExperienceDto.getStartDate());
        if (jobExperienceDto.getEndDate() != null){
            jobExperience.setEndDate(jobExperienceDto.getEndDate());
            jobExperience.setStillWorking(true);
        }else {
            jobExperience.setStillWorking(false);
            jobExperience.setEndDate(null);
        }
        _jobExperienceDao.save(jobExperience);
        return new SuccessResult("İş tecrübesi eklendi.");
    }
}
