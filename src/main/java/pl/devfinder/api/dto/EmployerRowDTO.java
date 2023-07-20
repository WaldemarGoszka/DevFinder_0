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
    String companyName;
    String logoFile;
    String website;
    Integer numberOfEmployees;
    City cityId;
    Integer amountOfAvailableOffers;
    //TODO https://stackoverflow.com/questions/45500779/mapstruct-add-a-new-calculated-field-to-the-dto
}
