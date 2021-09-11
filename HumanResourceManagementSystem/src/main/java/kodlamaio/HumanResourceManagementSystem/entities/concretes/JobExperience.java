package kodlamaio.HumanResourceManagementSystem.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job_experiences")
public class JobExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "company_name")
    private String companyName;
    @Column(name = "position_name")
    private String positionName;
    @Column(name = "job_information")
    private String jobInformation;
    @Column(name = "job_started_date")
    private LocalDate startDate;
    @Column(name = "job_ended_date")
    private LocalDate endDate;


    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "curriculumVitae_id")
    private CurriculumVitae curriculumVitae;
}