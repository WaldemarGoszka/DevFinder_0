package pl.devfinder.business.dao;


import pl.devfinder.domain.City;

import java.util.List;

public interface CityDAO {

    List<City> findAll();
}
