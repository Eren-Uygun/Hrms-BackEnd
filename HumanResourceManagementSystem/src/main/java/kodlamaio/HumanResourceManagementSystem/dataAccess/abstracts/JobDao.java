package kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts;

import kodlamaio.HumanResourceManagementSystem.entities.concretes.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobDao  extends JpaRepository<Job,Integer> {
    Job findByJobName(String occupationName);
    boolean existsByJobName(String occupationName);
    boolean existsById(int id);

}
