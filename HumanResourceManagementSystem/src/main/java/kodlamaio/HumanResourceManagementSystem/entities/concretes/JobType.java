package kodlamaio.HumanResourceManagementSystem.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job_types")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","jobAdvertisement"})
public class JobType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //Full-time , Part-Time , Freelancer

    @Column(name = "job_type")
    private String jobTypeName;

    @JsonIgnore
    @OneToMany(mappedBy = "jobType",cascade = CascadeType.ALL)
    private List<JobAdvertisement> jobAdvertisements;
}
