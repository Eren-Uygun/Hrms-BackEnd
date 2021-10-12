package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Skill;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.SkillDto;

import java.util.List;

public interface SkillService {
    Result add(SkillDto skillDto,Long cvId);
    Result update(SkillDto skillDto,Long cvId,Long skillId);
    Result delete(Long skillId,Long cvId);

}
