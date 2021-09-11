package kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts;

import kodlamaio.HumanResourceManagementSystem.entities.abstracts.Activation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivationDao extends JpaRepository<Activation,Integer> {
    Activation findByActivationNumber(String activationNumber);
}
