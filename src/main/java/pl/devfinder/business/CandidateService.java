package pl.devfinder.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.devfinder.business.dao.CandidateDAO;
import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.User;

import java.time.OffsetDateTime;
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

    public void save(User user) {
        Candidate candidate = Candidate.builder()
                .candidateUUId(user.getUserUuid())
                .createdAt(OffsetDateTime.now())
                .status(Keys.CandidateState.ACTIVE.getState())
                .build();
        candidateDAO.save(candidate);
    }
}
