package kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts;

import kodlamaio.HumanResourceManagementSystem.entities.concretes.HrmsEmployeeActivation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HrmsEmployeeActivationDao extends JpaRepository<HrmsEmployeeActivation,Long> {

}
