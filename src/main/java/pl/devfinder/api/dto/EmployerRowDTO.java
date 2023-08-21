package pl.devfinder.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.devfinder.domain.City;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployerRowDTO {
    Long employerId;
    String companyName;
    String logoFilename;
    String website;
    Integer numberOfEmployees;
    City cityId;
    Integer amountOfAvailableOffers;
}
