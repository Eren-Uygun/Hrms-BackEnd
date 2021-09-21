package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;

public interface ActivationService {

    Result activateByActivationCode(String activationCode);
    Result manualEmployerActivation(int employerId,int employeeId);
    Result activateEmployerByActivationCode(String activationCode);
    Result activateHrmsEmployeeByActivationCode(String activationCode);
    Result jobAdvertisementActivation(int employeeId,int jobAdvertisementId);

    //Result jobAdvertisementActivationByHrmsEmployee(int hrmsEmployeeId,String activationNumber,int advertisementNumber);

}
