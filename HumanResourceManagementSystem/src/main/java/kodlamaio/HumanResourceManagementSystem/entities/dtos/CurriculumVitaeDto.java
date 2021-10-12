package kodlamaio.HumanResourceManagementSystem.entities.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumVitaeDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private Long candidateId;
    private String photo;
    private String githubLink;
    private String linkedIn;
    //Alt bölüm Cv controller içinde ayrı ekleme ile yapılabilir ??




}
