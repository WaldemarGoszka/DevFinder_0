package pl.devfinder.infrastructure.database.repository.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.domain.City;
import pl.devfinder.infrastructure.database.entity.CityEntity;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CityEntityMapperImpl.class})
@ExtendWith(SpringExtension.class)
class CityEntityMapperImplDiffBlueTest {
    @Autowired
    private CityEntityMapperImpl cityEntityMapperImpl;

    /**
     * Method under test: {@link CityEntityMapperImpl#mapFromEntity(CityEntity)}
     */
    @Test
    void testMapFromEntity() {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());
        City actualMapFromEntityResult = cityEntityMapperImpl.mapFromEntity(cityEntity);
        assertEquals(1L, actualMapFromEntityResult.getCityId().longValue());
        assertEquals("Oxford", actualMapFromEntityResult.getCityName());
    }

    /**
     * Method under test: {@link CityEntityMapperImpl#mapFromEntity(CityEntity)}
     */
    @Test
    void testMapFromEntity2() {
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
        City actualMapFromEntityResult = cityEntityMapperImpl.mapFromEntity(cityEntity);
        assertEquals(1L, actualMapFromEntityResult.getCityId().longValue());
        assertEquals("Oxford", actualMapFromEntityResult.getCityName());
        verify(cityEntity).getCityId();
        verify(cityEntity).getCityName();
        verify(cityEntity).setCandidateResidenceCities(Mockito.any());
        verify(cityEntity).setCityId(Mockito.<Long>any());
        verify(cityEntity).setCityName(Mockito.any());
        verify(cityEntity).setEmployerCities(Mockito.any());
        verify(cityEntity).setOfferCities(Mockito.any());
    }

    /**
     * Method under test: {@link CityEntityMapperImpl#mapToEntity(City)}
     */
    @Test
    void testMapToEntity() {
        assertNull(cityEntityMapperImpl.mapToEntity(null));
    }
}

