package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Education;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.EducationDto;

import java.util.List;

public interface EducationService {

    Result add(EducationDto educationDto);
    Result delete(int id);
    DataResult<List<Education>> getAll();
    DataResult<Education> getOne(int id);


}
