package pl.devfinder.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.devfinder.domain.City;
import pl.devfinder.domain.Employer;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfferUpdateRequestDTO {
    Long offerId;
    String offerUuid;
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
    String cityName;
//    Set<OfferSkills> offerSkills;
    Set<String> offerSkillsNames;
}
