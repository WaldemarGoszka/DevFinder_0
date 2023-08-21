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
import pl.devfinder.business.dao.CandidateDAO;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.CandidateUpdateRequest;
import pl.devfinder.domain.User;
import pl.devfinder.domain.exception.FileUploadToProfileException;
import pl.devfinder.domain.exception.NotFoundException;
import pl.devfinder.domain.search.CandidateSearchCriteria;
import pl.devfinder.infrastructure.database.repository.criteria.CandidateCriteriaRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    private FileService fileService;

    @MockBean
    private SkillService skillService;

    /**
     * Method under test: {@link CandidateService#findAllByCriteria(CandidateSearchCriteria)}
     */
    @Test
    void testFindAllByCriteria() {
        PageImpl<Candidate> pageImpl = new PageImpl<>(new ArrayList<>());
        when(candidateCriteriaRepository.findAllByCriteria(Mockito.any())).thenReturn(pageImpl);

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
        verify(candidateCriteriaRepository).findAllByCriteria(Mockito.any());
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
        when(candidateDAO.findByCandidateUuid(Mockito.any())).thenReturn(emptyResult);
        Optional<Candidate> actualFindByCandidateUuidResult = candidateService
                .findByCandidateUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        assertSame(emptyResult, actualFindByCandidateUuidResult);
        assertFalse(actualFindByCandidateUuidResult.isPresent());
        verify(candidateDAO).findByCandidateUuid(Mockito.any());
    }

    /**
     * Method under test: {@link CandidateService#findByCandidateUuid(String)}
     */
    @Test
    void testFindByCandidateUuid2() {
        when(candidateDAO.findByCandidateUuid(Mockito.any()))
                .thenThrow(new FileUploadToProfileException("An error occurred"));
        assertThrows(FileUploadToProfileException.class,
                () -> candidateService.findByCandidateUuid("01234567-89AB-CDEF-FEDC-BA9876543210"));
        verify(candidateDAO).findByCandidateUuid(Mockito.any());
    }


    @Test
    void testUpdateCandidateProfile4() {
        when(cityService.save(Mockito.any())).thenThrow(new FileUploadToProfileException("An error occurred"));
        when(cityService.nonCityExist(Mockito.any())).thenReturn(true);
        CandidateUpdateRequest candidateUpdateRequest = mock(CandidateUpdateRequest.class);
        when(candidateUpdateRequest.getResidenceCityName()).thenReturn("Residence City Name");
        assertThrows(FileUploadToProfileException.class,
                () -> candidateService.updateCandidateProfile(candidateUpdateRequest, null));
        verify(cityService).nonCityExist(Mockito.any());
        verify(cityService).save(Mockito.any());
        verify(candidateUpdateRequest, atLeast(1)).getResidenceCityName();
    }


    /**
     * Method under test: {@link CandidateService#newCandidateProfile(CandidateUpdateRequest, User)}
     */


    /**
     * Method under test: {@link CandidateService#newCandidateProfile(CandidateUpdateRequest, User)}
     */
    @Test
    void testNewCandidateProfile4() {
        when(cityService.save(Mockito.any())).thenThrow(new FileUploadToProfileException("An error occurred"));
        when(cityService.nonCityExist(Mockito.any())).thenReturn(true);
        CandidateUpdateRequest candidateUpdateRequest = mock(CandidateUpdateRequest.class);
        when(candidateUpdateRequest.getResidenceCityName()).thenReturn("Residence City Name");
        assertThrows(FileUploadToProfileException.class,
                () -> candidateService.newCandidateProfile(candidateUpdateRequest, null));
        verify(cityService).nonCityExist(Mockito.any());
        verify(cityService).save(Mockito.any());
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
        when(candidateDAO.countByCityName(Mockito.any())).thenReturn(3L);
        assertEquals(3L, candidateService.countByCityName("Oxford"));
        verify(candidateDAO).countByCityName(Mockito.any());
    }

    /**
     * Method under test: {@link CandidateService#countByCityName(String)}
     */
    @Test
    void testCountByCityName2() {
        when(candidateDAO.countByCityName(Mockito.any()))
                .thenThrow(new FileUploadToProfileException("An error occurred"));
        assertThrows(FileUploadToProfileException.class, () -> candidateService.countByCityName("Oxford"));
        verify(candidateDAO).countByCityName(Mockito.any());
    }


}

