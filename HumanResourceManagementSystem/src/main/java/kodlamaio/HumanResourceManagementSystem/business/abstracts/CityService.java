package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.City;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.CityDto;

import java.util.List;

public interface CityService {
    Result add(CityDto cityDto);
    Result update(CityDto cityDto,int id);
    Result delete(int id);

    DataResult<List<City>> getAll();
    DataResult<City> getOne(int id);
}
