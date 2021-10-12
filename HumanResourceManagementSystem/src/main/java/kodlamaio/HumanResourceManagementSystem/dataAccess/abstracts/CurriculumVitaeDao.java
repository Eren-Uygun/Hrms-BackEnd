package kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.CurriculumVitae;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurriculumVitaeDao extends JpaRepository<CurriculumVitae, Long> {

    CurriculumVitae findCurriculumVitaeByCandidate_Id(Long candidateId);

    DataResult<List<CurriculumVitae>> getCurriculumVitaesByCandidate_Id(Long candidateId);

}
