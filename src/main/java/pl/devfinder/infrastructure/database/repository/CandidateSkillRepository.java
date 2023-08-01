package pl.devfinder.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.devfinder.business.dao.CandidateSkillDAO;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.CandidateSkill;
import pl.devfinder.infrastructure.database.entity.CandidateEntity;
import pl.devfinder.infrastructure.database.repository.jpa.CandidateSkillJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.CandidateEntityMapper;
import pl.devfinder.infrastructure.database.repository.mapper.CandidateSkillEntityMapper;

import java.util.List;
import java.util.Set;

@Repository
@AllArgsConstructor
public class CandidateSkillRepository implements CandidateSkillDAO {
    private final CandidateSkillJpaRepository candidateSkillJpaRepository;
    private final CandidateSkillEntityMapper candidateSkillEntityMapper;
    private final CandidateEntityMapper candidateEntityMapper;

    @Override
    public void saveAll(Set<CandidateSkill> candidateSkills) {
        candidateSkillJpaRepository.saveAll(candidateSkills.stream().map(candidateSkillEntityMapper::mapToEntity).toList());
    }

    @Override
    public void deleteAllByCandidate(Candidate candidate) {
        candidateSkillJpaRepository.deleteAllByCandidateId(candidateEntityMapper.mapToEntity(candidate));
    }
}
