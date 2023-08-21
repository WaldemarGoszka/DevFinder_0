package pl.devfinder.util;

import lombok.experimental.UtilityClass;
import pl.devfinder.api.dto.EmployerDetailsDTO;
import pl.devfinder.domain.City;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@UtilityClass
public class EmployerDetailsDTOFixtures {


    public static EmployerDetailsDTO someEmployerDetailsDTO1() {
        return EmployerDetailsDTO.builder()
                .employerId(1L)
                .employerUuid("employer1-000000-000000")
                .companyName("Test Name Company")
                .emailContact("testEmailCompany@example.com")
                .phoneNumber("123 456 789")
                .description("some description")
                .logoFilename("")
                .website("www.www.www")
                .numberOfEmployees(100)
                .amountOfAvailableOffers(1)
                .createdAt(OffsetDateTime.of(2022, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC))
                .cityId(City.builder()
                        .cityName("Warszawa")
                        .build())
                .build();
    }

}
