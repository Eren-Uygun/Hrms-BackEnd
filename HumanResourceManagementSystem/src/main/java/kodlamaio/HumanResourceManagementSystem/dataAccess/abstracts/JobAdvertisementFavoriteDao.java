package kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts;

import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobAdvertisement;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobAdvertisementFavorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobAdvertisementFavoriteDao extends JpaRepository<JobAdvertisementFavorite,Long> {
    boolean existsByCandidate_IdAndJobAdvertisement_Id(Long candidateId,Long jobAdvertisementId);

    List<JobAdvertisementFavorite> findByCandidate_Id(Long candidateId);
}
