package pl.devfinder.api.dto.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.api.dto.CandidateDetailsDTO;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.CandidateSkill;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CandidateDetailsMapperImpl.class})
@ExtendWith(SpringExtension.class)
class CandidateDetailsMapperImplDiffBlueTest {
    @Autowired
    private CandidateDetailsMapperImpl candidateDetailsMapperImpl;

    /**
     * Method under test: {@link CandidateDetailsMapperImpl#map(CandidateDetailsDTO)}
     */
    @Test
    void testMap() {
        Candidate actualMapResult = candidateDetailsMapperImpl.map(new CandidateDetailsDTO());
        assertNull(actualMapResult.getCandidateId());
        assertNull(actualMapResult.getYearsOfExperience());
        assertNull(actualMapResult.getStatus());
        assertNull(actualMapResult.getSalaryMin());
        assertNull(actualMapResult.getResidenceCityId());
        assertNull(actualMapResult.getPhotoFilename());
        assertNull(actualMapResult.getPhoneNumber());
        assertNull(actualMapResult.getOtherSkills());
        assertNull(actualMapResult.getOpenToRemoteJob());
        assertNull(actualMapResult.getLinkedinLink());
        assertNull(actualMapResult.getLastName());
        assertNull(actualMapResult.getHobby());
        assertNull(actualMapResult.getGithubLink());
        assertNull(actualMapResult.getForeignLanguage());
        assertNull(actualMapResult.getFirstName());
        assertNull(actualMapResult.getExperienceLevel());
        assertNull(actualMapResult.getEmployerId());
        assertNull(actualMapResult.getEmailContact());
        assertNull(actualMapResult.getEducation());
        assertNull(actualMapResult.getCvFilename());
        assertNull(actualMapResult.getCreatedAt());
        assertNull(actualMapResult.getCandidateUuid());
        assertNull(actualMapResult.getCandidateSkills());
    }

    /**
     * Method under test: {@link CandidateDetailsMapperImpl#map(CandidateDetailsDTO)}
     */
    @Test
    void testMap2() {
        assertNull(candidateDetailsMapperImpl.map((CandidateDetailsDTO) null));
    }

    /**
     * Method under test: {@link CandidateDetailsMapperImpl#map(CandidateDetailsDTO)}
     */
    @Test
    void testMap3() throws IOException {
        OffsetDateTime createdAt = OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC);
        BigDecimal salaryMin = BigDecimal.valueOf(1L);
        HashSet<CandidateSkill> candidateSkills = new HashSet<>();
        HashSet<String> candidateSkillsNames = new HashSet<>();
        MockMultipartFile filePhoto = new MockMultipartFile("Name",
                new ByteArrayInputStream("AXAXAXAX".getBytes(StandardCharsets.UTF_8)));

