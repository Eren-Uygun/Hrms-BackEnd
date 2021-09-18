package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.JobService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.*;
import kodlamaio.HumanResourceManagementSystem.core.utils.validations.JobValidation;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.JobDao;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Job;
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
    public Result add(Job job) {
        try{
            //Boşluklu ve değişik yazım stilleri ile bypass edilebiliyor.
            //String metotları ile düzeltilebilir.
            if (_jobDao.existsById(job.getId())){
                return new ErrorResult("İş sistemde kayıtlı");
            }
            _jobDao.save(job);
            return new SuccessResult("Ekleme işlemi yapıldı.");
        }catch (Exception ex){

            return new ErrorResult("Ekleme işlemi hatalı");
        }

    }

    @Override
    public Result update(Job job) {
        var tempJob = _jobDao.getById(job.getId());
        tempJob.setJobName(job.getJobName());
        _jobDao.save(tempJob);
        return new SuccessResult("Güncelleme işlemi yapıldı.");
    }

    @Override
    public Result delete(int id) {
       try{
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
    public DataResult<Job> getById(int id) {
        try{
            return new SuccessDataResult<Job>(_jobDao.getById(id),"Veri getirildi.");
        }catch (Exception ex){
            return new ErrorDataResult<>("Veri getirme hatası");
        }
    }
}
