package kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts;

import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobAdvertisementFavorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobAdvertisementFavoriteDao extends JpaRepository<JobAdvertisementFavorite,Integer> {
}
