package pl.devfinder.api.dto;

import pl.devfinder.domain.CandidateSkill;
import pl.devfinder.domain.City;

import java.math.BigDecimal;
import java.util.Set;

public class CandidateRowDTO {
    //String candidateUUId;
    String firstName;
    String lastName;
    String experienceLevel;
    Integer yearsOfExperience;
    BigDecimal salaryMin;
    City desiredJobCityId;
    City residenceCityId;
    Set<CandidateSkill> candidateSkills;
}
