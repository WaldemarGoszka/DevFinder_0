package pl.devfinder.api.dto.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.api.dto.CandidateUpdateRequestDTO;
import pl.devfinder.domain.CandidateUpdateRequest;

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

@ContextConfiguration(classes = {CandidateUpdateRequestMapperImpl.class})
@ExtendWith(SpringExtension.class)
class CandidateUpdateRequestMapperImplDiffBlueTest {
    @Autowired
    private CandidateUpdateRequestMapperImpl candidateUpdateRequestMapperImpl;

    /**
     * Method under test: {@link CandidateUpdateRequestMapperImpl#map(CandidateUpdateRequestDTO)}
     */
    @Test
    void testMap() {
        CandidateUpdateRequest actualMapResult = candidateUpdateRequestMapperImpl.map(new CandidateUpdateRequestDTO());
        assertNull(actualMapResult.getCandidateId());
        assertNull(actualMapResult.getYearsOfExperience());
        assertNull(actualMapResult.getStatus());
        assertNull(actualMapResult.getSalaryMin());
        assertNull(actualMapResult.getResidenceCityName());
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
        assertNull(actualMapResult.getFilePhoto());
        assertNull(actualMapResult.getFileCv());
        assertNull(actualMapResult.getExperienceLevel());
        assertNull(actualMapResult.getEmployerName());
        assertNull(actualMapResult.getEmailContact());
        assertNull(actualMapResult.getEducation());
        assertNull(actualMapResult.getCvFilename());
        assertNull(actualMapResult.getCreatedAt());
        assertNull(actualMapResult.getCandidateUuid());
        assertNull(actualMapResult.getCandidateSkillsNames());
    }

    /**
     * Method under test: {@link CandidateUpdateRequestMapperImpl#map(CandidateUpdateRequestDTO)}
     */
    @Test
    void testMap2() {
        assertNull(candidateUpdateRequestMapperImpl.map(null));
    }

    /**
     * Method under test: {@link CandidateUpdateRequestMapperImpl#map(CandidateUpdateRequestDTO)}
     */
    @Test
    void testMap3() throws IOException {
        OffsetDateTime createdAt = OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC);
        BigDecimal salaryMin = BigDecimal.valueOf(1L);
        HashSet<String> candidateSkillsNames = new HashSet<>();
        MockMultipartFile filePhoto = new MockMultipartFile("Name",
                new ByteArrayInputStream("AXAXAXAX".getBytes(StandardCharsets.UTF_8)));

