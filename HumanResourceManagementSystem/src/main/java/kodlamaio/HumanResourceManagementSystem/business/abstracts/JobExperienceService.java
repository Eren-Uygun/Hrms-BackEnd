package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobExperience;

import java.util.List;

public interface JobExperienceService {
    Result add(JobExperience jobExperience);
    Result update(JobExperience jobExperience);
    Result delete(int id);
    DataResult<JobExperience> getOne(int id);
    DataResult<List<JobExperience>> getAll();
}
