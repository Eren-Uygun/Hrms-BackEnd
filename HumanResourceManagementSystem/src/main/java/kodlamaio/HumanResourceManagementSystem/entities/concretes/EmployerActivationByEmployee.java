package kodlamaio.HumanResourceManagementSystem.entities.concretes;

import kodlamaio.HumanResourceManagementSystem.entities.abstracts.Activation;
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
@Table(name = "employer_activation_by_employees")
public class EmployerActivationByEmployee extends Activation {

    @OneToOne(cascade = CascadeType.DETACH,fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id")
    private Employer employer;

    @OneToOne()
    @JoinColumn(name = "hrms_employee_id")
    private HrmsEmployee employee;
}
