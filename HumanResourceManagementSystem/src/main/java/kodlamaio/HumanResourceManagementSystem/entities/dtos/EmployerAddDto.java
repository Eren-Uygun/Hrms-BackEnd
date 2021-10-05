package kodlamaio.HumanResourceManagementSystem.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerAddDto {

    private String companyName;
    private String website;
    private String phoneNumber;
    private String email;
    private String password;
    private String confirmPassword;

}
