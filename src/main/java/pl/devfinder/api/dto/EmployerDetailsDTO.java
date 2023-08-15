package pl.devfinder.api.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import pl.devfinder.domain.City;

import java.time.OffsetDateTime;

@Data
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
