package kodlamaio.HumanResourceManagementSystem.api.controllers;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.EmployerService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Candidate;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Employer;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.HrmsEmployee;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.EmployerAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/employers")
public class EmployersController {

    private EmployerService _employerService;

    @Autowired
    public EmployersController(EmployerService _employerService) {
        this._employerService = _employerService;
    }

    @CrossOrigin
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody EmployerAddDto employerAddDto){
        Result result=_employerService.add(employerAddDto);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(Long employerId){
        DataResult<?> result= _employerService.getById(employerId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    /*
    @GetMapping("/getall")
    public ResponseEntity<List<?>> getAll(){
        DataResult<List<Employer>> result = _employerService.getAll();
        if (result.isSuccess()){
            return ResponseEntity.ok(result.getData());
        }
        return ResponseEntity.badRequest().body(result.getData());
    }
*/
    @GetMapping("/getAll")
    public DataResult<List<Employer>> getAll(){
        return _employerService.getAll();
    }

    @CrossOrigin
    @DeleteMapping("/delete/{employerId}")
    public ResponseEntity<?> delete(@PathVariable("employerId") Long employerId){
        Result result=_employerService.delete(employerId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @CrossOrigin
    @PutMapping(value = "/update/{employerId}",headers = {"content-type=application/json"})
    public ResponseEntity<?> update( @RequestBody EmployerAddDto employerAddDto,@PathVariable("employerId") Long employerId){
        Result result=_employerService.update(employerId,employerAddDto);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

}
