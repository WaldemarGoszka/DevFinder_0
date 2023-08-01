package pl.devfinder.domain;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@With
@Data
@Builder
@EqualsAndHashCode(of = "candidateUuid")
@ToString(of = {"candidateId", "candidateUuid", "firstName", "lastName", "emailContact"})
public class CandidateUpdateRequest {

    Long candidateId;
    String candidateUuid;
    String firstName;
    String lastName;
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
    String residenceCityName;
    Set<String> nameCandidateSkills;
    MultipartFile filePhoto;
    MultipartFile fileCv;

}
