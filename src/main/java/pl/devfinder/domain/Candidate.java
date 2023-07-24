package pl.devfinder.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "candidateUUId")
@ToString(of = {"candidateId", "candidateUUId", "firstName", "lastName", "emailContact"})
public class Candidate {

    Long candidateId;
    String candidateUUId;
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
    String cvFile;
    String githubLink;
    String linkedinLink;
    String pictureFile;
    String experienceLevel;
    Integer yearsOfExperience;
    BigDecimal salaryMin;
    Boolean openToRelocation;
    Boolean openToRemoteJob;
    Employer employer;
    City desiredJobCityId;
    City residenceCityId;
    Set<CandidateSkill> candidateSkills;

}
