package pl.devfinder.infrastructure.database.repository.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.domain.*;
import pl.devfinder.infrastructure.database.entity.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CandidateSkillEntityMapperImpl.class})
@ExtendWith(SpringExtension.class)
class CandidateSkillEntityMapperImplDiffBlueTest {
    @Autowired
    private CandidateSkillEntityMapperImpl candidateSkillEntityMapperImpl;

    /**
     * Method under test: {@link CandidateSkillEntityMapperImpl#mapFromEntity(CandidateSkillEntity)}
     */
    @Test
    void testMapFromEntity() {
        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        EmployerEntity employerId = new EmployerEntity();
        employerId.setAmountOfAvailableOffers(10);
        employerId.setCityId(cityId);
        employerId.setCompanyName("Company Name");
        employerId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId.setDescription("The characteristics of someone or something");
        employerId.setEmailContact("jane.doe@example.org");
        employerId.setEmployerId(1L);
        employerId.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerId.setLogoFilename("foo.txt");
        employerId.setNumberOfEmployees(10);
        employerId.setPhoneNumber("6625550144");
        employerId.setWebsite("Website");

        CityEntity residenceCityId = new CityEntity();
        residenceCityId.setCandidateResidenceCities(new HashSet<>());
        residenceCityId.setCityId(1L);
        residenceCityId.setCityName("Oxford");
        residenceCityId.setEmployerCities(new HashSet<>());
        residenceCityId.setOfferCities(new HashSet<>());

        CandidateEntity candidateId = new CandidateEntity();
        candidateId.setCandidateId(1L);
        candidateId.setCandidateSkills(new HashSet<>());
        candidateId.setCandidateUuid("2020-03-01");
        candidateId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        candidateId.setCvFilename("foo.txt");
        candidateId.setEducation("Education");
        candidateId.setEmailContact("jane.doe@example.org");
        candidateId.setEmployerId(employerId);
        candidateId.setExperienceLevel("Experience Level");
        candidateId.setFirstName("Jane");
        candidateId.setForeignLanguage("en");
        candidateId.setGithubLink("Github Link");
        candidateId.setHobby("Hobby");
        candidateId.setLastName("Doe");
        candidateId.setLinkedinLink("Linkedin Link");
        candidateId.setOpenToRemoteJob(true);
        candidateId.setOtherSkills("Other Skills");
        candidateId.setPhoneNumber("6625550144");
        candidateId.setResidenceCityId(residenceCityId);
        BigDecimal salaryMin = BigDecimal.valueOf(1L);
        candidateId.setSalaryMin(salaryMin);
        candidateId.setStatus("Status");
        candidateId.setYearsOfExperience(1);

        SkillEntity skillId = new SkillEntity();
        skillId.setCandidateSkills(new HashSet<>());
        skillId.setOfferSkills(new HashSet<>());
        skillId.setSkillId(1L);
        skillId.setSkillName("Skill Name");

        CandidateSkillEntity candidateSkillEntity = new CandidateSkillEntity();
        candidateSkillEntity.setCandidateId(candidateId);
        candidateSkillEntity.setCandidateSkillId(1L);
        candidateSkillEntity.setSkillId(skillId);
        CandidateSkill actualMapFromEntityResult = candidateSkillEntityMapperImpl.mapFromEntity(candidateSkillEntity);
        assertEquals(1L, actualMapFromEntityResult.getCandidateSkillId().longValue());
        Skill skillId2 = actualMapFromEntityResult.getSkillId();
        assertEquals("Skill Name", skillId2.getSkillName());
        assertEquals(1L, skillId2.getSkillId().longValue());
        Candidate candidateId2 = actualMapFromEntityResult.getCandidateId();
        assertEquals("Status", candidateId2.getStatus());
        BigDecimal expectedSalaryMin = BigDecimal.ONE;
        BigDecimal salaryMin2 = candidateId2.getSalaryMin();
        assertSame(expectedSalaryMin, salaryMin2);
        assertEquals(1, candidateId2.getYearsOfExperience().intValue());
        assertTrue(candidateId2.getCandidateSkills().isEmpty());
        assertEquals("Education", candidateId2.getEducation());
        assertEquals("Doe", candidateId2.getLastName());
        assertEquals("Linkedin Link", candidateId2.getLinkedinLink());
        assertNull(candidateId2.getPhotoFilename());
        assertEquals("6625550144", candidateId2.getPhoneNumber());
        assertEquals("jane.doe@example.org", candidateId2.getEmailContact());
        assertTrue(candidateId2.getOpenToRemoteJob());
        assertEquals("Other Skills", candidateId2.getOtherSkills());
        assertEquals("2020-03-01", candidateId2.getCandidateUuid());
        assertEquals("Hobby", candidateId2.getHobby());
        assertEquals("Github Link", candidateId2.getGithubLink());
        assertEquals("en", candidateId2.getForeignLanguage());
        assertEquals("Jane", candidateId2.getFirstName());
        assertEquals("Experience Level", candidateId2.getExperienceLevel());
        assertEquals(1L, candidateId2.getCandidateId().longValue());
        assertEquals("Z", candidateId2.getCreatedAt().getOffset().toString());
        assertEquals("foo.txt", candidateId2.getCvFilename());
        Employer employerId2 = candidateId2.getEmployerId();
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", employerId2.getEmployerUuid());
        assertEquals(1L, employerId2.getEmployerId().longValue());
        assertEquals("jane.doe@example.org", employerId2.getEmailContact());
        assertEquals("The characteristics of someone or something", employerId2.getDescription());
        assertEquals("Z", employerId2.getCreatedAt().getOffset().toString());
        assertEquals("Company Name", employerId2.getCompanyName());
        City residenceCityId2 = candidateId2.getResidenceCityId();
        City cityId2 = employerId2.getCityId();
        assertEquals(residenceCityId2, cityId2);
        assertEquals("Oxford", residenceCityId2.getCityName());
        assertEquals("foo.txt", employerId2.getLogoFilename());
        assertEquals(10, employerId2.getAmountOfAvailableOffers().intValue());
        assertEquals(1L, residenceCityId2.getCityId().longValue());
        assertEquals("6625550144", employerId2.getPhoneNumber());
        assertEquals("Website", employerId2.getWebsite());
        assertEquals("1", salaryMin2.toString());
        assertEquals(10, employerId2.getNumberOfEmployees().intValue());
        assertEquals(1L, cityId2.getCityId().longValue());
        assertEquals("Oxford", cityId2.getCityName());
    }

    /**
     * Method under test: {@link CandidateSkillEntityMapperImpl#mapFromEntity(CandidateSkillEntity)}
     */
    @Test
    void testMapFromEntity2() {
        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        EmployerEntity employerId = new EmployerEntity();
        employerId.setAmountOfAvailableOffers(10);
        employerId.setCityId(cityId);
        employerId.setCompanyName("Company Name");
        employerId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId.setDescription("The characteristics of someone or something");
        employerId.setEmailContact("jane.doe@example.org");
        employerId.setEmployerId(1L);
        employerId.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerId.setLogoFilename("foo.txt");
        employerId.setNumberOfEmployees(10);
        employerId.setPhoneNumber("6625550144");
        employerId.setWebsite("Website");

        CityEntity residenceCityId = new CityEntity();
        residenceCityId.setCandidateResidenceCities(new HashSet<>());
        residenceCityId.setCityId(1L);
        residenceCityId.setCityName("Oxford");
        residenceCityId.setEmployerCities(new HashSet<>());
        residenceCityId.setOfferCities(new HashSet<>());

        CandidateEntity candidateId = new CandidateEntity();
        candidateId.setCandidateId(1L);
        candidateId.setCandidateSkills(new HashSet<>());
        candidateId.setCandidateUuid("2020-03-01");
        candidateId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        candidateId.setCvFilename("foo.txt");
        candidateId.setEducation("Education");
        candidateId.setEmailContact("jane.doe@example.org");
        candidateId.setEmployerId(employerId);
        candidateId.setExperienceLevel("Experience Level");
        candidateId.setFirstName("Jane");
        candidateId.setForeignLanguage("en");
        candidateId.setGithubLink("Github Link");
        candidateId.setHobby("Hobby");
        candidateId.setLastName("Doe");
        candidateId.setLinkedinLink("Linkedin Link");
        candidateId.setOpenToRemoteJob(true);
        candidateId.setOtherSkills("Other Skills");
        candidateId.setPhoneNumber("6625550144");
        candidateId.setResidenceCityId(residenceCityId);
        BigDecimal salaryMin = BigDecimal.valueOf(1L);
        candidateId.setSalaryMin(salaryMin);
        candidateId.setStatus("Status");
        candidateId.setYearsOfExperience(1);

        SkillEntity skillId = new SkillEntity();
        skillId.setCandidateSkills(new HashSet<>());
        skillId.setOfferSkills(new HashSet<>());
        skillId.setSkillId(1L);
        skillId.setSkillName("Skill Name");

        CityEntity cityId2 = new CityEntity();
        cityId2.setCandidateResidenceCities(new HashSet<>());
        cityId2.setCityId(1L);
        cityId2.setCityName("Oxford");
        cityId2.setEmployerCities(new HashSet<>());
        cityId2.setOfferCities(new HashSet<>());

        EmployerEntity employerId2 = new EmployerEntity();
        employerId2.setAmountOfAvailableOffers(10);
        employerId2.setCityId(cityId2);
        employerId2.setCompanyName("Company Name");
        employerId2.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId2.setDescription("The characteristics of someone or something");
        employerId2.setEmailContact("jane.doe@example.org");
        employerId2.setEmployerId(1L);
        employerId2.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerId2.setLogoFilename("foo.txt");
        employerId2.setNumberOfEmployees(10);
        employerId2.setPhoneNumber("6625550144");
        employerId2.setWebsite("Website");

        CityEntity residenceCityId2 = new CityEntity();
        residenceCityId2.setCandidateResidenceCities(new HashSet<>());
        residenceCityId2.setCityId(1L);
        residenceCityId2.setCityName("Oxford");
        residenceCityId2.setEmployerCities(new HashSet<>());
        residenceCityId2.setOfferCities(new HashSet<>());

        CandidateEntity candidateEntity = new CandidateEntity();
        candidateEntity.setCandidateId(1L);
        candidateEntity.setCandidateSkills(new HashSet<>());
        candidateEntity.setCandidateUuid("2020-03-01");
        candidateEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        candidateEntity.setCvFilename("foo.txt");
        candidateEntity.setEducation("Education");
        candidateEntity.setEmailContact("jane.doe@example.org");
        candidateEntity.setEmployerId(employerId2);
        candidateEntity.setExperienceLevel("Experience Level");
        candidateEntity.setFirstName("Jane");
        candidateEntity.setForeignLanguage("en");
        candidateEntity.setGithubLink("Github Link");
        candidateEntity.setHobby("Hobby");
        candidateEntity.setLastName("Doe");
        candidateEntity.setLinkedinLink("Linkedin Link");
        candidateEntity.setOpenToRemoteJob(true);
        candidateEntity.setOtherSkills("Other Skills");
        candidateEntity.setPhoneNumber("6625550144");
        candidateEntity.setResidenceCityId(residenceCityId2);
        candidateEntity.setSalaryMin(BigDecimal.valueOf(1L));
        candidateEntity.setStatus("Status");
        candidateEntity.setYearsOfExperience(1);

        SkillEntity skillEntity = new SkillEntity();
        skillEntity.setCandidateSkills(new HashSet<>());
        skillEntity.setOfferSkills(new HashSet<>());
        skillEntity.setSkillId(1L);
        skillEntity.setSkillName("Skill Name");
        CandidateSkillEntity candidateSkillEntity = mock(CandidateSkillEntity.class);
        when(candidateSkillEntity.getCandidateSkillId()).thenReturn(1L);
        when(candidateSkillEntity.getCandidateId()).thenReturn(candidateEntity);
        when(candidateSkillEntity.getSkillId()).thenReturn(skillEntity);
        doNothing().when(candidateSkillEntity).setCandidateId(Mockito.any());
        doNothing().when(candidateSkillEntity).setCandidateSkillId(Mockito.<Long>any());
        doNothing().when(candidateSkillEntity).setSkillId(Mockito.any());
        candidateSkillEntity.setCandidateId(candidateId);
        candidateSkillEntity.setCandidateSkillId(1L);
        candidateSkillEntity.setSkillId(skillId);
        CandidateSkill actualMapFromEntityResult = candidateSkillEntityMapperImpl.mapFromEntity(candidateSkillEntity);
        assertEquals(1L, actualMapFromEntityResult.getCandidateSkillId().longValue());
        Skill skillId2 = actualMapFromEntityResult.getSkillId();
        assertEquals("Skill Name", skillId2.getSkillName());
        assertEquals(1L, skillId2.getSkillId().longValue());
        Candidate candidateId2 = actualMapFromEntityResult.getCandidateId();
        assertEquals("Status", candidateId2.getStatus());
        BigDecimal expectedSalaryMin = BigDecimal.ONE;
        BigDecimal salaryMin2 = candidateId2.getSalaryMin();
        assertSame(expectedSalaryMin, salaryMin2);
        assertEquals(1, candidateId2.getYearsOfExperience().intValue());
        assertTrue(candidateId2.getCandidateSkills().isEmpty());
        assertEquals("Education", candidateId2.getEducation());
        assertEquals("Doe", candidateId2.getLastName());
        assertEquals("Linkedin Link", candidateId2.getLinkedinLink());
        assertNull(candidateId2.getPhotoFilename());
        assertEquals("6625550144", candidateId2.getPhoneNumber());
        assertEquals("jane.doe@example.org", candidateId2.getEmailContact());
        assertTrue(candidateId2.getOpenToRemoteJob());
        assertEquals("Other Skills", candidateId2.getOtherSkills());
        assertEquals("2020-03-01", candidateId2.getCandidateUuid());
        assertEquals("Hobby", candidateId2.getHobby());
        assertEquals("Github Link", candidateId2.getGithubLink());
        assertEquals("en", candidateId2.getForeignLanguage());
        assertEquals("Jane", candidateId2.getFirstName());
        assertEquals("Experience Level", candidateId2.getExperienceLevel());
        assertEquals(1L, candidateId2.getCandidateId().longValue());
        assertEquals("Z", candidateId2.getCreatedAt().getOffset().toString());
        assertEquals("foo.txt", candidateId2.getCvFilename());
        Employer employerId3 = candidateId2.getEmployerId();
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", employerId3.getEmployerUuid());
        assertEquals(1L, employerId3.getEmployerId().longValue());
        assertEquals("jane.doe@example.org", employerId3.getEmailContact());
        assertEquals("The characteristics of someone or something", employerId3.getDescription());
        assertEquals("Z", employerId3.getCreatedAt().getOffset().toString());
        assertEquals("Company Name", employerId3.getCompanyName());
        City residenceCityId3 = candidateId2.getResidenceCityId();
        City cityId3 = employerId3.getCityId();
        assertEquals(residenceCityId3, cityId3);
        assertEquals("Oxford", residenceCityId3.getCityName());
        assertEquals("foo.txt", employerId3.getLogoFilename());
        assertEquals(10, employerId3.getAmountOfAvailableOffers().intValue());
        assertEquals(1L, residenceCityId3.getCityId().longValue());
        assertEquals("6625550144", employerId3.getPhoneNumber());
        assertEquals("Website", employerId3.getWebsite());
        assertEquals("1", salaryMin2.toString());
        assertEquals(10, employerId3.getNumberOfEmployees().intValue());
        assertEquals(1L, cityId3.getCityId().longValue());
        assertEquals("Oxford", cityId3.getCityName());
        verify(candidateSkillEntity).getCandidateSkillId();
        verify(candidateSkillEntity).getCandidateId();
        verify(candidateSkillEntity).getSkillId();
        verify(candidateSkillEntity).setCandidateId(Mockito.any());
        verify(candidateSkillEntity).setCandidateSkillId(Mockito.<Long>any());
        verify(candidateSkillEntity).setSkillId(Mockito.any());
    }

    /**
     * Method under test: {@link CandidateSkillEntityMapperImpl#mapFromEntity(CandidateSkillEntity)}
     */
    @Test
    void testMapFromEntity3() {
        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        EmployerEntity employerId = new EmployerEntity();
        employerId.setAmountOfAvailableOffers(10);
        employerId.setCityId(cityId);
        employerId.setCompanyName("Company Name");
        employerId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId.setDescription("The characteristics of someone or something");
        employerId.setEmailContact("jane.doe@example.org");
        employerId.setEmployerId(1L);
        employerId.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerId.setLogoFilename("foo.txt");
        employerId.setNumberOfEmployees(10);
        employerId.setPhoneNumber("6625550144");
        employerId.setWebsite("Website");

        CityEntity residenceCityId = new CityEntity();
        residenceCityId.setCandidateResidenceCities(new HashSet<>());
        residenceCityId.setCityId(1L);
        residenceCityId.setCityName("Oxford");
        residenceCityId.setEmployerCities(new HashSet<>());
        residenceCityId.setOfferCities(new HashSet<>());

        CandidateEntity candidateId = new CandidateEntity();
        candidateId.setCandidateId(1L);
        candidateId.setCandidateSkills(new HashSet<>());
        candidateId.setCandidateUuid("2020-03-01");
        candidateId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        candidateId.setCvFilename("foo.txt");
        candidateId.setEducation("Education");
        candidateId.setEmailContact("jane.doe@example.org");
        candidateId.setEmployerId(employerId);
        candidateId.setExperienceLevel("Experience Level");
        candidateId.setFirstName("Jane");
        candidateId.setForeignLanguage("en");
        candidateId.setGithubLink("Github Link");
        candidateId.setHobby("Hobby");
        candidateId.setLastName("Doe");
        candidateId.setLinkedinLink("Linkedin Link");
        candidateId.setOpenToRemoteJob(true);
        candidateId.setOtherSkills("Other Skills");
        candidateId.setPhoneNumber("6625550144");
        candidateId.setResidenceCityId(residenceCityId);
        BigDecimal salaryMin = BigDecimal.valueOf(1L);
        candidateId.setSalaryMin(salaryMin);
        candidateId.setStatus("Status");
        candidateId.setYearsOfExperience(1);

        SkillEntity skillId = new SkillEntity();
        skillId.setCandidateSkills(new HashSet<>());
        skillId.setOfferSkills(new HashSet<>());
        skillId.setSkillId(1L);
        skillId.setSkillName("Skill Name");

        CityEntity cityId2 = new CityEntity();
        cityId2.setCandidateResidenceCities(new HashSet<>());
        cityId2.setCityId(1L);
        cityId2.setCityName("Oxford");
        cityId2.setEmployerCities(new HashSet<>());
        cityId2.setOfferCities(new HashSet<>());

        EmployerEntity employerId2 = new EmployerEntity();
        employerId2.setAmountOfAvailableOffers(10);
        employerId2.setCityId(cityId2);
        employerId2.setCompanyName("Company Name");
        employerId2.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId2.setDescription("The characteristics of someone or something");
        employerId2.setEmailContact("jane.doe@example.org");
        employerId2.setEmployerId(1L);
        employerId2.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerId2.setLogoFilename("foo.txt");
        employerId2.setNumberOfEmployees(10);
        employerId2.setPhoneNumber("6625550144");
        employerId2.setWebsite("Website");

        CityEntity residenceCityId2 = new CityEntity();
        residenceCityId2.setCandidateResidenceCities(new HashSet<>());
        residenceCityId2.setCityId(1L);
        residenceCityId2.setCityName("Oxford");
        residenceCityId2.setEmployerCities(new HashSet<>());
        residenceCityId2.setOfferCities(new HashSet<>());

        CityEntity cityEntity = new CityEntity();
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());

