package pl.devfinder.business.dao;


import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.Candidate;

import java.util.List;
import java.util.Optional;

public interface CandidateDAO {


    Candidate save(Candidate candidate);

    Optional<Candidate> findById(Long candidateId);

    Optional<Candidate> findByCandidateUuid(String uuid);

    void deleteById(Long candidateId);
    long countByCityName(String cityName);

}
