package pl.devfinder.infrastructure.database.repository.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.CandidateSkill;
import pl.devfinder.domain.City;
import pl.devfinder.domain.Employer;
import pl.devfinder.domain.Skill;
import pl.devfinder.infrastructure.database.entity.CandidateEntity;
import pl.devfinder.infrastructure.database.entity.CandidateSkillEntity;
import pl.devfinder.infrastructure.database.entity.CityEntity;
import pl.devfinder.infrastructure.database.entity.EmployerEntity;
import pl.devfinder.infrastructure.database.entity.OfferEntity;
import pl.devfinder.infrastructure.database.entity.OfferSkillEntity;
import pl.devfinder.infrastructure.database.entity.SkillEntity;

@ContextConfiguration(classes = {CandidateEntityMapperImpl.class})
@ExtendWith(SpringExtension.class)
class CandidateEntityMapperImplDiffBlueTest {
    @Autowired
    private CandidateEntityMapperImpl candidateEntityMapperImpl;

    /**
     * Method under test: {@link CandidateEntityMapperImpl#mapFromEntity(CandidateEntity)}
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
        Candidate actualMapFromEntityResult = candidateEntityMapperImpl.mapFromEntity(candidateEntity);
        assertEquals(1L, actualMapFromEntityResult.getCandidateId().longValue());
        assertEquals(1, actualMapFromEntityResult.getYearsOfExperience().intValue());
        assertEquals("Status", actualMapFromEntityResult.getStatus());
        BigDecimal expectedSalaryMin = salaryMin.ONE;
        BigDecimal salaryMin2 = actualMapFromEntityResult.getSalaryMin();
        assertSame(expectedSalaryMin, salaryMin2);
        assertTrue(actualMapFromEntityResult.getCandidateSkills().isEmpty());
        assertEquals("Z", actualMapFromEntityResult.getCreatedAt().getOffset().toString());
        assertTrue(actualMapFromEntityResult.getOpenToRemoteJob());
        assertEquals("Other Skills", actualMapFromEntityResult.getOtherSkills());
        assertEquals("Experience Level", actualMapFromEntityResult.getExperienceLevel());
        assertEquals("6625550144", actualMapFromEntityResult.getPhoneNumber());
        assertNull(actualMapFromEntityResult.getPhotoFilename());
        assertEquals("foo.txt", actualMapFromEntityResult.getCvFilename());
        assertEquals("Jane", actualMapFromEntityResult.getFirstName());
        assertEquals("en", actualMapFromEntityResult.getForeignLanguage());
        assertEquals("2020-03-01", actualMapFromEntityResult.getCandidateUuid());
        assertEquals("Education", actualMapFromEntityResult.getEducation());
        assertEquals("Github Link", actualMapFromEntityResult.getGithubLink());
        assertEquals("Hobby", actualMapFromEntityResult.getHobby());
        assertEquals("jane.doe@example.org", actualMapFromEntityResult.getEmailContact());
        assertEquals("Doe", actualMapFromEntityResult.getLastName());
        assertEquals("Linkedin Link", actualMapFromEntityResult.getLinkedinLink());
        Employer employerId2 = actualMapFromEntityResult.getEmployerId();
        assertEquals("Website", employerId2.getWebsite());
        City residenceCityId2 = actualMapFromEntityResult.getResidenceCityId();
        assertEquals("Oxford", residenceCityId2.getCityName());
        assertEquals(1L, residenceCityId2.getCityId().longValue());
        assertEquals(10, employerId2.getAmountOfAvailableOffers().intValue());
        assertEquals("Company Name", employerId2.getCompanyName());
        assertEquals("Z", employerId2.getCreatedAt().getOffset().toString());
        assertEquals(1L, employerId2.getEmployerId().longValue());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", employerId2.getEmployerUuid());
        assertEquals(10, employerId2.getNumberOfEmployees().intValue());
        assertEquals("1", salaryMin2.toString());
        assertNull(employerId2.getCityId());
        assertEquals("jane.doe@example.org", employerId2.getEmailContact());
        assertEquals("foo.txt", employerId2.getLogoFilename());
        assertEquals("6625550144", employerId2.getPhoneNumber());
        assertEquals("The characteristics of someone or something", employerId2.getDescription());
    }

    /**
     * Method under test: {@link CandidateEntityMapperImpl#mapFromEntity(CandidateEntity)}
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
        doNothing().when(candidateEntity).setCandidateSkills(Mockito.<Set<CandidateSkillEntity>>any());
        doNothing().when(candidateEntity).setCandidateUuid(Mockito.<String>any());
        doNothing().when(candidateEntity).setCreatedAt(Mockito.<OffsetDateTime>any());
        doNothing().when(candidateEntity).setCvFilename(Mockito.<String>any());
        doNothing().when(candidateEntity).setEducation(Mockito.<String>any());
        doNothing().when(candidateEntity).setEmailContact(Mockito.<String>any());
        doNothing().when(candidateEntity).setEmployerId(Mockito.<EmployerEntity>any());
        doNothing().when(candidateEntity).setExperienceLevel(Mockito.<String>any());
        doNothing().when(candidateEntity).setFirstName(Mockito.<String>any());
        doNothing().when(candidateEntity).setForeignLanguage(Mockito.<String>any());
        doNothing().when(candidateEntity).setGithubLink(Mockito.<String>any());
        doNothing().when(candidateEntity).setHobby(Mockito.<String>any());
        doNothing().when(candidateEntity).setLastName(Mockito.<String>any());
        doNothing().when(candidateEntity).setLinkedinLink(Mockito.<String>any());
        doNothing().when(candidateEntity).setOpenToRemoteJob(Mockito.<Boolean>any());
        doNothing().when(candidateEntity).setOtherSkills(Mockito.<String>any());
        doNothing().when(candidateEntity).setPhoneNumber(Mockito.<String>any());
        doNothing().when(candidateEntity).setResidenceCityId(Mockito.<CityEntity>any());
        doNothing().when(candidateEntity).setSalaryMin(Mockito.<BigDecimal>any());
        doNothing().when(candidateEntity).setStatus(Mockito.<String>any());
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
        Candidate actualMapFromEntityResult = candidateEntityMapperImpl.mapFromEntity(candidateEntity);
        assertEquals(1L, actualMapFromEntityResult.getCandidateId().longValue());
        assertEquals(1, actualMapFromEntityResult.getYearsOfExperience().intValue());
        assertEquals("Status", actualMapFromEntityResult.getStatus());
        BigDecimal expectedSalaryMin = salaryMin.ONE;
        BigDecimal salaryMin2 = actualMapFromEntityResult.getSalaryMin();
        assertSame(expectedSalaryMin, salaryMin2);
        assertTrue(actualMapFromEntityResult.getCandidateSkills().isEmpty());
        assertEquals("Z", actualMapFromEntityResult.getCreatedAt().getOffset().toString());
        assertTrue(actualMapFromEntityResult.getOpenToRemoteJob());
        assertEquals("Other Skills", actualMapFromEntityResult.getOtherSkills());
        assertEquals("Experience Level", actualMapFromEntityResult.getExperienceLevel());
        assertEquals("6625550144", actualMapFromEntityResult.getPhoneNumber());
        assertEquals("foo.txt", actualMapFromEntityResult.getPhotoFilename());
        assertEquals("foo.txt", actualMapFromEntityResult.getCvFilename());
        assertEquals("Jane", actualMapFromEntityResult.getFirstName());
        assertEquals("en", actualMapFromEntityResult.getForeignLanguage());
        assertEquals("2020-03-01", actualMapFromEntityResult.getCandidateUuid());
        assertEquals("Education", actualMapFromEntityResult.getEducation());
        assertEquals("Github Link", actualMapFromEntityResult.getGithubLink());
        assertEquals("Hobby", actualMapFromEntityResult.getHobby());
        assertEquals("jane.doe@example.org", actualMapFromEntityResult.getEmailContact());
        assertEquals("Doe", actualMapFromEntityResult.getLastName());
        assertEquals("Linkedin Link", actualMapFromEntityResult.getLinkedinLink());
        Employer employerId2 = actualMapFromEntityResult.getEmployerId();
        assertEquals("Website", employerId2.getWebsite());
        City residenceCityId2 = actualMapFromEntityResult.getResidenceCityId();
        assertEquals("Oxford", residenceCityId2.getCityName());
        assertEquals(1L, residenceCityId2.getCityId().longValue());
        assertEquals(10, employerId2.getAmountOfAvailableOffers().intValue());
        assertEquals("Company Name", employerId2.getCompanyName());
        assertEquals("Z", employerId2.getCreatedAt().getOffset().toString());
        assertEquals(1L, employerId2.getEmployerId().longValue());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", employerId2.getEmployerUuid());
        assertEquals(10, employerId2.getNumberOfEmployees().intValue());
        assertEquals("1", salaryMin2.toString());
        assertNull(employerId2.getCityId());
        assertEquals("jane.doe@example.org", employerId2.getEmailContact());
        assertEquals("foo.txt", employerId2.getLogoFilename());
        assertEquals("6625550144", employerId2.getPhoneNumber());
        assertEquals("The characteristics of someone or something", employerId2.getDescription());
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
        verify(candidateEntity).setCandidateSkills(Mockito.<Set<CandidateSkillEntity>>any());
        verify(candidateEntity).setCandidateUuid(Mockito.<String>any());
        verify(candidateEntity).setCreatedAt(Mockito.<OffsetDateTime>any());
        verify(candidateEntity).setCvFilename(Mockito.<String>any());
        verify(candidateEntity).setEducation(Mockito.<String>any());
        verify(candidateEntity).setEmailContact(Mockito.<String>any());
        verify(candidateEntity).setEmployerId(Mockito.<EmployerEntity>any());
        verify(candidateEntity).setExperienceLevel(Mockito.<String>any());
        verify(candidateEntity).setFirstName(Mockito.<String>any());
        verify(candidateEntity).setForeignLanguage(Mockito.<String>any());
        verify(candidateEntity).setGithubLink(Mockito.<String>any());
        verify(candidateEntity).setHobby(Mockito.<String>any());
        verify(candidateEntity).setLastName(Mockito.<String>any());
        verify(candidateEntity).setLinkedinLink(Mockito.<String>any());
        verify(candidateEntity).setOpenToRemoteJob(Mockito.<Boolean>any());
        verify(candidateEntity).setOtherSkills(Mockito.<String>any());
        verify(candidateEntity).setPhoneNumber(Mockito.<String>any());
        verify(candidateEntity).setResidenceCityId(Mockito.<CityEntity>any());
        verify(candidateEntity).setSalaryMin(Mockito.<BigDecimal>any());
        verify(candidateEntity).setStatus(Mockito.<String>any());
        verify(candidateEntity).setYearsOfExperience(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link CandidateEntityMapperImpl#mapFromEntity(CandidateEntity)}
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
        doNothing().when(candidateEntity).setCandidateSkills(Mockito.<Set<CandidateSkillEntity>>any());
        doNothing().when(candidateEntity).setCandidateUuid(Mockito.<String>any());
        doNothing().when(candidateEntity).setCreatedAt(Mockito.<OffsetDateTime>any());
        doNothing().when(candidateEntity).setCvFilename(Mockito.<String>any());
        doNothing().when(candidateEntity).setEducation(Mockito.<String>any());
        doNothing().when(candidateEntity).setEmailContact(Mockito.<String>any());
        doNothing().when(candidateEntity).setEmployerId(Mockito.<EmployerEntity>any());
        doNothing().when(candidateEntity).setExperienceLevel(Mockito.<String>any());
        doNothing().when(candidateEntity).setFirstName(Mockito.<String>any());
        doNothing().when(candidateEntity).setForeignLanguage(Mockito.<String>any());
        doNothing().when(candidateEntity).setGithubLink(Mockito.<String>any());
        doNothing().when(candidateEntity).setHobby(Mockito.<String>any());
        doNothing().when(candidateEntity).setLastName(Mockito.<String>any());
        doNothing().when(candidateEntity).setLinkedinLink(Mockito.<String>any());
        doNothing().when(candidateEntity).setOpenToRemoteJob(Mockito.<Boolean>any());
        doNothing().when(candidateEntity).setOtherSkills(Mockito.<String>any());
        doNothing().when(candidateEntity).setPhoneNumber(Mockito.<String>any());
        doNothing().when(candidateEntity).setResidenceCityId(Mockito.<CityEntity>any());
        doNothing().when(candidateEntity).setSalaryMin(Mockito.<BigDecimal>any());
        doNothing().when(candidateEntity).setStatus(Mockito.<String>any());
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
        Candidate actualMapFromEntityResult = candidateEntityMapperImpl.mapFromEntity(candidateEntity);
        assertEquals(1L, actualMapFromEntityResult.getCandidateId().longValue());
        assertEquals(1, actualMapFromEntityResult.getYearsOfExperience().intValue());
        assertEquals("Status", actualMapFromEntityResult.getStatus());
        BigDecimal expectedSalaryMin = salaryMin.ONE;
        BigDecimal salaryMin2 = actualMapFromEntityResult.getSalaryMin();
        assertSame(expectedSalaryMin, salaryMin2);
        assertEquals(1, actualMapFromEntityResult.getCandidateSkills().size());
        assertEquals("Z", actualMapFromEntityResult.getCreatedAt().getOffset().toString());
        assertTrue(actualMapFromEntityResult.getOpenToRemoteJob());
        assertEquals("Other Skills", actualMapFromEntityResult.getOtherSkills());
        assertEquals("Experience Level", actualMapFromEntityResult.getExperienceLevel());
        assertEquals("6625550144", actualMapFromEntityResult.getPhoneNumber());
        assertEquals("foo.txt", actualMapFromEntityResult.getPhotoFilename());
        assertEquals("foo.txt", actualMapFromEntityResult.getCvFilename());
        assertEquals("Jane", actualMapFromEntityResult.getFirstName());
        assertEquals("en", actualMapFromEntityResult.getForeignLanguage());
        assertEquals("2020-03-01", actualMapFromEntityResult.getCandidateUuid());
        assertEquals("Education", actualMapFromEntityResult.getEducation());
        assertEquals("Github Link", actualMapFromEntityResult.getGithubLink());
        assertEquals("Hobby", actualMapFromEntityResult.getHobby());
        assertEquals("jane.doe@example.org", actualMapFromEntityResult.getEmailContact());
        assertEquals("Doe", actualMapFromEntityResult.getLastName());
        assertEquals("Linkedin Link", actualMapFromEntityResult.getLinkedinLink());
        Employer employerId3 = actualMapFromEntityResult.getEmployerId();
        assertEquals("Website", employerId3.getWebsite());
        City residenceCityId3 = actualMapFromEntityResult.getResidenceCityId();
        assertEquals("Oxford", residenceCityId3.getCityName());
        assertEquals(1L, residenceCityId3.getCityId().longValue());
        assertEquals(10, employerId3.getAmountOfAvailableOffers().intValue());
        assertEquals("Company Name", employerId3.getCompanyName());
        assertEquals("Z", employerId3.getCreatedAt().getOffset().toString());
        assertEquals(1L, employerId3.getEmployerId().longValue());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", employerId3.getEmployerUuid());
        assertEquals(10, employerId3.getNumberOfEmployees().intValue());
        assertEquals("1", salaryMin2.toString());
        assertNull(employerId3.getCityId());
        assertEquals("jane.doe@example.org", employerId3.getEmailContact());
        assertEquals("foo.txt", employerId3.getLogoFilename());
        assertEquals("6625550144", employerId3.getPhoneNumber());
        assertEquals("The characteristics of someone or something", employerId3.getDescription());
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
        verify(candidateEntity).setCandidateSkills(Mockito.<Set<CandidateSkillEntity>>any());
        verify(candidateEntity).setCandidateUuid(Mockito.<String>any());
        verify(candidateEntity).setCreatedAt(Mockito.<OffsetDateTime>any());
        verify(candidateEntity).setCvFilename(Mockito.<String>any());
        verify(candidateEntity).setEducation(Mockito.<String>any());
        verify(candidateEntity).setEmailContact(Mockito.<String>any());
        verify(candidateEntity).setEmployerId(Mockito.<EmployerEntity>any());
        verify(candidateEntity).setExperienceLevel(Mockito.<String>any());
        verify(candidateEntity).setFirstName(Mockito.<String>any());
        verify(candidateEntity).setForeignLanguage(Mockito.<String>any());
        verify(candidateEntity).setGithubLink(Mockito.<String>any());
        verify(candidateEntity).setHobby(Mockito.<String>any());
        verify(candidateEntity).setLastName(Mockito.<String>any());
        verify(candidateEntity).setLinkedinLink(Mockito.<String>any());
        verify(candidateEntity).setOpenToRemoteJob(Mockito.<Boolean>any());
        verify(candidateEntity).setOtherSkills(Mockito.<String>any());
        verify(candidateEntity).setPhoneNumber(Mockito.<String>any());
        verify(candidateEntity).setResidenceCityId(Mockito.<CityEntity>any());
        verify(candidateEntity).setSalaryMin(Mockito.<BigDecimal>any());
        verify(candidateEntity).setStatus(Mockito.<String>any());
        verify(candidateEntity).setYearsOfExperience(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link CandidateEntityMapperImpl#mapFromEntity(CandidateEntity)}
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

        CityEntity cityId3 = new CityEntity();
        cityId3.setCandidateResidenceCities(new HashSet<>());
        cityId3.setCityId(2L);
        cityId3.setCityName("London");
        cityId3.setEmployerCities(new HashSet<>());
        cityId3.setOfferCities(new HashSet<>());

        EmployerEntity employerId3 = new EmployerEntity();
        employerId3.setAmountOfAvailableOffers(59);
        employerId3.setCityId(cityId3);
        employerId3.setCompanyName("pl.devfinder.infrastructure.database.entity.EmployerEntity");
        employerId3.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId3.setDescription("Description");
        employerId3.setEmailContact("john.smith@example.org");
        employerId3.setEmployerId(2L);
        employerId3.setEmployerUuid("1234");
        employerId3.setLogoFilename("Logo Filename");
        employerId3.setNumberOfEmployees(59);
        employerId3.setPhoneNumber("8605550118");
        employerId3.setWebsite("pl.devfinder.infrastructure.database.entity.EmployerEntity");

        CityEntity residenceCityId3 = new CityEntity();
        residenceCityId3.setCandidateResidenceCities(new HashSet<>());
        residenceCityId3.setCityId(2L);
        residenceCityId3.setCityName("London");
        residenceCityId3.setEmployerCities(new HashSet<>());
        residenceCityId3.setOfferCities(new HashSet<>());

        CandidateEntity candidateId2 = new CandidateEntity();
        candidateId2.setCandidateId(2L);
        candidateId2.setCandidateSkills(new HashSet<>());
        candidateId2.setCandidateUuid("2020/03/01");
        candidateId2.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        candidateId2.setCvFilename("Cv Filename");
        candidateId2.setEducation("pl.devfinder.infrastructure.database.entity.CandidateEntity");
        candidateId2.setEmailContact("john.smith@example.org");
        candidateId2.setEmployerId(employerId3);
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
        candidateId2.setResidenceCityId(residenceCityId3);
        candidateId2.setSalaryMin(BigDecimal.valueOf(59L));
        candidateId2.setStatus("pl.devfinder.infrastructure.database.entity.CandidateEntity");
        candidateId2.setYearsOfExperience(1);

        SkillEntity skillId2 = new SkillEntity();
        skillId2.setCandidateSkills(new HashSet<>());
        skillId2.setOfferSkills(new HashSet<>());
        skillId2.setSkillId(2L);
        skillId2.setSkillName("pl.devfinder.infrastructure.database.entity.SkillEntity");

        CandidateSkillEntity candidateSkillEntity2 = new CandidateSkillEntity();
        candidateSkillEntity2.setCandidateId(candidateId2);
        candidateSkillEntity2.setCandidateSkillId(2L);
        candidateSkillEntity2.setSkillId(skillId2);

        HashSet<CandidateSkillEntity> candidateSkillEntitySet = new HashSet<>();
        candidateSkillEntitySet.add(candidateSkillEntity2);
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
        doNothing().when(candidateEntity).setCandidateSkills(Mockito.<Set<CandidateSkillEntity>>any());
        doNothing().when(candidateEntity).setCandidateUuid(Mockito.<String>any());
        doNothing().when(candidateEntity).setCreatedAt(Mockito.<OffsetDateTime>any());
        doNothing().when(candidateEntity).setCvFilename(Mockito.<String>any());
        doNothing().when(candidateEntity).setEducation(Mockito.<String>any());
        doNothing().when(candidateEntity).setEmailContact(Mockito.<String>any());
        doNothing().when(candidateEntity).setEmployerId(Mockito.<EmployerEntity>any());
        doNothing().when(candidateEntity).setExperienceLevel(Mockito.<String>any());
        doNothing().when(candidateEntity).setFirstName(Mockito.<String>any());
        doNothing().when(candidateEntity).setForeignLanguage(Mockito.<String>any());
        doNothing().when(candidateEntity).setGithubLink(Mockito.<String>any());
        doNothing().when(candidateEntity).setHobby(Mockito.<String>any());
        doNothing().when(candidateEntity).setLastName(Mockito.<String>any());
        doNothing().when(candidateEntity).setLinkedinLink(Mockito.<String>any());
        doNothing().when(candidateEntity).setOpenToRemoteJob(Mockito.<Boolean>any());
        doNothing().when(candidateEntity).setOtherSkills(Mockito.<String>any());
        doNothing().when(candidateEntity).setPhoneNumber(Mockito.<String>any());
        doNothing().when(candidateEntity).setResidenceCityId(Mockito.<CityEntity>any());
        doNothing().when(candidateEntity).setSalaryMin(Mockito.<BigDecimal>any());
        doNothing().when(candidateEntity).setStatus(Mockito.<String>any());
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
        Candidate actualMapFromEntityResult = candidateEntityMapperImpl.mapFromEntity(candidateEntity);
        assertEquals(1L, actualMapFromEntityResult.getCandidateId().longValue());
        assertEquals(1, actualMapFromEntityResult.getYearsOfExperience().intValue());
        assertEquals("Status", actualMapFromEntityResult.getStatus());
        BigDecimal expectedSalaryMin = salaryMin.ONE;
        BigDecimal salaryMin2 = actualMapFromEntityResult.getSalaryMin();
        assertSame(expectedSalaryMin, salaryMin2);
        assertEquals(2, actualMapFromEntityResult.getCandidateSkills().size());
        assertEquals("Z", actualMapFromEntityResult.getCreatedAt().getOffset().toString());
        assertTrue(actualMapFromEntityResult.getOpenToRemoteJob());
        assertEquals("Other Skills", actualMapFromEntityResult.getOtherSkills());
        assertEquals("Experience Level", actualMapFromEntityResult.getExperienceLevel());
        assertEquals("6625550144", actualMapFromEntityResult.getPhoneNumber());
        assertEquals("foo.txt", actualMapFromEntityResult.getPhotoFilename());
        assertEquals("foo.txt", actualMapFromEntityResult.getCvFilename());
        assertEquals("Jane", actualMapFromEntityResult.getFirstName());
        assertEquals("en", actualMapFromEntityResult.getForeignLanguage());
        assertEquals("2020-03-01", actualMapFromEntityResult.getCandidateUuid());
        assertEquals("Education", actualMapFromEntityResult.getEducation());
        assertEquals("Github Link", actualMapFromEntityResult.getGithubLink());
        assertEquals("Hobby", actualMapFromEntityResult.getHobby());
        assertEquals("jane.doe@example.org", actualMapFromEntityResult.getEmailContact());
        assertEquals("Doe", actualMapFromEntityResult.getLastName());
        assertEquals("Linkedin Link", actualMapFromEntityResult.getLinkedinLink());
        Employer employerId4 = actualMapFromEntityResult.getEmployerId();
        assertEquals("Website", employerId4.getWebsite());
        City residenceCityId4 = actualMapFromEntityResult.getResidenceCityId();
        assertEquals("Oxford", residenceCityId4.getCityName());
        assertEquals(1L, residenceCityId4.getCityId().longValue());
        assertEquals(10, employerId4.getAmountOfAvailableOffers().intValue());
        assertEquals("Company Name", employerId4.getCompanyName());
        assertEquals("Z", employerId4.getCreatedAt().getOffset().toString());
        assertEquals(1L, employerId4.getEmployerId().longValue());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", employerId4.getEmployerUuid());
        assertEquals(10, employerId4.getNumberOfEmployees().intValue());
        assertEquals("1", salaryMin2.toString());
        assertNull(employerId4.getCityId());
        assertEquals("jane.doe@example.org", employerId4.getEmailContact());
        assertEquals("foo.txt", employerId4.getLogoFilename());
        assertEquals("6625550144", employerId4.getPhoneNumber());
        assertEquals("The characteristics of someone or something", employerId4.getDescription());
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
        verify(candidateEntity).setCandidateSkills(Mockito.<Set<CandidateSkillEntity>>any());
        verify(candidateEntity).setCandidateUuid(Mockito.<String>any());
        verify(candidateEntity).setCreatedAt(Mockito.<OffsetDateTime>any());
        verify(candidateEntity).setCvFilename(Mockito.<String>any());
        verify(candidateEntity).setEducation(Mockito.<String>any());
        verify(candidateEntity).setEmailContact(Mockito.<String>any());
        verify(candidateEntity).setEmployerId(Mockito.<EmployerEntity>any());
        verify(candidateEntity).setExperienceLevel(Mockito.<String>any());
        verify(candidateEntity).setFirstName(Mockito.<String>any());
        verify(candidateEntity).setForeignLanguage(Mockito.<String>any());
        verify(candidateEntity).setGithubLink(Mockito.<String>any());
        verify(candidateEntity).setHobby(Mockito.<String>any());
        verify(candidateEntity).setLastName(Mockito.<String>any());
        verify(candidateEntity).setLinkedinLink(Mockito.<String>any());
        verify(candidateEntity).setOpenToRemoteJob(Mockito.<Boolean>any());
        verify(candidateEntity).setOtherSkills(Mockito.<String>any());
        verify(candidateEntity).setPhoneNumber(Mockito.<String>any());
        verify(candidateEntity).setResidenceCityId(Mockito.<CityEntity>any());
        verify(candidateEntity).setSalaryMin(Mockito.<BigDecimal>any());
        verify(candidateEntity).setStatus(Mockito.<String>any());
        verify(candidateEntity).setYearsOfExperience(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link CandidateEntityMapperImpl#mapFromEntity(CandidateEntity)}
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
        CityEntity cityEntity = mock(CityEntity.class);
        when(cityEntity.getCityId()).thenReturn(1L);
        when(cityEntity.getCityName()).thenReturn("Oxford");
        doNothing().when(cityEntity).setCandidateResidenceCities(Mockito.<Set<CandidateEntity>>any());
        doNothing().when(cityEntity).setCityId(Mockito.<Long>any());
        doNothing().when(cityEntity).setCityName(Mockito.<String>any());
        doNothing().when(cityEntity).setEmployerCities(Mockito.<Set<EmployerEntity>>any());
        doNothing().when(cityEntity).setOfferCities(Mockito.<Set<OfferEntity>>any());
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
        doNothing().when(candidateEntity).setCandidateSkills(Mockito.<Set<CandidateSkillEntity>>any());
        doNothing().when(candidateEntity).setCandidateUuid(Mockito.<String>any());
        doNothing().when(candidateEntity).setCreatedAt(Mockito.<OffsetDateTime>any());
        doNothing().when(candidateEntity).setCvFilename(Mockito.<String>any());
        doNothing().when(candidateEntity).setEducation(Mockito.<String>any());
        doNothing().when(candidateEntity).setEmailContact(Mockito.<String>any());
        doNothing().when(candidateEntity).setEmployerId(Mockito.<EmployerEntity>any());
        doNothing().when(candidateEntity).setExperienceLevel(Mockito.<String>any());
        doNothing().when(candidateEntity).setFirstName(Mockito.<String>any());
        doNothing().when(candidateEntity).setForeignLanguage(Mockito.<String>any());
        doNothing().when(candidateEntity).setGithubLink(Mockito.<String>any());
        doNothing().when(candidateEntity).setHobby(Mockito.<String>any());
        doNothing().when(candidateEntity).setLastName(Mockito.<String>any());
        doNothing().when(candidateEntity).setLinkedinLink(Mockito.<String>any());
        doNothing().when(candidateEntity).setOpenToRemoteJob(Mockito.<Boolean>any());
        doNothing().when(candidateEntity).setOtherSkills(Mockito.<String>any());
        doNothing().when(candidateEntity).setPhoneNumber(Mockito.<String>any());
        doNothing().when(candidateEntity).setResidenceCityId(Mockito.<CityEntity>any());
        doNothing().when(candidateEntity).setSalaryMin(Mockito.<BigDecimal>any());
        doNothing().when(candidateEntity).setStatus(Mockito.<String>any());
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
        Candidate actualMapFromEntityResult = candidateEntityMapperImpl.mapFromEntity(candidateEntity);
        assertEquals(1L, actualMapFromEntityResult.getCandidateId().longValue());
        assertEquals(1, actualMapFromEntityResult.getYearsOfExperience().intValue());
        assertEquals("Status", actualMapFromEntityResult.getStatus());
        BigDecimal expectedSalaryMin = salaryMin.ONE;
        BigDecimal salaryMin2 = actualMapFromEntityResult.getSalaryMin();
        assertSame(expectedSalaryMin, salaryMin2);
        assertTrue(actualMapFromEntityResult.getCandidateSkills().isEmpty());
        assertEquals("Z", actualMapFromEntityResult.getCreatedAt().getOffset().toString());
        assertTrue(actualMapFromEntityResult.getOpenToRemoteJob());
        assertEquals("Other Skills", actualMapFromEntityResult.getOtherSkills());
        assertEquals("Experience Level", actualMapFromEntityResult.getExperienceLevel());
        assertEquals("6625550144", actualMapFromEntityResult.getPhoneNumber());
        assertEquals("foo.txt", actualMapFromEntityResult.getPhotoFilename());
        assertEquals("foo.txt", actualMapFromEntityResult.getCvFilename());
        assertEquals("Jane", actualMapFromEntityResult.getFirstName());
        assertEquals("en", actualMapFromEntityResult.getForeignLanguage());
        assertEquals("2020-03-01", actualMapFromEntityResult.getCandidateUuid());
        assertEquals("Education", actualMapFromEntityResult.getEducation());
        assertEquals("Github Link", actualMapFromEntityResult.getGithubLink());
        assertEquals("Hobby", actualMapFromEntityResult.getHobby());
        assertEquals("jane.doe@example.org", actualMapFromEntityResult.getEmailContact());
        assertEquals("Doe", actualMapFromEntityResult.getLastName());
        assertEquals("Linkedin Link", actualMapFromEntityResult.getLinkedinLink());
        Employer employerId2 = actualMapFromEntityResult.getEmployerId();
        assertEquals("Website", employerId2.getWebsite());
        City residenceCityId2 = actualMapFromEntityResult.getResidenceCityId();
        assertEquals("Oxford", residenceCityId2.getCityName());
        assertEquals(1L, residenceCityId2.getCityId().longValue());
        assertEquals(10, employerId2.getAmountOfAvailableOffers().intValue());
        assertEquals("Company Name", employerId2.getCompanyName());
        assertEquals("Z", employerId2.getCreatedAt().getOffset().toString());
        assertEquals(1L, employerId2.getEmployerId().longValue());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", employerId2.getEmployerUuid());
        assertEquals(10, employerId2.getNumberOfEmployees().intValue());
        assertEquals("1", salaryMin2.toString());
        assertNull(employerId2.getCityId());
        assertEquals("jane.doe@example.org", employerId2.getEmailContact());
        assertEquals("foo.txt", employerId2.getLogoFilename());
        assertEquals("6625550144", employerId2.getPhoneNumber());
        assertEquals("The characteristics of someone or something", employerId2.getDescription());
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
        verify(candidateEntity).setCandidateSkills(Mockito.<Set<CandidateSkillEntity>>any());
        verify(candidateEntity).setCandidateUuid(Mockito.<String>any());
        verify(candidateEntity).setCreatedAt(Mockito.<OffsetDateTime>any());
        verify(candidateEntity).setCvFilename(Mockito.<String>any());
        verify(candidateEntity).setEducation(Mockito.<String>any());
        verify(candidateEntity).setEmailContact(Mockito.<String>any());
        verify(candidateEntity).setEmployerId(Mockito.<EmployerEntity>any());
        verify(candidateEntity).setExperienceLevel(Mockito.<String>any());
        verify(candidateEntity).setFirstName(Mockito.<String>any());
        verify(candidateEntity).setForeignLanguage(Mockito.<String>any());
        verify(candidateEntity).setGithubLink(Mockito.<String>any());
        verify(candidateEntity).setHobby(Mockito.<String>any());
        verify(candidateEntity).setLastName(Mockito.<String>any());
        verify(candidateEntity).setLinkedinLink(Mockito.<String>any());
        verify(candidateEntity).setOpenToRemoteJob(Mockito.<Boolean>any());
        verify(candidateEntity).setOtherSkills(Mockito.<String>any());
        verify(candidateEntity).setPhoneNumber(Mockito.<String>any());
        verify(candidateEntity).setResidenceCityId(Mockito.<CityEntity>any());
        verify(candidateEntity).setSalaryMin(Mockito.<BigDecimal>any());
        verify(candidateEntity).setStatus(Mockito.<String>any());
        verify(candidateEntity).setYearsOfExperience(Mockito.<Integer>any());
        verify(cityEntity).getCityId();
        verify(cityEntity).getCityName();
        verify(cityEntity).setCandidateResidenceCities(Mockito.<Set<CandidateEntity>>any());
        verify(cityEntity).setCityId(Mockito.<Long>any());
        verify(cityEntity).setCityName(Mockito.<String>any());
        verify(cityEntity).setEmployerCities(Mockito.<Set<EmployerEntity>>any());
        verify(cityEntity).setOfferCities(Mockito.<Set<OfferEntity>>any());
    }

    /**
     * Method under test: {@link CandidateEntityMapperImpl#mapFromEntity(CandidateSkillEntity)}
     */
    @Test
    void testMapFromEntity6() {
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

        CandidateSkillEntity entity = new CandidateSkillEntity();
        entity.setCandidateId(candidateId);
        entity.setCandidateSkillId(1L);
        entity.setSkillId(skillId);
        CandidateSkill actualMapFromEntityResult = candidateEntityMapperImpl.mapFromEntity(entity);
        assertNull(actualMapFromEntityResult.getCandidateId());
        assertEquals(1L, actualMapFromEntityResult.getCandidateSkillId().longValue());
        Skill skillId2 = actualMapFromEntityResult.getSkillId();
        assertEquals("Skill Name", skillId2.getSkillName());
        assertEquals(1L, skillId2.getSkillId().longValue());
    }

