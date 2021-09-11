package kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts;

import kodlamaio.HumanResourceManagementSystem.entities.concretes.WorkPlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkPlaceDao extends JpaRepository<WorkPlace,Integer> {
}
