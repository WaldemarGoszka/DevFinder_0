package pl.devfinder.util;

import lombok.experimental.UtilityClass;
import pl.devfinder.api.dto.EmployerRowDTO;
import pl.devfinder.domain.City;

@UtilityClass
public class EmployerRowDTOFixtures {
    public static EmployerRowDTO someEmployerRowDTO1() {
        return EmployerRowDTO.builder()
                .employerId(1L)
                .companyName("Test Name Company")
                .logoFilename("")
                .website("www.www.www")
                .numberOfEmployees(100)
                .amountOfAvailableOffers(1)
                .cityId(City.builder()
                        .cityName("Warszawa")
                        .build())
                .build();
    }
}
