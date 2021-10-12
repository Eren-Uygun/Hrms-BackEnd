package kodlamaio.HumanResourceManagementSystem.api.controllers;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.CandidateService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Candidate;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.CandidateAddDto;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.CandidateUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidatesController {


    private final CandidateService _candidateService;

    @Autowired
    public CandidatesController(CandidateService candidateService) {
        this._candidateService = candidateService;
    }


    //@RequestMapping(method = RequestMethod.PUT,path = "update/{id}",)
    @PutMapping(value = "/update/{id}",headers = {"content-type=application/json"})
    public ResponseEntity<?> update(@PathVariable("id") Long id,@RequestBody CandidateUpdateDto candidateUpdateDto){
        Result  result = _candidateService.update(id,candidateUpdateDto);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);

    }

    @CrossOrigin
    @GetMapping("/getByNationalIdentityNumber")
    public ResponseEntity<?> findByNationalIdentityNumber(String nationalIdentityNumber){
        DataResult<Candidate> result = _candidateService.getByNationalIdentityNumber(nationalIdentityNumber);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @CrossOrigin
    @GetMapping("/getById")
    public ResponseEntity<?> getOne(Long id){

        DataResult<Candidate> result = _candidateService.getById(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @CrossOrigin
    @GetMapping("/getCandidateById")
    public DataResult<Candidate> getById(Long id){
        return _candidateService.getById(id);
    }

    @CrossOrigin
    @GetMapping("/getAll")
    public DataResult<List<Candidate>> getAllThem(){
        return _candidateService.getAll();

    }


    @CrossOrigin
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody CandidateAddDto candidate){

        Result result = _candidateService.add(candidate);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @CrossOrigin
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(Long  id){
        Result result = _candidateService.delete(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }



}





