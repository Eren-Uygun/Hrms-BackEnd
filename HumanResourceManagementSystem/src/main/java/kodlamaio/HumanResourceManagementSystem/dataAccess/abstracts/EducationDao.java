package kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EducationDao extends JpaRepository<Education,Integer> {

    @Query("select e from Education e where e.curriculumVitae.id =: id")
    DataResult<List<Education>>  getEducationsByCurriculumVitae_Id(int id);

}
