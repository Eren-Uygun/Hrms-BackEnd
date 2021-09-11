package kodlamaio.HumanResourceManagementSystem.entities.concretes;

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
@EqualsAndHashCode(callSuper = false)
@Table(name = "job_advertisements_activation_by_hrmsemployees")
public class JobAdvertisementActivationByEmployee extends JobAdvertisementActivation {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hrms_employee_id")
    private HrmsEmployee hrmsEmployee;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_advertisement_id")
    private JobAdvertisement jobAdvertisement;
}
