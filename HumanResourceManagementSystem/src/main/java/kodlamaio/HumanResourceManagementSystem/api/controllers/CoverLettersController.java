package kodlamaio.HumanResourceManagementSystem.api.controllers;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.CovertLetterService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.CoverLetterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/{cvId}/coverLetters")
public class CoverLettersController {

    private CovertLetterService _covertLetterService;

    @Autowired
    public CoverLettersController(CovertLetterService _covertLetterService) {
        this._covertLetterService = _covertLetterService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody CoverLetterDto coverLetterDto,@PathVariable("cvId") int cvId){
        Result result = _covertLetterService.add(coverLetterDto,cvId);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping("/update/{coverLetterId}")
    public ResponseEntity<?> update(@RequestBody CoverLetterDto coverLetterDto, @PathVariable("cvId") int cvId, @PathVariable("coverLetterId") int coverLetterId){
        Result result = _covertLetterService.update(coverLetterDto,cvId,coverLetterId);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);

    }

    @DeleteMapping("/delete/{coverLetterId}")
    public ResponseEntity<?> delete(@PathVariable("cvId") int cvId,@PathVariable("coverLetterId") int coverLetterId){
        Result result = _covertLetterService.delete(cvId,coverLetterId);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }


}
