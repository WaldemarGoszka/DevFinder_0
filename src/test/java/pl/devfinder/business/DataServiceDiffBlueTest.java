package pl.devfinder.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.business.dao.CandidateDAO;
import pl.devfinder.business.dao.EmployerDAO;
import pl.devfinder.business.dao.OfferDAO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {DataService.class})
@ExtendWith(SpringExtension.class)
class DataServiceDiffBlueTest {
    @MockBean
    private CandidateDAO candidateDAO;

    @Autowired
    private DataService dataService;

    @MockBean
    private EmployerDAO employerDAO;

    @MockBean
    private OfferDAO offerDAO;

    /**
     * Method under test: {@link DataService#countUsesOfCityName(String)}
     */
    @Test
    void testCountUsesOfCityName() {
        when(employerDAO.countByCityName(Mockito.any())).thenReturn(3L);
        when(offerDAO.countByCityName(Mockito.any())).thenReturn(3L);
        when(candidateDAO.countByCityName(Mockito.any())).thenReturn(3L);
        assertEquals(9L, dataService.countUsesOfCityName("Oxford"));
        verify(employerDAO).countByCityName(Mockito.any());
        verify(offerDAO).countByCityName(Mockito.any());
        verify(candidateDAO).countByCityName(Mockito.any());
    }
}

