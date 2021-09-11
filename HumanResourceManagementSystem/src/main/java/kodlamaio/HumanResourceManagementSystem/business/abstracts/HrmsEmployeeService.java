package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.HrmsEmployee;

import java.util.List;

public interface HrmsEmployeeService {

    Result add(HrmsEmployee employee);
    Result update(HrmsEmployee employee);
    Result delete(int id);

    DataResult<List<HrmsEmployee>> getAll();
    DataResult<HrmsEmployee> getById(int id);
}
