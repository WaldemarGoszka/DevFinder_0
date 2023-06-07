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
@EqualsAndHashCode(of = "offerUUId")
@ToString(of = {"offerId", "offerUUId", "title", "description", "createdAt", "status"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "offer")
public class OfferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offerId;

    @Column(name = "offer_uuid", nullable = false)
    private String offerUUId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "other_skills")
    private String otherSkills;

    @Column(name = "remote_work")
    private Integer remoteWork;

    @Column(name = "experience_level", nullable = false)
    private String experienceLevel;

    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;

    @Column(name = "salary_min")
    private BigDecimal salaryMin;

    @Column(name = "salary_max")
    private BigDecimal salaryMax;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "expiration_date")
    private OffsetDateTime expirationDate;

    @Column(name = "benefits")
    private String benefits;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "employer_id", nullable = false)
    private EmployerEntity employer_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private CityEntity cityId;

}
