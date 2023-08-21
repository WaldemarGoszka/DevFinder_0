package pl.devfinder.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.devfinder.infrastructure.database.entity.CandidateEntity;

import java.util.Optional;

@Repository
public interface CandidateJpaRepository extends JpaRepository<CandidateEntity, Long> {


    Optional<CandidateEntity> findByCandidateUuid(String uuid);

    @Query("""
            SELECT COUNT(o) FROM CandidateEntity o JOIN o.residenceCityId c WHERE c.cityName = :cityName
            """)
    long countByCityName(String cityName);

}
