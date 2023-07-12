package pl.devfinder.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.devfinder.business.dao.CityDAO;
import pl.devfinder.domain.City;

import java.util.List;

@Service
@AllArgsConstructor
public class CityService {
    private final CityDAO cityDAO;

    public List<City> findAll() {
        return cityDAO.findAll();
    }

    //TODO jeśli nie będzie takiego miasta to dodaj miasto
//    if(City == null){
//        city = saveCity();
//    }

}
