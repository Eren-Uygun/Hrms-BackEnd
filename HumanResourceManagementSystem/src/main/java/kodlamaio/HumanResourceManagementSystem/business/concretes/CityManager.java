package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.CityService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.*;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.CityDao;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class CityManager implements CityService {

    private CityDao _cityDao;

    @Autowired
    public CityManager(CityDao _cityDao) {
        this._cityDao = _cityDao;
    }

    @Override
    public Result add(City city) {
        try{
            var tempCity = _cityDao.existsCityByName(city.getName());
            if (tempCity)
            {
                return new ErrorResult("Sistemde kayıtlı bilgi girdiniz.");
            }
            else{
                _cityDao.save(city);
                return new SuccessResult("Ekleme işlemi yapıldı");
            }

        }catch (Exception ex) {
            return new ErrorResult("Kayıt işleminde hata mevcut");
        }
    }

    @Override
    public Result update(City city) {
        try{
            var tempCity = _cityDao.getById(city.getId());
            if (tempCity.getName()==null){
                return new ErrorResult("Veri bulunamadı.");
            }else{
                tempCity.setName(city.getName());
                _cityDao.save(tempCity);
                return new SuccessResult("Veri güncellendi.");
            }
        }catch (Exception ex) {
            return new ErrorResult("Güncelleme işleminde hata mevcut");
        }
    }

    @Override
    public Result delete(int id) {
        try{
            var tempCity = _cityDao.getById(id);
            if (tempCity.getName() == null){
                return new ErrorResult("Veri bulunamadı.");
            }else{
                _cityDao.deleteById(id);
                return new ErrorResult("Veri silindi.");

            }
        }catch (Exception ex) {
            return new ErrorResult("Veri silme hatası");
        }
    }

    @Override
    public DataResult<List<City>> getAll() {
        try{
            return new SuccessDataResult<List<City>>(_cityDao.findAll(),"Veriler getirildi.");
        }catch (Exception ex) {
            return new ErrorDataResult<List<City>>("Veri getirme hatası");
        }
    }

    @Override
    public DataResult<City> getOne(int id) {
        try{
            return new SuccessDataResult<City>(_cityDao.getById(id),"Veri getirildi.");
        }catch (Exception ex) {
            return new  ErrorDataResult<>("Veri getirme hatası");
        }
    }
}
