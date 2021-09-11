package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Candidate;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Job;

import java.util.List;

public interface CandidateService {
    Result add(Candidate candidate);
    Result update(Candidate candidate);
    Result delete(int id);

    DataResult<List<Candidate>> getAll();
    DataResult<Candidate> getById(int id);
    DataResult<Candidate> getByNationalIdentityNumber(String nationalIdentityNumber);
}