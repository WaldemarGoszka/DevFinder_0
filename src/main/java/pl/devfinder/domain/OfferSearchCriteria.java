package pl.devfinder.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

@Builder
@Data
@With
public class OfferSearchCriteria {
    List<String> experienceLevels;

    Integer remoteWork;
//    Integer yearsOfExperience;
    BigDecimal salaryMin;
//    BigDecimal salaryMax;
    String status;
    String city;
    Set<String> skills;

}
