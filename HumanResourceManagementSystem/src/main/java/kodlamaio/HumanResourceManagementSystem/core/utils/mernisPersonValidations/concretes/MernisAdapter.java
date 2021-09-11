package kodlamaio.HumanResourceManagementSystem.core.utils.mernisPersonValidations.concretes;

import kodlamaio.HumanResourceManagementSystem.core.utils.mernisPersonValidations.abstracts.CandidateValidationService;
import org.springframework.stereotype.Service;

@Service
public class MernisAdapter implements CandidateValidationService {
    @Override
    public boolean isRealPerson(int birthDate, String firstName, String lastName, String nationalIdentityNumber) {

      //  RJTKPSPublicSoap client = new RJTKPSPublicSoap();
       // return client.TCKimlikNoDogrula(Long.parseLong(nationalIdentityNumber),firstName.toUpperCase(Locale.ROOT),lastName.toUpperCase(Locale.ROOT),birthDate);
        return true;
    }
}
