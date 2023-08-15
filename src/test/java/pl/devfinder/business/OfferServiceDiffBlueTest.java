package pl.devfinder.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
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
import pl.devfinder.business.dao.OfferDAO;
import pl.devfinder.domain.Employer;
import pl.devfinder.domain.Offer;
import pl.devfinder.domain.OfferUpdateRequest;
import pl.devfinder.domain.exception.NotFoundException;
import pl.devfinder.domain.search.OfferSearchCriteria;
import pl.devfinder.infrastructure.database.repository.CandidateRepository;
import pl.devfinder.infrastructure.database.repository.CityRepository;
import pl.devfinder.infrastructure.database.repository.EmployerRepository;
import pl.devfinder.infrastructure.database.repository.OfferRepository;
import pl.devfinder.infrastructure.database.repository.OfferSkillRepository;
import pl.devfinder.infrastructure.database.repository.SkillRepository;
import pl.devfinder.infrastructure.database.repository.criteria.OfferCriteriaRepository;
import pl.devfinder.infrastructure.database.repository.jpa.CandidateJpaRepository;
import pl.devfinder.infrastructure.database.repository.jpa.CityJpaRepository;
import pl.devfinder.infrastructure.database.repository.jpa.EmployerJpaRepository;
import pl.devfinder.infrastructure.database.repository.jpa.OfferJpaRepository;
import pl.devfinder.infrastructure.database.repository.jpa.OfferSkillJpaRepository;
import pl.devfinder.infrastructure.database.repository.jpa.SkillJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.CandidateEntityMapperImpl;
import pl.devfinder.infrastructure.database.repository.mapper.CityEntityMapperImpl;
import pl.devfinder.infrastructure.database.repository.mapper.EmployerEntityMapperImpl;
import pl.devfinder.infrastructure.database.repository.mapper.OfferEntityMapperImpl;
import pl.devfinder.infrastructure.database.repository.mapper.OfferSkillEntityMapper;
import pl.devfinder.infrastructure.database.repository.mapper.SkillEntityMapper;

@ContextConfiguration(classes = {OfferService.class})
@ExtendWith(SpringExtension.class)
class OfferServiceDiffBlueTest {
    @MockBean
    private CityService cityService;

    @MockBean
    private DataService dataService;

    @MockBean
    private OfferCriteriaRepository offerCriteriaRepository;

    @MockBean
    private OfferDAO offerDAO;

    @Autowired
    private OfferService offerService;

    @MockBean
    private OfferSkillService offerSkillService;

    @MockBean
    private SkillService skillService;