        CityEntity cityId3 = new CityEntity();
        cityId3.setCandidateResidenceCities(new HashSet<>());
        cityId3.setCityId(1L);
        cityId3.setCityName("Oxford");
        cityId3.setEmployerCities(new HashSet<>());
        cityId3.setOfferCities(new HashSet<>());

        EmployerEntity employerEntity = new EmployerEntity();
        employerEntity.setAmountOfAvailableOffers(10);
        employerEntity.setCityId(cityId3);
        employerEntity.setCompanyName("Company Name");
        employerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerEntity.setDescription("The characteristics of someone or something");
        employerEntity.setEmailContact("jane.doe@example.org");
        employerEntity.setEmployerId(1L);
        employerEntity.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerEntity.setLogoFilename("foo.txt");
        employerEntity.setNumberOfEmployees(10);
        employerEntity.setPhoneNumber("6625550144");
        employerEntity.setWebsite("Website");
        CandidateEntity candidateEntity = mock(CandidateEntity.class);
        when(candidateEntity.getOpenToRemoteJob()).thenReturn(true);
        when(candidateEntity.getYearsOfExperience()).thenReturn(1);
        when(candidateEntity.getCandidateId()).thenReturn(1L);
        when(candidateEntity.getCandidateUuid()).thenReturn("2020-03-01");
        when(candidateEntity.getCvFilename()).thenReturn("foo.txt");
        when(candidateEntity.getEducation()).thenReturn("Education");
        when(candidateEntity.getEmailContact()).thenReturn("jane.doe@example.org");
        when(candidateEntity.getExperienceLevel()).thenReturn("Experience Level");
        when(candidateEntity.getFirstName()).thenReturn("Jane");
        when(candidateEntity.getForeignLanguage()).thenReturn("en");
        when(candidateEntity.getGithubLink()).thenReturn("Github Link");
        when(candidateEntity.getHobby()).thenReturn("Hobby");
        when(candidateEntity.getLastName()).thenReturn("Doe");
        when(candidateEntity.getLinkedinLink()).thenReturn("Linkedin Link");
        when(candidateEntity.getOtherSkills()).thenReturn("Other Skills");
        when(candidateEntity.getPhoneNumber()).thenReturn("6625550144");
        when(candidateEntity.getPhotoFilename()).thenReturn("foo.txt");
        when(candidateEntity.getStatus()).thenReturn("Status");
        when(candidateEntity.getSalaryMin()).thenReturn(BigDecimal.valueOf(1L));
        when(candidateEntity.getCreatedAt())
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        when(candidateEntity.getCandidateSkills()).thenReturn(new HashSet<>());
        when(candidateEntity.getResidenceCityId()).thenReturn(cityEntity);
        when(candidateEntity.getEmployerId()).thenReturn(employerEntity);
        doNothing().when(candidateEntity).setCandidateId(Mockito.<Long>any());
        doNothing().when(candidateEntity).setCandidateSkills(Mockito.any());
        doNothing().when(candidateEntity).setCandidateUuid(Mockito.any());
        doNothing().when(candidateEntity).setCreatedAt(Mockito.any());
        doNothing().when(candidateEntity).setCvFilename(Mockito.any());
        doNothing().when(candidateEntity).setEducation(Mockito.any());
        doNothing().when(candidateEntity).setEmailContact(Mockito.any());
        doNothing().when(candidateEntity).setEmployerId(Mockito.any());
        doNothing().when(candidateEntity).setExperienceLevel(Mockito.any());
        doNothing().when(candidateEntity).setFirstName(Mockito.any());
        doNothing().when(candidateEntity).setForeignLanguage(Mockito.any());
        doNothing().when(candidateEntity).setGithubLink(Mockito.any());
        doNothing().when(candidateEntity).setHobby(Mockito.any());
        doNothing().when(candidateEntity).setLastName(Mockito.any());
        doNothing().when(candidateEntity).setLinkedinLink(Mockito.any());
        doNothing().when(candidateEntity).setOpenToRemoteJob(Mockito.<Boolean>any());
        doNothing().when(candidateEntity).setOtherSkills(Mockito.any());
        doNothing().when(candidateEntity).setPhoneNumber(Mockito.any());
        doNothing().when(candidateEntity).setResidenceCityId(Mockito.any());
        doNothing().when(candidateEntity).setSalaryMin(Mockito.any());
        doNothing().when(candidateEntity).setStatus(Mockito.any());
        doNothing().when(candidateEntity).setYearsOfExperience(Mockito.<Integer>any());
        candidateEntity.setCandidateId(1L);
        candidateEntity.setCandidateSkills(new HashSet<>());
        candidateEntity.setCandidateUuid("2020-03-01");
        candidateEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        candidateEntity.setCvFilename("foo.txt");
        candidateEntity.setEducation("Education");
        candidateEntity.setEmailContact("jane.doe@example.org");
        candidateEntity.setEmployerId(employerId2);
        candidateEntity.setExperienceLevel("Experience Level");
        candidateEntity.setFirstName("Jane");
        candidateEntity.setForeignLanguage("en");
        candidateEntity.setGithubLink("Github Link");
        candidateEntity.setHobby("Hobby");
        candidateEntity.setLastName("Doe");
        candidateEntity.setLinkedinLink("Linkedin Link");
        candidateEntity.setOpenToRemoteJob(true);
        candidateEntity.setOtherSkills("Other Skills");
        candidateEntity.setPhoneNumber("6625550144");
        candidateEntity.setResidenceCityId(residenceCityId2);
        candidateEntity.setSalaryMin(BigDecimal.valueOf(1L));
        candidateEntity.setStatus("Status");
        candidateEntity.setYearsOfExperience(1);

