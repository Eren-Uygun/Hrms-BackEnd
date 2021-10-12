package kodlamaio.HumanResourceManagementSystem.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","curriculumVitae"})
@Table(name = "job_experiences")
public class JobExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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
    @Column(name = "work_status")
    public boolean isStillWorking=false;


    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "curriculumVitae_id")
    private CurriculumVitae curriculumVitae;
}
