package pl.devfinder.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.devfinder.infrastructure.database.entity.CandidateEntity;

import java.util.List;

@Repository
public interface CandidateJpaRepository extends JpaRepository<CandidateEntity, Long> {
@Query("""
        SELECT candidate FROM CandidateEntity candidate WHERE candidate.status LIKE :state
        """
)
    List<CandidateEntity> findAllByState(String state);
}
