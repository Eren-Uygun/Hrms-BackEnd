package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.ForeignLanguage;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobExperience;

import java.util.List;

public interface ForeignLanguageService {
    Result add(ForeignLanguage foreignLanguage);
    Result update(ForeignLanguage foreignLanguage);
    Result delete(int id);
    DataResult<ForeignLanguage> getOne(int id);
    DataResult<List<ForeignLanguage>> getAll();
}
