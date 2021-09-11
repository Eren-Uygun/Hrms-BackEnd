package kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts;

import kodlamaio.HumanResourceManagementSystem.entities.concretes.CandidateActivation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateActivationDao extends JpaRepository<CandidateActivation,Integer> {
}
