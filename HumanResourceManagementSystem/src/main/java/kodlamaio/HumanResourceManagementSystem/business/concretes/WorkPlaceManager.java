package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.WorkPlaceService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.*;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.WorkPlaceDao;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.WorkPlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkPlaceManager implements WorkPlaceService {

    private WorkPlaceDao _workPlaceDao;

    @Autowired
    public WorkPlaceManager(WorkPlaceDao _workPlaceDao) {
        this._workPlaceDao = _workPlaceDao;
    }

    @Override
    public Result add(WorkPlace workPlace) {
        return new SuccessResult("Veri eklendi");
    }

    @Override
    public Result update(WorkPlace workPlace) {
        var temp = _workPlaceDao.getById(workPlace.getId());
        if (temp.getId() != workPlace.getId()){
            return new ErrorResult("Çalışma yeri bulunamadı.");

        }else {
            temp.setWorkPlace(workPlace.getWorkPlace());
            temp.setJobAdvertisements(workPlace.getJobAdvertisements());
            _workPlaceDao.save(temp);
            return new SuccessResult("Veri güncellendi.");
        }
    }

    @Override
    public Result delete(int id) {
        var temp = _workPlaceDao.getById(id);
        if (temp.getId() != id) {
            return new ErrorResult("Veri bulunamadı.");
        } else {
            return new SuccessResult("Veri silindi.");
        }
    }

    @Override
    public DataResult<List<WorkPlace>> getAll() {
       try{
           return new SuccessDataResult<List<WorkPlace>>(_workPlaceDao.findAll(),"Veriler getirildi.");
       }catch (Exception ex){
           return new ErrorDataResult<>("Veri getirme hatası"+ ex);
       }
    }

    @Override
    public DataResult<WorkPlace> getOne(int id) {
        try{
            return new SuccessDataResult<WorkPlace>(_workPlaceDao.getById(id),"Veri getirildi.");
        }catch (Exception ex){
            return new ErrorDataResult<>("Veri getirme hatası"+ ex);
        }
    }
}
