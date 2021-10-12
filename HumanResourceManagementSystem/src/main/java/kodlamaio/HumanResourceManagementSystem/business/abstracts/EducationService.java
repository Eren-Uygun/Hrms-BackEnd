package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Education;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.EducationDto;

import java.util.List;

public interface EducationService {

    Result add(EducationDto educationDto,Long cvId);
    Result update(EducationDto educationDto,Long cvId,Long educationId);
    Result delete(Long id,Long cvId);
    DataResult<List<Education>> getAll(Long cvId);
    DataResult<Education> getOne(Long id);


}
