package kodlamaio.HumanResourceManagementSystem.core.utils.Claudinary.concretes;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import kodlamaio.HumanResourceManagementSystem.core.utils.Claudinary.abstracts.CustomImageService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomImageManager implements CustomImageService {


    Cloudinary cloudinary;
    private Map<String,String> valuesMap=new HashMap<>();

    @Autowired
    public CustomImageManager() {
        valuesMap.put("cloud_name","");
        valuesMap.put("api_key", "");
        valuesMap.put("api_secret","");
        cloudinary = new Cloudinary(valuesMap);
    }

    @Override
    public Map uploadImage(MultipartFile multipartFile) throws IOException {
            File file= convert(multipartFile);
            Map result=cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            file.delete();
            return result;
    }

    @Override
    public Map delete(String id) throws IOException {
        Map result= cloudinary.uploader().destroy(id,ObjectUtils.emptyMap());
        return result;
    }

    @Override
    public File convert(MultipartFile multipartFile) throws IOException {
        File file=new File(multipartFile.getOriginalFilename());
        FileOutputStream stream=new FileOutputStream(file);
        stream.write(multipartFile.getBytes());
        stream.close();
        return file;
    }
}
