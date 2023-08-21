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

@ContextConfiguration(classes = {OfferEntityMapperImpl.class})
@ExtendWith(SpringExtension.class)
class OfferEntityMapperImplDiffBlueTest {
    @Autowired
    private OfferEntityMapperImpl offerEntityMapperImpl;

    /**
     * Method under test: {@link OfferEntityMapperImpl#mapFromEntity(OfferEntity)}
     */
    @Test
    void testMapFromEntity() {
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
        BigDecimal salaryMin = BigDecimal.valueOf(1L);
        offerEntity.setSalaryMin(salaryMin);
        offerEntity.setStatus("Status");
        offerEntity.setTitle("Dr");
        offerEntity.setYearsOfExperience(1);
        Offer actualMapFromEntityResult = offerEntityMapperImpl.mapFromEntity(offerEntity);
        assertEquals("Benefits", actualMapFromEntityResult.getBenefits());
        assertEquals(1, actualMapFromEntityResult.getYearsOfExperience().intValue());
        assertEquals("Dr", actualMapFromEntityResult.getTitle());
        assertEquals("Status", actualMapFromEntityResult.getStatus());
        BigDecimal expectedSalaryMin = BigDecimal.ONE;
        BigDecimal salaryMin2 = actualMapFromEntityResult.getSalaryMin();
        assertSame(expectedSalaryMin, salaryMin2);
        assertSame(salaryMin2, actualMapFromEntityResult.getSalaryMax());
        assertEquals("The characteristics of someone or something", actualMapFromEntityResult.getDescription());
        assertTrue(actualMapFromEntityResult.getOfferSkills().isEmpty());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", actualMapFromEntityResult.getOfferUuid());
        assertEquals("Other Skills", actualMapFromEntityResult.getOtherSkills());
        assertEquals(1, actualMapFromEntityResult.getRemoteWork().intValue());
        assertEquals("Z", actualMapFromEntityResult.getCreatedAt().getOffset().toString());
        assertEquals("Experience Level", actualMapFromEntityResult.getExperienceLevel());
        assertEquals(1L, actualMapFromEntityResult.getOfferId().longValue());
        Employer employerId2 = actualMapFromEntityResult.getEmployerId();
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", employerId2.getEmployerUuid());
        assertEquals(1L, employerId2.getEmployerId().longValue());
        assertEquals("jane.doe@example.org", employerId2.getEmailContact());
        assertEquals("The characteristics of someone or something", employerId2.getDescription());
        assertEquals("Z", employerId2.getCreatedAt().getOffset().toString());
        assertEquals("Company Name", employerId2.getCompanyName());
        assertNull(employerId2.getCityId());
        assertEquals(10, employerId2.getAmountOfAvailableOffers().intValue());
        assertEquals("Website", employerId2.getWebsite());
        City cityId3 = actualMapFromEntityResult.getCityId();
        assertEquals("Oxford", cityId3.getCityName());
        assertEquals("1", salaryMin2.toString());
        assertEquals(10, employerId2.getNumberOfEmployees().intValue());
        assertEquals("6625550144", employerId2.getPhoneNumber());
        assertEquals(1L, cityId3.getCityId().longValue());
        assertEquals("foo.txt", employerId2.getLogoFilename());
    }

