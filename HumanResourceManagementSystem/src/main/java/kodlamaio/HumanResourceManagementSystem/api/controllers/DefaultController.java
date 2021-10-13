package kodlamaio.HumanResourceManagementSystem.api.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api")
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class DefaultController {

    public ModelAndView defaultPage(){
        return new ModelAndView("redirect:/swagger-ui.html");
    }

}
