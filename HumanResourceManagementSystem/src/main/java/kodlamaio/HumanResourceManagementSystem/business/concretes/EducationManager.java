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
    public Result add(EducationDto educationDto) {
        try {
            if (!_curriculumVitaeDao.existsById(educationDto.getCvId())) {
                return new ErrorResult("Cv bulunamadı.");
            } else if (educationDto.getSchoolName().length() <= 2) {
                return new ErrorResult("Okul adı 2 karakterden fazla olmalıdır.");
            } else if (educationDto.getDepartmentName().length() <= 2) {
                return new ErrorResult("Bölüm adı 2 karakterden fazla olmalıdır.");
            } else if (educationDto.getStartingDate() == null) {
                return new ErrorResult("Okul başlangıç tarihi boş bırakılamaz.");
            }

            Education edu = new Education();
            edu.setCurriculumVitae(_curriculumVitaeDao.getById(educationDto.getCvId()));
            edu.setSchoolName(educationDto.getSchoolName());
            edu.setDepartmentName(educationDto.getDepartmentName());
            edu.setStartingDate(educationDto.getStartingDate());
            edu.setGraduateDate(educationDto.getGraduateDate());
            if (edu.getGraduateDate() == null) {
                edu.setGradudated(false);
            }
            _educationDao.save(edu);
            return new SuccessResult("Okul bilgisi eklendi.");

        } catch (Exception ex) {
            return new ErrorResult("Veri ekleme hatası");
        }
    }


    @Override
    public Result delete(int id) {
        try {
            if (!_educationDao.existsById(id)) {
                return new ErrorResult("Silenecek veri bulunamadı.");
            } else {
                _educationDao.deleteById(id);
                return new SuccessResult("Okul bilgisi silindi");
            }

        } catch (Exception ex) {
            return new ErrorResult("Veri silme hatası" + ex);
        }
    }

    @Override
    public DataResult<List<Education>> getAll() {
        try {
            return new SuccessDataResult<List<Education>>(_educationDao.findAll(), "Veriler getirildi.");
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
