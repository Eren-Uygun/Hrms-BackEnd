package kodlamaio.HumanResourceManagementSystem.api.controllers;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.ActivationService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activations")
public class ActivationsController {

    private ActivationService _activationService;

    @Autowired
    public ActivationsController(ActivationService _activationService) {
        this._activationService = _activationService;
    }

    @PostMapping(value = "/candidateActivateByCode")
    public Result activateCandidateByActivationCode(String activationCode){
      return  _activationService.activateByActivationCode(activationCode);
    }

    @PostMapping(value = "/manualActivation")
    public Result manualEmployerActivation(int employerId,int hrmsPersonelId){
      return _activationService.manualEmployerActivation(employerId,hrmsPersonelId);

    }

    @PostMapping("/employerActivateByCode")
    public  Result activateEmployerByActivationCode(String activationCode){
        return _activationService.activateEmployerByActivationCode(activationCode);
    }

}
