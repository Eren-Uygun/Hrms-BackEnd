package kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts;

import kodlamaio.HumanResourceManagementSystem.entities.concretes.EmployerActivationByEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerActivationByEmployeeDao extends JpaRepository<EmployerActivationByEmployee,Integer> {
}
