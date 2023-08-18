package pl.devfinder.api.dto;

import lombok.*;
import org.hibernate.annotations.Where;
import org.springframework.web.multipart.MultipartFile;
import pl.devfinder.domain.City;

import java.time.OffsetDateTime;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployerDetailsDTO {
    Long employerId;
    String employerUuid;
    String companyName;
    String emailContact;
    String phoneNumber;
    String description;
    String logoFilename;
    String website;
    Integer numberOfEmployees;
    Integer amountOfAvailableOffers;
    OffsetDateTime createdAt;
    City cityId;
    String cityName;
    MultipartFile fileLogo;
}
