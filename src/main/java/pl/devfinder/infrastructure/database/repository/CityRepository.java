package pl.devfinder.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.devfinder.business.dao.CityDAO;
import pl.devfinder.domain.City;
import pl.devfinder.infrastructure.database.entity.CityEntity;
import pl.devfinder.infrastructure.database.repository.jpa.CityJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.CityEntityMapper;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CityRepository implements CityDAO {
private final CityJpaRepository cityJpaRepository;
private final CityEntityMapper cityEntityMapper;
    @Override
    public List<City> findAll() {
        return cityJpaRepository.findAll().stream().map(cityEntityMapper::mapFromEntity).toList();
    }

    @Override
    public Optional<City> findByCityName(String cityName) {
        return cityJpaRepository.findByCityName(cityName).map(cityEntityMapper::mapFromEntity);
    }

    @Override
    public City saveAnfFlush(City city) {
        return cityEntityMapper.mapFromEntity(cityJpaRepository.saveAndFlush(cityEntityMapper.mapToEntity(city)));
    }

    @Override
    public void deleteByCityName(String cityName) {
        cityJpaRepository.deleteByCityName(cityName);
    }
}
