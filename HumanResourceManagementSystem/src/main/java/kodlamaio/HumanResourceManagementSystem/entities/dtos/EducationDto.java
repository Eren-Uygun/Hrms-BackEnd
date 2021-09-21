package kodlamaio.HumanResourceManagementSystem.entities.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
public class EducationDto {

    private String schoolName;

    @PastOrPresent
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startingDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate graduateDate;

    private String departmentName;


}
