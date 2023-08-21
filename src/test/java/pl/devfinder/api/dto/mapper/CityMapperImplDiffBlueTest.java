package pl.devfinder.api.dto.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.domain.City;

import static org.junit.jupiter.api.Assertions.assertNull;

@ContextConfiguration(classes = {CityMapperImpl.class})
@ExtendWith(SpringExtension.class)
class CityMapperImplDiffBlueTest {
    @Autowired
    private CityMapperImpl cityMapperImpl;

    /**
     * Method under test: {@link CityMapperImpl#map(City)}
     */
    @Test
    void testMap() {
        assertNull(cityMapperImpl.map(null));
    }
}

