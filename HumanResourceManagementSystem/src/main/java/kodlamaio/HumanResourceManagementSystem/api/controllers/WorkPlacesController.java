package kodlamaio.HumanResourceManagementSystem.api.controllers;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.WorkPlaceService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.WorkPlace;
import org.hibernate.jdbc.Work;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequestMapping("/api/workPlaces/")
public class WorkPlacesController {
    private WorkPlaceService _workPlaceService;

    public WorkPlacesController(WorkPlaceService workPlaceService) {
        this._workPlaceService = workPlaceService;
    }

    @GetMapping("/getWorkPlace")
    public ResponseEntity<?> getOne(int id){

        DataResult<WorkPlace> result = _workPlaceService.getOne(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/getAll")
    public DataResult<List<WorkPlace>> getAll(){
        /*
        DataResult<List<WorkPlace>> result = _workPlaceService.getAll();
        if (result.isSuccess()){
            return ResponseEntity.ok(result.getData());
        }
return ResponseEntity.badRequest().body(result.getData());
*/
         return  _workPlaceService.getAll();
    }


    @PostMapping("/add")
    public ResponseEntity<?> add(WorkPlace workPlace){
        Result result = _workPlaceService.add(workPlace);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);

        }
        return ResponseEntity.badRequest().body(result);

    }


    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(int id){
        Result result = _workPlaceService.delete(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);

        }
        return ResponseEntity.badRequest().body(result);
    }
}
