package pl.devfinder.util;

import lombok.experimental.UtilityClass;
import pl.devfinder.domain.CandidateUpdateRequest;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@UtilityClass
public class CandidateUpdateRequestFixtures {

    public static CandidateUpdateRequest someCandidateUpdateRequest1() {
        return CandidateUpdateRequest.builder()
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
                .residenceCityName("Warszawa")
                .build();
    }
}
