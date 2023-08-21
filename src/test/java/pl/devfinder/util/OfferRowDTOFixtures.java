package pl.devfinder.util;

import lombok.experimental.UtilityClass;
import pl.devfinder.api.dto.OfferRowDTO;
import pl.devfinder.domain.City;
import pl.devfinder.domain.Employer;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@UtilityClass
public class OfferRowDTOFixtures {

    public static OfferRowDTO someOfferRowDTO1() {
        return OfferRowDTO.builder()
                .offerId(1L)
                .offerUuid("offer1-000000-000000")
                .title("Offer 1 Title")
                .experienceLevel("JUNIOR")
                .createdAt(OffsetDateTime.of(2022, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC))
                .status("ACTIVE")
                .employerId(Employer.builder().build())
                .cityId(City.builder().build())
                .build();
    }

}
