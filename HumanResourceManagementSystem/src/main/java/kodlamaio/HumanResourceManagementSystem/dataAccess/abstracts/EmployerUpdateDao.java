package kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts;

import kodlamaio.HumanResourceManagementSystem.entities.concretes.EmployerUpdate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerUpdateDao extends JpaRepository<EmployerUpdate,Long> {

    boolean existsByEmployerId(Long id);
    EmployerUpdate getByEmployerId(Long employerId);

}
