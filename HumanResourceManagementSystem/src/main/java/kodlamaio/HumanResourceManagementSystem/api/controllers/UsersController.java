package kodlamaio.HumanResourceManagementSystem.api.controllers;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.UserService;
import kodlamaio.HumanResourceManagementSystem.business.concretes.UserManager;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.RoleDao;
import kodlamaio.HumanResourceManagementSystem.entities.abstracts.Role;
import kodlamaio.HumanResourceManagementSystem.entities.abstracts.User;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.CandidateAddDto;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.EmployerAddDto;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.LoginDto;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.RoleAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UsersController {


    private final RoleDao _roleDao;
    private UserService _userService;


    public UsersController(RoleDao _roleDao, UserService _userService) {
        this._roleDao = _roleDao;
        this._userService = _userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(_userService.getUsers());
    }

    @PostMapping("/role/add")
    public ResponseEntity<?> addRole(@RequestBody Role role){
        Result tempRole = _userService.saveRole(role);
        if (tempRole.isSuccess()){
            return ResponseEntity.ok(tempRole);
        }
        return ResponseEntity.badRequest().body(tempRole);

    }

}
