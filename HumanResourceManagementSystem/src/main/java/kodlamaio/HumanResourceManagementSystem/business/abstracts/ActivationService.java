package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;

public interface ActivationService {

    Result activateByActivationCode(String activationCode);
    Result manualEmployerActivation(Long employerId,Long employeeId);
    Result activateEmployerByActivationCode(String activationCode);
    Result activateHrmsEmployeeByActivationCode(String activationCode);
    Result jobAdvertisementActivation(Long employeeId,Long jobAdvertisementId);
    Result employerUpdateConfirmation(Long employeeId,Long employerId);

    //Result jobAdvertisementActivationByHrmsEmployee(int hrmsEmployeeId,String activationNumber,int advertisementNumber);

}
