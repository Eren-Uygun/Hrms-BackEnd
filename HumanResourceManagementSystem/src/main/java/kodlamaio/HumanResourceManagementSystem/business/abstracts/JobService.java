package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Job;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.JobDto;

import java.util.List;

public interface JobService {
    Result add(JobDto jobDto);
    Result update(Long id,JobDto jobDto);
    Result delete(Long id);

    DataResult<List<Job>> getAll();
    DataResult<Job> getById(Long id);
}
