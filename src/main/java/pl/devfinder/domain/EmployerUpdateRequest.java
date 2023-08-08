package pl.devfinder.domain;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.OffsetDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = "employerUuid")
@ToString(of = {"employerId", "employerUuid", "companyName", "emailContact"})
public class EmployerUpdateRequest {
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
    String cityName;
    MultipartFile fileLogo;
}
