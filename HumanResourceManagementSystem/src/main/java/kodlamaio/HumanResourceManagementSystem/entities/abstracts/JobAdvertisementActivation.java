package kodlamaio.HumanResourceManagementSystem.entities.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.enums.jobAdvertisementEnums.JobAdvertisementActivationStatus;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobAdvertisement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "job_advertisement_activations")
public abstract class JobAdvertisementActivation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "advertisement_activation_date")
    private LocalDate advertisementActivationDate;

    @Column(name = "advertisement_activation_status")
    @Enumerated(EnumType.STRING)
    private JobAdvertisementActivationStatus jobAdvertisementActivationStatus;


}
