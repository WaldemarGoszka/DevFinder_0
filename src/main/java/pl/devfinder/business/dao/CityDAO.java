package pl.devfinder.business.dao;


import pl.devfinder.domain.City;

import java.util.List;
import java.util.Optional;

public interface CityDAO {

    List<City> findAll();

    Optional<City> findByCityName(String cityName);

    City saveAnfFlush(City city);
}
