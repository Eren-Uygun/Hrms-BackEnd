package kodlamaio.HumanResourceManagementSystem.api.controllers;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.CandidateService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Candidate;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.CandidateAddDto;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.CandidateUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@CrossOrigin
public class CandidatesController {


    private final CandidateService _candidateService;

    @Autowired
    public CandidatesController(CandidateService candidateService) {
        this._candidateService = candidateService;
    }


   // @PutMapping("/update/{id}/")
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id,@RequestBody CandidateUpdateDto candidateUpdateDto){
        Result  result = _candidateService.update(candidateUpdateDto,id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);

    }

/*
    @CrossOrigin

    public ResponseEntity<?> update(@PathVariable("id") int id,@RequestBody CandidateUpdateDto candidateUpdateDto){
        Result  result = _candidateService.update(candidateUpdateDto,id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);

    }
*/

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
    public ResponseEntity<?> getOne(int id){

        DataResult<Candidate> result = _candidateService.getById(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @CrossOrigin
    @GetMapping("/getCandidateById")
    public DataResult<Candidate> getById(int id){
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
    public ResponseEntity<?> delete(int  id){
        Result result = _candidateService.delete(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }



}





