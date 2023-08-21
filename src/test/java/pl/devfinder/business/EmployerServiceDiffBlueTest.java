package pl.devfinder.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.business.dao.EmployerDAO;
import pl.devfinder.domain.Employer;
import pl.devfinder.domain.exception.FileUploadToProfileException;
import pl.devfinder.domain.exception.NotFoundException;
import pl.devfinder.domain.search.EmployerSearchCriteria;
import pl.devfinder.infrastructure.database.repository.criteria.EmployerCriteriaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {EmployerService.class})
@ExtendWith(SpringExtension.class)
class EmployerServiceDiffBlueTest {
    @MockBean
    private CityService cityService;

    @MockBean
    private DataService dataService;

    @MockBean
    private EmployerCriteriaRepository employerCriteriaRepository;

    @MockBean
    private EmployerDAO employerDAO;

    @Autowired
    private EmployerService employerService;

    @MockBean
    private FileService fileService;

    @MockBean
    private OfferService offerService;

    @MockBean
    private OfferSkillService offerSkillService;

    /**
     * Method under test: {@link EmployerService#findById(Long)}
     */
    @Test
    void testFindById() {
        when(employerDAO.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> employerService.findById(1L));
        verify(employerDAO).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EmployerService#findById(Long)}
     */
    @Test
    void testFindById2() {
        when(employerDAO.findById(Mockito.<Long>any())).thenThrow(new FileUploadToProfileException("An error occurred"));
        assertThrows(FileUploadToProfileException.class, () -> employerService.findById(1L));
        verify(employerDAO).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EmployerService#findByCompanyName(String)}
     */
    @Test
    void testFindByCompanyName() {
        when(employerDAO.findByCompanyName(Mockito.any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> employerService.findByCompanyName("Employer Company Name"));
        verify(employerDAO).findByCompanyName(Mockito.any());
    }

    /**
     * Method under test: {@link EmployerService#findByCompanyName(String)}
     */
    @Test
    void testFindByCompanyName2() {
        when(employerDAO.findByCompanyName(Mockito.any()))
                .thenThrow(new FileUploadToProfileException("An error occurred"));
        assertThrows(FileUploadToProfileException.class,
                () -> employerService.findByCompanyName("Employer Company Name"));
        verify(employerDAO).findByCompanyName(Mockito.any());
    }

    /**
     * Method under test: {@link EmployerService#findAll()}
     */
    @Test
    void testFindAll() {
        ArrayList<Employer> employerList = new ArrayList<>();
        when(employerDAO.findAll()).thenReturn(employerList);
        List<Employer> actualFindAllResult = employerService.findAll();
        assertSame(employerList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(employerDAO).findAll();
    }

    /**
     * Method under test: {@link EmployerService#findAllByCriteria(EmployerSearchCriteria)}
     */
    @Test
    void testFindAllByCriteria() {
        PageImpl<Employer> pageImpl = new PageImpl<>(new ArrayList<>());
        when(employerCriteriaRepository.findAllByCriteria(Mockito.any())).thenReturn(pageImpl);

        EmployerSearchCriteria employerSearchCriteria = new EmployerSearchCriteria();
        employerSearchCriteria.setAmountOfAvailableOffers(10);
        employerSearchCriteria.setCity("Oxford");
        employerSearchCriteria.setCompanyName("Company Name");
        employerSearchCriteria.setJobOffersStatus(new ArrayList<>());
        employerSearchCriteria.setNumberOfEmployees(10);
        employerSearchCriteria.setPageNumber(10);
        employerSearchCriteria.setPageSize(3);
        employerSearchCriteria.setSkillsInOffers(new ArrayList<>());
        employerSearchCriteria.setSortBy("Sort By");
        employerSearchCriteria.setSortDirection(Sort.Direction.ASC);
        Page<Employer> actualFindAllByCriteriaResult = employerService.findAllByCriteria(employerSearchCriteria);
        assertSame(pageImpl, actualFindAllByCriteriaResult);
        assertTrue(actualFindAllByCriteriaResult.toList().isEmpty());
        verify(employerCriteriaRepository).findAllByCriteria(Mockito.any());
    }

    /**
     * Method under test: {@link EmployerService#findByEmployerUuid(String)}
     */
    @Test
    void testFindByEmployerUuid() {
        Optional<Employer> emptyResult = Optional.empty();
        when(employerDAO.findByEmployerUuid(Mockito.any())).thenReturn(emptyResult);
        Optional<Employer> actualFindByEmployerUuidResult = employerService
                .findByEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        assertSame(emptyResult, actualFindByEmployerUuidResult);
        assertFalse(actualFindByEmployerUuidResult.isPresent());
        verify(employerDAO).findByEmployerUuid(Mockito.any());
    }

    /**
     * Method under test: {@link EmployerService#findByEmployerUuid(String)}
     */
    @Test
    void testFindByEmployerUuid2() {
        when(employerDAO.findByEmployerUuid(Mockito.any()))
                .thenThrow(new FileUploadToProfileException("An error occurred"));
        assertThrows(FileUploadToProfileException.class,
                () -> employerService.findByEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210"));
        verify(employerDAO).findByEmployerUuid(Mockito.any());
    }

    /**
     * Method under test: {@link EmployerService#countByCityName(String)}
     */
    @Test
    void testCountByCityName() {
        when(employerDAO.countByCityName(Mockito.any())).thenReturn(3L);
        assertEquals(3L, employerService.countByCityName("Oxford"));
        verify(employerDAO).countByCityName(Mockito.any());
    }

    /**
     * Method under test: {@link EmployerService#countByCityName(String)}
     */
    @Test
    void testCountByCityName2() {
        when(employerDAO.countByCityName(Mockito.any()))
                .thenThrow(new FileUploadToProfileException("An error occurred"));
        assertThrows(FileUploadToProfileException.class, () -> employerService.countByCityName("Oxford"));
        verify(employerDAO).countByCityName(Mockito.any());
    }


    /**
     * Method under test: {@link EmployerService#deleteEmployerProfile(Long)}
     */
    @Test
    void testDeleteEmployerProfile() {
        when(employerDAO.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> employerService.deleteEmployerProfile(1L));
        verify(employerDAO).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EmployerService#deleteEmployerProfile(Long)}
     */
    @Test
    void testDeleteEmployerProfile2() {
        when(employerDAO.findById(Mockito.<Long>any())).thenThrow(new FileUploadToProfileException("An error occurred"));
        assertThrows(FileUploadToProfileException.class, () -> employerService.deleteEmployerProfile(1L));
        verify(employerDAO).findById(Mockito.<Long>any());
    }


    /**
     * Method under test: {@link EmployerService#fireCandidate(Long)}
     */
    @Test
    void testFireCandidate() {
        doNothing().when(employerDAO).deleteAssignEmployerToCandidateAndChangeStatus(Mockito.<Long>any());
        employerService.fireCandidate(1L);
        verify(employerDAO).deleteAssignEmployerToCandidateAndChangeStatus(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link EmployerService#fireCandidate(Long)}
     */
    @Test
    void testFireCandidate2() {
        doThrow(new FileUploadToProfileException("An error occurred")).when(employerDAO)
                .deleteAssignEmployerToCandidateAndChangeStatus(Mockito.<Long>any());
        assertThrows(FileUploadToProfileException.class, () -> employerService.fireCandidate(1L));
        verify(employerDAO).deleteAssignEmployerToCandidateAndChangeStatus(Mockito.<Long>any());
    }
}

