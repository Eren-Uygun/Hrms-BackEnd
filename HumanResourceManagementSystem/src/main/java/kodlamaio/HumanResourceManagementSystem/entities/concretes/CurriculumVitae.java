package kodlamaio.HumanResourceManagementSystem.entities.concretes;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Curriculum_vitaes")
public class CurriculumVitae {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne()
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;


    @Column(name = "github_address")
    private String github;

    @Column(name = "linkedin_address")
    private String linkedin;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "created_date")
    private LocalDate createdDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @OneToOne(mappedBy = "curriculumVitae",cascade = CascadeType.ALL)
    private CoverLetter coverLetters;

    @OneToOne(mappedBy = "curriculumVitae",cascade = CascadeType.ALL)
    private Image photo;

    @OneToMany(mappedBy = "curriculumVitae", cascade = CascadeType.ALL )
    private List<ForeignLanguage> foreignLanguage;

    @OneToMany(mappedBy = "curriculumVitae",cascade = CascadeType.ALL)
    private List<Education> education;

    @OneToMany(mappedBy = "curriculumVitae",cascade = CascadeType.ALL)
    private List<JobExperience> jobExperience;

    @OneToMany(mappedBy = "curriculumVitae",cascade = CascadeType.ALL)
    private  List<Skill> skill;






}
