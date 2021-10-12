package kodlamaio.HumanResourceManagementSystem.api.controllers;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.JobService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Job;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.JobDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin
public class JobsController {

    private final JobService _jobService;

    @Autowired
    public JobsController(JobService jobService) {
        this._jobService = jobService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody JobDto jobDto){
        Result result=this._jobService.add(jobDto);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }


    @PutMapping(value = "/update/{id}",headers = {"content-type=application/json"})
    public ResponseEntity<?> update(@PathVariable("id") Long id,@RequestBody JobDto jobDto){
       Result result = _jobService.update(id,jobDto);
       if (result.isSuccess()){
           return ResponseEntity.ok(result);
       }
       return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        Result result = this._jobService.delete(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }


    @GetMapping("/getOne")
    public ResponseEntity<?> getOne(Long id){
        DataResult<Job> result = _jobService.getById(id);

        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    /*
    @GetMapping("/getAll")
    public ResponseEntity<List<?>> getAll1(){
        DataResult<List<Job>> result = _jobService.getAll();
        if (result.isSuccess()){
            return ResponseEntity.ok(result.getData());
        }
        return ResponseEntity.badRequest().body(result.getData());
    }

    */

    @GetMapping("/getAll")
    public DataResult<List<Job>> getAll(){
        return _jobService.getAll();
    }


}
