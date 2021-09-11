package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Job;

import java.util.List;

public interface JobService {
    Result add(Job job);
    Result update(Job job);
    Result delete(int id);

    DataResult<List<Job>> getAll();
    DataResult<Job> getById(int id);
}
