package pl.devfinder.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = "candidateSkillId")
@ToString(of = {"candidateSkillId", "candidateId", "skillId"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "candidate_skill")
public class CandidateSkillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candiate_skill_id")
    private Long candidateSkillId;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private CandidateEntity candidateId;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private SkillEntity skillId;

}
