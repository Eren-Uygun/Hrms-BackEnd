package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobExperience;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.JobExperienceDto;

import java.util.List;

public interface JobExperienceService {
    Result add(JobExperienceDto jobExperienceDto);
    Result delete(int id);
    DataResult<JobExperience> getOne(int id);
    DataResult<List<JobExperience>> getAll();
}
