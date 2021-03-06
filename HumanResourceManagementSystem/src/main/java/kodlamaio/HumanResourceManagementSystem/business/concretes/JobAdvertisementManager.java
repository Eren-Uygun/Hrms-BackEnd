package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.JobAdvertisementService;
import kodlamaio.HumanResourceManagementSystem.business.constants.BusinessMessage;
import kodlamaio.HumanResourceManagementSystem.core.enums.jobAdvertisementEnums.JobAdvertisementActivationStatus;
import kodlamaio.HumanResourceManagementSystem.core.utils.activationUtils.AdvertisementNumberGenerator;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.*;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.*;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobAdvertisement;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobAdvertisementActivationByEmployee;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.JobAdvertisementDto;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.JobAdvertisementFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {

    private final JobAdvertisementDao _jobAdvertisementDao;
    private final JobAdvertisementActivationWithEmployeeDao _jobAdvertisementActivationByEmployeeDao;
    private final HrmsEmployeeDao _hrmsEmployeeDao;
    private final EmployerDao _employerDao;
    private final JobTypeDao _jobTypeDao;
    private final WorkPlaceDao _workPlaceDao;
    private final CityDao _cityDao;
    private final JobDao _jobDao;


    @Autowired
    public JobAdvertisementManager(JobAdvertisementDao _jobAdvertisementDao, JobAdvertisementActivationWithEmployeeDao _jobAdvertisementActivationByEmployeeDao, HrmsEmployeeDao _hrmsEmployeeDao, EmployerDao _employerDao, JobTypeDao _jobTypeDao, WorkPlaceDao _workPlaceDao, CityDao _cityDao, JobDao _jobDao) {
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
    public Result addDto(JobAdvertisementDto jobAdvertisementDto) {
       JobAdvertisement jobAdvertisement = new JobAdvertisement();
       jobAdvertisement.setAdvertisementNumber(AdvertisementNumberGenerator.generateAdvertisementNumber());
       JobAdvertisementActivationByEmployee jobAdvertisementActivationByEmployee = new JobAdvertisementActivationByEmployee();
       jobAdvertisement.setDescription(jobAdvertisementDto.getDescription());
       jobAdvertisement.setReleaseDate(LocalDate.now());
       if (!_jobDao.existsById(jobAdvertisementDto.getJobId())){
           return new ErrorResult("???? bulunamad??.");
       }
       jobAdvertisement.setJob(_jobDao.getById(jobAdvertisementDto.getJobId()));
       if (!_employerDao.existsById(jobAdvertisementDto.getEmployerId())){
           return new ErrorResult("???? veren bulunamad??.");
       }
       jobAdvertisement.setEmployer(_employerDao.getById(jobAdvertisementDto.getEmployerId()));
       if (!_cityDao.existsById(jobAdvertisementDto.getCityId())){
           return new ErrorResult("??ehir bulunamad??.");
       }
       jobAdvertisement.setCity(_cityDao.getById(jobAdvertisementDto.getCityId()));
       jobAdvertisement.setMinSalary(jobAdvertisementDto.getMinSalary());
       jobAdvertisement.setMaxSalary(jobAdvertisementDto.getMaxSalary());
       jobAdvertisement.setEndDate(jobAdvertisementDto.getEndDate());
       jobAdvertisement.setOpenPositions(jobAdvertisementDto.getOpenPosition());
       jobAdvertisement.setIsJobAdvertisementStatusActive(false);

       if (!_jobTypeDao.existsById(jobAdvertisementDto.getJobTypeId())){
           return new ErrorResult("???? tipi bulunamad??.");
       }
       jobAdvertisement.setJobType(_jobTypeDao.getById(jobAdvertisementDto.getJobTypeId()));
       if (!_workPlaceDao.existsById(jobAdvertisementDto.getWorkPlaceId())){
           return new ErrorResult("??al????ma yeri bulunamad??.");
       }
       jobAdvertisement.setWorkPlace(_workPlaceDao.getById(jobAdvertisementDto.getWorkPlaceId()));
        jobAdvertisementActivationByEmployee.setHrmsEmployee(null);
        jobAdvertisementActivationByEmployee.setJobAdvertisement(jobAdvertisement);
        jobAdvertisementActivationByEmployee.setJobAdvertisementActivationStatus(JobAdvertisementActivationStatus.WaitingActivation);
        jobAdvertisementActivationByEmployee.setAdvertisementActivationDate(LocalDate.now());
        _jobAdvertisementActivationByEmployeeDao.save(jobAdvertisementActivationByEmployee);
        _jobAdvertisementDao.save(jobAdvertisement);
        return new SuccessResult("???? ilan?? sisteme eklendi.");
    }

    @Override
    public Result update(JobAdvertisementDto jobAdvertisementDto,Long advertisementId) {
        var tempAdvertisement = _jobAdvertisementDao.getById(advertisementId);
        if (tempAdvertisement.getAdvertisementNumber() == null){
            return new ErrorResult("??lan bulunamad??.");
        }
        tempAdvertisement.setDescription(jobAdvertisementDto.getDescription());
        tempAdvertisement.setMaxSalary(jobAdvertisementDto.getMaxSalary());
        tempAdvertisement.setMinSalary(jobAdvertisementDto.getMinSalary());
        tempAdvertisement.setOpenPositions(jobAdvertisementDto.getOpenPosition());
        tempAdvertisement.setCity(_cityDao.getById(jobAdvertisementDto.getCityId()));
        tempAdvertisement.setEndDate(jobAdvertisementDto.getEndDate());
        tempAdvertisement.setEmployer(_employerDao.getById(jobAdvertisementDto.getEmployerId()));
        tempAdvertisement.setJobType(_jobTypeDao.getById(jobAdvertisementDto.getJobTypeId()));
        tempAdvertisement.setWorkPlace(_workPlaceDao.getById(jobAdvertisementDto.getWorkPlaceId()));
        tempAdvertisement.setJob(_jobDao.getById(jobAdvertisementDto.getJobId()));

        _jobAdvertisementDao.save(tempAdvertisement);
        return new SuccessResult("??lan g??ncellendi.");
    }

    @Override
    public Result delete(Long id) {
        try{

            if (!_jobAdvertisementDao.existsById(id))
            {
            return new ErrorResult("??lan bulunamad??");
            }else {
                _jobAdvertisementDao.deleteById(id);
                return new SuccessResult("??lan silindi.");
            }

        }catch (Exception ex){
            return new ErrorResult("Silme i??leminde hata mevcut");
        }
    }

    @Override
    public Result setActivationStatus(Long id) {
        try{
            if (!_jobAdvertisementDao.existsById(id)){
                return new ErrorResult("??lan bulunamad??");
            }else {
                JobAdvertisement tempAd=_jobAdvertisementDao.getById(id);
                if (tempAd.getIsJobAdvertisementStatusActive().equals(false)){
                    tempAd.setIsJobAdvertisementStatusActive(true);
                    _jobAdvertisementDao.save(tempAd);
                    return new SuccessResult("??lan aktif hale getirildi.");
                }else{

                   tempAd.setIsJobAdvertisementStatusActive(false);
                    _jobAdvertisementDao.save(tempAd);
                   return new SuccessResult("??lan pasif duruma ??ekildi.");
                }
            }

        }catch (Exception ex){
            return new ErrorResult("Aktivasyon de??i??tirilemedi.");
        }
    }

    @Override
    public DataResult<List<JobAdvertisement>> getAll() {
     try{
         return new SuccessDataResult<List<JobAdvertisement>>(_jobAdvertisementDao.findAll(),"??lanlar listelendi");
     }catch (Exception ex){
         return new ErrorDataResult<>("Veriler getirilemedi");
     }
    }

    @Override
    public DataResult<JobAdvertisement> getOne(Long id) {
        try{
         return new SuccessDataResult<>(_jobAdvertisementDao.getById(id),"Veri getirildi.");
        }catch (Exception ex){
            return new ErrorDataResult<>("Veri getirme hatas??");
        }
    }

    @Override
    public DataResult<JobAdvertisement> getByAdvertisementNumber(String advertisementNumber) {
        try{
            var temp = _jobAdvertisementDao.getJobAdvertisementByAdvertisementNumber(advertisementNumber);
            if (temp == null){
                return new ErrorDataResult<>("Bu id'ye ait ilan bulunmamaktad??r.");
            }
            else {
                return new SuccessDataResult<JobAdvertisement>(_jobAdvertisementDao.getJobAdvertisementByAdvertisementNumber(advertisementNumber),"??lan getirildi");
            }
        }catch (Exception ex){
            return new ErrorDataResult<>("??lan getirme hatas??");
        }
    }

    @Override
    public DataResult<List<JobAdvertisement>> getJobAdvertisementsByJobAdvertisementStatus(int pageNo,int pageSize) {
        try{
            Pageable pageable = PageRequest.of(pageNo -1,pageSize);
            return new SuccessDataResult<List<JobAdvertisement>>(_jobAdvertisementDao.getJobAdvertisementsByIsJobAdvertisementStatusActive(pageable),"Durumu aktif olan ilanlar getirildi.");
        }catch (Exception ex){
            return new ErrorDataResult<>("Veriler getirilemedi.");
        }
    }

    @Override
    public DataResult<List<JobAdvertisement>> getJobAdvertisementsByJobAdvertisementStatusAndReleaseDateOrderByReleaseDateDesc(int pageNo,int pageSize) {
        Pageable pageable = PageRequest.of(pageNo -1,pageSize);
       return new SuccessDataResult<List<JobAdvertisement>>(_jobAdvertisementDao.findJobAdvertisementsByIsJobAdvertisementStatusActiveOrderByReleaseDate(true,pageable),"");
    }


    @Override
    public DataResult<List<JobAdvertisement>> getJobAdvertisementsByEmployerAndJobAdvertisementStatus(Long employerId,int pageNo,int pageSize){
        Pageable pageable = PageRequest.of(pageNo -1,pageSize);
        return new SuccessDataResult<List<JobAdvertisement>>(_jobAdvertisementDao.findJobAdvertisementsByIsJobAdvertisementStatusActiveAndEmployer_Id(true,employerId,pageable),"???? verene ait aktif ilanlar getirildi.");
    }

    @Override
    public DataResult<List<JobAdvertisement>> getByIsActiveAndPageNumber(int pageNo, int pageSize,JobAdvertisementFilter jobAdvertisementFilter) {
        Pageable pageable = PageRequest.of(pageNo -1,pageSize);
        return new SuccessDataResult<List<JobAdvertisement>>(_jobAdvertisementDao.getByFilter(jobAdvertisementFilter,pageable).getContent(),_jobAdvertisementDao.getByFilter(jobAdvertisementFilter,pageable).getTotalElements()+" "+BusinessMessage.getOperationSuccess);
    }



}
