package pl.devfinder.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.devfinder.business.dao.CandidateDAO;
import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.Candidate;

import java.util.List;
@Slf4j
@Service
@AllArgsConstructor
public class CandidateService {
    CandidateDAO candidateDAO;

    public List<Candidate> findAllByState(Keys.CandidateState state) {
        List<Candidate> allAvailableCandidates = candidateDAO.findAllByState(state);
        log.info("Available Candidate count: [{}]", allAvailableCandidates.size());
        return allAvailableCandidates;
    }
}
