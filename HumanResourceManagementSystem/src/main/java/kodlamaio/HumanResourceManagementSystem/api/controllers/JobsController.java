package kodlamaio.HumanResourceManagementSystem.api.controllers;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.JobService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Job;
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

/*
    public ResponseEntity<?> add(@RequestBody Job job){
        Result result=this._jobService.add(job);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
    */
    @PostMapping("/add")
    public Result add(@RequestBody Job job){
return _jobService.add(job);
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
