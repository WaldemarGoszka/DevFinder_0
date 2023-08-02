package pl.devfinder.infrastructure.database.repository.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.devfinder.domain.City;
import pl.devfinder.infrastructure.database.entity.CityEntity;

import java.util.Optional;

@Repository
public interface CityJpaRepository extends JpaRepository<CityEntity, Long> {

    Optional<CityEntity> findByCityName(String cityName);

    void deleteByCityName(String cityName);
}
