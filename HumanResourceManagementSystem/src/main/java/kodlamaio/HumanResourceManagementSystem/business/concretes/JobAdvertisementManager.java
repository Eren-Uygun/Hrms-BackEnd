package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.JobAdvertisementService;
import kodlamaio.HumanResourceManagementSystem.core.enums.jobAdvertisementEnums.JobAdvertisementActivationStatus;
import kodlamaio.HumanResourceManagementSystem.core.enums.jobAdvertisementEnums.JobAdvertisementStatus;
import kodlamaio.HumanResourceManagementSystem.core.utils.activationUtils.AdvertisementNumberGenerator;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.*;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.*;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Employer;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.HrmsEmployee;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobAdvertisement;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobAdvertisementActivationByEmployee;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.JobAdvertisementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {

    private JobAdvertisementDao _jobAdvertisementDao;
    private JobAdvertisementActivationByEmployeeDao _jobAdvertisementActivationByEmployeeDao;
    private HrmsEmployeeDao _hrmsEmployeeDao;
    private EmployerDao _employerDao;
    private JobTypeDao _jobTypeDao;
    private WorkPlaceDao _workPlaceDao;
    private CityDao _cityDao;
    private JobDao _jobDao;


    @Autowired
    public JobAdvertisementManager(JobAdvertisementDao _jobAdvertisementDao, JobAdvertisementActivationByEmployeeDao _jobAdvertisementActivationByEmployeeDao, HrmsEmployeeDao _hrmsEmployeeDao, EmployerDao _employerDao, JobTypeDao _jobTypeDao, WorkPlaceDao _workPlaceDao, CityDao _cityDao, JobDao _jobDao) {
        this._jobAdvertisementDao = _jobAdvertisementDao;
        this._jobAdvertisementActivationByEmployeeDao = _jobAdvertisementActivationByEmployeeDao;
        this._hrmsEmployeeDao = _hrmsEmployeeDao;
        this._employerDao = _employerDao;
        this._jobTypeDao = _jobTypeDao;
        this._workPlaceDao = _workPlaceDao;
        this._cityDao = _cityDao;
        this._jobDao = _jobDao;
    }

    @Override
    public Result add(JobAdvertisement jobAdvertisement) {
       try{
           var tempAdv = _jobAdvertisementDao.getJobAdvertisementByAdvertisementNumber(jobAdvertisement.getAdvertisementNumber());
           if (tempAdv.getAdvertisementNumber()!=null){
               return new ErrorResult("bu ilan numarası mevcut");
           }else{
               JobAdvertisementActivationByEmployee jobAdvertisementActivationByEmployee = new JobAdvertisementActivationByEmployee();
               jobAdvertisement.setAdvertisementNumber(AdvertisementNumberGenerator.generateAdvertisementNumber());
               jobAdvertisement.setJobAdvertisementStatus(JobAdvertisementStatus.Passive);
               jobAdvertisement.setReleaseDate(LocalDate.now());
               jobAdvertisementActivationByEmployee.setHrmsEmployee(null);
               jobAdvertisementActivationByEmployee.setJobAdvertisement(jobAdvertisement);
               jobAdvertisementActivationByEmployee.setJobAdvertisementActivationStatus(JobAdvertisementActivationStatus.WaitingActivation);
               jobAdvertisementActivationByEmployee.setAdvertisementActivationDate(LocalDate.now());
               _jobAdvertisementActivationByEmployeeDao.save(jobAdvertisementActivationByEmployee);
               _jobAdvertisementDao.save(jobAdvertisement);
               return new SuccessResult("İş ilanı sisteme eklendi.");
           }
       }catch (Exception ex){
           return new ErrorResult("Ekleme hatası");
       }

    }

    @Override
    public Result addDto(JobAdvertisementDto jobAdvertisementDto) {
        //Fiziki olarak çalıştı ancak React'da denenmedi.
       JobAdvertisement jobAdvertisement = new JobAdvertisement();
       jobAdvertisement.setAdvertisementNumber(AdvertisementNumberGenerator.generateAdvertisementNumber());
       JobAdvertisementActivationByEmployee jobAdvertisementActivationByEmployee = new JobAdvertisementActivationByEmployee();
       jobAdvertisement.setDescription(jobAdvertisementDto.getDescription());
       jobAdvertisement.setReleaseDate(LocalDate.now());
       jobAdvertisement.setJob(_jobDao.getById(jobAdvertisementDto.getJobId()));
       jobAdvertisement.setEmployer(_employerDao.getById(jobAdvertisementDto.getEmployerId()));
       jobAdvertisement.setCity(_cityDao.getById(jobAdvertisementDto.getCityId()));
       jobAdvertisement.setMinSalary(jobAdvertisementDto.getMinSalary());
       jobAdvertisement.setMaxSalary(jobAdvertisementDto.getMaxSalary());
       jobAdvertisement.setOpenPositions(jobAdvertisementDto.getOpenPosition());
       jobAdvertisement.setJobAdvertisementStatus(JobAdvertisementStatus.Passive);
       jobAdvertisement.setJobType(_jobTypeDao.getById(jobAdvertisementDto.getJobTypeId()));
       jobAdvertisement.setWorkPlace(_workPlaceDao.getById(jobAdvertisementDto.getWorkPlaceId()));
        jobAdvertisementActivationByEmployee.setHrmsEmployee(null);
        jobAdvertisementActivationByEmployee.setJobAdvertisement(jobAdvertisement);
        jobAdvertisementActivationByEmployee.setJobAdvertisementActivationStatus(JobAdvertisementActivationStatus.WaitingActivation);
        jobAdvertisementActivationByEmployee.setAdvertisementActivationDate(LocalDate.now());
        _jobAdvertisementActivationByEmployeeDao.save(jobAdvertisementActivationByEmployee);
        _jobAdvertisementDao.save(jobAdvertisement);
        return new SuccessResult("İş ilanı sisteme eklendi.");
    }

    @Override
    public Result update(JobAdvertisement jobAdvertisement) {
        var tempAdvertisement = _jobAdvertisementDao.getById(jobAdvertisement.getId());
        if (tempAdvertisement.getAdvertisementNumber() == null){
            return new ErrorResult("İlan bulunamadı.");
        }
        tempAdvertisement.setDescription(jobAdvertisement.getDescription());
        tempAdvertisement.setMaxSalary(jobAdvertisement.getMaxSalary());
        tempAdvertisement.setMinSalary(jobAdvertisement.getMinSalary());
        tempAdvertisement.setOpenPositions(jobAdvertisement.getOpenPositions());
        tempAdvertisement.setCity(jobAdvertisement.getCity());
        tempAdvertisement.setReleaseDate(jobAdvertisement.getReleaseDate());
        tempAdvertisement.setEndDate(jobAdvertisement.getEndDate());
        tempAdvertisement.setEmployer(jobAdvertisement.getEmployer());
        return null;
    }

    @Override
    public Result delete(int id) {
        try{
            var tempDelete = _jobAdvertisementDao.getById(id);
            if (tempDelete.getId() != id)
            {
            return new ErrorResult("İlan bulunamadı");
            }else {
                _jobAdvertisementDao.deleteById(id);
                return new SuccessResult("İlan silindi.");
            }

        }catch (Exception ex){
            return new ErrorResult("Silme işleminde hata mevcut");
        }
    }

    @Override
    public Result setActivationStatus(int id) {
        try{
            var tempAd=_jobAdvertisementDao.getById(id);
            if (tempAd==null){
                return new ErrorResult("İlan bulunamadı");
            }else {
                if (tempAd.getJobAdvertisementStatus()== JobAdvertisementStatus.Passive){
                    tempAd.setJobAdvertisementStatus(JobAdvertisementStatus.Active);
                    return new SuccessResult("İlan aktif hale getirildi.");
                }else{
                   tempAd.setJobAdvertisementStatus(JobAdvertisementStatus.Passive);
                   return new SuccessResult("İlan pasif duruma çekildi.");
                }
            }

        }catch (Exception ex){
            return new ErrorResult("Aktivasyon değiştirilemedi.");
        }
    }

    @Override
    public DataResult<List<JobAdvertisement>> getAll() {
     try{
         return new SuccessDataResult<List<JobAdvertisement>>(   _jobAdvertisementDao.findAll(),"İlanlar listelendi");
     }catch (Exception ex){
         return new ErrorDataResult<>("Veriler getirilemedi");
     }
    }

    @Override
    public DataResult<JobAdvertisement> getOne(int id) {
        try{
         return new SuccessDataResult<>(_jobAdvertisementDao.getById(id),"Veri getirildi.");
        }catch (Exception ex){
            return new ErrorDataResult<>("Veri getirme hatası");
        }
    }

    @Override
    public DataResult<JobAdvertisement> getByAdvertisementNumber(String advertisementNumber) {
        try{
            var temp = _jobAdvertisementDao.getJobAdvertisementByAdvertisementNumber(advertisementNumber);
            if (temp == null){
                return new ErrorDataResult<>("Bu id'ye ait ilan bulunmamaktadır.");
            }
            else {
                return new SuccessDataResult<JobAdvertisement>(_jobAdvertisementDao.getJobAdvertisementByAdvertisementNumber(advertisementNumber),"İlan getirildi");
            }
        }catch (Exception ex){
            return new ErrorDataResult<>("İlan getirme hatası");
        }
    }
    @Override
    public DataResult<List<JobAdvertisement>> getJobAdvertisementsByJobAdvertisementStatus() {
        try{
            return new SuccessDataResult<List<JobAdvertisement>>(_jobAdvertisementDao.getJobAdvertisementsByJobAdvertisementStatus().getData(),"Durumu aktif olan ilanlar getirildi.");
        }catch (Exception ex){
            return new ErrorDataResult<>("Veriler getirilemedi.");
        }
    }

    @Override
    public DataResult<List<JobAdvertisement>> getJobAdvertisementsByJobAdvertisementStatusAndReleaseDateOrderByReleaseDateDesc() {
       return new SuccessDataResult<List<JobAdvertisement>>(_jobAdvertisementDao.getJobAdvertisementsByJobAdvertisementStatusAndReleaseDateOrderByReleaseDateDesc().getData(),"");
    }


    @Override
    public DataResult<List<JobAdvertisement>> getJobAdvertisementsByEmployerAndJobAdvertisementStatus(int hrmsEmployerId) {
        return new SuccessDataResult<List<JobAdvertisement>>(_jobAdvertisementDao.getJobAdvertisementsByEmployerAndJobAdvertisementStatus(hrmsEmployerId,JobAdvertisementStatus.Active).getData(),"İş verene ait aktif ilanlar getirildi.");
    }


}
