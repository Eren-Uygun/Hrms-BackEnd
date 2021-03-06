package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.enums.jobAdvertisementEnums.JobAdvertisementStatus;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobAdvertisement;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.JobAdvertisementDto;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.JobAdvertisementFilter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobAdvertisementService {

   // Result add(JobAdvertisement jobAdvertisement);
    Result addDto(JobAdvertisementDto jobAdvertisementDto);
    Result update(JobAdvertisementDto jobAdvertisementDto,Long advertisementId);
    Result delete(Long id);
    Result setActivationStatus(Long id);
    DataResult<List<JobAdvertisement>> getAll();
    DataResult<JobAdvertisement> getOne(Long id);//Repository'de var ancak c#'den gelen alışkanlık :)
    DataResult<JobAdvertisement>getByAdvertisementNumber(String advertisementNumber);


    DataResult<List<JobAdvertisement>>getJobAdvertisementsByJobAdvertisementStatus(int pageNo, int pageSize);

    DataResult<List<JobAdvertisement>>getJobAdvertisementsByJobAdvertisementStatusAndReleaseDateOrderByReleaseDateDesc(int pageNo, int pageSize);
    DataResult<List<JobAdvertisement>> getJobAdvertisementsByEmployerAndJobAdvertisementStatus(Long employerId,int pageNo, int pageSize);

    DataResult<List<JobAdvertisement>> getByIsActiveAndPageNumber(int pageNo, int pageSize,JobAdvertisementFilter jobAdvertisementFilter);



}
