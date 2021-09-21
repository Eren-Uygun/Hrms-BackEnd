package kodlamaio.HumanResourceManagementSystem.api.controllers;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.JobAdvertisementFavoriteService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobAdvertisementFavorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favoriteJobAdvertisement")
public class JobAdvertisementFavoritesController {
    private JobAdvertisementFavoriteService _jobAdvertisementFavoriteService;

    @Autowired
    public JobAdvertisementFavoritesController(JobAdvertisementFavoriteService _jobAdvertisementFavoriteService) {
        this._jobAdvertisementFavoriteService = _jobAdvertisementFavoriteService;
    }

    @PostMapping("/addFavorite")
    public ResponseEntity<?> addFavorite(int candidateId, int jobAdvertisementId){
        Result result = _jobAdvertisementFavoriteService.addFavorite(candidateId,jobAdvertisementId);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/removeFavorite")
    public ResponseEntity<?> removeFavorite(int favoriteId){
        Result result = _jobAdvertisementFavoriteService.removeFavorite(favoriteId);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/getFavorites")
    public DataResult<List<JobAdvertisementFavorite>> getFavorites(int candidateId){
        return _jobAdvertisementFavoriteService.getByCandidateId(candidateId);

    }

}
