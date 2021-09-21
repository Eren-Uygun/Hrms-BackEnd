package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Education;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.EducationDto;

import java.util.List;

public interface EducationService {

    Result add(EducationDto educationDto,int cvId);
    Result update(EducationDto educationDto,int cvId,int educationId);
    Result delete(int id,int cvId);
    DataResult<List<Education>> getAll(int cvId);
    DataResult<Education> getOne(int id);


}
