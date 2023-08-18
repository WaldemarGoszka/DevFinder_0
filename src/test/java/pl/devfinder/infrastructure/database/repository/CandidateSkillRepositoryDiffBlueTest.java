package pl.devfinder.infrastructure.database.repository;

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
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.CandidateSkill;
import pl.devfinder.infrastructure.database.entity.CandidateEntity;
import pl.devfinder.infrastructure.database.entity.CandidateSkillEntity;
import pl.devfinder.infrastructure.database.entity.CityEntity;
import pl.devfinder.infrastructure.database.entity.EmployerEntity;
import pl.devfinder.infrastructure.database.entity.SkillEntity;
import pl.devfinder.infrastructure.database.repository.jpa.CandidateSkillJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.CandidateEntityMapper;
import pl.devfinder.infrastructure.database.repository.mapper.CandidateSkillEntityMapper;

@ContextConfiguration(classes = {CandidateSkillRepository.class})
@ExtendWith(SpringExtension.class)
class CandidateSkillRepositoryDiffBlueTest {
    @MockBean
    private CandidateEntityMapper candidateEntityMapper;

    @MockBean
    private CandidateSkillEntityMapper candidateSkillEntityMapper;

    @MockBean
    private CandidateSkillJpaRepository candidateSkillJpaRepository;

    @Autowired
    private CandidateSkillRepository candidateSkillRepository;

    /**
     * Method under test: {@link CandidateSkillRepository#saveAll(Set)}
     */
    @Test
    void testSaveAll() {
        when(candidateSkillJpaRepository.saveAll(Mockito.<Iterable<CandidateSkillEntity>>any()))
                .thenReturn(new ArrayList<>());
        candidateSkillRepository.saveAll(new HashSet<>());
        verify(candidateSkillJpaRepository).saveAll(Mockito.<Iterable<CandidateSkillEntity>>any());
    }

    /**
     * Method under test: {@link CandidateSkillRepository#saveAll(Set)}
     */
    @Test
    void testSaveAll2() {
        when(candidateSkillJpaRepository.saveAll(Mockito.<Iterable<CandidateSkillEntity>>any()))
                .thenReturn(new ArrayList<>());

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
        when(candidateSkillEntityMapper.mapToEntity(Mockito.<CandidateSkill>any())).thenReturn(candidateSkillEntity);

        HashSet<CandidateSkill> candidateSkills = new HashSet<>();
        candidateSkills.add(new CandidateSkill(1L, null, null));
        candidateSkillRepository.saveAll(candidateSkills);
        verify(candidateSkillJpaRepository).saveAll(Mockito.<Iterable<CandidateSkillEntity>>any());
        verify(candidateSkillEntityMapper).mapToEntity(Mockito.<CandidateSkill>any());
    }

    /**
     * Method under test: {@link CandidateSkillRepository#deleteAllByCandidate(Candidate)}
     */
    @Test
    void testDeleteAllByCandidate() {
        doNothing().when(candidateSkillJpaRepository).deleteAllByCandidateId(Mockito.<CandidateEntity>any());

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
        when(candidateEntityMapper.mapToEntity(Mockito.<Candidate>any())).thenReturn(candidateEntity);
        candidateSkillRepository.deleteAllByCandidate(null);
        verify(candidateSkillJpaRepository).deleteAllByCandidateId(Mockito.<CandidateEntity>any());
        verify(candidateEntityMapper).mapToEntity(Mockito.<Candidate>any());
    }
}

