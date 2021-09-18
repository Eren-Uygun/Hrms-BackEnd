package kodlamaio.HumanResourceManagementSystem.api.controllers;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.ForeignLanguageService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.ForeignLanguage;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobExperience;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.ForeignLanguageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foreignLanguages")
public class ForeignLanguagesController {

    private ForeignLanguageService _foreignLanguageService;

    @Autowired
    public ForeignLanguagesController(ForeignLanguageService _foreignLanguageService) {
        this._foreignLanguageService = _foreignLanguageService;
    }

    @GetMapping("/getAll")
    public DataResult<List<ForeignLanguage>> getAll(){
        /*
        DataResult<List<ForeignLanguage>> result = _foreignLanguageService.getAll();
        if (result.isSuccess()){
            return ResponseEntity.ok(result.getData());
        }
        return ResponseEntity.badRequest().body(result.getData());*/
        return _foreignLanguageService.getAll();
    }

    @GetMapping("/getOne")
    public ResponseEntity<?> getOne(int id){
        Result result = _foreignLanguageService.getOne(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }


    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody ForeignLanguageDto foreignLanguageDto){
        Result result = _foreignLanguageService.add(foreignLanguageDto);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(int id){
        Result result = _foreignLanguageService.delete(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }


}
