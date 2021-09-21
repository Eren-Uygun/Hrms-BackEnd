package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.CityService;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.*;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.CityDao;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.City;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.CityDto;
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
    public Result add(CityDto cityDto) {
        try{
            if (_cityDao.existsCityByCityName(cityDto.getCityName()))
            {
                return new ErrorResult("Sistemde kayıtlı bilgi girdiniz.");
            }
            else{
                if (cityDto.getCityName().length()<2){
                    return new ErrorResult("Şehir adı 2 karaterden fazla olmalıdır.");
                }
                City city = new City();
                city.setCityName(cityDto.getCityName());
                _cityDao.save(city);
                return new SuccessResult("Şehir eklendi. işlemi yapıldı");
            }

        }catch (Exception ex) {
            return new ErrorResult("Kayıt işleminde hata mevcut");
        }
    }

    @Override
    public Result update(CityDto cityDto,int id) {
        try{
            var tempCity = _cityDao.getById(id);
            if (!_cityDao.existsById(tempCity.getId())){
                return new ErrorResult("Veri bulunamadı.");
            }else{
                if (_cityDao.existsCityByCityName(cityDto.getCityName().toLowerCase(Locale.ROOT))){
                    return new ErrorResult("Bu şehir zaten mevcut");
                }
                tempCity.setCityName(cityDto.getCityName());
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
            if (tempCity.getCityName() == null){
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
