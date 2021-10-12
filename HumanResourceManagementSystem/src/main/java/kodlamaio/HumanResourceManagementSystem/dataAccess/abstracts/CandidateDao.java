package kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts;

import kodlamaio.HumanResourceManagementSystem.entities.concretes.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateDao extends JpaRepository<Candidate,Long> {

    Candidate findByNationalIdentityNumber(String nationalIdentityNumber);
    Candidate findByEmail(String email);

}
