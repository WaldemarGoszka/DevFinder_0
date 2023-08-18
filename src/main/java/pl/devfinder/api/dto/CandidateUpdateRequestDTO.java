package pl.devfinder.api.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateUpdateRequestDTO {
    Long candidateId;
    String candidateUuid;
    @Size(max = 128, message = "Candidate first name should be max 128 chars")
    String firstName;
    @Size(max = 128, message = "Candidate last name should be max 128 chars")
    String lastName;
    @Email
    @Size(max = 128, message = "Candidate email contact should be max 128 chars")
    String emailContact;
    @Size(max = 128, message = "Candidate phone number should be max 128 chars")
    String phoneNumber;
    OffsetDateTime createdAt;
    @Pattern(regexp = "^(ACTIVE|INACTIVE|EMPLOYED)?$", message = "Candidate status should be 'ACTIVE', 'MID' or 'SENIOR'")
    @NotEmpty(message = "Candidate status should be 'ACTIVE', 'MID' or 'SENIOR'")
    String status;
    @Size(max = 512, message = "Candidate education should be max 512 chars")
    String education;
    @Size(max = 512, message = "Candidate other skills should be max 512 chars")
    String otherSkills;
    @Size(max = 512, message = "Candidate hobby should be max 512 chars")
    String hobby;
    @Size(max = 512, message = "Candidate foreign language should be max 512 chars")
    String foreignLanguage;
    @Size(max = 64, message = "Candidate cv file name should be max 64 chars")
    String cvFilename;
    @Size(max = 256, message = "Candidate github link should be max 256 chars")
    String githubLink;
    @Size(max = 256, message = "Candidate linkedin link should be max 256 chars")
    String linkedinLink;
    @Size(max = 64, message = "Candidate photo file name should be max 64 chars")
    String photoFilename;
    @Pattern(regexp = "^(JUNIOR|MID|SENIOR)?$", message = "Candidate experience level should be 'JUNIOR', 'MID' or 'SENIOR'")
    String experienceLevel;
    @Max(value = 100, message = "Candidate years of experience should be max 100")
    Integer yearsOfExperience;
    @Min(value = 0, message = "Candidate salary should be minimum 0")
    BigDecimal salaryMin;
    Boolean openToRemoteJob;
    String employerName;
    Set<String> candidateSkillsNames;
    @Size(max = 64, message = "Candidate city name should be max 64 chars")
    String residenceCityName;
    MultipartFile filePhoto;
    MultipartFile fileCv;
}
