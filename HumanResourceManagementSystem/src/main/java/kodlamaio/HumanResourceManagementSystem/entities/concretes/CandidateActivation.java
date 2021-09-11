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
@Table(name = "candidate_activations")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","candidate"})
public class CandidateActivation extends Activation {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
}
