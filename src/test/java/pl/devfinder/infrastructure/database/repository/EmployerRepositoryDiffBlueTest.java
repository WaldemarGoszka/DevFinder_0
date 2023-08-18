package pl.devfinder.infrastructure.database.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import pl.devfinder.domain.Employer;
import pl.devfinder.infrastructure.database.entity.CityEntity;
import pl.devfinder.infrastructure.database.entity.EmployerEntity;
import pl.devfinder.infrastructure.database.repository.jpa.EmployerJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.EmployerEntityMapper;

@ContextConfiguration(classes = {EmployerRepository.class})
@ExtendWith(SpringExtension.class)
class EmployerRepositoryDiffBlueTest {
    @MockBean
    private EmployerEntityMapper employerEntityMapper;

    @MockBean
    private EmployerJpaRepository employerJpaRepository;

    @Autowired
    private EmployerRepository employerRepository;

    /**
     * Method under test: {@link EmployerRepository#findAll()}
     */
    @Test
    void testFindAll() {
        when(employerJpaRepository.findAllByOrderByCompanyName()).thenReturn(new ArrayList<>());
        assertTrue(employerRepository.findAll().isEmpty());
        verify(employerJpaRepository).findAllByOrderByCompanyName();
    }

    /**
     * Method under test: {@link EmployerRepository#save(Employer)}
     */
    @Test
    void testSave() {
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
        when(employerJpaRepository.save(Mockito.<EmployerEntity>any())).thenReturn(employerEntity);

        CityEntity cityId2 = new CityEntity();
        cityId2.setCandidateResidenceCities(new HashSet<>());
        cityId2.setCityId(1L);
        cityId2.setCityName("Oxford");
        cityId2.setEmployerCities(new HashSet<>());
        cityId2.setOfferCities(new HashSet<>());

        EmployerEntity employerEntity2 = new EmployerEntity();
        employerEntity2.setAmountOfAvailableOffers(10);
        employerEntity2.setCityId(cityId2);
        employerEntity2.setCompanyName("Company Name");
        employerEntity2.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerEntity2.setDescription("The characteristics of someone or something");
        employerEntity2.setEmailContact("jane.doe@example.org");
        employerEntity2.setEmployerId(1L);
        employerEntity2.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerEntity2.setLogoFilename("foo.txt");
        employerEntity2.setNumberOfEmployees(10);
        employerEntity2.setPhoneNumber("6625550144");
        employerEntity2.setWebsite("Website");
        when(employerEntityMapper.mapToEntity(Mockito.<Employer>any())).thenReturn(employerEntity2);
        employerRepository.save(null);
        verify(employerJpaRepository).save(Mockito.<EmployerEntity>any());
        verify(employerEntityMapper).mapToEntity(Mockito.<Employer>any());
    }

    /**
     * Method under test: {@link EmployerRepository#findById(Long)}
     */
    @Test
    void testFindById() {
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
        Optional<EmployerEntity> ofResult = Optional.of(employerEntity);
        when(employerJpaRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(employerEntityMapper.mapFromEntity(Mockito.<EmployerEntity>any())).thenReturn(null);
        assertFalse(employerRepository.findById(1L).isPresent());
        verify(employerJpaRepository).findById(Mockito.<Long>any());
        verify(employerEntityMapper).mapFromEntity(Mockito.<EmployerEntity>any());
    }

    /**
     * Method under test: {@link EmployerRepository#findByEmployerUuid(String)}
     */
    @Test
    void testFindByEmployerUuid() {
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
        Optional<EmployerEntity> ofResult = Optional.of(employerEntity);
        when(employerJpaRepository.findByEmployerUuid(Mockito.<String>any())).thenReturn(ofResult);
        when(employerEntityMapper.mapFromEntity(Mockito.<EmployerEntity>any())).thenReturn(null);
        assertFalse(employerRepository.findByEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210").isPresent());
        verify(employerJpaRepository).findByEmployerUuid(Mockito.<String>any());
        verify(employerEntityMapper).mapFromEntity(Mockito.<EmployerEntity>any());
    }

    /**
     * Method under test: {@link EmployerRepository#countByCityName(String)}
     */
    @Test
    void testCountByCityName() {
        when(employerJpaRepository.countByCityName(Mockito.<String>any())).thenReturn(3L);
        assertEquals(3L, employerRepository.countByCityName("Oxford"));
        verify(employerJpaRepository).countByCityName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link EmployerRepository#deleteById(Long)}
     */
    @Test
    void testDeleteById() {
        doNothing().when(employerJpaRepository).deleteById(Mockito.<Long>any());
        employerRepository.deleteById(1L);
        verify(employerJpaRepository).deleteById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EmployerRepository#deleteEmployerFromAllCandidatesAndChangeStatus(Employer)}
     */
    @Test
    void testDeleteEmployerFromAllCandidatesAndChangeStatus() {
        doNothing().when(employerJpaRepository)
                .deleteEmployerFromAllCandidatesAndChangeStatus(Mockito.<EmployerEntity>any());

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
        when(employerEntityMapper.mapToEntity(Mockito.<Employer>any())).thenReturn(employerEntity);
        employerRepository.deleteEmployerFromAllCandidatesAndChangeStatus(null);
        verify(employerJpaRepository).deleteEmployerFromAllCandidatesAndChangeStatus(Mockito.<EmployerEntity>any());
        verify(employerEntityMapper).mapToEntity(Mockito.<Employer>any());
    }

    /**
     * Method under test: {@link EmployerRepository#assignEmployerToCandidateAndChangeStatus(Employer, Long)}
     */
    @Test
    void testAssignEmployerToCandidateAndChangeStatus() {
        doNothing().when(employerJpaRepository)
                .assignEmployerToCandidateAndChangeStatus(Mockito.<EmployerEntity>any(), Mockito.<Long>any());

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
        when(employerEntityMapper.mapToEntity(Mockito.<Employer>any())).thenReturn(employerEntity);
        employerRepository.assignEmployerToCandidateAndChangeStatus(null, 1L);
        verify(employerJpaRepository).assignEmployerToCandidateAndChangeStatus(Mockito.<EmployerEntity>any(),
                Mockito.<Long>any());
        verify(employerEntityMapper).mapToEntity(Mockito.<Employer>any());
    }

    /**
     * Method under test: {@link EmployerRepository#deleteAssignEmployerToCandidateAndChangeStatus(Long)}
     */
    @Test
    void testDeleteAssignEmployerToCandidateAndChangeStatus() {
        doNothing().when(employerJpaRepository).deleteEmployerFromCandidateAndChangeStatus(Mockito.<Long>any());
        employerRepository.deleteAssignEmployerToCandidateAndChangeStatus(1L);
        verify(employerJpaRepository).deleteEmployerFromCandidateAndChangeStatus(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EmployerRepository#findByCompanyName(String)}
     */
    @Test
    void testFindByCompanyName() {
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
        Optional<EmployerEntity> ofResult = Optional.of(employerEntity);
        when(employerJpaRepository.findByCompanyName(Mockito.<String>any())).thenReturn(ofResult);
        when(employerEntityMapper.mapFromEntity(Mockito.<EmployerEntity>any())).thenReturn(null);
        assertFalse(employerRepository.findByCompanyName("Employer Company Name").isPresent());
        verify(employerJpaRepository).findByCompanyName(Mockito.<String>any());
        verify(employerEntityMapper).mapFromEntity(Mockito.<EmployerEntity>any());
    }
}

