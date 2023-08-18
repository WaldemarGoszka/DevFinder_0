package pl.devfinder.infrastructure.database.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.Candidate;
import pl.devfinder.infrastructure.database.entity.CandidateEntity;
import pl.devfinder.infrastructure.database.entity.CityEntity;
import pl.devfinder.infrastructure.database.entity.EmployerEntity;
import pl.devfinder.infrastructure.database.repository.jpa.CandidateJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.CandidateEntityMapper;

@ContextConfiguration(classes = {CandidateRepository.class})
@ExtendWith(SpringExtension.class)
class CandidateRepositoryDiffBlueTest {
    @MockBean
    private CandidateEntityMapper candidateEntityMapper;

    @MockBean
    private CandidateJpaRepository candidateJpaRepository;

    @Autowired
    private CandidateRepository candidateRepository;


    /**
     * Method under test: {@link CandidateRepository#save(Candidate)}
     */
    @Test
    void testSave() {
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
        candidateEntity.setSalaryMin(BigDecimal.valueOf(1L));
        candidateEntity.setStatus("Status");
        candidateEntity.setYearsOfExperience(1);
        when(candidateJpaRepository.saveAndFlush(Mockito.<CandidateEntity>any())).thenReturn(candidateEntity);

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

        CandidateEntity candidateEntity2 = new CandidateEntity();
        candidateEntity2.setCandidateId(1L);
        candidateEntity2.setCandidateSkills(new HashSet<>());
        candidateEntity2.setCandidateUuid("2020-03-01");
        candidateEntity2.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        candidateEntity2.setCvFilename("foo.txt");
        candidateEntity2.setEducation("Education");
        candidateEntity2.setEmailContact("jane.doe@example.org");
        candidateEntity2.setEmployerId(employerId2);
        candidateEntity2.setExperienceLevel("Experience Level");
        candidateEntity2.setFirstName("Jane");
        candidateEntity2.setForeignLanguage("en");
        candidateEntity2.setGithubLink("Github Link");
        candidateEntity2.setHobby("Hobby");
        candidateEntity2.setLastName("Doe");
        candidateEntity2.setLinkedinLink("Linkedin Link");
        candidateEntity2.setOpenToRemoteJob(true);
        candidateEntity2.setOtherSkills("Other Skills");
        candidateEntity2.setPhoneNumber("6625550144");
        candidateEntity2.setResidenceCityId(residenceCityId2);
        candidateEntity2.setSalaryMin(BigDecimal.valueOf(1L));
        candidateEntity2.setStatus("Status");
        candidateEntity2.setYearsOfExperience(1);
        when(candidateEntityMapper.mapFromEntity(Mockito.<CandidateEntity>any())).thenReturn(null);
        when(candidateEntityMapper.mapToEntity(Mockito.<Candidate>any())).thenReturn(candidateEntity2);
        assertNull(candidateRepository.save(null));
        verify(candidateJpaRepository).saveAndFlush(Mockito.<CandidateEntity>any());
        verify(candidateEntityMapper).mapFromEntity(Mockito.<CandidateEntity>any());
        verify(candidateEntityMapper).mapToEntity(Mockito.<Candidate>any());
    }

    /**
     * Method under test: {@link CandidateRepository#findById(Long)}
     */
    @Test
    void testFindById() {
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
        candidateEntity.setSalaryMin(BigDecimal.valueOf(1L));
        candidateEntity.setStatus("Status");
        candidateEntity.setYearsOfExperience(1);
        Optional<CandidateEntity> ofResult = Optional.of(candidateEntity);
        when(candidateJpaRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(candidateEntityMapper.mapFromEntity(Mockito.<CandidateEntity>any())).thenReturn(null);
        assertFalse(candidateRepository.findById(1L).isPresent());
        verify(candidateJpaRepository).findById(Mockito.<Long>any());
        verify(candidateEntityMapper).mapFromEntity(Mockito.<CandidateEntity>any());
    }

    /**
     * Method under test: {@link CandidateRepository#findByCandidateUuid(String)}
     */
    @Test
    void testFindByCandidateUuid() {
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
        candidateEntity.setSalaryMin(BigDecimal.valueOf(1L));
        candidateEntity.setStatus("Status");
        candidateEntity.setYearsOfExperience(1);
        Optional<CandidateEntity> ofResult = Optional.of(candidateEntity);
        when(candidateJpaRepository.findByCandidateUuid(Mockito.<String>any())).thenReturn(ofResult);
        when(candidateEntityMapper.mapFromEntity(Mockito.<CandidateEntity>any())).thenReturn(null);
        assertFalse(candidateRepository.findByCandidateUuid("01234567-89AB-CDEF-FEDC-BA9876543210").isPresent());
        verify(candidateJpaRepository).findByCandidateUuid(Mockito.<String>any());
        verify(candidateEntityMapper).mapFromEntity(Mockito.<CandidateEntity>any());
    }

    /**
     * Method under test: {@link CandidateRepository#deleteById(Long)}
     */
    @Test
    void testDeleteById() {
        doNothing().when(candidateJpaRepository).deleteById(Mockito.<Long>any());
        candidateRepository.deleteById(1L);
        verify(candidateJpaRepository).deleteById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CandidateRepository#countByCityName(String)}
     */
    @Test
    void testCountByCityName() {
        when(candidateJpaRepository.countByCityName(Mockito.<String>any())).thenReturn(3L);
        assertEquals(3L, candidateRepository.countByCityName("Oxford"));
        verify(candidateJpaRepository).countByCityName(Mockito.<String>any());
    }
}

