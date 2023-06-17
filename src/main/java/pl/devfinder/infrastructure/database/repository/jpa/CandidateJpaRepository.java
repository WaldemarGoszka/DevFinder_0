package pl.devfinder.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.Candidate;
import pl.devfinder.infrastructure.database.entity.CandidateEntity;

import java.util.List;

@Repository
public interface CandidateJpaRepository extends JpaRepository<CandidateEntity, Integer> {
@Query("""
        SELECT candidate FROM CandidateEntity candidate WHERE candidate.status = :state
        """
)
    List<CandidateEntity> findAllByState(@Param("state")String state);
}