        Candidate actualMapResult = candidateDetailsMapperImpl.map(new CandidateDetailsDTO(1L, "2020-03-01", "Jane",
                "Doe", "jane.doe@example.org", "6625550144", createdAt, "Status", "Education", "Other Skills", "Hobby", "en",
                "foo.txt", "Github Link", "Linkedin Link", "foo.txt", "Experience Level", 1, salaryMin, true, null, null,
                candidateSkills, candidateSkillsNames, "Residence City Name", filePhoto,
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes(StandardCharsets.UTF_8)))));
        assertEquals(1L, actualMapResult.getCandidateId().longValue());
        assertEquals(1, actualMapResult.getYearsOfExperience().intValue());
        assertEquals("Status", actualMapResult.getStatus());
        BigDecimal expectedSalaryMin = BigDecimal.ONE;
        BigDecimal salaryMin2 = actualMapResult.getSalaryMin();
        assertSame(expectedSalaryMin, salaryMin2);
        assertNull(actualMapResult.getResidenceCityId());
        assertTrue(actualMapResult.getCandidateSkills().isEmpty());
        assertEquals("Z", actualMapResult.getCreatedAt().getOffset().toString());
        assertNull(actualMapResult.getEmployerId());
        assertEquals("foo.txt", actualMapResult.getPhotoFilename());
        assertEquals("6625550144", actualMapResult.getPhoneNumber());
        assertTrue(actualMapResult.getOpenToRemoteJob());
        assertEquals("Other Skills", actualMapResult.getOtherSkills());
        assertEquals("Linkedin Link", actualMapResult.getLinkedinLink());
        assertEquals("Doe", actualMapResult.getLastName());
        assertEquals("Hobby", actualMapResult.getHobby());
        assertEquals("Github Link", actualMapResult.getGithubLink());
        assertEquals("en", actualMapResult.getForeignLanguage());
        assertEquals("Jane", actualMapResult.getFirstName());
        assertEquals("Experience Level", actualMapResult.getExperienceLevel());
        assertEquals("foo.txt", actualMapResult.getCvFilename());
        assertEquals("jane.doe@example.org", actualMapResult.getEmailContact());
        assertEquals("Education", actualMapResult.getEducation());
        assertEquals("2020-03-01", actualMapResult.getCandidateUuid());
        assertEquals("1", salaryMin2.toString());
    }

    /**
     * Method under test: {@link CandidateDetailsMapperImpl#map(CandidateDetailsDTO)}
     */
    @Test
    void testMap4() {
        CandidateDetailsDTO candidateDetailsDTO = mock(CandidateDetailsDTO.class);
        when(candidateDetailsDTO.getOpenToRemoteJob()).thenReturn(true);
        when(candidateDetailsDTO.getYearsOfExperience()).thenReturn(1);
        when(candidateDetailsDTO.getCandidateId()).thenReturn(1L);
        when(candidateDetailsDTO.getCandidateUuid()).thenReturn("2020-03-01");
        when(candidateDetailsDTO.getCvFilename()).thenReturn("foo.txt");
        when(candidateDetailsDTO.getEducation()).thenReturn("Education");
        when(candidateDetailsDTO.getEmailContact()).thenReturn("jane.doe@example.org");
        when(candidateDetailsDTO.getExperienceLevel()).thenReturn("Experience Level");
        when(candidateDetailsDTO.getFirstName()).thenReturn("Jane");
        when(candidateDetailsDTO.getForeignLanguage()).thenReturn("en");
        when(candidateDetailsDTO.getGithubLink()).thenReturn("Github Link");
        when(candidateDetailsDTO.getHobby()).thenReturn("Hobby");
        when(candidateDetailsDTO.getLastName()).thenReturn("Doe");
        when(candidateDetailsDTO.getLinkedinLink()).thenReturn("Linkedin Link");
        when(candidateDetailsDTO.getOtherSkills()).thenReturn("Other Skills");
        when(candidateDetailsDTO.getPhoneNumber()).thenReturn("6625550144");
        when(candidateDetailsDTO.getPhotoFilename()).thenReturn("foo.txt");
        when(candidateDetailsDTO.getStatus()).thenReturn("Status");
        BigDecimal valueOfResult = BigDecimal.valueOf(1L);
        when(candidateDetailsDTO.getSalaryMin()).thenReturn(valueOfResult);
        when(candidateDetailsDTO.getCreatedAt())
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        when(candidateDetailsDTO.getCandidateSkills()).thenReturn(new HashSet<>());
        when(candidateDetailsDTO.getResidenceCityId()).thenReturn(null);
        when(candidateDetailsDTO.getEmployerId()).thenReturn(null);
        Candidate actualMapResult = candidateDetailsMapperImpl.map(candidateDetailsDTO);
        assertEquals(1L, actualMapResult.getCandidateId().longValue());
        assertEquals(1, actualMapResult.getYearsOfExperience().intValue());
        assertEquals("Status", actualMapResult.getStatus());
        BigDecimal expectedSalaryMin = BigDecimal.ONE;
        BigDecimal salaryMin = actualMapResult.getSalaryMin();
        assertSame(expectedSalaryMin, salaryMin);
        assertNull(actualMapResult.getResidenceCityId());
        assertTrue(actualMapResult.getCandidateSkills().isEmpty());
        assertEquals("Z", actualMapResult.getCreatedAt().getOffset().toString());
        assertNull(actualMapResult.getEmployerId());
        assertEquals("foo.txt", actualMapResult.getPhotoFilename());
        assertEquals("6625550144", actualMapResult.getPhoneNumber());
        assertTrue(actualMapResult.getOpenToRemoteJob());
        assertEquals("Other Skills", actualMapResult.getOtherSkills());
        assertEquals("Linkedin Link", actualMapResult.getLinkedinLink());
        assertEquals("Doe", actualMapResult.getLastName());
        assertEquals("Hobby", actualMapResult.getHobby());
        assertEquals("Github Link", actualMapResult.getGithubLink());
        assertEquals("en", actualMapResult.getForeignLanguage());
        assertEquals("Jane", actualMapResult.getFirstName());
        assertEquals("Experience Level", actualMapResult.getExperienceLevel());
        assertEquals("foo.txt", actualMapResult.getCvFilename());
        assertEquals("jane.doe@example.org", actualMapResult.getEmailContact());
        assertEquals("Education", actualMapResult.getEducation());
        assertEquals("2020-03-01", actualMapResult.getCandidateUuid());
        assertEquals("1", salaryMin.toString());
        verify(candidateDetailsDTO).getOpenToRemoteJob();
        verify(candidateDetailsDTO).getYearsOfExperience();
        verify(candidateDetailsDTO).getCandidateId();
        verify(candidateDetailsDTO).getCandidateUuid();
        verify(candidateDetailsDTO).getCvFilename();
        verify(candidateDetailsDTO).getEducation();
        verify(candidateDetailsDTO).getEmailContact();
        verify(candidateDetailsDTO).getExperienceLevel();
        verify(candidateDetailsDTO).getFirstName();
        verify(candidateDetailsDTO).getForeignLanguage();
        verify(candidateDetailsDTO).getGithubLink();
        verify(candidateDetailsDTO).getHobby();
        verify(candidateDetailsDTO).getLastName();
        verify(candidateDetailsDTO).getLinkedinLink();
        verify(candidateDetailsDTO).getOtherSkills();
        verify(candidateDetailsDTO).getPhoneNumber();
        verify(candidateDetailsDTO).getPhotoFilename();
        verify(candidateDetailsDTO).getStatus();
        verify(candidateDetailsDTO).getSalaryMin();
        verify(candidateDetailsDTO).getCreatedAt();
        verify(candidateDetailsDTO).getCandidateSkills();
        verify(candidateDetailsDTO).getResidenceCityId();
        verify(candidateDetailsDTO).getEmployerId();
    }

    /**
     * Method under test: {@link CandidateDetailsMapperImpl#map(Candidate)}
     */
    @Test
    void testMap5() {
        assertNull(candidateDetailsMapperImpl.map((Candidate) null));
    }
}

