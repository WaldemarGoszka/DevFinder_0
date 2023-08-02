package pl.devfinder.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.devfinder.infrastructure.database.entity.EmployerEntity;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface EmployerJpaRepository extends JpaRepository<EmployerEntity, Long> {


    Optional<EmployerEntity> findByEmployerUuid(String uuid);
    @Query("""
    SELECT COUNT(o) FROM EmployerEntity o JOIN o.cityId c WHERE c.cityName = :cityName
    """)
    long countByCityName(String cityName);
}
