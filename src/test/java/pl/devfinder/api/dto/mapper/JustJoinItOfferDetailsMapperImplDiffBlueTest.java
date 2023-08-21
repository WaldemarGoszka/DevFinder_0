package pl.devfinder.api.dto.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.domain.Offer;

import static org.junit.jupiter.api.Assertions.assertNull;

@ContextConfiguration(classes = {OfferDetailsMapperImpl.class})
@ExtendWith(SpringExtension.class)
class JustJoinItOfferDetailsMapperImplDiffBlueTest {
    @Autowired
    private OfferDetailsMapperImpl offerDetailsMapperImpl;

    /**
     * Method under test: {@link OfferDetailsMapperImpl#map(Offer)}
     */
    @Test
    void testMap() {
        assertNull(offerDetailsMapperImpl.map(null));
    }
}

