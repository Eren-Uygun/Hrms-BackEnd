package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Employer;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.EmployerAddDto;

import java.util.List;

public interface EmployerService {

    Result add(EmployerAddDto employerAddDto);
    Result update(Long employerId,EmployerAddDto employerAddDto);
    Result delete(Long id);

    DataResult<List<Employer>> getAll();
    DataResult<Employer> getById(Long id);
}
