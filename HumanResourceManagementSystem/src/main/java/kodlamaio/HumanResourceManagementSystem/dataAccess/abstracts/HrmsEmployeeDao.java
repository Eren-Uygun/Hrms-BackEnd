package kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts;

import kodlamaio.HumanResourceManagementSystem.entities.concretes.HrmsEmployee;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.HrmsEmployeeActivation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HrmsEmployeeDao extends JpaRepository<HrmsEmployee,Long> {
    boolean existsByEmail(String email);

}
