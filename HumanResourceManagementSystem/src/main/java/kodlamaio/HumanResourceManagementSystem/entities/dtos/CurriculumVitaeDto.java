package kodlamaio.HumanResourceManagementSystem.entities.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Education;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.ForeignLanguage;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobExperience;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Skill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumVitaeDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    private int candidateId;
    private String githubLink;
    private String linkedlIn;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate createdDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate updatedDate;
    private String coverLetter;
    private String photo;
    private List<JobExperience> jobExperiences;
    private List<ForeignLanguage> languages;
    private List<Skill> technologies;
    private List<Education> educations;



}
