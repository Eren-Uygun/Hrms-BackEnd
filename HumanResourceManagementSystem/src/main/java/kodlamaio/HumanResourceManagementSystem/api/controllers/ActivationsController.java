package kodlamaio.HumanResourceManagementSystem.api.controllers;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.ActivationService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping(value = "/manualEmployerActivation")
    public Result manualEmployerActivation(Long employerId,Long hrmsPersonelId){
      return _activationService.manualEmployerActivation(employerId,hrmsPersonelId);
    }

    @PostMapping("/activeEmployerByActivationCode")
    public  Result activateEmployerByActivationCode(String activationCode){
        return _activationService.activateEmployerByActivationCode(activationCode);
    }
    @PostMapping("/activateJobAdvertisementByEmployee")
    public Result activateJobAdvertisementByEmployee(Long employeeId,Long jobAdvertisementId){
        return _activationService.jobAdvertisementActivation(employeeId,jobAdvertisementId);
    }

    @PostMapping("/employerUpdateConfirmationByEmployee")
    public Result employerUpdateConfirmationByEmployee(Long employeeId,Long employerId){
        return _activationService.employerUpdateConfirmation(employeeId,employerId);
    }

}
