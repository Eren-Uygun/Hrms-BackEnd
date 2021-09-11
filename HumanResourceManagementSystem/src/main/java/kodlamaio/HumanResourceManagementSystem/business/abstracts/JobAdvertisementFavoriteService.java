package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobAdvertisementFavorite;

import java.util.List;

public interface JobAdvertisementFavoriteService {

    public DataResult<List<JobAdvertisementFavorite>> getByCandidateId(int candidateId);
    public Result addFavorite(int candidateId, int jobAdId);
    public Result removeFavorite(int favoriteId);

}
