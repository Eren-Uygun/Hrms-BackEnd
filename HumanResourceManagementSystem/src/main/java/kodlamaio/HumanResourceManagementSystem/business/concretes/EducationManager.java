package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.EducationService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.*;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.CurriculumVitaeDao;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.EducationDao;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Education;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.CurriculumVitaeDto;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.EducationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationManager implements EducationService {

    private EducationDao _educationDao;
    private CurriculumVitaeDao _curriculumVitaeDao;

    @Autowired
    public EducationManager(EducationDao _educationDao, CurriculumVitaeDao _curriculumVitaeDao) {
        this._educationDao = _educationDao;
        this._curriculumVitaeDao = _curriculumVitaeDao;
    }

    @Override
    public Result add(EducationDto educationDto,int cvId) {
        try {
            if (!_curriculumVitaeDao.existsById(cvId)) {
                return new ErrorResult("Cv bulunamadı.");
            } else if (educationDto.getSchoolName().length() <= 2) {
                return new ErrorResult("Okul adı 2 karakterden fazla olmalıdır.");
            } else if (educationDto.getDepartmentName().length() <= 2) {
                return new ErrorResult("Bölüm adı 2 karakterden fazla olmalıdır.");
            } else if (educationDto.getStartingDate() == null) {
                return new ErrorResult("Okul başlangıç tarihi boş bırakılamaz.");
            }

            Education edu = new Education();
            edu.setCurriculumVitae(_curriculumVitaeDao.getById(cvId));
            edu.setSchoolName(educationDto.getSchoolName());
            edu.setDepartmentName(educationDto.getDepartmentName());
            edu.setStartingDate(educationDto.getStartingDate());
            edu.setGraduateDate(educationDto.getGraduateDate());

            if (edu.getGraduateDate() == null) {
                edu.setGradudated(false);
            }
            else{
                edu.setGradudated(true);
            }
            _educationDao.save(edu);
            return new SuccessResult("Okul bilgisi eklendi.");

        } catch (Exception ex) {
            return new ErrorResult("Veri ekleme hatası");
        }
    }

    @Override
    public Result update(EducationDto educationDto, int cvId,int educationId) {
        if (!_curriculumVitaeDao.existsById(cvId)){
            return new ErrorResult("Cv bulunamadı.");
        }else if (educationDto.getStartingDate() == null){
            return new ErrorResult("Eğitim başlangıç tarihi boş geçilemez.");
        }else if(educationDto.getSchoolName().length()<2){
            return new ErrorResult("Okul adı 2 karakterden fazla olmalıdır.");
        } else if (educationDto.getDepartmentName().length()<2) {
            return new ErrorResult("Bölüm adı 2 karakterden fazla olmalıdır.");
        }

        Education education = _educationDao.getById(educationId);
        education.setSchoolName(educationDto.getSchoolName());
        education.setDepartmentName(educationDto.getDepartmentName());
        education.setStartingDate(educationDto.getStartingDate());
        education.setGraduateDate(educationDto.getGraduateDate());

        if (education.getGraduateDate()==null){
            education.setGradudated(false);
        }
        else
        {
            education.setGradudated(true);
        }
        _educationDao.save(education);
        return new SuccessResult("Eğitim bilgisi güncellendi.");
    }


    @Override
    public Result delete(int id,int cvId) {
        try {
            if (!_curriculumVitaeDao.existsById(cvId)) {
                return new ErrorResult("Cv bulunamadı.");
            }else if (!_curriculumVitaeDao.existsById(id)){
                return new ErrorResult("Eğitim bilgisi bulunamadı.");
            }
            else {
                _educationDao.deleteById(id);
                return new SuccessResult("Okul bilgisi silindi");
            }

        } catch (Exception ex) {
            return new ErrorResult("Veri silme hatası" + ex);
        }
    }

    @Override
    public DataResult<List<Education>> getAll(int cvId) {
        try {
            return new SuccessDataResult<List<Education>>(_educationDao.getEducationsByCurriculumVitae_Id(cvId), "Veriler getirildi.");
        } catch (Exception ex) {
            return new ErrorDataResult<>("Veri getirme hatası");
        }
    }

    @Override
    public DataResult<Education> getOne(int id) {
        try {
            return new SuccessDataResult<Education>(_educationDao.getById(id), "Veri getirildi.");
        } catch (Exception ex) {
            return new ErrorDataResult<>("Veri getirme hatası");
        }
    }
}
