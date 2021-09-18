package kodlamaio.HumanResourceManagementSystem.core.utils.Claudinary.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface CustomImageService {

    Map uploadImage(MultipartFile file) throws IOException;
    public Map delete(String id) throws IOException;
    public File convert(MultipartFile multipartFile) throws IOException;
}
