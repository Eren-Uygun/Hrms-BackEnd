package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.City;

import java.util.List;

public interface CityService {
    Result add(City city);
    Result update(City city);
    Result delete(int id);

    DataResult<List<City>> getAll();
    DataResult<City> getOne(int id);
}