    /**
     * Method under test: {@link OfferService#countByCityName(String)}
     */
    @Test
    void testCountByCityName() {
        when(offerDAO.countByCityName(Mockito.<String>any())).thenReturn(3L);
        assertEquals(3L, offerService.countByCityName("Oxford"));
        verify(offerDAO).countByCityName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link OfferService#countByCityName(String)}
     */
    @Test
    void testCountByCityName2() {
        when(offerDAO.countByCityName(Mockito.<String>any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> offerService.countByCityName("Oxford"));
        verify(offerDAO).countByCityName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link OfferService#findAllByCriteria(OfferSearchCriteria)}
     */
    @Test
    void testFindAllByCriteria() {
        PageImpl<Offer> pageImpl = new PageImpl<>(new ArrayList<>());
        when(offerCriteriaRepository.findAllByCriteria(Mockito.<OfferSearchCriteria>any())).thenReturn(pageImpl);

        OfferSearchCriteria offerSearchCriteria = new OfferSearchCriteria();
        offerSearchCriteria.setCity("Oxford");
        offerSearchCriteria.setEmployer("Employer");
        offerSearchCriteria.setExperienceLevels(new ArrayList<>());
        offerSearchCriteria.setPageNumber(10);
        offerSearchCriteria.setPageSize(3);
        offerSearchCriteria.setRemoteWork(new ArrayList<>());
        offerSearchCriteria.setSalary(new ArrayList<>());
        offerSearchCriteria.setSalaryMin(BigDecimal.valueOf(1L));
        offerSearchCriteria.setSkills(new ArrayList<>());
        offerSearchCriteria.setSortBy("Sort By");
        offerSearchCriteria.setSortDirection(Sort.Direction.ASC);
        offerSearchCriteria.setStatus(new ArrayList<>());
        Page<Offer> actualFindAllByCriteriaResult = offerService.findAllByCriteria(offerSearchCriteria);
        assertSame(pageImpl, actualFindAllByCriteriaResult);
        assertTrue(actualFindAllByCriteriaResult.toList().isEmpty());
        verify(offerCriteriaRepository).findAllByCriteria(Mockito.<OfferSearchCriteria>any());
    }

    /**
     * Method under test: {@link OfferService#getDaysSinceDate(OffsetDateTime)}
     */
    @Test
    void testGetDaysSinceDate() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R031 Method may be time-sensitive.
        //   Diffblue Cover was only able to write tests that are time-sensitive.
        //   The assertions don't pass when run at an alternate date, time, and
        //   timezone. Try refactoring the method to take a java.time.Clock instance so
        //   that the time can be parameterized during testing.
        //   See https://diff.blue/R031 for details.

        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository2 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper2 = new OfferEntityMapperImpl();
        OfferRepository offerDAO2 = new OfferRepository(offerJpaRepository2, offerEntityMapper2,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        DataService dataService = new DataService(employerDAO, offerDAO2,
                new CandidateRepository(candidateJpaRepository, new CandidateEntityMapperImpl()));

        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        OfferSkillJpaRepository offerSkillJpaRepository = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository, offerSkillEntityMapper, new OfferEntityMapperImpl()));
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService,
                offerSkillService,
                new SkillService(new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class))));
        offerService.getDaysSinceDate(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        assertEquals(0L, offerService.countByCityName("Oxford"));
        assertEquals("1 % time", offerService.formatRemoteWork(1));
    }

    /**
     * Method under test: {@link OfferService#getDaysSinceDate(OffsetDateTime)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetDaysSinceDate2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R031 Method may be time-sensitive.
        //   Diffblue Cover was only able to write tests that are time-sensitive.
        //   The assertions don't pass when run at an alternate date, time, and
        //   timezone. Try refactoring the method to take a java.time.Clock instance so
        //   that the time can be parameterized during testing.
        //   See https://diff.blue/R031 for details.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.time.temporal.Temporal.until(java.time.temporal.Temporal, java.time.temporal.TemporalUnit)" because "startInclusive" is null
        //       at java.base/java.time.Duration.between(Duration.java:490)
        //       at pl.devfinder.business.OfferService.getDaysSinceDate(OfferService.java:47)
        //   See https://diff.blue/R013 to resolve this issue.

        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository2 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper2 = new OfferEntityMapperImpl();
        OfferRepository offerDAO2 = new OfferRepository(offerJpaRepository2, offerEntityMapper2,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        DataService dataService = new DataService(employerDAO, offerDAO2,
                new CandidateRepository(candidateJpaRepository, new CandidateEntityMapperImpl()));

        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        OfferSkillJpaRepository offerSkillJpaRepository = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository, offerSkillEntityMapper, new OfferEntityMapperImpl()));
        (new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService, offerSkillService,
                new SkillService(new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)))))
                .getDaysSinceDate(null);
    }

    /**
     * Method under test: {@link OfferService#formatSalaryRange(BigDecimal, BigDecimal)}
     */
    @Test
    void testFormatSalaryRange() {
        BigDecimal salaryMin = BigDecimal.valueOf(1L);
        assertEquals("1 - 1", offerService.formatSalaryRange(salaryMin, BigDecimal.valueOf(1L)));
    }

    /**
     * Method under test: {@link OfferService#formatSalaryRange(BigDecimal, BigDecimal)}
     */
    @Test
    void testFormatSalaryRange2() {
        assertEquals("UNDISCLOSED", offerService.formatSalaryRange(null, BigDecimal.valueOf(1L)));
    }

    /**
     * Method under test: {@link OfferService#formatSalaryRange(BigDecimal, BigDecimal)}
     */
    @Test
    void testFormatSalaryRange3() {
        assertEquals("UNDISCLOSED", offerService.formatSalaryRange(BigDecimal.valueOf(1L), null));
    }

    /**
     * Method under test: {@link OfferService#formatRemoteWork(Integer)}
     */
    @Test
    void testFormatRemoteWork() {
        assertEquals("1 % time", offerService.formatRemoteWork(1));
        assertEquals("No", offerService.formatRemoteWork(0));
        assertEquals("No", offerService.formatRemoteWork(null));
    }

    /**
     * Method under test: {@link OfferService#findById(Long)}
     */
    @Test
    void testFindById() {
        when(offerDAO.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> offerService.findById(1L));
        verify(offerDAO).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link OfferService#findById(Long)}
     */
    @Test
    void testFindById2() {
        when(offerDAO.findById(Mockito.<Long>any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> offerService.findById(1L));
        verify(offerDAO).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link OfferService#findByOfferIdAndEmployerId(Long, Employer)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindByOfferIdAndEmployerId() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "pl.devfinder.domain.Employer.getEmployerId()" because "employer" is null
        //       at pl.devfinder.business.OfferService.findByOfferIdAndEmployerId(OfferService.java:73)
        //   See https://diff.blue/R013 to resolve this issue.

        offerService.findByOfferIdAndEmployerId(1L, null);
    }

    /**
     * Method under test: {@link OfferService#updateOffer(OfferUpdateRequest, Employer, Offer)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateOffer() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "pl.devfinder.domain.OfferUpdateRequest.getCityName()" because "offerUpdateRequest" is null
        //       at pl.devfinder.business.OfferService.updateOffer(OfferService.java:80)
        //   See https://diff.blue/R013 to resolve this issue.

        offerService.updateOffer(null, null, null);
    }

    /**
     * Method under test: {@link OfferService#createNewOffer(OfferUpdateRequest, Employer)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateNewOffer() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "pl.devfinder.domain.OfferUpdateRequest.getCityName()" because "offerUpdateRequest" is null
        //       at pl.devfinder.business.OfferService.createNewOffer(OfferService.java:131)
        //   See https://diff.blue/R013 to resolve this issue.

        offerService.createNewOffer(null, null);
    }

    /**
     * Method under test: {@link OfferService#deleteAllByEmployerId(Employer)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteAllByEmployerId() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "pl.devfinder.domain.Employer.getEmployerId()" because "employer" is null
        //       at pl.devfinder.business.OfferService.deleteAllByEmployerId(OfferService.java:166)
        //   See https://diff.blue/R013 to resolve this issue.

        offerService.deleteAllByEmployerId(null);
    }

    /**
     * Method under test: {@link OfferService#findAllByEmployer(Employer)}
     */
    @Test
    void testFindAllByEmployer() {
        ArrayList<Offer> offerList = new ArrayList<>();
        when(offerDAO.findAllByEmployer(Mockito.<Employer>any())).thenReturn(offerList);
        List<Offer> actualFindAllByEmployerResult = offerService.findAllByEmployer(null);
        assertSame(offerList, actualFindAllByEmployerResult);
        assertTrue(actualFindAllByEmployerResult.isEmpty());
        verify(offerDAO).findAllByEmployer(Mockito.<Employer>any());
    }

    /**
     * Method under test: {@link OfferService#deleteOffer(Long)}
     */
    @Test
    void testDeleteOffer() {
        when(offerDAO.findById(Mockito.<Long>any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> offerService.deleteOffer(1L));
        verify(offerDAO).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link OfferService#deleteOffer(Long)}
     */
    @Test
    void testDeleteOffer2() {
        when(offerDAO.findById(Mockito.<Long>any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> offerService.deleteOffer(1L));
        verify(offerDAO).findById(Mockito.<Long>any());
    }
}

