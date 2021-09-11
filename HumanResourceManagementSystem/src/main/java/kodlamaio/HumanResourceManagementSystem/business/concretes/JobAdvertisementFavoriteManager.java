package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.JobAdvertisementFavoriteService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.JobAdvertisementFavoriteDao;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobAdvertisementFavorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobAdvertisementFavoriteManager implements JobAdvertisementFavoriteService {

    private JobAdvertisementFavoriteDao _jobAdvertisementFavoriteDao;

    @Autowired
    public JobAdvertisementFavoriteManager(JobAdvertisementFavoriteDao jobAdvertisementFavoriteDao) {
        this._jobAdvertisementFavoriteDao = jobAdvertisementFavoriteDao;
    }

    @Override
    public DataResult<List<JobAdvertisementFavorite>> getByCandidateId(int candidateId) {
        return null;
    }

    @Override
    public Result addFavorite(int candidateId, int jobAdId) {
        return null;
    }

    @Override
    public Result removeFavorite(int favoriteId) {
        return null;
    }
}
