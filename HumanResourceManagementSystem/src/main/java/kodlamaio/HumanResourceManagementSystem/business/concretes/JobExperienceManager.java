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
    public Result add(JobExperienceDto jobExperienceDto) {
        try{

            if (!_cvDao.existsById(jobExperienceDto.getCvId())){
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
            jobExperience.setCurriculumVitae(_cvDao.getById(jobExperienceDto.getCvId()));
            jobExperience.setCompanyName(jobExperienceDto.getCompanyName());
            jobExperience.setPositionName(jobExperienceDto.getPositionName());
            jobExperience.setStartDate(jobExperienceDto.getStartDate());
            jobExperience.setEndDate(jobExperienceDto.getEndDate());
            jobExperience.setJobInformation(jobExperienceDto.getJobInformation());

            _jobExperienceDao.save(jobExperience);
            return new SuccessResult("İş tecrübesi eklendi.");


        }catch (Exception ex){
            return new ErrorResult("Veri ekleme hatası "+ex);
        }

    }

    @Override
    public Result delete(int id) {
        try{

            if (!_jobExperienceDao.existsById(id)){
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
