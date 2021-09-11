package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobType;

import java.util.List;

public interface JobTypeService {
    Result add(JobType jobType);
    Result update(JobType jobType);
    Result delete(int id);

    DataResult<List<JobType>> getAll();
    DataResult<JobType> getOne(int id);
}
