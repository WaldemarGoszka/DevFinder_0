package pl.devfinder.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.devfinder.domain.CandidateSkill;
import pl.devfinder.domain.City;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateRowDTO {
    Long candidateId;
    String firstName;
    String lastName;
    String experienceLevel;
    Integer yearsOfExperience;
    BigDecimal salaryMin;
    City residenceCityId;
    String status;
    Set<CandidateSkill> candidateSkills;
}
