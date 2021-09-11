package kodlamaio.HumanResourceManagementSystem.core.utils.mernisPersonValidations.abstracts;

public interface CandidateValidationService {
    boolean isRealPerson(int birthDate,String firstName,String lastName,String nationalIdentityNumber);
}
