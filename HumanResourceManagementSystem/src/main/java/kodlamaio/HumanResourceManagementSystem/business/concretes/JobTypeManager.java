package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.JobTypeService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.*;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.JobAdvertisementDao;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.JobTypeDao;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Job;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobType;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.JobTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobTypeManager implements JobTypeService {

    private JobTypeDao _jobTypeDao;
    private JobAdvertisementDao _jobAdvertisementDao;

    @Autowired
    public JobTypeManager(JobTypeDao _jobTypeDao, JobAdvertisementDao _jobAdvertisementDao) {
        this._jobTypeDao = _jobTypeDao;
        this._jobAdvertisementDao = _jobAdvertisementDao;
    }

    @Override
    public Result add(JobTypeDto jobTypeDto) {
        try{
            JobType jobType = new JobType();
            jobType.setJobType(jobTypeDto.getJobType());
           _jobTypeDao.save(jobType);
           return new SuccessResult("Ekleme işlemi başarılı");
        }catch (Exception ex){
            return new ErrorResult("Ekleme işlemi hatalı");
        }
    }

    @Override
    public Result update(JobTypeDto jobTypeDto,int jobTypeId) {
        if (!_jobTypeDao.existsById(jobTypeId)){
            return new ErrorResult("Veri bulunamadı.");
        }else {
            JobType tempType = _jobTypeDao.getById(jobTypeId);
            tempType.setJobType(jobTypeDto.getJobType());
            _jobTypeDao.save(tempType);
            return new SuccessResult("Veri güncellendi.");
        }
    }

    @Override
    public Result delete(int id) {
        if (!_jobTypeDao.existsById(id)){
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
