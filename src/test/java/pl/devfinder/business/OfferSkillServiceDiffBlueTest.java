package pl.devfinder.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.business.dao.OfferSkillDAO;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ContextConfiguration(classes = {OfferSkillService.class})
@ExtendWith(SpringExtension.class)
class OfferSkillServiceDiffBlueTest {
    @MockBean
    private OfferSkillDAO offerSkillDAO;

    @Autowired
    private OfferSkillService offerSkillService;

    /**
     * Method under test: {@link OfferSkillService#saveAll(Set)}
     */
    @Test
    void testSaveAll() {
        doNothing().when(offerSkillDAO).saveAll(Mockito.any());
        offerSkillService.saveAll(new HashSet<>());
        verify(offerSkillDAO).saveAll(Mockito.any());
    }


}

