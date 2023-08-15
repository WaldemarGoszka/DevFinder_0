package pl.devfinder.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import pl.devfinder.business.dao.CandidateDAO;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.CandidateUpdateRequest;
import pl.devfinder.domain.User;
import pl.devfinder.domain.exception.FileUploadToProfileException;
import pl.devfinder.domain.exception.NotFoundException;
import pl.devfinder.domain.search.CandidateSearchCriteria;
import pl.devfinder.infrastructure.database.repository.criteria.CandidateCriteriaRepository;

@ContextConfiguration(classes = {CandidateService.class})
@ExtendWith(SpringExtension.class)
class CandidateServiceDiffBlueTest {
    @MockBean
    private CandidateCriteriaRepository candidateCriteriaRepository;

    @MockBean
    private CandidateDAO candidateDAO;

    @Autowired
    private CandidateService candidateService;

    @MockBean
    private CandidateSkillService candidateSkillService;

    @MockBean
    private CityService cityService;

    @MockBean
    private DataService dataService;

    @MockBean
    private EmployerService employerService;

    @MockBean
    private FileUploadService fileUploadService;

    @MockBean
    private SkillService skillService;

    /**
     * Method under test: {@link CandidateService#findAllByCriteria(CandidateSearchCriteria)}
     */
    @Test
    void testFindAllByCriteria() {
        PageImpl<Candidate> pageImpl = new PageImpl<>(new ArrayList<>());
        when(candidateCriteriaRepository.findAllByCriteria(Mockito.<CandidateSearchCriteria>any())).thenReturn(pageImpl);

        CandidateSearchCriteria candidateSearchCriteria = new CandidateSearchCriteria();
        candidateSearchCriteria.setCity("Oxford");
        candidateSearchCriteria.setEmployer("Employer");
        candidateSearchCriteria.setExperienceLevels(new ArrayList<>());
        candidateSearchCriteria.setMinYearsOfExperience(1);
        candidateSearchCriteria.setOpenToRemoteJob(new ArrayList<>());
        candidateSearchCriteria.setPageNumber(10);
        candidateSearchCriteria.setPageSize(3);
        candidateSearchCriteria.setSalaryMax(BigDecimal.valueOf(1L));
        candidateSearchCriteria.setSkills(new ArrayList<>());
        candidateSearchCriteria.setSortBy("Sort By");
        candidateSearchCriteria.setSortDirection(Sort.Direction.ASC);
        candidateSearchCriteria.setStatus(new ArrayList<>());
        Page<Candidate> actualFindAllByCriteriaResult = candidateService.findAllByCriteria(candidateSearchCriteria);
        assertSame(pageImpl, actualFindAllByCriteriaResult);
        assertTrue(actualFindAllByCriteriaResult.toList().isEmpty());
        verify(candidateCriteriaRepository).findAllByCriteria(Mockito.<CandidateSearchCriteria>any());
    }

