package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.WorkPlaceService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.*;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.WorkPlaceDao;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.WorkPlace;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.WorkPlaceDto;
import org.hibernate.jdbc.Work;
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
    public Result add(WorkPlaceDto workPlaceDto) {
        WorkPlace workPlace = new WorkPlace();
        workPlace.setWorkPlace(workPlaceDto.getWorkPlace());
        _workPlaceDao.save(workPlace);
        return new SuccessResult("Veri eklendi");
    }

    @Override
    public Result update(WorkPlaceDto workPlaceDto,int workPlaceId) {

        if (!_workPlaceDao.existsById(workPlaceId)){
            return new ErrorResult("Çalışma yeri bulunamadı.");

        }else {
           WorkPlace workPlace = _workPlaceDao.getById(workPlaceId);
            workPlace.setWorkPlace(workPlace.getWorkPlace());
            _workPlaceDao.save(workPlace);
            return new SuccessResult("Veri güncellendi.");
        }
    }

    @Override
    public Result delete(int id) {
        if (!_workPlaceDao.existsById(id)) {
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
