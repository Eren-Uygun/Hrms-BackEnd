package kodlamaio.HumanResourceManagementSystem.entities.dtos;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class ForeignLanguageDto {
    private int cvId;
    private String languageName;

    @Min(1)@Max(5)
    private int languageLevel;
}
