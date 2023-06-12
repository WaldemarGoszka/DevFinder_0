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
//    Long employerId;
//    String employerUUId;
    String companyName;
//    String email_Contact;
//    String phoneNumber;
//    String description;
    String logoFile;
    String website;
    Integer numberOfEmployees;
//    OffsetDateTime createdAt;
    City cityId;
    //TODO https://stackoverflow.com/questions/45500779/mapstruct-add-a-new-calculated-field-to-the-dto
    //Integer NumberOfOffers;
}
