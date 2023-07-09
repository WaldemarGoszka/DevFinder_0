package pl.devfinder.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Builder
@Data
@With
public class OfferSearchCriteria {

    Integer remoteWork;
    Boolean IsExperienceLevelIsJunior;
    Boolean IsExperienceLevelIsMid;
    Boolean IsExperienceLevelIsSenior;
    Integer yearsOfExperience;
    BigDecimal salaryMin;
    BigDecimal salaryMax;
    OffsetDateTime createdAt;
    String status;
    Employer employerId;
    City cityId;
    Set<OfferSkills> offerSkills;

}
