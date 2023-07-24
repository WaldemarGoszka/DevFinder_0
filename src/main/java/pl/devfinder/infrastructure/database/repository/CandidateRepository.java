package pl.devfinder.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.devfinder.business.dao.CandidateDAO;
import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.Candidate;
import pl.devfinder.infrastructure.database.entity.CandidateEntity;
import pl.devfinder.infrastructure.database.repository.jpa.CandidateJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.CandidateEntityMapper;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CandidateRepository implements CandidateDAO {
    private final CandidateJpaRepository candidateJpaRepository;
    private final CandidateEntityMapper candidateEntityMapper;

    @Override
    public List<Candidate> findAllByState(Keys.CandidateState state) {
        return candidateJpaRepository.findAllByState(state.getName()).stream()
                .map(candidateEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public void save(Candidate candidate) {
        CandidateEntity candidateEntity = candidateEntityMapper.mapToEntity(candidate);
        candidateJpaRepository.save(candidateEntity);
    }

    @Override
    public Optional<Candidate> findById(Long candidateId) {
        return candidateJpaRepository.findById(candidateId).map(candidateEntityMapper::mapFromEntity);
    }

}
