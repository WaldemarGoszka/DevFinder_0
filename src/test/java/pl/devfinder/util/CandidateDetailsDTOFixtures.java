package pl.devfinder.util;

import lombok.experimental.UtilityClass;
import pl.devfinder.api.dto.CandidateDetailsDTO;
import pl.devfinder.domain.CandidateSkill;
import pl.devfinder.domain.City;
import pl.devfinder.domain.Skill;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Set;

@UtilityClass
public class CandidateDetailsDTOFixtures {

    public static CandidateDetailsDTO someCandidateDetailsDTO1() {
        return CandidateDetailsDTO.builder()
                .candidateId(1L)
                .candidateUuid("candidate1-000000-000000")
                .firstName("Jan")
                .lastName("Kowalski")
                .emailContact("jan@example.com")
                .phoneNumber("123 456 789")
                .createdAt(OffsetDateTime.of(2022, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC))
                .status("ACTIVE")
                .education("")
                .otherSkills("")
                .hobby("")
                .foreignLanguage("")
                .cvFilename("")
                .githubLink("")
                .linkedinLink("")
                .photoFilename("")
                .experienceLevel("JUNIOR")
                .yearsOfExperience(1)
                .salaryMin(BigDecimal.valueOf(1000))
                .openToRemoteJob(true)
                .employerId(null)
                .residenceCityId(City.builder()
                        .cityName("Warszawa")
                        .build())
                .candidateSkills(Set.of(CandidateSkill.builder()
                        .skillId(Skill.builder()
                                .skillId(1L)
                                .skillName("Python")
                                .build())
                        .build()))
                .employerId(EmployerFixtures.someEmployer1())
                .candidateSkillsNames(Set.of("Python"))
                .residenceCityName("Warszawa")
                .build();
    }
}
