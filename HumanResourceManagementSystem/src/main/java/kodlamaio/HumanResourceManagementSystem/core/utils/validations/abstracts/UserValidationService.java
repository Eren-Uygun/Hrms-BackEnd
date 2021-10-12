package kodlamaio.HumanResourceManagementSystem.core.utils.validations.abstracts;

public interface UserValidationService {

    boolean isUserExists(Long id);
    boolean isMailAddressExists(String mailaddress);


}
