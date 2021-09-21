package kodlamaio.HumanResourceManagementSystem.entities.dtos;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class SkillDto {
    private String skillName;
    @Min(1) @Max(5)
    private int SkillRate;
}
