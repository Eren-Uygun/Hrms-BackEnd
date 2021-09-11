package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoService {

    DataResult<List<Photo>> getAll();
    Result update(MultipartFile multipartFile, int cvId);
    Result delete(int id);
    DataResult<Photo> getById(int id);
    Boolean isExist(int id);
}