        SkillEntity skillEntity = new SkillEntity();
        skillEntity.setCandidateSkills(new HashSet<>());
        skillEntity.setOfferSkills(new HashSet<>());
        skillEntity.setSkillId(1L);
        skillEntity.setSkillName("Skill Name");
        CandidateSkillEntity candidateSkillEntity = mock(CandidateSkillEntity.class);
        when(candidateSkillEntity.getCandidateSkillId()).thenReturn(1L);
        when(candidateSkillEntity.getCandidateId()).thenReturn(candidateEntity);
        when(candidateSkillEntity.getSkillId()).thenReturn(skillEntity);
        doNothing().when(candidateSkillEntity).setCandidateId(Mockito.any());
        doNothing().when(candidateSkillEntity).setCandidateSkillId(Mockito.<Long>any());
        doNothing().when(candidateSkillEntity).setSkillId(Mockito.any());
        candidateSkillEntity.setCandidateId(candidateId);
        candidateSkillEntity.setCandidateSkillId(1L);
        candidateSkillEntity.setSkillId(skillId);
        CandidateSkill actualMapFromEntityResult = candidateSkillEntityMapperImpl.mapFromEntity(candidateSkillEntity);
        assertEquals(1L, actualMapFromEntityResult.getCandidateSkillId().longValue());
        Skill skillId2 = actualMapFromEntityResult.getSkillId();
        assertEquals("Skill Name", skillId2.getSkillName());
        assertEquals(1L, skillId2.getSkillId().longValue());
        Candidate candidateId2 = actualMapFromEntityResult.getCandidateId();
        assertEquals("Status", candidateId2.getStatus());
        BigDecimal expectedSalaryMin = BigDecimal.ONE;
        BigDecimal salaryMin2 = candidateId2.getSalaryMin();
        assertSame(expectedSalaryMin, salaryMin2);
        assertEquals(1, candidateId2.getYearsOfExperience().intValue());
        assertTrue(candidateId2.getCandidateSkills().isEmpty());
        assertEquals("Education", candidateId2.getEducation());
        assertEquals("Doe", candidateId2.getLastName());
        assertEquals("Linkedin Link", candidateId2.getLinkedinLink());
        assertEquals("foo.txt", candidateId2.getPhotoFilename());
        assertEquals("6625550144", candidateId2.getPhoneNumber());
        assertEquals("jane.doe@example.org", candidateId2.getEmailContact());
        assertTrue(candidateId2.getOpenToRemoteJob());
        assertEquals("Other Skills", candidateId2.getOtherSkills());
        assertEquals("2020-03-01", candidateId2.getCandidateUuid());
        assertEquals("Hobby", candidateId2.getHobby());
        assertEquals("Github Link", candidateId2.getGithubLink());
        assertEquals("en", candidateId2.getForeignLanguage());
        assertEquals("Jane", candidateId2.getFirstName());
        assertEquals("Experience Level", candidateId2.getExperienceLevel());
        assertEquals(1L, candidateId2.getCandidateId().longValue());
        assertEquals("Z", candidateId2.getCreatedAt().getOffset().toString());
        assertEquals("foo.txt", candidateId2.getCvFilename());
        Employer employerId3 = candidateId2.getEmployerId();
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", employerId3.getEmployerUuid());
        assertEquals(1L, employerId3.getEmployerId().longValue());
        assertEquals("jane.doe@example.org", employerId3.getEmailContact());
        assertEquals("The characteristics of someone or something", employerId3.getDescription());
        assertEquals("Z", employerId3.getCreatedAt().getOffset().toString());
        assertEquals("Company Name", employerId3.getCompanyName());
        City residenceCityId3 = candidateId2.getResidenceCityId();
        City cityId4 = employerId3.getCityId();
        assertEquals(residenceCityId3, cityId4);
        assertEquals("Oxford", residenceCityId3.getCityName());
        assertEquals("foo.txt", employerId3.getLogoFilename());
        assertEquals(10, employerId3.getAmountOfAvailableOffers().intValue());
        assertEquals(1L, residenceCityId3.getCityId().longValue());
        assertEquals("6625550144", employerId3.getPhoneNumber());
        assertEquals("Website", employerId3.getWebsite());
        assertEquals("1", salaryMin2.toString());
        assertEquals(10, employerId3.getNumberOfEmployees().intValue());
        assertEquals(1L, cityId4.getCityId().longValue());
        assertEquals("Oxford", cityId4.getCityName());
        verify(candidateSkillEntity).getCandidateSkillId();
        verify(candidateSkillEntity).getCandidateId();
        verify(candidateSkillEntity).getSkillId();
        verify(candidateSkillEntity).setCandidateId(Mockito.any());
        verify(candidateSkillEntity).setCandidateSkillId(Mockito.<Long>any());
        verify(candidateSkillEntity).setSkillId(Mockito.any());
        verify(candidateEntity).getOpenToRemoteJob();
        verify(candidateEntity).getYearsOfExperience();
        verify(candidateEntity).getCandidateId();
        verify(candidateEntity).getCandidateUuid();
        verify(candidateEntity).getCvFilename();
        verify(candidateEntity).getEducation();
        verify(candidateEntity).getEmailContact();
        verify(candidateEntity).getExperienceLevel();
        verify(candidateEntity).getFirstName();
        verify(candidateEntity).getForeignLanguage();
        verify(candidateEntity).getGithubLink();
        verify(candidateEntity).getHobby();
        verify(candidateEntity).getLastName();
        verify(candidateEntity).getLinkedinLink();
        verify(candidateEntity).getOtherSkills();
        verify(candidateEntity).getPhoneNumber();
        verify(candidateEntity).getPhotoFilename();
        verify(candidateEntity).getStatus();
        verify(candidateEntity).getSalaryMin();
        verify(candidateEntity).getCreatedAt();
        verify(candidateEntity).getCandidateSkills();
        verify(candidateEntity).getResidenceCityId();
        verify(candidateEntity).getEmployerId();
        verify(candidateEntity).setCandidateId(Mockito.<Long>any());
        verify(candidateEntity).setCandidateSkills(Mockito.any());
        verify(candidateEntity).setCandidateUuid(Mockito.any());
        verify(candidateEntity).setCreatedAt(Mockito.any());
        verify(candidateEntity).setCvFilename(Mockito.any());
        verify(candidateEntity).setEducation(Mockito.any());
        verify(candidateEntity).setEmailContact(Mockito.any());
        verify(candidateEntity).setEmployerId(Mockito.any());
        verify(candidateEntity).setExperienceLevel(Mockito.any());
        verify(candidateEntity).setFirstName(Mockito.any());
        verify(candidateEntity).setForeignLanguage(Mockito.any());
        verify(candidateEntity).setGithubLink(Mockito.any());
        verify(candidateEntity).setHobby(Mockito.any());
        verify(candidateEntity).setLastName(Mockito.any());
        verify(candidateEntity).setLinkedinLink(Mockito.any());
        verify(candidateEntity).setOpenToRemoteJob(Mockito.<Boolean>any());
        verify(candidateEntity).setOtherSkills(Mockito.any());
        verify(candidateEntity).setPhoneNumber(Mockito.any());
        verify(candidateEntity).setResidenceCityId(Mockito.any());
        verify(candidateEntity).setSalaryMin(Mockito.any());
        verify(candidateEntity).setStatus(Mockito.any());
        verify(candidateEntity).setYearsOfExperience(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link CandidateSkillEntityMapperImpl#mapFromEntity(CandidateSkillEntity)}
     */
    @Test
    void testMapFromEntity4() {
        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        EmployerEntity employerId = new EmployerEntity();
        employerId.setAmountOfAvailableOffers(10);
        employerId.setCityId(cityId);
        employerId.setCompanyName("Company Name");
        employerId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId.setDescription("The characteristics of someone or something");
        employerId.setEmailContact("jane.doe@example.org");
        employerId.setEmployerId(1L);
        employerId.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerId.setLogoFilename("foo.txt");
        employerId.setNumberOfEmployees(10);
        employerId.setPhoneNumber("6625550144");
        employerId.setWebsite("Website");

        CityEntity residenceCityId = new CityEntity();
        residenceCityId.setCandidateResidenceCities(new HashSet<>());
        residenceCityId.setCityId(1L);
        residenceCityId.setCityName("Oxford");
        residenceCityId.setEmployerCities(new HashSet<>());
        residenceCityId.setOfferCities(new HashSet<>());

        CandidateEntity candidateId = new CandidateEntity();
        candidateId.setCandidateId(1L);
        candidateId.setCandidateSkills(new HashSet<>());
        candidateId.setCandidateUuid("2020-03-01");
        candidateId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        candidateId.setCvFilename("foo.txt");
        candidateId.setEducation("Education");
        candidateId.setEmailContact("jane.doe@example.org");
        candidateId.setEmployerId(employerId);
        candidateId.setExperienceLevel("Experience Level");
        candidateId.setFirstName("Jane");
        candidateId.setForeignLanguage("en");
        candidateId.setGithubLink("Github Link");
        candidateId.setHobby("Hobby");
        candidateId.setLastName("Doe");
        candidateId.setLinkedinLink("Linkedin Link");
        candidateId.setOpenToRemoteJob(true);
        candidateId.setOtherSkills("Other Skills");
        candidateId.setPhoneNumber("6625550144");
        candidateId.setResidenceCityId(residenceCityId);
        BigDecimal salaryMin = BigDecimal.valueOf(1L);
        candidateId.setSalaryMin(salaryMin);
        candidateId.setStatus("Status");
        candidateId.setYearsOfExperience(1);

        SkillEntity skillId = new SkillEntity();
        skillId.setCandidateSkills(new HashSet<>());
        skillId.setOfferSkills(new HashSet<>());
        skillId.setSkillId(1L);
        skillId.setSkillName("Skill Name");

        CityEntity cityId2 = new CityEntity();
        cityId2.setCandidateResidenceCities(new HashSet<>());
        cityId2.setCityId(1L);
        cityId2.setCityName("Oxford");
        cityId2.setEmployerCities(new HashSet<>());
        cityId2.setOfferCities(new HashSet<>());

        EmployerEntity employerId2 = new EmployerEntity();
        employerId2.setAmountOfAvailableOffers(10);
        employerId2.setCityId(cityId2);
        employerId2.setCompanyName("Company Name");
        employerId2.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId2.setDescription("The characteristics of someone or something");
        employerId2.setEmailContact("jane.doe@example.org");
        employerId2.setEmployerId(1L);
        employerId2.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerId2.setLogoFilename("foo.txt");
        employerId2.setNumberOfEmployees(10);
        employerId2.setPhoneNumber("6625550144");
        employerId2.setWebsite("Website");

        CityEntity residenceCityId2 = new CityEntity();
        residenceCityId2.setCandidateResidenceCities(new HashSet<>());
        residenceCityId2.setCityId(1L);
        residenceCityId2.setCityName("Oxford");
        residenceCityId2.setEmployerCities(new HashSet<>());
        residenceCityId2.setOfferCities(new HashSet<>());

        CityEntity cityId3 = new CityEntity();
        cityId3.setCandidateResidenceCities(new HashSet<>());
        cityId3.setCityId(1L);
        cityId3.setCityName("Oxford");
        cityId3.setEmployerCities(new HashSet<>());
        cityId3.setOfferCities(new HashSet<>());

        EmployerEntity employerId3 = new EmployerEntity();
        employerId3.setAmountOfAvailableOffers(10);
        employerId3.setCityId(cityId3);
        employerId3.setCompanyName("Company Name");
        employerId3.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId3.setDescription("The characteristics of someone or something");
        employerId3.setEmailContact("jane.doe@example.org");
        employerId3.setEmployerId(1L);
        employerId3.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerId3.setLogoFilename("foo.txt");
        employerId3.setNumberOfEmployees(10);
        employerId3.setPhoneNumber("6625550144");
        employerId3.setWebsite("Website");

        CityEntity residenceCityId3 = new CityEntity();
        residenceCityId3.setCandidateResidenceCities(new HashSet<>());
        residenceCityId3.setCityId(1L);
        residenceCityId3.setCityName("Oxford");
        residenceCityId3.setEmployerCities(new HashSet<>());
        residenceCityId3.setOfferCities(new HashSet<>());

        CandidateEntity candidateId2 = new CandidateEntity();
        candidateId2.setCandidateId(1L);
        candidateId2.setCandidateSkills(new HashSet<>());
        candidateId2.setCandidateUuid("2020-03-01");
        candidateId2.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        candidateId2.setCvFilename("foo.txt");
        candidateId2.setEducation("Education");
        candidateId2.setEmailContact("jane.doe@example.org");
        candidateId2.setEmployerId(employerId3);
        candidateId2.setExperienceLevel("Experience Level");
        candidateId2.setFirstName("Jane");
        candidateId2.setForeignLanguage("en");
        candidateId2.setGithubLink("Github Link");
        candidateId2.setHobby("Hobby");
        candidateId2.setLastName("Doe");
        candidateId2.setLinkedinLink("Linkedin Link");
        candidateId2.setOpenToRemoteJob(true);
        candidateId2.setOtherSkills("Other Skills");
        candidateId2.setPhoneNumber("6625550144");
        candidateId2.setResidenceCityId(residenceCityId3);
        candidateId2.setSalaryMin(BigDecimal.valueOf(1L));
        candidateId2.setStatus("Status");
        candidateId2.setYearsOfExperience(1);

        SkillEntity skillId2 = new SkillEntity();
        skillId2.setCandidateSkills(new HashSet<>());
        skillId2.setOfferSkills(new HashSet<>());
        skillId2.setSkillId(1L);
        skillId2.setSkillName("Skill Name");

        CandidateSkillEntity candidateSkillEntity = new CandidateSkillEntity();
        candidateSkillEntity.setCandidateId(candidateId2);
        candidateSkillEntity.setCandidateSkillId(1L);
        candidateSkillEntity.setSkillId(skillId2);

        HashSet<CandidateSkillEntity> candidateSkillEntitySet = new HashSet<>();
        candidateSkillEntitySet.add(candidateSkillEntity);

        CityEntity cityEntity = new CityEntity();
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());

        CityEntity cityId4 = new CityEntity();
        cityId4.setCandidateResidenceCities(new HashSet<>());
        cityId4.setCityId(1L);
        cityId4.setCityName("Oxford");
        cityId4.setEmployerCities(new HashSet<>());
        cityId4.setOfferCities(new HashSet<>());

        EmployerEntity employerEntity = new EmployerEntity();
        employerEntity.setAmountOfAvailableOffers(10);
        employerEntity.setCityId(cityId4);
        employerEntity.setCompanyName("Company Name");
        employerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerEntity.setDescription("The characteristics of someone or something");
        employerEntity.setEmailContact("jane.doe@example.org");
        employerEntity.setEmployerId(1L);
        employerEntity.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerEntity.setLogoFilename("foo.txt");
        employerEntity.setNumberOfEmployees(10);
        employerEntity.setPhoneNumber("6625550144");
        employerEntity.setWebsite("Website");
        CandidateEntity candidateEntity = mock(CandidateEntity.class);
        when(candidateEntity.getOpenToRemoteJob()).thenReturn(true);
        when(candidateEntity.getYearsOfExperience()).thenReturn(1);
        when(candidateEntity.getCandidateId()).thenReturn(1L);
        when(candidateEntity.getCandidateUuid()).thenReturn("2020-03-01");
        when(candidateEntity.getCvFilename()).thenReturn("foo.txt");
        when(candidateEntity.getEducation()).thenReturn("Education");
        when(candidateEntity.getEmailContact()).thenReturn("jane.doe@example.org");
        when(candidateEntity.getExperienceLevel()).thenReturn("Experience Level");
        when(candidateEntity.getFirstName()).thenReturn("Jane");
        when(candidateEntity.getForeignLanguage()).thenReturn("en");
        when(candidateEntity.getGithubLink()).thenReturn("Github Link");
        when(candidateEntity.getHobby()).thenReturn("Hobby");
        when(candidateEntity.getLastName()).thenReturn("Doe");
        when(candidateEntity.getLinkedinLink()).thenReturn("Linkedin Link");
        when(candidateEntity.getOtherSkills()).thenReturn("Other Skills");
        when(candidateEntity.getPhoneNumber()).thenReturn("6625550144");
        when(candidateEntity.getPhotoFilename()).thenReturn("foo.txt");
        when(candidateEntity.getStatus()).thenReturn("Status");
        when(candidateEntity.getSalaryMin()).thenReturn(BigDecimal.valueOf(1L));
        when(candidateEntity.getCreatedAt())
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        when(candidateEntity.getCandidateSkills()).thenReturn(candidateSkillEntitySet);
        when(candidateEntity.getResidenceCityId()).thenReturn(cityEntity);
        when(candidateEntity.getEmployerId()).thenReturn(employerEntity);
        doNothing().when(candidateEntity).setCandidateId(Mockito.<Long>any());
        doNothing().when(candidateEntity).setCandidateSkills(Mockito.any());
        doNothing().when(candidateEntity).setCandidateUuid(Mockito.any());
        doNothing().when(candidateEntity).setCreatedAt(Mockito.any());
        doNothing().when(candidateEntity).setCvFilename(Mockito.any());
        doNothing().when(candidateEntity).setEducation(Mockito.any());
        doNothing().when(candidateEntity).setEmailContact(Mockito.any());
        doNothing().when(candidateEntity).setEmployerId(Mockito.any());
        doNothing().when(candidateEntity).setExperienceLevel(Mockito.any());
        doNothing().when(candidateEntity).setFirstName(Mockito.any());
        doNothing().when(candidateEntity).setForeignLanguage(Mockito.any());
        doNothing().when(candidateEntity).setGithubLink(Mockito.any());
        doNothing().when(candidateEntity).setHobby(Mockito.any());
        doNothing().when(candidateEntity).setLastName(Mockito.any());
        doNothing().when(candidateEntity).setLinkedinLink(Mockito.any());
        doNothing().when(candidateEntity).setOpenToRemoteJob(Mockito.<Boolean>any());
        doNothing().when(candidateEntity).setOtherSkills(Mockito.any());
        doNothing().when(candidateEntity).setPhoneNumber(Mockito.any());
        doNothing().when(candidateEntity).setResidenceCityId(Mockito.any());
        doNothing().when(candidateEntity).setSalaryMin(Mockito.any());
        doNothing().when(candidateEntity).setStatus(Mockito.any());
        doNothing().when(candidateEntity).setYearsOfExperience(Mockito.<Integer>any());
        candidateEntity.setCandidateId(1L);
        candidateEntity.setCandidateSkills(new HashSet<>());
        candidateEntity.setCandidateUuid("2020-03-01");
        candidateEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        candidateEntity.setCvFilename("foo.txt");
        candidateEntity.setEducation("Education");
        candidateEntity.setEmailContact("jane.doe@example.org");
        candidateEntity.setEmployerId(employerId2);
        candidateEntity.setExperienceLevel("Experience Level");
        candidateEntity.setFirstName("Jane");
        candidateEntity.setForeignLanguage("en");
        candidateEntity.setGithubLink("Github Link");
        candidateEntity.setHobby("Hobby");
        candidateEntity.setLastName("Doe");
        candidateEntity.setLinkedinLink("Linkedin Link");
        candidateEntity.setOpenToRemoteJob(true);
        candidateEntity.setOtherSkills("Other Skills");
        candidateEntity.setPhoneNumber("6625550144");
        candidateEntity.setResidenceCityId(residenceCityId2);
        candidateEntity.setSalaryMin(BigDecimal.valueOf(1L));
        candidateEntity.setStatus("Status");
        candidateEntity.setYearsOfExperience(1);

        SkillEntity skillEntity = new SkillEntity();
        skillEntity.setCandidateSkills(new HashSet<>());
        skillEntity.setOfferSkills(new HashSet<>());
        skillEntity.setSkillId(1L);
        skillEntity.setSkillName("Skill Name");
        CandidateSkillEntity candidateSkillEntity2 = mock(CandidateSkillEntity.class);
        when(candidateSkillEntity2.getCandidateSkillId()).thenReturn(1L);
        when(candidateSkillEntity2.getCandidateId()).thenReturn(candidateEntity);
        when(candidateSkillEntity2.getSkillId()).thenReturn(skillEntity);
        doNothing().when(candidateSkillEntity2).setCandidateId(Mockito.any());
        doNothing().when(candidateSkillEntity2).setCandidateSkillId(Mockito.<Long>any());
        doNothing().when(candidateSkillEntity2).setSkillId(Mockito.any());
        candidateSkillEntity2.setCandidateId(candidateId);
        candidateSkillEntity2.setCandidateSkillId(1L);
        candidateSkillEntity2.setSkillId(skillId);
        CandidateSkill actualMapFromEntityResult = candidateSkillEntityMapperImpl.mapFromEntity(candidateSkillEntity2);
        assertEquals(1L, actualMapFromEntityResult.getCandidateSkillId().longValue());
        Skill skillId3 = actualMapFromEntityResult.getSkillId();
        assertEquals("Skill Name", skillId3.getSkillName());
        assertEquals(1L, skillId3.getSkillId().longValue());
        Candidate candidateId3 = actualMapFromEntityResult.getCandidateId();
        assertEquals("Status", candidateId3.getStatus());
        BigDecimal expectedSalaryMin = BigDecimal.ONE;
        BigDecimal salaryMin2 = candidateId3.getSalaryMin();
        assertSame(expectedSalaryMin, salaryMin2);
        assertEquals(1, candidateId3.getYearsOfExperience().intValue());
        assertEquals(1, candidateId3.getCandidateSkills().size());
        assertEquals("Education", candidateId3.getEducation());
        assertEquals("Doe", candidateId3.getLastName());
        assertEquals("Linkedin Link", candidateId3.getLinkedinLink());
        assertEquals("foo.txt", candidateId3.getPhotoFilename());
        assertEquals("6625550144", candidateId3.getPhoneNumber());
        assertEquals("jane.doe@example.org", candidateId3.getEmailContact());
        assertTrue(candidateId3.getOpenToRemoteJob());
        assertEquals("Other Skills", candidateId3.getOtherSkills());
        assertEquals("2020-03-01", candidateId3.getCandidateUuid());
        assertEquals("Hobby", candidateId3.getHobby());
        assertEquals("Github Link", candidateId3.getGithubLink());
        assertEquals("en", candidateId3.getForeignLanguage());
        assertEquals("Jane", candidateId3.getFirstName());
        assertEquals("Experience Level", candidateId3.getExperienceLevel());
        assertEquals(1L, candidateId3.getCandidateId().longValue());
        assertEquals("Z", candidateId3.getCreatedAt().getOffset().toString());
        assertEquals("foo.txt", candidateId3.getCvFilename());
        Employer employerId4 = candidateId3.getEmployerId();
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", employerId4.getEmployerUuid());
        assertEquals(1L, employerId4.getEmployerId().longValue());
        assertEquals("jane.doe@example.org", employerId4.getEmailContact());
        assertEquals("The characteristics of someone or something", employerId4.getDescription());
        assertEquals("Z", employerId4.getCreatedAt().getOffset().toString());
        assertEquals("Company Name", employerId4.getCompanyName());
        City residenceCityId4 = candidateId3.getResidenceCityId();
        City cityId5 = employerId4.getCityId();
        assertEquals(residenceCityId4, cityId5);
        assertEquals("Oxford", residenceCityId4.getCityName());
        assertEquals("foo.txt", employerId4.getLogoFilename());
        assertEquals(10, employerId4.getAmountOfAvailableOffers().intValue());
        assertEquals(1L, residenceCityId4.getCityId().longValue());
        assertEquals("6625550144", employerId4.getPhoneNumber());
        assertEquals("Website", employerId4.getWebsite());
        assertEquals("1", salaryMin2.toString());
        assertEquals(10, employerId4.getNumberOfEmployees().intValue());
        assertEquals(1L, cityId5.getCityId().longValue());
        assertEquals("Oxford", cityId5.getCityName());
        verify(candidateSkillEntity2).getCandidateSkillId();
        verify(candidateSkillEntity2).getCandidateId();
        verify(candidateSkillEntity2).getSkillId();
        verify(candidateSkillEntity2).setCandidateId(Mockito.any());
        verify(candidateSkillEntity2).setCandidateSkillId(Mockito.<Long>any());
        verify(candidateSkillEntity2).setSkillId(Mockito.any());
        verify(candidateEntity).getOpenToRemoteJob();
        verify(candidateEntity).getYearsOfExperience();
        verify(candidateEntity).getCandidateId();
        verify(candidateEntity).getCandidateUuid();
        verify(candidateEntity).getCvFilename();
        verify(candidateEntity).getEducation();
        verify(candidateEntity).getEmailContact();
        verify(candidateEntity).getExperienceLevel();
        verify(candidateEntity).getFirstName();
        verify(candidateEntity).getForeignLanguage();
        verify(candidateEntity).getGithubLink();
        verify(candidateEntity).getHobby();
        verify(candidateEntity).getLastName();
        verify(candidateEntity).getLinkedinLink();
        verify(candidateEntity).getOtherSkills();
        verify(candidateEntity).getPhoneNumber();
        verify(candidateEntity).getPhotoFilename();
        verify(candidateEntity).getStatus();
        verify(candidateEntity).getSalaryMin();
        verify(candidateEntity).getCreatedAt();
        verify(candidateEntity).getCandidateSkills();
        verify(candidateEntity).getResidenceCityId();
        verify(candidateEntity).getEmployerId();
        verify(candidateEntity).setCandidateId(Mockito.<Long>any());
        verify(candidateEntity).setCandidateSkills(Mockito.any());
        verify(candidateEntity).setCandidateUuid(Mockito.any());
        verify(candidateEntity).setCreatedAt(Mockito.any());
        verify(candidateEntity).setCvFilename(Mockito.any());
        verify(candidateEntity).setEducation(Mockito.any());
        verify(candidateEntity).setEmailContact(Mockito.any());
        verify(candidateEntity).setEmployerId(Mockito.any());
        verify(candidateEntity).setExperienceLevel(Mockito.any());
        verify(candidateEntity).setFirstName(Mockito.any());
        verify(candidateEntity).setForeignLanguage(Mockito.any());
        verify(candidateEntity).setGithubLink(Mockito.any());
        verify(candidateEntity).setHobby(Mockito.any());
        verify(candidateEntity).setLastName(Mockito.any());
        verify(candidateEntity).setLinkedinLink(Mockito.any());
        verify(candidateEntity).setOpenToRemoteJob(Mockito.<Boolean>any());
        verify(candidateEntity).setOtherSkills(Mockito.any());
        verify(candidateEntity).setPhoneNumber(Mockito.any());
        verify(candidateEntity).setResidenceCityId(Mockito.any());
        verify(candidateEntity).setSalaryMin(Mockito.any());
        verify(candidateEntity).setStatus(Mockito.any());
        verify(candidateEntity).setYearsOfExperience(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link CandidateSkillEntityMapperImpl#mapFromEntity(CandidateSkillEntity)}
     */
    @Test
    void testMapFromEntity5() {
        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        EmployerEntity employerId = new EmployerEntity();
        employerId.setAmountOfAvailableOffers(10);
        employerId.setCityId(cityId);
        employerId.setCompanyName("Company Name");
        employerId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId.setDescription("The characteristics of someone or something");
        employerId.setEmailContact("jane.doe@example.org");
        employerId.setEmployerId(1L);
        employerId.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerId.setLogoFilename("foo.txt");
        employerId.setNumberOfEmployees(10);
        employerId.setPhoneNumber("6625550144");
        employerId.setWebsite("Website");

        CityEntity residenceCityId = new CityEntity();
        residenceCityId.setCandidateResidenceCities(new HashSet<>());
        residenceCityId.setCityId(1L);
        residenceCityId.setCityName("Oxford");
        residenceCityId.setEmployerCities(new HashSet<>());
        residenceCityId.setOfferCities(new HashSet<>());

        CandidateEntity candidateId = new CandidateEntity();
        candidateId.setCandidateId(1L);
        candidateId.setCandidateSkills(new HashSet<>());
        candidateId.setCandidateUuid("2020-03-01");
        candidateId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        candidateId.setCvFilename("foo.txt");
        candidateId.setEducation("Education");
        candidateId.setEmailContact("jane.doe@example.org");
        candidateId.setEmployerId(employerId);
        candidateId.setExperienceLevel("Experience Level");
        candidateId.setFirstName("Jane");
        candidateId.setForeignLanguage("en");
        candidateId.setGithubLink("Github Link");
        candidateId.setHobby("Hobby");
        candidateId.setLastName("Doe");
        candidateId.setLinkedinLink("Linkedin Link");
        candidateId.setOpenToRemoteJob(true);
        candidateId.setOtherSkills("Other Skills");
        candidateId.setPhoneNumber("6625550144");
        candidateId.setResidenceCityId(residenceCityId);
        BigDecimal salaryMin = BigDecimal.valueOf(1L);
        candidateId.setSalaryMin(salaryMin);
        candidateId.setStatus("Status");
        candidateId.setYearsOfExperience(1);

        SkillEntity skillId = new SkillEntity();
        skillId.setCandidateSkills(new HashSet<>());
        skillId.setOfferSkills(new HashSet<>());
        skillId.setSkillId(1L);
        skillId.setSkillName("Skill Name");

        CityEntity cityId2 = new CityEntity();
        cityId2.setCandidateResidenceCities(new HashSet<>());
        cityId2.setCityId(1L);
        cityId2.setCityName("Oxford");
        cityId2.setEmployerCities(new HashSet<>());
        cityId2.setOfferCities(new HashSet<>());

        EmployerEntity employerId2 = new EmployerEntity();
        employerId2.setAmountOfAvailableOffers(10);
        employerId2.setCityId(cityId2);
        employerId2.setCompanyName("Company Name");
        employerId2.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId2.setDescription("The characteristics of someone or something");
        employerId2.setEmailContact("jane.doe@example.org");
        employerId2.setEmployerId(1L);
        employerId2.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerId2.setLogoFilename("foo.txt");
        employerId2.setNumberOfEmployees(10);
        employerId2.setPhoneNumber("6625550144");
        employerId2.setWebsite("Website");

        CityEntity residenceCityId2 = new CityEntity();
        residenceCityId2.setCandidateResidenceCities(new HashSet<>());
        residenceCityId2.setCityId(1L);
        residenceCityId2.setCityName("Oxford");
        residenceCityId2.setEmployerCities(new HashSet<>());
        residenceCityId2.setOfferCities(new HashSet<>());
        CityEntity cityEntity = mock(CityEntity.class);
        when(cityEntity.getCityId()).thenReturn(1L);
        when(cityEntity.getCityName()).thenReturn("Oxford");
        doNothing().when(cityEntity).setCandidateResidenceCities(Mockito.any());
        doNothing().when(cityEntity).setCityId(Mockito.<Long>any());
        doNothing().when(cityEntity).setCityName(Mockito.any());
        doNothing().when(cityEntity).setEmployerCities(Mockito.any());
        doNothing().when(cityEntity).setOfferCities(Mockito.any());
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());

        CityEntity cityId3 = new CityEntity();
        cityId3.setCandidateResidenceCities(new HashSet<>());
        cityId3.setCityId(1L);
        cityId3.setCityName("Oxford");
        cityId3.setEmployerCities(new HashSet<>());
        cityId3.setOfferCities(new HashSet<>());

        EmployerEntity employerEntity = new EmployerEntity();
        employerEntity.setAmountOfAvailableOffers(10);
        employerEntity.setCityId(cityId3);
        employerEntity.setCompanyName("Company Name");
        employerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerEntity.setDescription("The characteristics of someone or something");
        employerEntity.setEmailContact("jane.doe@example.org");
        employerEntity.setEmployerId(1L);
        employerEntity.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerEntity.setLogoFilename("foo.txt");
        employerEntity.setNumberOfEmployees(10);
        employerEntity.setPhoneNumber("6625550144");
        employerEntity.setWebsite("Website");
        CandidateEntity candidateEntity = mock(CandidateEntity.class);
        when(candidateEntity.getOpenToRemoteJob()).thenReturn(true);
        when(candidateEntity.getYearsOfExperience()).thenReturn(1);
        when(candidateEntity.getCandidateId()).thenReturn(1L);
        when(candidateEntity.getCandidateUuid()).thenReturn("2020-03-01");
        when(candidateEntity.getCvFilename()).thenReturn("foo.txt");
        when(candidateEntity.getEducation()).thenReturn("Education");
        when(candidateEntity.getEmailContact()).thenReturn("jane.doe@example.org");
        when(candidateEntity.getExperienceLevel()).thenReturn("Experience Level");
        when(candidateEntity.getFirstName()).thenReturn("Jane");
        when(candidateEntity.getForeignLanguage()).thenReturn("en");
        when(candidateEntity.getGithubLink()).thenReturn("Github Link");
        when(candidateEntity.getHobby()).thenReturn("Hobby");
        when(candidateEntity.getLastName()).thenReturn("Doe");
        when(candidateEntity.getLinkedinLink()).thenReturn("Linkedin Link");
        when(candidateEntity.getOtherSkills()).thenReturn("Other Skills");
        when(candidateEntity.getPhoneNumber()).thenReturn("6625550144");
        when(candidateEntity.getPhotoFilename()).thenReturn("foo.txt");
        when(candidateEntity.getStatus()).thenReturn("Status");
        when(candidateEntity.getSalaryMin()).thenReturn(BigDecimal.valueOf(1L));
        when(candidateEntity.getCreatedAt())
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        when(candidateEntity.getCandidateSkills()).thenReturn(new HashSet<>());
        when(candidateEntity.getResidenceCityId()).thenReturn(cityEntity);
        when(candidateEntity.getEmployerId()).thenReturn(employerEntity);
        doNothing().when(candidateEntity).setCandidateId(Mockito.<Long>any());
        doNothing().when(candidateEntity).setCandidateSkills(Mockito.any());
        doNothing().when(candidateEntity).setCandidateUuid(Mockito.any());
        doNothing().when(candidateEntity).setCreatedAt(Mockito.any());
        doNothing().when(candidateEntity).setCvFilename(Mockito.any());
        doNothing().when(candidateEntity).setEducation(Mockito.any());
        doNothing().when(candidateEntity).setEmailContact(Mockito.any());
        doNothing().when(candidateEntity).setEmployerId(Mockito.any());
        doNothing().when(candidateEntity).setExperienceLevel(Mockito.any());
        doNothing().when(candidateEntity).setFirstName(Mockito.any());
        doNothing().when(candidateEntity).setForeignLanguage(Mockito.any());
        doNothing().when(candidateEntity).setGithubLink(Mockito.any());
        doNothing().when(candidateEntity).setHobby(Mockito.any());
        doNothing().when(candidateEntity).setLastName(Mockito.any());
        doNothing().when(candidateEntity).setLinkedinLink(Mockito.any());
        doNothing().when(candidateEntity).setOpenToRemoteJob(Mockito.<Boolean>any());
        doNothing().when(candidateEntity).setOtherSkills(Mockito.any());
        doNothing().when(candidateEntity).setPhoneNumber(Mockito.any());
        doNothing().when(candidateEntity).setResidenceCityId(Mockito.any());
        doNothing().when(candidateEntity).setSalaryMin(Mockito.any());
        doNothing().when(candidateEntity).setStatus(Mockito.any());
        doNothing().when(candidateEntity).setYearsOfExperience(Mockito.<Integer>any());
        candidateEntity.setCandidateId(1L);
        candidateEntity.setCandidateSkills(new HashSet<>());
        candidateEntity.setCandidateUuid("2020-03-01");
        candidateEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        candidateEntity.setCvFilename("foo.txt");
        candidateEntity.setEducation("Education");
        candidateEntity.setEmailContact("jane.doe@example.org");
        candidateEntity.setEmployerId(employerId2);
        candidateEntity.setExperienceLevel("Experience Level");
        candidateEntity.setFirstName("Jane");
        candidateEntity.setForeignLanguage("en");
        candidateEntity.setGithubLink("Github Link");
        candidateEntity.setHobby("Hobby");
        candidateEntity.setLastName("Doe");
        candidateEntity.setLinkedinLink("Linkedin Link");
        candidateEntity.setOpenToRemoteJob(true);
        candidateEntity.setOtherSkills("Other Skills");
        candidateEntity.setPhoneNumber("6625550144");
        candidateEntity.setResidenceCityId(residenceCityId2);
        candidateEntity.setSalaryMin(BigDecimal.valueOf(1L));
        candidateEntity.setStatus("Status");
        candidateEntity.setYearsOfExperience(1);

        SkillEntity skillEntity = new SkillEntity();
        skillEntity.setCandidateSkills(new HashSet<>());
        skillEntity.setOfferSkills(new HashSet<>());
        skillEntity.setSkillId(1L);
        skillEntity.setSkillName("Skill Name");
        CandidateSkillEntity candidateSkillEntity = mock(CandidateSkillEntity.class);
        when(candidateSkillEntity.getCandidateSkillId()).thenReturn(1L);
        when(candidateSkillEntity.getCandidateId()).thenReturn(candidateEntity);
        when(candidateSkillEntity.getSkillId()).thenReturn(skillEntity);
        doNothing().when(candidateSkillEntity).setCandidateId(Mockito.any());
        doNothing().when(candidateSkillEntity).setCandidateSkillId(Mockito.<Long>any());
        doNothing().when(candidateSkillEntity).setSkillId(Mockito.any());
        candidateSkillEntity.setCandidateId(candidateId);
        candidateSkillEntity.setCandidateSkillId(1L);
        candidateSkillEntity.setSkillId(skillId);
        CandidateSkill actualMapFromEntityResult = candidateSkillEntityMapperImpl.mapFromEntity(candidateSkillEntity);
        assertEquals(1L, actualMapFromEntityResult.getCandidateSkillId().longValue());
        Skill skillId2 = actualMapFromEntityResult.getSkillId();
        assertEquals("Skill Name", skillId2.getSkillName());
        assertEquals(1L, skillId2.getSkillId().longValue());
        Candidate candidateId2 = actualMapFromEntityResult.getCandidateId();
        assertEquals("Status", candidateId2.getStatus());
        BigDecimal expectedSalaryMin = BigDecimal.ONE;
        BigDecimal salaryMin2 = candidateId2.getSalaryMin();
        assertSame(expectedSalaryMin, salaryMin2);
        assertEquals(1, candidateId2.getYearsOfExperience().intValue());
        assertTrue(candidateId2.getCandidateSkills().isEmpty());
        assertEquals("Education", candidateId2.getEducation());
        assertEquals("Doe", candidateId2.getLastName());
        assertEquals("Linkedin Link", candidateId2.getLinkedinLink());
        assertEquals("foo.txt", candidateId2.getPhotoFilename());
        assertEquals("6625550144", candidateId2.getPhoneNumber());
        assertEquals("jane.doe@example.org", candidateId2.getEmailContact());
        assertTrue(candidateId2.getOpenToRemoteJob());
        assertEquals("Other Skills", candidateId2.getOtherSkills());
        assertEquals("2020-03-01", candidateId2.getCandidateUuid());
        assertEquals("Hobby", candidateId2.getHobby());
        assertEquals("Github Link", candidateId2.getGithubLink());
        assertEquals("en", candidateId2.getForeignLanguage());
        assertEquals("Jane", candidateId2.getFirstName());
        assertEquals("Experience Level", candidateId2.getExperienceLevel());
        assertEquals(1L, candidateId2.getCandidateId().longValue());
        assertEquals("Z", candidateId2.getCreatedAt().getOffset().toString());
        assertEquals("foo.txt", candidateId2.getCvFilename());
        Employer employerId3 = candidateId2.getEmployerId();
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", employerId3.getEmployerUuid());
        assertEquals(1L, employerId3.getEmployerId().longValue());
        assertEquals("jane.doe@example.org", employerId3.getEmailContact());
        assertEquals("The characteristics of someone or something", employerId3.getDescription());
        assertEquals("Z", employerId3.getCreatedAt().getOffset().toString());
        assertEquals("Company Name", employerId3.getCompanyName());
        City residenceCityId3 = candidateId2.getResidenceCityId();
        City cityId4 = employerId3.getCityId();
        assertEquals(residenceCityId3, cityId4);
        assertEquals("Oxford", residenceCityId3.getCityName());
        assertEquals("foo.txt", employerId3.getLogoFilename());
        assertEquals(10, employerId3.getAmountOfAvailableOffers().intValue());
        assertEquals(1L, residenceCityId3.getCityId().longValue());
        assertEquals("6625550144", employerId3.getPhoneNumber());
        assertEquals("Website", employerId3.getWebsite());
        assertEquals("1", salaryMin2.toString());
        assertEquals(10, employerId3.getNumberOfEmployees().intValue());
        assertEquals(1L, cityId4.getCityId().longValue());
        assertEquals("Oxford", cityId4.getCityName());
        verify(candidateSkillEntity).getCandidateSkillId();
        verify(candidateSkillEntity).getCandidateId();
        verify(candidateSkillEntity).getSkillId();
        verify(candidateSkillEntity).setCandidateId(Mockito.any());
        verify(candidateSkillEntity).setCandidateSkillId(Mockito.<Long>any());
        verify(candidateSkillEntity).setSkillId(Mockito.any());
        verify(candidateEntity).getOpenToRemoteJob();
        verify(candidateEntity).getYearsOfExperience();
        verify(candidateEntity).getCandidateId();
        verify(candidateEntity).getCandidateUuid();
        verify(candidateEntity).getCvFilename();
        verify(candidateEntity).getEducation();
        verify(candidateEntity).getEmailContact();
        verify(candidateEntity).getExperienceLevel();
        verify(candidateEntity).getFirstName();
        verify(candidateEntity).getForeignLanguage();
        verify(candidateEntity).getGithubLink();
        verify(candidateEntity).getHobby();
        verify(candidateEntity).getLastName();
        verify(candidateEntity).getLinkedinLink();
        verify(candidateEntity).getOtherSkills();
        verify(candidateEntity).getPhoneNumber();
        verify(candidateEntity).getPhotoFilename();
        verify(candidateEntity).getStatus();
        verify(candidateEntity).getSalaryMin();
        verify(candidateEntity).getCreatedAt();
        verify(candidateEntity).getCandidateSkills();
        verify(candidateEntity).getResidenceCityId();
        verify(candidateEntity).getEmployerId();
        verify(candidateEntity).setCandidateId(Mockito.<Long>any());
        verify(candidateEntity).setCandidateSkills(Mockito.any());
        verify(candidateEntity).setCandidateUuid(Mockito.any());
        verify(candidateEntity).setCreatedAt(Mockito.any());
        verify(candidateEntity).setCvFilename(Mockito.any());
        verify(candidateEntity).setEducation(Mockito.any());
        verify(candidateEntity).setEmailContact(Mockito.any());
        verify(candidateEntity).setEmployerId(Mockito.any());
        verify(candidateEntity).setExperienceLevel(Mockito.any());
        verify(candidateEntity).setFirstName(Mockito.any());
        verify(candidateEntity).setForeignLanguage(Mockito.any());
        verify(candidateEntity).setGithubLink(Mockito.any());
        verify(candidateEntity).setHobby(Mockito.any());
        verify(candidateEntity).setLastName(Mockito.any());
        verify(candidateEntity).setLinkedinLink(Mockito.any());
        verify(candidateEntity).setOpenToRemoteJob(Mockito.<Boolean>any());
        verify(candidateEntity).setOtherSkills(Mockito.any());
        verify(candidateEntity).setPhoneNumber(Mockito.any());
        verify(candidateEntity).setResidenceCityId(Mockito.any());
        verify(candidateEntity).setSalaryMin(Mockito.any());
        verify(candidateEntity).setStatus(Mockito.any());
        verify(candidateEntity).setYearsOfExperience(Mockito.<Integer>any());
        verify(cityEntity).getCityId();
        verify(cityEntity).getCityName();
        verify(cityEntity).setCandidateResidenceCities(Mockito.any());
        verify(cityEntity).setCityId(Mockito.<Long>any());
        verify(cityEntity).setCityName(Mockito.any());
        verify(cityEntity).setEmployerCities(Mockito.any());
        verify(cityEntity).setOfferCities(Mockito.any());
    }

    /**
     * Method under test: {@link CandidateSkillEntityMapperImpl#mapToEntity(CandidateSkill)}
     */
    @Test
    void testMapToEntity() {
        CandidateSkillEntity actualMapToEntityResult = candidateSkillEntityMapperImpl
                .mapToEntity(new CandidateSkill(1L, null, null));
        assertNull(actualMapToEntityResult.getCandidateId());
        assertNull(actualMapToEntityResult.getSkillId());
        assertEquals(1L, actualMapToEntityResult.getCandidateSkillId().longValue());
    }

    /**
     * Method under test: {@link CandidateSkillEntityMapperImpl#mapToEntity(CandidateSkill)}
     */
    @Test
    void testMapToEntity2() {
        assertNull(candidateSkillEntityMapperImpl.mapToEntity(null));
    }

    /**
     * Method under test: {@link CandidateSkillEntityMapperImpl#cityEntityToCity(CityEntity)}
     */
    @Test
    void testCityEntityToCity() {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());
        City actualCityEntityToCityResult = candidateSkillEntityMapperImpl.cityEntityToCity(cityEntity);
        assertEquals(1L, actualCityEntityToCityResult.getCityId().longValue());
        assertEquals("Oxford", actualCityEntityToCityResult.getCityName());
    }

