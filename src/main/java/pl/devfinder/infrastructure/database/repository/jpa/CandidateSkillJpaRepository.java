package pl.devfinder.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.devfinder.infrastructure.database.entity.CandidateEntity;
import pl.devfinder.infrastructure.database.entity.CandidateSkillEntity;

@Repository
public interface CandidateSkillJpaRepository extends JpaRepository<CandidateSkillEntity, Long> {

    void deleteAllByCandidateId(CandidateEntity candidateId);
}
