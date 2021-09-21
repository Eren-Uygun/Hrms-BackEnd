package kodlamaio.HumanResourceManagementSystem.api.controllers;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.EmployerService;
import kodlamaio.HumanResourceManagementSystem.business.abstracts.HrmsEmployeeService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.HrmsEmployeeDao;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Candidate;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Employer;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.HrmsEmployee;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.HrmsEmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.crypto.Data;
import java.util.List;

@RestController
@RequestMapping("/api/hrmsEmployees")
@CrossOrigin
public class HrmsEmployeesController {
    private HrmsEmployeeService _employeeService;

    @Autowired
    public HrmsEmployeesController(HrmsEmployeeService _employeeService) {
        this._employeeService = _employeeService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> add(@Valid @RequestBody HrmsEmployeeDto hrmsEmployee) {
        Result result = this._employeeService.add(hrmsEmployee);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(hrmsEmployee);

    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody HrmsEmployeeDto hrmsEmployeeDto,int hrmsEmployeeId){
        Result result = _employeeService.update(hrmsEmployeeDto,hrmsEmployeeId);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);

    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(int employerId){
        Result result= _employeeService.getById(employerId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    /*
    public ResponseEntity<List<?>> getAll(){
        DataResult<List<HrmsEmployee>> result = _employeeService.getAll();
        if (result.isSuccess()){
            return ResponseEntity.ok(result.getData());
        }
        return ResponseEntity.badRequest().body(result.getData());
    }
    */

    @GetMapping("/getall")
    public DataResult<List<HrmsEmployee>> getAll(){
        return _employeeService.getAll();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(int hrmsEmployeeId) {
        Result result = _employeeService.delete(hrmsEmployeeId);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }


}
