package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.WorkPlace;

import java.util.List;


public interface WorkPlaceService {
    Result add(WorkPlace workPlace);
    Result update(WorkPlace workPlace);
    Result delete(int id);

    DataResult<List<WorkPlace>> getAll();
    DataResult<WorkPlace> getOne(int id);
}
