package pl.devfinder.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pl.devfinder.business.dao.CandidateDAO;
import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.Employer;
import pl.devfinder.domain.User;
import pl.devfinder.domain.exception.NotFoundException;
import pl.devfinder.domain.search.CandidateSearchCriteria;
import pl.devfinder.infrastructure.database.repository.criteria.CandidateCriteriaRepository;

import java.time.OffsetDateTime;
import java.util.List;
@Slf4j
@Service
@AllArgsConstructor
public class CandidateService {
    private final CandidateDAO candidateDAO;
    private final CandidateCriteriaRepository candidateCriteriaRepository;

    public List<Candidate> findAllByState(Keys.CandidateState state) {
        List<Candidate> allAvailableCandidates = candidateDAO.findAllByState(state);
        log.info("Available Candidate ammount: [{}]", allAvailableCandidates.size());
        return allAvailableCandidates;
    }

    public void save(User user) {
        Candidate candidate = Candidate.builder()
                .candidateUUId(user.getUserUuid())
                .createdAt(OffsetDateTime.now())
                .status(Keys.CandidateState.ACTIVE.getName())
                .build();
        candidateDAO.save(candidate);
    }

    public Page<Candidate> findAllByCriteria(CandidateSearchCriteria candidateSearchCriteria) {
        return candidateCriteriaRepository.findAllByCriteria(candidateSearchCriteria);
    }


         public Candidate findById(Long candidateId) {
            log.info("Trying find candidateById, id: [{}]", candidateId);
            return candidateDAO.findById(candidateId).orElseThrow(() -> new NotFoundException(
                    "Could not find candidate by Id [%s]".formatted(candidateId)));
        }
}
