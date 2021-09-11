package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Education;

import java.util.List;

public interface EducationService {

    Result add(Education education);
    Result update(Education  education);
    Result delete(int id);
    DataResult<List<Education>> getAll();
    DataResult<Education> getOne(int id);


}
