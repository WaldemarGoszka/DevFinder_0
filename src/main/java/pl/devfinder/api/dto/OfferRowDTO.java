package pl.devfinder.api.dto;

import pl.devfinder.domain.City;
import pl.devfinder.domain.Employer;
import pl.devfinder.domain.OfferSkill;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

public class OfferRowDTO {
//    Long offerId;
//    String offerUUId;
    String title;
//    String description;
//    String otherSkills;
    Integer remoteWork;
    String experienceLevel;
//    Integer yearsOfExperience;
    BigDecimal salaryMin;
    BigDecimal salaryMax;
    OffsetDateTime createdAt;
//    String benefits;
//    String status;
    Employer employer_id;
    City cityId;
//    Set<OfferSkill> offerSkills;
}
