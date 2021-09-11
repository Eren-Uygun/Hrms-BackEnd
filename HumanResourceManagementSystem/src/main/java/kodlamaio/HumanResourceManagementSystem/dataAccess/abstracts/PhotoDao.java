package kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts;

import kodlamaio.HumanResourceManagementSystem.entities.concretes.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoDao extends JpaRepository<Photo,Integer> {
}
