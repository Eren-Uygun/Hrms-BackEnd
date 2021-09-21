package kodlamaio.HumanResourceManagementSystem.api.controllers;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.JobTypeService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobType;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.WorkPlace;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.JobTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobTypes")
public class JobTypesController {

    private JobTypeService _jobTypeService;

    @Autowired
    public JobTypesController(JobTypeService _jobTypeService) {
        this._jobTypeService = _jobTypeService;
    }


    @GetMapping("/getJobType")
    public ResponseEntity<?> getOne(int id){
        Result result = _jobTypeService.getOne(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);

    }

    @GetMapping("/getAll")
    public DataResult<List<JobType>> getAll(){
        /*
        DataResult<List<JobType>> result = _jobTypeService.getAll();
        if (result.isSuccess()){
            return ResponseEntity.ok(result.getData());
        }
        return ResponseEntity.badRequest().body(result.getData());
        */
       return  _jobTypeService.getAll();

    }


    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody JobTypeDto jobTypeDto){

        Result result = _jobTypeService.add(jobTypeDto);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(int  id){

        Result result = _jobTypeService.delete(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody JobTypeDto jobTypeDto,int jobTypeId){
      Result result =  _jobTypeService.update(jobTypeDto,jobTypeId);
      if (result.isSuccess()){
          return ResponseEntity.ok(result);
      }
      return ResponseEntity.badRequest().body(result);
    }

}
