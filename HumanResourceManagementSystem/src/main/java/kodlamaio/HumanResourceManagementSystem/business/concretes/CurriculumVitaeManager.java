package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.CurriculumVitaeService;
import kodlamaio.HumanResourceManagementSystem.business.constants.BusinessMessage;
import kodlamaio.HumanResourceManagementSystem.core.utils.Claudinary.abstracts.CustomImageService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.*;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.*;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.CurriculumVitae;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Image;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.CurriculumVitaeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CurriculumVitaeManager implements CurriculumVitaeService {
    private CurriculumVitaeDao _curriculumVitaeDao;
    private CandidateDao _candidateDao;
    private CustomImageService _imageService;
    private ImageDao _imageDao;
    private CoverLetterDao _coverLetterDao;
    private ForeignLanguageDao _foreignLanguageDao;
    private SkillDao _skillDao;
    private EducationDao _educationDao;
    private JobExperienceDao _jobExperienceDao;


    @Autowired
    public CurriculumVitaeManager(CurriculumVitaeDao _curriculumVitaeDao, CandidateDao _candidateDao, CustomImageService _imageService, ImageDao _imageDao, CoverLetterDao _coverLetterDao, ForeignLanguageDao _foreignLanguageDao, SkillDao _skillDao, EducationDao _educationDao, JobExperienceDao _jobExperienceDao) {
        this._curriculumVitaeDao = _curriculumVitaeDao;
        this._candidateDao = _candidateDao;
        this._imageService = _imageService;
        this._imageDao = _imageDao;
        this._coverLetterDao = _coverLetterDao;
        this._foreignLanguageDao = _foreignLanguageDao;
        this._skillDao = _skillDao;
        this._educationDao = _educationDao;
        this._jobExperienceDao = _jobExperienceDao;
    }

    @Override
    public Result add(CurriculumVitaeDto curriculumVitaeDto) {
        CurriculumVitae tempCurriculumVitae = new CurriculumVitae();
        tempCurriculumVitae.setCandidate(_candidateDao.getById(curriculumVitaeDto.getCandidateId()));
        tempCurriculumVitae.setCreatedDate(LocalDate.now());
        tempCurriculumVitae.setLinkedin(curriculumVitaeDto.getLinkedIn());
        tempCurriculumVitae.setGithub(curriculumVitaeDto.getGithubLink());

        Image photo = new Image();
        photo.setImageUrl(curriculumVitaeDto.getPhoto());
        if (!photo.getImageUrl().isEmpty() && !photo.getImageUrl().isBlank()){
            _imageDao.save(photo);
        }
        else{
            photo.setImageUrl("NoImage.jpg");
            _imageDao.save(photo);
        }
        _curriculumVitaeDao.save(tempCurriculumVitae);
       return new SuccessResult("Cv Eklendi.");
    }

    @Override
    public Result update(CurriculumVitaeDto curriculumVitaeDto,Long CvId) {
        if (!_curriculumVitaeDao.existsById(CvId)){
            return new ErrorResult("Cv bulunamad??.");
        }else if (!_candidateDao.existsById(curriculumVitaeDto.getCandidateId())){
            return new ErrorResult("Kullan??c?? bulunamad??.");
        }
        return null;
    }

    @Override
    public Result delete(Long id) {
        if (_curriculumVitaeDao.existsById(id)){
            return new ErrorResult("Silinecek veri bulunamad??.");
        }else{
            _curriculumVitaeDao.deleteById(id);
            return new SuccessResult("Veri silindi.");
        }
    }


    @Override
    public DataResult<CurriculumVitae> getCurriculumVitaeByCandidate(Long candidateId) {
        if (!_candidateDao.existsById(candidateId)){
            return new ErrorDataResult<>("Aday bulunamad??.");
        }
        return new SuccessDataResult<CurriculumVitae>(_curriculumVitaeDao.findCurriculumVitaeByCandidate_Id(candidateId),"Kullan??c??ya ait cv getirildi.");
    }

    @Override
    public DataResult<CurriculumVitae> findById(Long id) {

        return new SuccessDataResult<CurriculumVitae>(_curriculumVitaeDao.getById(id),"Veri getirildi.");
    }

    @Override
    public Result updateGithub(String github, Long cvId) {
        if (!_curriculumVitaeDao.existsById(cvId)){
            return new ErrorResult("Cv bulunamad??.");
        }
        if (!github.startsWith("www.gittub.com")||!github.startsWith("github.com")||!github.startsWith("https://github.com")){
            return new ErrorResult("Github linki ge??erli de??il");
        }
        CurriculumVitae tempCurriculumVitae = _curriculumVitaeDao.getById(cvId);
        tempCurriculumVitae.setGithub(github);
        _curriculumVitaeDao.save(tempCurriculumVitae);
        return new SuccessResult(BusinessMessage.updateOperationSuccess);
    }

    @Override
    public Result deleteGithub(Long cvId) {

        if (!_curriculumVitaeDao.existsById(cvId)){
            return new ErrorResult("Cv bulunamad??.");
        }
        CurriculumVitae curriculumVitae = _curriculumVitaeDao.getById(cvId);
        curriculumVitae.setGithub(null);
        _curriculumVitaeDao.save(curriculumVitae);

        return new SuccessResult(BusinessMessage.deleteOperationSuccess);
    }

    @Override
    public Result updateLinkedin(String linkedin, Long cvId) {
        if(!this._curriculumVitaeDao.existsById(cvId)){
            return new ErrorResult("B??yle bir cv yok");
        }else if(!linkedin.startsWith("https://www.linkedin.com") &&
                !linkedin.startsWith("www.linkedin.com") &&
                !linkedin.startsWith("https://linkedin.com") &&
                !linkedin.startsWith("linkedin.com")){
            return new ErrorResult("Ge??erli bir linked in adresi de??il");
        }
        CurriculumVitae cv=this._curriculumVitaeDao.getById(cvId);
        cv.setLinkedin(linkedin);
        this._curriculumVitaeDao.save(cv);
        return new SuccessResult("Kaydedildi");
    }

    @Override
    public Result deleteLinkedin(Long cvId) {
        if(!this._curriculumVitaeDao.existsById(cvId)){
            return new ErrorResult("B??yle bir cv yok");
        }
        CurriculumVitae cv=this._curriculumVitaeDao.getById(cvId);
        cv.setLinkedin(null);
        this._curriculumVitaeDao.save(cv);
        return new SuccessResult("Linkedin adresi silindi");
    }

    @Override
    public Result updateAboutMe(String biography, Long cvId) {
        if (!_curriculumVitaeDao.existsById(cvId)){
            return new ErrorResult("Cv bulunamad??.");
        }
        CurriculumVitae curriculumVitae = _curriculumVitaeDao.getById(cvId);
        curriculumVitae.setAboutMe(biography);
        _curriculumVitaeDao.save(curriculumVitae);

        return new SuccessResult("About Me k??sm?? g??ncellendi.");
    }

    @Override
    public Result deleteAboutMe(Long cvId) {
        if (_curriculumVitaeDao.existsById(cvId)){
            return new ErrorResult("Cv bulunamad??.");
        }
        CurriculumVitae curriculumVitae = _curriculumVitaeDao.getById(cvId);
        curriculumVitae.setAboutMe("");
        _curriculumVitaeDao.save(curriculumVitae);

        return new SuccessResult("About Me Silindi.");
    }


}
