package pl.devfinder.util;

import lombok.experimental.UtilityClass;
import pl.devfinder.domain.City;
import pl.devfinder.domain.Employer;
import pl.devfinder.domain.Offer;
import pl.devfinder.domain.OfferSkill;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Set;

@UtilityClass
public class OfferFixtures {


    public static Offer someOffer1() {
        return Offer.builder()
                .offerId(1L)
                .offerUuid("offer1-000000-000000")
                .title("Offer 1 Title")
                .description("offer1 description")
                .otherSkills("offer1 otherSkills")
                .remoteWork(0)
                .experienceLevel("JUNIOR")
                .yearsOfExperience(1)
                .salaryMin(BigDecimal.valueOf(1000))
                .salaryMax(BigDecimal.valueOf(2000))
                .createdAt(OffsetDateTime.of(2022,1,1,1,1,1,1, ZoneOffset.UTC))
                .benefits("offer1 benefits")
                .status("ACTIVE")
                .employerId(Employer.builder().build())
                .cityId(City.builder().build())
                .offerSkills(Set.of(OfferSkill.builder().build()))
                .build();
    }

}
