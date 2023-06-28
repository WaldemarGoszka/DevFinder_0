package pl.devfinder.business.dao;


import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.Candidate;

import java.util.List;

public interface CandidateDAO {

    List<Candidate> findAllByState(Keys.CandidateState state);

    void save(Candidate candidate);
}
