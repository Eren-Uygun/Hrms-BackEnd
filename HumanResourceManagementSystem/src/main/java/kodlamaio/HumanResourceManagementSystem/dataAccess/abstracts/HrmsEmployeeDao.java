package kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts;

import kodlamaio.HumanResourceManagementSystem.entities.concretes.HrmsEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HrmsEmployeeDao extends JpaRepository<HrmsEmployee, Integer> {
}
