package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.CurriculumVitaeService;
import kodlamaio.HumanResourceManagementSystem.core.utils.Claudinary.abstracts.ImageService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.*;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.CurriculumVitaeDao;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.CurriculumVitae;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.CurriculumVitaeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CurriculumVitaeManager implements CurriculumVitaeService {
    private CurriculumVitaeDao _curriculumVitaeDao;
    private ImageService _imageService;

    @Autowired
    public CurriculumVitaeManager(CurriculumVitaeDao _curriculumVitaeDao) {
        this._curriculumVitaeDao = _curriculumVitaeDao;
    }


    @Override
    public Result add(CurriculumVitaeDto curriculumVitaeDto) {

        return null;
    }

    @Override
    public Result update(CurriculumVitaeDto curriculumVitaeDto) {
        return null;
    }

    @Override
    public Result delete(int id) {
        var temp = _curriculumVitaeDao.getById(id);
        if (temp.getId()!=id){
            return new ErrorResult("Silinecek veri bulunamadı.");
        }else{
            _curriculumVitaeDao.deleteById(id);
            return new SuccessResult("Veri silindi.");
        }
    }

    @Override
    public DataResult<List<CurriculumVitae>> getCurriculumVitaeByCandidate(int candidateId) {
        return null;
    }

    @Override
    public DataResult<CurriculumVitae> findById(int id) {

        return new SuccessDataResult<CurriculumVitae>(_curriculumVitaeDao.getById(id),"Veri getirildi.");
    }

    @Override
    public Result uploadCvPhoto(int cvId, MultipartFile multipartFile) throws IOException {
        var temp = _curriculumVitaeDao.getById(cvId);
        if (temp.getPhoto()!=null){
            return new ErrorResult("Cv bulunamadı.");
        }
        _imageService.uploadImage(multipartFile);
        return null;
    }
}
