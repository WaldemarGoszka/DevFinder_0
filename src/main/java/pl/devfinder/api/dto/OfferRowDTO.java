package pl.devfinder.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.devfinder.domain.City;
import pl.devfinder.domain.Employer;

import java.time.OffsetDateTime;

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
}
