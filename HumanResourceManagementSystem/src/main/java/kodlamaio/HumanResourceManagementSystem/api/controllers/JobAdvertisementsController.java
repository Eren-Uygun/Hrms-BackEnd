package kodlamaio.HumanResourceManagementSystem.api.controllers;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.JobAdvertisementService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobAdvertisement;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.JobAdvertisementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/jobAdvertisements")
public class JobAdvertisementsController {

    private JobAdvertisementService _jobAdvertisementService;

    @Autowired
    public JobAdvertisementsController(JobAdvertisementService _jobAdvertisementService) {
        this._jobAdvertisementService = _jobAdvertisementService;
    }

    @GetMapping("/getOne")
    public ResponseEntity<?> getOne(int id){
        DataResult<?> result = _jobAdvertisementService.getOne(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<?>> getAll(){
        DataResult<List<JobAdvertisement>> result = _jobAdvertisementService.getAll();
        if (result.isSuccess()){
            return ResponseEntity.ok(result.getData());
        }
        return ResponseEntity.badRequest().body(result.getData());
    }

/*
    public ResponseEntity<?> add(@Valid @RequestBody JobAdvertisement jobAdvertisement){
        Result result = _jobAdvertisementService.add(jobAdvertisement);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
*/

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody JobAdvertisementDto jobAdvertisementDto){
        Result result = _jobAdvertisementService.addDto(jobAdvertisementDto);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(int id){
        Result result = _jobAdvertisementService.delete(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }


}
