package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.enums.jobAdvertisementEnums.JobAdvertisementStatus;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobAdvertisement;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.JobAdvertisementDto;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.JobAdvertisementFilter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import java.util.List;

public interface JobAdvertisementService {

   // Result add(JobAdvertisement jobAdvertisement);
    Result addDto(JobAdvertisementDto jobAdvertisementDto);
    Result update(JobAdvertisementDto jobAdvertisementDto,int advertisementId);
    Result delete(int id);
    Result setActivationStatus(int id);
    DataResult<List<JobAdvertisement>> getAll();
    DataResult<JobAdvertisement> getOne(int id);//Repository'de var ancak c#'den gelen alışkanlık :)
    DataResult<JobAdvertisement>getByAdvertisementNumber(String advertisementNumber);


    DataResult<List<JobAdvertisement>>getJobAdvertisementsByJobAdvertisementStatus();

    DataResult<List<JobAdvertisement>>getJobAdvertisementsByJobAdvertisementStatusAndReleaseDateOrderByReleaseDateDesc();
    DataResult<List<JobAdvertisement>> getJobAdvertisementsByEmployerAndJobAdvertisementStatus(int employerId);

    DataResult<List<JobAdvertisement>> getByIsActiveAndPageNumber(int pageNo, int pageSize,JobAdvertisementFilter jobAdvertisementFilter);


}
