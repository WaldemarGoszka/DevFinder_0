package pl.devfinder.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pl.devfinder.business.dao.EmployerDAO;
import pl.devfinder.domain.Employer;
import pl.devfinder.infrastructure.database.entity.EmployerEntity;
import pl.devfinder.infrastructure.database.repository.jpa.EmployerJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.EmployerEntityMapper;

import java.util.List;
import java.util.Optional;
@Slf4j
@Repository
@AllArgsConstructor
public class EmployerRepository implements EmployerDAO {
    private final EmployerJpaRepository employerJpaRepository;
    private final EmployerEntityMapper employerEntityMapper;

    @Override
    public List<Employer> findAll() {
        return employerJpaRepository.findAllByOrderByCompanyName().stream()
                .map(employerEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public void save(Employer employer) {
        log.info("Process save employer : [{}]",employer);
        EmployerEntity employerEntity = employerEntityMapper.mapToEntity(employer);
        employerJpaRepository.save(employerEntity);
    }

    @Override
    public Optional<Employer> findById(Long employerId) {
        return employerJpaRepository.findById(employerId).map(employerEntityMapper::mapFromEntity);
    }

    @Override
    public Optional<Employer> findByEmployerUuid(String uuid) {
        return employerJpaRepository.findByEmployerUuid(uuid).map(employerEntityMapper::mapFromEntity);
    }

    @Override
    public long countByCityName(String cityName) {
        return employerJpaRepository.countByCityName(cityName);
    }

    @Override
    public void deleteById(Long employerId) {
        employerJpaRepository.deleteById(employerId);
    }

    @Override
    public void deleteEmployerFromAllCandidatesAndChangeStatus(Employer employer) {
        employerJpaRepository.deleteEmployerFromAllCandidatesAndChangeStatus(employerEntityMapper.mapToEntity(employer));
    }

    @Override
    public void assignEmployerToCandidateAndChangeStatus(Employer employer, Long candidateId) {
        employerJpaRepository.assignEmployerToCandidateAndChangeStatus(employerEntityMapper.mapToEntity(employer),candidateId);
    }

    @Override
    public void deleteAssignEmployerToCandidateAndChangeStatus(Long candidateId) {
        employerJpaRepository.deleteEmployerFromCandidateAndChangeStatus(candidateId);
    }

    @Override
    public Optional<Employer> findByCompanyName(String employerCompanyName) {
        return employerJpaRepository.findByCompanyName(employerCompanyName).map(employerEntityMapper::mapFromEntity);
    }


}
