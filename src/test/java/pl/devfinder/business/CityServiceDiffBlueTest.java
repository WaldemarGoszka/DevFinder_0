package pl.devfinder.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.business.dao.CityDAO;
import pl.devfinder.domain.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CityService.class})
@ExtendWith(SpringExtension.class)
class CityServiceDiffBlueTest {
    @MockBean
    private CityDAO cityDAO;

    @Autowired
    private CityService cityService;

    /**
     * Method under test: {@link CityService#findAll()}
     */
    @Test
    void testFindAll() {
        ArrayList<City> cityList = new ArrayList<>();
        when(cityDAO.findAll()).thenReturn(cityList);
        List<City> actualFindAllResult = cityService.findAll();
        assertSame(cityList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(cityDAO).findAll();
    }

    /**
     * Method under test: {@link CityService#findByCityName(String)}
     */
    @Test
    void testFindByCityName() {
        Optional<City> emptyResult = Optional.empty();
        when(cityDAO.findByCityName(Mockito.any())).thenReturn(emptyResult);
        Optional<City> actualFindByCityNameResult = cityService.findByCityName("Oxford");
        assertSame(emptyResult, actualFindByCityNameResult);
        assertFalse(actualFindByCityNameResult.isPresent());
        verify(cityDAO).findByCityName(Mockito.any());
    }

    /**
     * Method under test: {@link CityService#save(String)}
     */
    @Test
    void testSave() {
        when(cityDAO.saveAnfFlush(Mockito.any())).thenReturn(null);
        assertNull(cityService.save("Oxford"));
        verify(cityDAO).saveAnfFlush(Mockito.any());
    }

    /**
     * Method under test: {@link CityService#deleteByCityName(String)}
     */
    @Test
    void testDeleteByCityName() {
        doNothing().when(cityDAO).deleteByCityName(Mockito.any());
        cityService.deleteByCityName("Oxford");
        verify(cityDAO).deleteByCityName(Mockito.any());
    }

    /**
     * Method under test: {@link CityService#nonCityExist(String)}
     */
    @Test
    void testNonCityExist() {
        when(cityDAO.findByCityName(Mockito.any())).thenReturn(Optional.empty());
        assertTrue(cityService.nonCityExist("Oxford"));
        verify(cityDAO).findByCityName(Mockito.any());
    }
}

