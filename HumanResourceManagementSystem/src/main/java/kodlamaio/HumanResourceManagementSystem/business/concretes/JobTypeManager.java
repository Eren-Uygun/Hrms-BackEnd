package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.JobTypeService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.*;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.JobTypeDao;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobTypeManager implements JobTypeService {

    private JobTypeDao _jobTypeDao;

    public JobTypeManager(JobTypeDao _jobTypeDao) {
        this._jobTypeDao = _jobTypeDao;
    }

    @Override
    public Result add(JobType jobType) {
        try{
           _jobTypeDao.save(jobType);
           return new SuccessResult("Ekleme işlemi başarılı");
        }catch (Exception ex){
            return new ErrorResult("Ekleme işlemi hatalı");
        }
    }

    @Override
    public Result update(JobType jobType) {
        var temp = _jobTypeDao.getById(jobType.getId());
        if (temp.getId() != jobType.getId()){
            return new ErrorResult("Veri bulunamadı.");
        }else {
            JobType tempType = new JobType();
            tempType.setJobType(jobType.getJobType());
            tempType.setJobAdvertisements(jobType.getJobAdvertisements());
            _jobTypeDao.save(tempType);
            return new SuccessResult("Veri güncellendi.");
        }
    }

    @Override
    public Result delete(int id) {
        var temp = _jobTypeDao.getById(id);
        if (temp.getId()!=id){
            return new ErrorResult("Kullanıcı bulunamadı.");
        }

        else{
            _jobTypeDao.deleteById(id);
            return new SuccessResult("Veri silindi.");
        }
    }

    @Override
    public DataResult<List<JobType>> getAll() {
        try{
            return new SuccessDataResult<List<JobType>>(_jobTypeDao.findAll(),"Veriler getirildi.");
        }catch (Exception ex){
            return new ErrorDataResult<>("Veri getirme hatası");
        }

    }

    @Override
    public DataResult<JobType> getOne(int id) {
        try{
            return new SuccessDataResult<JobType>(_jobTypeDao.getById(id),"Veri getirildi.");
        }catch (Exception ex){
            return new ErrorDataResult<>("Veri getirme hatası");
        }
    }
}
