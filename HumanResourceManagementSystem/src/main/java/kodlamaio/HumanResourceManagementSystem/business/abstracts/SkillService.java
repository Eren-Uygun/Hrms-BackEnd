package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Skill;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.SkillDto;

import java.util.List;

public interface SkillService {
    Result add(SkillDto skillDto,int cvId);
    Result update(SkillDto skillDto,int cvId,int skillId);
    Result delete(int skillId,int cvId);

}
