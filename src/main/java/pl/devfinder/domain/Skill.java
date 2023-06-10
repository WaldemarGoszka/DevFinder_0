package pl.devfinder.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "skillId")
@ToString(of = {"skillId", "skillName"})
public class Skill {
    Long skillId;
    String skillName;
    Set<CandidateSkill> candidateSkills;
    Set<OfferSkill> offerSkills;

}
