package pl.devfinder.infrastructure.database.repository.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.domain.City;
import pl.devfinder.domain.Employer;
import pl.devfinder.infrastructure.database.entity.CityEntity;
import pl.devfinder.infrastructure.database.entity.EmployerEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {EmployerEntityMapperImpl.class})
@ExtendWith(SpringExtension.class)
class EmployerEntityMapperImplDiffBlueTest {
    @Autowired
    private EmployerEntityMapperImpl employerEntityMapperImpl;

    /**
     * Method under test: {@link EmployerEntityMapperImpl#mapFromEntity(EmployerEntity)}
     */
    @Test
    void testMapFromEntity() {
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
        Employer actualMapFromEntityResult = employerEntityMapperImpl.mapFromEntity(employerEntity);
        assertEquals(10, actualMapFromEntityResult.getAmountOfAvailableOffers().intValue());
        assertEquals("Website", actualMapFromEntityResult.getWebsite());
        assertEquals("6625550144", actualMapFromEntityResult.getPhoneNumber());
        assertEquals(10, actualMapFromEntityResult.getNumberOfEmployees().intValue());
        assertEquals("foo.txt", actualMapFromEntityResult.getLogoFilename());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", actualMapFromEntityResult.getEmployerUuid());
        assertEquals(1L, actualMapFromEntityResult.getEmployerId().longValue());
        assertEquals("jane.doe@example.org", actualMapFromEntityResult.getEmailContact());
        assertEquals("The characteristics of someone or something", actualMapFromEntityResult.getDescription());
        assertEquals("Z", actualMapFromEntityResult.getCreatedAt().getOffset().toString());
        assertEquals("Company Name", actualMapFromEntityResult.getCompanyName());
        City cityId2 = actualMapFromEntityResult.getCityId();
        assertEquals(1L, cityId2.getCityId().longValue());
        assertEquals("Oxford", cityId2.getCityName());
    }

    /**
     * Method under test: {@link EmployerEntityMapperImpl#mapFromEntity(EmployerEntity)}
     */
    @Test
    void testMapFromEntity2() {
        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        CityEntity cityEntity = new CityEntity();
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());
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
        when(employerEntity.getCityId()).thenReturn(cityEntity);
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
        Employer actualMapFromEntityResult = employerEntityMapperImpl.mapFromEntity(employerEntity);
        assertEquals(10, actualMapFromEntityResult.getAmountOfAvailableOffers().intValue());
        assertEquals("Website", actualMapFromEntityResult.getWebsite());
        assertEquals("6625550144", actualMapFromEntityResult.getPhoneNumber());
        assertEquals(10, actualMapFromEntityResult.getNumberOfEmployees().intValue());
        assertEquals("foo.txt", actualMapFromEntityResult.getLogoFilename());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", actualMapFromEntityResult.getEmployerUuid());
        assertEquals(1L, actualMapFromEntityResult.getEmployerId().longValue());
        assertEquals("jane.doe@example.org", actualMapFromEntityResult.getEmailContact());
        assertEquals("The characteristics of someone or something", actualMapFromEntityResult.getDescription());
        assertEquals("Z", actualMapFromEntityResult.getCreatedAt().getOffset().toString());
        assertEquals("Company Name", actualMapFromEntityResult.getCompanyName());
        City cityId2 = actualMapFromEntityResult.getCityId();
        assertEquals(1L, cityId2.getCityId().longValue());
        assertEquals("Oxford", cityId2.getCityName());
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
        verify(employerEntity).getCityId();
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
     * Method under test: {@link EmployerEntityMapperImpl#mapFromEntity(EmployerEntity)}
     */
    @Test
    void testMapFromEntity3() {
        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());
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
        when(employerEntity.getCityId()).thenReturn(cityEntity);
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
        Employer actualMapFromEntityResult = employerEntityMapperImpl.mapFromEntity(employerEntity);
        assertEquals(10, actualMapFromEntityResult.getAmountOfAvailableOffers().intValue());
        assertEquals("Website", actualMapFromEntityResult.getWebsite());
        assertEquals("6625550144", actualMapFromEntityResult.getPhoneNumber());
        assertEquals(10, actualMapFromEntityResult.getNumberOfEmployees().intValue());
        assertEquals("foo.txt", actualMapFromEntityResult.getLogoFilename());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", actualMapFromEntityResult.getEmployerUuid());
        assertEquals(1L, actualMapFromEntityResult.getEmployerId().longValue());
        assertEquals("jane.doe@example.org", actualMapFromEntityResult.getEmailContact());
        assertEquals("The characteristics of someone or something", actualMapFromEntityResult.getDescription());
        assertEquals("Z", actualMapFromEntityResult.getCreatedAt().getOffset().toString());
        assertEquals("Company Name", actualMapFromEntityResult.getCompanyName());
        City cityId2 = actualMapFromEntityResult.getCityId();
        assertEquals(1L, cityId2.getCityId().longValue());
        assertEquals("Oxford", cityId2.getCityName());
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
        verify(employerEntity).getCityId();
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
        verify(cityEntity).getCityId();
        verify(cityEntity).getCityName();
        verify(cityEntity).setCandidateResidenceCities(Mockito.any());
        verify(cityEntity).setCityId(Mockito.<Long>any());
        verify(cityEntity).setCityName(Mockito.any());
        verify(cityEntity).setEmployerCities(Mockito.any());
        verify(cityEntity).setOfferCities(Mockito.any());
    }

    /**
     * Method under test: {@link EmployerEntityMapperImpl#mapToEntity(Employer)}
     */
    @Test
    void testMapToEntity() {
        assertNull(employerEntityMapperImpl.mapToEntity(null));
    }

    /**
     * Method under test: {@link EmployerEntityMapperImpl#cityEntityToCity(CityEntity)}
     */
    @Test
    void testCityEntityToCity() {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());
        City actualCityEntityToCityResult = employerEntityMapperImpl.cityEntityToCity(cityEntity);
        assertEquals(1L, actualCityEntityToCityResult.getCityId().longValue());
        assertEquals("Oxford", actualCityEntityToCityResult.getCityName());
    }

    /**
     * Method under test: {@link EmployerEntityMapperImpl#cityEntityToCity(CityEntity)}
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
        City actualCityEntityToCityResult = employerEntityMapperImpl.cityEntityToCity(cityEntity);
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
     * Method under test: {@link EmployerEntityMapperImpl#cityToCityEntity(City)}
     */
    @Test
    void testCityToCityEntity() {
        assertNull(employerEntityMapperImpl.cityToCityEntity(null));
    }
}

