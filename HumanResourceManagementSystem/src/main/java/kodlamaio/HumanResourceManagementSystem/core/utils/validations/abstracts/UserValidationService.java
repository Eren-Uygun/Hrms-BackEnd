package kodlamaio.HumanResourceManagementSystem.core.utils.validations.abstracts;

public interface UserValidationService {

    boolean isUserExists(int id);
    boolean isMailAddressExists(String mailaddress);


}
