package pl.devfinder.api.dto;

import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import pl.devfinder.domain.CandidateSkill;
import pl.devfinder.domain.City;
import pl.devfinder.domain.Employer;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDetailsDTO {
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
    Employer employerId;
    City residenceCityId;
    Set<CandidateSkill> candidateSkills;

    Set<String> candidateSkillsNames;
    String residenceCityName;
    MultipartFile filePhoto;
    MultipartFile fileCv;
}
