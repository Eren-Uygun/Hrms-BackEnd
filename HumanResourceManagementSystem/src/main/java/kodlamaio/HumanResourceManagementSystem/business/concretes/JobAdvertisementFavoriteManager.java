package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.JobAdvertisementFavoriteService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.*;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.CandidateDao;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.JobAdvertisementDao;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.JobAdvertisementFavoriteDao;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobAdvertisementFavorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobAdvertisementFavoriteManager implements JobAdvertisementFavoriteService {

    private JobAdvertisementFavoriteDao _jobAdvertisementFavoriteDao;
    private CandidateDao _candidateDao;
    private JobAdvertisementDao _jobAdvertisementDao;

    @Autowired
    public JobAdvertisementFavoriteManager(JobAdvertisementFavoriteDao _jobAdvertisementFavoriteDao, CandidateDao _candidateDao, JobAdvertisementDao _jobAdvertisementDao) {
        this._jobAdvertisementFavoriteDao = _jobAdvertisementFavoriteDao;
        this._candidateDao = _candidateDao;
        this._jobAdvertisementDao = _jobAdvertisementDao;
    }

    @Override
    public DataResult<List<JobAdvertisementFavorite>> getByCandidateId(Long candidateId) {
        if (!_candidateDao.existsById(candidateId)){
            return new ErrorDataResult<>("Kullanıcı bulunamadı.");
        }
        return new SuccessDataResult<List<JobAdvertisementFavorite>>(_jobAdvertisementFavoriteDao.findByCandidate_Id(candidateId),"Kullanıcıya ait favori ilanlar getirildi.");
    }

    @Override
    public Result addFavorite(Long candidateId, Long jobAdId) {
        if (!_candidateDao.existsById(candidateId)) {
            return new ErrorResult("Kullanıcı bulunamadı.");
        } else if (!_jobAdvertisementDao.existsById(jobAdId)) {
            return new ErrorResult("İlan bulunamadı.");
        }else if (_jobAdvertisementFavoriteDao.existsByCandidate_IdAndJobAdvertisement_Id(candidateId,jobAdId)){
            return new ErrorResult("İlan favorilerde");
        }
        JobAdvertisementFavorite jobAdvertisementFavorite = new JobAdvertisementFavorite();
        jobAdvertisementFavorite.setCandidate(_candidateDao.getById(candidateId));
        jobAdvertisementFavorite.setJobAdvertisement(_jobAdvertisementDao.getById(jobAdId));
        _jobAdvertisementFavoriteDao.save(jobAdvertisementFavorite);
        return new SuccessResult("İlan favorilere eklendi.");
    }



    @Override
    public Result removeFavorite(Long favoriteId) {
        if (!_jobAdvertisementFavoriteDao.existsById(favoriteId)){
            return new ErrorResult("İlan bulunamadı.");
        }else{
            _jobAdvertisementFavoriteDao.deleteById(favoriteId);
            return new ErrorResult("İlan favorilerden kaldırıldı.");
        }
    }
}
