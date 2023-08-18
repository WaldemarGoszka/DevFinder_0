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

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.Employer;
import pl.devfinder.domain.Offer;
import pl.devfinder.infrastructure.database.entity.CityEntity;
import pl.devfinder.infrastructure.database.entity.EmployerEntity;
import pl.devfinder.infrastructure.database.entity.OfferEntity;
import pl.devfinder.infrastructure.database.repository.jpa.OfferJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.EmployerEntityMapper;
import pl.devfinder.infrastructure.database.repository.mapper.OfferEntityMapper;

@ContextConfiguration(classes = {OfferRepository.class})
@ExtendWith(SpringExtension.class)
class OfferRepositoryDiffBlueTest {
    @MockBean
    private EmployerEntityMapper employerEntityMapper;

    @MockBean
    private OfferEntityMapper offerEntityMapper;

    @MockBean
    private OfferJpaRepository offerJpaRepository;

    @Autowired
    private OfferRepository offerRepository;

    /**
     * Method under test: {@link OfferRepository#findAllByState(Keys.OfferState)}
     */
    @Test
    void testFindAllByState() {
        when(offerJpaRepository.findAllByState(Mockito.<String>any())).thenReturn(new ArrayList<>());
        assertTrue(offerRepository.findAllByState(Keys.OfferState.ACTIVE).isEmpty());
        verify(offerJpaRepository).findAllByState(Mockito.<String>any());
    }

    /**
     * Method under test: {@link OfferRepository#findAllByState(Keys.OfferState)}
     */
    @Test
    void testFindAllByState2() {
        when(offerJpaRepository.findAllByState(Mockito.<String>any())).thenReturn(new ArrayList<>());
        assertTrue(offerRepository.findAllByState(Keys.OfferState.EXPIRED).isEmpty());
        verify(offerJpaRepository).findAllByState(Mockito.<String>any());
    }

