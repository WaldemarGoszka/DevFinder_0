package pl.devfinder.infrastructure.database.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.domain.City;
import pl.devfinder.infrastructure.database.entity.CityEntity;
import pl.devfinder.infrastructure.database.repository.jpa.CityJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.CityEntityMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CityRepository.class})
@ExtendWith(SpringExtension.class)
class CityRepositoryDiffBlueTest {
    @MockBean
    private CityEntityMapper cityEntityMapper;

    @MockBean
    private CityJpaRepository cityJpaRepository;

    @Autowired
    private CityRepository cityRepository;

    /**
     * Method under test: {@link CityRepository#findAll()}
     */
    @Test
    void testFindAll() {
        when(cityJpaRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(cityRepository.findAll().isEmpty());
        verify(cityJpaRepository).findAll();
    }

    /**
     * Method under test: {@link CityRepository#findByCityName(String)}
     */
    @Test
    void testFindByCityName() {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());
        Optional<CityEntity> ofResult = Optional.of(cityEntity);
        when(cityJpaRepository.findByCityName(Mockito.any())).thenReturn(ofResult);
        when(cityEntityMapper.mapFromEntity(Mockito.any())).thenReturn(null);
        assertFalse(cityRepository.findByCityName("Oxford").isPresent());
        verify(cityJpaRepository).findByCityName(Mockito.any());
        verify(cityEntityMapper).mapFromEntity(Mockito.any());
    }

    /**
     * Method under test: {@link CityRepository#saveAnfFlush(City)}
     */
    @Test
    void testSaveAnfFlush() {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());
        when(cityJpaRepository.saveAndFlush(Mockito.any())).thenReturn(cityEntity);

        CityEntity cityEntity2 = new CityEntity();
        cityEntity2.setCandidateResidenceCities(new HashSet<>());
        cityEntity2.setCityId(1L);
        cityEntity2.setCityName("Oxford");
        cityEntity2.setEmployerCities(new HashSet<>());
        cityEntity2.setOfferCities(new HashSet<>());
        when(cityEntityMapper.mapFromEntity(Mockito.any())).thenReturn(null);
        when(cityEntityMapper.mapToEntity(Mockito.any())).thenReturn(cityEntity2);
        assertNull(cityRepository.saveAnfFlush(null));
        verify(cityJpaRepository).saveAndFlush(Mockito.any());
        verify(cityEntityMapper).mapFromEntity(Mockito.any());
        verify(cityEntityMapper).mapToEntity(Mockito.any());
    }

    /**
     * Method under test: {@link CityRepository#deleteByCityName(String)}
     */
    @Test
    void testDeleteByCityName() {
        doNothing().when(cityJpaRepository).deleteByCityName(Mockito.any());
        cityRepository.deleteByCityName("Oxford");
        verify(cityJpaRepository).deleteByCityName(Mockito.any());
    }
}

