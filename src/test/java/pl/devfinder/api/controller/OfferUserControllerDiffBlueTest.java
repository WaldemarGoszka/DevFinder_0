package pl.devfinder.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.reactive.context.StandardReactiveWebEnvironment;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import pl.devfinder.api.dto.OfferDetailsDTO;
import pl.devfinder.api.dto.OfferUpdateRequestDTO;
import pl.devfinder.api.dto.mapper.CityMapper;
import pl.devfinder.api.dto.mapper.OfferDetailsMapper;
import pl.devfinder.api.dto.mapper.OfferRowMapper;
import pl.devfinder.api.dto.mapper.OfferRowMapperImpl;
import pl.devfinder.api.dto.mapper.OfferUpdateRequestMapper;
import pl.devfinder.api.dto.mapper.SkillMapper;
import pl.devfinder.business.CityService;
import pl.devfinder.business.DataService;
import pl.devfinder.business.EmployerService;
import pl.devfinder.business.FileUploadService;
import pl.devfinder.business.OfferService;
import pl.devfinder.business.OfferSkillService;
import pl.devfinder.business.SkillService;
import pl.devfinder.business.UserService;
import pl.devfinder.domain.Offer;
import pl.devfinder.domain.Role;
import pl.devfinder.domain.User;
import pl.devfinder.domain.exception.NotFoundException;
import pl.devfinder.infrastructure.database.entity.CityEntity;
import pl.devfinder.infrastructure.database.entity.EmployerEntity;
import pl.devfinder.infrastructure.database.entity.OfferEntity;
import pl.devfinder.infrastructure.database.entity.RoleEntity;
import pl.devfinder.infrastructure.database.entity.UserEntity;
import pl.devfinder.infrastructure.database.repository.CandidateRepository;
import pl.devfinder.infrastructure.database.repository.CityRepository;
import pl.devfinder.infrastructure.database.repository.EmailVerificationTokenRepository;
import pl.devfinder.infrastructure.database.repository.EmployerRepository;
import pl.devfinder.infrastructure.database.repository.OfferRepository;
import pl.devfinder.infrastructure.database.repository.OfferSkillRepository;
import pl.devfinder.infrastructure.database.repository.SkillRepository;
import pl.devfinder.infrastructure.database.repository.UserRepository;
import pl.devfinder.infrastructure.database.repository.criteria.EmployerCriteriaRepository;
import pl.devfinder.infrastructure.database.repository.criteria.OfferCriteriaRepository;
import pl.devfinder.infrastructure.database.repository.jpa.CandidateJpaRepository;
import pl.devfinder.infrastructure.database.repository.jpa.CityJpaRepository;
import pl.devfinder.infrastructure.database.repository.jpa.EmailVerificationTokenJpaRepository;
import pl.devfinder.infrastructure.database.repository.jpa.EmployerJpaRepository;
import pl.devfinder.infrastructure.database.repository.jpa.OfferJpaRepository;
import pl.devfinder.infrastructure.database.repository.jpa.OfferSkillJpaRepository;
import pl.devfinder.infrastructure.database.repository.jpa.SkillJpaRepository;
import pl.devfinder.infrastructure.database.repository.jpa.UserJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.CandidateEntityMapperImpl;
import pl.devfinder.infrastructure.database.repository.mapper.CityEntityMapperImpl;
import pl.devfinder.infrastructure.database.repository.mapper.EmailVerificationTokenMapperImpl;
import pl.devfinder.infrastructure.database.repository.mapper.EmployerEntityMapperImpl;
import pl.devfinder.infrastructure.database.repository.mapper.OfferEntityMapperImpl;
import pl.devfinder.infrastructure.database.repository.mapper.OfferSkillEntityMapper;
import pl.devfinder.infrastructure.database.repository.mapper.SkillEntityMapper;
import pl.devfinder.infrastructure.database.repository.mapper.UserEntityMapperImpl;

@ContextConfiguration(classes = {OfferUserController.class})
@ExtendWith(SpringExtension.class)
class OfferUserControllerDiffBlueTest {
    @MockBean
    private CityMapper cityMapper;

    @MockBean
    private CityService cityService;

    @MockBean
    private EmployerService employerService;

    @MockBean
    private OfferDetailsMapper offerDetailsMapper;

    @MockBean
    private OfferRowMapper offerRowMapper;

    @MockBean
    private OfferService offerService;

    @MockBean
    private OfferUpdateRequestMapper offerUpdateRequestMapper;

    @Autowired
    private OfferUserController offerUserController;

    @MockBean
    private SkillMapper skillMapper;

