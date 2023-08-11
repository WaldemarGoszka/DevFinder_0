package pl.devfinder.business.dao;


import pl.devfinder.domain.Employer;

import java.util.List;
import java.util.Optional;

public interface EmployerDAO {
    List<Employer> findAll();

    void save(Employer employer);

    Optional<Employer> findById(Long employerId);

    Optional<Employer> findByEmployerUuid(String uuid);

    long countByCityName(String cityName);

    void deleteById(Long aLong);

    void deleteEmployerFromAllCandidatesAndChangeStatus(Employer employer);

    void assignEmployerToCandidateAndChangeStatus(Employer employerId, Long candidateId);

    void deleteAssignEmployerToCandidateAndChangeStatus(Long candidateId);

    Optional<Employer> findByCompanyName(String employerCompanyName);
}