        MockMultipartFile fileCv = new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes(StandardCharsets.UTF_8)));

        CandidateUpdateRequest actualMapResult = candidateUpdateRequestMapperImpl.map(new CandidateUpdateRequestDTO(1L,
                "2020-03-01", "Jane", "Doe", "jane.doe@example.org", "6625550144", createdAt, "Status", "Education",
                "Other Skills", "Hobby", "en", "foo.txt", "Github Link", "Linkedin Link", "foo.txt", "Experience Level", 1,
                salaryMin, true, "Employer Name", candidateSkillsNames, "Residence City Name", filePhoto, fileCv));
        assertEquals(1L, actualMapResult.getCandidateId().longValue());
        assertEquals(1, actualMapResult.getYearsOfExperience().intValue());
        assertEquals("Status", actualMapResult.getStatus());
        BigDecimal expectedSalaryMin = BigDecimal.ONE;
        BigDecimal salaryMin2 = actualMapResult.getSalaryMin();
        assertSame(expectedSalaryMin, salaryMin2);
        assertEquals("Residence City Name", actualMapResult.getResidenceCityName());
        assertTrue(actualMapResult.getCandidateSkillsNames().isEmpty());
        assertEquals("Z", actualMapResult.getCreatedAt().getOffset().toString());
        assertEquals("Employer Name", actualMapResult.getEmployerName());
        assertEquals("foo.txt", actualMapResult.getPhotoFilename());
        assertEquals("6625550144", actualMapResult.getPhoneNumber());
        assertEquals("Other Skills", actualMapResult.getOtherSkills());
        assertTrue(actualMapResult.getOpenToRemoteJob());
        assertEquals("Doe", actualMapResult.getLastName());
        assertEquals("Linkedin Link", actualMapResult.getLinkedinLink());
        assertEquals("Hobby", actualMapResult.getHobby());
        assertEquals("Github Link", actualMapResult.getGithubLink());
        assertEquals("en", actualMapResult.getForeignLanguage());
        assertEquals("Jane", actualMapResult.getFirstName());
        assertSame(filePhoto, actualMapResult.getFilePhoto());
        assertSame(fileCv, actualMapResult.getFileCv());
        assertEquals("Experience Level", actualMapResult.getExperienceLevel());
        assertEquals("foo.txt", actualMapResult.getCvFilename());
        assertEquals("2020-03-01", actualMapResult.getCandidateUuid());
        assertEquals("Education", actualMapResult.getEducation());
        assertEquals("jane.doe@example.org", actualMapResult.getEmailContact());
        assertEquals("1", salaryMin2.toString());
    }

    /**
     * Method under test: {@link CandidateUpdateRequestMapperImpl#map(CandidateUpdateRequestDTO)}
     */
    @Test
    void testMap4() throws IOException {
        CandidateUpdateRequestDTO candidateUpdateRequestDTO = mock(CandidateUpdateRequestDTO.class);
        when(candidateUpdateRequestDTO.getOpenToRemoteJob()).thenReturn(true);
        when(candidateUpdateRequestDTO.getYearsOfExperience()).thenReturn(1);
        when(candidateUpdateRequestDTO.getCandidateId()).thenReturn(1L);
        when(candidateUpdateRequestDTO.getCandidateUuid()).thenReturn("2020-03-01");
        when(candidateUpdateRequestDTO.getCvFilename()).thenReturn("foo.txt");
        when(candidateUpdateRequestDTO.getEducation()).thenReturn("Education");
        when(candidateUpdateRequestDTO.getEmailContact()).thenReturn("jane.doe@example.org");
        when(candidateUpdateRequestDTO.getEmployerName()).thenReturn("Employer Name");
        when(candidateUpdateRequestDTO.getExperienceLevel()).thenReturn("Experience Level");
        when(candidateUpdateRequestDTO.getFirstName()).thenReturn("Jane");
        when(candidateUpdateRequestDTO.getForeignLanguage()).thenReturn("en");
        when(candidateUpdateRequestDTO.getGithubLink()).thenReturn("Github Link");
        when(candidateUpdateRequestDTO.getHobby()).thenReturn("Hobby");
        when(candidateUpdateRequestDTO.getLastName()).thenReturn("Doe");
        when(candidateUpdateRequestDTO.getLinkedinLink()).thenReturn("Linkedin Link");
        when(candidateUpdateRequestDTO.getOtherSkills()).thenReturn("Other Skills");
        when(candidateUpdateRequestDTO.getPhoneNumber()).thenReturn("6625550144");
        when(candidateUpdateRequestDTO.getPhotoFilename()).thenReturn("foo.txt");
        when(candidateUpdateRequestDTO.getResidenceCityName()).thenReturn("Residence City Name");
        when(candidateUpdateRequestDTO.getStatus()).thenReturn("Status");
        BigDecimal valueOfResult = BigDecimal.valueOf(1L);
        when(candidateUpdateRequestDTO.getSalaryMin()).thenReturn(valueOfResult);
        when(candidateUpdateRequestDTO.getCreatedAt())
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        when(candidateUpdateRequestDTO.getCandidateSkillsNames()).thenReturn(new HashSet<>());
        MockMultipartFile mockMultipartFile = new MockMultipartFile("Name",
                new ByteArrayInputStream("AXAXAXAX".getBytes(StandardCharsets.UTF_8)));

        when(candidateUpdateRequestDTO.getFileCv()).thenReturn(mockMultipartFile);
        MockMultipartFile mockMultipartFile2 = new MockMultipartFile("Name",
                new ByteArrayInputStream("AXAXAXAX".getBytes(StandardCharsets.UTF_8)));

        when(candidateUpdateRequestDTO.getFilePhoto()).thenReturn(mockMultipartFile2);
        CandidateUpdateRequest actualMapResult = candidateUpdateRequestMapperImpl.map(candidateUpdateRequestDTO);
        assertEquals(1L, actualMapResult.getCandidateId().longValue());
        assertEquals(1, actualMapResult.getYearsOfExperience().intValue());
        assertEquals("Status", actualMapResult.getStatus());
        BigDecimal expectedSalaryMin = BigDecimal.ONE;
        BigDecimal salaryMin = actualMapResult.getSalaryMin();
        assertSame(expectedSalaryMin, salaryMin);
        assertEquals("Residence City Name", actualMapResult.getResidenceCityName());
        assertTrue(actualMapResult.getCandidateSkillsNames().isEmpty());
        assertEquals("Z", actualMapResult.getCreatedAt().getOffset().toString());
        assertEquals("Employer Name", actualMapResult.getEmployerName());
        assertEquals("foo.txt", actualMapResult.getPhotoFilename());
        assertEquals("6625550144", actualMapResult.getPhoneNumber());
        assertEquals("Other Skills", actualMapResult.getOtherSkills());
        assertTrue(actualMapResult.getOpenToRemoteJob());
        assertEquals("Doe", actualMapResult.getLastName());
        assertEquals("Linkedin Link", actualMapResult.getLinkedinLink());
        assertEquals("Hobby", actualMapResult.getHobby());
        assertEquals("Github Link", actualMapResult.getGithubLink());
        assertEquals("en", actualMapResult.getForeignLanguage());
        assertEquals("Jane", actualMapResult.getFirstName());
        assertSame(mockMultipartFile2, actualMapResult.getFilePhoto());
        assertSame(mockMultipartFile, actualMapResult.getFileCv());
        assertEquals("Experience Level", actualMapResult.getExperienceLevel());
        assertEquals("foo.txt", actualMapResult.getCvFilename());
        assertEquals("2020-03-01", actualMapResult.getCandidateUuid());
        assertEquals("Education", actualMapResult.getEducation());
        assertEquals("jane.doe@example.org", actualMapResult.getEmailContact());
        assertEquals("1", salaryMin.toString());
        verify(candidateUpdateRequestDTO).getOpenToRemoteJob();
        verify(candidateUpdateRequestDTO).getYearsOfExperience();
        verify(candidateUpdateRequestDTO).getCandidateId();
        verify(candidateUpdateRequestDTO).getCandidateUuid();
        verify(candidateUpdateRequestDTO).getCvFilename();
        verify(candidateUpdateRequestDTO).getEducation();
        verify(candidateUpdateRequestDTO).getEmailContact();
        verify(candidateUpdateRequestDTO).getEmployerName();
        verify(candidateUpdateRequestDTO).getExperienceLevel();
        verify(candidateUpdateRequestDTO).getFirstName();
        verify(candidateUpdateRequestDTO).getForeignLanguage();
        verify(candidateUpdateRequestDTO).getGithubLink();
        verify(candidateUpdateRequestDTO).getHobby();
        verify(candidateUpdateRequestDTO).getLastName();
        verify(candidateUpdateRequestDTO).getLinkedinLink();
        verify(candidateUpdateRequestDTO).getOtherSkills();
        verify(candidateUpdateRequestDTO).getPhoneNumber();
        verify(candidateUpdateRequestDTO).getPhotoFilename();
        verify(candidateUpdateRequestDTO).getResidenceCityName();
        verify(candidateUpdateRequestDTO).getStatus();
        verify(candidateUpdateRequestDTO).getSalaryMin();
        verify(candidateUpdateRequestDTO).getCreatedAt();
        verify(candidateUpdateRequestDTO).getCandidateSkillsNames();
        verify(candidateUpdateRequestDTO).getFileCv();
        verify(candidateUpdateRequestDTO).getFilePhoto();
    }
}

