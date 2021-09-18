package kodlamaio.HumanResourceManagementSystem.entities.dtos;

import kodlamaio.HumanResourceManagementSystem.entities.concretes.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumVitaeDetailsAddDto {

    private int cvId;
    private List<CoverLetter> coverLetters;
    private List<JobExperience> jobExperiences;
    private List<ForeignLanguage> languages;
    private List<Skill> skills;
    private List<Education> educations;
}
