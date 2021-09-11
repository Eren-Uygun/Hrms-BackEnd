package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.PhotoService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class PhotoManager implements PhotoService {
    @Override
    public DataResult<List<Photo>> getAll() {
        return null;
    }

    @Override
    public Result update(MultipartFile multipartFile, int cvId) {
        return null;
    }

    @Override
    public Result delete(int id) {
        return null;
    }

    @Override
    public DataResult<Photo> getById(int id) {
        return null;
    }

    @Override
    public Boolean isExist(int id) {
        return null;
    }
}
