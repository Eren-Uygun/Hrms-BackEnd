package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {

    DataResult<List<Image>> getAll();
    Result update(MultipartFile multipartFile, int cvId) throws IOException;
    Result delete(int id);
    DataResult<Image> getById(int id);
    Boolean isExist(int id);

}
