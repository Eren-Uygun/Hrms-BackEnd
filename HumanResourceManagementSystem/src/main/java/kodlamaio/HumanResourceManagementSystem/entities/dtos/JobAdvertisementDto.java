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

    private int jobId;
    private int employerId;
    private String description;
    private String advertisementNumber;
    private int cityId;
    private int minSalary;
    private int maxSalary;
    private int openPosition;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate releaseDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;
    private JobAdvertisementStatus jobAdvertisementStatus;
    private int workPlaceId;
    private int jobTypeId;

}
