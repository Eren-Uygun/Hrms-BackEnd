package kodlamaio.HumanResourceManagementSystem.api.controllers;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.AuthService;
import kodlamaio.HumanResourceManagementSystem.business.abstracts.UserService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.ErrorResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auths")
@RequiredArgsConstructor
public class AuthsController {

    private final AuthService _authService;

    @PostMapping(value = "/login",headers = {"content-type=application/json"})
   public String login(@RequestBody LoginDto loginDto) throws Exception {
        return _authService.login(loginDto);
    }

    @ExceptionHandler(value = {
            UsernameNotFoundException.class,
            IllegalStateException.class,
            Exception.class,
            AuthenticationException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result handleException(Exception e, HttpServletRequest httpServletRequest) {
        return new ErrorResult("Exception Message Found: "+e.getMessage());
    }

}
