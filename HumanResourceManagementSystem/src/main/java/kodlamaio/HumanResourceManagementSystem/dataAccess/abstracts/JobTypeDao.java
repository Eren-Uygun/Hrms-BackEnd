package kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts;

import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobTypeDao extends JpaRepository<JobType,Long> {
    boolean existsByJobTypeName(String jobType);
}
