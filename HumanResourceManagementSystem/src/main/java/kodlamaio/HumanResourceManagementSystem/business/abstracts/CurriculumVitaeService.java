package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.CurriculumVitae;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.CurriculumVitaeDetailsAddDto;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.CurriculumVitaeDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CurriculumVitaeService {
    Result add(CurriculumVitaeDto curriculumVitaeDto );
    Result update(CurriculumVitaeDto curriculumVitaeDto,Long cvId);
    Result delete(Long id);

    DataResult<CurriculumVitae> getCurriculumVitaeByCandidate(Long candidateId);
    DataResult<CurriculumVitae> findById(Long id);

    public Result updateGithub(String github, Long cvId);
    public Result deleteGithub(Long cvId);

    public Result updateLinkedin(String linkedin, Long cvId);
    public Result deleteLinkedin(Long cvId);

    public Result updateAboutMe(String biography, Long cvId);
    public Result deleteAboutMe(Long cvId);




}
