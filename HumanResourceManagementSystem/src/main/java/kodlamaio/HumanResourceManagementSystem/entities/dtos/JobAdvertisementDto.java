package kodlamaio.HumanResourceManagementSystem.entities.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import kodlamaio.HumanResourceManagementSystem.core.enums.jobAdvertisementEnums.JobAdvertisementStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisementDto {

    private Long jobId;
    private Long employerId;
    private String description;
    private Long cityId;
    private int minSalary;
    private int maxSalary;
    private int openPosition;
    private Long workPlaceId;
    private Long jobTypeId;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;

}
