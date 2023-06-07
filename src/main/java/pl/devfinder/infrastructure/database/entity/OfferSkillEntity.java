package pl.devfinder.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

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

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private OfferEntity offerId;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private SkillEntity skillId;
}
