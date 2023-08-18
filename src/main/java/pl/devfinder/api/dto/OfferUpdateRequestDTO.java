package pl.devfinder.api.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @NotEmpty(message = "Offer title should not be empty")
    @Size(max = 256, message = "Offer title should be max 256 chars")
    String title;
    @Size(max = 512, message = "Offer description should be max 512 chars")
    String description;
    @Size(max = 256, message = "Offer other skills should be max 256 chars")
    String otherSkills;
    @Min(value = 0, message = "Offer remote work should be between 0 - 100")
    @Max(value = 100, message = "Offer remote work should be between 0 - 100")
    Integer remoteWork;
    @Pattern(regexp = "^(JUNIOR|MID|SENIOR)?$", message = "Offer experience level should be 'JUNIOR', 'MID' or 'SENIOR'")
    String experienceLevel;
    @Max(value = 100, message = "Offer years of experience should be max 100")
    Integer yearsOfExperience;
    @Min(value = 0, message = "Offer salary min should be minimum 0")
    BigDecimal salaryMin;
    @Min(value = 0, message = "Offer salary max should be minimum 0")
    BigDecimal salaryMax;
    OffsetDateTime createdAt;
    @Size(max = 256, message = "Offer benefits should be max 256 chars")
    String benefits;
    @Pattern(regexp = "^(ACTIVE|EXPIRED)?$", message = "Offer status should be 'ACTIVE' or 'EXPIRED'")
    String status;
    Employer employerId;
    @Size(max = 64, message = "Offer city name should be max 64 chars")
    String cityName;
    Set<String> offerSkillsNames;
}
