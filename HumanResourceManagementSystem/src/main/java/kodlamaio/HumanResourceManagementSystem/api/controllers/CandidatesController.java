package kodlamaio.HumanResourceManagementSystem.api.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import kodlamaio.HumanResourceManagementSystem.business.abstracts.CandidateService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.SuccessDataResult;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Candidate;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobType;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.WorkPlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostUpdate;
import javax.persistence.PreUpdate;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@CrossOrigin
public class CandidatesController {

    private CandidateService _candidateService;

    @Autowired
    public CandidatesController(CandidateService _candidateService) {
        this._candidateService = _candidateService;
    }

    @GetMapping("/getForUpdate")
    public ResponseEntity<?> update(int id){
        Result result = _candidateService.getById(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(Candidate candidate){
        Result  result = _candidateService.update(candidate);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);

    }


    @GetMapping("/getByNationalIdentityNumber")
    public ResponseEntity<?> findByNationalIdentityNumber(String nationalIdentityNumber){
        DataResult<Candidate> result = _candidateService.getByNationalIdentityNumber(nationalIdentityNumber);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getOne(int id){

        DataResult<Candidate> result = _candidateService.getById(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/getCandidateById")
    public DataResult<Candidate> getById(int id){
        return _candidateService.getById(id);
    }





    /*
    public ResponseEntity<List<Candidate>> getAll(){
        DataResult<List<Candidate>> result = _candidateService.getAll();
        if (result.isSuccess()){
            return ResponseEntity.ok(result.getData());
        }
        return ResponseEntity.badRequest().body(result.getData());
    }
*/
    @GetMapping("/getAll")
    public DataResult<List<Candidate>> getAllThem(){
        return _candidateService.getAll();

    }


    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Candidate candidate){

        Result result = _candidateService.add(candidate);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(int  id){

        Result result = _candidateService.delete(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }



}





