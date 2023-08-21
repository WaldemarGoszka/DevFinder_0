package pl.devfinder.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.devfinder.business.dao.CityDAO;
import pl.devfinder.domain.City;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CityService {
    private final CityDAO cityDAO;


    public List<City> findAll() {
        return cityDAO.findAll();
    }

    public Optional<City> findByCityName(String cityName) {
        return cityDAO.findByCityName(cityName);
    }

    public City save(String cityName) {
        City city = City.builder()
                .cityName(cityName)
                .build();
        return cityDAO.saveAnfFlush(city);
    }

    public void deleteByCityName(String cityName) {
        cityDAO.deleteByCityName(cityName);
    }

    public Boolean nonCityExist(String cityName) {
        Optional<City> city = findByCityName(cityName);
        return city.isEmpty();
    }


}