    /**
     * Method under test: {@link CandidateService#findById(Long)}
     */
    @Test
    void testFindById() {
        when(candidateDAO.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> candidateService.findById(1L));
        verify(candidateDAO).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CandidateService#findById(Long)}
     */
    @Test
    void testFindById2() {
        when(candidateDAO.findById(Mockito.<Long>any())).thenThrow(new FileUploadToProfileException("An error occurred"));
        assertThrows(FileUploadToProfileException.class, () -> candidateService.findById(1L));
        verify(candidateDAO).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CandidateService#findByCandidateUuid(String)}
     */
    @Test
    void testFindByCandidateUuid() {
        Optional<Candidate> emptyResult = Optional.empty();
        when(candidateDAO.findByCandidateUuid(Mockito.<String>any())).thenReturn(emptyResult);
        Optional<Candidate> actualFindByCandidateUuidResult = candidateService
                .findByCandidateUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        assertSame(emptyResult, actualFindByCandidateUuidResult);
        assertFalse(actualFindByCandidateUuidResult.isPresent());
        verify(candidateDAO).findByCandidateUuid(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CandidateService#findByCandidateUuid(String)}
     */
    @Test
    void testFindByCandidateUuid2() {
        when(candidateDAO.findByCandidateUuid(Mockito.<String>any()))
                .thenThrow(new FileUploadToProfileException("An error occurred"));
        assertThrows(FileUploadToProfileException.class,
                () -> candidateService.findByCandidateUuid("01234567-89AB-CDEF-FEDC-BA9876543210"));
        verify(candidateDAO).findByCandidateUuid(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CandidateService#updateCandidateProfile(CandidateUpdateRequest, Candidate)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateCandidateProfile() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "pl.devfinder.domain.CandidateUpdateRequest.getResidenceCityName()" because "candidateUpdateRequest" is null
        //       at pl.devfinder.business.CandidateService.updateCandidateProfile(CandidateService.java:51)
        //   See https://diff.blue/R013 to resolve this issue.

        candidateService.updateCandidateProfile(null, null);
    }

    /**
     * Method under test: {@link CandidateService#updateCandidateProfile(CandidateUpdateRequest, Candidate)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateCandidateProfile2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:377)
        //       at pl.devfinder.business.CandidateService.updateCandidateProfile(CandidateService.java:54)
        //   See https://diff.blue/R013 to resolve this issue.

        when(cityService.save(Mockito.<String>any())).thenReturn(null);
        when(cityService.nonCityExist(Mockito.<String>any())).thenReturn(true);
        when(cityService.findByCityName(Mockito.<String>any())).thenReturn(Optional.empty());
        CandidateUpdateRequest candidateUpdateRequest = mock(CandidateUpdateRequest.class);
        when(candidateUpdateRequest.getResidenceCityName()).thenReturn("Residence City Name");
        candidateService.updateCandidateProfile(candidateUpdateRequest, null);
    }

    /**
     * Method under test: {@link CandidateService#updateCandidateProfile(CandidateUpdateRequest, Candidate)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateCandidateProfile3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:377)
        //       at pl.devfinder.business.CandidateService.updateCandidateProfile(CandidateService.java:54)
        //   See https://diff.blue/R013 to resolve this issue.

        when(cityService.save(Mockito.<String>any())).thenReturn(null);
        when(cityService.nonCityExist(Mockito.<String>any())).thenReturn(false);
        when(cityService.findByCityName(Mockito.<String>any())).thenReturn(Optional.empty());
        CandidateUpdateRequest candidateUpdateRequest = mock(CandidateUpdateRequest.class);
        when(candidateUpdateRequest.getResidenceCityName()).thenReturn("Residence City Name");
        candidateService.updateCandidateProfile(candidateUpdateRequest, null);
    }

    /**
     * Method under test: {@link CandidateService#updateCandidateProfile(CandidateUpdateRequest, Candidate)}
     */
    @Test
    void testUpdateCandidateProfile4() {
        when(cityService.save(Mockito.<String>any())).thenThrow(new FileUploadToProfileException("An error occurred"));
        when(cityService.nonCityExist(Mockito.<String>any())).thenReturn(true);
        CandidateUpdateRequest candidateUpdateRequest = mock(CandidateUpdateRequest.class);
        when(candidateUpdateRequest.getResidenceCityName()).thenReturn("Residence City Name");
        assertThrows(FileUploadToProfileException.class,
                () -> candidateService.updateCandidateProfile(candidateUpdateRequest, null));
        verify(cityService).nonCityExist(Mockito.<String>any());
        verify(cityService).save(Mockito.<String>any());
        verify(candidateUpdateRequest, atLeast(1)).getResidenceCityName();
    }

    /**
     * Method under test: {@link CandidateService#newCandidateProfile(CandidateUpdateRequest, User)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testNewCandidateProfile() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "pl.devfinder.domain.CandidateUpdateRequest.getResidenceCityName()" because "candidateUpdateRequest" is null
        //       at pl.devfinder.business.CandidateService.newCandidateProfile(CandidateService.java:96)
        //   See https://diff.blue/R013 to resolve this issue.

        candidateService.newCandidateProfile(null, null);
    }

    /**
     * Method under test: {@link CandidateService#newCandidateProfile(CandidateUpdateRequest, User)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testNewCandidateProfile2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:377)
        //       at pl.devfinder.business.CandidateService.newCandidateProfile(CandidateService.java:99)
        //   See https://diff.blue/R013 to resolve this issue.

        when(cityService.save(Mockito.<String>any())).thenReturn(null);
        when(cityService.nonCityExist(Mockito.<String>any())).thenReturn(true);
        when(cityService.findByCityName(Mockito.<String>any())).thenReturn(Optional.empty());
        CandidateUpdateRequest candidateUpdateRequest = mock(CandidateUpdateRequest.class);
        when(candidateUpdateRequest.getResidenceCityName()).thenReturn("Residence City Name");
        candidateService.newCandidateProfile(candidateUpdateRequest, null);
    }

    /**
     * Method under test: {@link CandidateService#newCandidateProfile(CandidateUpdateRequest, User)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testNewCandidateProfile3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:377)
        //       at pl.devfinder.business.CandidateService.newCandidateProfile(CandidateService.java:99)
        //   See https://diff.blue/R013 to resolve this issue.

        when(cityService.save(Mockito.<String>any())).thenReturn(null);
        when(cityService.nonCityExist(Mockito.<String>any())).thenReturn(false);
        when(cityService.findByCityName(Mockito.<String>any())).thenReturn(Optional.empty());
        CandidateUpdateRequest candidateUpdateRequest = mock(CandidateUpdateRequest.class);
        when(candidateUpdateRequest.getResidenceCityName()).thenReturn("Residence City Name");
        candidateService.newCandidateProfile(candidateUpdateRequest, null);
    }

    /**
     * Method under test: {@link CandidateService#newCandidateProfile(CandidateUpdateRequest, User)}
     */
    @Test
    void testNewCandidateProfile4() {
        when(cityService.save(Mockito.<String>any())).thenThrow(new FileUploadToProfileException("An error occurred"));
        when(cityService.nonCityExist(Mockito.<String>any())).thenReturn(true);
        CandidateUpdateRequest candidateUpdateRequest = mock(CandidateUpdateRequest.class);
        when(candidateUpdateRequest.getResidenceCityName()).thenReturn("Residence City Name");
        assertThrows(FileUploadToProfileException.class,
                () -> candidateService.newCandidateProfile(candidateUpdateRequest, null));
        verify(cityService).nonCityExist(Mockito.<String>any());
        verify(cityService).save(Mockito.<String>any());
        verify(candidateUpdateRequest, atLeast(1)).getResidenceCityName();
    }

    /**
     * Method under test: {@link CandidateService#deleteCandidateProfile(Long)}
     */
    @Test
    void testDeleteCandidateProfile() {
        when(candidateDAO.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> candidateService.deleteCandidateProfile(1L));
        verify(candidateDAO).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CandidateService#deleteCandidateProfile(Long)}
     */
    @Test
    void testDeleteCandidateProfile2() {
        when(candidateDAO.findById(Mockito.<Long>any())).thenThrow(new FileUploadToProfileException("An error occurred"));
        assertThrows(FileUploadToProfileException.class, () -> candidateService.deleteCandidateProfile(1L));
        verify(candidateDAO).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CandidateService#countByCityName(String)}
     */
    @Test
    void testCountByCityName() {
        when(candidateDAO.countByCityName(Mockito.<String>any())).thenReturn(3L);
        assertEquals(3L, candidateService.countByCityName("Oxford"));
        verify(candidateDAO).countByCityName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CandidateService#countByCityName(String)}
     */
    @Test
    void testCountByCityName2() {
        when(candidateDAO.countByCityName(Mockito.<String>any()))
                .thenThrow(new FileUploadToProfileException("An error occurred"));
        assertThrows(FileUploadToProfileException.class, () -> candidateService.countByCityName("Oxford"));
        verify(candidateDAO).countByCityName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CandidateService#deleteCvFile(Candidate)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteCvFile() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "pl.devfinder.domain.Candidate.getCandidateUuid()" because "candidate" is null
        //       at pl.devfinder.business.CandidateService.deleteCvFile(CandidateService.java:249)
        //   See https://diff.blue/R013 to resolve this issue.

        candidateService.deleteCvFile(null);
    }

    /**
     * Method under test: {@link CandidateService#deletePhotoFile(Candidate)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeletePhotoFile() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "pl.devfinder.domain.Candidate.getCandidateUuid()" because "candidate" is null
        //       at pl.devfinder.business.CandidateService.deletePhotoFile(CandidateService.java:256)
        //   See https://diff.blue/R013 to resolve this issue.

        candidateService.deletePhotoFile(null);
    }
}

