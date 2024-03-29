package pl.devfinder.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "skillId")
@ToString(of = {"skillId", "skillName"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "skill")
public class SkillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private Long skillId;

    @Column(name = "skill_name", unique = true, nullable = false)
    private String skillName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "skillId")
    private Set<CandidateSkillEntity> candidateSkills;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "skillId")
    private Set<OfferSkillEntity> offerSkills;

}
