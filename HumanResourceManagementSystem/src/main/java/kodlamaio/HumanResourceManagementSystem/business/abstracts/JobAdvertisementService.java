package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.enums.jobAdvertisementEnums.JobAdvertisementStatus;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobAdvertisement;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.JobAdvertisementDto;

import java.util.List;

public interface JobAdvertisementService {

    Result add(JobAdvertisement jobAdvertisement);
    Result update(JobAdvertisement jobAdvertisement);
    Result delete(int id);
    Result setActivationStatus(int id);
    DataResult<List<JobAdvertisement>> getAll();
    DataResult<JobAdvertisement> getOne(int id);//Repository'de var ancak c#'den gelen alışkanlık :)
    DataResult<JobAdvertisement>getByAdvertisementNumber(String advertisementNumber);
    DataResult<List<JobAdvertisement>>getJobAdvertisementsByJobAdvertisementStatus();
    DataResult<List<JobAdvertisement>>getJobAdvertisementsByJobAdvertisementStatusAndReleaseDateOrderByReleaseDateDesc();
    DataResult<List<JobAdvertisement>> getJobAdvertisementsByEmployerAndJobAdvertisementStatus(int employerId);



}