package kodlamaio.HumanResourceManagementSystem.api.controllers;

import kodlamaio.HumanResourceManagementSystem.entities.dtos.CandidateAddDto;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.EmployerAddDto;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.LoginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UsersController {

    @PostMapping("/candidate/register")
    public ResponseEntity<?> register(CandidateAddDto candidateAddDto) {
        return null;
    }

    @PostMapping("/employer/register")
    public ResponseEntity<?> register(EmployerAddDto employerAddDto) {
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(LoginDto dto) {
        return null;
    }

}
