package pl.devfinder.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "skillId")
@ToString(of = {"skillId", "skillName"})
public class Skill {
    Long skillId;
    String skillName;


}
