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
    Result update(CurriculumVitaeDto curriculumVitaeDto,int cvId);
    Result delete(int id);

    DataResult<CurriculumVitae> getCurriculumVitaeByCandidate(int candidateId);
    DataResult<CurriculumVitae> findById(int id);

    public Result updateGithub(String github, int cvId);
    public Result deleteGithub(int cvId);

    public Result updateLinkedin(String linkedin, int cvId);
    public Result deleteLinkedin(int cvId);

    public Result updateAboutMe(String biography, int cvId);
    public Result deleteAboutMe(int cvId);




}