    @MockBean
    private SkillService skillService;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link OfferUserController#getOfferDetailsToEmployer(Long, Model, Authentication)}
     */
    @Test
    void testGetOfferDetailsToEmployer() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.OfferUserController.lambda$getOfferDetailsToEmployer$2(OfferUserController.java:74)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferDetailsToEmployer(OfferUserController.java:74)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService2 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService2,
                offerSkillService, new SkillService(null));

        FileUploadService fileUploadService = new FileUploadService(new StandardReactiveWebEnvironment());
        OfferSkillJpaRepository offerSkillJpaRepository = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService2 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository, offerSkillEntityMapper, new OfferEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository2 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO2 = new EmployerRepository(employerJpaRepository2, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository2 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper2 = new OfferEntityMapperImpl();
        OfferRepository offerDAO2 = new OfferRepository(offerJpaRepository2, offerEntityMapper2,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository, new CandidateEntityMapperImpl())));

        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository2 = mock(OfferCriteriaRepository.class);
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository4 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper4 = new OfferEntityMapperImpl();
        OfferRepository offerDAO4 = new OfferRepository(offerJpaRepository4, offerEntityMapper4,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        DataService dataService2 = new DataService(employerDAO3, offerDAO4,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl()));

        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService3 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferSkillJpaRepository offerSkillJpaRepository2 = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper2 = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService3 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository2, offerSkillEntityMapper2, new OfferEntityMapperImpl()));
        OfferService offerService2 = new OfferService(offerDAO3, offerCriteriaRepository2, dataService2, cityService3,
                offerSkillService3,
                new SkillService(new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class))));

        CityJpaRepository cityJpaRepository3 = mock(CityJpaRepository.class);
        CityService cityService4 = new CityService(new CityRepository(cityJpaRepository3, new CityEntityMapperImpl()));
        CityMapper cityMapper = mock(CityMapper.class);
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        OfferDetailsMapper offerDetailsMapper = mock(OfferDetailsMapper.class);
        OfferUserController offerUserController = new OfferUserController(userService, employerService, offerService2,
                cityService4, cityMapper, skillService, skillMapper, offerDetailsMapper, new OfferRowMapperImpl(),
                mock(OfferUpdateRequestMapper.class));
        assertThrows(NotFoundException.class,
                () -> offerUserController.getOfferDetailsToEmployer(1L, new ConcurrentModel(), null));
    }

    /**
     * Method under test: {@link OfferUserController#getOfferDetailsToEmployer(Long, Model, Authentication)}
     */
    @Test
    void testGetOfferDetailsToEmployer2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.OfferUserController.lambda$getOfferDetailsToEmployer$2(OfferUserController.java:74)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferDetailsToEmployer(OfferUserController.java:74)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        UserService userService = mock(UserService.class);
        when(userService.findByEmail(Mockito.<String>any())).thenReturn(Optional.empty());
        OfferUserController offerUserController = new OfferUserController(userService, mock(EmployerService.class),
                mock(OfferService.class), mock(CityService.class), mock(CityMapper.class), mock(SkillService.class),
                mock(SkillMapper.class), mock(OfferDetailsMapper.class), mock(OfferRowMapper.class),
                mock(OfferUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        assertThrows(NotFoundException.class, () -> offerUserController.getOfferDetailsToEmployer(1L, model,
                new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userService).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link OfferUserController#getOfferDetailsToEmployer(Long, Model, Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetOfferDetailsToEmployer3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.OfferUserController.lambda$getOfferDetailsToEmployer$2(OfferUserController.java:74)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferDetailsToEmployer(OfferUserController.java:74)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   pl.devfinder.domain.exception.NotFoundException: An error occurred
        //       at pl.devfinder.business.management.Utility.putUserDataToModel(Utility.java:34)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferDetailsToEmployer(OfferUserController.java:73)
        //   See https://diff.blue/R013 to resolve this issue.

        UserService userService = mock(UserService.class);
        when(userService.findByEmail(Mockito.<String>any())).thenThrow(new NotFoundException("An error occurred"));
        OfferUserController offerUserController = new OfferUserController(userService, mock(EmployerService.class),
                mock(OfferService.class), mock(CityService.class), mock(CityMapper.class), mock(SkillService.class),
                mock(SkillMapper.class), mock(OfferDetailsMapper.class), mock(OfferRowMapper.class),
                mock(OfferUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        offerUserController.getOfferDetailsToEmployer(1L, model,
                new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link OfferUserController#getOfferDetailsToEmployer(Long, Model, Authentication)}
     */
    @Test
    void testGetOfferDetailsToEmployer4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.OfferUserController.lambda$getOfferDetailsToEmployer$2(OfferUserController.java:74)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferDetailsToEmployer(OfferUserController.java:74)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setRole("Role");
        roleEntity.setUserId(new HashSet<>());
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getIsEnabled()).thenReturn(true);
        when(userEntity.getId()).thenReturn(1L);
        when(userEntity.getEmail()).thenReturn("jane.doe@example.org");
        when(userEntity.getPassword()).thenReturn("iloveyou");
        when(userEntity.getUserName()).thenReturn("janedoe");
        when(userEntity.getUserUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userEntity.getRoleId()).thenReturn(roleEntity);
        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        when(userJpaRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(userEntity));
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        EmployerEntity employerEntity = new EmployerEntity();
        employerEntity.setAmountOfAvailableOffers(10);
        employerEntity.setCityId(cityId);
        employerEntity.setCompanyName("Company Name");
        employerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerEntity.setDescription("The characteristics of someone or something");
        employerEntity.setEmailContact("jane.doe@example.org");
        employerEntity.setEmployerId(1L);
        employerEntity.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerEntity.setLogoFilename("foo.txt");
        employerEntity.setNumberOfEmployees(10);
        employerEntity.setPhoneNumber("6625550144");
        employerEntity.setWebsite("Website");
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        when(employerJpaRepository.findByEmployerUuid(Mockito.<String>any())).thenReturn(Optional.of(employerEntity));
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService2 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService2,
                offerSkillService, new SkillService(null));

        FileUploadService fileUploadService = new FileUploadService(new StandardReactiveWebEnvironment());
        OfferSkillJpaRepository offerSkillJpaRepository = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService2 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository, offerSkillEntityMapper, new OfferEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository2 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO2 = new EmployerRepository(employerJpaRepository2, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository2 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper2 = new OfferEntityMapperImpl();
        OfferRepository offerDAO2 = new OfferRepository(offerJpaRepository2, offerEntityMapper2,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository, new CandidateEntityMapperImpl())));

        CityEntity cityId2 = new CityEntity();
        cityId2.setCandidateResidenceCities(new HashSet<>());
        cityId2.setCityId(1L);
        cityId2.setCityName("Oxford");
        cityId2.setEmployerCities(new HashSet<>());
        cityId2.setOfferCities(new HashSet<>());

        CityEntity cityId3 = new CityEntity();
        cityId3.setCandidateResidenceCities(new HashSet<>());
        cityId3.setCityId(1L);
        cityId3.setCityName("Oxford");
        cityId3.setEmployerCities(new HashSet<>());
        cityId3.setOfferCities(new HashSet<>());

        EmployerEntity employerId = new EmployerEntity();
        employerId.setAmountOfAvailableOffers(10);
        employerId.setCityId(cityId3);
        employerId.setCompanyName("Company Name");
        employerId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId.setDescription("The characteristics of someone or something");
        employerId.setEmailContact("jane.doe@example.org");
        employerId.setEmployerId(1L);
        employerId.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerId.setLogoFilename("foo.txt");
        employerId.setNumberOfEmployees(10);
        employerId.setPhoneNumber("6625550144");
        employerId.setWebsite("Website");

        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setBenefits("Benefits");
        offerEntity.setCityId(cityId2);
        offerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        offerEntity.setDescription("The characteristics of someone or something");
        offerEntity.setEmployerId(employerId);
        offerEntity.setExperienceLevel("Experience Level");
        offerEntity.setOfferId(1L);
        offerEntity.setOfferSkills(new HashSet<>());
        offerEntity.setOfferUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        offerEntity.setOtherSkills("Other Skills");
        offerEntity.setRemoteWork(1);
        offerEntity.setSalaryMax(BigDecimal.valueOf(1L));
        offerEntity.setSalaryMin(BigDecimal.valueOf(1L));
        offerEntity.setStatus("Status");
        offerEntity.setTitle("Dr");
        offerEntity.setYearsOfExperience(1);
        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        when(offerJpaRepository3.findByOfferIdAndEmployerId(Mockito.<Long>any(), Mockito.<EmployerEntity>any()))
                .thenReturn(Optional.of(offerEntity));
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository2 = mock(OfferCriteriaRepository.class);
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository4 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper4 = new OfferEntityMapperImpl();
        OfferRepository offerDAO4 = new OfferRepository(offerJpaRepository4, offerEntityMapper4,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        DataService dataService2 = new DataService(employerDAO3, offerDAO4,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl()));

        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService3 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferSkillJpaRepository offerSkillJpaRepository2 = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper2 = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService3 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository2, offerSkillEntityMapper2, new OfferEntityMapperImpl()));
        OfferService offerService2 = new OfferService(offerDAO3, offerCriteriaRepository2, dataService2, cityService3,
                offerSkillService3,
                new SkillService(new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class))));

        OfferDetailsMapper offerDetailsMapper = mock(OfferDetailsMapper.class);
        when(offerDetailsMapper.map(Mockito.<Offer>any())).thenReturn(new OfferDetailsDTO());
        CityJpaRepository cityJpaRepository3 = mock(CityJpaRepository.class);
        CityService cityService4 = new CityService(new CityRepository(cityJpaRepository3, new CityEntityMapperImpl()));
        CityMapper cityMapper = mock(CityMapper.class);
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        OfferUserController offerUserController = new OfferUserController(userService, employerService, offerService2,
                cityService4, cityMapper, skillService, skillMapper, offerDetailsMapper, new OfferRowMapperImpl(),
                mock(OfferUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        assertEquals("employer/offer_details", offerUserController.getOfferDetailsToEmployer(1L, model,
                new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userJpaRepository).findByEmail(Mockito.<String>any());
        verify(userEntity).getIsEnabled();
        verify(userEntity).getId();
        verify(userEntity).getEmail();
        verify(userEntity).getPassword();
        verify(userEntity).getUserName();
        verify(userEntity).getUserUuid();
        verify(userEntity).getRoleId();
        verify(employerJpaRepository).findByEmployerUuid(Mockito.<String>any());
        verify(offerJpaRepository3).findByOfferIdAndEmployerId(Mockito.<Long>any(), Mockito.<EmployerEntity>any());
        verify(offerDetailsMapper).map(Mockito.<Offer>any());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", ((User) model.get("user")).getUserUuid());
        assertEquals("janedoe", ((User) model.get("user")).getUserName());
        assertEquals("iloveyou", ((User) model.get("user")).getPassword());
        assertEquals("jane.doe@example.org", ((User) model.get("user")).getEmail());
        assertTrue(((User) model.get("user")).getIsEnabled());
        assertEquals(1L, ((User) model.get("user")).getId().longValue());
        Role role = ((User) model.get("user")).getRole();
        assertNull(role.getUserId());
        assertEquals(1L, role.getId().longValue());
        assertEquals("Role", role.getRole());
    }

    /**
     * Method under test: {@link OfferUserController#getOfferDetailsToEmployer(Long, Model, Authentication)}
     */
    @Test
    void testGetOfferDetailsToEmployer5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.OfferUserController.lambda$getOfferDetailsToEmployer$2(OfferUserController.java:74)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferDetailsToEmployer(OfferUserController.java:74)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setRole("Role");
        roleEntity.setUserId(new HashSet<>());
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getIsEnabled()).thenReturn(true);
        when(userEntity.getId()).thenReturn(1L);
        when(userEntity.getEmail()).thenReturn("jane.doe@example.org");
        when(userEntity.getPassword()).thenReturn("iloveyou");
        when(userEntity.getUserName()).thenReturn("janedoe");
        when(userEntity.getUserUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userEntity.getRoleId()).thenReturn(roleEntity);
        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        when(userJpaRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(userEntity));
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        EmployerEntity employerEntity = new EmployerEntity();
        employerEntity.setAmountOfAvailableOffers(10);
        employerEntity.setCityId(cityId);
        employerEntity.setCompanyName("Company Name");
        employerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerEntity.setDescription("The characteristics of someone or something");
        employerEntity.setEmailContact("jane.doe@example.org");
        employerEntity.setEmployerId(1L);
        employerEntity.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerEntity.setLogoFilename("foo.txt");
        employerEntity.setNumberOfEmployees(10);
        employerEntity.setPhoneNumber("6625550144");
        employerEntity.setWebsite("Website");
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        when(employerJpaRepository.findByEmployerUuid(Mockito.<String>any())).thenReturn(Optional.of(employerEntity));
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService2 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService2,
                offerSkillService, new SkillService(null));

        FileUploadService fileUploadService = new FileUploadService(new StandardReactiveWebEnvironment());
        OfferSkillJpaRepository offerSkillJpaRepository = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService2 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository, offerSkillEntityMapper, new OfferEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository2 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO2 = new EmployerRepository(employerJpaRepository2, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository2 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper2 = new OfferEntityMapperImpl();
        OfferRepository offerDAO2 = new OfferRepository(offerJpaRepository2, offerEntityMapper2,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository, new CandidateEntityMapperImpl())));

        CityEntity cityId2 = new CityEntity();
        cityId2.setCandidateResidenceCities(new HashSet<>());
        cityId2.setCityId(1L);
        cityId2.setCityName("Oxford");
        cityId2.setEmployerCities(new HashSet<>());
        cityId2.setOfferCities(new HashSet<>());

        CityEntity cityId3 = new CityEntity();
        cityId3.setCandidateResidenceCities(new HashSet<>());
        cityId3.setCityId(1L);
        cityId3.setCityName("Oxford");
        cityId3.setEmployerCities(new HashSet<>());
        cityId3.setOfferCities(new HashSet<>());

        EmployerEntity employerId = new EmployerEntity();
        employerId.setAmountOfAvailableOffers(10);
        employerId.setCityId(cityId3);
        employerId.setCompanyName("Company Name");
        employerId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId.setDescription("The characteristics of someone or something");
        employerId.setEmailContact("jane.doe@example.org");
        employerId.setEmployerId(1L);
        employerId.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerId.setLogoFilename("foo.txt");
        employerId.setNumberOfEmployees(10);
        employerId.setPhoneNumber("6625550144");
        employerId.setWebsite("Website");

        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setBenefits("Benefits");
        offerEntity.setCityId(cityId2);
        offerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        offerEntity.setDescription("The characteristics of someone or something");
        offerEntity.setEmployerId(employerId);
        offerEntity.setExperienceLevel("Experience Level");
        offerEntity.setOfferId(1L);
        offerEntity.setOfferSkills(new HashSet<>());
        offerEntity.setOfferUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        offerEntity.setOtherSkills("Other Skills");
        offerEntity.setRemoteWork(1);
        offerEntity.setSalaryMax(BigDecimal.valueOf(1L));
        offerEntity.setSalaryMin(BigDecimal.valueOf(1L));
        offerEntity.setStatus("Status");
        offerEntity.setTitle("Dr");
        offerEntity.setYearsOfExperience(1);
        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        when(offerJpaRepository3.findByOfferIdAndEmployerId(Mockito.<Long>any(), Mockito.<EmployerEntity>any()))
                .thenReturn(Optional.of(offerEntity));
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository2 = mock(OfferCriteriaRepository.class);
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository4 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper4 = new OfferEntityMapperImpl();
        OfferRepository offerDAO4 = new OfferRepository(offerJpaRepository4, offerEntityMapper4,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        DataService dataService2 = new DataService(employerDAO3, offerDAO4,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl()));

        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService3 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferSkillJpaRepository offerSkillJpaRepository2 = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper2 = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService3 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository2, offerSkillEntityMapper2, new OfferEntityMapperImpl()));
        OfferService offerService2 = new OfferService(offerDAO3, offerCriteriaRepository2, dataService2, cityService3,
                offerSkillService3,
                new SkillService(new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class))));

        OfferDetailsMapper offerDetailsMapper = mock(OfferDetailsMapper.class);
        when(offerDetailsMapper.map(Mockito.<Offer>any())).thenThrow(new NotFoundException("An error occurred"));
        CityJpaRepository cityJpaRepository3 = mock(CityJpaRepository.class);
        CityService cityService4 = new CityService(new CityRepository(cityJpaRepository3, new CityEntityMapperImpl()));
        CityMapper cityMapper = mock(CityMapper.class);
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        OfferUserController offerUserController = new OfferUserController(userService, employerService, offerService2,
                cityService4, cityMapper, skillService, skillMapper, offerDetailsMapper, new OfferRowMapperImpl(),
                mock(OfferUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        assertThrows(NotFoundException.class, () -> offerUserController.getOfferDetailsToEmployer(1L, model,
                new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userJpaRepository).findByEmail(Mockito.<String>any());
        verify(userEntity).getIsEnabled();
        verify(userEntity).getId();
        verify(userEntity).getEmail();
        verify(userEntity).getPassword();
        verify(userEntity).getUserName();
        verify(userEntity).getUserUuid();
        verify(userEntity).getRoleId();
        verify(employerJpaRepository).findByEmployerUuid(Mockito.<String>any());
        verify(offerJpaRepository3).findByOfferIdAndEmployerId(Mockito.<Long>any(), Mockito.<EmployerEntity>any());
        verify(offerDetailsMapper).map(Mockito.<Offer>any());
    }

    /**
     * Method under test: {@link OfferUserController#getOfferDetailsToEmployer(Long, Model, Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetOfferDetailsToEmployer6() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.OfferUserController.lambda$getOfferDetailsToEmployer$2(OfferUserController.java:74)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferDetailsToEmployer(OfferUserController.java:74)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at java.base/java.util.Objects.requireNonNull(Objects.java:208)
        //       at pl.devfinder.infrastructure.database.repository.UserRepository.findByEmail(UserRepository.java:24)
        //       at pl.devfinder.business.UserService.findByEmail(UserService.java:26)
        //       at pl.devfinder.business.management.Utility.putUserDataToModel(Utility.java:34)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferDetailsToEmployer(OfferUserController.java:73)
        //   See https://diff.blue/R013 to resolve this issue.

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setRole("Role");
        roleEntity.setUserId(new HashSet<>());
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getIsEnabled()).thenReturn(true);
        when(userEntity.getId()).thenReturn(1L);
        when(userEntity.getEmail()).thenReturn("jane.doe@example.org");
        when(userEntity.getPassword()).thenReturn("iloveyou");
        when(userEntity.getUserName()).thenReturn("janedoe");
        when(userEntity.getUserUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userEntity.getRoleId()).thenReturn(roleEntity);
        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        when(userJpaRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(userEntity));
        UserRepository userDAO = new UserRepository(userJpaRepository, null);

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        EmployerEntity employerEntity = new EmployerEntity();
        employerEntity.setAmountOfAvailableOffers(10);
        employerEntity.setCityId(cityId);
        employerEntity.setCompanyName("Company Name");
        employerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerEntity.setDescription("The characteristics of someone or something");
        employerEntity.setEmailContact("jane.doe@example.org");
        employerEntity.setEmployerId(1L);
        employerEntity.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerEntity.setLogoFilename("foo.txt");
        employerEntity.setNumberOfEmployees(10);
        employerEntity.setPhoneNumber("6625550144");
        employerEntity.setWebsite("Website");
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        when(employerJpaRepository.findByEmployerUuid(Mockito.<String>any())).thenReturn(Optional.of(employerEntity));
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService2 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService2,
                offerSkillService, new SkillService(null));

        FileUploadService fileUploadService = new FileUploadService(new StandardReactiveWebEnvironment());
        OfferSkillJpaRepository offerSkillJpaRepository = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService2 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository, offerSkillEntityMapper, new OfferEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository2 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO2 = new EmployerRepository(employerJpaRepository2, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository2 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper2 = new OfferEntityMapperImpl();
        OfferRepository offerDAO2 = new OfferRepository(offerJpaRepository2, offerEntityMapper2,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository, new CandidateEntityMapperImpl())));

        CityEntity cityId2 = new CityEntity();
        cityId2.setCandidateResidenceCities(new HashSet<>());
        cityId2.setCityId(1L);
        cityId2.setCityName("Oxford");
        cityId2.setEmployerCities(new HashSet<>());
        cityId2.setOfferCities(new HashSet<>());

        CityEntity cityId3 = new CityEntity();
        cityId3.setCandidateResidenceCities(new HashSet<>());
        cityId3.setCityId(1L);
        cityId3.setCityName("Oxford");
        cityId3.setEmployerCities(new HashSet<>());
        cityId3.setOfferCities(new HashSet<>());

        EmployerEntity employerId = new EmployerEntity();
        employerId.setAmountOfAvailableOffers(10);
        employerId.setCityId(cityId3);
        employerId.setCompanyName("Company Name");
        employerId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId.setDescription("The characteristics of someone or something");
        employerId.setEmailContact("jane.doe@example.org");
        employerId.setEmployerId(1L);
        employerId.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerId.setLogoFilename("foo.txt");
        employerId.setNumberOfEmployees(10);
        employerId.setPhoneNumber("6625550144");
        employerId.setWebsite("Website");

        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setBenefits("Benefits");
        offerEntity.setCityId(cityId2);
        offerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        offerEntity.setDescription("The characteristics of someone or something");
        offerEntity.setEmployerId(employerId);
        offerEntity.setExperienceLevel("Experience Level");
        offerEntity.setOfferId(1L);
        offerEntity.setOfferSkills(new HashSet<>());
        offerEntity.setOfferUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        offerEntity.setOtherSkills("Other Skills");
        offerEntity.setRemoteWork(1);
        offerEntity.setSalaryMax(BigDecimal.valueOf(1L));
        offerEntity.setSalaryMin(BigDecimal.valueOf(1L));
        offerEntity.setStatus("Status");
        offerEntity.setTitle("Dr");
        offerEntity.setYearsOfExperience(1);
        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        when(offerJpaRepository3.findByOfferIdAndEmployerId(Mockito.<Long>any(), Mockito.<EmployerEntity>any()))
                .thenReturn(Optional.of(offerEntity));
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository2 = mock(OfferCriteriaRepository.class);
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository4 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper4 = new OfferEntityMapperImpl();
        OfferRepository offerDAO4 = new OfferRepository(offerJpaRepository4, offerEntityMapper4,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        DataService dataService2 = new DataService(employerDAO3, offerDAO4,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl()));

        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService3 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferSkillJpaRepository offerSkillJpaRepository2 = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper2 = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService3 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository2, offerSkillEntityMapper2, new OfferEntityMapperImpl()));
        OfferService offerService2 = new OfferService(offerDAO3, offerCriteriaRepository2, dataService2, cityService3,
                offerSkillService3,
                new SkillService(new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class))));

        OfferDetailsMapper offerDetailsMapper = mock(OfferDetailsMapper.class);
        when(offerDetailsMapper.map(Mockito.<Offer>any())).thenReturn(new OfferDetailsDTO());
        CityJpaRepository cityJpaRepository3 = mock(CityJpaRepository.class);
        CityService cityService4 = new CityService(new CityRepository(cityJpaRepository3, new CityEntityMapperImpl()));
        CityMapper cityMapper = mock(CityMapper.class);
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        OfferUserController offerUserController = new OfferUserController(userService, employerService, offerService2,
                cityService4, cityMapper, skillService, skillMapper, offerDetailsMapper, new OfferRowMapperImpl(),
                mock(OfferUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        offerUserController.getOfferDetailsToEmployer(1L, model,
                new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link OfferUserController#getOfferDetailsToEmployer(Long, Model, Authentication)}
     */
    @Test
    void testGetOfferDetailsToEmployer7() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.OfferUserController.lambda$getOfferDetailsToEmployer$2(OfferUserController.java:74)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferDetailsToEmployer(OfferUserController.java:74)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setRole("Role");
        roleEntity.setUserId(new HashSet<>());
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getIsEnabled()).thenReturn(true);
        when(userEntity.getId()).thenReturn(1L);
        when(userEntity.getEmail()).thenReturn("jane.doe@example.org");
        when(userEntity.getPassword()).thenReturn("iloveyou");
        when(userEntity.getUserName()).thenReturn("janedoe");
        when(userEntity.getUserUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userEntity.getRoleId()).thenReturn(roleEntity);
        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        when(userJpaRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(userEntity));
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        CityEntity cityEntity = new CityEntity();
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());
        EmployerEntity employerEntity = mock(EmployerEntity.class);
        when(employerEntity.getAmountOfAvailableOffers()).thenReturn(10);
        when(employerEntity.getNumberOfEmployees()).thenReturn(10);
        when(employerEntity.getEmployerId()).thenReturn(1L);
        when(employerEntity.getCompanyName()).thenReturn("Company Name");
        when(employerEntity.getDescription()).thenReturn("The characteristics of someone or something");
        when(employerEntity.getEmailContact()).thenReturn("jane.doe@example.org");
        when(employerEntity.getEmployerUuid()).thenReturn("foo");
        when(employerEntity.getLogoFilename()).thenReturn("foo.txt");
        when(employerEntity.getPhoneNumber()).thenReturn("6625550144");
        when(employerEntity.getWebsite()).thenReturn("Website");
        when(employerEntity.getCreatedAt())
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        when(employerEntity.getCityId()).thenReturn(cityEntity);
        doNothing().when(employerEntity).setAmountOfAvailableOffers(Mockito.<Integer>any());
        doNothing().when(employerEntity).setCityId(Mockito.<CityEntity>any());
        doNothing().when(employerEntity).setCompanyName(Mockito.<String>any());
        doNothing().when(employerEntity).setCreatedAt(Mockito.<OffsetDateTime>any());
        doNothing().when(employerEntity).setDescription(Mockito.<String>any());
        doNothing().when(employerEntity).setEmailContact(Mockito.<String>any());
        doNothing().when(employerEntity).setEmployerId(Mockito.<Long>any());
        doNothing().when(employerEntity).setEmployerUuid(Mockito.<String>any());
        doNothing().when(employerEntity).setLogoFilename(Mockito.<String>any());
        doNothing().when(employerEntity).setNumberOfEmployees(Mockito.<Integer>any());
        doNothing().when(employerEntity).setPhoneNumber(Mockito.<String>any());
        doNothing().when(employerEntity).setWebsite(Mockito.<String>any());
        employerEntity.setAmountOfAvailableOffers(10);
        employerEntity.setCityId(cityId);
        employerEntity.setCompanyName("Company Name");
        employerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerEntity.setDescription("The characteristics of someone or something");
        employerEntity.setEmailContact("jane.doe@example.org");
        employerEntity.setEmployerId(1L);
        employerEntity.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerEntity.setLogoFilename("foo.txt");
        employerEntity.setNumberOfEmployees(10);
        employerEntity.setPhoneNumber("6625550144");
        employerEntity.setWebsite("Website");
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        when(employerJpaRepository.findByEmployerUuid(Mockito.<String>any())).thenReturn(Optional.of(employerEntity));
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService2 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService2,
                offerSkillService, new SkillService(null));

        FileUploadService fileUploadService = new FileUploadService(new StandardReactiveWebEnvironment());
        OfferSkillJpaRepository offerSkillJpaRepository = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService2 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository, offerSkillEntityMapper, new OfferEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository2 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO2 = new EmployerRepository(employerJpaRepository2, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository2 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper2 = new OfferEntityMapperImpl();
        OfferRepository offerDAO2 = new OfferRepository(offerJpaRepository2, offerEntityMapper2,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository, new CandidateEntityMapperImpl())));

        CityEntity cityId2 = new CityEntity();
        cityId2.setCandidateResidenceCities(new HashSet<>());
        cityId2.setCityId(1L);
        cityId2.setCityName("Oxford");
        cityId2.setEmployerCities(new HashSet<>());
        cityId2.setOfferCities(new HashSet<>());

        CityEntity cityId3 = new CityEntity();
        cityId3.setCandidateResidenceCities(new HashSet<>());
        cityId3.setCityId(1L);
        cityId3.setCityName("Oxford");
        cityId3.setEmployerCities(new HashSet<>());
        cityId3.setOfferCities(new HashSet<>());

        EmployerEntity employerId = new EmployerEntity();
        employerId.setAmountOfAvailableOffers(10);
        employerId.setCityId(cityId3);
        employerId.setCompanyName("Company Name");
        employerId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId.setDescription("The characteristics of someone or something");
        employerId.setEmailContact("jane.doe@example.org");
        employerId.setEmployerId(1L);
        employerId.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerId.setLogoFilename("foo.txt");
        employerId.setNumberOfEmployees(10);
        employerId.setPhoneNumber("6625550144");
        employerId.setWebsite("Website");

        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setBenefits("Benefits");
        offerEntity.setCityId(cityId2);
        offerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        offerEntity.setDescription("The characteristics of someone or something");
        offerEntity.setEmployerId(employerId);
        offerEntity.setExperienceLevel("Experience Level");
        offerEntity.setOfferId(1L);
        offerEntity.setOfferSkills(new HashSet<>());
        offerEntity.setOfferUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        offerEntity.setOtherSkills("Other Skills");
        offerEntity.setRemoteWork(1);
        offerEntity.setSalaryMax(BigDecimal.valueOf(1L));
        offerEntity.setSalaryMin(BigDecimal.valueOf(1L));
        offerEntity.setStatus("Status");
        offerEntity.setTitle("Dr");
        offerEntity.setYearsOfExperience(1);
        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        when(offerJpaRepository3.findByOfferIdAndEmployerId(Mockito.<Long>any(), Mockito.<EmployerEntity>any()))
                .thenReturn(Optional.of(offerEntity));
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository2 = mock(OfferCriteriaRepository.class);
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository4 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper4 = new OfferEntityMapperImpl();
        OfferRepository offerDAO4 = new OfferRepository(offerJpaRepository4, offerEntityMapper4,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        DataService dataService2 = new DataService(employerDAO3, offerDAO4,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl()));

        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService3 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferSkillJpaRepository offerSkillJpaRepository2 = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper2 = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService3 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository2, offerSkillEntityMapper2, new OfferEntityMapperImpl()));
        OfferService offerService2 = new OfferService(offerDAO3, offerCriteriaRepository2, dataService2, cityService3,
                offerSkillService3,
                new SkillService(new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class))));

        OfferDetailsMapper offerDetailsMapper = mock(OfferDetailsMapper.class);
        when(offerDetailsMapper.map(Mockito.<Offer>any())).thenReturn(new OfferDetailsDTO());
        CityJpaRepository cityJpaRepository3 = mock(CityJpaRepository.class);
        CityService cityService4 = new CityService(new CityRepository(cityJpaRepository3, new CityEntityMapperImpl()));
        CityMapper cityMapper = mock(CityMapper.class);
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        OfferUserController offerUserController = new OfferUserController(userService, employerService, offerService2,
                cityService4, cityMapper, skillService, skillMapper, offerDetailsMapper, new OfferRowMapperImpl(),
                mock(OfferUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        assertEquals("employer/offer_details", offerUserController.getOfferDetailsToEmployer(1L, model,
                new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userJpaRepository).findByEmail(Mockito.<String>any());
        verify(userEntity).getIsEnabled();
        verify(userEntity).getId();
        verify(userEntity).getEmail();
        verify(userEntity).getPassword();
        verify(userEntity).getUserName();
        verify(userEntity).getUserUuid();
        verify(userEntity).getRoleId();
        verify(employerJpaRepository).findByEmployerUuid(Mockito.<String>any());
        verify(employerEntity).getAmountOfAvailableOffers();
        verify(employerEntity).getNumberOfEmployees();
        verify(employerEntity).getEmployerId();
        verify(employerEntity).getCompanyName();
        verify(employerEntity).getDescription();
        verify(employerEntity).getEmailContact();
        verify(employerEntity).getEmployerUuid();
        verify(employerEntity).getLogoFilename();
        verify(employerEntity).getPhoneNumber();
        verify(employerEntity).getWebsite();
        verify(employerEntity).getCreatedAt();
        verify(employerEntity).getCityId();
        verify(employerEntity).setAmountOfAvailableOffers(Mockito.<Integer>any());
        verify(employerEntity).setCityId(Mockito.<CityEntity>any());
        verify(employerEntity).setCompanyName(Mockito.<String>any());
        verify(employerEntity).setCreatedAt(Mockito.<OffsetDateTime>any());
        verify(employerEntity).setDescription(Mockito.<String>any());
        verify(employerEntity).setEmailContact(Mockito.<String>any());
        verify(employerEntity).setEmployerId(Mockito.<Long>any());
        verify(employerEntity).setEmployerUuid(Mockito.<String>any());
        verify(employerEntity).setLogoFilename(Mockito.<String>any());
        verify(employerEntity).setNumberOfEmployees(Mockito.<Integer>any());
        verify(employerEntity).setPhoneNumber(Mockito.<String>any());
        verify(employerEntity).setWebsite(Mockito.<String>any());
        verify(offerJpaRepository3).findByOfferIdAndEmployerId(Mockito.<Long>any(), Mockito.<EmployerEntity>any());
        verify(offerDetailsMapper).map(Mockito.<Offer>any());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", ((User) model.get("user")).getUserUuid());
        assertEquals("janedoe", ((User) model.get("user")).getUserName());
        assertEquals("iloveyou", ((User) model.get("user")).getPassword());
        assertEquals("jane.doe@example.org", ((User) model.get("user")).getEmail());
        assertTrue(((User) model.get("user")).getIsEnabled());
        assertEquals(1L, ((User) model.get("user")).getId().longValue());
        Role role = ((User) model.get("user")).getRole();
        assertNull(role.getUserId());
        assertEquals(1L, role.getId().longValue());
        assertEquals("Role", role.getRole());
    }

    /**
     * Method under test: {@link OfferUserController#getOfferDetailsToEmployer(Long, Model, Authentication)}
     */
    @Test
    void testGetOfferDetailsToEmployer8() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.OfferUserController.lambda$getOfferDetailsToEmployer$2(OfferUserController.java:74)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferDetailsToEmployer(OfferUserController.java:74)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setRole("Role");
        roleEntity.setUserId(new HashSet<>());
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getIsEnabled()).thenReturn(true);
        when(userEntity.getId()).thenReturn(1L);
        when(userEntity.getEmail()).thenReturn("jane.doe@example.org");
        when(userEntity.getPassword()).thenReturn("iloveyou");
        when(userEntity.getUserName()).thenReturn("janedoe");
        when(userEntity.getUserUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userEntity.getRoleId()).thenReturn(roleEntity);
        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        when(userJpaRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(userEntity));
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        when(employerJpaRepository.findByEmployerUuid(Mockito.<String>any())).thenReturn(Optional.empty());
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService2 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService2,
                offerSkillService, new SkillService(null));

        FileUploadService fileUploadService = new FileUploadService(new StandardReactiveWebEnvironment());
        OfferSkillJpaRepository offerSkillJpaRepository = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService2 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository, offerSkillEntityMapper, new OfferEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository2 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO2 = new EmployerRepository(employerJpaRepository2, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository2 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper2 = new OfferEntityMapperImpl();
        OfferRepository offerDAO2 = new OfferRepository(offerJpaRepository2, offerEntityMapper2,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository, new CandidateEntityMapperImpl())));

        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository2 = mock(OfferCriteriaRepository.class);
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository4 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper4 = new OfferEntityMapperImpl();
        OfferRepository offerDAO4 = new OfferRepository(offerJpaRepository4, offerEntityMapper4,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        DataService dataService2 = new DataService(employerDAO3, offerDAO4,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl()));

        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService3 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferSkillJpaRepository offerSkillJpaRepository2 = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper2 = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService3 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository2, offerSkillEntityMapper2, new OfferEntityMapperImpl()));
        OfferService offerService2 = new OfferService(offerDAO3, offerCriteriaRepository2, dataService2, cityService3,
                offerSkillService3,
                new SkillService(new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class))));

        CityJpaRepository cityJpaRepository3 = mock(CityJpaRepository.class);
        CityService cityService4 = new CityService(new CityRepository(cityJpaRepository3, new CityEntityMapperImpl()));
        CityMapper cityMapper = mock(CityMapper.class);
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        OfferDetailsMapper offerDetailsMapper = mock(OfferDetailsMapper.class);
        OfferUserController offerUserController = new OfferUserController(userService, employerService, offerService2,
                cityService4, cityMapper, skillService, skillMapper, offerDetailsMapper, new OfferRowMapperImpl(),
                mock(OfferUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        assertThrows(NotFoundException.class, () -> offerUserController.getOfferDetailsToEmployer(1L, model,
                new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userJpaRepository).findByEmail(Mockito.<String>any());
        verify(userEntity).getIsEnabled();
        verify(userEntity).getId();
        verify(userEntity).getEmail();
        verify(userEntity).getPassword();
        verify(userEntity).getUserName();
        verify(userEntity).getUserUuid();
        verify(userEntity).getRoleId();
        verify(employerJpaRepository).findByEmployerUuid(Mockito.<String>any());
    }

    /**
     * Method under test: {@link OfferUserController#getOfferDetailsToEmployer(Long, Model, Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetOfferDetailsToEmployer9() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.OfferUserController.lambda$getOfferDetailsToEmployer$2(OfferUserController.java:74)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferDetailsToEmployer(OfferUserController.java:74)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at java.base/java.util.Objects.requireNonNull(Objects.java:208)
        //       at pl.devfinder.infrastructure.database.repository.EmployerRepository.findByEmployerUuid(EmployerRepository.java:42)
        //       at pl.devfinder.business.EmployerService.findByEmployerUuid(EmployerService.java:60)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferDetailsToEmployer(OfferUserController.java:75)
        //   See https://diff.blue/R013 to resolve this issue.

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setRole("Role");
        roleEntity.setUserId(new HashSet<>());
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getIsEnabled()).thenReturn(true);
        when(userEntity.getId()).thenReturn(1L);
        when(userEntity.getEmail()).thenReturn("jane.doe@example.org");
        when(userEntity.getPassword()).thenReturn("iloveyou");
        when(userEntity.getUserName()).thenReturn("janedoe");
        when(userEntity.getUserUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userEntity.getRoleId()).thenReturn(roleEntity);
        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        when(userJpaRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(userEntity));
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        CityEntity cityEntity = new CityEntity();
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());
        EmployerEntity employerEntity = mock(EmployerEntity.class);
        when(employerEntity.getAmountOfAvailableOffers()).thenReturn(10);
        when(employerEntity.getNumberOfEmployees()).thenReturn(10);
        when(employerEntity.getEmployerId()).thenReturn(1L);
        when(employerEntity.getCompanyName()).thenReturn("Company Name");
        when(employerEntity.getDescription()).thenReturn("The characteristics of someone or something");
        when(employerEntity.getEmailContact()).thenReturn("jane.doe@example.org");
        when(employerEntity.getEmployerUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(employerEntity.getLogoFilename()).thenReturn("foo.txt");
        when(employerEntity.getPhoneNumber()).thenReturn("6625550144");
        when(employerEntity.getWebsite()).thenReturn("Website");
        when(employerEntity.getCreatedAt())
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        when(employerEntity.getCityId()).thenReturn(cityEntity);
        doNothing().when(employerEntity).setAmountOfAvailableOffers(Mockito.<Integer>any());
        doNothing().when(employerEntity).setCityId(Mockito.<CityEntity>any());
        doNothing().when(employerEntity).setCompanyName(Mockito.<String>any());
        doNothing().when(employerEntity).setCreatedAt(Mockito.<OffsetDateTime>any());
        doNothing().when(employerEntity).setDescription(Mockito.<String>any());
        doNothing().when(employerEntity).setEmailContact(Mockito.<String>any());
        doNothing().when(employerEntity).setEmployerId(Mockito.<Long>any());
        doNothing().when(employerEntity).setEmployerUuid(Mockito.<String>any());
        doNothing().when(employerEntity).setLogoFilename(Mockito.<String>any());
        doNothing().when(employerEntity).setNumberOfEmployees(Mockito.<Integer>any());
        doNothing().when(employerEntity).setPhoneNumber(Mockito.<String>any());
        doNothing().when(employerEntity).setWebsite(Mockito.<String>any());
        employerEntity.setAmountOfAvailableOffers(10);
        employerEntity.setCityId(cityId);
        employerEntity.setCompanyName("Company Name");
        employerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerEntity.setDescription("The characteristics of someone or something");
        employerEntity.setEmailContact("jane.doe@example.org");
        employerEntity.setEmployerId(1L);
        employerEntity.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerEntity.setLogoFilename("foo.txt");
        employerEntity.setNumberOfEmployees(10);
        employerEntity.setPhoneNumber("6625550144");
        employerEntity.setWebsite("Website");
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        when(employerJpaRepository.findByEmployerUuid(Mockito.<String>any())).thenReturn(Optional.of(employerEntity));
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, null);

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService2 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService2,
                offerSkillService, new SkillService(null));

        FileUploadService fileUploadService = new FileUploadService(new StandardReactiveWebEnvironment());
        OfferSkillJpaRepository offerSkillJpaRepository = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService2 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository, offerSkillEntityMapper, new OfferEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository2 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO2 = new EmployerRepository(employerJpaRepository2, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository2 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper2 = new OfferEntityMapperImpl();
        OfferRepository offerDAO2 = new OfferRepository(offerJpaRepository2, offerEntityMapper2,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository, new CandidateEntityMapperImpl())));

        CityEntity cityId2 = new CityEntity();
        cityId2.setCandidateResidenceCities(new HashSet<>());
        cityId2.setCityId(1L);
        cityId2.setCityName("Oxford");
        cityId2.setEmployerCities(new HashSet<>());
        cityId2.setOfferCities(new HashSet<>());

        CityEntity cityId3 = new CityEntity();
        cityId3.setCandidateResidenceCities(new HashSet<>());
        cityId3.setCityId(1L);
        cityId3.setCityName("Oxford");
        cityId3.setEmployerCities(new HashSet<>());
        cityId3.setOfferCities(new HashSet<>());

        EmployerEntity employerId = new EmployerEntity();
        employerId.setAmountOfAvailableOffers(10);
        employerId.setCityId(cityId3);
        employerId.setCompanyName("Company Name");
        employerId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId.setDescription("The characteristics of someone or something");
        employerId.setEmailContact("jane.doe@example.org");
        employerId.setEmployerId(1L);
        employerId.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerId.setLogoFilename("foo.txt");
        employerId.setNumberOfEmployees(10);
        employerId.setPhoneNumber("6625550144");
        employerId.setWebsite("Website");

        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setBenefits("Benefits");
        offerEntity.setCityId(cityId2);
        offerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        offerEntity.setDescription("The characteristics of someone or something");
        offerEntity.setEmployerId(employerId);
        offerEntity.setExperienceLevel("Experience Level");
        offerEntity.setOfferId(1L);
        offerEntity.setOfferSkills(new HashSet<>());
        offerEntity.setOfferUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        offerEntity.setOtherSkills("Other Skills");
        offerEntity.setRemoteWork(1);
        offerEntity.setSalaryMax(BigDecimal.valueOf(1L));
        offerEntity.setSalaryMin(BigDecimal.valueOf(1L));
        offerEntity.setStatus("Status");
        offerEntity.setTitle("Dr");
        offerEntity.setYearsOfExperience(1);
        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        when(offerJpaRepository3.findByOfferIdAndEmployerId(Mockito.<Long>any(), Mockito.<EmployerEntity>any()))
                .thenReturn(Optional.of(offerEntity));
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository2 = mock(OfferCriteriaRepository.class);
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository4 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper4 = new OfferEntityMapperImpl();
        OfferRepository offerDAO4 = new OfferRepository(offerJpaRepository4, offerEntityMapper4,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        DataService dataService2 = new DataService(employerDAO3, offerDAO4,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl()));

        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService3 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferSkillJpaRepository offerSkillJpaRepository2 = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper2 = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService3 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository2, offerSkillEntityMapper2, new OfferEntityMapperImpl()));
        OfferService offerService2 = new OfferService(offerDAO3, offerCriteriaRepository2, dataService2, cityService3,
                offerSkillService3,
                new SkillService(new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class))));

        OfferDetailsMapper offerDetailsMapper = mock(OfferDetailsMapper.class);
        when(offerDetailsMapper.map(Mockito.<Offer>any())).thenReturn(new OfferDetailsDTO());
        CityJpaRepository cityJpaRepository3 = mock(CityJpaRepository.class);
        CityService cityService4 = new CityService(new CityRepository(cityJpaRepository3, new CityEntityMapperImpl()));
        CityMapper cityMapper = mock(CityMapper.class);
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        OfferUserController offerUserController = new OfferUserController(userService, employerService, offerService2,
                cityService4, cityMapper, skillService, skillMapper, offerDetailsMapper, new OfferRowMapperImpl(),
                mock(OfferUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        offerUserController.getOfferDetailsToEmployer(1L, model,
                new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link OfferUserController#getOfferEditForm(Long, Model, Authentication)}
     */
    @Test
    void testGetOfferEditForm() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.OfferUserController.lambda$getOfferEditForm$4(OfferUserController.java:90)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferEditForm(OfferUserController.java:90)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService2 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService2,
                offerSkillService, new SkillService(null));

        FileUploadService fileUploadService = new FileUploadService(new StandardReactiveWebEnvironment());
        OfferSkillJpaRepository offerSkillJpaRepository = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService2 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository, offerSkillEntityMapper, new OfferEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository2 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO2 = new EmployerRepository(employerJpaRepository2, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository2 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper2 = new OfferEntityMapperImpl();
        OfferRepository offerDAO2 = new OfferRepository(offerJpaRepository2, offerEntityMapper2,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository, new CandidateEntityMapperImpl())));

        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository2 = mock(OfferCriteriaRepository.class);
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository4 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper4 = new OfferEntityMapperImpl();
        OfferRepository offerDAO4 = new OfferRepository(offerJpaRepository4, offerEntityMapper4,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        DataService dataService2 = new DataService(employerDAO3, offerDAO4,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl()));

        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService3 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferSkillJpaRepository offerSkillJpaRepository2 = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper2 = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService3 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository2, offerSkillEntityMapper2, new OfferEntityMapperImpl()));
        OfferService offerService2 = new OfferService(offerDAO3, offerCriteriaRepository2, dataService2, cityService3,
                offerSkillService3,
                new SkillService(new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class))));

        CityJpaRepository cityJpaRepository3 = mock(CityJpaRepository.class);
        CityService cityService4 = new CityService(new CityRepository(cityJpaRepository3, new CityEntityMapperImpl()));
        CityMapper cityMapper = mock(CityMapper.class);
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        OfferDetailsMapper offerDetailsMapper = mock(OfferDetailsMapper.class);
        OfferUserController offerUserController = new OfferUserController(userService, employerService, offerService2,
                cityService4, cityMapper, skillService, skillMapper, offerDetailsMapper, new OfferRowMapperImpl(),
                mock(OfferUpdateRequestMapper.class));
        assertThrows(NotFoundException.class,
                () -> offerUserController.getOfferEditForm(1L, new ConcurrentModel(), null));
    }

    /**
     * Method under test: {@link OfferUserController#getOfferEditForm(Long, Model, Authentication)}
     */
    @Test
    void testGetOfferEditForm2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.OfferUserController.lambda$getOfferEditForm$4(OfferUserController.java:90)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferEditForm(OfferUserController.java:90)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        UserService userService = mock(UserService.class);
        when(userService.findByEmail(Mockito.<String>any())).thenReturn(Optional.empty());
        OfferUserController offerUserController = new OfferUserController(userService, mock(EmployerService.class),
                mock(OfferService.class), mock(CityService.class), mock(CityMapper.class), mock(SkillService.class),
                mock(SkillMapper.class), mock(OfferDetailsMapper.class), mock(OfferRowMapper.class),
                mock(OfferUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        assertThrows(NotFoundException.class, () -> offerUserController.getOfferEditForm(1L, model,
                new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userService).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link OfferUserController#getOfferEditForm(Long, Model, Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetOfferEditForm3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.OfferUserController.lambda$getOfferEditForm$4(OfferUserController.java:90)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferEditForm(OfferUserController.java:90)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   pl.devfinder.domain.exception.NotFoundException: An error occurred
        //       at pl.devfinder.business.management.Utility.putUserDataToModel(Utility.java:34)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferEditForm(OfferUserController.java:89)
        //   See https://diff.blue/R013 to resolve this issue.

        UserService userService = mock(UserService.class);
        when(userService.findByEmail(Mockito.<String>any())).thenThrow(new NotFoundException("An error occurred"));
        OfferUserController offerUserController = new OfferUserController(userService, mock(EmployerService.class),
                mock(OfferService.class), mock(CityService.class), mock(CityMapper.class), mock(SkillService.class),
                mock(SkillMapper.class), mock(OfferDetailsMapper.class), mock(OfferRowMapper.class),
                mock(OfferUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        offerUserController.getOfferEditForm(1L, model, new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link OfferUserController#getOfferEditForm(Long, Model, Authentication)}
     */
    @Test
    void testGetOfferEditForm4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.OfferUserController.lambda$getOfferEditForm$4(OfferUserController.java:90)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferEditForm(OfferUserController.java:90)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setRole("Role");
        roleEntity.setUserId(new HashSet<>());
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getIsEnabled()).thenReturn(true);
        when(userEntity.getId()).thenReturn(1L);
        when(userEntity.getEmail()).thenReturn("jane.doe@example.org");
        when(userEntity.getPassword()).thenReturn("iloveyou");
        when(userEntity.getUserName()).thenReturn("janedoe");
        when(userEntity.getUserUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userEntity.getRoleId()).thenReturn(roleEntity);
        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        when(userJpaRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(userEntity));
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        EmployerEntity employerEntity = new EmployerEntity();
        employerEntity.setAmountOfAvailableOffers(10);
        employerEntity.setCityId(cityId);
        employerEntity.setCompanyName("Company Name");
        employerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerEntity.setDescription("The characteristics of someone or something");
        employerEntity.setEmailContact("jane.doe@example.org");
        employerEntity.setEmployerId(1L);
        employerEntity.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerEntity.setLogoFilename("foo.txt");
        employerEntity.setNumberOfEmployees(10);
        employerEntity.setPhoneNumber("6625550144");
        employerEntity.setWebsite("Website");
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        when(employerJpaRepository.findByEmployerUuid(Mockito.<String>any())).thenReturn(Optional.of(employerEntity));
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService2 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService2,
                offerSkillService, new SkillService(null));

        FileUploadService fileUploadService = new FileUploadService(new StandardReactiveWebEnvironment());
        OfferSkillJpaRepository offerSkillJpaRepository = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService2 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository, offerSkillEntityMapper, new OfferEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository2 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO2 = new EmployerRepository(employerJpaRepository2, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository2 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper2 = new OfferEntityMapperImpl();
        OfferRepository offerDAO2 = new OfferRepository(offerJpaRepository2, offerEntityMapper2,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository, new CandidateEntityMapperImpl())));

        CityEntity cityId2 = new CityEntity();
        cityId2.setCandidateResidenceCities(new HashSet<>());
        cityId2.setCityId(1L);
        cityId2.setCityName("Oxford");
        cityId2.setEmployerCities(new HashSet<>());
        cityId2.setOfferCities(new HashSet<>());

        CityEntity cityId3 = new CityEntity();
        cityId3.setCandidateResidenceCities(new HashSet<>());
        cityId3.setCityId(1L);
        cityId3.setCityName("Oxford");
        cityId3.setEmployerCities(new HashSet<>());
        cityId3.setOfferCities(new HashSet<>());

        EmployerEntity employerId = new EmployerEntity();
        employerId.setAmountOfAvailableOffers(10);
        employerId.setCityId(cityId3);
        employerId.setCompanyName("Company Name");
        employerId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId.setDescription("The characteristics of someone or something");
        employerId.setEmailContact("jane.doe@example.org");
        employerId.setEmployerId(1L);
        employerId.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerId.setLogoFilename("foo.txt");
        employerId.setNumberOfEmployees(10);
        employerId.setPhoneNumber("6625550144");
        employerId.setWebsite("Website");

        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setBenefits("Benefits");
        offerEntity.setCityId(cityId2);
        offerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        offerEntity.setDescription("The characteristics of someone or something");
        offerEntity.setEmployerId(employerId);
        offerEntity.setExperienceLevel("Experience Level");
        offerEntity.setOfferId(1L);
        offerEntity.setOfferSkills(new HashSet<>());
        offerEntity.setOfferUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        offerEntity.setOtherSkills("Other Skills");
        offerEntity.setRemoteWork(1);
        offerEntity.setSalaryMax(BigDecimal.valueOf(1L));
        offerEntity.setSalaryMin(BigDecimal.valueOf(1L));
        offerEntity.setStatus("Status");
        offerEntity.setTitle("Dr");
        offerEntity.setYearsOfExperience(1);
        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        when(offerJpaRepository3.findByOfferIdAndEmployerId(Mockito.<Long>any(), Mockito.<EmployerEntity>any()))
                .thenReturn(Optional.of(offerEntity));
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository2 = mock(OfferCriteriaRepository.class);
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository4 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper4 = new OfferEntityMapperImpl();
        OfferRepository offerDAO4 = new OfferRepository(offerJpaRepository4, offerEntityMapper4,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        DataService dataService2 = new DataService(employerDAO3, offerDAO4,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl()));

        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService3 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferSkillJpaRepository offerSkillJpaRepository2 = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper2 = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService3 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository2, offerSkillEntityMapper2, new OfferEntityMapperImpl()));
        OfferService offerService2 = new OfferService(offerDAO3, offerCriteriaRepository2, dataService2, cityService3,
                offerSkillService3,
                new SkillService(new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class))));

        SkillJpaRepository skillJpaRepository = mock(SkillJpaRepository.class);
        when(skillJpaRepository.findAll()).thenReturn(new ArrayList<>());
        SkillService skillService = new SkillService(
                new SkillRepository(skillJpaRepository, mock(SkillEntityMapper.class)));
        OfferDetailsMapper offerDetailsMapper = mock(OfferDetailsMapper.class);
        when(offerDetailsMapper.map(Mockito.<Offer>any())).thenReturn(new OfferDetailsDTO());
        CityJpaRepository cityJpaRepository3 = mock(CityJpaRepository.class);
        CityService cityService4 = new CityService(new CityRepository(cityJpaRepository3, new CityEntityMapperImpl()));
        CityMapper cityMapper = mock(CityMapper.class);
        SkillMapper skillMapper = mock(SkillMapper.class);
        OfferUserController offerUserController = new OfferUserController(userService, employerService, offerService2,
                cityService4, cityMapper, skillService, skillMapper, offerDetailsMapper, new OfferRowMapperImpl(),
                mock(OfferUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        assertEquals("employer/edit_offer",
                offerUserController.getOfferEditForm(1L, model, new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userJpaRepository).findByEmail(Mockito.<String>any());
        verify(userEntity).getIsEnabled();
        verify(userEntity).getId();
        verify(userEntity).getEmail();
        verify(userEntity).getPassword();
        verify(userEntity).getUserName();
        verify(userEntity).getUserUuid();
        verify(userEntity).getRoleId();
        verify(employerJpaRepository).findByEmployerUuid(Mockito.<String>any());
        verify(offerJpaRepository3).findByOfferIdAndEmployerId(Mockito.<Long>any(), Mockito.<EmployerEntity>any());
        verify(skillJpaRepository).findAll();
        verify(offerDetailsMapper).map(Mockito.<Offer>any());
        assertTrue(((User) model.get("user")).getIsEnabled());
        assertEquals(1L, ((User) model.get("user")).getId().longValue());
        assertEquals("jane.doe@example.org", ((User) model.get("user")).getEmail());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", ((User) model.get("user")).getUserUuid());
        assertEquals("janedoe", ((User) model.get("user")).getUserName());
        assertEquals("iloveyou", ((User) model.get("user")).getPassword());
        Role role = ((User) model.get("user")).getRole();
        assertEquals(1L, role.getId().longValue());
        assertEquals("Role", role.getRole());
        assertNull(role.getUserId());
    }

    /**
     * Method under test: {@link OfferUserController#getOfferEditForm(Long, Model, Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetOfferEditForm5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.OfferUserController.lambda$getOfferEditForm$4(OfferUserController.java:90)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferEditForm(OfferUserController.java:90)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   pl.devfinder.domain.exception.NotFoundException: An error occurred
        //       at pl.devfinder.infrastructure.database.repository.SkillRepository.findAll(SkillRepository.java:23)
        //       at pl.devfinder.business.SkillService.findAll(SkillService.java:19)
        //       at pl.devfinder.api.controller.OfferUserController.prepareModelToEditOrNewOffer(OfferUserController.java:205)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferEditForm(OfferUserController.java:96)
        //   See https://diff.blue/R013 to resolve this issue.

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setRole("Role");
        roleEntity.setUserId(new HashSet<>());
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getIsEnabled()).thenReturn(true);
        when(userEntity.getId()).thenReturn(1L);
        when(userEntity.getEmail()).thenReturn("jane.doe@example.org");
        when(userEntity.getPassword()).thenReturn("iloveyou");
        when(userEntity.getUserName()).thenReturn("janedoe");
        when(userEntity.getUserUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userEntity.getRoleId()).thenReturn(roleEntity);
        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        when(userJpaRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(userEntity));
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        EmployerEntity employerEntity = new EmployerEntity();
        employerEntity.setAmountOfAvailableOffers(10);
        employerEntity.setCityId(cityId);
        employerEntity.setCompanyName("Company Name");
        employerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerEntity.setDescription("The characteristics of someone or something");
        employerEntity.setEmailContact("jane.doe@example.org");
        employerEntity.setEmployerId(1L);
        employerEntity.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerEntity.setLogoFilename("foo.txt");
        employerEntity.setNumberOfEmployees(10);
        employerEntity.setPhoneNumber("6625550144");
        employerEntity.setWebsite("Website");
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        when(employerJpaRepository.findByEmployerUuid(Mockito.<String>any())).thenReturn(Optional.of(employerEntity));
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService2 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService2,
                offerSkillService, new SkillService(null));

        FileUploadService fileUploadService = new FileUploadService(new StandardReactiveWebEnvironment());
        OfferSkillJpaRepository offerSkillJpaRepository = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService2 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository, offerSkillEntityMapper, new OfferEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository2 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO2 = new EmployerRepository(employerJpaRepository2, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository2 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper2 = new OfferEntityMapperImpl();
        OfferRepository offerDAO2 = new OfferRepository(offerJpaRepository2, offerEntityMapper2,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository, new CandidateEntityMapperImpl())));

        CityEntity cityId2 = new CityEntity();
        cityId2.setCandidateResidenceCities(new HashSet<>());
        cityId2.setCityId(1L);
        cityId2.setCityName("Oxford");
        cityId2.setEmployerCities(new HashSet<>());
        cityId2.setOfferCities(new HashSet<>());

        CityEntity cityId3 = new CityEntity();
        cityId3.setCandidateResidenceCities(new HashSet<>());
        cityId3.setCityId(1L);
        cityId3.setCityName("Oxford");
        cityId3.setEmployerCities(new HashSet<>());
        cityId3.setOfferCities(new HashSet<>());

        EmployerEntity employerId = new EmployerEntity();
        employerId.setAmountOfAvailableOffers(10);
        employerId.setCityId(cityId3);
        employerId.setCompanyName("Company Name");
        employerId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId.setDescription("The characteristics of someone or something");
        employerId.setEmailContact("jane.doe@example.org");
        employerId.setEmployerId(1L);
        employerId.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerId.setLogoFilename("foo.txt");
        employerId.setNumberOfEmployees(10);
        employerId.setPhoneNumber("6625550144");
        employerId.setWebsite("Website");

        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setBenefits("Benefits");
        offerEntity.setCityId(cityId2);
        offerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        offerEntity.setDescription("The characteristics of someone or something");
        offerEntity.setEmployerId(employerId);
        offerEntity.setExperienceLevel("Experience Level");
        offerEntity.setOfferId(1L);
        offerEntity.setOfferSkills(new HashSet<>());
        offerEntity.setOfferUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        offerEntity.setOtherSkills("Other Skills");
        offerEntity.setRemoteWork(1);
        offerEntity.setSalaryMax(BigDecimal.valueOf(1L));
        offerEntity.setSalaryMin(BigDecimal.valueOf(1L));
        offerEntity.setStatus("Status");
        offerEntity.setTitle("Dr");
        offerEntity.setYearsOfExperience(1);
        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        when(offerJpaRepository3.findByOfferIdAndEmployerId(Mockito.<Long>any(), Mockito.<EmployerEntity>any()))
                .thenReturn(Optional.of(offerEntity));
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository2 = mock(OfferCriteriaRepository.class);
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository4 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper4 = new OfferEntityMapperImpl();
        OfferRepository offerDAO4 = new OfferRepository(offerJpaRepository4, offerEntityMapper4,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        DataService dataService2 = new DataService(employerDAO3, offerDAO4,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl()));

        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService3 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferSkillJpaRepository offerSkillJpaRepository2 = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper2 = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService3 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository2, offerSkillEntityMapper2, new OfferEntityMapperImpl()));
        OfferService offerService2 = new OfferService(offerDAO3, offerCriteriaRepository2, dataService2, cityService3,
                offerSkillService3,
                new SkillService(new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class))));

        SkillJpaRepository skillJpaRepository = mock(SkillJpaRepository.class);
        when(skillJpaRepository.findAll()).thenThrow(new NotFoundException("An error occurred"));
        SkillService skillService = new SkillService(
                new SkillRepository(skillJpaRepository, mock(SkillEntityMapper.class)));
        OfferDetailsMapper offerDetailsMapper = mock(OfferDetailsMapper.class);
        when(offerDetailsMapper.map(Mockito.<Offer>any())).thenReturn(new OfferDetailsDTO());
        CityJpaRepository cityJpaRepository3 = mock(CityJpaRepository.class);
        CityService cityService4 = new CityService(new CityRepository(cityJpaRepository3, new CityEntityMapperImpl()));
        CityMapper cityMapper = mock(CityMapper.class);
        SkillMapper skillMapper = mock(SkillMapper.class);
        OfferUserController offerUserController = new OfferUserController(userService, employerService, offerService2,
                cityService4, cityMapper, skillService, skillMapper, offerDetailsMapper, new OfferRowMapperImpl(),
                mock(OfferUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        offerUserController.getOfferEditForm(1L, model, new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link OfferUserController#getOfferEditForm(Long, Model, Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetOfferEditForm6() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.OfferUserController.lambda$getOfferEditForm$4(OfferUserController.java:90)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferEditForm(OfferUserController.java:90)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.map(java.util.function.Function)" because the return value of "pl.devfinder.infrastructure.database.repository.jpa.UserJpaRepository.findByEmail(String)" is null
        //       at pl.devfinder.infrastructure.database.repository.UserRepository.findByEmail(UserRepository.java:24)
        //       at pl.devfinder.business.UserService.findByEmail(UserService.java:26)
        //       at pl.devfinder.business.management.Utility.putUserDataToModel(Utility.java:34)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferEditForm(OfferUserController.java:89)
        //   See https://diff.blue/R013 to resolve this issue.

        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        when(userJpaRepository.findByEmail(Mockito.<String>any())).thenReturn(null);
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        EmployerEntity employerEntity = new EmployerEntity();
        employerEntity.setAmountOfAvailableOffers(10);
        employerEntity.setCityId(cityId);
        employerEntity.setCompanyName("Company Name");
        employerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerEntity.setDescription("The characteristics of someone or something");
        employerEntity.setEmailContact("jane.doe@example.org");
        employerEntity.setEmployerId(1L);
        employerEntity.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerEntity.setLogoFilename("foo.txt");
        employerEntity.setNumberOfEmployees(10);
        employerEntity.setPhoneNumber("6625550144");
        employerEntity.setWebsite("Website");
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        when(employerJpaRepository.findByEmployerUuid(Mockito.<String>any())).thenReturn(Optional.of(employerEntity));
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService2 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService2,
                offerSkillService, new SkillService(null));

        FileUploadService fileUploadService = new FileUploadService(new StandardReactiveWebEnvironment());
        OfferSkillJpaRepository offerSkillJpaRepository = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService2 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository, offerSkillEntityMapper, new OfferEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository2 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO2 = new EmployerRepository(employerJpaRepository2, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository2 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper2 = new OfferEntityMapperImpl();
        OfferRepository offerDAO2 = new OfferRepository(offerJpaRepository2, offerEntityMapper2,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository, new CandidateEntityMapperImpl())));

        CityEntity cityId2 = new CityEntity();
        cityId2.setCandidateResidenceCities(new HashSet<>());
        cityId2.setCityId(1L);
        cityId2.setCityName("Oxford");
        cityId2.setEmployerCities(new HashSet<>());
        cityId2.setOfferCities(new HashSet<>());

        CityEntity cityId3 = new CityEntity();
        cityId3.setCandidateResidenceCities(new HashSet<>());
        cityId3.setCityId(1L);
        cityId3.setCityName("Oxford");
        cityId3.setEmployerCities(new HashSet<>());
        cityId3.setOfferCities(new HashSet<>());

        EmployerEntity employerId = new EmployerEntity();
        employerId.setAmountOfAvailableOffers(10);
        employerId.setCityId(cityId3);
        employerId.setCompanyName("Company Name");
        employerId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId.setDescription("The characteristics of someone or something");
        employerId.setEmailContact("jane.doe@example.org");
        employerId.setEmployerId(1L);
        employerId.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerId.setLogoFilename("foo.txt");
        employerId.setNumberOfEmployees(10);
        employerId.setPhoneNumber("6625550144");
        employerId.setWebsite("Website");

        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setBenefits("Benefits");
        offerEntity.setCityId(cityId2);
        offerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        offerEntity.setDescription("The characteristics of someone or something");
        offerEntity.setEmployerId(employerId);
        offerEntity.setExperienceLevel("Experience Level");
        offerEntity.setOfferId(1L);
        offerEntity.setOfferSkills(new HashSet<>());
        offerEntity.setOfferUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        offerEntity.setOtherSkills("Other Skills");
        offerEntity.setRemoteWork(1);
        offerEntity.setSalaryMax(BigDecimal.valueOf(1L));
        offerEntity.setSalaryMin(BigDecimal.valueOf(1L));
        offerEntity.setStatus("Status");
        offerEntity.setTitle("Dr");
        offerEntity.setYearsOfExperience(1);
        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        when(offerJpaRepository3.findByOfferIdAndEmployerId(Mockito.<Long>any(), Mockito.<EmployerEntity>any()))
                .thenReturn(Optional.of(offerEntity));
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository2 = mock(OfferCriteriaRepository.class);
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository4 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper4 = new OfferEntityMapperImpl();
        OfferRepository offerDAO4 = new OfferRepository(offerJpaRepository4, offerEntityMapper4,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        DataService dataService2 = new DataService(employerDAO3, offerDAO4,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl()));

        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService3 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferSkillJpaRepository offerSkillJpaRepository2 = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper2 = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService3 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository2, offerSkillEntityMapper2, new OfferEntityMapperImpl()));
        OfferService offerService2 = new OfferService(offerDAO3, offerCriteriaRepository2, dataService2, cityService3,
                offerSkillService3,
                new SkillService(new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class))));

        SkillJpaRepository skillJpaRepository = mock(SkillJpaRepository.class);
        when(skillJpaRepository.findAll()).thenReturn(new ArrayList<>());
        SkillService skillService = new SkillService(
                new SkillRepository(skillJpaRepository, mock(SkillEntityMapper.class)));
        OfferDetailsMapper offerDetailsMapper = mock(OfferDetailsMapper.class);
        when(offerDetailsMapper.map(Mockito.<Offer>any())).thenReturn(new OfferDetailsDTO());
        CityJpaRepository cityJpaRepository3 = mock(CityJpaRepository.class);
        CityService cityService4 = new CityService(new CityRepository(cityJpaRepository3, new CityEntityMapperImpl()));
        CityMapper cityMapper = mock(CityMapper.class);
        SkillMapper skillMapper = mock(SkillMapper.class);
        OfferUserController offerUserController = new OfferUserController(userService, employerService, offerService2,
                cityService4, cityMapper, skillService, skillMapper, offerDetailsMapper, new OfferRowMapperImpl(),
                mock(OfferUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        offerUserController.getOfferEditForm(1L, model, new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link OfferUserController#getOfferEditForm(Long, Model, Authentication)}
     */
    @Test
    void testGetOfferEditForm7() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.OfferUserController.lambda$getOfferEditForm$4(OfferUserController.java:90)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferEditForm(OfferUserController.java:90)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setRole("Role");
        roleEntity.setUserId(new HashSet<>());
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getIsEnabled()).thenReturn(true);
        when(userEntity.getId()).thenReturn(1L);
        when(userEntity.getEmail()).thenReturn("jane.doe@example.org");
        when(userEntity.getPassword()).thenReturn("iloveyou");
        when(userEntity.getUserName()).thenReturn("janedoe");
        when(userEntity.getUserUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userEntity.getRoleId()).thenReturn(roleEntity);
        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        when(userJpaRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(userEntity));
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        CityEntity cityEntity = new CityEntity();
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());
        EmployerEntity employerEntity = mock(EmployerEntity.class);
        when(employerEntity.getAmountOfAvailableOffers()).thenReturn(10);
        when(employerEntity.getNumberOfEmployees()).thenReturn(10);
        when(employerEntity.getEmployerId()).thenReturn(1L);
        when(employerEntity.getCompanyName()).thenReturn("Company Name");
        when(employerEntity.getDescription()).thenReturn("The characteristics of someone or something");
        when(employerEntity.getEmailContact()).thenReturn("jane.doe@example.org");
        when(employerEntity.getEmployerUuid()).thenReturn("foo");
        when(employerEntity.getLogoFilename()).thenReturn("foo.txt");
        when(employerEntity.getPhoneNumber()).thenReturn("6625550144");
        when(employerEntity.getWebsite()).thenReturn("Website");
        when(employerEntity.getCreatedAt())
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        when(employerEntity.getCityId()).thenReturn(cityEntity);
        doNothing().when(employerEntity).setAmountOfAvailableOffers(Mockito.<Integer>any());
        doNothing().when(employerEntity).setCityId(Mockito.<CityEntity>any());
        doNothing().when(employerEntity).setCompanyName(Mockito.<String>any());
        doNothing().when(employerEntity).setCreatedAt(Mockito.<OffsetDateTime>any());
        doNothing().when(employerEntity).setDescription(Mockito.<String>any());
        doNothing().when(employerEntity).setEmailContact(Mockito.<String>any());
        doNothing().when(employerEntity).setEmployerId(Mockito.<Long>any());
        doNothing().when(employerEntity).setEmployerUuid(Mockito.<String>any());
        doNothing().when(employerEntity).setLogoFilename(Mockito.<String>any());
        doNothing().when(employerEntity).setNumberOfEmployees(Mockito.<Integer>any());
        doNothing().when(employerEntity).setPhoneNumber(Mockito.<String>any());
        doNothing().when(employerEntity).setWebsite(Mockito.<String>any());
        employerEntity.setAmountOfAvailableOffers(10);
        employerEntity.setCityId(cityId);
        employerEntity.setCompanyName("Company Name");
        employerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerEntity.setDescription("The characteristics of someone or something");
        employerEntity.setEmailContact("jane.doe@example.org");
        employerEntity.setEmployerId(1L);
        employerEntity.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerEntity.setLogoFilename("foo.txt");
        employerEntity.setNumberOfEmployees(10);
        employerEntity.setPhoneNumber("6625550144");
        employerEntity.setWebsite("Website");
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        when(employerJpaRepository.findByEmployerUuid(Mockito.<String>any())).thenReturn(Optional.of(employerEntity));
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService2 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService2,
                offerSkillService, new SkillService(null));

        FileUploadService fileUploadService = new FileUploadService(new StandardReactiveWebEnvironment());
        OfferSkillJpaRepository offerSkillJpaRepository = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService2 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository, offerSkillEntityMapper, new OfferEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository2 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO2 = new EmployerRepository(employerJpaRepository2, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository2 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper2 = new OfferEntityMapperImpl();
        OfferRepository offerDAO2 = new OfferRepository(offerJpaRepository2, offerEntityMapper2,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository, new CandidateEntityMapperImpl())));

        CityEntity cityId2 = new CityEntity();
        cityId2.setCandidateResidenceCities(new HashSet<>());
        cityId2.setCityId(1L);
        cityId2.setCityName("Oxford");
        cityId2.setEmployerCities(new HashSet<>());
        cityId2.setOfferCities(new HashSet<>());

        CityEntity cityId3 = new CityEntity();
        cityId3.setCandidateResidenceCities(new HashSet<>());
        cityId3.setCityId(1L);
        cityId3.setCityName("Oxford");
        cityId3.setEmployerCities(new HashSet<>());
        cityId3.setOfferCities(new HashSet<>());

        EmployerEntity employerId = new EmployerEntity();
        employerId.setAmountOfAvailableOffers(10);
        employerId.setCityId(cityId3);
        employerId.setCompanyName("Company Name");
        employerId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId.setDescription("The characteristics of someone or something");
        employerId.setEmailContact("jane.doe@example.org");
        employerId.setEmployerId(1L);
        employerId.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerId.setLogoFilename("foo.txt");
        employerId.setNumberOfEmployees(10);
        employerId.setPhoneNumber("6625550144");
        employerId.setWebsite("Website");

        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setBenefits("Benefits");
        offerEntity.setCityId(cityId2);
        offerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        offerEntity.setDescription("The characteristics of someone or something");
        offerEntity.setEmployerId(employerId);
        offerEntity.setExperienceLevel("Experience Level");
        offerEntity.setOfferId(1L);
        offerEntity.setOfferSkills(new HashSet<>());
        offerEntity.setOfferUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        offerEntity.setOtherSkills("Other Skills");
        offerEntity.setRemoteWork(1);
        offerEntity.setSalaryMax(BigDecimal.valueOf(1L));
        offerEntity.setSalaryMin(BigDecimal.valueOf(1L));
        offerEntity.setStatus("Status");
        offerEntity.setTitle("Dr");
        offerEntity.setYearsOfExperience(1);
        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        when(offerJpaRepository3.findByOfferIdAndEmployerId(Mockito.<Long>any(), Mockito.<EmployerEntity>any()))
                .thenReturn(Optional.of(offerEntity));
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository2 = mock(OfferCriteriaRepository.class);
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository4 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper4 = new OfferEntityMapperImpl();
        OfferRepository offerDAO4 = new OfferRepository(offerJpaRepository4, offerEntityMapper4,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        DataService dataService2 = new DataService(employerDAO3, offerDAO4,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl()));

        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService3 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferSkillJpaRepository offerSkillJpaRepository2 = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper2 = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService3 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository2, offerSkillEntityMapper2, new OfferEntityMapperImpl()));
        OfferService offerService2 = new OfferService(offerDAO3, offerCriteriaRepository2, dataService2, cityService3,
                offerSkillService3,
                new SkillService(new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class))));

        SkillJpaRepository skillJpaRepository = mock(SkillJpaRepository.class);
        when(skillJpaRepository.findAll()).thenReturn(new ArrayList<>());
        SkillService skillService = new SkillService(
                new SkillRepository(skillJpaRepository, mock(SkillEntityMapper.class)));
        OfferDetailsMapper offerDetailsMapper = mock(OfferDetailsMapper.class);
        when(offerDetailsMapper.map(Mockito.<Offer>any())).thenReturn(new OfferDetailsDTO());
        CityJpaRepository cityJpaRepository3 = mock(CityJpaRepository.class);
        CityService cityService4 = new CityService(new CityRepository(cityJpaRepository3, new CityEntityMapperImpl()));
        CityMapper cityMapper = mock(CityMapper.class);
        SkillMapper skillMapper = mock(SkillMapper.class);
        OfferUserController offerUserController = new OfferUserController(userService, employerService, offerService2,
                cityService4, cityMapper, skillService, skillMapper, offerDetailsMapper, new OfferRowMapperImpl(),
                mock(OfferUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        assertEquals("employer/edit_offer",
                offerUserController.getOfferEditForm(1L, model, new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userJpaRepository).findByEmail(Mockito.<String>any());
        verify(userEntity).getIsEnabled();
        verify(userEntity).getId();
        verify(userEntity).getEmail();
        verify(userEntity).getPassword();
        verify(userEntity).getUserName();
        verify(userEntity).getUserUuid();
        verify(userEntity).getRoleId();
        verify(employerJpaRepository).findByEmployerUuid(Mockito.<String>any());
        verify(employerEntity).getAmountOfAvailableOffers();
        verify(employerEntity).getNumberOfEmployees();
        verify(employerEntity).getEmployerId();
        verify(employerEntity).getCompanyName();
        verify(employerEntity).getDescription();
        verify(employerEntity).getEmailContact();
        verify(employerEntity).getEmployerUuid();
        verify(employerEntity).getLogoFilename();
        verify(employerEntity).getPhoneNumber();
        verify(employerEntity).getWebsite();
        verify(employerEntity).getCreatedAt();
        verify(employerEntity).getCityId();
        verify(employerEntity).setAmountOfAvailableOffers(Mockito.<Integer>any());
        verify(employerEntity).setCityId(Mockito.<CityEntity>any());
        verify(employerEntity).setCompanyName(Mockito.<String>any());
        verify(employerEntity).setCreatedAt(Mockito.<OffsetDateTime>any());
        verify(employerEntity).setDescription(Mockito.<String>any());
        verify(employerEntity).setEmailContact(Mockito.<String>any());
        verify(employerEntity).setEmployerId(Mockito.<Long>any());
        verify(employerEntity).setEmployerUuid(Mockito.<String>any());
        verify(employerEntity).setLogoFilename(Mockito.<String>any());
        verify(employerEntity).setNumberOfEmployees(Mockito.<Integer>any());
        verify(employerEntity).setPhoneNumber(Mockito.<String>any());
        verify(employerEntity).setWebsite(Mockito.<String>any());
        verify(offerJpaRepository3).findByOfferIdAndEmployerId(Mockito.<Long>any(), Mockito.<EmployerEntity>any());
        verify(skillJpaRepository).findAll();
        verify(offerDetailsMapper).map(Mockito.<Offer>any());
        assertTrue(((User) model.get("user")).getIsEnabled());
        assertEquals(1L, ((User) model.get("user")).getId().longValue());
        assertEquals("jane.doe@example.org", ((User) model.get("user")).getEmail());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", ((User) model.get("user")).getUserUuid());
        assertEquals("janedoe", ((User) model.get("user")).getUserName());
        assertEquals("iloveyou", ((User) model.get("user")).getPassword());
        Role role = ((User) model.get("user")).getRole();
        assertEquals(1L, role.getId().longValue());
        assertEquals("Role", role.getRole());
        assertNull(role.getUserId());
    }

    /**
     * Method under test: {@link OfferUserController#getOfferEditForm(Long, Model, Authentication)}
     */
    @Test
    void testGetOfferEditForm8() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.OfferUserController.lambda$getOfferEditForm$4(OfferUserController.java:90)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferEditForm(OfferUserController.java:90)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setRole("Role");
        roleEntity.setUserId(new HashSet<>());
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getIsEnabled()).thenReturn(true);
        when(userEntity.getId()).thenReturn(1L);
        when(userEntity.getEmail()).thenReturn("jane.doe@example.org");
        when(userEntity.getPassword()).thenReturn("iloveyou");
        when(userEntity.getUserName()).thenReturn("janedoe");
        when(userEntity.getUserUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userEntity.getRoleId()).thenReturn(roleEntity);
        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        when(userJpaRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(userEntity));
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        when(employerJpaRepository.findByEmployerUuid(Mockito.<String>any())).thenReturn(Optional.empty());
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService2 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService2,
                offerSkillService, new SkillService(null));

        FileUploadService fileUploadService = new FileUploadService(new StandardReactiveWebEnvironment());
        OfferSkillJpaRepository offerSkillJpaRepository = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService2 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository, offerSkillEntityMapper, new OfferEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository2 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO2 = new EmployerRepository(employerJpaRepository2, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository2 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper2 = new OfferEntityMapperImpl();
        OfferRepository offerDAO2 = new OfferRepository(offerJpaRepository2, offerEntityMapper2,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository, new CandidateEntityMapperImpl())));

        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository2 = mock(OfferCriteriaRepository.class);
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository4 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper4 = new OfferEntityMapperImpl();
        OfferRepository offerDAO4 = new OfferRepository(offerJpaRepository4, offerEntityMapper4,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        DataService dataService2 = new DataService(employerDAO3, offerDAO4,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl()));

        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService3 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferSkillJpaRepository offerSkillJpaRepository2 = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper2 = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService3 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository2, offerSkillEntityMapper2, new OfferEntityMapperImpl()));
        OfferService offerService2 = new OfferService(offerDAO3, offerCriteriaRepository2, dataService2, cityService3,
                offerSkillService3,
                new SkillService(new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class))));

        CityJpaRepository cityJpaRepository3 = mock(CityJpaRepository.class);
        CityService cityService4 = new CityService(new CityRepository(cityJpaRepository3, new CityEntityMapperImpl()));
        CityMapper cityMapper = mock(CityMapper.class);
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        OfferDetailsMapper offerDetailsMapper = mock(OfferDetailsMapper.class);
        OfferUserController offerUserController = new OfferUserController(userService, employerService, offerService2,
                cityService4, cityMapper, skillService, skillMapper, offerDetailsMapper, new OfferRowMapperImpl(),
                mock(OfferUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        assertThrows(NotFoundException.class, () -> offerUserController.getOfferEditForm(1L, model,
                new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userJpaRepository).findByEmail(Mockito.<String>any());
        verify(userEntity).getIsEnabled();
        verify(userEntity).getId();
        verify(userEntity).getEmail();
        verify(userEntity).getPassword();
        verify(userEntity).getUserName();
        verify(userEntity).getUserUuid();
        verify(userEntity).getRoleId();
        verify(employerJpaRepository).findByEmployerUuid(Mockito.<String>any());
    }

    /**
     * Method under test: {@link OfferUserController#getOfferEditForm(Long, Model, Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetOfferEditForm9() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.OfferUserController.lambda$getOfferEditForm$4(OfferUserController.java:90)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferEditForm(OfferUserController.java:90)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at java.base/java.util.Objects.requireNonNull(Objects.java:208)
        //       at pl.devfinder.infrastructure.database.repository.EmployerRepository.findByEmployerUuid(EmployerRepository.java:42)
        //       at pl.devfinder.business.EmployerService.findByEmployerUuid(EmployerService.java:60)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferEditForm(OfferUserController.java:91)
        //   See https://diff.blue/R013 to resolve this issue.

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setRole("Role");
        roleEntity.setUserId(new HashSet<>());
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getIsEnabled()).thenReturn(true);
        when(userEntity.getId()).thenReturn(1L);
        when(userEntity.getEmail()).thenReturn("jane.doe@example.org");
        when(userEntity.getPassword()).thenReturn("iloveyou");
        when(userEntity.getUserName()).thenReturn("janedoe");
        when(userEntity.getUserUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userEntity.getRoleId()).thenReturn(roleEntity);
        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        when(userJpaRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(userEntity));
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        CityEntity cityEntity = new CityEntity();
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());
        EmployerEntity employerEntity = mock(EmployerEntity.class);
        when(employerEntity.getAmountOfAvailableOffers()).thenReturn(10);
        when(employerEntity.getNumberOfEmployees()).thenReturn(10);
        when(employerEntity.getEmployerId()).thenReturn(1L);
        when(employerEntity.getCompanyName()).thenReturn("Company Name");
        when(employerEntity.getDescription()).thenReturn("The characteristics of someone or something");
        when(employerEntity.getEmailContact()).thenReturn("jane.doe@example.org");
        when(employerEntity.getEmployerUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(employerEntity.getLogoFilename()).thenReturn("foo.txt");
        when(employerEntity.getPhoneNumber()).thenReturn("6625550144");
        when(employerEntity.getWebsite()).thenReturn("Website");
        when(employerEntity.getCreatedAt())
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        when(employerEntity.getCityId()).thenReturn(cityEntity);
        doNothing().when(employerEntity).setAmountOfAvailableOffers(Mockito.<Integer>any());
        doNothing().when(employerEntity).setCityId(Mockito.<CityEntity>any());
        doNothing().when(employerEntity).setCompanyName(Mockito.<String>any());
        doNothing().when(employerEntity).setCreatedAt(Mockito.<OffsetDateTime>any());
        doNothing().when(employerEntity).setDescription(Mockito.<String>any());
        doNothing().when(employerEntity).setEmailContact(Mockito.<String>any());
        doNothing().when(employerEntity).setEmployerId(Mockito.<Long>any());
        doNothing().when(employerEntity).setEmployerUuid(Mockito.<String>any());
        doNothing().when(employerEntity).setLogoFilename(Mockito.<String>any());
        doNothing().when(employerEntity).setNumberOfEmployees(Mockito.<Integer>any());
        doNothing().when(employerEntity).setPhoneNumber(Mockito.<String>any());
        doNothing().when(employerEntity).setWebsite(Mockito.<String>any());
        employerEntity.setAmountOfAvailableOffers(10);
        employerEntity.setCityId(cityId);
        employerEntity.setCompanyName("Company Name");
        employerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerEntity.setDescription("The characteristics of someone or something");
        employerEntity.setEmailContact("jane.doe@example.org");
        employerEntity.setEmployerId(1L);
        employerEntity.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerEntity.setLogoFilename("foo.txt");
        employerEntity.setNumberOfEmployees(10);
        employerEntity.setPhoneNumber("6625550144");
        employerEntity.setWebsite("Website");
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        when(employerJpaRepository.findByEmployerUuid(Mockito.<String>any())).thenReturn(Optional.of(employerEntity));
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, null);

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService2 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService2,
                offerSkillService, new SkillService(null));

        FileUploadService fileUploadService = new FileUploadService(new StandardReactiveWebEnvironment());
        OfferSkillJpaRepository offerSkillJpaRepository = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService2 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository, offerSkillEntityMapper, new OfferEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository2 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO2 = new EmployerRepository(employerJpaRepository2, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository2 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper2 = new OfferEntityMapperImpl();
        OfferRepository offerDAO2 = new OfferRepository(offerJpaRepository2, offerEntityMapper2,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository, new CandidateEntityMapperImpl())));

        CityEntity cityId2 = new CityEntity();
        cityId2.setCandidateResidenceCities(new HashSet<>());
        cityId2.setCityId(1L);
        cityId2.setCityName("Oxford");
        cityId2.setEmployerCities(new HashSet<>());
        cityId2.setOfferCities(new HashSet<>());

        CityEntity cityId3 = new CityEntity();
        cityId3.setCandidateResidenceCities(new HashSet<>());
        cityId3.setCityId(1L);
        cityId3.setCityName("Oxford");
        cityId3.setEmployerCities(new HashSet<>());
        cityId3.setOfferCities(new HashSet<>());

        EmployerEntity employerId = new EmployerEntity();
        employerId.setAmountOfAvailableOffers(10);
        employerId.setCityId(cityId3);
        employerId.setCompanyName("Company Name");
        employerId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId.setDescription("The characteristics of someone or something");
        employerId.setEmailContact("jane.doe@example.org");
        employerId.setEmployerId(1L);
        employerId.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerId.setLogoFilename("foo.txt");
        employerId.setNumberOfEmployees(10);
        employerId.setPhoneNumber("6625550144");
        employerId.setWebsite("Website");

        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setBenefits("Benefits");
        offerEntity.setCityId(cityId2);
        offerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        offerEntity.setDescription("The characteristics of someone or something");
        offerEntity.setEmployerId(employerId);
        offerEntity.setExperienceLevel("Experience Level");
        offerEntity.setOfferId(1L);
        offerEntity.setOfferSkills(new HashSet<>());
        offerEntity.setOfferUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        offerEntity.setOtherSkills("Other Skills");
        offerEntity.setRemoteWork(1);
        offerEntity.setSalaryMax(BigDecimal.valueOf(1L));
        offerEntity.setSalaryMin(BigDecimal.valueOf(1L));
        offerEntity.setStatus("Status");
        offerEntity.setTitle("Dr");
        offerEntity.setYearsOfExperience(1);
        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        when(offerJpaRepository3.findByOfferIdAndEmployerId(Mockito.<Long>any(), Mockito.<EmployerEntity>any()))
                .thenReturn(Optional.of(offerEntity));
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository2 = mock(OfferCriteriaRepository.class);
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository4 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper4 = new OfferEntityMapperImpl();
        OfferRepository offerDAO4 = new OfferRepository(offerJpaRepository4, offerEntityMapper4,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        DataService dataService2 = new DataService(employerDAO3, offerDAO4,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl()));

        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService3 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferSkillJpaRepository offerSkillJpaRepository2 = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper2 = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService3 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository2, offerSkillEntityMapper2, new OfferEntityMapperImpl()));
        OfferService offerService2 = new OfferService(offerDAO3, offerCriteriaRepository2, dataService2, cityService3,
                offerSkillService3,
                new SkillService(new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class))));

        SkillJpaRepository skillJpaRepository = mock(SkillJpaRepository.class);
        when(skillJpaRepository.findAll()).thenReturn(new ArrayList<>());
        SkillService skillService = new SkillService(
                new SkillRepository(skillJpaRepository, mock(SkillEntityMapper.class)));
        OfferDetailsMapper offerDetailsMapper = mock(OfferDetailsMapper.class);
        when(offerDetailsMapper.map(Mockito.<Offer>any())).thenReturn(new OfferDetailsDTO());
        CityJpaRepository cityJpaRepository3 = mock(CityJpaRepository.class);
        CityService cityService4 = new CityService(new CityRepository(cityJpaRepository3, new CityEntityMapperImpl()));
        CityMapper cityMapper = mock(CityMapper.class);
        SkillMapper skillMapper = mock(SkillMapper.class);
        OfferUserController offerUserController = new OfferUserController(userService, employerService, offerService2,
                cityService4, cityMapper, skillService, skillMapper, offerDetailsMapper, new OfferRowMapperImpl(),
                mock(OfferUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        offerUserController.getOfferEditForm(1L, model, new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link OfferUserController#getOfferNewForm(Model, Authentication)}
     */
    @Test
    void testGetOfferNewForm() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.OfferUserController.lambda$getOfferNewForm$6(OfferUserController.java:104)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferNewForm(OfferUserController.java:104)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService2 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService2,
                offerSkillService, new SkillService(null));

        FileUploadService fileUploadService = new FileUploadService(new StandardReactiveWebEnvironment());
        OfferSkillJpaRepository offerSkillJpaRepository = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService2 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository, offerSkillEntityMapper, new OfferEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository2 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO2 = new EmployerRepository(employerJpaRepository2, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository2 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper2 = new OfferEntityMapperImpl();
        OfferRepository offerDAO2 = new OfferRepository(offerJpaRepository2, offerEntityMapper2,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository, new CandidateEntityMapperImpl())));

        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository2 = mock(OfferCriteriaRepository.class);
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository4 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper4 = new OfferEntityMapperImpl();
        OfferRepository offerDAO4 = new OfferRepository(offerJpaRepository4, offerEntityMapper4,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        DataService dataService2 = new DataService(employerDAO3, offerDAO4,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl()));

        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService3 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferSkillJpaRepository offerSkillJpaRepository2 = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper2 = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService3 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository2, offerSkillEntityMapper2, new OfferEntityMapperImpl()));
        OfferService offerService2 = new OfferService(offerDAO3, offerCriteriaRepository2, dataService2, cityService3,
                offerSkillService3,
                new SkillService(new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class))));

        CityJpaRepository cityJpaRepository3 = mock(CityJpaRepository.class);
        CityService cityService4 = new CityService(new CityRepository(cityJpaRepository3, new CityEntityMapperImpl()));
        CityMapper cityMapper = mock(CityMapper.class);
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        OfferDetailsMapper offerDetailsMapper = mock(OfferDetailsMapper.class);
        OfferUserController offerUserController = new OfferUserController(userService, employerService, offerService2,
                cityService4, cityMapper, skillService, skillMapper, offerDetailsMapper, new OfferRowMapperImpl(),
                mock(OfferUpdateRequestMapper.class));
        assertThrows(NotFoundException.class, () -> offerUserController.getOfferNewForm(new ConcurrentModel(), null));
    }

    /**
     * Method under test: {@link OfferUserController#getOfferNewForm(Model, Authentication)}
     */
    @Test
    void testGetOfferNewForm2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.OfferUserController.lambda$getOfferNewForm$6(OfferUserController.java:104)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferNewForm(OfferUserController.java:104)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        UserService userService = mock(UserService.class);
        when(userService.findByEmail(Mockito.<String>any())).thenReturn(Optional.empty());
        OfferUserController offerUserController = new OfferUserController(userService, mock(EmployerService.class),
                mock(OfferService.class), mock(CityService.class), mock(CityMapper.class), mock(SkillService.class),
                mock(SkillMapper.class), mock(OfferDetailsMapper.class), mock(OfferRowMapper.class),
                mock(OfferUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        assertThrows(NotFoundException.class,
                () -> offerUserController.getOfferNewForm(model, new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userService).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link OfferUserController#getOfferNewForm(Model, Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetOfferNewForm3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.OfferUserController.lambda$getOfferNewForm$6(OfferUserController.java:104)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferNewForm(OfferUserController.java:104)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   pl.devfinder.domain.exception.NotFoundException: An error occurred
        //       at pl.devfinder.business.management.Utility.putUserDataToModel(Utility.java:34)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferNewForm(OfferUserController.java:103)
        //   See https://diff.blue/R013 to resolve this issue.

        UserService userService = mock(UserService.class);
        when(userService.findByEmail(Mockito.<String>any())).thenThrow(new NotFoundException("An error occurred"));
        OfferUserController offerUserController = new OfferUserController(userService, mock(EmployerService.class),
                mock(OfferService.class), mock(CityService.class), mock(CityMapper.class), mock(SkillService.class),
                mock(SkillMapper.class), mock(OfferDetailsMapper.class), mock(OfferRowMapper.class),
                mock(OfferUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        offerUserController.getOfferNewForm(model, new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link OfferUserController#getOfferNewForm(Model, Authentication)}
     */
    @Test
    void testGetOfferNewForm4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.OfferUserController.lambda$getOfferNewForm$6(OfferUserController.java:104)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferNewForm(OfferUserController.java:104)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setRole("Role");
        roleEntity.setUserId(new HashSet<>());
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getIsEnabled()).thenReturn(true);
        when(userEntity.getId()).thenReturn(1L);
        when(userEntity.getEmail()).thenReturn("jane.doe@example.org");
        when(userEntity.getPassword()).thenReturn("iloveyou");
        when(userEntity.getUserName()).thenReturn("janedoe");
        when(userEntity.getUserUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userEntity.getRoleId()).thenReturn(roleEntity);
        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        when(userJpaRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(userEntity));
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        EmployerEntity employerEntity = new EmployerEntity();
        employerEntity.setAmountOfAvailableOffers(10);
        employerEntity.setCityId(cityId);
        employerEntity.setCompanyName("Company Name");
        employerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerEntity.setDescription("The characteristics of someone or something");
        employerEntity.setEmailContact("jane.doe@example.org");
        employerEntity.setEmployerId(1L);
        employerEntity.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerEntity.setLogoFilename("foo.txt");
        employerEntity.setNumberOfEmployees(10);
        employerEntity.setPhoneNumber("6625550144");
        employerEntity.setWebsite("Website");
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        when(employerJpaRepository.findByEmployerUuid(Mockito.<String>any())).thenReturn(Optional.of(employerEntity));
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService2 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService2,
                offerSkillService, new SkillService(null));

        FileUploadService fileUploadService = new FileUploadService(new StandardReactiveWebEnvironment());
        OfferSkillJpaRepository offerSkillJpaRepository = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService2 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository, offerSkillEntityMapper, new OfferEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository2 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO2 = new EmployerRepository(employerJpaRepository2, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository2 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper2 = new OfferEntityMapperImpl();
        OfferRepository offerDAO2 = new OfferRepository(offerJpaRepository2, offerEntityMapper2,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository, new CandidateEntityMapperImpl())));

        SkillJpaRepository skillJpaRepository = mock(SkillJpaRepository.class);
        when(skillJpaRepository.findAll()).thenReturn(new ArrayList<>());
        SkillService skillService = new SkillService(
                new SkillRepository(skillJpaRepository, mock(SkillEntityMapper.class)));
        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository2 = mock(OfferCriteriaRepository.class);
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository4 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper4 = new OfferEntityMapperImpl();
        OfferRepository offerDAO4 = new OfferRepository(offerJpaRepository4, offerEntityMapper4,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        DataService dataService2 = new DataService(employerDAO3, offerDAO4,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl()));

        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService3 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferSkillJpaRepository offerSkillJpaRepository2 = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper2 = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService3 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository2, offerSkillEntityMapper2, new OfferEntityMapperImpl()));
        OfferService offerService2 = new OfferService(offerDAO3, offerCriteriaRepository2, dataService2, cityService3,
                offerSkillService3,
                new SkillService(new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class))));

        CityJpaRepository cityJpaRepository3 = mock(CityJpaRepository.class);
        CityService cityService4 = new CityService(new CityRepository(cityJpaRepository3, new CityEntityMapperImpl()));
        CityMapper cityMapper = mock(CityMapper.class);
        SkillMapper skillMapper = mock(SkillMapper.class);
        OfferDetailsMapper offerDetailsMapper = mock(OfferDetailsMapper.class);
        OfferUserController offerUserController = new OfferUserController(userService, employerService, offerService2,
                cityService4, cityMapper, skillService, skillMapper, offerDetailsMapper, new OfferRowMapperImpl(),
                mock(OfferUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        assertEquals("employer/new_offer",
                offerUserController.getOfferNewForm(model, new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userJpaRepository).findByEmail(Mockito.<String>any());
        verify(userEntity).getIsEnabled();
        verify(userEntity).getId();
        verify(userEntity).getEmail();
        verify(userEntity).getPassword();
        verify(userEntity).getUserName();
        verify(userEntity).getUserUuid();
        verify(userEntity).getRoleId();
        verify(employerJpaRepository).findByEmployerUuid(Mockito.<String>any());
        verify(skillJpaRepository).findAll();
        assertTrue(((User) model.get("user")).getIsEnabled());
        assertEquals(1L, ((User) model.get("user")).getId().longValue());
        assertEquals("jane.doe@example.org", ((User) model.get("user")).getEmail());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", ((User) model.get("user")).getUserUuid());
        assertEquals("janedoe", ((User) model.get("user")).getUserName());
        assertEquals("iloveyou", ((User) model.get("user")).getPassword());
        Role role = ((User) model.get("user")).getRole();
        assertEquals(1L, role.getId().longValue());
        assertEquals("Role", role.getRole());
        assertNull(role.getUserId());
    }

    /**
     * Method under test: {@link OfferUserController#getOfferNewForm(Model, Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetOfferNewForm5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.OfferUserController.lambda$getOfferNewForm$6(OfferUserController.java:104)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferNewForm(OfferUserController.java:104)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   pl.devfinder.domain.exception.NotFoundException: An error occurred
        //       at pl.devfinder.infrastructure.database.repository.SkillRepository.findAll(SkillRepository.java:23)
        //       at pl.devfinder.business.SkillService.findAll(SkillService.java:19)
        //       at pl.devfinder.api.controller.OfferUserController.prepareModelToEditOrNewOffer(OfferUserController.java:205)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferNewForm(OfferUserController.java:113)
        //   See https://diff.blue/R013 to resolve this issue.

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setRole("Role");
        roleEntity.setUserId(new HashSet<>());
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getIsEnabled()).thenReturn(true);
        when(userEntity.getId()).thenReturn(1L);
        when(userEntity.getEmail()).thenReturn("jane.doe@example.org");
        when(userEntity.getPassword()).thenReturn("iloveyou");
        when(userEntity.getUserName()).thenReturn("janedoe");
        when(userEntity.getUserUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userEntity.getRoleId()).thenReturn(roleEntity);
        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        when(userJpaRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(userEntity));
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        EmployerEntity employerEntity = new EmployerEntity();
        employerEntity.setAmountOfAvailableOffers(10);
        employerEntity.setCityId(cityId);
        employerEntity.setCompanyName("Company Name");
        employerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerEntity.setDescription("The characteristics of someone or something");
        employerEntity.setEmailContact("jane.doe@example.org");
        employerEntity.setEmployerId(1L);
        employerEntity.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerEntity.setLogoFilename("foo.txt");
        employerEntity.setNumberOfEmployees(10);
        employerEntity.setPhoneNumber("6625550144");
        employerEntity.setWebsite("Website");
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        when(employerJpaRepository.findByEmployerUuid(Mockito.<String>any())).thenReturn(Optional.of(employerEntity));
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService2 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService2,
                offerSkillService, new SkillService(null));

        FileUploadService fileUploadService = new FileUploadService(new StandardReactiveWebEnvironment());
        OfferSkillJpaRepository offerSkillJpaRepository = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService2 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository, offerSkillEntityMapper, new OfferEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository2 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO2 = new EmployerRepository(employerJpaRepository2, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository2 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper2 = new OfferEntityMapperImpl();
        OfferRepository offerDAO2 = new OfferRepository(offerJpaRepository2, offerEntityMapper2,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository, new CandidateEntityMapperImpl())));

        SkillJpaRepository skillJpaRepository = mock(SkillJpaRepository.class);
        when(skillJpaRepository.findAll()).thenThrow(new NotFoundException("An error occurred"));
        SkillService skillService = new SkillService(
                new SkillRepository(skillJpaRepository, mock(SkillEntityMapper.class)));
        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository2 = mock(OfferCriteriaRepository.class);
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository4 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper4 = new OfferEntityMapperImpl();
        OfferRepository offerDAO4 = new OfferRepository(offerJpaRepository4, offerEntityMapper4,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        DataService dataService2 = new DataService(employerDAO3, offerDAO4,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl()));

        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService3 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferSkillJpaRepository offerSkillJpaRepository2 = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper2 = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService3 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository2, offerSkillEntityMapper2, new OfferEntityMapperImpl()));
        OfferService offerService2 = new OfferService(offerDAO3, offerCriteriaRepository2, dataService2, cityService3,
                offerSkillService3,
                new SkillService(new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class))));

        CityJpaRepository cityJpaRepository3 = mock(CityJpaRepository.class);
        CityService cityService4 = new CityService(new CityRepository(cityJpaRepository3, new CityEntityMapperImpl()));
        CityMapper cityMapper = mock(CityMapper.class);
        SkillMapper skillMapper = mock(SkillMapper.class);
        OfferDetailsMapper offerDetailsMapper = mock(OfferDetailsMapper.class);
        OfferUserController offerUserController = new OfferUserController(userService, employerService, offerService2,
                cityService4, cityMapper, skillService, skillMapper, offerDetailsMapper, new OfferRowMapperImpl(),
                mock(OfferUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        offerUserController.getOfferNewForm(model, new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link OfferUserController#getOfferNewForm(Model, Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetOfferNewForm6() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.OfferUserController.lambda$getOfferNewForm$6(OfferUserController.java:104)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferNewForm(OfferUserController.java:104)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.map(java.util.function.Function)" because the return value of "pl.devfinder.infrastructure.database.repository.jpa.UserJpaRepository.findByEmail(String)" is null
        //       at pl.devfinder.infrastructure.database.repository.UserRepository.findByEmail(UserRepository.java:24)
        //       at pl.devfinder.business.UserService.findByEmail(UserService.java:26)
        //       at pl.devfinder.business.management.Utility.putUserDataToModel(Utility.java:34)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferNewForm(OfferUserController.java:103)
        //   See https://diff.blue/R013 to resolve this issue.

        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        when(userJpaRepository.findByEmail(Mockito.<String>any())).thenReturn(null);
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        EmployerEntity employerEntity = new EmployerEntity();
        employerEntity.setAmountOfAvailableOffers(10);
        employerEntity.setCityId(cityId);
        employerEntity.setCompanyName("Company Name");
        employerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerEntity.setDescription("The characteristics of someone or something");
        employerEntity.setEmailContact("jane.doe@example.org");
        employerEntity.setEmployerId(1L);
        employerEntity.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerEntity.setLogoFilename("foo.txt");
        employerEntity.setNumberOfEmployees(10);
        employerEntity.setPhoneNumber("6625550144");
        employerEntity.setWebsite("Website");
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        when(employerJpaRepository.findByEmployerUuid(Mockito.<String>any())).thenReturn(Optional.of(employerEntity));
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService2 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService2,
                offerSkillService, new SkillService(null));

        FileUploadService fileUploadService = new FileUploadService(new StandardReactiveWebEnvironment());
        OfferSkillJpaRepository offerSkillJpaRepository = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService2 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository, offerSkillEntityMapper, new OfferEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository2 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO2 = new EmployerRepository(employerJpaRepository2, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository2 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper2 = new OfferEntityMapperImpl();
        OfferRepository offerDAO2 = new OfferRepository(offerJpaRepository2, offerEntityMapper2,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository, new CandidateEntityMapperImpl())));

        SkillJpaRepository skillJpaRepository = mock(SkillJpaRepository.class);
        when(skillJpaRepository.findAll()).thenReturn(new ArrayList<>());
        SkillService skillService = new SkillService(
                new SkillRepository(skillJpaRepository, mock(SkillEntityMapper.class)));
        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository2 = mock(OfferCriteriaRepository.class);
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository4 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper4 = new OfferEntityMapperImpl();
        OfferRepository offerDAO4 = new OfferRepository(offerJpaRepository4, offerEntityMapper4,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        DataService dataService2 = new DataService(employerDAO3, offerDAO4,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl()));

        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService3 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferSkillJpaRepository offerSkillJpaRepository2 = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper2 = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService3 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository2, offerSkillEntityMapper2, new OfferEntityMapperImpl()));
        OfferService offerService2 = new OfferService(offerDAO3, offerCriteriaRepository2, dataService2, cityService3,
                offerSkillService3,
                new SkillService(new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class))));

        CityJpaRepository cityJpaRepository3 = mock(CityJpaRepository.class);
        CityService cityService4 = new CityService(new CityRepository(cityJpaRepository3, new CityEntityMapperImpl()));
        CityMapper cityMapper = mock(CityMapper.class);
        SkillMapper skillMapper = mock(SkillMapper.class);
        OfferDetailsMapper offerDetailsMapper = mock(OfferDetailsMapper.class);
        OfferUserController offerUserController = new OfferUserController(userService, employerService, offerService2,
                cityService4, cityMapper, skillService, skillMapper, offerDetailsMapper, new OfferRowMapperImpl(),
                mock(OfferUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        offerUserController.getOfferNewForm(model, new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link OfferUserController#getOfferNewForm(Model, Authentication)}
     */
    @Test
    void testGetOfferNewForm7() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.OfferUserController.lambda$getOfferNewForm$6(OfferUserController.java:104)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferNewForm(OfferUserController.java:104)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setRole("Role");
        roleEntity.setUserId(new HashSet<>());
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getIsEnabled()).thenReturn(true);
        when(userEntity.getId()).thenReturn(1L);
        when(userEntity.getEmail()).thenReturn("jane.doe@example.org");
        when(userEntity.getPassword()).thenReturn("iloveyou");
        when(userEntity.getUserName()).thenReturn("janedoe");
        when(userEntity.getUserUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userEntity.getRoleId()).thenReturn(roleEntity);
        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        when(userJpaRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(userEntity));
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        CityEntity cityEntity = new CityEntity();
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());
        EmployerEntity employerEntity = mock(EmployerEntity.class);
        when(employerEntity.getAmountOfAvailableOffers()).thenReturn(10);
        when(employerEntity.getNumberOfEmployees()).thenReturn(10);
        when(employerEntity.getEmployerId()).thenReturn(1L);
        when(employerEntity.getCompanyName()).thenReturn("Company Name");
        when(employerEntity.getDescription()).thenReturn("The characteristics of someone or something");
        when(employerEntity.getEmailContact()).thenReturn("jane.doe@example.org");
        when(employerEntity.getEmployerUuid()).thenReturn("foo");
        when(employerEntity.getLogoFilename()).thenReturn("foo.txt");
        when(employerEntity.getPhoneNumber()).thenReturn("6625550144");
        when(employerEntity.getWebsite()).thenReturn("Website");
        when(employerEntity.getCreatedAt())
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        when(employerEntity.getCityId()).thenReturn(cityEntity);
        doNothing().when(employerEntity).setAmountOfAvailableOffers(Mockito.<Integer>any());
        doNothing().when(employerEntity).setCityId(Mockito.<CityEntity>any());
        doNothing().when(employerEntity).setCompanyName(Mockito.<String>any());
        doNothing().when(employerEntity).setCreatedAt(Mockito.<OffsetDateTime>any());
        doNothing().when(employerEntity).setDescription(Mockito.<String>any());
        doNothing().when(employerEntity).setEmailContact(Mockito.<String>any());
        doNothing().when(employerEntity).setEmployerId(Mockito.<Long>any());
        doNothing().when(employerEntity).setEmployerUuid(Mockito.<String>any());
        doNothing().when(employerEntity).setLogoFilename(Mockito.<String>any());
        doNothing().when(employerEntity).setNumberOfEmployees(Mockito.<Integer>any());
        doNothing().when(employerEntity).setPhoneNumber(Mockito.<String>any());
        doNothing().when(employerEntity).setWebsite(Mockito.<String>any());
        employerEntity.setAmountOfAvailableOffers(10);
        employerEntity.setCityId(cityId);
        employerEntity.setCompanyName("Company Name");
        employerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerEntity.setDescription("The characteristics of someone or something");
        employerEntity.setEmailContact("jane.doe@example.org");
        employerEntity.setEmployerId(1L);
        employerEntity.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerEntity.setLogoFilename("foo.txt");
        employerEntity.setNumberOfEmployees(10);
        employerEntity.setPhoneNumber("6625550144");
        employerEntity.setWebsite("Website");
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        when(employerJpaRepository.findByEmployerUuid(Mockito.<String>any())).thenReturn(Optional.of(employerEntity));
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService2 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService2,
                offerSkillService, new SkillService(null));

        FileUploadService fileUploadService = new FileUploadService(new StandardReactiveWebEnvironment());
        OfferSkillJpaRepository offerSkillJpaRepository = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService2 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository, offerSkillEntityMapper, new OfferEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository2 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO2 = new EmployerRepository(employerJpaRepository2, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository2 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper2 = new OfferEntityMapperImpl();
        OfferRepository offerDAO2 = new OfferRepository(offerJpaRepository2, offerEntityMapper2,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository, new CandidateEntityMapperImpl())));

        SkillJpaRepository skillJpaRepository = mock(SkillJpaRepository.class);
        when(skillJpaRepository.findAll()).thenReturn(new ArrayList<>());
        SkillService skillService = new SkillService(
                new SkillRepository(skillJpaRepository, mock(SkillEntityMapper.class)));
        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository2 = mock(OfferCriteriaRepository.class);
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository4 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper4 = new OfferEntityMapperImpl();
        OfferRepository offerDAO4 = new OfferRepository(offerJpaRepository4, offerEntityMapper4,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        DataService dataService2 = new DataService(employerDAO3, offerDAO4,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl()));

        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService3 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferSkillJpaRepository offerSkillJpaRepository2 = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper2 = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService3 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository2, offerSkillEntityMapper2, new OfferEntityMapperImpl()));
        OfferService offerService2 = new OfferService(offerDAO3, offerCriteriaRepository2, dataService2, cityService3,
                offerSkillService3,
                new SkillService(new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class))));

        CityJpaRepository cityJpaRepository3 = mock(CityJpaRepository.class);
        CityService cityService4 = new CityService(new CityRepository(cityJpaRepository3, new CityEntityMapperImpl()));
        CityMapper cityMapper = mock(CityMapper.class);
        SkillMapper skillMapper = mock(SkillMapper.class);
        OfferDetailsMapper offerDetailsMapper = mock(OfferDetailsMapper.class);
        OfferUserController offerUserController = new OfferUserController(userService, employerService, offerService2,
                cityService4, cityMapper, skillService, skillMapper, offerDetailsMapper, new OfferRowMapperImpl(),
                mock(OfferUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        assertEquals("employer/new_offer",
                offerUserController.getOfferNewForm(model, new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userJpaRepository).findByEmail(Mockito.<String>any());
        verify(userEntity).getIsEnabled();
        verify(userEntity).getId();
        verify(userEntity).getEmail();
        verify(userEntity).getPassword();
        verify(userEntity).getUserName();
        verify(userEntity).getUserUuid();
        verify(userEntity).getRoleId();
        verify(employerJpaRepository).findByEmployerUuid(Mockito.<String>any());
        verify(employerEntity).getAmountOfAvailableOffers();
        verify(employerEntity).getNumberOfEmployees();
        verify(employerEntity).getEmployerId();
        verify(employerEntity).getCompanyName();
        verify(employerEntity).getDescription();
        verify(employerEntity).getEmailContact();
        verify(employerEntity).getEmployerUuid();
        verify(employerEntity).getLogoFilename();
        verify(employerEntity).getPhoneNumber();
        verify(employerEntity).getWebsite();
        verify(employerEntity).getCreatedAt();
        verify(employerEntity).getCityId();
        verify(employerEntity).setAmountOfAvailableOffers(Mockito.<Integer>any());
        verify(employerEntity).setCityId(Mockito.<CityEntity>any());
        verify(employerEntity).setCompanyName(Mockito.<String>any());
        verify(employerEntity).setCreatedAt(Mockito.<OffsetDateTime>any());
        verify(employerEntity).setDescription(Mockito.<String>any());
        verify(employerEntity).setEmailContact(Mockito.<String>any());
        verify(employerEntity).setEmployerId(Mockito.<Long>any());
        verify(employerEntity).setEmployerUuid(Mockito.<String>any());
        verify(employerEntity).setLogoFilename(Mockito.<String>any());
        verify(employerEntity).setNumberOfEmployees(Mockito.<Integer>any());
        verify(employerEntity).setPhoneNumber(Mockito.<String>any());
        verify(employerEntity).setWebsite(Mockito.<String>any());
        verify(skillJpaRepository).findAll();
        assertTrue(((User) model.get("user")).getIsEnabled());
        assertEquals(1L, ((User) model.get("user")).getId().longValue());
        assertEquals("jane.doe@example.org", ((User) model.get("user")).getEmail());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", ((User) model.get("user")).getUserUuid());
        assertEquals("janedoe", ((User) model.get("user")).getUserName());
        assertEquals("iloveyou", ((User) model.get("user")).getPassword());
        Role role = ((User) model.get("user")).getRole();
        assertEquals(1L, role.getId().longValue());
        assertEquals("Role", role.getRole());
        assertNull(role.getUserId());
    }

    /**
     * Method under test: {@link OfferUserController#getOfferNewForm(Model, Authentication)}
     */
    @Test
    void testGetOfferNewForm8() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.OfferUserController.lambda$getOfferNewForm$6(OfferUserController.java:104)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferNewForm(OfferUserController.java:104)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setRole("Role");
        roleEntity.setUserId(new HashSet<>());
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getIsEnabled()).thenReturn(true);
        when(userEntity.getId()).thenReturn(1L);
        when(userEntity.getEmail()).thenReturn("jane.doe@example.org");
        when(userEntity.getPassword()).thenReturn("iloveyou");
        when(userEntity.getUserName()).thenReturn("janedoe");
        when(userEntity.getUserUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userEntity.getRoleId()).thenReturn(roleEntity);
        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        when(userJpaRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(userEntity));
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        when(employerJpaRepository.findByEmployerUuid(Mockito.<String>any())).thenReturn(Optional.empty());
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService2 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService2,
                offerSkillService, new SkillService(null));

        FileUploadService fileUploadService = new FileUploadService(new StandardReactiveWebEnvironment());
        OfferSkillJpaRepository offerSkillJpaRepository = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService2 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository, offerSkillEntityMapper, new OfferEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository2 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO2 = new EmployerRepository(employerJpaRepository2, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository2 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper2 = new OfferEntityMapperImpl();
        OfferRepository offerDAO2 = new OfferRepository(offerJpaRepository2, offerEntityMapper2,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository, new CandidateEntityMapperImpl())));

        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository2 = mock(OfferCriteriaRepository.class);
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository4 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper4 = new OfferEntityMapperImpl();
        OfferRepository offerDAO4 = new OfferRepository(offerJpaRepository4, offerEntityMapper4,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        DataService dataService2 = new DataService(employerDAO3, offerDAO4,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl()));

        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService3 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferSkillJpaRepository offerSkillJpaRepository2 = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper2 = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService3 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository2, offerSkillEntityMapper2, new OfferEntityMapperImpl()));
        OfferService offerService2 = new OfferService(offerDAO3, offerCriteriaRepository2, dataService2, cityService3,
                offerSkillService3,
                new SkillService(new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class))));

        CityJpaRepository cityJpaRepository3 = mock(CityJpaRepository.class);
        CityService cityService4 = new CityService(new CityRepository(cityJpaRepository3, new CityEntityMapperImpl()));
        CityMapper cityMapper = mock(CityMapper.class);
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        OfferDetailsMapper offerDetailsMapper = mock(OfferDetailsMapper.class);
        OfferUserController offerUserController = new OfferUserController(userService, employerService, offerService2,
                cityService4, cityMapper, skillService, skillMapper, offerDetailsMapper, new OfferRowMapperImpl(),
                mock(OfferUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        assertEquals("redirect:../employer_portal/edit_profile?first_create_profile",
                offerUserController.getOfferNewForm(model, new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userJpaRepository).findByEmail(Mockito.<String>any());
        verify(userEntity).getIsEnabled();
        verify(userEntity).getId();
        verify(userEntity).getEmail();
        verify(userEntity).getPassword();
        verify(userEntity).getUserName();
        verify(userEntity).getUserUuid();
        verify(userEntity).getRoleId();
        verify(employerJpaRepository).findByEmployerUuid(Mockito.<String>any());
        assertEquals("jane.doe@example.org", ((User) model.get("user")).getEmail());
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", ((User) model.get("user")).getUserUuid());
        assertEquals("janedoe", ((User) model.get("user")).getUserName());
        assertEquals("iloveyou", ((User) model.get("user")).getPassword());
        assertEquals(1L, ((User) model.get("user")).getId().longValue());
        assertTrue(((User) model.get("user")).getIsEnabled());
        Role role = ((User) model.get("user")).getRole();
        assertEquals(1L, role.getId().longValue());
        assertEquals("Role", role.getRole());
        assertNull(role.getUserId());
    }

    /**
     * Method under test: {@link OfferUserController#getOfferNewForm(Model, Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetOfferNewForm9() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.OfferUserController.lambda$getOfferNewForm$6(OfferUserController.java:104)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferNewForm(OfferUserController.java:104)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at java.base/java.util.Objects.requireNonNull(Objects.java:208)
        //       at pl.devfinder.infrastructure.database.repository.EmployerRepository.findByEmployerUuid(EmployerRepository.java:42)
        //       at pl.devfinder.business.EmployerService.findByEmployerUuid(EmployerService.java:60)
        //       at pl.devfinder.api.controller.OfferUserController.getOfferNewForm(OfferUserController.java:105)
        //   See https://diff.blue/R013 to resolve this issue.

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setRole("Role");
        roleEntity.setUserId(new HashSet<>());
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getIsEnabled()).thenReturn(true);
        when(userEntity.getId()).thenReturn(1L);
        when(userEntity.getEmail()).thenReturn("jane.doe@example.org");
        when(userEntity.getPassword()).thenReturn("iloveyou");
        when(userEntity.getUserName()).thenReturn("janedoe");
        when(userEntity.getUserUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userEntity.getRoleId()).thenReturn(roleEntity);
        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        when(userJpaRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(userEntity));
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        CityEntity cityEntity = new CityEntity();
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());
        EmployerEntity employerEntity = mock(EmployerEntity.class);
        when(employerEntity.getAmountOfAvailableOffers()).thenReturn(10);
        when(employerEntity.getNumberOfEmployees()).thenReturn(10);
        when(employerEntity.getEmployerId()).thenReturn(1L);
        when(employerEntity.getCompanyName()).thenReturn("Company Name");
        when(employerEntity.getDescription()).thenReturn("The characteristics of someone or something");
        when(employerEntity.getEmailContact()).thenReturn("jane.doe@example.org");
        when(employerEntity.getEmployerUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(employerEntity.getLogoFilename()).thenReturn("foo.txt");
        when(employerEntity.getPhoneNumber()).thenReturn("6625550144");
        when(employerEntity.getWebsite()).thenReturn("Website");
        when(employerEntity.getCreatedAt())
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        when(employerEntity.getCityId()).thenReturn(cityEntity);
        doNothing().when(employerEntity).setAmountOfAvailableOffers(Mockito.<Integer>any());
        doNothing().when(employerEntity).setCityId(Mockito.<CityEntity>any());
        doNothing().when(employerEntity).setCompanyName(Mockito.<String>any());
        doNothing().when(employerEntity).setCreatedAt(Mockito.<OffsetDateTime>any());
        doNothing().when(employerEntity).setDescription(Mockito.<String>any());
        doNothing().when(employerEntity).setEmailContact(Mockito.<String>any());
        doNothing().when(employerEntity).setEmployerId(Mockito.<Long>any());
        doNothing().when(employerEntity).setEmployerUuid(Mockito.<String>any());
        doNothing().when(employerEntity).setLogoFilename(Mockito.<String>any());
        doNothing().when(employerEntity).setNumberOfEmployees(Mockito.<Integer>any());
        doNothing().when(employerEntity).setPhoneNumber(Mockito.<String>any());
        doNothing().when(employerEntity).setWebsite(Mockito.<String>any());
        employerEntity.setAmountOfAvailableOffers(10);
        employerEntity.setCityId(cityId);
        employerEntity.setCompanyName("Company Name");
        employerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerEntity.setDescription("The characteristics of someone or something");
        employerEntity.setEmailContact("jane.doe@example.org");
        employerEntity.setEmployerId(1L);
        employerEntity.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerEntity.setLogoFilename("foo.txt");
        employerEntity.setNumberOfEmployees(10);
        employerEntity.setPhoneNumber("6625550144");
        employerEntity.setWebsite("Website");
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        when(employerJpaRepository.findByEmployerUuid(Mockito.<String>any())).thenReturn(Optional.of(employerEntity));
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, null);

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService2 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService2,
                offerSkillService, new SkillService(null));

        FileUploadService fileUploadService = new FileUploadService(new StandardReactiveWebEnvironment());
        OfferSkillJpaRepository offerSkillJpaRepository = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService2 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository, offerSkillEntityMapper, new OfferEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository2 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO2 = new EmployerRepository(employerJpaRepository2, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository2 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper2 = new OfferEntityMapperImpl();
        OfferRepository offerDAO2 = new OfferRepository(offerJpaRepository2, offerEntityMapper2,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository, new CandidateEntityMapperImpl())));

        SkillJpaRepository skillJpaRepository = mock(SkillJpaRepository.class);
        when(skillJpaRepository.findAll()).thenReturn(new ArrayList<>());
        SkillService skillService = new SkillService(
                new SkillRepository(skillJpaRepository, mock(SkillEntityMapper.class)));
        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository2 = mock(OfferCriteriaRepository.class);
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository4 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper4 = new OfferEntityMapperImpl();
        OfferRepository offerDAO4 = new OfferRepository(offerJpaRepository4, offerEntityMapper4,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        DataService dataService2 = new DataService(employerDAO3, offerDAO4,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl()));

        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService3 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferSkillJpaRepository offerSkillJpaRepository2 = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper2 = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService3 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository2, offerSkillEntityMapper2, new OfferEntityMapperImpl()));
        OfferService offerService2 = new OfferService(offerDAO3, offerCriteriaRepository2, dataService2, cityService3,
                offerSkillService3,
                new SkillService(new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class))));

        CityJpaRepository cityJpaRepository3 = mock(CityJpaRepository.class);
        CityService cityService4 = new CityService(new CityRepository(cityJpaRepository3, new CityEntityMapperImpl()));
        CityMapper cityMapper = mock(CityMapper.class);
        SkillMapper skillMapper = mock(SkillMapper.class);
        OfferDetailsMapper offerDetailsMapper = mock(OfferDetailsMapper.class);
        OfferUserController offerUserController = new OfferUserController(userService, employerService, offerService2,
                cityService4, cityMapper, skillService, skillMapper, offerDetailsMapper, new OfferRowMapperImpl(),
                mock(OfferUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        offerUserController.getOfferNewForm(model, new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link OfferUserController#createNewOfferRequest(OfferUpdateRequestDTO, BindingResult, Model, Authentication)}
     */
    @Test
    void testCreateNewOfferRequest() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService2 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService2,
                offerSkillService, new SkillService(null));

        FileUploadService fileUploadService = new FileUploadService(new StandardReactiveWebEnvironment());
        OfferSkillJpaRepository offerSkillJpaRepository = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService2 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository, offerSkillEntityMapper, new OfferEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository2 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO2 = new EmployerRepository(employerJpaRepository2, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository2 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper2 = new OfferEntityMapperImpl();
        OfferRepository offerDAO2 = new OfferRepository(offerJpaRepository2, offerEntityMapper2,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository, new CandidateEntityMapperImpl())));

        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository2 = mock(OfferCriteriaRepository.class);
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository4 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper4 = new OfferEntityMapperImpl();
        OfferRepository offerDAO4 = new OfferRepository(offerJpaRepository4, offerEntityMapper4,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        DataService dataService2 = new DataService(employerDAO3, offerDAO4,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl()));

        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService3 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferSkillJpaRepository offerSkillJpaRepository2 = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper2 = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService3 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository2, offerSkillEntityMapper2, new OfferEntityMapperImpl()));
        OfferService offerService2 = new OfferService(offerDAO3, offerCriteriaRepository2, dataService2, cityService3,
                offerSkillService3,
                new SkillService(new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class))));

        CityJpaRepository cityJpaRepository3 = mock(CityJpaRepository.class);
        CityService cityService4 = new CityService(new CityRepository(cityJpaRepository3, new CityEntityMapperImpl()));
        CityMapper cityMapper = mock(CityMapper.class);
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        OfferDetailsMapper offerDetailsMapper = mock(OfferDetailsMapper.class);
        OfferUserController offerUserController = new OfferUserController(userService, employerService, offerService2,
                cityService4, cityMapper, skillService, skillMapper, offerDetailsMapper, new OfferRowMapperImpl(),
                mock(OfferUpdateRequestMapper.class));
        OfferUpdateRequestDTO offerUpdateRequestDTO = new OfferUpdateRequestDTO();
        BindException bindingResult = new BindException("Target", "Object Name");

        assertThrows(NotFoundException.class, () -> offerUserController.createNewOfferRequest(offerUpdateRequestDTO,
                bindingResult, new ConcurrentModel(), null));
    }

    /**
     * Method under test: {@link OfferUserController#createNewOfferRequest(OfferUpdateRequestDTO, BindingResult, Model, Authentication)}
     */
    @Test
    void testCreateNewOfferRequest2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        UserService userService = mock(UserService.class);
        when(userService.findByEmail(Mockito.<String>any())).thenReturn(Optional.empty());
        OfferUserController offerUserController = new OfferUserController(userService, mock(EmployerService.class),
                mock(OfferService.class), mock(CityService.class), mock(CityMapper.class), mock(SkillService.class),
                mock(SkillMapper.class), mock(OfferDetailsMapper.class), mock(OfferRowMapper.class),
                mock(OfferUpdateRequestMapper.class));
        OfferUpdateRequestDTO offerUpdateRequestDTO = new OfferUpdateRequestDTO();
        BindException bindingResult = new BindException("Target", "Object Name");

        ConcurrentModel model = new ConcurrentModel();
        assertThrows(NotFoundException.class, () -> offerUserController.createNewOfferRequest(offerUpdateRequestDTO,
                bindingResult, model, new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userService).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link OfferUserController#createNewOfferRequest(OfferUpdateRequestDTO, BindingResult, Model, Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateNewOfferRequest3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   pl.devfinder.domain.exception.NotFoundException: An error occurred
        //       at pl.devfinder.business.management.Utility.putUserDataToModel(Utility.java:34)
        //       at pl.devfinder.api.controller.OfferUserController.createNewOfferRequest(OfferUserController.java:149)
        //   See https://diff.blue/R013 to resolve this issue.

        UserService userService = mock(UserService.class);
        when(userService.findByEmail(Mockito.<String>any())).thenThrow(new NotFoundException("An error occurred"));
        OfferUserController offerUserController = new OfferUserController(userService, mock(EmployerService.class),
                mock(OfferService.class), mock(CityService.class), mock(CityMapper.class), mock(SkillService.class),
                mock(SkillMapper.class), mock(OfferDetailsMapper.class), mock(OfferRowMapper.class),
                mock(OfferUpdateRequestMapper.class));
        OfferUpdateRequestDTO offerUpdateRequestDTO = new OfferUpdateRequestDTO();
        BindException bindingResult = new BindException("Target", "Object Name");

        ConcurrentModel model = new ConcurrentModel();
        offerUserController.createNewOfferRequest(offerUpdateRequestDTO, bindingResult, model,
                new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link OfferUserController#createNewOfferRequest(OfferUpdateRequestDTO, BindingResult, Model, Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateNewOfferRequest4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.validation.BindingResult.hasErrors()" because "bindingResult" is null
        //       at pl.devfinder.api.controller.OfferUserController.createNewOfferRequest(OfferUserController.java:146)
        //   See https://diff.blue/R013 to resolve this issue.

        UserService userService = mock(UserService.class);
        when(userService.findByEmail(Mockito.<String>any())).thenThrow(new NotFoundException("An error occurred"));
        OfferUserController offerUserController = new OfferUserController(userService, mock(EmployerService.class),
                mock(OfferService.class), mock(CityService.class), mock(CityMapper.class), mock(SkillService.class),
                mock(SkillMapper.class), mock(OfferDetailsMapper.class), mock(OfferRowMapper.class),
                mock(OfferUpdateRequestMapper.class));
        OfferUpdateRequestDTO offerUpdateRequestDTO = new OfferUpdateRequestDTO();
        ConcurrentModel model = new ConcurrentModel();
        offerUserController.createNewOfferRequest(offerUpdateRequestDTO, null, model,
                new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link OfferUserController#createNewOfferRequest(OfferUpdateRequestDTO, BindingResult, Model, Authentication)}
     */
    @Test
    void testCreateNewOfferRequest5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        OfferUserController offerUserController = new OfferUserController(mock(UserService.class),
                mock(EmployerService.class), mock(OfferService.class), mock(CityService.class), mock(CityMapper.class),
                mock(SkillService.class), mock(SkillMapper.class), mock(OfferDetailsMapper.class), mock(OfferRowMapper.class),
                mock(OfferUpdateRequestMapper.class));
        OfferUpdateRequestDTO offerUpdateRequestDTO = new OfferUpdateRequestDTO();
        BeanPropertyBindingResult bindingResult = mock(BeanPropertyBindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        ConcurrentModel model = new ConcurrentModel();
        assertEquals("error", offerUserController.createNewOfferRequest(offerUpdateRequestDTO, bindingResult, model,
                new TestingAuthenticationToken("Principal", "Credentials")));
        verify(bindingResult).hasErrors();
    }

    /**
     * Method under test: {@link OfferUserController#createNewOfferRequest(OfferUpdateRequestDTO, BindingResult, Model, Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateNewOfferRequest6() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "pl.devfinder.domain.OfferUpdateRequest.getCityName()" because "offerUpdateRequest" is null
        //       at pl.devfinder.business.OfferService.createNewOffer(OfferService.java:131)
        //       at pl.devfinder.api.controller.OfferUserController.createNewOfferRequest(OfferUserController.java:158)
        //   See https://diff.blue/R013 to resolve this issue.

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setRole("Role");
        roleEntity.setUserId(new HashSet<>());
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getIsEnabled()).thenReturn(true);
        when(userEntity.getId()).thenReturn(1L);
        when(userEntity.getEmail()).thenReturn("jane.doe@example.org");
        when(userEntity.getPassword()).thenReturn("iloveyou");
        when(userEntity.getUserName()).thenReturn("janedoe");
        when(userEntity.getUserUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userEntity.getRoleId()).thenReturn(roleEntity);
        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        when(userJpaRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(userEntity));
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        EmployerEntity employerEntity = new EmployerEntity();
        employerEntity.setAmountOfAvailableOffers(10);
        employerEntity.setCityId(cityId);
        employerEntity.setCompanyName("Company Name");
        employerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerEntity.setDescription("The characteristics of someone or something");
        employerEntity.setEmailContact("jane.doe@example.org");
        employerEntity.setEmployerId(1L);
        employerEntity.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerEntity.setLogoFilename("foo.txt");
        employerEntity.setNumberOfEmployees(10);
        employerEntity.setPhoneNumber("6625550144");
        employerEntity.setWebsite("Website");
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        when(employerJpaRepository.findByEmployerUuid(Mockito.<String>any())).thenReturn(Optional.of(employerEntity));
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService2 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService2,
                offerSkillService, new SkillService(null));

        FileUploadService fileUploadService = new FileUploadService(new StandardReactiveWebEnvironment());
        OfferSkillJpaRepository offerSkillJpaRepository = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService2 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository, offerSkillEntityMapper, new OfferEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository2 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO2 = new EmployerRepository(employerJpaRepository2, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository2 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper2 = new OfferEntityMapperImpl();
        OfferRepository offerDAO2 = new OfferRepository(offerJpaRepository2, offerEntityMapper2,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository, new CandidateEntityMapperImpl())));

        OfferUpdateRequestMapper offerUpdateRequestMapper = mock(OfferUpdateRequestMapper.class);
        when(offerUpdateRequestMapper.map(Mockito.<OfferUpdateRequestDTO>any())).thenReturn(null);
        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository2 = mock(OfferCriteriaRepository.class);
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository4 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper4 = new OfferEntityMapperImpl();
        OfferRepository offerDAO4 = new OfferRepository(offerJpaRepository4, offerEntityMapper4,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        DataService dataService2 = new DataService(employerDAO3, offerDAO4,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl()));

        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService3 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferSkillJpaRepository offerSkillJpaRepository2 = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper2 = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService3 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository2, offerSkillEntityMapper2, new OfferEntityMapperImpl()));
        OfferService offerService2 = new OfferService(offerDAO3, offerCriteriaRepository2, dataService2, cityService3,
                offerSkillService3,
                new SkillService(new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class))));

        CityJpaRepository cityJpaRepository3 = mock(CityJpaRepository.class);
        CityService cityService4 = new CityService(new CityRepository(cityJpaRepository3, new CityEntityMapperImpl()));
        CityMapper cityMapper = mock(CityMapper.class);
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        OfferDetailsMapper offerDetailsMapper = mock(OfferDetailsMapper.class);
        OfferUserController offerUserController = new OfferUserController(userService, employerService, offerService2,
                cityService4, cityMapper, skillService, skillMapper, offerDetailsMapper, new OfferRowMapperImpl(),
                offerUpdateRequestMapper);
        OfferUpdateRequestDTO offerUpdateRequestDTO = new OfferUpdateRequestDTO();
        BindException bindingResult = new BindException("Target", "Object Name");

        ConcurrentModel model = new ConcurrentModel();
        offerUserController.createNewOfferRequest(offerUpdateRequestDTO, bindingResult, model,
                new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link OfferUserController#createNewOfferRequest(OfferUpdateRequestDTO, BindingResult, Model, Authentication)}
     */
    @Test
    void testCreateNewOfferRequest7() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setRole("Role");
        roleEntity.setUserId(new HashSet<>());
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getIsEnabled()).thenReturn(true);
        when(userEntity.getId()).thenReturn(1L);
        when(userEntity.getEmail()).thenReturn("jane.doe@example.org");
        when(userEntity.getPassword()).thenReturn("iloveyou");
        when(userEntity.getUserName()).thenReturn("janedoe");
        when(userEntity.getUserUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userEntity.getRoleId()).thenReturn(roleEntity);
        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        when(userJpaRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(userEntity));
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        EmployerEntity employerEntity = new EmployerEntity();
        employerEntity.setAmountOfAvailableOffers(10);
        employerEntity.setCityId(cityId);
        employerEntity.setCompanyName("Company Name");
        employerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerEntity.setDescription("The characteristics of someone or something");
        employerEntity.setEmailContact("jane.doe@example.org");
        employerEntity.setEmployerId(1L);
        employerEntity.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerEntity.setLogoFilename("foo.txt");
        employerEntity.setNumberOfEmployees(10);
        employerEntity.setPhoneNumber("6625550144");
        employerEntity.setWebsite("Website");
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        when(employerJpaRepository.findByEmployerUuid(Mockito.<String>any())).thenReturn(Optional.of(employerEntity));
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService2 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService2,
                offerSkillService, new SkillService(null));

        FileUploadService fileUploadService = new FileUploadService(new StandardReactiveWebEnvironment());
        OfferSkillJpaRepository offerSkillJpaRepository = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService2 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository, offerSkillEntityMapper, new OfferEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository2 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO2 = new EmployerRepository(employerJpaRepository2, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository2 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper2 = new OfferEntityMapperImpl();
        OfferRepository offerDAO2 = new OfferRepository(offerJpaRepository2, offerEntityMapper2,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository, new CandidateEntityMapperImpl())));

        OfferUpdateRequestMapper offerUpdateRequestMapper = mock(OfferUpdateRequestMapper.class);
        when(offerUpdateRequestMapper.map(Mockito.<OfferUpdateRequestDTO>any()))
                .thenThrow(new NotFoundException("An error occurred"));
        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository2 = mock(OfferCriteriaRepository.class);
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository4 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper4 = new OfferEntityMapperImpl();
        OfferRepository offerDAO4 = new OfferRepository(offerJpaRepository4, offerEntityMapper4,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        DataService dataService2 = new DataService(employerDAO3, offerDAO4,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl()));

        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService3 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferSkillJpaRepository offerSkillJpaRepository2 = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper2 = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService3 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository2, offerSkillEntityMapper2, new OfferEntityMapperImpl()));
        OfferService offerService2 = new OfferService(offerDAO3, offerCriteriaRepository2, dataService2, cityService3,
                offerSkillService3,
                new SkillService(new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class))));

        CityJpaRepository cityJpaRepository3 = mock(CityJpaRepository.class);
        CityService cityService4 = new CityService(new CityRepository(cityJpaRepository3, new CityEntityMapperImpl()));
        CityMapper cityMapper = mock(CityMapper.class);
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        OfferDetailsMapper offerDetailsMapper = mock(OfferDetailsMapper.class);
        OfferUserController offerUserController = new OfferUserController(userService, employerService, offerService2,
                cityService4, cityMapper, skillService, skillMapper, offerDetailsMapper, new OfferRowMapperImpl(),
                offerUpdateRequestMapper);
        OfferUpdateRequestDTO offerUpdateRequestDTO = new OfferUpdateRequestDTO();
        BindException bindingResult = new BindException("Target", "Object Name");

        ConcurrentModel model = new ConcurrentModel();
        assertThrows(NotFoundException.class, () -> offerUserController.createNewOfferRequest(offerUpdateRequestDTO,
                bindingResult, model, new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userJpaRepository).findByEmail(Mockito.<String>any());
        verify(userEntity).getIsEnabled();
        verify(userEntity).getId();
        verify(userEntity).getEmail();
        verify(userEntity).getPassword();
        verify(userEntity).getUserName();
        verify(userEntity).getUserUuid();
        verify(userEntity).getRoleId();
        verify(employerJpaRepository).findByEmployerUuid(Mockito.<String>any());
        verify(offerUpdateRequestMapper).map(Mockito.<OfferUpdateRequestDTO>any());
    }

    /**
     * Method under test: {@link OfferUserController#createNewOfferRequest(OfferUpdateRequestDTO, BindingResult, Model, Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateNewOfferRequest8() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at java.base/java.util.Objects.requireNonNull(Objects.java:208)
        //       at pl.devfinder.infrastructure.database.repository.UserRepository.findByEmail(UserRepository.java:24)
        //       at pl.devfinder.business.UserService.findByEmail(UserService.java:26)
        //       at pl.devfinder.business.management.Utility.putUserDataToModel(Utility.java:34)
        //       at pl.devfinder.api.controller.OfferUserController.createNewOfferRequest(OfferUserController.java:149)
        //   See https://diff.blue/R013 to resolve this issue.

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setRole("Role");
        roleEntity.setUserId(new HashSet<>());
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getIsEnabled()).thenReturn(true);
        when(userEntity.getId()).thenReturn(1L);
        when(userEntity.getEmail()).thenReturn("jane.doe@example.org");
        when(userEntity.getPassword()).thenReturn("iloveyou");
        when(userEntity.getUserName()).thenReturn("janedoe");
        when(userEntity.getUserUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userEntity.getRoleId()).thenReturn(roleEntity);
        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        when(userJpaRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(userEntity));
        UserRepository userDAO = new UserRepository(userJpaRepository, null);

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        EmployerEntity employerEntity = new EmployerEntity();
        employerEntity.setAmountOfAvailableOffers(10);
        employerEntity.setCityId(cityId);
        employerEntity.setCompanyName("Company Name");
        employerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerEntity.setDescription("The characteristics of someone or something");
        employerEntity.setEmailContact("jane.doe@example.org");
        employerEntity.setEmployerId(1L);
        employerEntity.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerEntity.setLogoFilename("foo.txt");
        employerEntity.setNumberOfEmployees(10);
        employerEntity.setPhoneNumber("6625550144");
        employerEntity.setWebsite("Website");
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        when(employerJpaRepository.findByEmployerUuid(Mockito.<String>any())).thenReturn(Optional.of(employerEntity));
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService2 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService2,
                offerSkillService, new SkillService(null));

        FileUploadService fileUploadService = new FileUploadService(new StandardReactiveWebEnvironment());
        OfferSkillJpaRepository offerSkillJpaRepository = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService2 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository, offerSkillEntityMapper, new OfferEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository2 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO2 = new EmployerRepository(employerJpaRepository2, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository2 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper2 = new OfferEntityMapperImpl();
        OfferRepository offerDAO2 = new OfferRepository(offerJpaRepository2, offerEntityMapper2,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository, new CandidateEntityMapperImpl())));

        OfferUpdateRequestMapper offerUpdateRequestMapper = mock(OfferUpdateRequestMapper.class);
        when(offerUpdateRequestMapper.map(Mockito.<OfferUpdateRequestDTO>any())).thenReturn(null);
        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository2 = mock(OfferCriteriaRepository.class);
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository4 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper4 = new OfferEntityMapperImpl();
        OfferRepository offerDAO4 = new OfferRepository(offerJpaRepository4, offerEntityMapper4,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        DataService dataService2 = new DataService(employerDAO3, offerDAO4,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl()));

        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService3 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferSkillJpaRepository offerSkillJpaRepository2 = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper2 = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService3 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository2, offerSkillEntityMapper2, new OfferEntityMapperImpl()));
        OfferService offerService2 = new OfferService(offerDAO3, offerCriteriaRepository2, dataService2, cityService3,
                offerSkillService3,
                new SkillService(new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class))));

        CityJpaRepository cityJpaRepository3 = mock(CityJpaRepository.class);
        CityService cityService4 = new CityService(new CityRepository(cityJpaRepository3, new CityEntityMapperImpl()));
        CityMapper cityMapper = mock(CityMapper.class);
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        OfferDetailsMapper offerDetailsMapper = mock(OfferDetailsMapper.class);
        OfferUserController offerUserController = new OfferUserController(userService, employerService, offerService2,
                cityService4, cityMapper, skillService, skillMapper, offerDetailsMapper, new OfferRowMapperImpl(),
                offerUpdateRequestMapper);
        OfferUpdateRequestDTO offerUpdateRequestDTO = new OfferUpdateRequestDTO();
        BindException bindingResult = new BindException("Target", "Object Name");

        ConcurrentModel model = new ConcurrentModel();
        offerUserController.createNewOfferRequest(offerUpdateRequestDTO, bindingResult, model,
                new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link OfferUserController#createNewOfferRequest(OfferUpdateRequestDTO, BindingResult, Model, Authentication)}
     */
    @Test
    void testCreateNewOfferRequest9() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setRole("Role");
        roleEntity.setUserId(new HashSet<>());
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getIsEnabled()).thenReturn(true);
        when(userEntity.getId()).thenReturn(1L);
        when(userEntity.getEmail()).thenReturn("jane.doe@example.org");
        when(userEntity.getPassword()).thenReturn("iloveyou");
        when(userEntity.getUserName()).thenReturn("janedoe");
        when(userEntity.getUserUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userEntity.getRoleId()).thenReturn(roleEntity);
        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        when(userJpaRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(userEntity));
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        when(employerJpaRepository.findByEmployerUuid(Mockito.<String>any())).thenReturn(Optional.empty());
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService2 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService2,
                offerSkillService, new SkillService(null));

        FileUploadService fileUploadService = new FileUploadService(new StandardReactiveWebEnvironment());
        OfferSkillJpaRepository offerSkillJpaRepository = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService2 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository, offerSkillEntityMapper, new OfferEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository2 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO2 = new EmployerRepository(employerJpaRepository2, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository2 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper2 = new OfferEntityMapperImpl();
        OfferRepository offerDAO2 = new OfferRepository(offerJpaRepository2, offerEntityMapper2,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository, new CandidateEntityMapperImpl())));

        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository2 = mock(OfferCriteriaRepository.class);
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository4 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper4 = new OfferEntityMapperImpl();
        OfferRepository offerDAO4 = new OfferRepository(offerJpaRepository4, offerEntityMapper4,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        DataService dataService2 = new DataService(employerDAO3, offerDAO4,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl()));

        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService3 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferSkillJpaRepository offerSkillJpaRepository2 = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper2 = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService3 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository2, offerSkillEntityMapper2, new OfferEntityMapperImpl()));
        OfferService offerService2 = new OfferService(offerDAO3, offerCriteriaRepository2, dataService2, cityService3,
                offerSkillService3,
                new SkillService(new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class))));

        CityJpaRepository cityJpaRepository3 = mock(CityJpaRepository.class);
        CityService cityService4 = new CityService(new CityRepository(cityJpaRepository3, new CityEntityMapperImpl()));
        CityMapper cityMapper = mock(CityMapper.class);
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        OfferDetailsMapper offerDetailsMapper = mock(OfferDetailsMapper.class);
        OfferUserController offerUserController = new OfferUserController(userService, employerService, offerService2,
                cityService4, cityMapper, skillService, skillMapper, offerDetailsMapper, new OfferRowMapperImpl(),
                mock(OfferUpdateRequestMapper.class));
        OfferUpdateRequestDTO offerUpdateRequestDTO = new OfferUpdateRequestDTO();
        BindException bindingResult = new BindException("Target", "Object Name");

        ConcurrentModel model = new ConcurrentModel();
        assertThrows(NotFoundException.class, () -> offerUserController.createNewOfferRequest(offerUpdateRequestDTO,
                bindingResult, model, new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userJpaRepository).findByEmail(Mockito.<String>any());
        verify(userEntity).getIsEnabled();
        verify(userEntity).getId();
        verify(userEntity).getEmail();
        verify(userEntity).getPassword();
        verify(userEntity).getUserName();
        verify(userEntity).getUserUuid();
        verify(userEntity).getRoleId();
        verify(employerJpaRepository).findByEmployerUuid(Mockito.<String>any());
    }

    /**
     * Method under test: {@link OfferUserController#createNewOfferRequest(OfferUpdateRequestDTO, BindingResult, Model, Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateNewOfferRequest10() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at java.base/java.util.Objects.requireNonNull(Objects.java:208)
        //       at pl.devfinder.infrastructure.database.repository.EmployerRepository.findByEmployerUuid(EmployerRepository.java:42)
        //       at pl.devfinder.business.EmployerService.findByEmployerUuid(EmployerService.java:60)
        //       at pl.devfinder.api.controller.OfferUserController.createNewOfferRequest(OfferUserController.java:151)
        //   See https://diff.blue/R013 to resolve this issue.

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setRole("Role");
        roleEntity.setUserId(new HashSet<>());
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getIsEnabled()).thenReturn(true);
        when(userEntity.getId()).thenReturn(1L);
        when(userEntity.getEmail()).thenReturn("jane.doe@example.org");
        when(userEntity.getPassword()).thenReturn("iloveyou");
        when(userEntity.getUserName()).thenReturn("janedoe");
        when(userEntity.getUserUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userEntity.getRoleId()).thenReturn(roleEntity);
        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        when(userJpaRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(userEntity));
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        CityEntity cityEntity = new CityEntity();
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());
        EmployerEntity employerEntity = mock(EmployerEntity.class);
        when(employerEntity.getAmountOfAvailableOffers()).thenReturn(10);
        when(employerEntity.getNumberOfEmployees()).thenReturn(10);
        when(employerEntity.getEmployerId()).thenReturn(1L);
        when(employerEntity.getCompanyName()).thenReturn("Company Name");
        when(employerEntity.getDescription()).thenReturn("The characteristics of someone or something");
        when(employerEntity.getEmailContact()).thenReturn("jane.doe@example.org");
        when(employerEntity.getEmployerUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(employerEntity.getLogoFilename()).thenReturn("foo.txt");
        when(employerEntity.getPhoneNumber()).thenReturn("6625550144");
        when(employerEntity.getWebsite()).thenReturn("Website");
        when(employerEntity.getCreatedAt())
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        when(employerEntity.getCityId()).thenReturn(cityEntity);
        doNothing().when(employerEntity).setAmountOfAvailableOffers(Mockito.<Integer>any());
        doNothing().when(employerEntity).setCityId(Mockito.<CityEntity>any());
        doNothing().when(employerEntity).setCompanyName(Mockito.<String>any());
        doNothing().when(employerEntity).setCreatedAt(Mockito.<OffsetDateTime>any());
        doNothing().when(employerEntity).setDescription(Mockito.<String>any());
        doNothing().when(employerEntity).setEmailContact(Mockito.<String>any());
        doNothing().when(employerEntity).setEmployerId(Mockito.<Long>any());
        doNothing().when(employerEntity).setEmployerUuid(Mockito.<String>any());
        doNothing().when(employerEntity).setLogoFilename(Mockito.<String>any());
        doNothing().when(employerEntity).setNumberOfEmployees(Mockito.<Integer>any());
        doNothing().when(employerEntity).setPhoneNumber(Mockito.<String>any());
        doNothing().when(employerEntity).setWebsite(Mockito.<String>any());
        employerEntity.setAmountOfAvailableOffers(10);
        employerEntity.setCityId(cityId);
        employerEntity.setCompanyName("Company Name");
        employerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerEntity.setDescription("The characteristics of someone or something");
        employerEntity.setEmailContact("jane.doe@example.org");
        employerEntity.setEmployerId(1L);
        employerEntity.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerEntity.setLogoFilename("foo.txt");
        employerEntity.setNumberOfEmployees(10);
        employerEntity.setPhoneNumber("6625550144");
        employerEntity.setWebsite("Website");
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        when(employerJpaRepository.findByEmployerUuid(Mockito.<String>any())).thenReturn(Optional.of(employerEntity));
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, null);

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService2 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService2,
                offerSkillService, new SkillService(null));

        FileUploadService fileUploadService = new FileUploadService(new StandardReactiveWebEnvironment());
        OfferSkillJpaRepository offerSkillJpaRepository = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService2 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository, offerSkillEntityMapper, new OfferEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository2 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO2 = new EmployerRepository(employerJpaRepository2, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository2 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper2 = new OfferEntityMapperImpl();
        OfferRepository offerDAO2 = new OfferRepository(offerJpaRepository2, offerEntityMapper2,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository, new CandidateEntityMapperImpl())));

        OfferUpdateRequestMapper offerUpdateRequestMapper = mock(OfferUpdateRequestMapper.class);
        when(offerUpdateRequestMapper.map(Mockito.<OfferUpdateRequestDTO>any())).thenReturn(null);
        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository2 = mock(OfferCriteriaRepository.class);
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository4 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper4 = new OfferEntityMapperImpl();
        OfferRepository offerDAO4 = new OfferRepository(offerJpaRepository4, offerEntityMapper4,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        DataService dataService2 = new DataService(employerDAO3, offerDAO4,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl()));

        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService3 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferSkillJpaRepository offerSkillJpaRepository2 = mock(OfferSkillJpaRepository.class);
        OfferSkillEntityMapper offerSkillEntityMapper2 = mock(OfferSkillEntityMapper.class);
        OfferSkillService offerSkillService3 = new OfferSkillService(
                new OfferSkillRepository(offerSkillJpaRepository2, offerSkillEntityMapper2, new OfferEntityMapperImpl()));
        OfferService offerService2 = new OfferService(offerDAO3, offerCriteriaRepository2, dataService2, cityService3,
                offerSkillService3,
                new SkillService(new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class))));

        CityJpaRepository cityJpaRepository3 = mock(CityJpaRepository.class);
        CityService cityService4 = new CityService(new CityRepository(cityJpaRepository3, new CityEntityMapperImpl()));
        CityMapper cityMapper = mock(CityMapper.class);
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        OfferDetailsMapper offerDetailsMapper = mock(OfferDetailsMapper.class);
        OfferUserController offerUserController = new OfferUserController(userService, employerService, offerService2,
                cityService4, cityMapper, skillService, skillMapper, offerDetailsMapper, new OfferRowMapperImpl(),
                offerUpdateRequestMapper);
        OfferUpdateRequestDTO offerUpdateRequestDTO = new OfferUpdateRequestDTO();
        BindException bindingResult = new BindException("Target", "Object Name");

        ConcurrentModel model = new ConcurrentModel();
        offerUserController.createNewOfferRequest(offerUpdateRequestDTO, bindingResult, model,
                new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link OfferUserController#createNewOfferRequest(OfferUpdateRequestDTO, BindingResult, Model, Authentication)}
     */
    @Test
    void testCreateNewOfferRequest11() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        RoleEntity roleEntity = mock(RoleEntity.class);
        when(roleEntity.getId()).thenReturn(1L);
        when(roleEntity.getRole()).thenReturn("Role");
        doNothing().when(roleEntity).setId(Mockito.<Long>any());
        doNothing().when(roleEntity).setRole(Mockito.<String>any());
        doNothing().when(roleEntity).setUserId(Mockito.<Set<UserEntity>>any());
        roleEntity.setId(1L);
        roleEntity.setRole("Role");
        roleEntity.setUserId(new HashSet<>());
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getIsEnabled()).thenReturn(true);
        when(userEntity.getId()).thenReturn(1L);
        when(userEntity.getEmail()).thenReturn("jane.doe@example.org");
        when(userEntity.getPassword()).thenReturn("iloveyou");
        when(userEntity.getUserName()).thenReturn("janedoe");
        when(userEntity.getUserUuid()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userEntity.getRoleId()).thenReturn(roleEntity);
        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        when(userJpaRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(userEntity));
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        EmployerService employerService = mock(EmployerService.class);
        when(employerService.findByEmployerUuid(Mockito.<String>any())).thenReturn(Optional.empty());
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

        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService2 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        CityMapper cityMapper = mock(CityMapper.class);
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        OfferDetailsMapper offerDetailsMapper = mock(OfferDetailsMapper.class);
        OfferUserController offerUserController = new OfferUserController(userService, employerService, offerService,
                cityService2, cityMapper, skillService, skillMapper, offerDetailsMapper, new OfferRowMapperImpl(),
                mock(OfferUpdateRequestMapper.class));
        OfferUpdateRequestDTO offerUpdateRequestDTO = new OfferUpdateRequestDTO();
        BindException bindingResult = new BindException("Target", "Object Name");

        ConcurrentModel model = new ConcurrentModel();
        assertThrows(NotFoundException.class, () -> offerUserController.createNewOfferRequest(offerUpdateRequestDTO,
                bindingResult, model, new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userJpaRepository).findByEmail(Mockito.<String>any());
        verify(userEntity).getIsEnabled();
        verify(userEntity).getId();
        verify(userEntity).getEmail();
        verify(userEntity).getPassword();
        verify(userEntity).getUserName();
        verify(userEntity).getUserUuid();
        verify(userEntity).getRoleId();
        verify(roleEntity).getId();
        verify(roleEntity).getRole();
        verify(roleEntity).setId(Mockito.<Long>any());
        verify(roleEntity).setRole(Mockito.<String>any());
        verify(roleEntity).setUserId(Mockito.<Set<UserEntity>>any());
        verify(employerService).findByEmployerUuid(Mockito.<String>any());
    }

    /**
     * Method under test: {@link OfferUserController#deleteCandidateProfile(Long, Model, Authentication)}
     */
    @Test
    void testDeleteCandidateProfile() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders
                .formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(offerUserController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

