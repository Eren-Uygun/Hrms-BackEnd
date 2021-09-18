package kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.ForeignLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ForeignLanguageDao extends JpaRepository<ForeignLanguage, Integer> {

    @Query("select f from ForeignLanguage f where f.curriculumVitae.id =: cvId")
    DataResult<List<ForeignLanguage>> getForeignLanguagesByCurriculumVitae_Id(int cvId);
}
