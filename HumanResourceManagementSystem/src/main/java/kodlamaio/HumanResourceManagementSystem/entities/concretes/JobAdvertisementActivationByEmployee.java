package kodlamaio.HumanResourceManagementSystem.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kodlamaio.HumanResourceManagementSystem.entities.abstracts.JobAdvertisementActivation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","jobAdvertisement","hrmsEmployee"})
@Table(name = "job_advertisements_activation_by_hrmsemployees")
public class JobAdvertisementActivationByEmployee extends JobAdvertisementActivation {

    @ManyToOne()
    @JoinColumn(name = "hrms_employee_id")
    private HrmsEmployee hrmsEmployee;

    @OneToOne()
    @JoinColumn(name = "job_advertisement_id")
    private JobAdvertisement jobAdvertisement;
}
