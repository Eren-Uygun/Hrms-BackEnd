package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Candidate;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.CoverLetter;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.CoverLetterDto;

import javax.xml.crypto.Data;
import java.util.List;

public interface CovertLetterService {
    Result add(CoverLetterDto coverLetterDto);
    Result delete(int id);

    DataResult<List<CoverLetter>> getAll();
    DataResult<CoverLetter> getOne(int id);
}
