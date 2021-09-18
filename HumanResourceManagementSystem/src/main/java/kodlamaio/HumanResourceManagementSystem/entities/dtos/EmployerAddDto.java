package kodlamaio.HumanResourceManagementSystem.entities.dtos;

import lombok.Data;

@Data
public class EmployerAddDto {

    private String companyName;
    private String website;
    private String phoneNumber;
    private String email;
    private String password;
    private String confirmPassword;

}
