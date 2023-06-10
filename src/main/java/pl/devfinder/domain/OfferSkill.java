package pl.devfinder.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "offerSkillId")
@ToString(of = {"offerSkillId", "offerId", "skillId"})
public class OfferSkill {
    Long offerSkillId;
    Offer offerId;
    Skill skillId;
}
