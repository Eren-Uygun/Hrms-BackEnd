package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.ForeignLanguage;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobExperience;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.ForeignLanguageDto;

import java.util.List;

public interface ForeignLanguageService {
    Result add(ForeignLanguageDto foreignLanguageDto,int cvId);
    Result delete(int id,int cvId);
    Result update(ForeignLanguageDto foreignLanguageDto,int cvId,int foreignLanguageId);
    DataResult<ForeignLanguage> getOne(int id);
    DataResult<List<ForeignLanguage>> getAll();
}
