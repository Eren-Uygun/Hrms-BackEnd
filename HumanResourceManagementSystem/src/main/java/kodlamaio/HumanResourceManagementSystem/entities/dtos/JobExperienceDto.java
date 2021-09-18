package kodlamaio.HumanResourceManagementSystem.entities.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
public class JobExperienceDto {
    private int cvId;

    private String companyName;

    private String positionName;

    private String jobInformation;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Past
    private LocalDate startDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    @PastOrPresent
    private LocalDate endDate;
}
