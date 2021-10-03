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

    private JobService _jobService;

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


    @PutMapping(value = "/update/{jobId}",headers = {"content-type=application/json"})
    public ResponseEntity<?> update(@RequestBody JobDto jobDto,@PathVariable("jobId") int jobId){
       Result result = _jobService.update(jobDto,jobId);
       if (result.isSuccess()){
           return ResponseEntity.ok(result);
       }
       return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(int id){
        Result result = this._jobService.delete(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }


    @GetMapping("/getOne")
    public ResponseEntity<Job> getOne(int id){
        DataResult<Job> result = _jobService.getById(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result.getData());
        }
        return ResponseEntity.badRequest().body(result.getData());
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
