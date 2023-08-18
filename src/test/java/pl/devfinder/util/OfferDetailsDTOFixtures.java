package pl.devfinder.util;

import lombok.experimental.UtilityClass;
import pl.devfinder.api.dto.OfferDetailsDTO;
import pl.devfinder.domain.City;
import pl.devfinder.domain.Employer;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@UtilityClass
public class OfferDetailsDTOFixtures {

    public static OfferDetailsDTO someOfferDetailsDTO1() {
        return OfferDetailsDTO.builder()
                .offerId(1L)
                .offerUuid("offer1-000000-000000")
                .title("Offer 1 Title")
                .description("offer1 description")
                .otherSkills("offer1 otherSkills")
                .yearsOfExperience(5)
                .salaryMin(BigDecimal.valueOf(1000))
                .salaryMax(BigDecimal.valueOf(2000))
                .benefits("offer1 benefits")
                .experienceLevel("JUNIOR")
                .createdAt(OffsetDateTime.of(2022,1,1,1,1,1,1, ZoneOffset.UTC))
                .status("ACTIVE")
                .employerId(Employer.builder().build())
                .cityId(City.builder().build())
                .build();
    }

}