    /**
     * Method under test: {@link OfferEntityMapperImpl#mapFromEntity(OfferEntity)}
     */
    @Test
    void testMapFromEntity2() {
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
        OfferEntity offerEntity = mock(OfferEntity.class);
        when(offerEntity.getRemoteWork()).thenReturn(1);
        when(offerEntity.getYearsOfExperience()).thenReturn(1);
        when(offerEntity.getOfferId()).thenReturn(1L);
        when(offerEntity.getBenefits()).thenReturn("Benefits");
        when(offerEntity.getDescription()).thenReturn("The characteristics of someone or something");
        when(offerEntity.getExperienceLevel()).thenReturn("Experience Level");
        when(offerEntity.getOfferUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(offerEntity.getOtherSkills()).thenReturn("Other Skills");
        when(offerEntity.getStatus()).thenReturn("Status");
        when(offerEntity.getTitle()).thenReturn("Dr");
        when(offerEntity.getSalaryMax()).thenReturn(BigDecimal.valueOf(1L));
        when(offerEntity.getSalaryMin()).thenReturn(BigDecimal.valueOf(1L));
        when(offerEntity.getCreatedAt())
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        when(offerEntity.getOfferSkills()).thenReturn(new HashSet<>());
        when(offerEntity.getCityId()).thenReturn(cityEntity);
        when(offerEntity.getEmployerId()).thenReturn(employerEntity);
        doNothing().when(offerEntity).setBenefits(Mockito.any());
        doNothing().when(offerEntity).setCityId(Mockito.any());
        doNothing().when(offerEntity).setCreatedAt(Mockito.any());
        doNothing().when(offerEntity).setDescription(Mockito.any());
        doNothing().when(offerEntity).setEmployerId(Mockito.any());
        doNothing().when(offerEntity).setExperienceLevel(Mockito.any());
        doNothing().when(offerEntity).setOfferId(Mockito.<Long>any());
        doNothing().when(offerEntity).setOfferSkills(Mockito.any());
        doNothing().when(offerEntity).setOfferUuid(Mockito.any());
        doNothing().when(offerEntity).setOtherSkills(Mockito.any());
        doNothing().when(offerEntity).setRemoteWork(Mockito.<Integer>any());
        doNothing().when(offerEntity).setSalaryMax(Mockito.any());
        doNothing().when(offerEntity).setSalaryMin(Mockito.any());
        doNothing().when(offerEntity).setStatus(Mockito.any());
        doNothing().when(offerEntity).setTitle(Mockito.any());
        doNothing().when(offerEntity).setYearsOfExperience(Mockito.<Integer>any());
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
        BigDecimal salaryMin = BigDecimal.valueOf(1L);
        offerEntity.setSalaryMin(salaryMin);
        offerEntity.setStatus("Status");
        offerEntity.setTitle("Dr");
        offerEntity.setYearsOfExperience(1);
        Offer actualMapFromEntityResult = offerEntityMapperImpl.mapFromEntity(offerEntity);
        assertEquals("Benefits", actualMapFromEntityResult.getBenefits());
        assertEquals(1, actualMapFromEntityResult.getYearsOfExperience().intValue());
        assertEquals("Dr", actualMapFromEntityResult.getTitle());
        assertEquals("Status", actualMapFromEntityResult.getStatus());
        BigDecimal expectedSalaryMin = BigDecimal.ONE;
        BigDecimal salaryMin2 = actualMapFromEntityResult.getSalaryMin();
        assertSame(expectedSalaryMin, salaryMin2);
        assertSame(salaryMin2, actualMapFromEntityResult.getSalaryMax());
        assertEquals("The characteristics of someone or something", actualMapFromEntityResult.getDescription());
        assertTrue(actualMapFromEntityResult.getOfferSkills().isEmpty());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", actualMapFromEntityResult.getOfferUuid());
        assertEquals("Other Skills", actualMapFromEntityResult.getOtherSkills());
        assertEquals(1, actualMapFromEntityResult.getRemoteWork().intValue());
        assertEquals("Z", actualMapFromEntityResult.getCreatedAt().getOffset().toString());
        assertEquals("Experience Level", actualMapFromEntityResult.getExperienceLevel());
        assertEquals(1L, actualMapFromEntityResult.getOfferId().longValue());
        Employer employerId2 = actualMapFromEntityResult.getEmployerId();
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", employerId2.getEmployerUuid());
        assertEquals(1L, employerId2.getEmployerId().longValue());
        assertEquals("jane.doe@example.org", employerId2.getEmailContact());
        assertEquals("The characteristics of someone or something", employerId2.getDescription());
        assertEquals("Z", employerId2.getCreatedAt().getOffset().toString());
        assertEquals("Company Name", employerId2.getCompanyName());
        assertNull(employerId2.getCityId());
        assertEquals(10, employerId2.getAmountOfAvailableOffers().intValue());
        assertEquals("Website", employerId2.getWebsite());
        City cityId4 = actualMapFromEntityResult.getCityId();
        assertEquals("Oxford", cityId4.getCityName());
        assertEquals("1", salaryMin2.toString());
        assertEquals(10, employerId2.getNumberOfEmployees().intValue());
        assertEquals("6625550144", employerId2.getPhoneNumber());
        assertEquals(1L, cityId4.getCityId().longValue());
        assertEquals("foo.txt", employerId2.getLogoFilename());
        verify(offerEntity).getRemoteWork();
        verify(offerEntity).getYearsOfExperience();
        verify(offerEntity).getOfferId();
        verify(offerEntity).getBenefits();
        verify(offerEntity).getDescription();
        verify(offerEntity).getExperienceLevel();
        verify(offerEntity).getOfferUuid();
        verify(offerEntity).getOtherSkills();
        verify(offerEntity).getStatus();
        verify(offerEntity).getTitle();
        verify(offerEntity).getSalaryMax();
        verify(offerEntity).getSalaryMin();
        verify(offerEntity).getCreatedAt();
        verify(offerEntity).getOfferSkills();
        verify(offerEntity).getCityId();
        verify(offerEntity).getEmployerId();
        verify(offerEntity).setBenefits(Mockito.any());
        verify(offerEntity).setCityId(Mockito.any());
        verify(offerEntity).setCreatedAt(Mockito.any());
        verify(offerEntity).setDescription(Mockito.any());
        verify(offerEntity).setEmployerId(Mockito.any());
        verify(offerEntity).setExperienceLevel(Mockito.any());
        verify(offerEntity).setOfferId(Mockito.<Long>any());
        verify(offerEntity).setOfferSkills(Mockito.any());
        verify(offerEntity).setOfferUuid(Mockito.any());
        verify(offerEntity).setOtherSkills(Mockito.any());
        verify(offerEntity).setRemoteWork(Mockito.<Integer>any());
        verify(offerEntity).setSalaryMax(Mockito.any());
        verify(offerEntity).setSalaryMin(Mockito.any());
        verify(offerEntity).setStatus(Mockito.any());
        verify(offerEntity).setTitle(Mockito.any());
        verify(offerEntity).setYearsOfExperience(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link OfferEntityMapperImpl#mapFromEntity(OfferEntity)}
     */
    @Test
    void testMapFromEntity3() {
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

        OfferEntity offerId = new OfferEntity();
        offerId.setBenefits("Benefits");
        offerId.setCityId(cityId3);
        offerId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        offerId.setDescription("The characteristics of someone or something");
        offerId.setEmployerId(employerId2);
        offerId.setExperienceLevel("Experience Level");
        offerId.setOfferId(1L);
        offerId.setOfferSkills(new HashSet<>());
        offerId.setOfferUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        offerId.setOtherSkills("Other Skills");
        offerId.setRemoteWork(1);
        offerId.setSalaryMax(BigDecimal.valueOf(1L));
        offerId.setSalaryMin(BigDecimal.valueOf(1L));
        offerId.setStatus("Status");
        offerId.setTitle("Dr");
        offerId.setYearsOfExperience(1);

        SkillEntity skillId = new SkillEntity();
        skillId.setCandidateSkills(new HashSet<>());
        skillId.setOfferSkills(new HashSet<>());
        skillId.setSkillId(1L);
        skillId.setSkillName("Skill Name");

        OfferSkillEntity offerSkillEntity = new OfferSkillEntity();
        offerSkillEntity.setOfferId(offerId);
        offerSkillEntity.setOfferSkillId(1L);
        offerSkillEntity.setSkillId(skillId);

        HashSet<OfferSkillEntity> offerSkillEntitySet = new HashSet<>();
        offerSkillEntitySet.add(offerSkillEntity);

        CityEntity cityEntity = new CityEntity();
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());

        CityEntity cityId5 = new CityEntity();
        cityId5.setCandidateResidenceCities(new HashSet<>());
        cityId5.setCityId(1L);
        cityId5.setCityName("Oxford");
        cityId5.setEmployerCities(new HashSet<>());
        cityId5.setOfferCities(new HashSet<>());

        EmployerEntity employerEntity = new EmployerEntity();
        employerEntity.setAmountOfAvailableOffers(10);
        employerEntity.setCityId(cityId5);
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
        OfferEntity offerEntity = mock(OfferEntity.class);
        when(offerEntity.getRemoteWork()).thenReturn(1);
        when(offerEntity.getYearsOfExperience()).thenReturn(1);
        when(offerEntity.getOfferId()).thenReturn(1L);
        when(offerEntity.getBenefits()).thenReturn("Benefits");
        when(offerEntity.getDescription()).thenReturn("The characteristics of someone or something");
        when(offerEntity.getExperienceLevel()).thenReturn("Experience Level");
        when(offerEntity.getOfferUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(offerEntity.getOtherSkills()).thenReturn("Other Skills");
        when(offerEntity.getStatus()).thenReturn("Status");
        when(offerEntity.getTitle()).thenReturn("Dr");
        when(offerEntity.getSalaryMax()).thenReturn(BigDecimal.valueOf(1L));
        when(offerEntity.getSalaryMin()).thenReturn(BigDecimal.valueOf(1L));
        when(offerEntity.getCreatedAt())
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        when(offerEntity.getOfferSkills()).thenReturn(offerSkillEntitySet);
        when(offerEntity.getCityId()).thenReturn(cityEntity);
        when(offerEntity.getEmployerId()).thenReturn(employerEntity);
        doNothing().when(offerEntity).setBenefits(Mockito.any());
        doNothing().when(offerEntity).setCityId(Mockito.any());
        doNothing().when(offerEntity).setCreatedAt(Mockito.any());
        doNothing().when(offerEntity).setDescription(Mockito.any());
        doNothing().when(offerEntity).setEmployerId(Mockito.any());
        doNothing().when(offerEntity).setExperienceLevel(Mockito.any());
        doNothing().when(offerEntity).setOfferId(Mockito.<Long>any());
        doNothing().when(offerEntity).setOfferSkills(Mockito.any());
        doNothing().when(offerEntity).setOfferUuid(Mockito.any());
        doNothing().when(offerEntity).setOtherSkills(Mockito.any());
        doNothing().when(offerEntity).setRemoteWork(Mockito.<Integer>any());
        doNothing().when(offerEntity).setSalaryMax(Mockito.any());
        doNothing().when(offerEntity).setSalaryMin(Mockito.any());
        doNothing().when(offerEntity).setStatus(Mockito.any());
        doNothing().when(offerEntity).setTitle(Mockito.any());
        doNothing().when(offerEntity).setYearsOfExperience(Mockito.<Integer>any());
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
        BigDecimal salaryMin = BigDecimal.valueOf(1L);
        offerEntity.setSalaryMin(salaryMin);
        offerEntity.setStatus("Status");
        offerEntity.setTitle("Dr");
        offerEntity.setYearsOfExperience(1);
        Offer actualMapFromEntityResult = offerEntityMapperImpl.mapFromEntity(offerEntity);
        assertEquals("Benefits", actualMapFromEntityResult.getBenefits());
        assertEquals(1, actualMapFromEntityResult.getYearsOfExperience().intValue());
        assertEquals("Dr", actualMapFromEntityResult.getTitle());
        assertEquals("Status", actualMapFromEntityResult.getStatus());
        BigDecimal expectedSalaryMin = BigDecimal.ONE;
        BigDecimal salaryMin2 = actualMapFromEntityResult.getSalaryMin();
        assertSame(expectedSalaryMin, salaryMin2);
        assertSame(salaryMin2, actualMapFromEntityResult.getSalaryMax());
        assertEquals("The characteristics of someone or something", actualMapFromEntityResult.getDescription());
        assertEquals(1, actualMapFromEntityResult.getOfferSkills().size());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", actualMapFromEntityResult.getOfferUuid());
        assertEquals("Other Skills", actualMapFromEntityResult.getOtherSkills());
        assertEquals(1, actualMapFromEntityResult.getRemoteWork().intValue());
        assertEquals("Z", actualMapFromEntityResult.getCreatedAt().getOffset().toString());
        assertEquals("Experience Level", actualMapFromEntityResult.getExperienceLevel());
        assertEquals(1L, actualMapFromEntityResult.getOfferId().longValue());
        Employer employerId3 = actualMapFromEntityResult.getEmployerId();
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", employerId3.getEmployerUuid());
        assertEquals(1L, employerId3.getEmployerId().longValue());
        assertEquals("jane.doe@example.org", employerId3.getEmailContact());
        assertEquals("The characteristics of someone or something", employerId3.getDescription());
        assertEquals("Z", employerId3.getCreatedAt().getOffset().toString());
        assertEquals("Company Name", employerId3.getCompanyName());
        assertNull(employerId3.getCityId());
        assertEquals(10, employerId3.getAmountOfAvailableOffers().intValue());
        assertEquals("Website", employerId3.getWebsite());
        City cityId6 = actualMapFromEntityResult.getCityId();
        assertEquals("Oxford", cityId6.getCityName());
        assertEquals("1", salaryMin2.toString());
        assertEquals(10, employerId3.getNumberOfEmployees().intValue());
        assertEquals("6625550144", employerId3.getPhoneNumber());
        assertEquals(1L, cityId6.getCityId().longValue());
        assertEquals("foo.txt", employerId3.getLogoFilename());
        verify(offerEntity).getRemoteWork();
        verify(offerEntity).getYearsOfExperience();
        verify(offerEntity).getOfferId();
        verify(offerEntity).getBenefits();
        verify(offerEntity).getDescription();
        verify(offerEntity).getExperienceLevel();
        verify(offerEntity).getOfferUuid();
        verify(offerEntity).getOtherSkills();
        verify(offerEntity).getStatus();
        verify(offerEntity).getTitle();
        verify(offerEntity).getSalaryMax();
        verify(offerEntity).getSalaryMin();
        verify(offerEntity).getCreatedAt();
        verify(offerEntity).getOfferSkills();
        verify(offerEntity).getCityId();
        verify(offerEntity).getEmployerId();
        verify(offerEntity).setBenefits(Mockito.any());
        verify(offerEntity).setCityId(Mockito.any());
        verify(offerEntity).setCreatedAt(Mockito.any());
        verify(offerEntity).setDescription(Mockito.any());
        verify(offerEntity).setEmployerId(Mockito.any());
        verify(offerEntity).setExperienceLevel(Mockito.any());
        verify(offerEntity).setOfferId(Mockito.<Long>any());
        verify(offerEntity).setOfferSkills(Mockito.any());
        verify(offerEntity).setOfferUuid(Mockito.any());
        verify(offerEntity).setOtherSkills(Mockito.any());
        verify(offerEntity).setRemoteWork(Mockito.<Integer>any());
        verify(offerEntity).setSalaryMax(Mockito.any());
        verify(offerEntity).setSalaryMin(Mockito.any());
        verify(offerEntity).setStatus(Mockito.any());
        verify(offerEntity).setTitle(Mockito.any());
        verify(offerEntity).setYearsOfExperience(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link OfferEntityMapperImpl#mapFromEntity(OfferEntity)}
     */
    @Test
    void testMapFromEntity4() {
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

        OfferEntity offerId = new OfferEntity();
        offerId.setBenefits("Benefits");
        offerId.setCityId(cityId3);
        offerId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        offerId.setDescription("The characteristics of someone or something");
        offerId.setEmployerId(employerId2);
        offerId.setExperienceLevel("Experience Level");
        offerId.setOfferId(1L);
        offerId.setOfferSkills(new HashSet<>());
        offerId.setOfferUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        offerId.setOtherSkills("Other Skills");
        offerId.setRemoteWork(1);
        offerId.setSalaryMax(BigDecimal.valueOf(1L));
        offerId.setSalaryMin(BigDecimal.valueOf(1L));
        offerId.setStatus("Status");
        offerId.setTitle("Dr");
        offerId.setYearsOfExperience(1);

        SkillEntity skillId = new SkillEntity();
        skillId.setCandidateSkills(new HashSet<>());
        skillId.setOfferSkills(new HashSet<>());
        skillId.setSkillId(1L);
        skillId.setSkillName("Skill Name");

        OfferSkillEntity offerSkillEntity = new OfferSkillEntity();
        offerSkillEntity.setOfferId(offerId);
        offerSkillEntity.setOfferSkillId(1L);
        offerSkillEntity.setSkillId(skillId);

        CityEntity cityId5 = new CityEntity();
        cityId5.setCandidateResidenceCities(new HashSet<>());
        cityId5.setCityId(2L);
        cityId5.setCityName("London");
        cityId5.setEmployerCities(new HashSet<>());
        cityId5.setOfferCities(new HashSet<>());

        CityEntity cityId6 = new CityEntity();
        cityId6.setCandidateResidenceCities(new HashSet<>());
        cityId6.setCityId(2L);
        cityId6.setCityName("London");
        cityId6.setEmployerCities(new HashSet<>());
        cityId6.setOfferCities(new HashSet<>());

        EmployerEntity employerId3 = new EmployerEntity();
        employerId3.setAmountOfAvailableOffers(59);
        employerId3.setCityId(cityId6);
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

        OfferEntity offerId2 = new OfferEntity();
        offerId2.setBenefits("pl.devfinder.infrastructure.database.entity.OfferEntity");
        offerId2.setCityId(cityId5);
        offerId2.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        offerId2.setDescription("Description");
        offerId2.setEmployerId(employerId3);
        offerId2.setExperienceLevel("pl.devfinder.infrastructure.database.entity.OfferEntity");
        offerId2.setOfferId(2L);
        offerId2.setOfferSkills(new HashSet<>());
        offerId2.setOfferUuid("1234");
        offerId2.setOtherSkills("pl.devfinder.infrastructure.database.entity.OfferEntity");
        offerId2.setRemoteWork(1);
        offerId2.setSalaryMax(BigDecimal.valueOf(59L));
        offerId2.setSalaryMin(BigDecimal.valueOf(59L));
        offerId2.setStatus("pl.devfinder.infrastructure.database.entity.OfferEntity");
        offerId2.setTitle("Mr");
        offerId2.setYearsOfExperience(1);

        SkillEntity skillId2 = new SkillEntity();
        skillId2.setCandidateSkills(new HashSet<>());
        skillId2.setOfferSkills(new HashSet<>());
        skillId2.setSkillId(2L);
        skillId2.setSkillName("pl.devfinder.infrastructure.database.entity.SkillEntity");

        OfferSkillEntity offerSkillEntity2 = new OfferSkillEntity();
        offerSkillEntity2.setOfferId(offerId2);
        offerSkillEntity2.setOfferSkillId(2L);
        offerSkillEntity2.setSkillId(skillId2);

        HashSet<OfferSkillEntity> offerSkillEntitySet = new HashSet<>();
        offerSkillEntitySet.add(offerSkillEntity2);
        offerSkillEntitySet.add(offerSkillEntity);

        CityEntity cityEntity = new CityEntity();
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());

        CityEntity cityId7 = new CityEntity();
        cityId7.setCandidateResidenceCities(new HashSet<>());
        cityId7.setCityId(1L);
        cityId7.setCityName("Oxford");
        cityId7.setEmployerCities(new HashSet<>());
        cityId7.setOfferCities(new HashSet<>());

        EmployerEntity employerEntity = new EmployerEntity();
        employerEntity.setAmountOfAvailableOffers(10);
        employerEntity.setCityId(cityId7);
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
        OfferEntity offerEntity = mock(OfferEntity.class);
        when(offerEntity.getRemoteWork()).thenReturn(1);
        when(offerEntity.getYearsOfExperience()).thenReturn(1);
        when(offerEntity.getOfferId()).thenReturn(1L);
        when(offerEntity.getBenefits()).thenReturn("Benefits");
        when(offerEntity.getDescription()).thenReturn("The characteristics of someone or something");
        when(offerEntity.getExperienceLevel()).thenReturn("Experience Level");
        when(offerEntity.getOfferUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(offerEntity.getOtherSkills()).thenReturn("Other Skills");
        when(offerEntity.getStatus()).thenReturn("Status");
        when(offerEntity.getTitle()).thenReturn("Dr");
        when(offerEntity.getSalaryMax()).thenReturn(BigDecimal.valueOf(1L));
        when(offerEntity.getSalaryMin()).thenReturn(BigDecimal.valueOf(1L));
        when(offerEntity.getCreatedAt())
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        when(offerEntity.getOfferSkills()).thenReturn(offerSkillEntitySet);
        when(offerEntity.getCityId()).thenReturn(cityEntity);
        when(offerEntity.getEmployerId()).thenReturn(employerEntity);
        doNothing().when(offerEntity).setBenefits(Mockito.any());
        doNothing().when(offerEntity).setCityId(Mockito.any());
        doNothing().when(offerEntity).setCreatedAt(Mockito.any());
        doNothing().when(offerEntity).setDescription(Mockito.any());
        doNothing().when(offerEntity).setEmployerId(Mockito.any());
        doNothing().when(offerEntity).setExperienceLevel(Mockito.any());
        doNothing().when(offerEntity).setOfferId(Mockito.<Long>any());
        doNothing().when(offerEntity).setOfferSkills(Mockito.any());
        doNothing().when(offerEntity).setOfferUuid(Mockito.any());
        doNothing().when(offerEntity).setOtherSkills(Mockito.any());
        doNothing().when(offerEntity).setRemoteWork(Mockito.<Integer>any());
        doNothing().when(offerEntity).setSalaryMax(Mockito.any());
        doNothing().when(offerEntity).setSalaryMin(Mockito.any());
        doNothing().when(offerEntity).setStatus(Mockito.any());
        doNothing().when(offerEntity).setTitle(Mockito.any());
        doNothing().when(offerEntity).setYearsOfExperience(Mockito.<Integer>any());
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
        BigDecimal salaryMin = BigDecimal.valueOf(1L);
        offerEntity.setSalaryMin(salaryMin);
        offerEntity.setStatus("Status");
        offerEntity.setTitle("Dr");
        offerEntity.setYearsOfExperience(1);
        Offer actualMapFromEntityResult = offerEntityMapperImpl.mapFromEntity(offerEntity);
        assertEquals("Benefits", actualMapFromEntityResult.getBenefits());
        assertEquals(1, actualMapFromEntityResult.getYearsOfExperience().intValue());
        assertEquals("Dr", actualMapFromEntityResult.getTitle());
        assertEquals("Status", actualMapFromEntityResult.getStatus());
        BigDecimal expectedSalaryMin = BigDecimal.ONE;
        BigDecimal salaryMin2 = actualMapFromEntityResult.getSalaryMin();
        assertSame(expectedSalaryMin, salaryMin2);
        assertSame(salaryMin2, actualMapFromEntityResult.getSalaryMax());
        assertEquals("The characteristics of someone or something", actualMapFromEntityResult.getDescription());
        assertEquals(2, actualMapFromEntityResult.getOfferSkills().size());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", actualMapFromEntityResult.getOfferUuid());
        assertEquals("Other Skills", actualMapFromEntityResult.getOtherSkills());
        assertEquals(1, actualMapFromEntityResult.getRemoteWork().intValue());
        assertEquals("Z", actualMapFromEntityResult.getCreatedAt().getOffset().toString());
        assertEquals("Experience Level", actualMapFromEntityResult.getExperienceLevel());
        assertEquals(1L, actualMapFromEntityResult.getOfferId().longValue());
        Employer employerId4 = actualMapFromEntityResult.getEmployerId();
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", employerId4.getEmployerUuid());
        assertEquals(1L, employerId4.getEmployerId().longValue());
        assertEquals("jane.doe@example.org", employerId4.getEmailContact());
        assertEquals("The characteristics of someone or something", employerId4.getDescription());
        assertEquals("Z", employerId4.getCreatedAt().getOffset().toString());
        assertEquals("Company Name", employerId4.getCompanyName());
        assertNull(employerId4.getCityId());
        assertEquals(10, employerId4.getAmountOfAvailableOffers().intValue());
        assertEquals("Website", employerId4.getWebsite());
        City cityId8 = actualMapFromEntityResult.getCityId();
        assertEquals("Oxford", cityId8.getCityName());
        assertEquals("1", salaryMin2.toString());
        assertEquals(10, employerId4.getNumberOfEmployees().intValue());
        assertEquals("6625550144", employerId4.getPhoneNumber());
        assertEquals(1L, cityId8.getCityId().longValue());
        assertEquals("foo.txt", employerId4.getLogoFilename());
        verify(offerEntity).getRemoteWork();
        verify(offerEntity).getYearsOfExperience();
        verify(offerEntity).getOfferId();
        verify(offerEntity).getBenefits();
        verify(offerEntity).getDescription();
        verify(offerEntity).getExperienceLevel();
        verify(offerEntity).getOfferUuid();
        verify(offerEntity).getOtherSkills();
        verify(offerEntity).getStatus();
        verify(offerEntity).getTitle();
        verify(offerEntity).getSalaryMax();
        verify(offerEntity).getSalaryMin();
        verify(offerEntity).getCreatedAt();
        verify(offerEntity).getOfferSkills();
        verify(offerEntity).getCityId();
        verify(offerEntity).getEmployerId();
        verify(offerEntity).setBenefits(Mockito.any());
        verify(offerEntity).setCityId(Mockito.any());
        verify(offerEntity).setCreatedAt(Mockito.any());
        verify(offerEntity).setDescription(Mockito.any());
        verify(offerEntity).setEmployerId(Mockito.any());
        verify(offerEntity).setExperienceLevel(Mockito.any());
        verify(offerEntity).setOfferId(Mockito.<Long>any());
        verify(offerEntity).setOfferSkills(Mockito.any());
        verify(offerEntity).setOfferUuid(Mockito.any());
        verify(offerEntity).setOtherSkills(Mockito.any());
        verify(offerEntity).setRemoteWork(Mockito.<Integer>any());
        verify(offerEntity).setSalaryMax(Mockito.any());
        verify(offerEntity).setSalaryMin(Mockito.any());
        verify(offerEntity).setStatus(Mockito.any());
        verify(offerEntity).setTitle(Mockito.any());
        verify(offerEntity).setYearsOfExperience(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link OfferEntityMapperImpl#mapFromEntity(OfferEntity)}
     */
    @Test
    void testMapFromEntity5() {
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
        OfferEntity offerEntity = mock(OfferEntity.class);
        when(offerEntity.getRemoteWork()).thenReturn(1);
        when(offerEntity.getYearsOfExperience()).thenReturn(1);
        when(offerEntity.getOfferId()).thenReturn(1L);
        when(offerEntity.getBenefits()).thenReturn("Benefits");
        when(offerEntity.getDescription()).thenReturn("The characteristics of someone or something");
        when(offerEntity.getExperienceLevel()).thenReturn("Experience Level");
        when(offerEntity.getOfferUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(offerEntity.getOtherSkills()).thenReturn("Other Skills");
        when(offerEntity.getStatus()).thenReturn("Status");
        when(offerEntity.getTitle()).thenReturn("Dr");
        when(offerEntity.getSalaryMax()).thenReturn(BigDecimal.valueOf(1L));
        when(offerEntity.getSalaryMin()).thenReturn(BigDecimal.valueOf(1L));
        when(offerEntity.getCreatedAt())
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        when(offerEntity.getOfferSkills()).thenReturn(new HashSet<>());
        when(offerEntity.getCityId()).thenReturn(cityEntity);
        when(offerEntity.getEmployerId()).thenReturn(employerEntity);
        doNothing().when(offerEntity).setBenefits(Mockito.any());
        doNothing().when(offerEntity).setCityId(Mockito.any());
        doNothing().when(offerEntity).setCreatedAt(Mockito.any());
        doNothing().when(offerEntity).setDescription(Mockito.any());
        doNothing().when(offerEntity).setEmployerId(Mockito.any());
        doNothing().when(offerEntity).setExperienceLevel(Mockito.any());
        doNothing().when(offerEntity).setOfferId(Mockito.<Long>any());
        doNothing().when(offerEntity).setOfferSkills(Mockito.any());
        doNothing().when(offerEntity).setOfferUuid(Mockito.any());
        doNothing().when(offerEntity).setOtherSkills(Mockito.any());
        doNothing().when(offerEntity).setRemoteWork(Mockito.<Integer>any());
        doNothing().when(offerEntity).setSalaryMax(Mockito.any());
        doNothing().when(offerEntity).setSalaryMin(Mockito.any());
        doNothing().when(offerEntity).setStatus(Mockito.any());
        doNothing().when(offerEntity).setTitle(Mockito.any());
        doNothing().when(offerEntity).setYearsOfExperience(Mockito.<Integer>any());
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
        BigDecimal salaryMin = BigDecimal.valueOf(1L);
        offerEntity.setSalaryMin(salaryMin);
        offerEntity.setStatus("Status");
        offerEntity.setTitle("Dr");
        offerEntity.setYearsOfExperience(1);
        Offer actualMapFromEntityResult = offerEntityMapperImpl.mapFromEntity(offerEntity);
        assertEquals("Benefits", actualMapFromEntityResult.getBenefits());
        assertEquals(1, actualMapFromEntityResult.getYearsOfExperience().intValue());
        assertEquals("Dr", actualMapFromEntityResult.getTitle());
        assertEquals("Status", actualMapFromEntityResult.getStatus());
        BigDecimal expectedSalaryMin = BigDecimal.ONE;
        BigDecimal salaryMin2 = actualMapFromEntityResult.getSalaryMin();
        assertSame(expectedSalaryMin, salaryMin2);
        assertSame(salaryMin2, actualMapFromEntityResult.getSalaryMax());
        assertEquals("The characteristics of someone or something", actualMapFromEntityResult.getDescription());
        assertTrue(actualMapFromEntityResult.getOfferSkills().isEmpty());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", actualMapFromEntityResult.getOfferUuid());
        assertEquals("Other Skills", actualMapFromEntityResult.getOtherSkills());
        assertEquals(1, actualMapFromEntityResult.getRemoteWork().intValue());
        assertEquals("Z", actualMapFromEntityResult.getCreatedAt().getOffset().toString());
        assertEquals("Experience Level", actualMapFromEntityResult.getExperienceLevel());
        assertEquals(1L, actualMapFromEntityResult.getOfferId().longValue());
        Employer employerId2 = actualMapFromEntityResult.getEmployerId();
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", employerId2.getEmployerUuid());
        assertEquals(1L, employerId2.getEmployerId().longValue());
        assertEquals("jane.doe@example.org", employerId2.getEmailContact());
        assertEquals("The characteristics of someone or something", employerId2.getDescription());
        assertEquals("Z", employerId2.getCreatedAt().getOffset().toString());
        assertEquals("Company Name", employerId2.getCompanyName());
        assertNull(employerId2.getCityId());
        assertEquals(10, employerId2.getAmountOfAvailableOffers().intValue());
        assertEquals("Website", employerId2.getWebsite());
        City cityId4 = actualMapFromEntityResult.getCityId();
        assertEquals("Oxford", cityId4.getCityName());
        assertEquals("1", salaryMin2.toString());
        assertEquals(10, employerId2.getNumberOfEmployees().intValue());
        assertEquals("6625550144", employerId2.getPhoneNumber());
        assertEquals(1L, cityId4.getCityId().longValue());
        assertEquals("foo.txt", employerId2.getLogoFilename());
        verify(offerEntity).getRemoteWork();
        verify(offerEntity).getYearsOfExperience();
        verify(offerEntity).getOfferId();
        verify(offerEntity).getBenefits();
        verify(offerEntity).getDescription();
        verify(offerEntity).getExperienceLevel();
        verify(offerEntity).getOfferUuid();
        verify(offerEntity).getOtherSkills();
        verify(offerEntity).getStatus();
        verify(offerEntity).getTitle();
        verify(offerEntity).getSalaryMax();
        verify(offerEntity).getSalaryMin();
        verify(offerEntity).getCreatedAt();
        verify(offerEntity).getOfferSkills();
        verify(offerEntity).getCityId();
        verify(offerEntity).getEmployerId();
        verify(offerEntity).setBenefits(Mockito.any());
        verify(offerEntity).setCityId(Mockito.any());
        verify(offerEntity).setCreatedAt(Mockito.any());
        verify(offerEntity).setDescription(Mockito.any());
        verify(offerEntity).setEmployerId(Mockito.any());
        verify(offerEntity).setExperienceLevel(Mockito.any());
        verify(offerEntity).setOfferId(Mockito.<Long>any());
        verify(offerEntity).setOfferSkills(Mockito.any());
        verify(offerEntity).setOfferUuid(Mockito.any());
        verify(offerEntity).setOtherSkills(Mockito.any());
        verify(offerEntity).setRemoteWork(Mockito.<Integer>any());
        verify(offerEntity).setSalaryMax(Mockito.any());
        verify(offerEntity).setSalaryMin(Mockito.any());
        verify(offerEntity).setStatus(Mockito.any());
        verify(offerEntity).setTitle(Mockito.any());
        verify(offerEntity).setYearsOfExperience(Mockito.<Integer>any());
        verify(cityEntity).getCityId();
        verify(cityEntity).getCityName();
        verify(cityEntity).setCandidateResidenceCities(Mockito.any());
        verify(cityEntity).setCityId(Mockito.<Long>any());
        verify(cityEntity).setCityName(Mockito.any());
        verify(cityEntity).setEmployerCities(Mockito.any());
        verify(cityEntity).setOfferCities(Mockito.any());
    }

    /**
     * Method under test: {@link OfferEntityMapperImpl#mapFromEntity(OfferEntity)}
     */
    @Test
    void testMapFromEntity6() {
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
        doNothing().when(employerEntity).setCityId(Mockito.any());
        doNothing().when(employerEntity).setCompanyName(Mockito.any());
        doNothing().when(employerEntity).setCreatedAt(Mockito.any());
        doNothing().when(employerEntity).setDescription(Mockito.any());
        doNothing().when(employerEntity).setEmailContact(Mockito.any());
        doNothing().when(employerEntity).setEmployerId(Mockito.<Long>any());
        doNothing().when(employerEntity).setEmployerUuid(Mockito.any());
        doNothing().when(employerEntity).setLogoFilename(Mockito.any());
        doNothing().when(employerEntity).setNumberOfEmployees(Mockito.<Integer>any());
        doNothing().when(employerEntity).setPhoneNumber(Mockito.any());
        doNothing().when(employerEntity).setWebsite(Mockito.any());
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
        OfferEntity offerEntity = mock(OfferEntity.class);
        when(offerEntity.getRemoteWork()).thenReturn(1);
        when(offerEntity.getYearsOfExperience()).thenReturn(1);
        when(offerEntity.getOfferId()).thenReturn(1L);
        when(offerEntity.getBenefits()).thenReturn("Benefits");
        when(offerEntity.getDescription()).thenReturn("The characteristics of someone or something");
        when(offerEntity.getExperienceLevel()).thenReturn("Experience Level");
        when(offerEntity.getOfferUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(offerEntity.getOtherSkills()).thenReturn("Other Skills");
        when(offerEntity.getStatus()).thenReturn("Status");
        when(offerEntity.getTitle()).thenReturn("Dr");
        when(offerEntity.getSalaryMax()).thenReturn(BigDecimal.valueOf(1L));
        when(offerEntity.getSalaryMin()).thenReturn(BigDecimal.valueOf(1L));
        when(offerEntity.getCreatedAt())
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        when(offerEntity.getOfferSkills()).thenReturn(new HashSet<>());
        when(offerEntity.getCityId()).thenReturn(cityEntity);
        when(offerEntity.getEmployerId()).thenReturn(employerEntity);
        doNothing().when(offerEntity).setBenefits(Mockito.any());
        doNothing().when(offerEntity).setCityId(Mockito.any());
        doNothing().when(offerEntity).setCreatedAt(Mockito.any());
        doNothing().when(offerEntity).setDescription(Mockito.any());
        doNothing().when(offerEntity).setEmployerId(Mockito.any());
        doNothing().when(offerEntity).setExperienceLevel(Mockito.any());
        doNothing().when(offerEntity).setOfferId(Mockito.<Long>any());
        doNothing().when(offerEntity).setOfferSkills(Mockito.any());
        doNothing().when(offerEntity).setOfferUuid(Mockito.any());
        doNothing().when(offerEntity).setOtherSkills(Mockito.any());
        doNothing().when(offerEntity).setRemoteWork(Mockito.<Integer>any());
        doNothing().when(offerEntity).setSalaryMax(Mockito.any());
        doNothing().when(offerEntity).setSalaryMin(Mockito.any());
        doNothing().when(offerEntity).setStatus(Mockito.any());
        doNothing().when(offerEntity).setTitle(Mockito.any());
        doNothing().when(offerEntity).setYearsOfExperience(Mockito.<Integer>any());
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
        BigDecimal salaryMin = BigDecimal.valueOf(1L);
        offerEntity.setSalaryMin(salaryMin);
        offerEntity.setStatus("Status");
        offerEntity.setTitle("Dr");
        offerEntity.setYearsOfExperience(1);
        Offer actualMapFromEntityResult = offerEntityMapperImpl.mapFromEntity(offerEntity);
        assertEquals("Benefits", actualMapFromEntityResult.getBenefits());
        assertEquals(1, actualMapFromEntityResult.getYearsOfExperience().intValue());
        assertEquals("Dr", actualMapFromEntityResult.getTitle());
        assertEquals("Status", actualMapFromEntityResult.getStatus());
        BigDecimal expectedSalaryMin = BigDecimal.ONE;
        BigDecimal salaryMin2 = actualMapFromEntityResult.getSalaryMin();
        assertSame(expectedSalaryMin, salaryMin2);
        assertSame(salaryMin2, actualMapFromEntityResult.getSalaryMax());
        assertEquals("The characteristics of someone or something", actualMapFromEntityResult.getDescription());
        assertTrue(actualMapFromEntityResult.getOfferSkills().isEmpty());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", actualMapFromEntityResult.getOfferUuid());
        assertEquals("Other Skills", actualMapFromEntityResult.getOtherSkills());
        assertEquals(1, actualMapFromEntityResult.getRemoteWork().intValue());
        assertEquals("Z", actualMapFromEntityResult.getCreatedAt().getOffset().toString());
        assertEquals("Experience Level", actualMapFromEntityResult.getExperienceLevel());
        assertEquals(1L, actualMapFromEntityResult.getOfferId().longValue());
        Employer employerId2 = actualMapFromEntityResult.getEmployerId();
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", employerId2.getEmployerUuid());
        assertEquals(1L, employerId2.getEmployerId().longValue());
        assertEquals("jane.doe@example.org", employerId2.getEmailContact());
        assertEquals("The characteristics of someone or something", employerId2.getDescription());
        assertEquals("Z", employerId2.getCreatedAt().getOffset().toString());
        assertEquals("Company Name", employerId2.getCompanyName());
        assertNull(employerId2.getCityId());
        assertEquals(10, employerId2.getAmountOfAvailableOffers().intValue());
        assertEquals("Website", employerId2.getWebsite());
        City cityId4 = actualMapFromEntityResult.getCityId();
        assertEquals("Oxford", cityId4.getCityName());
        assertEquals("1", salaryMin2.toString());
        assertEquals(10, employerId2.getNumberOfEmployees().intValue());
        assertEquals("6625550144", employerId2.getPhoneNumber());
        assertEquals(1L, cityId4.getCityId().longValue());
        assertEquals("foo.txt", employerId2.getLogoFilename());
        verify(offerEntity).getRemoteWork();
        verify(offerEntity).getYearsOfExperience();
        verify(offerEntity).getOfferId();
        verify(offerEntity).getBenefits();
        verify(offerEntity).getDescription();
        verify(offerEntity).getExperienceLevel();
        verify(offerEntity).getOfferUuid();
        verify(offerEntity).getOtherSkills();
        verify(offerEntity).getStatus();
        verify(offerEntity).getTitle();
        verify(offerEntity).getSalaryMax();
        verify(offerEntity).getSalaryMin();
        verify(offerEntity).getCreatedAt();
        verify(offerEntity).getOfferSkills();
        verify(offerEntity).getCityId();
        verify(offerEntity).getEmployerId();
        verify(offerEntity).setBenefits(Mockito.any());
        verify(offerEntity).setCityId(Mockito.any());
        verify(offerEntity).setCreatedAt(Mockito.any());
        verify(offerEntity).setDescription(Mockito.any());
        verify(offerEntity).setEmployerId(Mockito.any());
        verify(offerEntity).setExperienceLevel(Mockito.any());
        verify(offerEntity).setOfferId(Mockito.<Long>any());
        verify(offerEntity).setOfferSkills(Mockito.any());
        verify(offerEntity).setOfferUuid(Mockito.any());
        verify(offerEntity).setOtherSkills(Mockito.any());
        verify(offerEntity).setRemoteWork(Mockito.<Integer>any());
        verify(offerEntity).setSalaryMax(Mockito.any());
        verify(offerEntity).setSalaryMin(Mockito.any());
        verify(offerEntity).setStatus(Mockito.any());
        verify(offerEntity).setTitle(Mockito.any());
        verify(offerEntity).setYearsOfExperience(Mockito.<Integer>any());
        verify(cityEntity).getCityId();
        verify(cityEntity).getCityName();
        verify(cityEntity).setCandidateResidenceCities(Mockito.any());
        verify(cityEntity).setCityId(Mockito.<Long>any());
        verify(cityEntity).setCityName(Mockito.any());
        verify(cityEntity).setEmployerCities(Mockito.any());
        verify(cityEntity).setOfferCities(Mockito.any());
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
        verify(employerEntity).setCityId(Mockito.any());
        verify(employerEntity).setCompanyName(Mockito.any());
        verify(employerEntity).setCreatedAt(Mockito.any());
        verify(employerEntity).setDescription(Mockito.any());
        verify(employerEntity).setEmailContact(Mockito.any());
        verify(employerEntity).setEmployerId(Mockito.<Long>any());
        verify(employerEntity).setEmployerUuid(Mockito.any());
        verify(employerEntity).setLogoFilename(Mockito.any());
        verify(employerEntity).setNumberOfEmployees(Mockito.<Integer>any());
        verify(employerEntity).setPhoneNumber(Mockito.any());
        verify(employerEntity).setWebsite(Mockito.any());
    }

    /**
     * Method under test: {@link OfferEntityMapperImpl#mapFromEntity(OfferSkillEntity)}
     */
    @Test
    void testMapFromEntity7() {
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

        OfferEntity offerId = new OfferEntity();
        offerId.setBenefits("Benefits");
        offerId.setCityId(cityId);
        offerId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        offerId.setDescription("The characteristics of someone or something");
        offerId.setEmployerId(employerId);
        offerId.setExperienceLevel("Experience Level");
        offerId.setOfferId(1L);
        offerId.setOfferSkills(new HashSet<>());
        offerId.setOfferUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        offerId.setOtherSkills("Other Skills");
        offerId.setRemoteWork(1);
        offerId.setSalaryMax(BigDecimal.valueOf(1L));
        offerId.setSalaryMin(BigDecimal.valueOf(1L));
        offerId.setStatus("Status");
        offerId.setTitle("Dr");
        offerId.setYearsOfExperience(1);

        SkillEntity skillId = new SkillEntity();
        skillId.setCandidateSkills(new HashSet<>());
        skillId.setOfferSkills(new HashSet<>());
        skillId.setSkillId(1L);
        skillId.setSkillName("Skill Name");

        OfferSkillEntity entity = new OfferSkillEntity();
        entity.setOfferId(offerId);
        entity.setOfferSkillId(1L);
        entity.setSkillId(skillId);
        OfferSkill actualMapFromEntityResult = offerEntityMapperImpl.mapFromEntity(entity);
        assertNull(actualMapFromEntityResult.getOfferId());
        assertEquals(1L, actualMapFromEntityResult.getOfferSkillId().longValue());
        Skill skillId2 = actualMapFromEntityResult.getSkillId();
        assertEquals("Skill Name", skillId2.getSkillName());
        assertEquals(1L, skillId2.getSkillId().longValue());
    }

    /**
     * Method under test: {@link OfferEntityMapperImpl#mapFromEntity(OfferSkillEntity)}
     */
    @Test
    void testMapFromEntity8() {
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

        OfferEntity offerId = new OfferEntity();
        offerId.setBenefits("Benefits");
        offerId.setCityId(cityId);
        offerId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        offerId.setDescription("The characteristics of someone or something");
        offerId.setEmployerId(employerId);
        offerId.setExperienceLevel("Experience Level");
        offerId.setOfferId(1L);
        offerId.setOfferSkills(new HashSet<>());
        offerId.setOfferUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        offerId.setOtherSkills("Other Skills");
        offerId.setRemoteWork(1);
        offerId.setSalaryMax(BigDecimal.valueOf(1L));
        offerId.setSalaryMin(BigDecimal.valueOf(1L));
        offerId.setStatus("Status");
        offerId.setTitle("Dr");
        offerId.setYearsOfExperience(1);

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
        OfferSkillEntity entity = mock(OfferSkillEntity.class);
        when(entity.getOfferSkillId()).thenReturn(1L);
        when(entity.getSkillId()).thenReturn(skillEntity);
        doNothing().when(entity).setOfferId(Mockito.any());
        doNothing().when(entity).setOfferSkillId(Mockito.<Long>any());
        doNothing().when(entity).setSkillId(Mockito.any());
        entity.setOfferId(offerId);
        entity.setOfferSkillId(1L);
        entity.setSkillId(skillId);
        OfferSkill actualMapFromEntityResult = offerEntityMapperImpl.mapFromEntity(entity);
        assertNull(actualMapFromEntityResult.getOfferId());
        assertEquals(1L, actualMapFromEntityResult.getOfferSkillId().longValue());
        Skill skillId2 = actualMapFromEntityResult.getSkillId();
        assertEquals("Skill Name", skillId2.getSkillName());
        assertEquals(1L, skillId2.getSkillId().longValue());
        verify(entity).getOfferSkillId();
        verify(entity).getSkillId();
        verify(entity).setOfferId(Mockito.any());
        verify(entity).setOfferSkillId(Mockito.<Long>any());
        verify(entity).setSkillId(Mockito.any());
    }

    /**
     * Method under test: {@link OfferEntityMapperImpl#mapFromEntity(OfferSkillEntity)}
     */
    @Test
    void testMapFromEntity9() {
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

        OfferEntity offerId = new OfferEntity();
        offerId.setBenefits("Benefits");
        offerId.setCityId(cityId);
        offerId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        offerId.setDescription("The characteristics of someone or something");
        offerId.setEmployerId(employerId);
        offerId.setExperienceLevel("Experience Level");
        offerId.setOfferId(1L);
        offerId.setOfferSkills(new HashSet<>());
        offerId.setOfferUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        offerId.setOtherSkills("Other Skills");
        offerId.setRemoteWork(1);
        offerId.setSalaryMax(BigDecimal.valueOf(1L));
        offerId.setSalaryMin(BigDecimal.valueOf(1L));
        offerId.setStatus("Status");
        offerId.setTitle("Dr");
        offerId.setYearsOfExperience(1);

        SkillEntity skillId = new SkillEntity();
        skillId.setCandidateSkills(new HashSet<>());
        skillId.setOfferSkills(new HashSet<>());
        skillId.setSkillId(1L);
        skillId.setSkillName("Skill Name");
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
        OfferSkillEntity entity = mock(OfferSkillEntity.class);
        when(entity.getOfferSkillId()).thenReturn(1L);
        when(entity.getSkillId()).thenReturn(skillEntity);
        doNothing().when(entity).setOfferId(Mockito.any());
        doNothing().when(entity).setOfferSkillId(Mockito.<Long>any());
        doNothing().when(entity).setSkillId(Mockito.any());
        entity.setOfferId(offerId);
        entity.setOfferSkillId(1L);
        entity.setSkillId(skillId);
        OfferSkill actualMapFromEntityResult = offerEntityMapperImpl.mapFromEntity(entity);
        assertNull(actualMapFromEntityResult.getOfferId());
        assertEquals(1L, actualMapFromEntityResult.getOfferSkillId().longValue());
        Skill skillId2 = actualMapFromEntityResult.getSkillId();
        assertEquals("Skill Name", skillId2.getSkillName());
        assertEquals(1L, skillId2.getSkillId().longValue());
        verify(entity).getOfferSkillId();
        verify(entity).getSkillId();
        verify(entity).setOfferId(Mockito.any());
        verify(entity).setOfferSkillId(Mockito.<Long>any());
        verify(entity).setSkillId(Mockito.any());
        verify(skillEntity).getSkillId();
        verify(skillEntity).getSkillName();
        verify(skillEntity).setCandidateSkills(Mockito.any());
        verify(skillEntity).setOfferSkills(Mockito.any());
        verify(skillEntity).setSkillId(Mockito.<Long>any());
        verify(skillEntity).setSkillName(Mockito.any());
    }

    /**
     * Method under test: {@link OfferEntityMapperImpl#mapToEntity(Offer)}
     */
    @Test
    void testMapToEntity() {
        assertNull(offerEntityMapperImpl.mapToEntity(null));
    }

    /**
     * Method under test: {@link OfferEntityMapperImpl#employerEntityToEmployer(EmployerEntity)}
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
        Employer actualEmployerEntityToEmployerResult = offerEntityMapperImpl.employerEntityToEmployer(employerEntity);
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
     * Method under test: {@link OfferEntityMapperImpl#employerEntityToEmployer(EmployerEntity)}
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
        doNothing().when(employerEntity).setCityId(Mockito.any());
        doNothing().when(employerEntity).setCompanyName(Mockito.any());
        doNothing().when(employerEntity).setCreatedAt(Mockito.any());
        doNothing().when(employerEntity).setDescription(Mockito.any());
        doNothing().when(employerEntity).setEmailContact(Mockito.any());
        doNothing().when(employerEntity).setEmployerId(Mockito.<Long>any());
        doNothing().when(employerEntity).setEmployerUuid(Mockito.any());
        doNothing().when(employerEntity).setLogoFilename(Mockito.any());
        doNothing().when(employerEntity).setNumberOfEmployees(Mockito.<Integer>any());
        doNothing().when(employerEntity).setPhoneNumber(Mockito.any());
        doNothing().when(employerEntity).setWebsite(Mockito.any());
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
        Employer actualEmployerEntityToEmployerResult = offerEntityMapperImpl.employerEntityToEmployer(employerEntity);
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
        verify(employerEntity).setCityId(Mockito.any());
        verify(employerEntity).setCompanyName(Mockito.any());
        verify(employerEntity).setCreatedAt(Mockito.any());
        verify(employerEntity).setDescription(Mockito.any());
        verify(employerEntity).setEmailContact(Mockito.any());
        verify(employerEntity).setEmployerId(Mockito.<Long>any());
        verify(employerEntity).setEmployerUuid(Mockito.any());
        verify(employerEntity).setLogoFilename(Mockito.any());
        verify(employerEntity).setNumberOfEmployees(Mockito.<Integer>any());
        verify(employerEntity).setPhoneNumber(Mockito.any());
        verify(employerEntity).setWebsite(Mockito.any());
    }

    /**
     * Method under test: {@link OfferEntityMapperImpl#cityEntityToCity(CityEntity)}
     */
    @Test
    void testCityEntityToCity() {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());
        City actualCityEntityToCityResult = offerEntityMapperImpl.cityEntityToCity(cityEntity);
        assertEquals(1L, actualCityEntityToCityResult.getCityId().longValue());
        assertEquals("Oxford", actualCityEntityToCityResult.getCityName());
    }

    /**
     * Method under test: {@link OfferEntityMapperImpl#cityEntityToCity(CityEntity)}
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
        City actualCityEntityToCityResult = offerEntityMapperImpl.cityEntityToCity(cityEntity);
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
     * Method under test: {@link OfferEntityMapperImpl#skillEntityToSkill(SkillEntity)}
     */
    @Test
    void testSkillEntityToSkill() {
        SkillEntity skillEntity = new SkillEntity();
        skillEntity.setCandidateSkills(new HashSet<>());
        skillEntity.setOfferSkills(new HashSet<>());
        skillEntity.setSkillId(1L);
        skillEntity.setSkillName("Skill Name");
        Skill actualSkillEntityToSkillResult = offerEntityMapperImpl.skillEntityToSkill(skillEntity);
        assertEquals(1L, actualSkillEntityToSkillResult.getSkillId().longValue());
        assertEquals("Skill Name", actualSkillEntityToSkillResult.getSkillName());
    }

    /**
     * Method under test: {@link OfferEntityMapperImpl#skillEntityToSkill(SkillEntity)}
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
        Skill actualSkillEntityToSkillResult = offerEntityMapperImpl.skillEntityToSkill(skillEntity);
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
     * Method under test: {@link OfferEntityMapperImpl#cityToCityEntity(City)}
     */
    @Test
    void testCityToCityEntity() {
        assertNull(offerEntityMapperImpl.cityToCityEntity(null));
    }

    /**
     * Method under test: {@link OfferEntityMapperImpl#employerToEmployerEntity(Employer)}
     */
    @Test
    void testEmployerToEmployerEntity() {
        assertNull(offerEntityMapperImpl.employerToEmployerEntity(null));
    }

    /**
     * Method under test: {@link OfferEntityMapperImpl#skillToSkillEntity(Skill)}
     */
    @Test
    void testSkillToSkillEntity() {
        assertNull(offerEntityMapperImpl.skillToSkillEntity(null));
    }

    /**
     * Method under test: {@link OfferEntityMapperImpl#offerSkillToOfferSkillEntity(OfferSkill)}
     */
    @Test
    void testOfferSkillToOfferSkillEntity() {
        assertNull(offerEntityMapperImpl.offerSkillToOfferSkillEntity(null));
    }

    /**
     * Method under test: {@link OfferEntityMapperImpl#offerSkillSetToOfferSkillEntitySet(Set)}
     */
    @Test
    void testOfferSkillSetToOfferSkillEntitySet() {
        assertTrue(offerEntityMapperImpl.offerSkillSetToOfferSkillEntitySet(new HashSet<>()).isEmpty());
    }
}

