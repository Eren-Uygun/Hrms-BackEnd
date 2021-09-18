package kodlamaio.HumanResourceManagementSystem.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Entity
@Table(name="educations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Education {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="school_name")
    private String schoolName;

    @Column(name="start_date") @PastOrPresent
    private LocalDate startingDate;

    @Column(name="graduate_date")@PastOrPresent
    private LocalDate graduateDate;

    @Column(name="department_name")
    private String departmentName;

    @Column(name="is_gradudated")
    private boolean isGradudated=false;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="curriculumVitae_id")
    private CurriculumVitae curriculumVitae;
}