    /**
     * Method under test: {@link CandidateSkillEntityMapperImpl#cityEntityToCity(CityEntity)}
     */
    @Test
    void testCityEntityToCity2() {
        CityEntity cityEntity = mock(CityEntity.class);
        when(cityEntity.getCityId()).thenReturn(1L);
        when(cityEntity.getCityName()).thenReturn("Oxford");
        doNothing().when(cityEntity).setCandidateResidenceCities(Mockito.any());
        doNothing().when(cityEntity).setCityId(Mockito.<Long>any());
        doNothing().when(cityEntity).setCityName(Mockito.any());
        doNothing().when(cityEntity).setEmployerCities(Mockito.any());
        doNothing().when(cityEntity).setOfferCities(Mockito.any());
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());
        City actualCityEntityToCityResult = candidateSkillEntityMapperImpl.cityEntityToCity(cityEntity);
        assertEquals(1L, actualCityEntityToCityResult.getCityId().longValue());
        assertEquals("Oxford", actualCityEntityToCityResult.getCityName());
        verify(cityEntity).getCityId();
        verify(cityEntity).getCityName();
        verify(cityEntity).setCandidateResidenceCities(Mockito.any());
        verify(cityEntity).setCityId(Mockito.<Long>any());
        verify(cityEntity).setCityName(Mockito.any());
        verify(cityEntity).setEmployerCities(Mockito.any());
        verify(cityEntity).setOfferCities(Mockito.any());
    }

    /**
     * Method under test: {@link CandidateSkillEntityMapperImpl#candidateSkillEntitySetToCandidateSkillSet(Set)}
     */
    @Test
    void testCandidateSkillEntitySetToCandidateSkillSet() {
        assertTrue(candidateSkillEntityMapperImpl.candidateSkillEntitySetToCandidateSkillSet(new HashSet<>()).isEmpty());
    }

    /**
     * Method under test: {@link CandidateSkillEntityMapperImpl#candidateSkillEntitySetToCandidateSkillSet(Set)}
     */
    @Test
    void testCandidateSkillEntitySetToCandidateSkillSet2() {
        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        EmployerEntity employerId = new EmployerEntity();
        employerId.setAmountOfAvailableOffers(10);
        employerId.setCityId(cityId);
        employerId.setCompanyName("Company Name");
        employerId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId.setDescription("The characteristics of someone or something");
        employerId.setEmailContact("jane.doe@example.org");
        employerId.setEmployerId(1L);
        employerId.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerId.setLogoFilename("foo.txt");
        employerId.setNumberOfEmployees(10);
        employerId.setPhoneNumber("6625550144");
        employerId.setWebsite("Website");

        CityEntity residenceCityId = new CityEntity();
        residenceCityId.setCandidateResidenceCities(new HashSet<>());
        residenceCityId.setCityId(1L);
        residenceCityId.setCityName("Oxford");
        residenceCityId.setEmployerCities(new HashSet<>());
        residenceCityId.setOfferCities(new HashSet<>());

        CandidateEntity candidateId = new CandidateEntity();
        candidateId.setCandidateId(1L);
        candidateId.setCandidateSkills(new HashSet<>());
        candidateId.setCandidateUuid("2020-03-01");
        candidateId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        candidateId.setCvFilename("foo.txt");
        candidateId.setEducation("Education");
        candidateId.setEmailContact("jane.doe@example.org");
        candidateId.setEmployerId(employerId);
        candidateId.setExperienceLevel("Experience Level");
        candidateId.setFirstName("Jane");
        candidateId.setForeignLanguage("en");
        candidateId.setGithubLink("Github Link");
        candidateId.setHobby("Hobby");
        candidateId.setLastName("Doe");
        candidateId.setLinkedinLink("Linkedin Link");
        candidateId.setOpenToRemoteJob(true);
        candidateId.setOtherSkills("Other Skills");
        candidateId.setPhoneNumber("6625550144");
        candidateId.setResidenceCityId(residenceCityId);
        candidateId.setSalaryMin(BigDecimal.valueOf(1L));
        candidateId.setStatus("Status");
        candidateId.setYearsOfExperience(1);

        SkillEntity skillId = new SkillEntity();
        skillId.setCandidateSkills(new HashSet<>());
        skillId.setOfferSkills(new HashSet<>());
        skillId.setSkillId(1L);
        skillId.setSkillName("Skill Name");

        CandidateSkillEntity candidateSkillEntity = new CandidateSkillEntity();
        candidateSkillEntity.setCandidateId(candidateId);
        candidateSkillEntity.setCandidateSkillId(1L);
        candidateSkillEntity.setSkillId(skillId);

        HashSet<CandidateSkillEntity> set = new HashSet<>();
        set.add(candidateSkillEntity);
        assertEquals(1, candidateSkillEntityMapperImpl.candidateSkillEntitySetToCandidateSkillSet(set).size());
    }

    /**
     * Method under test: {@link CandidateSkillEntityMapperImpl#candidateSkillEntitySetToCandidateSkillSet(Set)}
     */
    @Test
    void testCandidateSkillEntitySetToCandidateSkillSet3() {
        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        EmployerEntity employerId = new EmployerEntity();
        employerId.setAmountOfAvailableOffers(10);
        employerId.setCityId(cityId);
        employerId.setCompanyName("Company Name");
        employerId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId.setDescription("The characteristics of someone or something");
        employerId.setEmailContact("jane.doe@example.org");
        employerId.setEmployerId(1L);
        employerId.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerId.setLogoFilename("foo.txt");
        employerId.setNumberOfEmployees(10);
        employerId.setPhoneNumber("6625550144");
        employerId.setWebsite("Website");

        CityEntity residenceCityId = new CityEntity();
        residenceCityId.setCandidateResidenceCities(new HashSet<>());
        residenceCityId.setCityId(1L);
        residenceCityId.setCityName("Oxford");
        residenceCityId.setEmployerCities(new HashSet<>());
        residenceCityId.setOfferCities(new HashSet<>());

        CandidateEntity candidateId = new CandidateEntity();
        candidateId.setCandidateId(1L);
        candidateId.setCandidateSkills(new HashSet<>());
        candidateId.setCandidateUuid("2020-03-01");
        candidateId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        candidateId.setCvFilename("foo.txt");
        candidateId.setEducation("Education");
        candidateId.setEmailContact("jane.doe@example.org");
        candidateId.setEmployerId(employerId);
        candidateId.setExperienceLevel("Experience Level");
        candidateId.setFirstName("Jane");
        candidateId.setForeignLanguage("en");
        candidateId.setGithubLink("Github Link");
        candidateId.setHobby("Hobby");
        candidateId.setLastName("Doe");
        candidateId.setLinkedinLink("Linkedin Link");
        candidateId.setOpenToRemoteJob(true);
        candidateId.setOtherSkills("Other Skills");
        candidateId.setPhoneNumber("6625550144");
        candidateId.setResidenceCityId(residenceCityId);
        candidateId.setSalaryMin(BigDecimal.valueOf(1L));
        candidateId.setStatus("Status");
        candidateId.setYearsOfExperience(1);

        SkillEntity skillId = new SkillEntity();
        skillId.setCandidateSkills(new HashSet<>());
        skillId.setOfferSkills(new HashSet<>());
        skillId.setSkillId(1L);
        skillId.setSkillName("Skill Name");

        CandidateSkillEntity candidateSkillEntity = new CandidateSkillEntity();
        candidateSkillEntity.setCandidateId(candidateId);
        candidateSkillEntity.setCandidateSkillId(1L);
        candidateSkillEntity.setSkillId(skillId);

        CityEntity cityId2 = new CityEntity();
        cityId2.setCandidateResidenceCities(new HashSet<>());
        cityId2.setCityId(2L);
        cityId2.setCityName("London");
        cityId2.setEmployerCities(new HashSet<>());
        cityId2.setOfferCities(new HashSet<>());

        EmployerEntity employerId2 = new EmployerEntity();
        employerId2.setAmountOfAvailableOffers(1);
        employerId2.setCityId(cityId2);
        employerId2.setCompanyName("pl.devfinder.infrastructure.database.entity.EmployerEntity");
        employerId2.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId2.setDescription("Description");
        employerId2.setEmailContact("john.smith@example.org");
        employerId2.setEmployerId(2L);
        employerId2.setEmployerUuid("1234");
        employerId2.setLogoFilename("Logo Filename");
        employerId2.setNumberOfEmployees(1);
        employerId2.setPhoneNumber("8605550118");
        employerId2.setWebsite("pl.devfinder.infrastructure.database.entity.EmployerEntity");

        CityEntity residenceCityId2 = new CityEntity();
        residenceCityId2.setCandidateResidenceCities(new HashSet<>());
        residenceCityId2.setCityId(2L);
        residenceCityId2.setCityName("London");
        residenceCityId2.setEmployerCities(new HashSet<>());
        residenceCityId2.setOfferCities(new HashSet<>());

        CandidateEntity candidateId2 = new CandidateEntity();
        candidateId2.setCandidateId(2L);
        candidateId2.setCandidateSkills(new HashSet<>());
        candidateId2.setCandidateUuid("2020/03/01");
        candidateId2.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        candidateId2.setCvFilename("Cv Filename");
        candidateId2.setEducation("pl.devfinder.infrastructure.database.entity.CandidateEntity");
        candidateId2.setEmailContact("john.smith@example.org");
        candidateId2.setEmployerId(employerId2);
        candidateId2.setExperienceLevel("pl.devfinder.infrastructure.database.entity.CandidateEntity");
        candidateId2.setFirstName("John");
        candidateId2.setForeignLanguage("eng");
        candidateId2.setGithubLink("pl.devfinder.infrastructure.database.entity.CandidateEntity");
        candidateId2.setHobby("pl.devfinder.infrastructure.database.entity.CandidateEntity");
        candidateId2.setLastName("Smith");
        candidateId2.setLinkedinLink("pl.devfinder.infrastructure.database.entity.CandidateEntity");
        candidateId2.setOpenToRemoteJob(false);
        candidateId2.setOtherSkills("pl.devfinder.infrastructure.database.entity.CandidateEntity");
        candidateId2.setPhoneNumber("8605550118");
        candidateId2.setResidenceCityId(residenceCityId2);
        candidateId2.setSalaryMin(BigDecimal.valueOf(1L));
        candidateId2.setStatus("pl.devfinder.infrastructure.database.entity.CandidateEntity");
        candidateId2.setYearsOfExperience(Short.SIZE);

        SkillEntity skillId2 = new SkillEntity();
        skillId2.setCandidateSkills(new HashSet<>());
        skillId2.setOfferSkills(new HashSet<>());
        skillId2.setSkillId(2L);
        skillId2.setSkillName("pl.devfinder.infrastructure.database.entity.SkillEntity");

        CandidateSkillEntity candidateSkillEntity2 = new CandidateSkillEntity();
        candidateSkillEntity2.setCandidateId(candidateId2);
        candidateSkillEntity2.setCandidateSkillId(2L);
        candidateSkillEntity2.setSkillId(skillId2);

        HashSet<CandidateSkillEntity> set = new HashSet<>();
        set.add(candidateSkillEntity2);
        set.add(candidateSkillEntity);
        assertEquals(2, candidateSkillEntityMapperImpl.candidateSkillEntitySetToCandidateSkillSet(set).size());
    }

    /**
     * Method under test: {@link CandidateSkillEntityMapperImpl#candidateSkillEntitySetToCandidateSkillSet(Set)}
     */
    @Test
    void testCandidateSkillEntitySetToCandidateSkillSet4() {
        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        EmployerEntity employerId = new EmployerEntity();
        employerId.setAmountOfAvailableOffers(10);
        employerId.setCityId(cityId);
        employerId.setCompanyName("Company Name");
        employerId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId.setDescription("The characteristics of someone or something");
        employerId.setEmailContact("jane.doe@example.org");
        employerId.setEmployerId(1L);
        employerId.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerId.setLogoFilename("foo.txt");
        employerId.setNumberOfEmployees(10);
        employerId.setPhoneNumber("6625550144");
        employerId.setWebsite("Website");

        CityEntity residenceCityId = new CityEntity();
        residenceCityId.setCandidateResidenceCities(new HashSet<>());
        residenceCityId.setCityId(1L);
        residenceCityId.setCityName("Oxford");
        residenceCityId.setEmployerCities(new HashSet<>());
        residenceCityId.setOfferCities(new HashSet<>());

        CandidateEntity candidateId = new CandidateEntity();
        candidateId.setCandidateId(1L);
        candidateId.setCandidateSkills(new HashSet<>());
        candidateId.setCandidateUuid(null);
        candidateId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        candidateId.setCvFilename("foo.txt");
        candidateId.setEducation("Education");
        candidateId.setEmailContact("jane.doe@example.org");
        candidateId.setEmployerId(employerId);
        candidateId.setExperienceLevel("Experience Level");
        candidateId.setFirstName("Jane");
        candidateId.setForeignLanguage("en");
        candidateId.setGithubLink("Github Link");
        candidateId.setHobby("Hobby");
        candidateId.setLastName("Doe");
        candidateId.setLinkedinLink("Linkedin Link");
        candidateId.setOpenToRemoteJob(true);
        candidateId.setOtherSkills("Other Skills");
        candidateId.setPhoneNumber("6625550144");
        candidateId.setResidenceCityId(residenceCityId);
        candidateId.setSalaryMin(BigDecimal.valueOf(1L));
        candidateId.setStatus("Status");
        candidateId.setYearsOfExperience(1);

        SkillEntity skillId = new SkillEntity();
        skillId.setCandidateSkills(new HashSet<>());
        skillId.setOfferSkills(new HashSet<>());
        skillId.setSkillId(1L);
        skillId.setSkillName("Skill Name");

        CandidateSkillEntity candidateSkillEntity = new CandidateSkillEntity();
        candidateSkillEntity.setCandidateId(candidateId);
        candidateSkillEntity.setCandidateSkillId(1L);
        candidateSkillEntity.setSkillId(skillId);

        HashSet<CandidateSkillEntity> set = new HashSet<>();
        set.add(candidateSkillEntity);
        assertEquals(1, candidateSkillEntityMapperImpl.candidateSkillEntitySetToCandidateSkillSet(set).size());
    }

    /**
     * Method under test: {@link CandidateSkillEntityMapperImpl#candidateSkillEntitySetToCandidateSkillSet(Set)}
     */
    @Test
    void testCandidateSkillEntitySetToCandidateSkillSet5() {
        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        EmployerEntity employerId = new EmployerEntity();
        employerId.setAmountOfAvailableOffers(10);
        employerId.setCityId(cityId);
        employerId.setCompanyName("Company Name");
        employerId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId.setDescription("The characteristics of someone or something");
        employerId.setEmailContact("jane.doe@example.org");
        employerId.setEmployerId(1L);
        employerId.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerId.setLogoFilename("foo.txt");
        employerId.setNumberOfEmployees(10);
        employerId.setPhoneNumber("6625550144");
        employerId.setWebsite("Website");

        CityEntity residenceCityId = new CityEntity();
        residenceCityId.setCandidateResidenceCities(new HashSet<>());
        residenceCityId.setCityId(1L);
        residenceCityId.setCityName("Oxford");
        residenceCityId.setEmployerCities(new HashSet<>());
        residenceCityId.setOfferCities(new HashSet<>());

        CandidateEntity candidateId = new CandidateEntity();
        candidateId.setCandidateId(1L);
        candidateId.setCandidateSkills(new HashSet<>());
        candidateId.setCandidateUuid("2020-03-01");
        candidateId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        candidateId.setCvFilename("foo.txt");
        candidateId.setEducation("Education");
        candidateId.setEmailContact("jane.doe@example.org");
        candidateId.setEmployerId(employerId);
        candidateId.setExperienceLevel("Experience Level");
        candidateId.setFirstName("Jane");
        candidateId.setForeignLanguage("en");
        candidateId.setGithubLink("Github Link");
        candidateId.setHobby("Hobby");
        candidateId.setLastName("Doe");
        candidateId.setLinkedinLink("Linkedin Link");
        candidateId.setOpenToRemoteJob(true);
        candidateId.setOtherSkills("Other Skills");
        candidateId.setPhoneNumber("6625550144");
        candidateId.setResidenceCityId(residenceCityId);
        candidateId.setSalaryMin(BigDecimal.valueOf(1L));
        candidateId.setStatus("Status");
        candidateId.setYearsOfExperience(1);

        SkillEntity skillId = new SkillEntity();
        skillId.setCandidateSkills(new HashSet<>());
        skillId.setOfferSkills(new HashSet<>());
        skillId.setSkillId(1L);
        skillId.setSkillName("Skill Name");

        CandidateSkillEntity candidateSkillEntity = new CandidateSkillEntity();
        candidateSkillEntity.setCandidateId(candidateId);
        candidateSkillEntity.setCandidateSkillId(null);
        candidateSkillEntity.setSkillId(skillId);

        HashSet<CandidateSkillEntity> set = new HashSet<>();
        set.add(candidateSkillEntity);
        assertEquals(1, candidateSkillEntityMapperImpl.candidateSkillEntitySetToCandidateSkillSet(set).size());
    }

    /**
     * Method under test: {@link CandidateSkillEntityMapperImpl#candidateSkillEntitySetToCandidateSkillSet(Set)}
     */
    @Test
    void testCandidateSkillEntitySetToCandidateSkillSet6() {
        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        EmployerEntity employerId = new EmployerEntity();
        employerId.setAmountOfAvailableOffers(10);
        employerId.setCityId(cityId);
        employerId.setCompanyName("Company Name");
        employerId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId.setDescription("The characteristics of someone or something");
        employerId.setEmailContact("jane.doe@example.org");
        employerId.setEmployerId(1L);
        employerId.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerId.setLogoFilename("foo.txt");
        employerId.setNumberOfEmployees(10);
        employerId.setPhoneNumber("6625550144");
        employerId.setWebsite("Website");

        CityEntity residenceCityId = new CityEntity();
        residenceCityId.setCandidateResidenceCities(new HashSet<>());
        residenceCityId.setCityId(1L);
        residenceCityId.setCityName("Oxford");
        residenceCityId.setEmployerCities(new HashSet<>());
        residenceCityId.setOfferCities(new HashSet<>());

        CandidateEntity candidateId = new CandidateEntity();
        candidateId.setCandidateId(1L);
        candidateId.setCandidateSkills(new HashSet<>());
        candidateId.setCandidateUuid("2020-03-01");
        candidateId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        candidateId.setCvFilename("foo.txt");
        candidateId.setEducation("Education");
        candidateId.setEmailContact("jane.doe@example.org");
        candidateId.setEmployerId(employerId);
        candidateId.setExperienceLevel("Experience Level");
        candidateId.setFirstName("Jane");
        candidateId.setForeignLanguage("en");
        candidateId.setGithubLink("Github Link");
        candidateId.setHobby("Hobby");
        candidateId.setLastName("Doe");
        candidateId.setLinkedinLink("Linkedin Link");
        candidateId.setOpenToRemoteJob(true);
        candidateId.setOtherSkills("Other Skills");
        candidateId.setPhoneNumber("6625550144");
        candidateId.setResidenceCityId(residenceCityId);
        candidateId.setSalaryMin(BigDecimal.valueOf(1L));
        candidateId.setStatus("Status");
        candidateId.setYearsOfExperience(1);

        SkillEntity skillId = new SkillEntity();
        skillId.setCandidateSkills(new HashSet<>());
        skillId.setOfferSkills(new HashSet<>());
        skillId.setSkillId(null);
        skillId.setSkillName("Skill Name");

        CandidateSkillEntity candidateSkillEntity = new CandidateSkillEntity();
        candidateSkillEntity.setCandidateId(candidateId);
        candidateSkillEntity.setCandidateSkillId(1L);
        candidateSkillEntity.setSkillId(skillId);

        HashSet<CandidateSkillEntity> set = new HashSet<>();
        set.add(candidateSkillEntity);
        assertEquals(1, candidateSkillEntityMapperImpl.candidateSkillEntitySetToCandidateSkillSet(set).size());
    }

    /**
     * Method under test: {@link CandidateSkillEntityMapperImpl#candidateEntityToCandidate(CandidateEntity)}
     */
    @Test
    void testCandidateEntityToCandidate() {
        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        EmployerEntity employerId = new EmployerEntity();
        employerId.setAmountOfAvailableOffers(10);
        employerId.setCityId(cityId);
        employerId.setCompanyName("Company Name");
        employerId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId.setDescription("The characteristics of someone or something");
        employerId.setEmailContact("jane.doe@example.org");
        employerId.setEmployerId(1L);
        employerId.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerId.setLogoFilename("foo.txt");
        employerId.setNumberOfEmployees(10);
        employerId.setPhoneNumber("6625550144");
        employerId.setWebsite("Website");

        CityEntity residenceCityId = new CityEntity();
        residenceCityId.setCandidateResidenceCities(new HashSet<>());
        residenceCityId.setCityId(1L);
        residenceCityId.setCityName("Oxford");
        residenceCityId.setEmployerCities(new HashSet<>());
        residenceCityId.setOfferCities(new HashSet<>());

        CandidateEntity candidateEntity = new CandidateEntity();
        candidateEntity.setCandidateId(1L);
        candidateEntity.setCandidateSkills(new HashSet<>());
        candidateEntity.setCandidateUuid("2020-03-01");
        candidateEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        candidateEntity.setCvFilename("foo.txt");
        candidateEntity.setEducation("Education");
        candidateEntity.setEmailContact("jane.doe@example.org");
        candidateEntity.setEmployerId(employerId);
        candidateEntity.setExperienceLevel("Experience Level");
        candidateEntity.setFirstName("Jane");
        candidateEntity.setForeignLanguage("en");
        candidateEntity.setGithubLink("Github Link");
        candidateEntity.setHobby("Hobby");
        candidateEntity.setLastName("Doe");
        candidateEntity.setLinkedinLink("Linkedin Link");
        candidateEntity.setOpenToRemoteJob(true);
        candidateEntity.setOtherSkills("Other Skills");
        candidateEntity.setPhoneNumber("6625550144");
        candidateEntity.setResidenceCityId(residenceCityId);
        BigDecimal salaryMin = BigDecimal.valueOf(1L);
        candidateEntity.setSalaryMin(salaryMin);
        candidateEntity.setStatus("Status");
        candidateEntity.setYearsOfExperience(1);
        Candidate actualCandidateEntityToCandidateResult = candidateSkillEntityMapperImpl
                .candidateEntityToCandidate(candidateEntity);
        assertEquals(1L, actualCandidateEntityToCandidateResult.getCandidateId().longValue());
        assertEquals(1, actualCandidateEntityToCandidateResult.getYearsOfExperience().intValue());
        assertEquals("Status", actualCandidateEntityToCandidateResult.getStatus());
        BigDecimal expectedSalaryMin = BigDecimal.ONE;
        BigDecimal salaryMin2 = actualCandidateEntityToCandidateResult.getSalaryMin();
        assertSame(expectedSalaryMin, salaryMin2);
        assertTrue(actualCandidateEntityToCandidateResult.getCandidateSkills().isEmpty());
        assertEquals("Z", actualCandidateEntityToCandidateResult.getCreatedAt().getOffset().toString());
        assertTrue(actualCandidateEntityToCandidateResult.getOpenToRemoteJob());
        assertEquals("Other Skills", actualCandidateEntityToCandidateResult.getOtherSkills());
        assertEquals("Experience Level", actualCandidateEntityToCandidateResult.getExperienceLevel());
        assertEquals("6625550144", actualCandidateEntityToCandidateResult.getPhoneNumber());
        assertNull(actualCandidateEntityToCandidateResult.getPhotoFilename());
        assertEquals("foo.txt", actualCandidateEntityToCandidateResult.getCvFilename());
        assertEquals("Jane", actualCandidateEntityToCandidateResult.getFirstName());
        assertEquals("en", actualCandidateEntityToCandidateResult.getForeignLanguage());
        assertEquals("2020-03-01", actualCandidateEntityToCandidateResult.getCandidateUuid());
        assertEquals("Education", actualCandidateEntityToCandidateResult.getEducation());
        assertEquals("Github Link", actualCandidateEntityToCandidateResult.getGithubLink());
        assertEquals("Hobby", actualCandidateEntityToCandidateResult.getHobby());
        assertEquals("jane.doe@example.org", actualCandidateEntityToCandidateResult.getEmailContact());
        assertEquals("Doe", actualCandidateEntityToCandidateResult.getLastName());
        assertEquals("Linkedin Link", actualCandidateEntityToCandidateResult.getLinkedinLink());
        Employer employerId2 = actualCandidateEntityToCandidateResult.getEmployerId();
        assertEquals("Website", employerId2.getWebsite());
        City residenceCityId2 = actualCandidateEntityToCandidateResult.getResidenceCityId();
        assertEquals("Oxford", residenceCityId2.getCityName());
        assertEquals(1L, residenceCityId2.getCityId().longValue());
        assertEquals(10, employerId2.getAmountOfAvailableOffers().intValue());
        assertEquals("Company Name", employerId2.getCompanyName());
        assertEquals("Z", employerId2.getCreatedAt().getOffset().toString());
        assertEquals(1L, employerId2.getEmployerId().longValue());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", employerId2.getEmployerUuid());
        assertEquals(10, employerId2.getNumberOfEmployees().intValue());
        assertEquals("1", salaryMin2.toString());
        City cityId2 = employerId2.getCityId();
        assertEquals(residenceCityId2, cityId2);
        assertEquals("jane.doe@example.org", employerId2.getEmailContact());
        assertEquals("The characteristics of someone or something", employerId2.getDescription());
        assertEquals("foo.txt", employerId2.getLogoFilename());
        assertEquals("6625550144", employerId2.getPhoneNumber());
        assertEquals("Oxford", cityId2.getCityName());
        assertEquals(1L, cityId2.getCityId().longValue());
    }

    /**
     * Method under test: {@link CandidateSkillEntityMapperImpl#candidateEntityToCandidate(CandidateEntity)}
     */
    @Test
    void testCandidateEntityToCandidate2() {
        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        EmployerEntity employerId = new EmployerEntity();
        employerId.setAmountOfAvailableOffers(10);
        employerId.setCityId(cityId);
        employerId.setCompanyName("Company Name");
        employerId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId.setDescription("The characteristics of someone or something");
        employerId.setEmailContact("jane.doe@example.org");
        employerId.setEmployerId(1L);
        employerId.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerId.setLogoFilename("foo.txt");
        employerId.setNumberOfEmployees(10);
        employerId.setPhoneNumber("6625550144");
        employerId.setWebsite("Website");

        CityEntity residenceCityId = new CityEntity();
        residenceCityId.setCandidateResidenceCities(new HashSet<>());
        residenceCityId.setCityId(1L);
        residenceCityId.setCityName("Oxford");
        residenceCityId.setEmployerCities(new HashSet<>());
        residenceCityId.setOfferCities(new HashSet<>());

        CityEntity cityEntity = new CityEntity();
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());

        CityEntity cityId2 = new CityEntity();
        cityId2.setCandidateResidenceCities(new HashSet<>());
        cityId2.setCityId(1L);
        cityId2.setCityName("Oxford");
        cityId2.setEmployerCities(new HashSet<>());
        cityId2.setOfferCities(new HashSet<>());

        EmployerEntity employerEntity = new EmployerEntity();
        employerEntity.setAmountOfAvailableOffers(10);
        employerEntity.setCityId(cityId2);
        employerEntity.setCompanyName("Company Name");
        employerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerEntity.setDescription("The characteristics of someone or something");
        employerEntity.setEmailContact("jane.doe@example.org");
        employerEntity.setEmployerId(1L);
        employerEntity.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerEntity.setLogoFilename("foo.txt");
        employerEntity.setNumberOfEmployees(10);
        employerEntity.setPhoneNumber("6625550144");
        employerEntity.setWebsite("Website");
        CandidateEntity candidateEntity = mock(CandidateEntity.class);
        when(candidateEntity.getOpenToRemoteJob()).thenReturn(true);
        when(candidateEntity.getYearsOfExperience()).thenReturn(1);
        when(candidateEntity.getCandidateId()).thenReturn(1L);
        when(candidateEntity.getCandidateUuid()).thenReturn("2020-03-01");
        when(candidateEntity.getCvFilename()).thenReturn("foo.txt");
        when(candidateEntity.getEducation()).thenReturn("Education");
        when(candidateEntity.getEmailContact()).thenReturn("jane.doe@example.org");
        when(candidateEntity.getExperienceLevel()).thenReturn("Experience Level");
        when(candidateEntity.getFirstName()).thenReturn("Jane");
        when(candidateEntity.getForeignLanguage()).thenReturn("en");
        when(candidateEntity.getGithubLink()).thenReturn("Github Link");
        when(candidateEntity.getHobby()).thenReturn("Hobby");
        when(candidateEntity.getLastName()).thenReturn("Doe");
        when(candidateEntity.getLinkedinLink()).thenReturn("Linkedin Link");
        when(candidateEntity.getOtherSkills()).thenReturn("Other Skills");
        when(candidateEntity.getPhoneNumber()).thenReturn("6625550144");
        when(candidateEntity.getPhotoFilename()).thenReturn("foo.txt");
        when(candidateEntity.getStatus()).thenReturn("Status");
        when(candidateEntity.getSalaryMin()).thenReturn(BigDecimal.valueOf(1L));
        when(candidateEntity.getCreatedAt())
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        when(candidateEntity.getCandidateSkills()).thenReturn(new HashSet<>());
        when(candidateEntity.getResidenceCityId()).thenReturn(cityEntity);
        when(candidateEntity.getEmployerId()).thenReturn(employerEntity);
        doNothing().when(candidateEntity).setCandidateId(Mockito.<Long>any());
        doNothing().when(candidateEntity).setCandidateSkills(Mockito.any());
        doNothing().when(candidateEntity).setCandidateUuid(Mockito.any());
        doNothing().when(candidateEntity).setCreatedAt(Mockito.any());
        doNothing().when(candidateEntity).setCvFilename(Mockito.any());
        doNothing().when(candidateEntity).setEducation(Mockito.any());
        doNothing().when(candidateEntity).setEmailContact(Mockito.any());
        doNothing().when(candidateEntity).setEmployerId(Mockito.any());
        doNothing().when(candidateEntity).setExperienceLevel(Mockito.any());
        doNothing().when(candidateEntity).setFirstName(Mockito.any());
        doNothing().when(candidateEntity).setForeignLanguage(Mockito.any());
        doNothing().when(candidateEntity).setGithubLink(Mockito.any());
        doNothing().when(candidateEntity).setHobby(Mockito.any());
        doNothing().when(candidateEntity).setLastName(Mockito.any());
        doNothing().when(candidateEntity).setLinkedinLink(Mockito.any());
        doNothing().when(candidateEntity).setOpenToRemoteJob(Mockito.<Boolean>any());
        doNothing().when(candidateEntity).setOtherSkills(Mockito.any());
        doNothing().when(candidateEntity).setPhoneNumber(Mockito.any());
        doNothing().when(candidateEntity).setResidenceCityId(Mockito.any());
        doNothing().when(candidateEntity).setSalaryMin(Mockito.any());
        doNothing().when(candidateEntity).setStatus(Mockito.any());
        doNothing().when(candidateEntity).setYearsOfExperience(Mockito.<Integer>any());
        candidateEntity.setCandidateId(1L);
        candidateEntity.setCandidateSkills(new HashSet<>());
        candidateEntity.setCandidateUuid("2020-03-01");
        candidateEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        candidateEntity.setCvFilename("foo.txt");
        candidateEntity.setEducation("Education");
        candidateEntity.setEmailContact("jane.doe@example.org");
        candidateEntity.setEmployerId(employerId);
        candidateEntity.setExperienceLevel("Experience Level");
        candidateEntity.setFirstName("Jane");
        candidateEntity.setForeignLanguage("en");
        candidateEntity.setGithubLink("Github Link");
        candidateEntity.setHobby("Hobby");
        candidateEntity.setLastName("Doe");
        candidateEntity.setLinkedinLink("Linkedin Link");
        candidateEntity.setOpenToRemoteJob(true);
        candidateEntity.setOtherSkills("Other Skills");
        candidateEntity.setPhoneNumber("6625550144");
        candidateEntity.setResidenceCityId(residenceCityId);
        BigDecimal salaryMin = BigDecimal.valueOf(1L);
        candidateEntity.setSalaryMin(salaryMin);
        candidateEntity.setStatus("Status");
        candidateEntity.setYearsOfExperience(1);
        Candidate actualCandidateEntityToCandidateResult = candidateSkillEntityMapperImpl
                .candidateEntityToCandidate(candidateEntity);
        assertEquals(1L, actualCandidateEntityToCandidateResult.getCandidateId().longValue());
        assertEquals(1, actualCandidateEntityToCandidateResult.getYearsOfExperience().intValue());
        assertEquals("Status", actualCandidateEntityToCandidateResult.getStatus());
        BigDecimal expectedSalaryMin = BigDecimal.ONE;
        BigDecimal salaryMin2 = actualCandidateEntityToCandidateResult.getSalaryMin();
        assertSame(expectedSalaryMin, salaryMin2);
        assertTrue(actualCandidateEntityToCandidateResult.getCandidateSkills().isEmpty());
        assertEquals("Z", actualCandidateEntityToCandidateResult.getCreatedAt().getOffset().toString());
        assertTrue(actualCandidateEntityToCandidateResult.getOpenToRemoteJob());
        assertEquals("Other Skills", actualCandidateEntityToCandidateResult.getOtherSkills());
        assertEquals("Experience Level", actualCandidateEntityToCandidateResult.getExperienceLevel());
        assertEquals("6625550144", actualCandidateEntityToCandidateResult.getPhoneNumber());
        assertEquals("foo.txt", actualCandidateEntityToCandidateResult.getPhotoFilename());
        assertEquals("foo.txt", actualCandidateEntityToCandidateResult.getCvFilename());
        assertEquals("Jane", actualCandidateEntityToCandidateResult.getFirstName());
        assertEquals("en", actualCandidateEntityToCandidateResult.getForeignLanguage());
        assertEquals("2020-03-01", actualCandidateEntityToCandidateResult.getCandidateUuid());
        assertEquals("Education", actualCandidateEntityToCandidateResult.getEducation());
        assertEquals("Github Link", actualCandidateEntityToCandidateResult.getGithubLink());
        assertEquals("Hobby", actualCandidateEntityToCandidateResult.getHobby());
        assertEquals("jane.doe@example.org", actualCandidateEntityToCandidateResult.getEmailContact());
        assertEquals("Doe", actualCandidateEntityToCandidateResult.getLastName());
        assertEquals("Linkedin Link", actualCandidateEntityToCandidateResult.getLinkedinLink());
        Employer employerId2 = actualCandidateEntityToCandidateResult.getEmployerId();
        assertEquals("Website", employerId2.getWebsite());
        City residenceCityId2 = actualCandidateEntityToCandidateResult.getResidenceCityId();
        assertEquals("Oxford", residenceCityId2.getCityName());
        assertEquals(1L, residenceCityId2.getCityId().longValue());
        assertEquals(10, employerId2.getAmountOfAvailableOffers().intValue());
        assertEquals("Company Name", employerId2.getCompanyName());
        assertEquals("Z", employerId2.getCreatedAt().getOffset().toString());
        assertEquals(1L, employerId2.getEmployerId().longValue());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", employerId2.getEmployerUuid());
        assertEquals(10, employerId2.getNumberOfEmployees().intValue());
        assertEquals("1", salaryMin2.toString());
        City cityId3 = employerId2.getCityId();
        assertEquals(residenceCityId2, cityId3);
        assertEquals("jane.doe@example.org", employerId2.getEmailContact());
        assertEquals("The characteristics of someone or something", employerId2.getDescription());
        assertEquals("foo.txt", employerId2.getLogoFilename());
        assertEquals("6625550144", employerId2.getPhoneNumber());
        assertEquals("Oxford", cityId3.getCityName());
        assertEquals(1L, cityId3.getCityId().longValue());
        verify(candidateEntity).getOpenToRemoteJob();
        verify(candidateEntity).getYearsOfExperience();
        verify(candidateEntity).getCandidateId();
        verify(candidateEntity).getCandidateUuid();
        verify(candidateEntity).getCvFilename();
        verify(candidateEntity).getEducation();
        verify(candidateEntity).getEmailContact();
        verify(candidateEntity).getExperienceLevel();
        verify(candidateEntity).getFirstName();
        verify(candidateEntity).getForeignLanguage();
        verify(candidateEntity).getGithubLink();
        verify(candidateEntity).getHobby();
        verify(candidateEntity).getLastName();
        verify(candidateEntity).getLinkedinLink();
        verify(candidateEntity).getOtherSkills();
        verify(candidateEntity).getPhoneNumber();
        verify(candidateEntity).getPhotoFilename();
        verify(candidateEntity).getStatus();
        verify(candidateEntity).getSalaryMin();
        verify(candidateEntity).getCreatedAt();
        verify(candidateEntity).getCandidateSkills();
        verify(candidateEntity).getResidenceCityId();
        verify(candidateEntity).getEmployerId();
        verify(candidateEntity).setCandidateId(Mockito.<Long>any());
        verify(candidateEntity).setCandidateSkills(Mockito.any());
        verify(candidateEntity).setCandidateUuid(Mockito.any());
        verify(candidateEntity).setCreatedAt(Mockito.any());
        verify(candidateEntity).setCvFilename(Mockito.any());
        verify(candidateEntity).setEducation(Mockito.any());
        verify(candidateEntity).setEmailContact(Mockito.any());
        verify(candidateEntity).setEmployerId(Mockito.any());
        verify(candidateEntity).setExperienceLevel(Mockito.any());
        verify(candidateEntity).setFirstName(Mockito.any());
        verify(candidateEntity).setForeignLanguage(Mockito.any());
        verify(candidateEntity).setGithubLink(Mockito.any());
        verify(candidateEntity).setHobby(Mockito.any());
        verify(candidateEntity).setLastName(Mockito.any());
        verify(candidateEntity).setLinkedinLink(Mockito.any());
        verify(candidateEntity).setOpenToRemoteJob(Mockito.<Boolean>any());
        verify(candidateEntity).setOtherSkills(Mockito.any());
        verify(candidateEntity).setPhoneNumber(Mockito.any());
        verify(candidateEntity).setResidenceCityId(Mockito.any());
        verify(candidateEntity).setSalaryMin(Mockito.any());
        verify(candidateEntity).setStatus(Mockito.any());
        verify(candidateEntity).setYearsOfExperience(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link CandidateSkillEntityMapperImpl#candidateEntityToCandidate(CandidateEntity)}
     */
    @Test
    void testCandidateEntityToCandidate3() {
        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        EmployerEntity employerId = new EmployerEntity();
        employerId.setAmountOfAvailableOffers(10);
        employerId.setCityId(cityId);
        employerId.setCompanyName("Company Name");
        employerId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId.setDescription("The characteristics of someone or something");
        employerId.setEmailContact("jane.doe@example.org");
        employerId.setEmployerId(1L);
        employerId.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerId.setLogoFilename("foo.txt");
        employerId.setNumberOfEmployees(10);
        employerId.setPhoneNumber("6625550144");
        employerId.setWebsite("Website");

        CityEntity residenceCityId = new CityEntity();
        residenceCityId.setCandidateResidenceCities(new HashSet<>());
        residenceCityId.setCityId(1L);
        residenceCityId.setCityName("Oxford");
        residenceCityId.setEmployerCities(new HashSet<>());
        residenceCityId.setOfferCities(new HashSet<>());

        CityEntity cityId2 = new CityEntity();
        cityId2.setCandidateResidenceCities(new HashSet<>());
        cityId2.setCityId(1L);
        cityId2.setCityName("Oxford");
        cityId2.setEmployerCities(new HashSet<>());
        cityId2.setOfferCities(new HashSet<>());

        EmployerEntity employerId2 = new EmployerEntity();
        employerId2.setAmountOfAvailableOffers(10);
        employerId2.setCityId(cityId2);
        employerId2.setCompanyName("Company Name");
        employerId2.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId2.setDescription("The characteristics of someone or something");
        employerId2.setEmailContact("jane.doe@example.org");
        employerId2.setEmployerId(1L);
        employerId2.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerId2.setLogoFilename("foo.txt");
        employerId2.setNumberOfEmployees(10);
        employerId2.setPhoneNumber("6625550144");
        employerId2.setWebsite("Website");

        CityEntity residenceCityId2 = new CityEntity();
        residenceCityId2.setCandidateResidenceCities(new HashSet<>());
        residenceCityId2.setCityId(1L);
        residenceCityId2.setCityName("Oxford");
        residenceCityId2.setEmployerCities(new HashSet<>());
        residenceCityId2.setOfferCities(new HashSet<>());

        CandidateEntity candidateId = new CandidateEntity();
        candidateId.setCandidateId(1L);
        candidateId.setCandidateSkills(new HashSet<>());
        candidateId.setCandidateUuid("2020-03-01");
        candidateId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        candidateId.setCvFilename("foo.txt");
        candidateId.setEducation("Education");
        candidateId.setEmailContact("jane.doe@example.org");
        candidateId.setEmployerId(employerId2);
        candidateId.setExperienceLevel("Experience Level");
        candidateId.setFirstName("Jane");
        candidateId.setForeignLanguage("en");
        candidateId.setGithubLink("Github Link");
        candidateId.setHobby("Hobby");
        candidateId.setLastName("Doe");
        candidateId.setLinkedinLink("Linkedin Link");
        candidateId.setOpenToRemoteJob(true);
        candidateId.setOtherSkills("Other Skills");
        candidateId.setPhoneNumber("6625550144");
        candidateId.setResidenceCityId(residenceCityId2);
        candidateId.setSalaryMin(BigDecimal.valueOf(1L));
        candidateId.setStatus("Status");
        candidateId.setYearsOfExperience(1);

        SkillEntity skillId = new SkillEntity();
        skillId.setCandidateSkills(new HashSet<>());
        skillId.setOfferSkills(new HashSet<>());
        skillId.setSkillId(1L);
        skillId.setSkillName("Skill Name");

        CandidateSkillEntity candidateSkillEntity = new CandidateSkillEntity();
        candidateSkillEntity.setCandidateId(candidateId);
        candidateSkillEntity.setCandidateSkillId(1L);
        candidateSkillEntity.setSkillId(skillId);

        HashSet<CandidateSkillEntity> candidateSkillEntitySet = new HashSet<>();
        candidateSkillEntitySet.add(candidateSkillEntity);

        CityEntity cityEntity = new CityEntity();
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());

        CityEntity cityId3 = new CityEntity();
        cityId3.setCandidateResidenceCities(new HashSet<>());
        cityId3.setCityId(1L);
        cityId3.setCityName("Oxford");
        cityId3.setEmployerCities(new HashSet<>());
        cityId3.setOfferCities(new HashSet<>());

        EmployerEntity employerEntity = new EmployerEntity();
        employerEntity.setAmountOfAvailableOffers(10);
        employerEntity.setCityId(cityId3);
        employerEntity.setCompanyName("Company Name");
        employerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerEntity.setDescription("The characteristics of someone or something");
        employerEntity.setEmailContact("jane.doe@example.org");
        employerEntity.setEmployerId(1L);
        employerEntity.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerEntity.setLogoFilename("foo.txt");
        employerEntity.setNumberOfEmployees(10);
        employerEntity.setPhoneNumber("6625550144");
        employerEntity.setWebsite("Website");
        CandidateEntity candidateEntity = mock(CandidateEntity.class);
        when(candidateEntity.getOpenToRemoteJob()).thenReturn(true);
        when(candidateEntity.getYearsOfExperience()).thenReturn(1);
        when(candidateEntity.getCandidateId()).thenReturn(1L);
        when(candidateEntity.getCandidateUuid()).thenReturn("2020-03-01");
        when(candidateEntity.getCvFilename()).thenReturn("foo.txt");
        when(candidateEntity.getEducation()).thenReturn("Education");
        when(candidateEntity.getEmailContact()).thenReturn("jane.doe@example.org");
        when(candidateEntity.getExperienceLevel()).thenReturn("Experience Level");
        when(candidateEntity.getFirstName()).thenReturn("Jane");
        when(candidateEntity.getForeignLanguage()).thenReturn("en");
        when(candidateEntity.getGithubLink()).thenReturn("Github Link");
        when(candidateEntity.getHobby()).thenReturn("Hobby");
        when(candidateEntity.getLastName()).thenReturn("Doe");
        when(candidateEntity.getLinkedinLink()).thenReturn("Linkedin Link");
        when(candidateEntity.getOtherSkills()).thenReturn("Other Skills");
        when(candidateEntity.getPhoneNumber()).thenReturn("6625550144");
        when(candidateEntity.getPhotoFilename()).thenReturn("foo.txt");
        when(candidateEntity.getStatus()).thenReturn("Status");
        when(candidateEntity.getSalaryMin()).thenReturn(BigDecimal.valueOf(1L));
        when(candidateEntity.getCreatedAt())
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        when(candidateEntity.getCandidateSkills()).thenReturn(candidateSkillEntitySet);
        when(candidateEntity.getResidenceCityId()).thenReturn(cityEntity);
        when(candidateEntity.getEmployerId()).thenReturn(employerEntity);
        doNothing().when(candidateEntity).setCandidateId(Mockito.<Long>any());
        doNothing().when(candidateEntity).setCandidateSkills(Mockito.any());
        doNothing().when(candidateEntity).setCandidateUuid(Mockito.any());
        doNothing().when(candidateEntity).setCreatedAt(Mockito.any());
        doNothing().when(candidateEntity).setCvFilename(Mockito.any());
        doNothing().when(candidateEntity).setEducation(Mockito.any());
        doNothing().when(candidateEntity).setEmailContact(Mockito.any());
        doNothing().when(candidateEntity).setEmployerId(Mockito.any());
        doNothing().when(candidateEntity).setExperienceLevel(Mockito.any());
        doNothing().when(candidateEntity).setFirstName(Mockito.any());
        doNothing().when(candidateEntity).setForeignLanguage(Mockito.any());
        doNothing().when(candidateEntity).setGithubLink(Mockito.any());
        doNothing().when(candidateEntity).setHobby(Mockito.any());
        doNothing().when(candidateEntity).setLastName(Mockito.any());
        doNothing().when(candidateEntity).setLinkedinLink(Mockito.any());
        doNothing().when(candidateEntity).setOpenToRemoteJob(Mockito.<Boolean>any());
        doNothing().when(candidateEntity).setOtherSkills(Mockito.any());
        doNothing().when(candidateEntity).setPhoneNumber(Mockito.any());
        doNothing().when(candidateEntity).setResidenceCityId(Mockito.any());
        doNothing().when(candidateEntity).setSalaryMin(Mockito.any());
        doNothing().when(candidateEntity).setStatus(Mockito.any());
        doNothing().when(candidateEntity).setYearsOfExperience(Mockito.<Integer>any());
        candidateEntity.setCandidateId(1L);
        candidateEntity.setCandidateSkills(new HashSet<>());
        candidateEntity.setCandidateUuid("2020-03-01");
        candidateEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        candidateEntity.setCvFilename("foo.txt");
        candidateEntity.setEducation("Education");
        candidateEntity.setEmailContact("jane.doe@example.org");
        candidateEntity.setEmployerId(employerId);
        candidateEntity.setExperienceLevel("Experience Level");
        candidateEntity.setFirstName("Jane");
        candidateEntity.setForeignLanguage("en");
        candidateEntity.setGithubLink("Github Link");
        candidateEntity.setHobby("Hobby");
        candidateEntity.setLastName("Doe");
        candidateEntity.setLinkedinLink("Linkedin Link");
        candidateEntity.setOpenToRemoteJob(true);
        candidateEntity.setOtherSkills("Other Skills");
        candidateEntity.setPhoneNumber("6625550144");
        candidateEntity.setResidenceCityId(residenceCityId);
        BigDecimal salaryMin = BigDecimal.valueOf(1L);
        candidateEntity.setSalaryMin(salaryMin);
        candidateEntity.setStatus("Status");
        candidateEntity.setYearsOfExperience(1);
        Candidate actualCandidateEntityToCandidateResult = candidateSkillEntityMapperImpl
                .candidateEntityToCandidate(candidateEntity);
        assertEquals(1L, actualCandidateEntityToCandidateResult.getCandidateId().longValue());
        assertEquals(1, actualCandidateEntityToCandidateResult.getYearsOfExperience().intValue());
        assertEquals("Status", actualCandidateEntityToCandidateResult.getStatus());
        BigDecimal expectedSalaryMin = BigDecimal.ONE;
        BigDecimal salaryMin2 = actualCandidateEntityToCandidateResult.getSalaryMin();
        assertSame(expectedSalaryMin, salaryMin2);
        assertEquals(1, actualCandidateEntityToCandidateResult.getCandidateSkills().size());
        assertEquals("Z", actualCandidateEntityToCandidateResult.getCreatedAt().getOffset().toString());
        assertTrue(actualCandidateEntityToCandidateResult.getOpenToRemoteJob());
        assertEquals("Other Skills", actualCandidateEntityToCandidateResult.getOtherSkills());
        assertEquals("Experience Level", actualCandidateEntityToCandidateResult.getExperienceLevel());
        assertEquals("6625550144", actualCandidateEntityToCandidateResult.getPhoneNumber());
        assertEquals("foo.txt", actualCandidateEntityToCandidateResult.getPhotoFilename());
        assertEquals("foo.txt", actualCandidateEntityToCandidateResult.getCvFilename());
        assertEquals("Jane", actualCandidateEntityToCandidateResult.getFirstName());
        assertEquals("en", actualCandidateEntityToCandidateResult.getForeignLanguage());
        assertEquals("2020-03-01", actualCandidateEntityToCandidateResult.getCandidateUuid());
        assertEquals("Education", actualCandidateEntityToCandidateResult.getEducation());
        assertEquals("Github Link", actualCandidateEntityToCandidateResult.getGithubLink());
        assertEquals("Hobby", actualCandidateEntityToCandidateResult.getHobby());
        assertEquals("jane.doe@example.org", actualCandidateEntityToCandidateResult.getEmailContact());
        assertEquals("Doe", actualCandidateEntityToCandidateResult.getLastName());
        assertEquals("Linkedin Link", actualCandidateEntityToCandidateResult.getLinkedinLink());
        Employer employerId3 = actualCandidateEntityToCandidateResult.getEmployerId();
        assertEquals("Website", employerId3.getWebsite());
        City residenceCityId3 = actualCandidateEntityToCandidateResult.getResidenceCityId();
        assertEquals("Oxford", residenceCityId3.getCityName());
        assertEquals(1L, residenceCityId3.getCityId().longValue());
        assertEquals(10, employerId3.getAmountOfAvailableOffers().intValue());
        assertEquals("Company Name", employerId3.getCompanyName());
        assertEquals("Z", employerId3.getCreatedAt().getOffset().toString());
        assertEquals(1L, employerId3.getEmployerId().longValue());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", employerId3.getEmployerUuid());
        assertEquals(10, employerId3.getNumberOfEmployees().intValue());
        assertEquals("1", salaryMin2.toString());
        City cityId4 = employerId3.getCityId();
        assertEquals(residenceCityId3, cityId4);
        assertEquals("jane.doe@example.org", employerId3.getEmailContact());
        assertEquals("The characteristics of someone or something", employerId3.getDescription());
        assertEquals("foo.txt", employerId3.getLogoFilename());
        assertEquals("6625550144", employerId3.getPhoneNumber());
        assertEquals("Oxford", cityId4.getCityName());
        assertEquals(1L, cityId4.getCityId().longValue());
        verify(candidateEntity).getOpenToRemoteJob();
        verify(candidateEntity).getYearsOfExperience();
        verify(candidateEntity).getCandidateId();
        verify(candidateEntity).getCandidateUuid();
        verify(candidateEntity).getCvFilename();
        verify(candidateEntity).getEducation();
        verify(candidateEntity).getEmailContact();
        verify(candidateEntity).getExperienceLevel();
        verify(candidateEntity).getFirstName();
        verify(candidateEntity).getForeignLanguage();
        verify(candidateEntity).getGithubLink();
        verify(candidateEntity).getHobby();
        verify(candidateEntity).getLastName();
        verify(candidateEntity).getLinkedinLink();
        verify(candidateEntity).getOtherSkills();
        verify(candidateEntity).getPhoneNumber();
        verify(candidateEntity).getPhotoFilename();
        verify(candidateEntity).getStatus();
        verify(candidateEntity).getSalaryMin();
        verify(candidateEntity).getCreatedAt();
        verify(candidateEntity).getCandidateSkills();
        verify(candidateEntity).getResidenceCityId();
        verify(candidateEntity).getEmployerId();
        verify(candidateEntity).setCandidateId(Mockito.<Long>any());
        verify(candidateEntity).setCandidateSkills(Mockito.any());
        verify(candidateEntity).setCandidateUuid(Mockito.any());
        verify(candidateEntity).setCreatedAt(Mockito.any());
        verify(candidateEntity).setCvFilename(Mockito.any());
        verify(candidateEntity).setEducation(Mockito.any());
        verify(candidateEntity).setEmailContact(Mockito.any());
        verify(candidateEntity).setEmployerId(Mockito.any());
        verify(candidateEntity).setExperienceLevel(Mockito.any());
        verify(candidateEntity).setFirstName(Mockito.any());
        verify(candidateEntity).setForeignLanguage(Mockito.any());
        verify(candidateEntity).setGithubLink(Mockito.any());
        verify(candidateEntity).setHobby(Mockito.any());
        verify(candidateEntity).setLastName(Mockito.any());
        verify(candidateEntity).setLinkedinLink(Mockito.any());
        verify(candidateEntity).setOpenToRemoteJob(Mockito.<Boolean>any());
        verify(candidateEntity).setOtherSkills(Mockito.any());
        verify(candidateEntity).setPhoneNumber(Mockito.any());
        verify(candidateEntity).setResidenceCityId(Mockito.any());
        verify(candidateEntity).setSalaryMin(Mockito.any());
        verify(candidateEntity).setStatus(Mockito.any());
        verify(candidateEntity).setYearsOfExperience(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link CandidateSkillEntityMapperImpl#candidateEntityToCandidate(CandidateEntity)}
     */
    @Test
    void testCandidateEntityToCandidate4() {
        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        EmployerEntity employerId = new EmployerEntity();
        employerId.setAmountOfAvailableOffers(10);
        employerId.setCityId(cityId);
        employerId.setCompanyName("Company Name");
        employerId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId.setDescription("The characteristics of someone or something");
        employerId.setEmailContact("jane.doe@example.org");
        employerId.setEmployerId(1L);
        employerId.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerId.setLogoFilename("foo.txt");
        employerId.setNumberOfEmployees(10);
        employerId.setPhoneNumber("6625550144");
        employerId.setWebsite("Website");

        CityEntity residenceCityId = new CityEntity();
        residenceCityId.setCandidateResidenceCities(new HashSet<>());
        residenceCityId.setCityId(1L);
        residenceCityId.setCityName("Oxford");
        residenceCityId.setEmployerCities(new HashSet<>());
        residenceCityId.setOfferCities(new HashSet<>());
        CityEntity cityEntity = mock(CityEntity.class);
        when(cityEntity.getCityId()).thenReturn(1L);
        when(cityEntity.getCityName()).thenReturn("Oxford");
        doNothing().when(cityEntity).setCandidateResidenceCities(Mockito.any());
        doNothing().when(cityEntity).setCityId(Mockito.<Long>any());
        doNothing().when(cityEntity).setCityName(Mockito.any());
        doNothing().when(cityEntity).setEmployerCities(Mockito.any());
        doNothing().when(cityEntity).setOfferCities(Mockito.any());
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());

        CityEntity cityId2 = new CityEntity();
        cityId2.setCandidateResidenceCities(new HashSet<>());
        cityId2.setCityId(1L);
        cityId2.setCityName("Oxford");
        cityId2.setEmployerCities(new HashSet<>());
        cityId2.setOfferCities(new HashSet<>());

        EmployerEntity employerEntity = new EmployerEntity();
        employerEntity.setAmountOfAvailableOffers(10);
        employerEntity.setCityId(cityId2);
        employerEntity.setCompanyName("Company Name");
        employerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerEntity.setDescription("The characteristics of someone or something");
        employerEntity.setEmailContact("jane.doe@example.org");
        employerEntity.setEmployerId(1L);
        employerEntity.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerEntity.setLogoFilename("foo.txt");
        employerEntity.setNumberOfEmployees(10);
        employerEntity.setPhoneNumber("6625550144");
        employerEntity.setWebsite("Website");
        CandidateEntity candidateEntity = mock(CandidateEntity.class);
        when(candidateEntity.getOpenToRemoteJob()).thenReturn(true);
        when(candidateEntity.getYearsOfExperience()).thenReturn(1);
        when(candidateEntity.getCandidateId()).thenReturn(1L);
        when(candidateEntity.getCandidateUuid()).thenReturn("2020-03-01");
        when(candidateEntity.getCvFilename()).thenReturn("foo.txt");
        when(candidateEntity.getEducation()).thenReturn("Education");
        when(candidateEntity.getEmailContact()).thenReturn("jane.doe@example.org");
        when(candidateEntity.getExperienceLevel()).thenReturn("Experience Level");
        when(candidateEntity.getFirstName()).thenReturn("Jane");
        when(candidateEntity.getForeignLanguage()).thenReturn("en");
        when(candidateEntity.getGithubLink()).thenReturn("Github Link");
        when(candidateEntity.getHobby()).thenReturn("Hobby");
        when(candidateEntity.getLastName()).thenReturn("Doe");
        when(candidateEntity.getLinkedinLink()).thenReturn("Linkedin Link");
        when(candidateEntity.getOtherSkills()).thenReturn("Other Skills");
        when(candidateEntity.getPhoneNumber()).thenReturn("6625550144");
        when(candidateEntity.getPhotoFilename()).thenReturn("foo.txt");
        when(candidateEntity.getStatus()).thenReturn("Status");
        when(candidateEntity.getSalaryMin()).thenReturn(BigDecimal.valueOf(1L));
        when(candidateEntity.getCreatedAt())
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        when(candidateEntity.getCandidateSkills()).thenReturn(new HashSet<>());
        when(candidateEntity.getResidenceCityId()).thenReturn(cityEntity);
        when(candidateEntity.getEmployerId()).thenReturn(employerEntity);
        doNothing().when(candidateEntity).setCandidateId(Mockito.<Long>any());
        doNothing().when(candidateEntity).setCandidateSkills(Mockito.any());
        doNothing().when(candidateEntity).setCandidateUuid(Mockito.any());
        doNothing().when(candidateEntity).setCreatedAt(Mockito.any());
        doNothing().when(candidateEntity).setCvFilename(Mockito.any());
        doNothing().when(candidateEntity).setEducation(Mockito.any());
        doNothing().when(candidateEntity).setEmailContact(Mockito.any());
        doNothing().when(candidateEntity).setEmployerId(Mockito.any());
        doNothing().when(candidateEntity).setExperienceLevel(Mockito.any());
        doNothing().when(candidateEntity).setFirstName(Mockito.any());
        doNothing().when(candidateEntity).setForeignLanguage(Mockito.any());
        doNothing().when(candidateEntity).setGithubLink(Mockito.any());
        doNothing().when(candidateEntity).setHobby(Mockito.any());
        doNothing().when(candidateEntity).setLastName(Mockito.any());
        doNothing().when(candidateEntity).setLinkedinLink(Mockito.any());
        doNothing().when(candidateEntity).setOpenToRemoteJob(Mockito.<Boolean>any());
        doNothing().when(candidateEntity).setOtherSkills(Mockito.any());
        doNothing().when(candidateEntity).setPhoneNumber(Mockito.any());
        doNothing().when(candidateEntity).setResidenceCityId(Mockito.any());
        doNothing().when(candidateEntity).setSalaryMin(Mockito.any());
        doNothing().when(candidateEntity).setStatus(Mockito.any());
        doNothing().when(candidateEntity).setYearsOfExperience(Mockito.<Integer>any());
        candidateEntity.setCandidateId(1L);
        candidateEntity.setCandidateSkills(new HashSet<>());
        candidateEntity.setCandidateUuid("2020-03-01");
        candidateEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        candidateEntity.setCvFilename("foo.txt");
        candidateEntity.setEducation("Education");
        candidateEntity.setEmailContact("jane.doe@example.org");
        candidateEntity.setEmployerId(employerId);
        candidateEntity.setExperienceLevel("Experience Level");
        candidateEntity.setFirstName("Jane");
        candidateEntity.setForeignLanguage("en");
        candidateEntity.setGithubLink("Github Link");
        candidateEntity.setHobby("Hobby");
        candidateEntity.setLastName("Doe");
        candidateEntity.setLinkedinLink("Linkedin Link");
        candidateEntity.setOpenToRemoteJob(true);
        candidateEntity.setOtherSkills("Other Skills");
        candidateEntity.setPhoneNumber("6625550144");
        candidateEntity.setResidenceCityId(residenceCityId);
        BigDecimal salaryMin = BigDecimal.valueOf(1L);
        candidateEntity.setSalaryMin(salaryMin);
        candidateEntity.setStatus("Status");
        candidateEntity.setYearsOfExperience(1);
        Candidate actualCandidateEntityToCandidateResult = candidateSkillEntityMapperImpl
                .candidateEntityToCandidate(candidateEntity);
        assertEquals(1L, actualCandidateEntityToCandidateResult.getCandidateId().longValue());
        assertEquals(1, actualCandidateEntityToCandidateResult.getYearsOfExperience().intValue());
        assertEquals("Status", actualCandidateEntityToCandidateResult.getStatus());
        BigDecimal expectedSalaryMin = BigDecimal.ONE;
        BigDecimal salaryMin2 = actualCandidateEntityToCandidateResult.getSalaryMin();
        assertSame(expectedSalaryMin, salaryMin2);
        assertTrue(actualCandidateEntityToCandidateResult.getCandidateSkills().isEmpty());
        assertEquals("Z", actualCandidateEntityToCandidateResult.getCreatedAt().getOffset().toString());
        assertTrue(actualCandidateEntityToCandidateResult.getOpenToRemoteJob());
        assertEquals("Other Skills", actualCandidateEntityToCandidateResult.getOtherSkills());
        assertEquals("Experience Level", actualCandidateEntityToCandidateResult.getExperienceLevel());
        assertEquals("6625550144", actualCandidateEntityToCandidateResult.getPhoneNumber());
        assertEquals("foo.txt", actualCandidateEntityToCandidateResult.getPhotoFilename());
        assertEquals("foo.txt", actualCandidateEntityToCandidateResult.getCvFilename());
        assertEquals("Jane", actualCandidateEntityToCandidateResult.getFirstName());
        assertEquals("en", actualCandidateEntityToCandidateResult.getForeignLanguage());
        assertEquals("2020-03-01", actualCandidateEntityToCandidateResult.getCandidateUuid());
        assertEquals("Education", actualCandidateEntityToCandidateResult.getEducation());
        assertEquals("Github Link", actualCandidateEntityToCandidateResult.getGithubLink());
        assertEquals("Hobby", actualCandidateEntityToCandidateResult.getHobby());
        assertEquals("jane.doe@example.org", actualCandidateEntityToCandidateResult.getEmailContact());
        assertEquals("Doe", actualCandidateEntityToCandidateResult.getLastName());
        assertEquals("Linkedin Link", actualCandidateEntityToCandidateResult.getLinkedinLink());
        Employer employerId2 = actualCandidateEntityToCandidateResult.getEmployerId();
        assertEquals("Website", employerId2.getWebsite());
        City residenceCityId2 = actualCandidateEntityToCandidateResult.getResidenceCityId();
        assertEquals("Oxford", residenceCityId2.getCityName());
        assertEquals(1L, residenceCityId2.getCityId().longValue());
        assertEquals(10, employerId2.getAmountOfAvailableOffers().intValue());
        assertEquals("Company Name", employerId2.getCompanyName());
        assertEquals("Z", employerId2.getCreatedAt().getOffset().toString());
        assertEquals(1L, employerId2.getEmployerId().longValue());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", employerId2.getEmployerUuid());
        assertEquals(10, employerId2.getNumberOfEmployees().intValue());
        assertEquals("1", salaryMin2.toString());
        City cityId3 = employerId2.getCityId();
        assertEquals(residenceCityId2, cityId3);
        assertEquals("jane.doe@example.org", employerId2.getEmailContact());
        assertEquals("The characteristics of someone or something", employerId2.getDescription());
        assertEquals("foo.txt", employerId2.getLogoFilename());
        assertEquals("6625550144", employerId2.getPhoneNumber());
        assertEquals("Oxford", cityId3.getCityName());
        assertEquals(1L, cityId3.getCityId().longValue());
        verify(candidateEntity).getOpenToRemoteJob();
        verify(candidateEntity).getYearsOfExperience();
        verify(candidateEntity).getCandidateId();
        verify(candidateEntity).getCandidateUuid();
        verify(candidateEntity).getCvFilename();
        verify(candidateEntity).getEducation();
        verify(candidateEntity).getEmailContact();
        verify(candidateEntity).getExperienceLevel();
        verify(candidateEntity).getFirstName();
        verify(candidateEntity).getForeignLanguage();
        verify(candidateEntity).getGithubLink();
        verify(candidateEntity).getHobby();
        verify(candidateEntity).getLastName();
        verify(candidateEntity).getLinkedinLink();
        verify(candidateEntity).getOtherSkills();
        verify(candidateEntity).getPhoneNumber();
        verify(candidateEntity).getPhotoFilename();
        verify(candidateEntity).getStatus();
        verify(candidateEntity).getSalaryMin();
        verify(candidateEntity).getCreatedAt();
        verify(candidateEntity).getCandidateSkills();
        verify(candidateEntity).getResidenceCityId();
        verify(candidateEntity).getEmployerId();
        verify(candidateEntity).setCandidateId(Mockito.<Long>any());
        verify(candidateEntity).setCandidateSkills(Mockito.any());
        verify(candidateEntity).setCandidateUuid(Mockito.any());
        verify(candidateEntity).setCreatedAt(Mockito.any());
        verify(candidateEntity).setCvFilename(Mockito.any());
        verify(candidateEntity).setEducation(Mockito.any());
        verify(candidateEntity).setEmailContact(Mockito.any());
        verify(candidateEntity).setEmployerId(Mockito.any());
        verify(candidateEntity).setExperienceLevel(Mockito.any());
        verify(candidateEntity).setFirstName(Mockito.any());
        verify(candidateEntity).setForeignLanguage(Mockito.any());
        verify(candidateEntity).setGithubLink(Mockito.any());
        verify(candidateEntity).setHobby(Mockito.any());
        verify(candidateEntity).setLastName(Mockito.any());
        verify(candidateEntity).setLinkedinLink(Mockito.any());
        verify(candidateEntity).setOpenToRemoteJob(Mockito.<Boolean>any());
        verify(candidateEntity).setOtherSkills(Mockito.any());
        verify(candidateEntity).setPhoneNumber(Mockito.any());
        verify(candidateEntity).setResidenceCityId(Mockito.any());
        verify(candidateEntity).setSalaryMin(Mockito.any());
        verify(candidateEntity).setStatus(Mockito.any());
        verify(candidateEntity).setYearsOfExperience(Mockito.<Integer>any());
        verify(cityEntity).getCityId();
        verify(cityEntity).getCityName();
        verify(cityEntity).setCandidateResidenceCities(Mockito.any());
        verify(cityEntity).setCityId(Mockito.<Long>any());
        verify(cityEntity).setCityName(Mockito.any());
        verify(cityEntity).setEmployerCities(Mockito.any());
        verify(cityEntity).setOfferCities(Mockito.any());
    }

    /**
     * Method under test: {@link CandidateSkillEntityMapperImpl#skillEntityToSkill(SkillEntity)}
     */
    @Test
    void testSkillEntityToSkill() {
        SkillEntity skillEntity = new SkillEntity();
        skillEntity.setCandidateSkills(new HashSet<>());
        skillEntity.setOfferSkills(new HashSet<>());
        skillEntity.setSkillId(1L);
        skillEntity.setSkillName("Skill Name");
        Skill actualSkillEntityToSkillResult = candidateSkillEntityMapperImpl.skillEntityToSkill(skillEntity);
        assertEquals(1L, actualSkillEntityToSkillResult.getSkillId().longValue());
        assertEquals("Skill Name", actualSkillEntityToSkillResult.getSkillName());
    }

    /**
     * Method under test: {@link CandidateSkillEntityMapperImpl#skillEntityToSkill(SkillEntity)}
     */
    @Test
    void testSkillEntityToSkill2() {
        SkillEntity skillEntity = mock(SkillEntity.class);
        when(skillEntity.getSkillId()).thenReturn(1L);
        when(skillEntity.getSkillName()).thenReturn("Skill Name");
        doNothing().when(skillEntity).setCandidateSkills(Mockito.any());
        doNothing().when(skillEntity).setOfferSkills(Mockito.any());
        doNothing().when(skillEntity).setSkillId(Mockito.<Long>any());
        doNothing().when(skillEntity).setSkillName(Mockito.any());
        skillEntity.setCandidateSkills(new HashSet<>());
        skillEntity.setOfferSkills(new HashSet<>());
        skillEntity.setSkillId(1L);
        skillEntity.setSkillName("Skill Name");
        Skill actualSkillEntityToSkillResult = candidateSkillEntityMapperImpl.skillEntityToSkill(skillEntity);
        assertEquals(1L, actualSkillEntityToSkillResult.getSkillId().longValue());
        assertEquals("Skill Name", actualSkillEntityToSkillResult.getSkillName());
        verify(skillEntity).getSkillId();
        verify(skillEntity).getSkillName();
        verify(skillEntity).setCandidateSkills(Mockito.any());
        verify(skillEntity).setOfferSkills(Mockito.any());
        verify(skillEntity).setSkillId(Mockito.<Long>any());
        verify(skillEntity).setSkillName(Mockito.any());
    }

    /**
     * Method under test: {@link CandidateSkillEntityMapperImpl#cityToCityEntity(City)}
     */
    @Test
    void testCityToCityEntity() {
        assertNull(candidateSkillEntityMapperImpl.cityToCityEntity(null));
    }

    /**
     * Method under test: {@link CandidateSkillEntityMapperImpl#employerToEmployerEntity(Employer)}
     */
    @Test
    void testEmployerToEmployerEntity() {
        assertNull(candidateSkillEntityMapperImpl.employerToEmployerEntity(null));
    }

    /**
     * Method under test: {@link CandidateSkillEntityMapperImpl#candidateSkillSetToCandidateSkillEntitySet(Set)}
     */
    @Test
    void testCandidateSkillSetToCandidateSkillEntitySet() {
        assertTrue(candidateSkillEntityMapperImpl.candidateSkillSetToCandidateSkillEntitySet(new HashSet<>()).isEmpty());
    }

    /**
     * Method under test: {@link CandidateSkillEntityMapperImpl#candidateSkillSetToCandidateSkillEntitySet(Set)}
     */
    @Test
    void testCandidateSkillSetToCandidateSkillEntitySet2() {
        HashSet<CandidateSkill> set = new HashSet<>();
        set.add(new CandidateSkill(1L, null, null));
        assertEquals(1, candidateSkillEntityMapperImpl.candidateSkillSetToCandidateSkillEntitySet(set).size());
    }

    /**
     * Method under test: {@link CandidateSkillEntityMapperImpl#candidateSkillSetToCandidateSkillEntitySet(Set)}
     */
    @Test
    void testCandidateSkillSetToCandidateSkillEntitySet3() {
        HashSet<CandidateSkill> set = new HashSet<>();
        set.add(new CandidateSkill(null, null, null));
        assertEquals(1, candidateSkillEntityMapperImpl.candidateSkillSetToCandidateSkillEntitySet(set).size());
    }

    /**
     * Method under test: {@link CandidateSkillEntityMapperImpl#candidateSkillSetToCandidateSkillEntitySet(Set)}
     */
    @Test
    void testCandidateSkillSetToCandidateSkillEntitySet4() {
        HashSet<CandidateSkill> set = new HashSet<>();
        set.add(new CandidateSkill(2L, null, null));
        set.add(new CandidateSkill(1L, null, null));
        assertEquals(2, candidateSkillEntityMapperImpl.candidateSkillSetToCandidateSkillEntitySet(set).size());
    }

    /**
     * Method under test: {@link CandidateSkillEntityMapperImpl#candidateSkillSetToCandidateSkillEntitySet(Set)}
     */
    @Test
    void testCandidateSkillSetToCandidateSkillEntitySet5() {
        HashSet<CandidateSkill> set = new HashSet<>();
        set.add(null);
        assertEquals(1, candidateSkillEntityMapperImpl.candidateSkillSetToCandidateSkillEntitySet(set).size());
    }

    /**
     * Method under test: {@link CandidateSkillEntityMapperImpl#candidateToCandidateEntity(Candidate)}
     */
    @Test
    void testCandidateToCandidateEntity() {
        assertNull(candidateSkillEntityMapperImpl.candidateToCandidateEntity(null));
    }

    /**
     * Method under test: {@link CandidateSkillEntityMapperImpl#skillToSkillEntity(Skill)}
     */
    @Test
    void testSkillToSkillEntity() {
        assertNull(candidateSkillEntityMapperImpl.skillToSkillEntity(null));
    }
}

