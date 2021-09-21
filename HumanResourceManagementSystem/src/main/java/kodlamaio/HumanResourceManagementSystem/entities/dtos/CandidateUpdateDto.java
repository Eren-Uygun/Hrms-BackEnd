package kodlamaio.HumanResourceManagementSystem.entities.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CandidateUpdateDto {
   // private int id;
    private String firstName;
    private String lastName;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthDate;
    private String nationalIdentityNumber;
    private String email;
    private String password;
    private String passwordConfirm;
}
