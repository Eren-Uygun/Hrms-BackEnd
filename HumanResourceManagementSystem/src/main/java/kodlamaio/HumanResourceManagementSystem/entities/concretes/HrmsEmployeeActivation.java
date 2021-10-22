package kodlamaio.HumanResourceManagementSystem.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kodlamaio.HumanResourceManagementSystem.entities.abstracts.Activation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "hrms_employee_activations")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","hrmsemployee"})
public class HrmsEmployeeActivation extends Activation {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hrms_employee_id")
    private HrmsEmployee employee;

}
