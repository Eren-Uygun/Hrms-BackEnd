package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.CurriculumVitaeService;
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
    public Result update(CurriculumVitaeDto curriculumVitaeDto,int CvId) {
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
    public DataResult<CurriculumVitae> getCurriculumVitaeByCandidate(int candidateId) {
        return new SuccessDataResult<CurriculumVitae>(_curriculumVitaeDao.findCurriculumVitaeByCandidate_Id(candidateId),"Kullanıcıya ait cv getirildi.");
    }

    @Override
    public DataResult<CurriculumVitae> findById(int id) {

        return new SuccessDataResult<CurriculumVitae>(_curriculumVitaeDao.getById(id),"Veri getirildi.");
    }

    @Override
    public Result updateGithub(String github, int cvId) {
        return null;
    }

    @Override
    public Result deleteGithub(int cvId) {

        if (!_curriculumVitaeDao.existsById(cvId)){
            return new ErrorResult("Cv bulunamadı.");
        }
        CurriculumVitae curriculumVitae = _curriculumVitaeDao.getById(cvId);
        curriculumVitae.setGithub(null);
        _curriculumVitaeDao.save(curriculumVitae);

        return new SuccessResult("Github adresi silindi.");
    }

    @Override
    public Result updateLinkedin(String linkedin, int cvId) {
        if(!this._curriculumVitaeDao.existsById(cvId)){
            return new ErrorResult("Böyle bir cv yok");
        }else if(!linkedin.startsWith("https://www.linkedin.com") &&
                !linkedin.startsWith("www.linkedin.com") &&
                !linkedin.startsWith("https://linkedin.com") &&
                !linkedin.startsWith("linkedin.com")){
            return new ErrorResult("Geçerli bir linked in adresi değil");
        }
        CurriculumVitae cv=this._curriculumVitaeDao.getById(cvId);
        cv.setLinkedin(linkedin);
        this._curriculumVitaeDao.save(cv);
        return new SuccessResult("Kaydedildi");
    }

    @Override
    public Result deleteLinkedin(int cvId) {
        if(!this._curriculumVitaeDao.existsById(cvId)){
            return new ErrorResult("Böyle bir cv yok");
        }
        CurriculumVitae cv=this._curriculumVitaeDao.getById(cvId);
        cv.setLinkedin(null);
        this._curriculumVitaeDao.save(cv);
        return new SuccessResult("Linkedin adresi silindi");
    }

    @Override
    public Result updateAboutMe(String biography, int cvId) {
        if (!_curriculumVitaeDao.existsById(cvId)){
            return new ErrorResult("Cv bulunamadı.");
        }
        CurriculumVitae curriculumVitae = _curriculumVitaeDao.getById(cvId);
        curriculumVitae.setAboutMe(biography);
        _curriculumVitaeDao.save(curriculumVitae);

        return new SuccessResult("About Me kısmı güncellendi.");
    }

    @Override
    public Result deleteAboutMe(int cvId) {
        if (_curriculumVitaeDao.existsById(cvId)){
            return new ErrorResult("Cv bulunamadı.");
        }
        CurriculumVitae curriculumVitae = _curriculumVitaeDao.getById(cvId);
        curriculumVitae.setAboutMe("");
        _curriculumVitaeDao.save(curriculumVitae);

        return new SuccessResult("About Me Silindi.");
    }


}
