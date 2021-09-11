package kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts;

import kodlamaio.HumanResourceManagementSystem.entities.concretes.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillDao extends JpaRepository<Skill,Integer> {
}
