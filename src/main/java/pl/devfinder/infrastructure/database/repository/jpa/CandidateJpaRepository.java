package pl.devfinder.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.devfinder.infrastructure.database.entity.CandidateEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface CandidateJpaRepository extends JpaRepository<CandidateEntity, Long> {
@Query("""
        SELECT candidate FROM CandidateEntity candidate WHERE candidate.status LIKE :state
        """
)
    List<CandidateEntity> findAllByState(String state);

    Optional<CandidateEntity> findByCandidateUuid(String uuid);
    @Query("""
    SELECT COUNT(o) FROM CandidateEntity o JOIN o.residenceCityId c WHERE c.cityName = :cityName
    """)
    long countByCityName(String cityName);

}
