package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {

    DataResult<List<Image>> getAll();
    Result update(MultipartFile multipartFile, Long cvId) throws IOException;
    Result delete(Long id);
    DataResult<Image> getById(Long id);
    Boolean isExist(Long id);

}
