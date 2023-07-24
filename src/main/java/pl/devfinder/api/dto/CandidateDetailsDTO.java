package pl.devfinder.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.devfinder.domain.CandidateSkill;
import pl.devfinder.domain.City;
import pl.devfinder.domain.Employer;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDetailsDTO {
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
    Boolean openToRemoteJob;
    Employer employer;
    City residenceCityId;
    Set<CandidateSkill> candidateSkills;
}
