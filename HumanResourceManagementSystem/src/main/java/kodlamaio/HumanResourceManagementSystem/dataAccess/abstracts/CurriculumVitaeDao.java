package kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.CurriculumVitae;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurriculumVitaeDao extends JpaRepository<CurriculumVitae, Integer> {

    CurriculumVitae findCurriculumVitaeByCandidate_Id(int candidateId);

    DataResult<List<CurriculumVitae>> getCurriculumVitaesByCandidate_Id(int candidateId);

}
