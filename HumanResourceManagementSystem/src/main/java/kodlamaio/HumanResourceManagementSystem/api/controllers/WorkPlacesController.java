package kodlamaio.HumanResourceManagementSystem.api.controllers;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.WorkPlaceService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.WorkPlace;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.WorkPlaceDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequestMapping("/api/workPlaces/")
@CrossOrigin
public class WorkPlacesController {
    private final WorkPlaceService _workPlaceService;

    public WorkPlacesController(WorkPlaceService workPlaceService) {
        this._workPlaceService = workPlaceService;
    }

    @GetMapping("/getWorkPlace")
    public ResponseEntity<?> getOne(Long id){

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
    public ResponseEntity<?> add(@RequestBody WorkPlaceDto workPlaceDto){
        Result result = _workPlaceService.add(workPlaceDto);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);

        }
        return ResponseEntity.badRequest().body(result);

    }

    @PutMapping("/update/{workPlaceId}")
    public ResponseEntity<?> update(@RequestBody WorkPlaceDto workPlaceDto,@PathVariable("workPlaceId") Long workPlaceId){
        Result result = _workPlaceService.update(workPlaceDto,workPlaceId);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);

    }


    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(Long id){
        Result result = _workPlaceService.delete(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);

        }
        return ResponseEntity.badRequest().body(result);
    }
}
