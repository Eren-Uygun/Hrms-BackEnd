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
@RequestMapping("/api/educations")
public class EducationsController {

    private EducationService _educationService;

    @Autowired
    public EducationsController(EducationService _educationService) {
        this._educationService = _educationService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody EducationDto education){
        Result result = _educationService.add(education);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.badRequest().body(result);
        }
    }


    @GetMapping("/getEducation")
    public ResponseEntity<?> getOne(int id){

        DataResult<Education> result = _educationService.getOne(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    /*
    public ResponseEntity<List<?>> getAll(){
        DataResult<List<Education>> result = _educationService.getAll();
        if (result.isSuccess()){
            return ResponseEntity.ok(result.getData());
        }
        return ResponseEntity.badRequest().body(result.getData());
    }
    */

    @GetMapping("/getAll")
    public DataResult<List<Education>> getAll(){
        return _educationService.getAll();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(int id){
        Result result = _educationService.delete(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.badRequest().body(result);
        }
    }





}
