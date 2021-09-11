package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.CurriculumVitae;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.CurriculumVitaeDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CurriculumVitaeService {
    Result add(CurriculumVitaeDto curriculumVitaeDto );
    Result update(CurriculumVitaeDto curriculumVitaeDto);
    Result delete(int id);

    DataResult<List<CurriculumVitae>> getCurriculumVitaeByCandidate(int candidateId);
    DataResult<CurriculumVitae> findById(int id);

    Result uploadCvPhoto(int cvId, MultipartFile  multipartFile)throws IOException;


}
