package pl.devfinder.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.devfinder.business.dao.CandidateSkillDAO;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.CandidateSkill;

import java.util.Set;

@Service
@AllArgsConstructor
public class CandidateSkillService {
    private final CandidateSkillDAO candidateSkillDAO;

    public void saveAll(Set<CandidateSkill> candidateSkills) {
        candidateSkillDAO.saveAll(candidateSkills);
    }

    public void deleteAllByCandidate(Candidate candidate) {
        candidateSkillDAO.deleteAllByCandidate(candidate);
    }
}
