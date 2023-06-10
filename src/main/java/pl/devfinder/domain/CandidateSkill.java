package pl.devfinder.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "candidateSkillId")
@ToString(of = {"candidateSkillId", "candidateId", "skillId"})
public class CandidateSkill {

    Long candidateSkillId;
    Candidate candidateId;
    Skill skillId;

}
