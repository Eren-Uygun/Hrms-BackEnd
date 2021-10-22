package kodlamaio.HumanResourceManagementSystem.entities.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HrmsEmployeeDto {
    private String firstName;
    private String lastName;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthDate;
    private String department;
    private String email;
    private String password;
    private String passwordConfirm;
}
