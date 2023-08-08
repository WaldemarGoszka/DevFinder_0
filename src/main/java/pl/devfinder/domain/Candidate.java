package pl.devfinder.domain;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "candidateUuid")
@ToString(of = {"candidateId", "candidateUuid", "firstName", "lastName", "emailContact"})
public class Candidate {

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
    Employer employerId;
    City residenceCityId;
    Set<CandidateSkill> candidateSkills;

//    String residenceCityName;
//    Set<String> nameCandidateSkills;
//    MultipartFile filePhoto;
//    MultipartFile fileCv;

}
