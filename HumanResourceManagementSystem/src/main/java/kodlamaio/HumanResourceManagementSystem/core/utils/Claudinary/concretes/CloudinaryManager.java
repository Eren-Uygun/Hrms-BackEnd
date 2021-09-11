package kodlamaio.HumanResourceManagementSystem.core.utils.Claudinary.concretes;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import kodlamaio.HumanResourceManagementSystem.core.utils.Claudinary.abstracts.ImageService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.ErrorDataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.SuccessDataResult;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class CloudinaryManager implements ImageService {

    Cloudinary cloudinary = new Cloudinary();

    @Override
    public DataResult<?> uploadImage(MultipartFile file) {
        try {
            Map upload = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            return new SuccessDataResult<Map>(upload,"Resim eklendi.");
        } catch (IOException e) {
            e.printStackTrace();
            return new ErrorDataResult<Map>("Resim ekleme hatası");
        }
    }
}
