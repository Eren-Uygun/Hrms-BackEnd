package kodlamaio.HumanResourceManagementSystem.core.utils.Claudinary.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface ImageService {
    DataResult<?> uploadImage(MultipartFile file);
}
