package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.WorkPlace;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.WorkPlaceDto;

import java.util.List;


public interface WorkPlaceService {
    Result add(WorkPlaceDto workPlaceDto);
    Result update(WorkPlaceDto workPlaceDto,Long workPlaceId);
    Result delete(Long id);

    DataResult<List<WorkPlace>> getAll();
    DataResult<WorkPlace> getOne(Long id);
}
