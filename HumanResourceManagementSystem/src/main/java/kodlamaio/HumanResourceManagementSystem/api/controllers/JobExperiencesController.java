package kodlamaio.HumanResourceManagementSystem.api.controllers;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.JobExperienceService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobExperience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experiences")
public class JobExperiencesController {

    private JobExperienceService _jobExperienceService;

    @Autowired
    public JobExperiencesController(JobExperienceService jobExperienceService) {
        this._jobExperienceService = jobExperienceService;
    }

    @GetMapping("/getAll")
    public DataResult<List<JobExperience>> getAll(){
        /*
        DataResult<List<JobExperience>> result = _jobExperienceService.getAll();
        if (result.isSuccess()){
            return ResponseEntity.ok(result.getData());
        }
        return ResponseEntity.badRequest().body(result.getData());*/
        return _jobExperienceService.getAll();

    }

    @GetMapping("/getOne")
    public ResponseEntity<?> getOne(int id){
        Result result = _jobExperienceService.getOne(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }


    @PostMapping("/add")
    public ResponseEntity<?> add(JobExperience experience){
        Result result = _jobExperienceService.add(experience);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(int id){
        Result result = _jobExperienceService.delete(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

}
