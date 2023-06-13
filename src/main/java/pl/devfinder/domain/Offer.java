package pl.devfinder.domain;


import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "offerUUId")
@ToString(of = {"offerId", "offerUUId", "title"})
public class Offer {
    Long offerId;
    String offerUUId;
    String title;
    String description;
    String otherSkills;
    Integer remoteWork;
    String experienceLevel;
    Integer yearsOfExperience;
    BigDecimal salaryMin;
    BigDecimal salaryMax;
    OffsetDateTime createdAt;
    String benefits;
    String status;
    Employer employerId;
    City cityId;
    Set<OfferSkill> offerSkills;

}
