package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.CovertLetterService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.*;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.CoverLetterDao;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.CurriculumVitaeDao;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Candidate;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.CoverLetter;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.CoverLetterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoverLetterManager implements CovertLetterService {

    private CoverLetterDao _coverLetterDao;
    private CurriculumVitaeDao _cvDao;

    @Autowired
    public CoverLetterManager(CoverLetterDao _coverLetterDao) {
        this._coverLetterDao = _coverLetterDao;
    }

    @Override
    public Result add(CoverLetterDto coverLetterDto) {
        try {
            if (!_coverLetterDao.existsById(coverLetterDto.getCvId())) {
                return new ErrorResult("Cv bulunamadı.");
            } else if (coverLetterDto.getCoverLetter().length() <= 2) {
                return new ErrorResult("Ön yazı boş olmamalıdır.");
            }

            CoverLetter coverLetter = new CoverLetter();
            coverLetter.setCurriculumVitae(_cvDao.getById(coverLetterDto.getCvId()));
            coverLetter.setCoverLetter(coverLetterDto.getCoverLetter());
            _coverLetterDao.save(coverLetter);
            return new SuccessResult("Ön yazı eklendi.");


        } catch (Exception ex) {
            return new ErrorResult("Veri ekleme Hatası" + ex);
        }
    }


    @Override
    public Result delete(int id) {

        if (!_coverLetterDao.existsById(id)) {
            return new ErrorResult("Veri bulunamadı.");
        } else {
            _coverLetterDao.deleteById(id);
            return new SuccessResult("Veri silindi.");
        }
    }

    @Override
    public DataResult<List<CoverLetter>> getAll() {
        try {
            return new SuccessDataResult<List<CoverLetter>>(_coverLetterDao.findAll(), "Veriler getirildi.");
        } catch (Exception ex) {
            return new ErrorDataResult<>("Veriler getirilemedi. " + ex);
        }
    }

    @Override
    public DataResult<CoverLetter> getOne(int id) {
        try {

            return new SuccessDataResult<CoverLetter>(_coverLetterDao.getById(id), "Veri getirildi.");
        } catch (Exception ex) {
            return new ErrorDataResult<>("Veri getirilemedi. " + ex);
        }
    }

}
