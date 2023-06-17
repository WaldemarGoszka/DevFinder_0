package pl.devfinder.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.devfinder.business.dao.CandidateDAO;
import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.Employer;
import pl.devfinder.infrastructure.database.repository.jpa.CandidateJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.CandidateEntityMapper;

import java.util.List;

@Repository
@AllArgsConstructor
public class CandidateRepository implements CandidateDAO {
    private final CandidateJpaRepository candidateJpaRepository;
    private final CandidateEntityMapper candidateEntityMapper;

    @Override
    public List<Candidate> findAllByState(Keys.CandidateState state) {
        return candidateJpaRepository.findAllByState(state.getState()).stream()
                .map(candidateEntityMapper::mapFromEntity)
                .toList();
    }

}
