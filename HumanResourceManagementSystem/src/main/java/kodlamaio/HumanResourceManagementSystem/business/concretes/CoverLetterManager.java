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
    public CoverLetterManager(CoverLetterDao _coverLetterDao, CurriculumVitaeDao _cvDao) {
        this._coverLetterDao = _coverLetterDao;
        this._cvDao = _cvDao;
    }

    @Override
    public Result add(CoverLetterDto coverLetterDto,Long cvId) {
        try {
            if (!_cvDao.existsById(cvId)) {
                return new ErrorResult("Cv bulunamadı.");
            } else if (coverLetterDto.getCoverLetter().length() <= 2) {
                return new ErrorResult("Ön yazı boş olmamalıdır.");
            }

            CoverLetter coverLetter = new CoverLetter();
            coverLetter.setCurriculumVitae(_cvDao.getById(cvId));
            coverLetter.setCoverLetter(coverLetterDto.getCoverLetter());
            _coverLetterDao.save(coverLetter);
            return new SuccessResult("Ön yazı eklendi.");


        } catch (Exception ex) {
            return new ErrorResult("Veri ekleme Hatası" + ex);
        }
    }


    @Override
    public Result delete(Long cvId,Long coverLetterId) {

        if (!_cvDao.existsById(cvId)) {
            return new ErrorResult("Veri bulunamadı.");
        }else if (!_coverLetterDao.existsById(coverLetterId)){
            return new ErrorResult("Ön yazı bulunamadı.");
        }
        else {
            _coverLetterDao.deleteById(coverLetterId);
            return new SuccessResult("Veri silindi.");
        }
    }

    @Override
    public Result update(CoverLetterDto coverLetterDto, Long cvId,Long coverLetterId) {
        try{
            if (!_cvDao.existsById(cvId)){
                return new ErrorResult("Cv bulunamadı.");
            }else if (!_coverLetterDao.existsById(coverLetterId)){
                return new ErrorResult("Ön yazı bulunamadı.");
            }
            CoverLetter coverLetter = _coverLetterDao.getById(coverLetterId);
            coverLetter.setCoverLetter(coverLetterDto.getCoverLetter());
            _coverLetterDao.save(coverLetter);

            return new SuccessResult("Ön yazı güncellendi.");
        }catch (Exception ex){
            return new ErrorResult("Veri güncelleme hatası "+ex);
        }

    }

    @Override
    public DataResult<List<CoverLetter>> getAll(Long cvId) {
        try {
            return new SuccessDataResult<List<CoverLetter>>(_coverLetterDao.getCoverLettersByCurriculumVitae_Id(cvId), "Veriler getirildi.");
        } catch (Exception ex) {
            return new ErrorDataResult<>("Veriler getirilemedi. " + ex);
        }
    }

    @Override
    public DataResult<CoverLetter> getOne(Long id) {
        try {

            return new SuccessDataResult<CoverLetter>(_coverLetterDao.getById(id), "Veri getirildi.");
        } catch (Exception ex) {
            return new ErrorDataResult<>("Veri getirilemedi. " + ex);
        }
    }

}
