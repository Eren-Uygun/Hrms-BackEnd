package kodlamaio.HumanResourceManagementSystem.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","curriculumVitae"})
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "skill_name")
    private String skillName;

    @Column(name = "skill_rate")
    @Max(5) @Min(1)
    private int skillRate;

    @JsonIgnore
    @JoinColumn(name = "curriculumVitae_id")
    @ManyToOne()
    private CurriculumVitae curriculumVitae;
}
