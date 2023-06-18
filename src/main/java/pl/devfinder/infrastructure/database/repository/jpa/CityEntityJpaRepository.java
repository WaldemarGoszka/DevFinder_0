package pl.devfinder.infrastructure.database.repository.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.devfinder.infrastructure.database.entity.CityEntity;

@Repository
public interface CityEntityJpaRepository extends JpaRepository<CityEntity, Long> {

}
