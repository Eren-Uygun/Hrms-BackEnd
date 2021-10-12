package kodlamaio.HumanResourceManagementSystem.entities.abstracts;

import com.fasterxml.jackson.annotation.JsonFormat;
import kodlamaio.HumanResourceManagementSystem.core.enums.activationEnums.UserActivationStatus;
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
@Table(name = "activations")
public abstract class Activation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "activation_number",unique = true)
    private String activationNumber;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "activation_date")
    private LocalDate activationDate;

    @Column(name = "activation_status")
    @Enumerated(EnumType.STRING)
    private UserActivationStatus userActivationStatus;

}
