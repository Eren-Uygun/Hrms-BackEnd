package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobType;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.JobTypeDto;

import java.util.List;

public interface JobTypeService {
    Result add(JobTypeDto jobTypeDto);
    Result update(JobTypeDto jobTypeDto,Long jobTypeId);
    Result delete(Long id);

    DataResult<List<JobType>> getAll();
    DataResult<JobType> getOne(Long id);
}
