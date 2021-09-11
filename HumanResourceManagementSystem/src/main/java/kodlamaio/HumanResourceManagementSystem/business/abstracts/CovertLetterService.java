package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.CoverLetter;

import javax.xml.crypto.Data;
import java.util.List;

public interface CovertLetterService {
    Result add(CoverLetter coverLetter);
    Result update(CoverLetter coverLetter);
    Result delete(int id);

    DataResult<List<CoverLetter>> getAll();
    DataResult<CoverLetter> getOne(int id);
}
