package kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.CoverLetter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CoverLetterDao extends JpaRepository<CoverLetter, Integer> {

    @Query("select c from CoverLetter c where c.curriculumVitae.id =: id")
    DataResult<List<CoverLetter>> getCoverLettersByCurriculumVitae_Id(int id);
}
