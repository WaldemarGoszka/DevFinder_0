package pl.devfinder.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

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
import pl.devfinder.domain.EmployerUpdateRequest;
import pl.devfinder.domain.User;
import pl.devfinder.domain.exception.FileUploadToProfileException;
import pl.devfinder.domain.exception.NotFoundException;
import pl.devfinder.domain.search.EmployerSearchCriteria;
import pl.devfinder.infrastructure.database.repository.criteria.EmployerCriteriaRepository;

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
    private FileUploadService fileUploadService;

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
        when(employerDAO.findByCompanyName(Mockito.<String>any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> employerService.findByCompanyName("Employer Company Name"));
        verify(employerDAO).findByCompanyName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link EmployerService#findByCompanyName(String)}
     */
    @Test
    void testFindByCompanyName2() {
        when(employerDAO.findByCompanyName(Mockito.<String>any()))
                .thenThrow(new FileUploadToProfileException("An error occurred"));
        assertThrows(FileUploadToProfileException.class,
                () -> employerService.findByCompanyName("Employer Company Name"));
        verify(employerDAO).findByCompanyName(Mockito.<String>any());
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
        when(employerCriteriaRepository.findAllByCriteria(Mockito.<EmployerSearchCriteria>any())).thenReturn(pageImpl);

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
        verify(employerCriteriaRepository).findAllByCriteria(Mockito.<EmployerSearchCriteria>any());
    }

    /**
     * Method under test: {@link EmployerService#findByEmployerUuid(String)}
     */
    @Test
    void testFindByEmployerUuid() {
        Optional<Employer> emptyResult = Optional.empty();
        when(employerDAO.findByEmployerUuid(Mockito.<String>any())).thenReturn(emptyResult);
        Optional<Employer> actualFindByEmployerUuidResult = employerService
                .findByEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        assertSame(emptyResult, actualFindByEmployerUuidResult);
        assertFalse(actualFindByEmployerUuidResult.isPresent());
        verify(employerDAO).findByEmployerUuid(Mockito.<String>any());
    }

    /**
     * Method under test: {@link EmployerService#findByEmployerUuid(String)}
     */
    @Test
    void testFindByEmployerUuid2() {
        when(employerDAO.findByEmployerUuid(Mockito.<String>any()))
                .thenThrow(new FileUploadToProfileException("An error occurred"));
        assertThrows(FileUploadToProfileException.class,
                () -> employerService.findByEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210"));
        verify(employerDAO).findByEmployerUuid(Mockito.<String>any());
    }

    /**
     * Method under test: {@link EmployerService#countByCityName(String)}
     */
    @Test
    void testCountByCityName() {
        when(employerDAO.countByCityName(Mockito.<String>any())).thenReturn(3L);
        assertEquals(3L, employerService.countByCityName("Oxford"));
        verify(employerDAO).countByCityName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link EmployerService#countByCityName(String)}
     */
    @Test
    void testCountByCityName2() {
        when(employerDAO.countByCityName(Mockito.<String>any()))
                .thenThrow(new FileUploadToProfileException("An error occurred"));
        assertThrows(FileUploadToProfileException.class, () -> employerService.countByCityName("Oxford"));
        verify(employerDAO).countByCityName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link EmployerService#newEmployerProfile(EmployerUpdateRequest, User)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testNewEmployerProfile() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "pl.devfinder.domain.EmployerUpdateRequest.getCityName()" because "employerUpdateRequest" is null
        //       at pl.devfinder.business.EmployerService.newEmployerProfile(EmployerService.java:70)
        //   See https://diff.blue/R013 to resolve this issue.

        employerService.newEmployerProfile(null, null);
    }

    /**
     * Method under test: {@link EmployerService#updateEmployerProfile(EmployerUpdateRequest, Employer)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateEmployerProfile() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "pl.devfinder.domain.EmployerUpdateRequest.getCityName()" because "employerUpdateRequest" is null
        //       at pl.devfinder.business.EmployerService.updateEmployerProfile(EmployerService.java:110)
        //   See https://diff.blue/R013 to resolve this issue.

        employerService.updateEmployerProfile(null, null);
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
     * Method under test: {@link EmployerService#deleteLogoFile(Employer)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteLogoFile() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "pl.devfinder.domain.Employer.getEmployerUuid()" because "employer" is null
        //       at pl.devfinder.business.EmployerService.deleteLogoFile(EmployerService.java:183)
        //   See https://diff.blue/R013 to resolve this issue.

        employerService.deleteLogoFile(null);
    }

    /**
     * Method under test: {@link EmployerService#hireCandidate(Employer, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHireCandidate() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "pl.devfinder.domain.Employer.getEmployerId()" because "employer" is null
        //       at pl.devfinder.business.EmployerService.hireCandidate(EmployerService.java:190)
        //   See https://diff.blue/R013 to resolve this issue.

        employerService.hireCandidate(null, 1L);
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

