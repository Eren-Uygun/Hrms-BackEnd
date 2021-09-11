package kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts;

import kodlamaio.HumanResourceManagementSystem.entities.concretes.CoverLetter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoverLetterDao extends JpaRepository<CoverLetter, Integer> {
}
