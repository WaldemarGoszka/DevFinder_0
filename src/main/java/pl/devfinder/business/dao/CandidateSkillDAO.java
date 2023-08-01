package pl.devfinder.business.dao;


import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.CandidateSkill;

import java.util.Set;

public interface CandidateSkillDAO {

    void saveAll(Set<CandidateSkill> candidateSkills);

    void deleteAllByCandidate(Candidate candidate);
}
