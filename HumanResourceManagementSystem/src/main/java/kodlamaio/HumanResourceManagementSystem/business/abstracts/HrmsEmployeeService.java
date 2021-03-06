package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.HrmsEmployee;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.HrmsEmployeeDto;

import java.util.List;

public interface HrmsEmployeeService {

    Result add(HrmsEmployeeDto employeeDto);
    Result update(Long id,HrmsEmployeeDto employeeDto);
    Result delete(Long id);

    DataResult<List<HrmsEmployee>> getAll();
    DataResult<HrmsEmployee> getById(Long id);
}
