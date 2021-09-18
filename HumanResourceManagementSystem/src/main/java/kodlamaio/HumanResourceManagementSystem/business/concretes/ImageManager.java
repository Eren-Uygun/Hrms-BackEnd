package kodlamaio.HumanResourceManagementSystem.business.concretes;


import kodlamaio.HumanResourceManagementSystem.business.abstracts.ImageService;
import kodlamaio.HumanResourceManagementSystem.core.utils.Claudinary.abstracts.CustomImageService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.*;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.CurriculumVitaeDao;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.ImageDao;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ImageManager implements ImageService {

    private ImageDao _imageDao;
    private CurriculumVitaeDao _curriculumVitaeDao;
    private CustomImageService _cloudinaryImageService;


    @Override
    public DataResult<List<Image>> getAll() {
        return new SuccessDataResult<>(_imageDao.findAll(),"Veriler getirildi.");
    }

    @Override
    public Result update(MultipartFile multipartFile, int cvId) throws IOException {
        try {
            BufferedImage bufferedImage= ImageIO.read(multipartFile.getInputStream());
            if(bufferedImage==null){
                return new ErrorResult("Resim validasyonu başarısız");
            }
        }catch (IOException exception){
            return new ErrorResult("Resim validasyonu başarısız");
        }
        Image image=this._imageDao.findByCurriculumVitae_Id(cvId);
        if(image.getImageId()==null){
            try {
                Map result=_cloudinaryImageService.uploadImage(multipartFile);
                image.setName((String)result.get("original_filename"));
                image.setImageUrl((String)result.get("url"));
                image.setImageId((String)result.get("public_id"));
                this._imageDao.save(image);
                return new SuccessResult("Başarıyla eklendi");
            }catch (IOException exception){
                return new ErrorResult("Resim yükleme aşamasında bir sorun oldu");
            }
        }else if(image.getImageId()!=null){
            //claudanry silme
            try {
                Map result=_cloudinaryImageService.delete(image.getImageId());
                Map result2=_cloudinaryImageService.uploadImage(multipartFile);
                image.setName((String)result2.get("original_filename"));
                image.setImageUrl((String)result2.get("url"));
                image.setImageId((String)result2.get("public_id"));
                this._imageDao.save(image);
                return new SuccessResult("Başarıyla yüklendi");
            }catch (IOException exception){
                return new ErrorResult("Resim yükleme aşamasında bir sorun oldu");
            }
        }else{
            return new ErrorResult("Bir sorun var lütfen iletişime mail at");
        }
    }

    @Override
    public Result delete(int id) {
        return null;
    }

    @Override
    public DataResult<Image> getById(int id) {
        return null;
    }

    @Override
    public Boolean isExist(int id) {
        return null;
    }
}