    /**
     * Method under test: {@link CandidateEntityMapperImpl#mapFromEntity(CandidateSkillEntity)}
     */
    @Test
    void testMapFromEntity7() {
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

        SkillEntity skillEntity = new SkillEntity();
        skillEntity.setCandidateSkills(new HashSet<>());
        skillEntity.setOfferSkills(new HashSet<>());
        skillEntity.setSkillId(1L);
        skillEntity.setSkillName("Skill Name");
        CandidateSkillEntity entity = mock(CandidateSkillEntity.class);
        when(entity.getCandidateSkillId()).thenReturn(1L);
        when(entity.getSkillId()).thenReturn(skillEntity);
        doNothing().when(entity).setCandidateId(Mockito.<CandidateEntity>any());
        doNothing().when(entity).setCandidateSkillId(Mockito.<Long>any());
        doNothing().when(entity).setSkillId(Mockito.<SkillEntity>any());
        entity.setCandidateId(candidateId);
        entity.setCandidateSkillId(1L);
        entity.setSkillId(skillId);
        CandidateSkill actualMapFromEntityResult = candidateEntityMapperImpl.mapFromEntity(entity);
        assertNull(actualMapFromEntityResult.getCandidateId());
        assertEquals(1L, actualMapFromEntityResult.getCandidateSkillId().longValue());
        Skill skillId2 = actualMapFromEntityResult.getSkillId();
        assertEquals("Skill Name", skillId2.getSkillName());
        assertEquals(1L, skillId2.getSkillId().longValue());
        verify(entity).getCandidateSkillId();
        verify(entity).getSkillId();
        verify(entity).setCandidateId(Mockito.<CandidateEntity>any());
        verify(entity).setCandidateSkillId(Mockito.<Long>any());
        verify(entity).setSkillId(Mockito.<SkillEntity>any());
    }

