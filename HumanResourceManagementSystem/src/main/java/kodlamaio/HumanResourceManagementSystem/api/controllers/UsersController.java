package kodlamaio.HumanResourceManagementSystem.api.controllers;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.UserService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.abstracts.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static java.util.Arrays.stream;

@RestController
@RequestMapping("/api")
public class UsersController {


    private UserService _userService;


    @Autowired
    public UsersController( UserService _userService) {
        this._userService = _userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(_userService.getUsers());
    }


}
