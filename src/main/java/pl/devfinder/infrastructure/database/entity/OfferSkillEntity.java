package pl.devfinder.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "offerSkillId")
@ToString(of = {"offerSkillId", "offerId", "skillId",})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "offer_skill")
public class OfferSkillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_skill_id")
    private Long offerSkillId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offer_id")
    private OfferEntity offerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id")
    private SkillEntity skillId;
}
