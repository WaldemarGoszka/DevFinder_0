package pl.devfinder.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.devfinder.domain.City;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployerDetailsDTO {
    Long employerId;
    String employerUUId;
    String companyName;
    String emailContact;
    String phoneNumber;
    String description;
    String logoFile;
    String website;
    Integer numberOfEmployees;
    Integer amountOfAvailableOffers;
    OffsetDateTime createdAt;
    City cityId;
}
