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
    public ResponseEntity<?> add(@Valid @RequestBody CurriculumVitaeDto curriculumVitaeDto){
        Result result = _curriculumVitaeService.add(curriculumVitaeDto);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);

    }

    @DeleteMapping("/delete/{cvId}")
    public ResponseEntity<?> delete(@PathVariable("cvId") int cvId){
        Result result = _curriculumVitaeService.delete(cvId);
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

    @PutMapping(value = "/updateGithub",headers = {"content-type=application/json"})
    public ResponseEntity<?> updateGithub(@RequestParam String github,@RequestParam int cvId){
        Result result=this._curriculumVitaeService.updateGithub(github,cvId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/deleteGithub")
    public ResponseEntity<?> deleteGithub(@RequestParam int cvId){
        Result result=this._curriculumVitaeService.deleteGithub(cvId);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping(value = "/updateLinkedin",headers = {"content-type=application/json"})
    public ResponseEntity<?> updateLinkedin(@RequestParam String linkedin,@RequestParam int cvId){
        Result result=this._curriculumVitaeService.updateLinkedin(linkedin,cvId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/deleteLinkedin")
    public ResponseEntity<?> deleteLinkedin(@RequestParam int cvId){
        Result result=this._curriculumVitaeService.deleteLinkedin(cvId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping(value = "/updateBiography",headers = {"content-type=application/json"})
    public ResponseEntity<?> updateBiography(@RequestParam String biography,@RequestParam int cvId){
        Result result=this._curriculumVitaeService.updateAboutMe(biography,cvId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/deleteBiography")
    public ResponseEntity<?> deleteBiography(@RequestParam int cvId){
        Result result=this._curriculumVitaeService.deleteAboutMe(cvId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }



}
