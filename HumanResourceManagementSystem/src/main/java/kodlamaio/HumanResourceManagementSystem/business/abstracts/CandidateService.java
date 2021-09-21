package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Candidate;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Job;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.CandidateAddDto;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.CandidateUpdateDto;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CandidateService {
    Result add(CandidateAddDto candidateAddDto);
    Result update(CandidateUpdateDto candidateUpdateDto,int id);
    Result delete(int id);

    DataResult<List<Candidate>> getAll();
    DataResult<Candidate> getById(int id);
    DataResult<Candidate> getByNationalIdentityNumber(String nationalIdentityNumber);
}
