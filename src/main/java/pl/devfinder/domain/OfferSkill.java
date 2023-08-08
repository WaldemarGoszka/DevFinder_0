package pl.devfinder.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"offerSkillId", "offerId", "skillId"})
@ToString(of = {"offerSkillId", "offerId", "skillId"})
public class OfferSkill {
    Long offerSkillId;
    Offer offerId;
    Skill skillId;
}