    /**
     * Method under test: {@link CandidateEntityMapperImpl#mapFromEntity(CandidateSkillEntity)}
     */
    @Test
    void testMapFromEntity8() {
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
        SkillEntity skillEntity = mock(SkillEntity.class);
        when(skillEntity.getSkillId()).thenReturn(1L);
        when(skillEntity.getSkillName()).thenReturn("Skill Name");
        doNothing().when(skillEntity).setCandidateSkills(Mockito.<Set<CandidateSkillEntity>>any());
        doNothing().when(skillEntity).setOfferSkills(Mockito.<Set<OfferSkillEntity>>any());
        doNothing().when(skillEntity).setSkillId(Mockito.<Long>any());
        doNothing().when(skillEntity).setSkillName(Mockito.<String>any());
        skillEntity.setCandidateSkills(new HashSet<>());
        skillEntity.setOfferSkills(new HashSet<>());
        skillEntity.setSkillId(1L);
        skillEntity.setSkillName("Skill Name");
        CandidateSkillEntity entity = mock(CandidateSkillEntity.class);
        when(entity.getCandidateSkillId()).thenReturn(1L);
        when(entity.getSkillId()).thenReturn(skillEntity);
        doNothing().when(entity).setCandidateId(Mockito.<CandidateEntity>any());
        doNothing().when(entity).setCandidateSkillId(Mockito.<Long>any());
        doNothing().when(entity).setSkillId(Mockito.<SkillEntity>any());
        entity.setCandidateId(candidateId);
        entity.setCandidateSkillId(1L);
        entity.setSkillId(skillId);
        CandidateSkill actualMapFromEntityResult = candidateEntityMapperImpl.mapFromEntity(entity);
        assertNull(actualMapFromEntityResult.getCandidateId());
        assertEquals(1L, actualMapFromEntityResult.getCandidateSkillId().longValue());
        Skill skillId2 = actualMapFromEntityResult.getSkillId();
        assertEquals("Skill Name", skillId2.getSkillName());
        assertEquals(1L, skillId2.getSkillId().longValue());
        verify(entity).getCandidateSkillId();
        verify(entity).getSkillId();
        verify(entity).setCandidateId(Mockito.<CandidateEntity>any());
        verify(entity).setCandidateSkillId(Mockito.<Long>any());
        verify(entity).setSkillId(Mockito.<SkillEntity>any());
        verify(skillEntity).getSkillId();
        verify(skillEntity).getSkillName();
        verify(skillEntity).setCandidateSkills(Mockito.<Set<CandidateSkillEntity>>any());
        verify(skillEntity).setOfferSkills(Mockito.<Set<OfferSkillEntity>>any());
        verify(skillEntity).setSkillId(Mockito.<Long>any());
        verify(skillEntity).setSkillName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CandidateEntityMapperImpl#mapToEntity(Candidate)}
     */
    @Test
    void testMapToEntity() {
        assertNull(candidateEntityMapperImpl.mapToEntity(null));
    }

    /**
     * Method under test: {@link CandidateEntityMapperImpl#employerEntityToEmployer(EmployerEntity)}
     */
    @Test
    void testEmployerEntityToEmployer() {
        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        EmployerEntity employerEntity = new EmployerEntity();
        employerEntity.setAmountOfAvailableOffers(10);
        employerEntity.setCityId(cityId);
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
        Employer actualEmployerEntityToEmployerResult = candidateEntityMapperImpl
                .employerEntityToEmployer(employerEntity);
        assertEquals(10, actualEmployerEntityToEmployerResult.getAmountOfAvailableOffers().intValue());
        assertEquals("Website", actualEmployerEntityToEmployerResult.getWebsite());
        assertEquals("6625550144", actualEmployerEntityToEmployerResult.getPhoneNumber());
        assertEquals(10, actualEmployerEntityToEmployerResult.getNumberOfEmployees().intValue());
        assertEquals("foo.txt", actualEmployerEntityToEmployerResult.getLogoFilename());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", actualEmployerEntityToEmployerResult.getEmployerUuid());
        assertEquals(1L, actualEmployerEntityToEmployerResult.getEmployerId().longValue());
        assertEquals("jane.doe@example.org", actualEmployerEntityToEmployerResult.getEmailContact());
        assertEquals("The characteristics of someone or something",
                actualEmployerEntityToEmployerResult.getDescription());
        assertEquals("Z", actualEmployerEntityToEmployerResult.getCreatedAt().getOffset().toString());
        assertEquals("Company Name", actualEmployerEntityToEmployerResult.getCompanyName());
        assertNull(actualEmployerEntityToEmployerResult.getCityId());
    }

    /**
     * Method under test: {@link CandidateEntityMapperImpl#employerEntityToEmployer(EmployerEntity)}
     */
    @Test
    void testEmployerEntityToEmployer2() {
        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());
        EmployerEntity employerEntity = mock(EmployerEntity.class);
        when(employerEntity.getAmountOfAvailableOffers()).thenReturn(10);
        when(employerEntity.getNumberOfEmployees()).thenReturn(10);
        when(employerEntity.getEmployerId()).thenReturn(1L);
        when(employerEntity.getCompanyName()).thenReturn("Company Name");
        when(employerEntity.getDescription()).thenReturn("The characteristics of someone or something");
        when(employerEntity.getEmailContact()).thenReturn("jane.doe@example.org");
        when(employerEntity.getEmployerUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(employerEntity.getLogoFilename()).thenReturn("foo.txt");
        when(employerEntity.getPhoneNumber()).thenReturn("6625550144");
        when(employerEntity.getWebsite()).thenReturn("Website");
        when(employerEntity.getCreatedAt())
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        doNothing().when(employerEntity).setAmountOfAvailableOffers(Mockito.<Integer>any());
        doNothing().when(employerEntity).setCityId(Mockito.<CityEntity>any());
        doNothing().when(employerEntity).setCompanyName(Mockito.<String>any());
        doNothing().when(employerEntity).setCreatedAt(Mockito.<OffsetDateTime>any());
        doNothing().when(employerEntity).setDescription(Mockito.<String>any());
        doNothing().when(employerEntity).setEmailContact(Mockito.<String>any());
        doNothing().when(employerEntity).setEmployerId(Mockito.<Long>any());
        doNothing().when(employerEntity).setEmployerUuid(Mockito.<String>any());
        doNothing().when(employerEntity).setLogoFilename(Mockito.<String>any());
        doNothing().when(employerEntity).setNumberOfEmployees(Mockito.<Integer>any());
        doNothing().when(employerEntity).setPhoneNumber(Mockito.<String>any());
        doNothing().when(employerEntity).setWebsite(Mockito.<String>any());
        employerEntity.setAmountOfAvailableOffers(10);
        employerEntity.setCityId(cityId);
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
        Employer actualEmployerEntityToEmployerResult = candidateEntityMapperImpl
                .employerEntityToEmployer(employerEntity);
        assertEquals(10, actualEmployerEntityToEmployerResult.getAmountOfAvailableOffers().intValue());
        assertEquals("Website", actualEmployerEntityToEmployerResult.getWebsite());
        assertEquals("6625550144", actualEmployerEntityToEmployerResult.getPhoneNumber());
        assertEquals(10, actualEmployerEntityToEmployerResult.getNumberOfEmployees().intValue());
        assertEquals("foo.txt", actualEmployerEntityToEmployerResult.getLogoFilename());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", actualEmployerEntityToEmployerResult.getEmployerUuid());
        assertEquals(1L, actualEmployerEntityToEmployerResult.getEmployerId().longValue());
        assertEquals("jane.doe@example.org", actualEmployerEntityToEmployerResult.getEmailContact());
        assertEquals("The characteristics of someone or something",
                actualEmployerEntityToEmployerResult.getDescription());
        assertEquals("Z", actualEmployerEntityToEmployerResult.getCreatedAt().getOffset().toString());
        assertEquals("Company Name", actualEmployerEntityToEmployerResult.getCompanyName());
        assertNull(actualEmployerEntityToEmployerResult.getCityId());
        verify(employerEntity).getAmountOfAvailableOffers();
        verify(employerEntity).getNumberOfEmployees();
        verify(employerEntity).getEmployerId();
        verify(employerEntity).getCompanyName();
        verify(employerEntity).getDescription();
        verify(employerEntity).getEmailContact();
        verify(employerEntity).getEmployerUuid();
        verify(employerEntity).getLogoFilename();
        verify(employerEntity).getPhoneNumber();
        verify(employerEntity).getWebsite();
        verify(employerEntity).getCreatedAt();
        verify(employerEntity).setAmountOfAvailableOffers(Mockito.<Integer>any());
        verify(employerEntity).setCityId(Mockito.<CityEntity>any());
        verify(employerEntity).setCompanyName(Mockito.<String>any());
        verify(employerEntity).setCreatedAt(Mockito.<OffsetDateTime>any());
        verify(employerEntity).setDescription(Mockito.<String>any());
        verify(employerEntity).setEmailContact(Mockito.<String>any());
        verify(employerEntity).setEmployerId(Mockito.<Long>any());
        verify(employerEntity).setEmployerUuid(Mockito.<String>any());
        verify(employerEntity).setLogoFilename(Mockito.<String>any());
        verify(employerEntity).setNumberOfEmployees(Mockito.<Integer>any());
        verify(employerEntity).setPhoneNumber(Mockito.<String>any());
        verify(employerEntity).setWebsite(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CandidateEntityMapperImpl#cityEntityToCity(CityEntity)}
     */
    @Test
    void testCityEntityToCity() {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());
        City actualCityEntityToCityResult = candidateEntityMapperImpl.cityEntityToCity(cityEntity);
        assertEquals(1L, actualCityEntityToCityResult.getCityId().longValue());
        assertEquals("Oxford", actualCityEntityToCityResult.getCityName());
    }

    /**
     * Method under test: {@link CandidateEntityMapperImpl#cityEntityToCity(CityEntity)}
     */
    @Test
    void testCityEntityToCity2() {
        CityEntity cityEntity = mock(CityEntity.class);
        when(cityEntity.getCityId()).thenReturn(1L);
        when(cityEntity.getCityName()).thenReturn("Oxford");
        doNothing().when(cityEntity).setCandidateResidenceCities(Mockito.<Set<CandidateEntity>>any());
        doNothing().when(cityEntity).setCityId(Mockito.<Long>any());
        doNothing().when(cityEntity).setCityName(Mockito.<String>any());
        doNothing().when(cityEntity).setEmployerCities(Mockito.<Set<EmployerEntity>>any());
        doNothing().when(cityEntity).setOfferCities(Mockito.<Set<OfferEntity>>any());
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());
        City actualCityEntityToCityResult = candidateEntityMapperImpl.cityEntityToCity(cityEntity);
        assertEquals(1L, actualCityEntityToCityResult.getCityId().longValue());
        assertEquals("Oxford", actualCityEntityToCityResult.getCityName());
        verify(cityEntity).getCityId();
        verify(cityEntity).getCityName();
        verify(cityEntity).setCandidateResidenceCities(Mockito.<Set<CandidateEntity>>any());
        verify(cityEntity).setCityId(Mockito.<Long>any());
        verify(cityEntity).setCityName(Mockito.<String>any());
        verify(cityEntity).setEmployerCities(Mockito.<Set<EmployerEntity>>any());
        verify(cityEntity).setOfferCities(Mockito.<Set<OfferEntity>>any());
    }

    /**
     * Method under test: {@link CandidateEntityMapperImpl#skillEntityToSkill(SkillEntity)}
     */
    @Test
    void testSkillEntityToSkill() {
        SkillEntity skillEntity = new SkillEntity();
        skillEntity.setCandidateSkills(new HashSet<>());
        skillEntity.setOfferSkills(new HashSet<>());
        skillEntity.setSkillId(1L);
        skillEntity.setSkillName("Skill Name");
        Skill actualSkillEntityToSkillResult = candidateEntityMapperImpl.skillEntityToSkill(skillEntity);
        assertEquals(1L, actualSkillEntityToSkillResult.getSkillId().longValue());
        assertEquals("Skill Name", actualSkillEntityToSkillResult.getSkillName());
    }

    /**
     * Method under test: {@link CandidateEntityMapperImpl#skillEntityToSkill(SkillEntity)}
     */
    @Test
    void testSkillEntityToSkill2() {
        SkillEntity skillEntity = mock(SkillEntity.class);
        when(skillEntity.getSkillId()).thenReturn(1L);
        when(skillEntity.getSkillName()).thenReturn("Skill Name");
        doNothing().when(skillEntity).setCandidateSkills(Mockito.<Set<CandidateSkillEntity>>any());
        doNothing().when(skillEntity).setOfferSkills(Mockito.<Set<OfferSkillEntity>>any());
        doNothing().when(skillEntity).setSkillId(Mockito.<Long>any());
        doNothing().when(skillEntity).setSkillName(Mockito.<String>any());
        skillEntity.setCandidateSkills(new HashSet<>());
        skillEntity.setOfferSkills(new HashSet<>());
        skillEntity.setSkillId(1L);
        skillEntity.setSkillName("Skill Name");
        Skill actualSkillEntityToSkillResult = candidateEntityMapperImpl.skillEntityToSkill(skillEntity);
        assertEquals(1L, actualSkillEntityToSkillResult.getSkillId().longValue());
        assertEquals("Skill Name", actualSkillEntityToSkillResult.getSkillName());
        verify(skillEntity).getSkillId();
        verify(skillEntity).getSkillName();
        verify(skillEntity).setCandidateSkills(Mockito.<Set<CandidateSkillEntity>>any());
        verify(skillEntity).setOfferSkills(Mockito.<Set<OfferSkillEntity>>any());
        verify(skillEntity).setSkillId(Mockito.<Long>any());
        verify(skillEntity).setSkillName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CandidateEntityMapperImpl#cityToCityEntity(City)}
     */
    @Test
    void testCityToCityEntity() {
        assertNull(candidateEntityMapperImpl.cityToCityEntity(null));
    }

    /**
     * Method under test: {@link CandidateEntityMapperImpl#employerToEmployerEntity(Employer)}
     */
    @Test
    void testEmployerToEmployerEntity() {
        assertNull(candidateEntityMapperImpl.employerToEmployerEntity(null));
    }

    /**
     * Method under test: {@link CandidateEntityMapperImpl#skillToSkillEntity(Skill)}
     */
    @Test
    void testSkillToSkillEntity() {
        assertNull(candidateEntityMapperImpl.skillToSkillEntity(null));
    }

    /**
     * Method under test: {@link CandidateEntityMapperImpl#candidateSkillToCandidateSkillEntity(CandidateSkill)}
     */
    @Test
    void testCandidateSkillToCandidateSkillEntity() {
        CandidateSkillEntity actualCandidateSkillToCandidateSkillEntityResult = candidateEntityMapperImpl
                .candidateSkillToCandidateSkillEntity(new CandidateSkill(1L, null, null));
        assertNull(actualCandidateSkillToCandidateSkillEntityResult.getCandidateId());
        assertNull(actualCandidateSkillToCandidateSkillEntityResult.getSkillId());
        assertEquals(1L, actualCandidateSkillToCandidateSkillEntityResult.getCandidateSkillId().longValue());
    }

    /**
     * Method under test: {@link CandidateEntityMapperImpl#candidateSkillToCandidateSkillEntity(CandidateSkill)}
     */
    @Test
    void testCandidateSkillToCandidateSkillEntity2() {
        assertNull(candidateEntityMapperImpl.candidateSkillToCandidateSkillEntity(null));
    }

    /**
     * Method under test: {@link CandidateEntityMapperImpl#candidateSkillSetToCandidateSkillEntitySet(Set)}
     */
    @Test
    void testCandidateSkillSetToCandidateSkillEntitySet() {
        assertTrue(candidateEntityMapperImpl.candidateSkillSetToCandidateSkillEntitySet(new HashSet<>()).isEmpty());
    }

    /**
     * Method under test: {@link CandidateEntityMapperImpl#candidateSkillSetToCandidateSkillEntitySet(Set)}
     */
    @Test
    void testCandidateSkillSetToCandidateSkillEntitySet2() {
        HashSet<CandidateSkill> set = new HashSet<>();
        set.add(new CandidateSkill(1L, null, null));
        assertEquals(1, candidateEntityMapperImpl.candidateSkillSetToCandidateSkillEntitySet(set).size());
    }

    /**
     * Method under test: {@link CandidateEntityMapperImpl#candidateSkillSetToCandidateSkillEntitySet(Set)}
     */
    @Test
    void testCandidateSkillSetToCandidateSkillEntitySet3() {
        HashSet<CandidateSkill> set = new HashSet<>();
        set.add(new CandidateSkill(null, null, null));
        assertEquals(1, candidateEntityMapperImpl.candidateSkillSetToCandidateSkillEntitySet(set).size());
    }

    /**
     * Method under test: {@link CandidateEntityMapperImpl#candidateSkillSetToCandidateSkillEntitySet(Set)}
     */
    @Test
    void testCandidateSkillSetToCandidateSkillEntitySet4() {
        HashSet<CandidateSkill> set = new HashSet<>();
        set.add(new CandidateSkill(2L, null, null));
        set.add(new CandidateSkill(1L, null, null));
        assertEquals(2, candidateEntityMapperImpl.candidateSkillSetToCandidateSkillEntitySet(set).size());
    }

    /**
     * Method under test: {@link CandidateEntityMapperImpl#candidateSkillSetToCandidateSkillEntitySet(Set)}
     */
    @Test
    void testCandidateSkillSetToCandidateSkillEntitySet5() {
        HashSet<CandidateSkill> set = new HashSet<>();
        set.add(null);
        assertEquals(1, candidateEntityMapperImpl.candidateSkillSetToCandidateSkillEntitySet(set).size());
    }
}

