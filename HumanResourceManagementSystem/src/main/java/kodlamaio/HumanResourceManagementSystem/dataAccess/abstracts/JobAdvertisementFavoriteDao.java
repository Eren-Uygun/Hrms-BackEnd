package kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts;

import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobAdvertisement;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobAdvertisementFavorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobAdvertisementFavoriteDao extends JpaRepository<JobAdvertisementFavorite,Integer> {
    boolean existsByCandidate_IdAndJobAdvertisement_Id(int candidateId,int jobAdvertisementId);

    List<JobAdvertisementFavorite> findByCandidate_Id(int candidateId);
}
