package pl.devfinder.util;

import lombok.experimental.UtilityClass;
import pl.devfinder.api.dto.CandidateRowDTO;
import pl.devfinder.domain.CandidateSkill;
import pl.devfinder.domain.City;
import pl.devfinder.domain.Skill;

import java.math.BigDecimal;
import java.util.Set;

@UtilityClass
public class CandidateRowDTOFixtures {

    public static CandidateRowDTO someCandidateRowDTO1() {
        return CandidateRowDTO.builder()
                .candidateId(1L)
                .firstName("Jan")
                .lastName("Kowalski")
                .status("ACTIVE")
                .experienceLevel("JUNIOR")
                .yearsOfExperience(1)
                .salaryMin(BigDecimal.valueOf(1000))
                .residenceCityId(City.builder()
                        .cityName("Warszawa")
                        .build())
                .candidateSkills(Set.of(CandidateSkill.builder()
                        .skillId(Skill.builder()
                                .skillId(1L)
                                .skillName("Python")
                                .build())
                        .build()))
                .build();
    }
}