    /**
     * Method under test: {@link OfferRepository#findAllByState(Keys.OfferState, Pageable)}
     */
    @Test
    void testFindAllByState3() {
        when(offerJpaRepository.findAllByState(Mockito.<String>any(), Mockito.<Pageable>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(offerRepository.findAllByState(Keys.OfferState.ACTIVE, null).toList().isEmpty());
        verify(offerJpaRepository).findAllByState(Mockito.<String>any(), Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link OfferRepository#findAllByState(Keys.OfferState, Pageable)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindAllByState4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.data.domain.Page.map(java.util.function.Function)" because the return value of "pl.devfinder.infrastructure.database.repository.jpa.OfferJpaRepository.findAllByState(String, org.springframework.data.domain.Pageable)" is null
        //       at pl.devfinder.infrastructure.database.repository.OfferRepository.findAllByState(OfferRepository.java:37)
        //   See https://diff.blue/R013 to resolve this issue.

        when(offerJpaRepository.findAllByState(Mockito.<String>any(), Mockito.<Pageable>any())).thenReturn(null);
        offerRepository.findAllByState(Keys.OfferState.ACTIVE, null);
    }

    /**
     * Method under test: {@link OfferRepository#findAllByState(Keys.OfferState, Pageable)}
     */
    @Test
    void testFindAllByState5() {
        when(offerJpaRepository.findAllByState(Mockito.<String>any(), Mockito.<Pageable>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(offerRepository.findAllByState(Keys.OfferState.EXPIRED, null).toList().isEmpty());
        verify(offerJpaRepository).findAllByState(Mockito.<String>any(), Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link OfferRepository#getNumberOfOffersByEmployerAndByState(Long, Keys.OfferState)}
     */
    @Test
    void testGetNumberOfOffersByEmployerAndByState() {
        when(offerJpaRepository.getNumberOfOffersByEmployerAndByState(Mockito.<Long>any(), Mockito.<String>any()))
                .thenReturn(1L);
        assertEquals(1L, offerRepository.getNumberOfOffersByEmployerAndByState(1L, Keys.OfferState.ACTIVE).longValue());
        verify(offerJpaRepository).getNumberOfOffersByEmployerAndByState(Mockito.<Long>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link OfferRepository#getNumberOfOffersByEmployerAndByState(Long, Keys.OfferState)}
     */
    @Test
    void testGetNumberOfOffersByEmployerAndByState2() {
        when(offerJpaRepository.getNumberOfOffersByEmployerAndByState(Mockito.<Long>any(), Mockito.<String>any()))
                .thenReturn(1L);
        assertEquals(1L, offerRepository.getNumberOfOffersByEmployerAndByState(1L, Keys.OfferState.EXPIRED).longValue());
        verify(offerJpaRepository).getNumberOfOffersByEmployerAndByState(Mockito.<Long>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link OfferRepository#findById(Long)}
     */
    @Test
    void testFindById() {
        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        CityEntity cityId2 = new CityEntity();
        cityId2.setCandidateResidenceCities(new HashSet<>());
        cityId2.setCityId(1L);
        cityId2.setCityName("Oxford");
        cityId2.setEmployerCities(new HashSet<>());
        cityId2.setOfferCities(new HashSet<>());

        EmployerEntity employerId = new EmployerEntity();
        employerId.setAmountOfAvailableOffers(10);
        employerId.setCityId(cityId2);
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

        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setBenefits("Benefits");
        offerEntity.setCityId(cityId);
        offerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        offerEntity.setDescription("The characteristics of someone or something");
        offerEntity.setEmployerId(employerId);
        offerEntity.setExperienceLevel("Experience Level");
        offerEntity.setOfferId(1L);
        offerEntity.setOfferSkills(new HashSet<>());
        offerEntity.setOfferUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        offerEntity.setOtherSkills("Other Skills");
        offerEntity.setRemoteWork(1);
        offerEntity.setSalaryMax(BigDecimal.valueOf(1L));
        offerEntity.setSalaryMin(BigDecimal.valueOf(1L));
        offerEntity.setStatus("Status");
        offerEntity.setTitle("Dr");
        offerEntity.setYearsOfExperience(1);
        Optional<OfferEntity> ofResult = Optional.of(offerEntity);
        when(offerJpaRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(offerEntityMapper.mapFromEntity(Mockito.<OfferEntity>any())).thenReturn(null);
        assertFalse(offerRepository.findById(1L).isPresent());
        verify(offerJpaRepository).findById(Mockito.<Long>any());
        verify(offerEntityMapper).mapFromEntity(Mockito.<OfferEntity>any());
    }

    /**
     * Method under test: {@link OfferRepository#countByCityName(String)}
     */
    @Test
    void testCountByCityName() {
        when(offerJpaRepository.countByCityName(Mockito.<String>any())).thenReturn(3L);
        assertEquals(3L, offerRepository.countByCityName("Oxford"));
        verify(offerJpaRepository).countByCityName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link OfferRepository#findByOfferIdAndEmployerId(Long, Employer)}
     */
    @Test
    void testFindByOfferIdAndEmployerId() {
        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        CityEntity cityId2 = new CityEntity();
        cityId2.setCandidateResidenceCities(new HashSet<>());
        cityId2.setCityId(1L);
        cityId2.setCityName("Oxford");
        cityId2.setEmployerCities(new HashSet<>());
        cityId2.setOfferCities(new HashSet<>());

        EmployerEntity employerId = new EmployerEntity();
        employerId.setAmountOfAvailableOffers(10);
        employerId.setCityId(cityId2);
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

        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setBenefits("Benefits");
        offerEntity.setCityId(cityId);
        offerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        offerEntity.setDescription("The characteristics of someone or something");
        offerEntity.setEmployerId(employerId);
        offerEntity.setExperienceLevel("Experience Level");
        offerEntity.setOfferId(1L);
        offerEntity.setOfferSkills(new HashSet<>());
        offerEntity.setOfferUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        offerEntity.setOtherSkills("Other Skills");
        offerEntity.setRemoteWork(1);
        offerEntity.setSalaryMax(BigDecimal.valueOf(1L));
        offerEntity.setSalaryMin(BigDecimal.valueOf(1L));
        offerEntity.setStatus("Status");
        offerEntity.setTitle("Dr");
        offerEntity.setYearsOfExperience(1);
        Optional<OfferEntity> ofResult = Optional.of(offerEntity);
        when(offerJpaRepository.findByOfferIdAndEmployerId(Mockito.<Long>any(), Mockito.<EmployerEntity>any()))
                .thenReturn(ofResult);
        when(offerEntityMapper.mapFromEntity(Mockito.<OfferEntity>any())).thenReturn(null);

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
        when(employerEntityMapper.mapToEntity(Mockito.<Employer>any())).thenReturn(employerEntity);
        assertFalse(offerRepository.findByOfferIdAndEmployerId(1L, null).isPresent());
        verify(offerJpaRepository).findByOfferIdAndEmployerId(Mockito.<Long>any(), Mockito.<EmployerEntity>any());
        verify(offerEntityMapper).mapFromEntity(Mockito.<OfferEntity>any());
        verify(employerEntityMapper).mapToEntity(Mockito.<Employer>any());
    }

    /**
     * Method under test: {@link OfferRepository#deleteAllByEmployerId(Employer)}
     */
    @Test
    void testDeleteAllByEmployerId() {
        doNothing().when(offerJpaRepository).deleteAllByEmployerId(Mockito.<EmployerEntity>any());

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
        offerRepository.deleteAllByEmployerId(null);
        verify(offerJpaRepository).deleteAllByEmployerId(Mockito.<EmployerEntity>any());
        verify(employerEntityMapper).mapToEntity(Mockito.<Employer>any());
    }

    /**
     * Method under test: {@link OfferRepository#findAllByEmployer(Employer)}
     */
    @Test
    void testFindAllByEmployer() {
        when(offerJpaRepository.findAllByEmployerId(Mockito.<EmployerEntity>any())).thenReturn(new ArrayList<>());

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
        assertTrue(offerRepository.findAllByEmployer(null).isEmpty());
        verify(offerJpaRepository).findAllByEmployerId(Mockito.<EmployerEntity>any());
        verify(employerEntityMapper).mapToEntity(Mockito.<Employer>any());
    }

    /**
     * Method under test: {@link OfferRepository#deleteById(Long)}
     */
    @Test
    void testDeleteById() {
        doNothing().when(offerJpaRepository).deleteById(Mockito.<Long>any());
        offerRepository.deleteById(1L);
        verify(offerJpaRepository).deleteById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link OfferRepository#save(Offer)}
     */
    @Test
    void testSave() {
        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        CityEntity cityId2 = new CityEntity();
        cityId2.setCandidateResidenceCities(new HashSet<>());
        cityId2.setCityId(1L);
        cityId2.setCityName("Oxford");
        cityId2.setEmployerCities(new HashSet<>());
        cityId2.setOfferCities(new HashSet<>());

        EmployerEntity employerId = new EmployerEntity();
        employerId.setAmountOfAvailableOffers(10);
        employerId.setCityId(cityId2);
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

        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setBenefits("Benefits");
        offerEntity.setCityId(cityId);
        offerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        offerEntity.setDescription("The characteristics of someone or something");
        offerEntity.setEmployerId(employerId);
        offerEntity.setExperienceLevel("Experience Level");
        offerEntity.setOfferId(1L);
        offerEntity.setOfferSkills(new HashSet<>());
        offerEntity.setOfferUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        offerEntity.setOtherSkills("Other Skills");
        offerEntity.setRemoteWork(1);
        offerEntity.setSalaryMax(BigDecimal.valueOf(1L));
        offerEntity.setSalaryMin(BigDecimal.valueOf(1L));
        offerEntity.setStatus("Status");
        offerEntity.setTitle("Dr");
        offerEntity.setYearsOfExperience(1);
        when(offerJpaRepository.saveAndFlush(Mockito.<OfferEntity>any())).thenReturn(offerEntity);

        CityEntity cityId3 = new CityEntity();
        cityId3.setCandidateResidenceCities(new HashSet<>());
        cityId3.setCityId(1L);
        cityId3.setCityName("Oxford");
        cityId3.setEmployerCities(new HashSet<>());
        cityId3.setOfferCities(new HashSet<>());

        CityEntity cityId4 = new CityEntity();
        cityId4.setCandidateResidenceCities(new HashSet<>());
        cityId4.setCityId(1L);
        cityId4.setCityName("Oxford");
        cityId4.setEmployerCities(new HashSet<>());
        cityId4.setOfferCities(new HashSet<>());

        EmployerEntity employerId2 = new EmployerEntity();
        employerId2.setAmountOfAvailableOffers(10);
        employerId2.setCityId(cityId4);
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

        OfferEntity offerEntity2 = new OfferEntity();
        offerEntity2.setBenefits("Benefits");
        offerEntity2.setCityId(cityId3);
        offerEntity2.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        offerEntity2.setDescription("The characteristics of someone or something");
        offerEntity2.setEmployerId(employerId2);
        offerEntity2.setExperienceLevel("Experience Level");
        offerEntity2.setOfferId(1L);
        offerEntity2.setOfferSkills(new HashSet<>());
        offerEntity2.setOfferUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        offerEntity2.setOtherSkills("Other Skills");
        offerEntity2.setRemoteWork(1);
        offerEntity2.setSalaryMax(BigDecimal.valueOf(1L));
        offerEntity2.setSalaryMin(BigDecimal.valueOf(1L));
        offerEntity2.setStatus("Status");
        offerEntity2.setTitle("Dr");
        offerEntity2.setYearsOfExperience(1);
        when(offerEntityMapper.mapFromEntity(Mockito.<OfferEntity>any())).thenReturn(null);
        when(offerEntityMapper.mapToEntity(Mockito.<Offer>any())).thenReturn(offerEntity2);
        assertNull(offerRepository.save(null));
        verify(offerJpaRepository).saveAndFlush(Mockito.<OfferEntity>any());
        verify(offerEntityMapper).mapFromEntity(Mockito.<OfferEntity>any());
        verify(offerEntityMapper).mapToEntity(Mockito.<Offer>any());
    }
}

