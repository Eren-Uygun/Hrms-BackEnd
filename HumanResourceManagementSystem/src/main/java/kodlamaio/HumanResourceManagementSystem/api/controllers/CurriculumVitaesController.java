package kodlamaio.HumanResourceManagementSystem.api.controllers;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.CurriculumVitaeService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.CurriculumVitae;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.CurriculumVitaeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/curriculumVitaes")
public class CurriculumVitaesController {

    private CurriculumVitaeService _curriculumVitaeService;

    @Autowired
    public CurriculumVitaesController(CurriculumVitaeService _curriculumVitaeService) {
        this._curriculumVitaeService = _curriculumVitaeService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody CurriculumVitaeDto curriculumVitae){
        Result result = _curriculumVitaeService.add(curriculumVitae);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);

    }

    @GetMapping("/getOne")
    public ResponseEntity<?> getOne(int id){
        Result result = _curriculumVitaeService.findById(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/getByCandidateId")
    public ResponseEntity<?> getByCandidateId(@RequestParam int candidateId){
        Result result = _curriculumVitaeService.getCurriculumVitaeByCandidate(candidateId);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    public ResponseEntity<?> addPhoto(@RequestBody MultipartFile multipartFile,@RequestParam int id){
        try{
            Result result = _curriculumVitaeService.uploadCvPhoto(id,multipartFile);
            if (result.isSuccess()){
                return ResponseEntity.ok(result);
            }
            return ResponseEntity.badRequest().body(result);
        }catch(Exception exception){
             return ResponseEntity.badRequest().body(exception);
        }

    }



}
