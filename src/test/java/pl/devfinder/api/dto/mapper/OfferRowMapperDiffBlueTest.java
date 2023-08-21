package pl.devfinder.api.dto.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.business.OfferService;
import pl.devfinder.domain.Offer;

import static org.junit.jupiter.api.Assertions.assertNull;

@ContextConfiguration(classes = {OfferRowMapperImpl.class})
@ExtendWith(SpringExtension.class)
class OfferRowMapperDiffBlueTest {
    @Autowired
    private OfferRowMapper offerRowMapper;

    @MockBean
    private OfferService offerService;

    /**
     * Method under test: {@link OfferRowMapper#map(Offer)}
     */
    @Test
    void testMap() {
        assertNull(offerRowMapper.map(null));
    }
}

