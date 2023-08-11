package pl.devfinder.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pl.devfinder.business.dao.CandidateDAO;
import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.Candidate;
import pl.devfinder.infrastructure.database.entity.CandidateEntity;
import pl.devfinder.infrastructure.database.repository.jpa.CandidateJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.CandidateEntityMapper;

import java.util.List;
import java.util.Optional;
@Slf4j
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
    public Candidate save(Candidate candidate) {
        log.info("Process save candidate : [{}]",candidate);
        return candidateEntityMapper.mapFromEntity(candidateJpaRepository.saveAndFlush(candidateEntityMapper.mapToEntity(candidate)));
    }

    @Override
    public Optional<Candidate> findById(Long candidateId) {
        return candidateJpaRepository.findById(candidateId).map(candidateEntityMapper::mapFromEntity);
    }

    @Override
    public Optional<Candidate> findByCandidateUuid(String uuid) {
        return candidateJpaRepository.findByCandidateUuid(uuid).map(candidateEntityMapper::mapFromEntity);
    }

    @Override
    public void deleteById(Long candidateId) {
        candidateJpaRepository.deleteById(candidateId);
    }

    @Override
    public long countByCityName(String cityName) {
        return candidateJpaRepository.countByCityName(cityName);
    }

}
