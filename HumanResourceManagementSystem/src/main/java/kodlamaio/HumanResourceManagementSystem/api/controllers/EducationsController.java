package kodlamaio.HumanResourceManagementSystem.api.controllers;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.EducationService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Education;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.WorkPlace;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.EducationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.List;

@RestController
@RequestMapping("/api/{cvId}/educations")
public class EducationsController {

    private EducationService _educationService;

    @Autowired
    public EducationsController(EducationService _educationService) {
        this._educationService = _educationService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody EducationDto education,@PathVariable("cvId") Long cvId){
        Result result = _educationService.add(education,cvId);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.badRequest().body(result);
        }
    }

    @PutMapping(value = "/update/{educationId}",headers = {"content-type=application/json"})
    public ResponseEntity<?> update(@RequestBody EducationDto educationDto,@PathVariable("cvId") Long cvId,@PathVariable("educationId") Long educationId){
        Result result = _educationService.update(educationDto,cvId,educationId);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id,@PathVariable("cvId") Long cvId){
        Result result = _educationService.delete(id,cvId);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.badRequest().body(result);
        }
    }


    /*
    @GetMapping("/getEducation/{id}")
    public ResponseEntity<?> getOne(@PathVariable("educationId") int educationId,@PathVariable("cvId") int cvId){

        DataResult<Education> result = _educationService.getOne(educationId);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }*/

    /*
    public ResponseEntity<List<?>> getAll(){
        DataResult<List<Education>> result = _educationService.getAll();
        if (result.isSuccess()){
            return ResponseEntity.ok(result.getData());
        }
        return ResponseEntity.badRequest().body(result.getData());
    }
    */





}
