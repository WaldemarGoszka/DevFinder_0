package pl.devfinder.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.devfinder.domain.City;
import pl.devfinder.domain.Employer;
import pl.devfinder.domain.OfferSkills;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfferRowDTO {
    Long offerId;
    String title;
    String offerUUId;
    String remoteWorkFormatted;
    String experienceLevel;
    String salaryRange;
    OffsetDateTime createdAt;
    Employer employerId;
    City cityId;
    String daysSinceCreated;
    Set<OfferSkills> offerSkills;
}
