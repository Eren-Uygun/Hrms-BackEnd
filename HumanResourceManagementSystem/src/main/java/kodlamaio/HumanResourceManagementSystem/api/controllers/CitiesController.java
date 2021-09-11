package kodlamaio.HumanResourceManagementSystem.api.controllers;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.CityService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.City;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Education;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {

    private CityService _cityService;

    @Autowired
    public CitiesController(CityService _cityService) {
        this._cityService = _cityService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody City city){
        Result result =  _cityService.add(city);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(int id){
        Result result = _cityService.delete(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }else
        {
            return ResponseEntity.badRequest().body(result);
        }
    }

    @GetMapping("/getCity")
    public ResponseEntity<?> getOne(int id){

        DataResult<City> result = _cityService.getOne(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }


    /*
    public ResponseEntity<List<?>> getAll(){
        DataResult<List<City>> result = _cityService.getAll();
        if (result.isSuccess()){
            return ResponseEntity.ok(result.getData());
        }
        return ResponseEntity.badRequest().body(result.getData());
    }
    */



    @GetMapping("/getCities")
    public DataResult<List<City>> getAll(){
        return _cityService.getAll();
    }



}
