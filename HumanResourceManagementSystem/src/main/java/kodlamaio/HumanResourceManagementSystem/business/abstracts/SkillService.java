package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Skill;

import java.util.List;

public interface SkillService {
    Result add(Skill skill);
    Result update(Skill skill);
    Result delete(int id);

    DataResult<Skill> getOne(int id);
    DataResult<List<Skill>> getAll();
}
