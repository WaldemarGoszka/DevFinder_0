package pl.devfinder.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "offerSkillId")
@ToString(of = {"offerSkillId", "offerId", "skillId"})
public class OfferSkills {
    Long offerSkillId;
    Offer offerId;
    Skill skillId;
}
