package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.JobService;
import kodlamaio.HumanResourceManagementSystem.core.utils.TextEditOperation;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.*;
import kodlamaio.HumanResourceManagementSystem.core.utils.validations.JobValidation;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.JobDao;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Job;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.JobDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class JobManager implements JobService {

    private JobDao _jobDao;

    @Autowired
    public JobManager(JobDao _jobDao) {
        this._jobDao = _jobDao;
    }

    @Override
    public Result add(JobDto jobDto) {
        try{
            if (_jobDao.existsByJobName(TextEditOperation.makeAllWordsCapitalLetter(jobDto.getJobName()))){
                return new ErrorResult("İş sistemde kayıtlı");
            }else if(jobDto.getJobName().length()<2){
                return new ErrorResult("İş adı boş olamaz.");
            }
            Job job = new Job();
            job.setJobName(TextEditOperation.makeAllWordsCapitalLetter(jobDto.getJobName()));
            _jobDao.save(job);
            return new SuccessResult("Ekleme işlemi yapıldı.");
        }catch (Exception ex){

            return new ErrorResult("Ekleme işlemi hatalı");
        }

    }

    @Override
    public Result update(Long id,JobDto jobDto) {
        if (!_jobDao.existsById(id)){
            return new ErrorResult("İş kolu bulunamadı.");
        }

        if (_jobDao.existsByJobName(TextEditOperation.makeAllWordsCapitalLetter(jobDto.getJobName()))){
            return new ErrorResult("İş adı sistemde mevcut");
        }
        Job job = _jobDao.findById(id).get();
        job.setJobName(TextEditOperation.makeAllWordsCapitalLetter(jobDto.getJobName()));
        _jobDao.save(job);
        return new SuccessResult("Güncelleme işlemi yapıldı.");
    }

    @Override
    public Result delete(Long id) {
       try{
           if (!_jobDao.existsById(id)){
               return new ErrorResult("İş kolu bulunamadı.");
           }
           _jobDao.getById(id);
           return new SuccessResult("Silme işlemi yapıldı.");
       }catch (Exception ex){
           return new ErrorResult("Silme işlemi hatalı");
       }
    }

    @Override
    public DataResult<List<Job>> getAll() {
        try{
            return new SuccessDataResult<List<Job>>(_jobDao.findAll(),"Veriler getirildi.");
        }catch (Exception ex){

            return new ErrorDataResult<>("Veri getirme hatası");
        }
    }

    @Override
    public DataResult<Job> getById(Long id) {
        try{
            return new SuccessDataResult<Job>(_jobDao.findById(id).get(),"Veri getirildi.");
        }catch (Exception ex){
            return new ErrorDataResult<>("Veri getirme hatası");
        }
    }
}
