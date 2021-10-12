package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobAdvertisementFavorite;

import java.util.List;

public interface JobAdvertisementFavoriteService {

    public DataResult<List<JobAdvertisementFavorite>> getByCandidateId(Long candidateId);
    public Result addFavorite(Long candidateId, Long jobAdId);
    public Result removeFavorite(Long favoriteId);

}
