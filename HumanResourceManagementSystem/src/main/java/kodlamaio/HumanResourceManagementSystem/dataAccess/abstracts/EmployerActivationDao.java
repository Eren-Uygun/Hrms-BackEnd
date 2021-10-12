package kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts;

import kodlamaio.HumanResourceManagementSystem.entities.concretes.EmployerActivation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerActivationDao extends JpaRepository<EmployerActivation,Long> {
    EmployerActivation findByActivationNumber(String activationNumber);
}
