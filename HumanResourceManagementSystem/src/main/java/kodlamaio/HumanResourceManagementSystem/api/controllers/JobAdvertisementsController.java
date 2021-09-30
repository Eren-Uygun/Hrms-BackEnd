package kodlamaio.HumanResourceManagementSystem.api.controllers;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.JobAdvertisementService;
import kodlamaio.HumanResourceManagementSystem.core.enums.jobAdvertisementEnums.JobAdvertisementStatus;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.SuccessDataResult;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobAdvertisement;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.JobAdvertisementDto;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.JobAdvertisementFilter;
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


    @GetMapping("/getActives")
    public DataResult<List<JobAdvertisement>> getActives(@RequestParam int pageNo, @RequestParam int pageSize){
        return new SuccessDataResult<List<JobAdvertisement>>(_jobAdvertisementService.getJobAdvertisementsByJobAdvertisementStatus(pageNo,pageSize).getData(),"Aktif ilanlar getirildi.");
    }


 @GetMapping("/getActivesByReleaseDate")
    public DataResult<List<JobAdvertisement>> getActivesByReleaseDate(@RequestParam int pageNo ,@RequestParam int pageSize){
        return new SuccessDataResult<List<JobAdvertisement>>(_jobAdvertisementService.getJobAdvertisementsByJobAdvertisementStatusAndReleaseDateOrderByReleaseDateDesc(pageNo,pageSize).getData(),"Aktif ilanlar getirildi.");
    }


    @GetMapping("/getActivesByEmployerAndStatus")
    public DataResult<List<JobAdvertisement>> getActivesByEmployerAndStatus(@RequestParam int employerId,@RequestParam int pageNo,@RequestParam int pageSize){

        return new SuccessDataResult<List<JobAdvertisement>>(_jobAdvertisementService.getJobAdvertisementsByEmployerAndJobAdvertisementStatus(employerId, pageNo,pageSize).getData(),"Aktif ilanlar getirildi.");
    }

    /*
    @PostMapping("/getByActiveAndFilteredAdvertisement")
    public DataResult<List<JobAdvertisement>> getJobAdvertisementByFilterAndPage(@RequestParam int pageNo, @RequestParam int pageSize,@RequestBody JobAdvertisementFilter jobAdvertisementFilter){
        return new SuccessDataResult<List<JobAdvertisement>>(_jobAdvertisementService.getByIsActiveAndPageNumber(pageNo,pageSize,jobAdvertisementFilter).getData(),"Veriler getirildi.");
    }
*/

    @PostMapping("/getByActiveAndFilteredAdvertisement")
    public Result getJobAdvertisementByFilterAndPage(@RequestParam int pageNo, @RequestParam int pageSize,@RequestBody JobAdvertisementFilter jobAdvertisementFilter){
        return _jobAdvertisementService.getByIsActiveAndPageNumber(pageNo,pageSize,jobAdvertisementFilter);
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

    @PutMapping(value = "/update/{advertisementId}",headers = {"content-type=application/json"})
    public ResponseEntity<?> update(@RequestBody JobAdvertisementDto jobAdvertisementDto,@PathVariable("advertisementId") int advertisementId){
        Result result = _jobAdvertisementService.update(jobAdvertisementDto,advertisementId);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);

    }



    /*
    @PostMapping("/activateJobAdvertisement")
    public ResponseEntity<?> activateJobAdvertisement(int advertisementId){
       Result result = _jobAdvertisementService.setActivationStatus(advertisementId);
       if (result.isSuccess()){
           return ResponseEntity.ok(result);
       }
       return ResponseEntity.badRequest().body(result);

    }
*/

}
