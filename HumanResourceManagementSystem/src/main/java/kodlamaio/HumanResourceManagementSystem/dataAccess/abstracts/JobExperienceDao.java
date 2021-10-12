package kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Job;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobExperienceDao extends JpaRepository<JobExperience,Long> {

    @Query("select j from JobExperience j where j.curriculumVitae.id =: cvId")
    DataResult<List<JobExperience>> getJobExperiencesByCurriculumVitae_Id(Long cvId);
}
