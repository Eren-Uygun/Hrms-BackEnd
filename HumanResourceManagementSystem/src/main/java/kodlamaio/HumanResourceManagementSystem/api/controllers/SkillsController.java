package kodlamaio.HumanResourceManagementSystem.api.controllers;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.SkillService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Skill;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.SkillDto;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/{cvId}/skills")
public class SkillsController {

    private SkillService _skillService;

    @Autowired
    public SkillsController(SkillService _skillService) {
        this._skillService = _skillService;
    }

    /*
    @GetMapping("/getOne")
    public ResponseEntity<?> getOne(int id){
        Result result = _skillService.getOne(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/getAll")
    public DataResult<List<Skill>> getAll(){
        /*
        DataResult<List<Skill>> result = _skillService.getAll();
        if (result.isSuccess()){
            return ResponseEntity.ok(result.getData());
        }
        return ResponseEntity.badRequest().body(result.getData());
        return _skillService.getAll();
    }
*/


    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(int skillId,@PathVariable("cvId") int cvId){
        Result result = _skillService.delete(skillId,cvId);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody SkillDto skill,@PathVariable("cvId") int cvId){
        Result result = _skillService.add(skill,cvId);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping("/update/{skillId}")
    public ResponseEntity<?> update(@RequestBody SkillDto skillDto,@PathVariable("cvId") int cvId, @PathVariable("skillId") int skillId){
        Result result = _skillService.update(skillDto,cvId,skillId);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }



}
