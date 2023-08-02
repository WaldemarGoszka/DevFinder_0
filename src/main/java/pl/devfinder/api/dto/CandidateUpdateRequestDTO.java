package pl.devfinder.api.dto;

import jakarta.validation.constraints.Email;
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
    String firstName;
    String lastName;
    @Email
            //TODO add validate other pool
    String emailContact;
    String phoneNumber;
    OffsetDateTime createdAt;
    String status;
    String education;
    String otherSkills;
    String hobby;
    String foreignLanguage;
    String cvFilename;
    String githubLink;
    String linkedinLink;
    String photoFilename;
    String experienceLevel;
    Integer yearsOfExperience;
    BigDecimal salaryMin;
    Boolean openToRemoteJob;
    String employerName;
    Set<String> candidateSkillsNames;
    String residenceCityName;
    MultipartFile filePhoto;
    MultipartFile fileCv;
}
