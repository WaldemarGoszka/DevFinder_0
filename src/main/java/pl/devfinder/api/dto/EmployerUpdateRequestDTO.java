package pl.devfinder.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployerUpdateRequestDTO {
    Long employerId;
    String employerUuid;
    @Size(max = 255, message = "Employer company name should be max 255 chars")
    String companyName;
    @Size(max = 128, message = "Employer email contact should be max 128 chars")
    @Email
    String emailContact;
    @Size(max = 64, message = "Employer phone number should be max 64 chars")
    String phoneNumber;
    @Size(max = 512, message = "Employer description should be max 512 chars")
    String description;
    @Size(max = 64, message = "Employer logo file name should be max 64 chars")
    String logoFilename;
    @Size(max = 255, message = "Employer website should be max 255 chars")
    String website;
    Integer numberOfEmployees;
    Integer amountOfAvailableOffers;
    OffsetDateTime createdAt;
    @Size(max = 64)
    String CityName;
    MultipartFile fileLogo;
}
