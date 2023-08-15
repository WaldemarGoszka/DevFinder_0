package pl.devfinder.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
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
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import pl.devfinder.api.dto.CandidateDetailsDTO;
import pl.devfinder.api.dto.CandidateUpdateRequestDTO;
import pl.devfinder.api.dto.mapper.CandidateDetailsMapper;
import pl.devfinder.api.dto.mapper.CandidateDetailsMapperImpl;
import pl.devfinder.api.dto.mapper.CandidateUpdateRequestMapper;
import pl.devfinder.api.dto.mapper.SkillMapper;
import pl.devfinder.business.CandidateService;
import pl.devfinder.business.CandidateSkillService;
import pl.devfinder.business.CityService;
import pl.devfinder.business.DataService;
import pl.devfinder.business.EmployerService;
import pl.devfinder.business.FileUploadService;
import pl.devfinder.business.OfferService;
import pl.devfinder.business.OfferSkillService;
import pl.devfinder.business.SkillService;
import pl.devfinder.business.UserService;
import pl.devfinder.domain.City;
import pl.devfinder.domain.Employer;
import pl.devfinder.domain.Role;
import pl.devfinder.domain.User;
import pl.devfinder.domain.exception.NotFoundException;
import pl.devfinder.infrastructure.database.entity.CandidateEntity;
import pl.devfinder.infrastructure.database.entity.CityEntity;
import pl.devfinder.infrastructure.database.entity.EmployerEntity;
import pl.devfinder.infrastructure.database.entity.RoleEntity;
import pl.devfinder.infrastructure.database.entity.UserEntity;
import pl.devfinder.infrastructure.database.repository.CandidateRepository;
import pl.devfinder.infrastructure.database.repository.CandidateSkillRepository;
import pl.devfinder.infrastructure.database.repository.CityRepository;
import pl.devfinder.infrastructure.database.repository.EmailVerificationTokenRepository;
import pl.devfinder.infrastructure.database.repository.EmployerRepository;
import pl.devfinder.infrastructure.database.repository.OfferRepository;
import pl.devfinder.infrastructure.database.repository.OfferSkillRepository;
import pl.devfinder.infrastructure.database.repository.SkillRepository;
import pl.devfinder.infrastructure.database.repository.UserRepository;
import pl.devfinder.infrastructure.database.repository.criteria.CandidateCriteriaRepository;
import pl.devfinder.infrastructure.database.repository.criteria.EmployerCriteriaRepository;
import pl.devfinder.infrastructure.database.repository.criteria.OfferCriteriaRepository;
import pl.devfinder.infrastructure.database.repository.jpa.CandidateJpaRepository;
import pl.devfinder.infrastructure.database.repository.jpa.CandidateSkillJpaRepository;
import pl.devfinder.infrastructure.database.repository.jpa.CityJpaRepository;
import pl.devfinder.infrastructure.database.repository.jpa.EmailVerificationTokenJpaRepository;
import pl.devfinder.infrastructure.database.repository.jpa.EmployerJpaRepository;
import pl.devfinder.infrastructure.database.repository.jpa.OfferJpaRepository;
import pl.devfinder.infrastructure.database.repository.jpa.OfferSkillJpaRepository;
import pl.devfinder.infrastructure.database.repository.jpa.SkillJpaRepository;
import pl.devfinder.infrastructure.database.repository.jpa.UserJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.CandidateEntityMapperImpl;
import pl.devfinder.infrastructure.database.repository.mapper.CandidateSkillEntityMapperImpl;
import pl.devfinder.infrastructure.database.repository.mapper.CityEntityMapperImpl;
import pl.devfinder.infrastructure.database.repository.mapper.EmailVerificationTokenMapperImpl;
import pl.devfinder.infrastructure.database.repository.mapper.EmployerEntityMapperImpl;
import pl.devfinder.infrastructure.database.repository.mapper.OfferEntityMapperImpl;
import pl.devfinder.infrastructure.database.repository.mapper.OfferSkillEntityMapper;
import pl.devfinder.infrastructure.database.repository.mapper.SkillEntityMapper;
import pl.devfinder.infrastructure.database.repository.mapper.UserEntityMapperImpl;

@ContextConfiguration(classes = {CandidateUserController.class})
@ExtendWith(SpringExtension.class)
class CandidateUserControllerDiffBlueTest {
    @MockBean
    private CandidateDetailsMapper candidateDetailsMapper;

    @MockBean
    private CandidateService candidateService;

    @MockBean
    private CandidateUpdateRequestMapper candidateUpdateRequestMapper;

    @Autowired
    private CandidateUserController candidateUserController;

    @MockBean
    private SkillMapper skillMapper;

    @MockBean
    private SkillService skillService;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link CandidateUserController#getCandidateProfile(Model, Authentication)}
     */
    @Test
    void testGetCandidateProfile() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.CandidateUserController.lambda$getCandidateProfile$0(CandidateUserController.java:50)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.CandidateUserController.getCandidateProfile(CandidateUserController.java:50)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        CandidateRepository candidateDAO = new CandidateRepository(candidateJpaRepository,
                new CandidateEntityMapperImpl());

        CandidateCriteriaRepository candidateCriteriaRepository = mock(CandidateCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        SkillService skillService2 = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        CandidateSkillJpaRepository candidateSkillJpaRepository = mock(CandidateSkillJpaRepository.class);
        CandidateSkillEntityMapperImpl candidateSkillEntityMapper = new CandidateSkillEntityMapperImpl();
        CandidateSkillService candidateSkillService = new CandidateSkillService(new CandidateSkillRepository(
                candidateSkillJpaRepository, candidateSkillEntityMapper, new CandidateEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityService cityService2 = new CityService(null);
        OfferService offerService = new OfferService(null, mock(OfferCriteriaRepository.class), null, null, null, null);

        FileUploadService fileUploadService = new FileUploadService(new StandardReactiveWebEnvironment());
        OfferSkillService offerSkillService = new OfferSkillService(null);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService2,
                offerService, fileUploadService, offerSkillService, new DataService(null, null, null));

        FileUploadService fileUploadService2 = new FileUploadService(new StandardReactiveWebEnvironment());
        EmployerJpaRepository employerJpaRepository2 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO2 = new EmployerRepository(employerJpaRepository2, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        CandidateService candidateService = new CandidateService(candidateDAO, candidateCriteriaRepository, cityService,
                skillService2, candidateSkillService, employerService, fileUploadService2, new DataService(employerDAO2,
                offerDAO, new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl())));

        CandidateUserController candidateUserController = new CandidateUserController(userService, skillService,
                skillMapper, candidateService, new CandidateDetailsMapperImpl(), mock(CandidateUpdateRequestMapper.class));
        assertThrows(NotFoundException.class,
                () -> candidateUserController.getCandidateProfile(new ConcurrentModel(), null));
    }

    /**
     * Method under test: {@link CandidateUserController#getCandidateProfile(Model, Authentication)}
     */
    @Test
    void testGetCandidateProfile2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.CandidateUserController.lambda$getCandidateProfile$0(CandidateUserController.java:50)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.CandidateUserController.getCandidateProfile(CandidateUserController.java:50)
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

        CityEntity cityEntity = new CityEntity();
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());

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
        CandidateEntity candidateEntity = mock(CandidateEntity.class);
        when(candidateEntity.getOpenToRemoteJob()).thenReturn(true);
        when(candidateEntity.getYearsOfExperience()).thenReturn(1);
        when(candidateEntity.getCandidateId()).thenReturn(1L);
        when(candidateEntity.getCandidateUuid()).thenReturn("2020-03-01");
        when(candidateEntity.getCvFilename()).thenReturn("foo.txt");
        when(candidateEntity.getEducation()).thenReturn("Education");
        when(candidateEntity.getEmailContact()).thenReturn("jane.doe@example.org");
        when(candidateEntity.getExperienceLevel()).thenReturn("Experience Level");
        when(candidateEntity.getFirstName()).thenReturn("Jane");
        when(candidateEntity.getForeignLanguage()).thenReturn("en");
        when(candidateEntity.getGithubLink()).thenReturn("Github Link");
        when(candidateEntity.getHobby()).thenReturn("Hobby");
        when(candidateEntity.getLastName()).thenReturn("Doe");
        when(candidateEntity.getLinkedinLink()).thenReturn("Linkedin Link");
        when(candidateEntity.getOtherSkills()).thenReturn("Other Skills");
        when(candidateEntity.getPhoneNumber()).thenReturn("6625550144");
        when(candidateEntity.getPhotoFilename()).thenReturn("foo.txt");
        when(candidateEntity.getStatus()).thenReturn("Status");
        BigDecimal valueOfResult = BigDecimal.valueOf(1L);
        when(candidateEntity.getSalaryMin()).thenReturn(valueOfResult);
        when(candidateEntity.getCreatedAt())
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        when(candidateEntity.getCandidateSkills()).thenReturn(new HashSet<>());
        when(candidateEntity.getResidenceCityId()).thenReturn(cityEntity);
        when(candidateEntity.getEmployerId()).thenReturn(employerEntity);
        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        when(candidateJpaRepository.findByCandidateUuid(Mockito.<String>any())).thenReturn(Optional.of(candidateEntity));
        CandidateRepository candidateDAO = new CandidateRepository(candidateJpaRepository,
                new CandidateEntityMapperImpl());

        CandidateCriteriaRepository candidateCriteriaRepository = mock(CandidateCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        CandidateSkillJpaRepository candidateSkillJpaRepository = mock(CandidateSkillJpaRepository.class);
        CandidateSkillEntityMapperImpl candidateSkillEntityMapper = new CandidateSkillEntityMapperImpl();
        CandidateSkillService candidateSkillService = new CandidateSkillService(new CandidateSkillRepository(
                candidateSkillJpaRepository, candidateSkillEntityMapper, new CandidateEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService2 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService3 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService3,
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

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService2,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl())));

        FileUploadService fileUploadService2 = new FileUploadService(new StandardReactiveWebEnvironment());
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository3 = mock(CandidateJpaRepository.class);
        CandidateService candidateService = new CandidateService(candidateDAO, candidateCriteriaRepository, cityService,
                skillService, candidateSkillService, employerService, fileUploadService2, new DataService(employerDAO3,
                offerDAO3, new CandidateRepository(candidateJpaRepository3, new CandidateEntityMapperImpl())));

        SkillService skillService2 = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        CandidateUserController candidateUserController = new CandidateUserController(userService, skillService2,
                skillMapper, candidateService, new CandidateDetailsMapperImpl(), mock(CandidateUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        assertEquals("candidate/profile", candidateUserController.getCandidateProfile(model,
                new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userJpaRepository).findByEmail(Mockito.<String>any());
        verify(userEntity).getIsEnabled();
        verify(userEntity).getId();
        verify(userEntity).getEmail();
        verify(userEntity).getPassword();
        verify(userEntity).getUserName();
        verify(userEntity).getUserUuid();
        verify(userEntity).getRoleId();
        verify(candidateJpaRepository).findByCandidateUuid(Mockito.<String>any());
        verify(candidateEntity).getOpenToRemoteJob();
        verify(candidateEntity).getYearsOfExperience();
        verify(candidateEntity).getCandidateId();
        verify(candidateEntity).getCandidateUuid();
        verify(candidateEntity).getCvFilename();
        verify(candidateEntity).getEducation();
        verify(candidateEntity).getEmailContact();
        verify(candidateEntity).getExperienceLevel();
        verify(candidateEntity).getFirstName();
        verify(candidateEntity).getForeignLanguage();
        verify(candidateEntity).getGithubLink();
        verify(candidateEntity).getHobby();
        verify(candidateEntity).getLastName();
        verify(candidateEntity).getLinkedinLink();
        verify(candidateEntity).getOtherSkills();
        verify(candidateEntity).getPhoneNumber();
        verify(candidateEntity).getPhotoFilename();
        verify(candidateEntity).getStatus();
        verify(candidateEntity).getSalaryMin();
        verify(candidateEntity).getCreatedAt();
        verify(candidateEntity).getCandidateSkills();
        verify(candidateEntity).getResidenceCityId();
        verify(candidateEntity).getEmployerId();
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", ((User) model.get("user")).getUserUuid());
        assertEquals("janedoe", ((User) model.get("user")).getUserName());
        assertEquals("iloveyou", ((User) model.get("user")).getPassword());
        assertEquals(1, ((CandidateDetailsDTO) model.get("candidateDetailsDTO")).getYearsOfExperience().intValue());
        assertTrue(((CandidateDetailsDTO) model.get("candidateDetailsDTO")).getCandidateSkills().isEmpty());
        assertEquals("foo.txt", ((CandidateDetailsDTO) model.get("candidateDetailsDTO")).getCvFilename());
        assertTrue(((User) model.get("user")).getIsEnabled());
        assertEquals(1L, ((User) model.get("user")).getId().longValue());
        assertEquals("jane.doe@example.org", ((User) model.get("user")).getEmail());
        assertEquals("Status", ((CandidateDetailsDTO) model.get("candidateDetailsDTO")).getStatus());
        BigDecimal expectedSalaryMin = valueOfResult.ONE;
        assertSame(expectedSalaryMin, ((CandidateDetailsDTO) model.get("candidateDetailsDTO")).getSalaryMin());
        assertNull(((CandidateDetailsDTO) model.get("candidateDetailsDTO")).getResidenceCityName());
        assertEquals("en", ((CandidateDetailsDTO) model.get("candidateDetailsDTO")).getForeignLanguage());
        assertEquals("Github Link", ((CandidateDetailsDTO) model.get("candidateDetailsDTO")).getGithubLink());
        assertEquals("Education", ((CandidateDetailsDTO) model.get("candidateDetailsDTO")).getEducation());
        assertEquals("foo.txt", ((CandidateDetailsDTO) model.get("candidateDetailsDTO")).getPhotoFilename());
        assertEquals("Hobby", ((CandidateDetailsDTO) model.get("candidateDetailsDTO")).getHobby());
        assertEquals("Doe", ((CandidateDetailsDTO) model.get("candidateDetailsDTO")).getLastName());
        assertEquals("6625550144", ((CandidateDetailsDTO) model.get("candidateDetailsDTO")).getPhoneNumber());
        assertEquals("Other Skills", ((CandidateDetailsDTO) model.get("candidateDetailsDTO")).getOtherSkills());
        assertTrue(((CandidateDetailsDTO) model.get("candidateDetailsDTO")).getOpenToRemoteJob());
        assertNull(((CandidateDetailsDTO) model.get("candidateDetailsDTO")).getCandidateSkillsNames());
        assertEquals("jane.doe@example.org", ((CandidateDetailsDTO) model.get("candidateDetailsDTO")).getEmailContact());
        assertEquals("Linkedin Link", ((CandidateDetailsDTO) model.get("candidateDetailsDTO")).getLinkedinLink());
        assertEquals(1L, ((CandidateDetailsDTO) model.get("candidateDetailsDTO")).getCandidateId().longValue());
        assertEquals("2020-03-01", ((CandidateDetailsDTO) model.get("candidateDetailsDTO")).getCandidateUuid());
        assertEquals("Experience Level", ((CandidateDetailsDTO) model.get("candidateDetailsDTO")).getExperienceLevel());
        assertNull(((CandidateDetailsDTO) model.get("candidateDetailsDTO")).getFileCv());
        assertEquals("Z", ((CandidateDetailsDTO) model.get("candidateDetailsDTO")).getCreatedAt().getOffset().toString());
        assertNull(((CandidateDetailsDTO) model.get("candidateDetailsDTO")).getFilePhoto());
        assertEquals("Jane", ((CandidateDetailsDTO) model.get("candidateDetailsDTO")).getFirstName());
        Employer employerId = ((CandidateDetailsDTO) model.get("candidateDetailsDTO")).getEmployerId();
        assertEquals("Company Name", employerId.getCompanyName());
        assertEquals("The characteristics of someone or something", employerId.getDescription());
        Role role = ((User) model.get("user")).getRole();
        assertNull(role.getUserId());
        City residenceCityId = ((CandidateDetailsDTO) model.get("candidateDetailsDTO")).getResidenceCityId();
        assertEquals(1L, residenceCityId.getCityId().longValue());
        assertEquals("Role", role.getRole());
        assertNull(employerId.getCityId());
        assertEquals("Z", employerId.getCreatedAt().getOffset().toString());
        assertEquals(1L, role.getId().longValue());
        assertEquals(10, employerId.getAmountOfAvailableOffers().intValue());
        assertEquals("Oxford", residenceCityId.getCityName());
    }

    /**
     * Method under test: {@link CandidateUserController#getCandidateProfile(Model, Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCandidateProfile3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.CandidateUserController.lambda$getCandidateProfile$0(CandidateUserController.java:50)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.CandidateUserController.getCandidateProfile(CandidateUserController.java:50)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   pl.devfinder.domain.exception.NotFoundException: An error occurred
        //       at pl.devfinder.infrastructure.database.repository.mapper.CandidateEntityMapperImpl.mapFromEntity(CandidateEntityMapperImpl.java:34)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //       at pl.devfinder.infrastructure.database.repository.CandidateRepository.findByCandidateUuid(CandidateRepository.java:42)
        //       at pl.devfinder.business.CandidateService.findByCandidateUuid(CandidateService.java:46)
        //       at pl.devfinder.api.controller.CandidateUserController.getCandidateProfile(CandidateUserController.java:51)
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

        CandidateEntity candidateEntity = mock(CandidateEntity.class);
        when(candidateEntity.getOpenToRemoteJob()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getYearsOfExperience()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getCandidateId()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getCandidateUuid()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getCvFilename()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getEducation()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getEmailContact()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getExperienceLevel()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getFirstName()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getForeignLanguage()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getGithubLink()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getHobby()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getLastName()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getLinkedinLink()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getOtherSkills()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getPhoneNumber()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getPhotoFilename()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getStatus()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getSalaryMin()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getCreatedAt()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getCandidateSkills()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getResidenceCityId()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getEmployerId()).thenThrow(new NotFoundException("An error occurred"));
        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        when(candidateJpaRepository.findByCandidateUuid(Mockito.<String>any())).thenReturn(Optional.of(candidateEntity));
        CandidateRepository candidateDAO = new CandidateRepository(candidateJpaRepository,
                new CandidateEntityMapperImpl());

        CandidateCriteriaRepository candidateCriteriaRepository = mock(CandidateCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        CandidateSkillJpaRepository candidateSkillJpaRepository = mock(CandidateSkillJpaRepository.class);
        CandidateSkillEntityMapperImpl candidateSkillEntityMapper = new CandidateSkillEntityMapperImpl();
        CandidateSkillService candidateSkillService = new CandidateSkillService(new CandidateSkillRepository(
                candidateSkillJpaRepository, candidateSkillEntityMapper, new CandidateEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService2 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService3 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService3,
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

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService2,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl())));

        FileUploadService fileUploadService2 = new FileUploadService(new StandardReactiveWebEnvironment());
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository3 = mock(CandidateJpaRepository.class);
        CandidateService candidateService = new CandidateService(candidateDAO, candidateCriteriaRepository, cityService,
                skillService, candidateSkillService, employerService, fileUploadService2, new DataService(employerDAO3,
                offerDAO3, new CandidateRepository(candidateJpaRepository3, new CandidateEntityMapperImpl())));

        SkillService skillService2 = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        CandidateUserController candidateUserController = new CandidateUserController(userService, skillService2,
                skillMapper, candidateService, new CandidateDetailsMapperImpl(), mock(CandidateUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        candidateUserController.getCandidateProfile(model, new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link CandidateUserController#getCandidateProfile(Model, Authentication)}
     */
    @Test
    void testGetCandidateProfile4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.CandidateUserController.lambda$getCandidateProfile$0(CandidateUserController.java:50)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.CandidateUserController.getCandidateProfile(CandidateUserController.java:50)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        when(userJpaRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.empty());
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        CandidateRepository candidateDAO = new CandidateRepository(candidateJpaRepository,
                new CandidateEntityMapperImpl());

        CandidateCriteriaRepository candidateCriteriaRepository = mock(CandidateCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        SkillService skillService2 = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        CandidateSkillJpaRepository candidateSkillJpaRepository = mock(CandidateSkillJpaRepository.class);
        CandidateSkillEntityMapperImpl candidateSkillEntityMapper = new CandidateSkillEntityMapperImpl();
        CandidateSkillService candidateSkillService = new CandidateSkillService(new CandidateSkillRepository(
                candidateSkillJpaRepository, candidateSkillEntityMapper, new CandidateEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService2 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService3 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService3,
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

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService2,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl())));

        FileUploadService fileUploadService2 = new FileUploadService(new StandardReactiveWebEnvironment());
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository3 = mock(CandidateJpaRepository.class);
        CandidateService candidateService = new CandidateService(candidateDAO, candidateCriteriaRepository, cityService,
                skillService2, candidateSkillService, employerService, fileUploadService2, new DataService(employerDAO3,
                offerDAO3, new CandidateRepository(candidateJpaRepository3, new CandidateEntityMapperImpl())));

        CandidateUserController candidateUserController = new CandidateUserController(userService, skillService,
                skillMapper, candidateService, new CandidateDetailsMapperImpl(), mock(CandidateUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        assertThrows(NotFoundException.class, () -> candidateUserController.getCandidateProfile(model,
                new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userJpaRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CandidateUserController#getCandidateProfile(Model, Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCandidateProfile5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.CandidateUserController.lambda$getCandidateProfile$0(CandidateUserController.java:50)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.CandidateUserController.getCandidateProfile(CandidateUserController.java:50)
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
        //       at pl.devfinder.api.controller.CandidateUserController.getCandidateProfile(CandidateUserController.java:49)
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

        CityEntity cityEntity = new CityEntity();
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());

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
        CandidateEntity candidateEntity = mock(CandidateEntity.class);
        when(candidateEntity.getOpenToRemoteJob()).thenReturn(true);
        when(candidateEntity.getYearsOfExperience()).thenReturn(1);
        when(candidateEntity.getCandidateId()).thenReturn(1L);
        when(candidateEntity.getCandidateUuid()).thenReturn("2020-03-01");
        when(candidateEntity.getCvFilename()).thenReturn("foo.txt");
        when(candidateEntity.getEducation()).thenReturn("Education");
        when(candidateEntity.getEmailContact()).thenReturn("jane.doe@example.org");
        when(candidateEntity.getExperienceLevel()).thenReturn("Experience Level");
        when(candidateEntity.getFirstName()).thenReturn("Jane");
        when(candidateEntity.getForeignLanguage()).thenReturn("en");
        when(candidateEntity.getGithubLink()).thenReturn("Github Link");
        when(candidateEntity.getHobby()).thenReturn("Hobby");
        when(candidateEntity.getLastName()).thenReturn("Doe");
        when(candidateEntity.getLinkedinLink()).thenReturn("Linkedin Link");
        when(candidateEntity.getOtherSkills()).thenReturn("Other Skills");
        when(candidateEntity.getPhoneNumber()).thenReturn("6625550144");
        when(candidateEntity.getPhotoFilename()).thenReturn("foo.txt");
        when(candidateEntity.getStatus()).thenReturn("Status");
        when(candidateEntity.getSalaryMin()).thenReturn(BigDecimal.valueOf(1L));
        when(candidateEntity.getCreatedAt())
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        when(candidateEntity.getCandidateSkills()).thenReturn(new HashSet<>());
        when(candidateEntity.getResidenceCityId()).thenReturn(cityEntity);
        when(candidateEntity.getEmployerId()).thenReturn(employerEntity);
        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        when(candidateJpaRepository.findByCandidateUuid(Mockito.<String>any())).thenReturn(Optional.of(candidateEntity));
        CandidateRepository candidateDAO = new CandidateRepository(candidateJpaRepository,
                new CandidateEntityMapperImpl());

        CandidateCriteriaRepository candidateCriteriaRepository = mock(CandidateCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        CandidateSkillJpaRepository candidateSkillJpaRepository = mock(CandidateSkillJpaRepository.class);
        CandidateSkillEntityMapperImpl candidateSkillEntityMapper = new CandidateSkillEntityMapperImpl();
        CandidateSkillService candidateSkillService = new CandidateSkillService(new CandidateSkillRepository(
                candidateSkillJpaRepository, candidateSkillEntityMapper, new CandidateEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService2 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService3 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService3,
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

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService2,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl())));

        FileUploadService fileUploadService2 = new FileUploadService(new StandardReactiveWebEnvironment());
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository3 = mock(CandidateJpaRepository.class);
        CandidateService candidateService = new CandidateService(candidateDAO, candidateCriteriaRepository, cityService,
                skillService, candidateSkillService, employerService, fileUploadService2, new DataService(employerDAO3,
                offerDAO3, new CandidateRepository(candidateJpaRepository3, new CandidateEntityMapperImpl())));

        SkillService skillService2 = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        CandidateUserController candidateUserController = new CandidateUserController(userService, skillService2,
                skillMapper, candidateService, new CandidateDetailsMapperImpl(), mock(CandidateUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        candidateUserController.getCandidateProfile(model, new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link CandidateUserController#getCandidateProfile(Model, Authentication)}
     */
    @Test
    void testGetCandidateProfile6() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.CandidateUserController.lambda$getCandidateProfile$0(CandidateUserController.java:50)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.CandidateUserController.getCandidateProfile(CandidateUserController.java:50)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        UserService userService = mock(UserService.class);
        when(userService.findByEmail(Mockito.<String>any())).thenReturn(Optional.empty());
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        CandidateRepository candidateDAO = new CandidateRepository(candidateJpaRepository,
                new CandidateEntityMapperImpl());

        CandidateCriteriaRepository candidateCriteriaRepository = mock(CandidateCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        SkillService skillService2 = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        CandidateSkillJpaRepository candidateSkillJpaRepository = mock(CandidateSkillJpaRepository.class);
        CandidateSkillEntityMapperImpl candidateSkillEntityMapper = new CandidateSkillEntityMapperImpl();
        CandidateSkillService candidateSkillService = new CandidateSkillService(new CandidateSkillRepository(
                candidateSkillJpaRepository, candidateSkillEntityMapper, new CandidateEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService2 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService3 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService3,
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

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService2,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl())));

        FileUploadService fileUploadService2 = new FileUploadService(new StandardReactiveWebEnvironment());
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository3 = mock(CandidateJpaRepository.class);
        CandidateService candidateService = new CandidateService(candidateDAO, candidateCriteriaRepository, cityService,
                skillService2, candidateSkillService, employerService, fileUploadService2, new DataService(employerDAO3,
                offerDAO3, new CandidateRepository(candidateJpaRepository3, new CandidateEntityMapperImpl())));

        CandidateUserController candidateUserController = new CandidateUserController(userService, skillService,
                skillMapper, candidateService, new CandidateDetailsMapperImpl(), mock(CandidateUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        assertThrows(NotFoundException.class, () -> candidateUserController.getCandidateProfile(model,
                new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userService).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CandidateUserController#getCandidateEditProfile(Model, Authentication)}
     */
    @Test
    void testGetCandidateEditProfile() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.CandidateUserController.lambda$getCandidateEditProfile$1(CandidateUserController.java:67)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.CandidateUserController.getCandidateEditProfile(CandidateUserController.java:67)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        CandidateRepository candidateDAO = new CandidateRepository(candidateJpaRepository,
                new CandidateEntityMapperImpl());

        CandidateCriteriaRepository candidateCriteriaRepository = mock(CandidateCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        SkillService skillService2 = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        CandidateSkillJpaRepository candidateSkillJpaRepository = mock(CandidateSkillJpaRepository.class);
        CandidateSkillEntityMapperImpl candidateSkillEntityMapper = new CandidateSkillEntityMapperImpl();
        CandidateSkillService candidateSkillService = new CandidateSkillService(new CandidateSkillRepository(
                candidateSkillJpaRepository, candidateSkillEntityMapper, new CandidateEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityService cityService2 = new CityService(null);
        OfferService offerService = new OfferService(null, mock(OfferCriteriaRepository.class), null, null, null, null);

        FileUploadService fileUploadService = new FileUploadService(new StandardReactiveWebEnvironment());
        OfferSkillService offerSkillService = new OfferSkillService(null);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService2,
                offerService, fileUploadService, offerSkillService, new DataService(null, null, null));

        FileUploadService fileUploadService2 = new FileUploadService(new StandardReactiveWebEnvironment());
        EmployerJpaRepository employerJpaRepository2 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO2 = new EmployerRepository(employerJpaRepository2, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        CandidateService candidateService = new CandidateService(candidateDAO, candidateCriteriaRepository, cityService,
                skillService2, candidateSkillService, employerService, fileUploadService2, new DataService(employerDAO2,
                offerDAO, new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl())));

        CandidateUserController candidateUserController = new CandidateUserController(userService, skillService,
                skillMapper, candidateService, new CandidateDetailsMapperImpl(), mock(CandidateUpdateRequestMapper.class));
        assertThrows(NotFoundException.class,
                () -> candidateUserController.getCandidateEditProfile(new ConcurrentModel(), null));
    }

    /**
     * Method under test: {@link CandidateUserController#getCandidateEditProfile(Model, Authentication)}
     */
    @Test
    void testGetCandidateEditProfile2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.CandidateUserController.lambda$getCandidateEditProfile$1(CandidateUserController.java:67)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.CandidateUserController.getCandidateEditProfile(CandidateUserController.java:67)
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

        SkillJpaRepository skillJpaRepository = mock(SkillJpaRepository.class);
        when(skillJpaRepository.findAll()).thenReturn(new ArrayList<>());
        SkillService skillService = new SkillService(
                new SkillRepository(skillJpaRepository, mock(SkillEntityMapper.class)));

        CityEntity cityEntity = new CityEntity();
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());

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
        CandidateEntity candidateEntity = mock(CandidateEntity.class);
        when(candidateEntity.getOpenToRemoteJob()).thenReturn(true);
        when(candidateEntity.getYearsOfExperience()).thenReturn(1);
        when(candidateEntity.getCandidateId()).thenReturn(1L);
        when(candidateEntity.getCandidateUuid()).thenReturn("2020-03-01");
        when(candidateEntity.getCvFilename()).thenReturn("foo.txt");
        when(candidateEntity.getEducation()).thenReturn("Education");
        when(candidateEntity.getEmailContact()).thenReturn("jane.doe@example.org");
        when(candidateEntity.getExperienceLevel()).thenReturn("Experience Level");
        when(candidateEntity.getFirstName()).thenReturn("Jane");
        when(candidateEntity.getForeignLanguage()).thenReturn("en");
        when(candidateEntity.getGithubLink()).thenReturn("Github Link");
        when(candidateEntity.getHobby()).thenReturn("Hobby");
        when(candidateEntity.getLastName()).thenReturn("Doe");
        when(candidateEntity.getLinkedinLink()).thenReturn("Linkedin Link");
        when(candidateEntity.getOtherSkills()).thenReturn("Other Skills");
        when(candidateEntity.getPhoneNumber()).thenReturn("6625550144");
        when(candidateEntity.getPhotoFilename()).thenReturn("foo.txt");
        when(candidateEntity.getStatus()).thenReturn("Status");
        when(candidateEntity.getSalaryMin()).thenReturn(BigDecimal.valueOf(1L));
        when(candidateEntity.getCreatedAt())
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        when(candidateEntity.getCandidateSkills()).thenReturn(new HashSet<>());
        when(candidateEntity.getResidenceCityId()).thenReturn(cityEntity);
        when(candidateEntity.getEmployerId()).thenReturn(employerEntity);
        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        when(candidateJpaRepository.findByCandidateUuid(Mockito.<String>any())).thenReturn(Optional.of(candidateEntity));
        CandidateRepository candidateDAO = new CandidateRepository(candidateJpaRepository,
                new CandidateEntityMapperImpl());

        CandidateCriteriaRepository candidateCriteriaRepository = mock(CandidateCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        SkillService skillService2 = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        CandidateSkillJpaRepository candidateSkillJpaRepository = mock(CandidateSkillJpaRepository.class);
        CandidateSkillEntityMapperImpl candidateSkillEntityMapper = new CandidateSkillEntityMapperImpl();
        CandidateSkillService candidateSkillService = new CandidateSkillService(new CandidateSkillRepository(
                candidateSkillJpaRepository, candidateSkillEntityMapper, new CandidateEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService2 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService3 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService3,
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

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService2,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl())));

        FileUploadService fileUploadService2 = new FileUploadService(new StandardReactiveWebEnvironment());
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository3 = mock(CandidateJpaRepository.class);
        CandidateService candidateService = new CandidateService(candidateDAO, candidateCriteriaRepository, cityService,
                skillService2, candidateSkillService, employerService, fileUploadService2, new DataService(employerDAO3,
                offerDAO3, new CandidateRepository(candidateJpaRepository3, new CandidateEntityMapperImpl())));

        SkillMapper skillMapper = mock(SkillMapper.class);
        CandidateUserController candidateUserController = new CandidateUserController(userService, skillService,
                skillMapper, candidateService, new CandidateDetailsMapperImpl(), mock(CandidateUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        assertEquals("candidate/edit_profile", candidateUserController.getCandidateEditProfile(model,
                new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userJpaRepository).findByEmail(Mockito.<String>any());
        verify(userEntity).getIsEnabled();
        verify(userEntity).getId();
        verify(userEntity).getEmail();
        verify(userEntity).getPassword();
        verify(userEntity).getUserName();
        verify(userEntity).getUserUuid();
        verify(userEntity).getRoleId();
        verify(skillJpaRepository).findAll();
        verify(candidateJpaRepository).findByCandidateUuid(Mockito.<String>any());
        verify(candidateEntity).getOpenToRemoteJob();
        verify(candidateEntity).getYearsOfExperience();
        verify(candidateEntity).getCandidateId();
        verify(candidateEntity).getCandidateUuid();
        verify(candidateEntity).getCvFilename();
        verify(candidateEntity).getEducation();
        verify(candidateEntity).getEmailContact();
        verify(candidateEntity).getExperienceLevel();
        verify(candidateEntity).getFirstName();
        verify(candidateEntity).getForeignLanguage();
        verify(candidateEntity).getGithubLink();
        verify(candidateEntity).getHobby();
        verify(candidateEntity).getLastName();
        verify(candidateEntity).getLinkedinLink();
        verify(candidateEntity).getOtherSkills();
        verify(candidateEntity).getPhoneNumber();
        verify(candidateEntity).getPhotoFilename();
        verify(candidateEntity).getStatus();
        verify(candidateEntity).getSalaryMin();
        verify(candidateEntity).getCreatedAt();
        verify(candidateEntity).getCandidateSkills();
        verify(candidateEntity).getResidenceCityId();
        verify(candidateEntity).getEmployerId();
    }

    /**
     * Method under test: {@link CandidateUserController#getCandidateEditProfile(Model, Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCandidateEditProfile3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.CandidateUserController.lambda$getCandidateEditProfile$1(CandidateUserController.java:67)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.CandidateUserController.getCandidateEditProfile(CandidateUserController.java:67)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   pl.devfinder.domain.exception.NotFoundException: An error occurred
        //       at pl.devfinder.infrastructure.database.repository.mapper.CandidateEntityMapperImpl.mapFromEntity(CandidateEntityMapperImpl.java:34)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //       at pl.devfinder.infrastructure.database.repository.CandidateRepository.findByCandidateUuid(CandidateRepository.java:42)
        //       at pl.devfinder.business.CandidateService.findByCandidateUuid(CandidateService.java:46)
        //       at pl.devfinder.api.controller.CandidateUserController.getCandidateEditProfile(CandidateUserController.java:68)
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

        SkillJpaRepository skillJpaRepository = mock(SkillJpaRepository.class);
        when(skillJpaRepository.findAll()).thenReturn(new ArrayList<>());
        SkillService skillService = new SkillService(
                new SkillRepository(skillJpaRepository, mock(SkillEntityMapper.class)));
        CandidateEntity candidateEntity = mock(CandidateEntity.class);
        when(candidateEntity.getOpenToRemoteJob()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getYearsOfExperience()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getCandidateId()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getCandidateUuid()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getCvFilename()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getEducation()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getEmailContact()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getExperienceLevel()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getFirstName()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getForeignLanguage()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getGithubLink()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getHobby()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getLastName()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getLinkedinLink()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getOtherSkills()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getPhoneNumber()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getPhotoFilename()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getStatus()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getSalaryMin()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getCreatedAt()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getCandidateSkills()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getResidenceCityId()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getEmployerId()).thenThrow(new NotFoundException("An error occurred"));
        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        when(candidateJpaRepository.findByCandidateUuid(Mockito.<String>any())).thenReturn(Optional.of(candidateEntity));
        CandidateRepository candidateDAO = new CandidateRepository(candidateJpaRepository,
                new CandidateEntityMapperImpl());

        CandidateCriteriaRepository candidateCriteriaRepository = mock(CandidateCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        SkillService skillService2 = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        CandidateSkillJpaRepository candidateSkillJpaRepository = mock(CandidateSkillJpaRepository.class);
        CandidateSkillEntityMapperImpl candidateSkillEntityMapper = new CandidateSkillEntityMapperImpl();
        CandidateSkillService candidateSkillService = new CandidateSkillService(new CandidateSkillRepository(
                candidateSkillJpaRepository, candidateSkillEntityMapper, new CandidateEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService2 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService3 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService3,
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

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService2,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl())));

        FileUploadService fileUploadService2 = new FileUploadService(new StandardReactiveWebEnvironment());
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository3 = mock(CandidateJpaRepository.class);
        CandidateService candidateService = new CandidateService(candidateDAO, candidateCriteriaRepository, cityService,
                skillService2, candidateSkillService, employerService, fileUploadService2, new DataService(employerDAO3,
                offerDAO3, new CandidateRepository(candidateJpaRepository3, new CandidateEntityMapperImpl())));

        SkillMapper skillMapper = mock(SkillMapper.class);
        CandidateUserController candidateUserController = new CandidateUserController(userService, skillService,
                skillMapper, candidateService, new CandidateDetailsMapperImpl(), mock(CandidateUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        candidateUserController.getCandidateEditProfile(model,
                new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link CandidateUserController#getCandidateEditProfile(Model, Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCandidateEditProfile4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.CandidateUserController.lambda$getCandidateEditProfile$1(CandidateUserController.java:67)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.CandidateUserController.getCandidateEditProfile(CandidateUserController.java:67)
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
        //       at pl.devfinder.api.controller.CandidateUserController.getCandidateEditProfile(CandidateUserController.java:66)
        //   See https://diff.blue/R013 to resolve this issue.

        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        when(userJpaRepository.findByEmail(Mockito.<String>any())).thenReturn(null);
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        SkillJpaRepository skillJpaRepository = mock(SkillJpaRepository.class);
        when(skillJpaRepository.findAll()).thenReturn(new ArrayList<>());
        SkillService skillService = new SkillService(
                new SkillRepository(skillJpaRepository, mock(SkillEntityMapper.class)));

        CityEntity cityEntity = new CityEntity();
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());

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
        CandidateEntity candidateEntity = mock(CandidateEntity.class);
        when(candidateEntity.getOpenToRemoteJob()).thenReturn(true);
        when(candidateEntity.getYearsOfExperience()).thenReturn(1);
        when(candidateEntity.getCandidateId()).thenReturn(1L);
        when(candidateEntity.getCandidateUuid()).thenReturn("2020-03-01");
        when(candidateEntity.getCvFilename()).thenReturn("foo.txt");
        when(candidateEntity.getEducation()).thenReturn("Education");
        when(candidateEntity.getEmailContact()).thenReturn("jane.doe@example.org");
        when(candidateEntity.getExperienceLevel()).thenReturn("Experience Level");
        when(candidateEntity.getFirstName()).thenReturn("Jane");
        when(candidateEntity.getForeignLanguage()).thenReturn("en");
        when(candidateEntity.getGithubLink()).thenReturn("Github Link");
        when(candidateEntity.getHobby()).thenReturn("Hobby");
        when(candidateEntity.getLastName()).thenReturn("Doe");
        when(candidateEntity.getLinkedinLink()).thenReturn("Linkedin Link");
        when(candidateEntity.getOtherSkills()).thenReturn("Other Skills");
        when(candidateEntity.getPhoneNumber()).thenReturn("6625550144");
        when(candidateEntity.getPhotoFilename()).thenReturn("foo.txt");
        when(candidateEntity.getStatus()).thenReturn("Status");
        when(candidateEntity.getSalaryMin()).thenReturn(BigDecimal.valueOf(1L));
        when(candidateEntity.getCreatedAt())
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        when(candidateEntity.getCandidateSkills()).thenReturn(new HashSet<>());
        when(candidateEntity.getResidenceCityId()).thenReturn(cityEntity);
        when(candidateEntity.getEmployerId()).thenReturn(employerEntity);
        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        when(candidateJpaRepository.findByCandidateUuid(Mockito.<String>any())).thenReturn(Optional.of(candidateEntity));
        CandidateRepository candidateDAO = new CandidateRepository(candidateJpaRepository,
                new CandidateEntityMapperImpl());

        CandidateCriteriaRepository candidateCriteriaRepository = mock(CandidateCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        SkillService skillService2 = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        CandidateSkillJpaRepository candidateSkillJpaRepository = mock(CandidateSkillJpaRepository.class);
        CandidateSkillEntityMapperImpl candidateSkillEntityMapper = new CandidateSkillEntityMapperImpl();
        CandidateSkillService candidateSkillService = new CandidateSkillService(new CandidateSkillRepository(
                candidateSkillJpaRepository, candidateSkillEntityMapper, new CandidateEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService2 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService3 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService3,
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

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService2,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl())));

        FileUploadService fileUploadService2 = new FileUploadService(new StandardReactiveWebEnvironment());
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository3 = mock(CandidateJpaRepository.class);
        CandidateService candidateService = new CandidateService(candidateDAO, candidateCriteriaRepository, cityService,
                skillService2, candidateSkillService, employerService, fileUploadService2, new DataService(employerDAO3,
                offerDAO3, new CandidateRepository(candidateJpaRepository3, new CandidateEntityMapperImpl())));

        SkillMapper skillMapper = mock(SkillMapper.class);
        CandidateUserController candidateUserController = new CandidateUserController(userService, skillService,
                skillMapper, candidateService, new CandidateDetailsMapperImpl(), mock(CandidateUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        candidateUserController.getCandidateEditProfile(model,
                new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link CandidateUserController#getCandidateEditProfile(Model, Authentication)}
     */
    @Test
    void testGetCandidateEditProfile5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.CandidateUserController.lambda$getCandidateEditProfile$1(CandidateUserController.java:67)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.CandidateUserController.getCandidateEditProfile(CandidateUserController.java:67)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        when(userJpaRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.empty());
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        CandidateRepository candidateDAO = new CandidateRepository(candidateJpaRepository,
                new CandidateEntityMapperImpl());

        CandidateCriteriaRepository candidateCriteriaRepository = mock(CandidateCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        SkillService skillService2 = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        CandidateSkillJpaRepository candidateSkillJpaRepository = mock(CandidateSkillJpaRepository.class);
        CandidateSkillEntityMapperImpl candidateSkillEntityMapper = new CandidateSkillEntityMapperImpl();
        CandidateSkillService candidateSkillService = new CandidateSkillService(new CandidateSkillRepository(
                candidateSkillJpaRepository, candidateSkillEntityMapper, new CandidateEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService2 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService3 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService3,
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

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService2,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl())));

        FileUploadService fileUploadService2 = new FileUploadService(new StandardReactiveWebEnvironment());
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository3 = mock(CandidateJpaRepository.class);
        CandidateService candidateService = new CandidateService(candidateDAO, candidateCriteriaRepository, cityService,
                skillService2, candidateSkillService, employerService, fileUploadService2, new DataService(employerDAO3,
                offerDAO3, new CandidateRepository(candidateJpaRepository3, new CandidateEntityMapperImpl())));

        CandidateUserController candidateUserController = new CandidateUserController(userService, skillService,
                skillMapper, candidateService, new CandidateDetailsMapperImpl(), mock(CandidateUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        assertThrows(NotFoundException.class, () -> candidateUserController.getCandidateEditProfile(model,
                new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userJpaRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CandidateUserController#getCandidateEditProfile(Model, Authentication)}
     */
    @Test
    void testGetCandidateEditProfile6() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.CandidateUserController.lambda$getCandidateEditProfile$1(CandidateUserController.java:67)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.CandidateUserController.getCandidateEditProfile(CandidateUserController.java:67)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        UserService userService = mock(UserService.class);
        when(userService.findByEmail(Mockito.<String>any())).thenReturn(Optional.empty());
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        CandidateRepository candidateDAO = new CandidateRepository(candidateJpaRepository,
                new CandidateEntityMapperImpl());

        CandidateCriteriaRepository candidateCriteriaRepository = mock(CandidateCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        SkillService skillService2 = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        CandidateSkillJpaRepository candidateSkillJpaRepository = mock(CandidateSkillJpaRepository.class);
        CandidateSkillEntityMapperImpl candidateSkillEntityMapper = new CandidateSkillEntityMapperImpl();
        CandidateSkillService candidateSkillService = new CandidateSkillService(new CandidateSkillRepository(
                candidateSkillJpaRepository, candidateSkillEntityMapper, new CandidateEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService2 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService3 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService3,
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

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService2,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl())));

        FileUploadService fileUploadService2 = new FileUploadService(new StandardReactiveWebEnvironment());
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository3 = mock(CandidateJpaRepository.class);
        CandidateService candidateService = new CandidateService(candidateDAO, candidateCriteriaRepository, cityService,
                skillService2, candidateSkillService, employerService, fileUploadService2, new DataService(employerDAO3,
                offerDAO3, new CandidateRepository(candidateJpaRepository3, new CandidateEntityMapperImpl())));

        CandidateUserController candidateUserController = new CandidateUserController(userService, skillService,
                skillMapper, candidateService, new CandidateDetailsMapperImpl(), mock(CandidateUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        assertThrows(NotFoundException.class, () -> candidateUserController.getCandidateEditProfile(model,
                new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userService).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CandidateUserController#deleteCandidateProfile(Model, Authentication)}
     */
    @Test
    void testDeleteCandidateProfile() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(candidateUserController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link CandidateUserController#deleteCvFile(Model, Authentication)}
     */
    @Test
    void testDeleteCvFile() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:596)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.CandidateUserController.lambda$deleteCvFile$3(CandidateUserController.java:110)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.CandidateUserController.deleteCvFile(CandidateUserController.java:110)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:596)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        CandidateRepository candidateDAO = new CandidateRepository(candidateJpaRepository,
                new CandidateEntityMapperImpl());

        CandidateCriteriaRepository candidateCriteriaRepository = mock(CandidateCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        SkillService skillService2 = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        CandidateSkillJpaRepository candidateSkillJpaRepository = mock(CandidateSkillJpaRepository.class);
        CandidateSkillEntityMapperImpl candidateSkillEntityMapper = new CandidateSkillEntityMapperImpl();
        CandidateSkillService candidateSkillService = new CandidateSkillService(new CandidateSkillRepository(
                candidateSkillJpaRepository, candidateSkillEntityMapper, new CandidateEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityService cityService2 = new CityService(null);
        OfferService offerService = new OfferService(null, mock(OfferCriteriaRepository.class), null, null, null, null);

        FileUploadService fileUploadService = new FileUploadService(new StandardReactiveWebEnvironment());
        OfferSkillService offerSkillService = new OfferSkillService(null);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService2,
                offerService, fileUploadService, offerSkillService, new DataService(null, null, null));

        FileUploadService fileUploadService2 = new FileUploadService(new StandardReactiveWebEnvironment());
        EmployerJpaRepository employerJpaRepository2 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO2 = new EmployerRepository(employerJpaRepository2, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        CandidateService candidateService = new CandidateService(candidateDAO, candidateCriteriaRepository, cityService,
                skillService2, candidateSkillService, employerService, fileUploadService2, new DataService(employerDAO2,
                offerDAO, new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl())));

        CandidateUserController candidateUserController = new CandidateUserController(userService, skillService,
                skillMapper, candidateService, new CandidateDetailsMapperImpl(), mock(CandidateUpdateRequestMapper.class));
        assertThrows(NotFoundException.class, () -> candidateUserController.deleteCvFile(new ConcurrentModel(), null));
    }

    /**
     * Method under test: {@link CandidateUserController#deleteCvFile(Model, Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteCvFile2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:596)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.CandidateUserController.lambda$deleteCvFile$3(CandidateUserController.java:110)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.CandidateUserController.deleteCvFile(CandidateUserController.java:110)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:596)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   pl.devfinder.domain.exception.NotFoundException: An error occurred
        //       at pl.devfinder.infrastructure.database.repository.mapper.CandidateEntityMapperImpl.mapFromEntity(CandidateEntityMapperImpl.java:34)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //       at pl.devfinder.infrastructure.database.repository.CandidateRepository.findByCandidateUuid(CandidateRepository.java:42)
        //       at pl.devfinder.business.CandidateService.findByCandidateUuid(CandidateService.java:46)
        //       at pl.devfinder.api.controller.CandidateUserController.deleteCvFile(CandidateUserController.java:111)
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

        CandidateEntity candidateEntity = mock(CandidateEntity.class);
        when(candidateEntity.getOpenToRemoteJob()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getYearsOfExperience()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getCandidateId()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getCandidateUuid()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getCvFilename()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getEducation()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getEmailContact()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getExperienceLevel()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getFirstName()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getForeignLanguage()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getGithubLink()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getHobby()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getLastName()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getLinkedinLink()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getOtherSkills()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getPhoneNumber()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getPhotoFilename()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getStatus()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getSalaryMin()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getCreatedAt()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getCandidateSkills()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getResidenceCityId()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getEmployerId()).thenThrow(new NotFoundException("An error occurred"));
        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        when(candidateJpaRepository.findByCandidateUuid(Mockito.<String>any())).thenReturn(Optional.of(candidateEntity));
        CandidateRepository candidateDAO = new CandidateRepository(candidateJpaRepository,
                new CandidateEntityMapperImpl());

        CandidateCriteriaRepository candidateCriteriaRepository = mock(CandidateCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        CandidateSkillJpaRepository candidateSkillJpaRepository = mock(CandidateSkillJpaRepository.class);
        CandidateSkillEntityMapperImpl candidateSkillEntityMapper = new CandidateSkillEntityMapperImpl();
        CandidateSkillService candidateSkillService = new CandidateSkillService(new CandidateSkillRepository(
                candidateSkillJpaRepository, candidateSkillEntityMapper, new CandidateEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService2 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService3 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService3,
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

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService2,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl())));

        FileUploadService fileUploadService2 = new FileUploadService(new StandardReactiveWebEnvironment());
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository3 = mock(CandidateJpaRepository.class);
        CandidateService candidateService = new CandidateService(candidateDAO, candidateCriteriaRepository, cityService,
                skillService, candidateSkillService, employerService, fileUploadService2, new DataService(employerDAO3,
                offerDAO3, new CandidateRepository(candidateJpaRepository3, new CandidateEntityMapperImpl())));

        SkillService skillService2 = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        CandidateUserController candidateUserController = new CandidateUserController(userService, skillService2,
                skillMapper, candidateService, new CandidateDetailsMapperImpl(), mock(CandidateUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        candidateUserController.deleteCvFile(model, new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link CandidateUserController#deleteCvFile(Model, Authentication)}
     */
    @Test
    void testDeleteCvFile3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:596)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.CandidateUserController.lambda$deleteCvFile$3(CandidateUserController.java:110)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.CandidateUserController.deleteCvFile(CandidateUserController.java:110)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:596)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        when(userJpaRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.empty());
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        CandidateRepository candidateDAO = new CandidateRepository(candidateJpaRepository,
                new CandidateEntityMapperImpl());

        CandidateCriteriaRepository candidateCriteriaRepository = mock(CandidateCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        SkillService skillService2 = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        CandidateSkillJpaRepository candidateSkillJpaRepository = mock(CandidateSkillJpaRepository.class);
        CandidateSkillEntityMapperImpl candidateSkillEntityMapper = new CandidateSkillEntityMapperImpl();
        CandidateSkillService candidateSkillService = new CandidateSkillService(new CandidateSkillRepository(
                candidateSkillJpaRepository, candidateSkillEntityMapper, new CandidateEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService2 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService3 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService3,
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

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService2,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl())));

        FileUploadService fileUploadService2 = new FileUploadService(new StandardReactiveWebEnvironment());
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository3 = mock(CandidateJpaRepository.class);
        CandidateService candidateService = new CandidateService(candidateDAO, candidateCriteriaRepository, cityService,
                skillService2, candidateSkillService, employerService, fileUploadService2, new DataService(employerDAO3,
                offerDAO3, new CandidateRepository(candidateJpaRepository3, new CandidateEntityMapperImpl())));

        CandidateUserController candidateUserController = new CandidateUserController(userService, skillService,
                skillMapper, candidateService, new CandidateDetailsMapperImpl(), mock(CandidateUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        assertThrows(NotFoundException.class, () -> candidateUserController.deleteCvFile(model,
                new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userJpaRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CandidateUserController#deleteCvFile(Model, Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteCvFile4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:596)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.CandidateUserController.lambda$deleteCvFile$3(CandidateUserController.java:110)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.CandidateUserController.deleteCvFile(CandidateUserController.java:110)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:596)
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
        //       at pl.devfinder.api.controller.CandidateUserController.deleteCvFile(CandidateUserController.java:109)
        //   See https://diff.blue/R013 to resolve this issue.

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
        UserRepository userDAO = new UserRepository(userJpaRepository, null);

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        CityEntity cityEntity = new CityEntity();
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());

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
        CandidateEntity candidateEntity = mock(CandidateEntity.class);
        when(candidateEntity.getOpenToRemoteJob()).thenReturn(true);
        when(candidateEntity.getYearsOfExperience()).thenReturn(1);
        when(candidateEntity.getCandidateId()).thenReturn(1L);
        when(candidateEntity.getCandidateUuid()).thenReturn("2020-03-01");
        when(candidateEntity.getCvFilename()).thenReturn("foo.txt");
        when(candidateEntity.getEducation()).thenReturn("Education");
        when(candidateEntity.getEmailContact()).thenReturn("jane.doe@example.org");
        when(candidateEntity.getExperienceLevel()).thenReturn("Experience Level");
        when(candidateEntity.getFirstName()).thenReturn("Jane");
        when(candidateEntity.getForeignLanguage()).thenReturn("en");
        when(candidateEntity.getGithubLink()).thenReturn("Github Link");
        when(candidateEntity.getHobby()).thenReturn("Hobby");
        when(candidateEntity.getLastName()).thenReturn("Doe");
        when(candidateEntity.getLinkedinLink()).thenReturn("Linkedin Link");
        when(candidateEntity.getOtherSkills()).thenReturn("Other Skills");
        when(candidateEntity.getPhoneNumber()).thenReturn("6625550144");
        when(candidateEntity.getPhotoFilename()).thenReturn("foo.txt");
        when(candidateEntity.getStatus()).thenReturn("Status");
        when(candidateEntity.getSalaryMin()).thenReturn(BigDecimal.valueOf(1L));
        when(candidateEntity.getCreatedAt())
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        when(candidateEntity.getCandidateSkills()).thenReturn(new HashSet<>());
        when(candidateEntity.getResidenceCityId()).thenReturn(cityEntity);
        when(candidateEntity.getEmployerId()).thenReturn(employerEntity);
        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        when(candidateJpaRepository.findByCandidateUuid(Mockito.<String>any())).thenReturn(Optional.of(candidateEntity));
        CandidateRepository candidateDAO = new CandidateRepository(candidateJpaRepository,
                new CandidateEntityMapperImpl());

        CandidateCriteriaRepository candidateCriteriaRepository = mock(CandidateCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        CandidateSkillJpaRepository candidateSkillJpaRepository = mock(CandidateSkillJpaRepository.class);
        CandidateSkillEntityMapperImpl candidateSkillEntityMapper = new CandidateSkillEntityMapperImpl();
        CandidateSkillService candidateSkillService = new CandidateSkillService(new CandidateSkillRepository(
                candidateSkillJpaRepository, candidateSkillEntityMapper, new CandidateEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService2 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService3 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService3,
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

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService2,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl())));

        FileUploadService fileUploadService2 = new FileUploadService(new StandardReactiveWebEnvironment());
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository3 = mock(CandidateJpaRepository.class);
        CandidateService candidateService = new CandidateService(candidateDAO, candidateCriteriaRepository, cityService,
                skillService, candidateSkillService, employerService, fileUploadService2, new DataService(employerDAO3,
                offerDAO3, new CandidateRepository(candidateJpaRepository3, new CandidateEntityMapperImpl())));

        SkillService skillService2 = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        CandidateUserController candidateUserController = new CandidateUserController(userService, skillService2,
                skillMapper, candidateService, new CandidateDetailsMapperImpl(), mock(CandidateUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        candidateUserController.deleteCvFile(model, new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link CandidateUserController#deleteCvFile(Model, Authentication)}
     */
    @Test
    void testDeleteCvFile5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:596)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.CandidateUserController.lambda$deleteCvFile$3(CandidateUserController.java:110)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.CandidateUserController.deleteCvFile(CandidateUserController.java:110)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:596)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        UserService userService = mock(UserService.class);
        when(userService.findByEmail(Mockito.<String>any())).thenReturn(Optional.empty());
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        CandidateRepository candidateDAO = new CandidateRepository(candidateJpaRepository,
                new CandidateEntityMapperImpl());

        CandidateCriteriaRepository candidateCriteriaRepository = mock(CandidateCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        SkillService skillService2 = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        CandidateSkillJpaRepository candidateSkillJpaRepository = mock(CandidateSkillJpaRepository.class);
        CandidateSkillEntityMapperImpl candidateSkillEntityMapper = new CandidateSkillEntityMapperImpl();
        CandidateSkillService candidateSkillService = new CandidateSkillService(new CandidateSkillRepository(
                candidateSkillJpaRepository, candidateSkillEntityMapper, new CandidateEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService2 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService3 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService3,
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

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService2,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl())));

        FileUploadService fileUploadService2 = new FileUploadService(new StandardReactiveWebEnvironment());
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository3 = mock(CandidateJpaRepository.class);
        CandidateService candidateService = new CandidateService(candidateDAO, candidateCriteriaRepository, cityService,
                skillService2, candidateSkillService, employerService, fileUploadService2, new DataService(employerDAO3,
                offerDAO3, new CandidateRepository(candidateJpaRepository3, new CandidateEntityMapperImpl())));

        CandidateUserController candidateUserController = new CandidateUserController(userService, skillService,
                skillMapper, candidateService, new CandidateDetailsMapperImpl(), mock(CandidateUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        assertThrows(NotFoundException.class, () -> candidateUserController.deleteCvFile(model,
                new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userService).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CandidateUserController#deletePhotoFile(Model, Authentication)}
     */
    @Test
    void testDeletePhotoFile() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:596)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.CandidateUserController.lambda$deletePhotoFile$5(CandidateUserController.java:121)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.CandidateUserController.deletePhotoFile(CandidateUserController.java:121)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:596)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        CandidateRepository candidateDAO = new CandidateRepository(candidateJpaRepository,
                new CandidateEntityMapperImpl());

        CandidateCriteriaRepository candidateCriteriaRepository = mock(CandidateCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        SkillService skillService2 = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        CandidateSkillJpaRepository candidateSkillJpaRepository = mock(CandidateSkillJpaRepository.class);
        CandidateSkillEntityMapperImpl candidateSkillEntityMapper = new CandidateSkillEntityMapperImpl();
        CandidateSkillService candidateSkillService = new CandidateSkillService(new CandidateSkillRepository(
                candidateSkillJpaRepository, candidateSkillEntityMapper, new CandidateEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityService cityService2 = new CityService(null);
        OfferService offerService = new OfferService(null, mock(OfferCriteriaRepository.class), null, null, null, null);

        FileUploadService fileUploadService = new FileUploadService(new StandardReactiveWebEnvironment());
        OfferSkillService offerSkillService = new OfferSkillService(null);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService2,
                offerService, fileUploadService, offerSkillService, new DataService(null, null, null));

        FileUploadService fileUploadService2 = new FileUploadService(new StandardReactiveWebEnvironment());
        EmployerJpaRepository employerJpaRepository2 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO2 = new EmployerRepository(employerJpaRepository2, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        CandidateService candidateService = new CandidateService(candidateDAO, candidateCriteriaRepository, cityService,
                skillService2, candidateSkillService, employerService, fileUploadService2, new DataService(employerDAO2,
                offerDAO, new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl())));

        CandidateUserController candidateUserController = new CandidateUserController(userService, skillService,
                skillMapper, candidateService, new CandidateDetailsMapperImpl(), mock(CandidateUpdateRequestMapper.class));
        assertThrows(NotFoundException.class, () -> candidateUserController.deletePhotoFile(new ConcurrentModel(), null));
    }

    /**
     * Method under test: {@link CandidateUserController#deletePhotoFile(Model, Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeletePhotoFile2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:596)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.CandidateUserController.lambda$deletePhotoFile$5(CandidateUserController.java:121)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.CandidateUserController.deletePhotoFile(CandidateUserController.java:121)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:596)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   pl.devfinder.domain.exception.NotFoundException: An error occurred
        //       at pl.devfinder.infrastructure.database.repository.mapper.CandidateEntityMapperImpl.mapFromEntity(CandidateEntityMapperImpl.java:34)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //       at pl.devfinder.infrastructure.database.repository.CandidateRepository.findByCandidateUuid(CandidateRepository.java:42)
        //       at pl.devfinder.business.CandidateService.findByCandidateUuid(CandidateService.java:46)
        //       at pl.devfinder.api.controller.CandidateUserController.deletePhotoFile(CandidateUserController.java:122)
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

        CandidateEntity candidateEntity = mock(CandidateEntity.class);
        when(candidateEntity.getOpenToRemoteJob()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getYearsOfExperience()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getCandidateId()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getCandidateUuid()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getCvFilename()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getEducation()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getEmailContact()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getExperienceLevel()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getFirstName()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getForeignLanguage()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getGithubLink()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getHobby()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getLastName()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getLinkedinLink()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getOtherSkills()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getPhoneNumber()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getPhotoFilename()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getStatus()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getSalaryMin()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getCreatedAt()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getCandidateSkills()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getResidenceCityId()).thenThrow(new NotFoundException("An error occurred"));
        when(candidateEntity.getEmployerId()).thenThrow(new NotFoundException("An error occurred"));
        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        when(candidateJpaRepository.findByCandidateUuid(Mockito.<String>any())).thenReturn(Optional.of(candidateEntity));
        CandidateRepository candidateDAO = new CandidateRepository(candidateJpaRepository,
                new CandidateEntityMapperImpl());

        CandidateCriteriaRepository candidateCriteriaRepository = mock(CandidateCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        CandidateSkillJpaRepository candidateSkillJpaRepository = mock(CandidateSkillJpaRepository.class);
        CandidateSkillEntityMapperImpl candidateSkillEntityMapper = new CandidateSkillEntityMapperImpl();
        CandidateSkillService candidateSkillService = new CandidateSkillService(new CandidateSkillRepository(
                candidateSkillJpaRepository, candidateSkillEntityMapper, new CandidateEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService2 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService3 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService3,
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

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService2,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl())));

        FileUploadService fileUploadService2 = new FileUploadService(new StandardReactiveWebEnvironment());
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository3 = mock(CandidateJpaRepository.class);
        CandidateService candidateService = new CandidateService(candidateDAO, candidateCriteriaRepository, cityService,
                skillService, candidateSkillService, employerService, fileUploadService2, new DataService(employerDAO3,
                offerDAO3, new CandidateRepository(candidateJpaRepository3, new CandidateEntityMapperImpl())));

        SkillService skillService2 = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        CandidateUserController candidateUserController = new CandidateUserController(userService, skillService2,
                skillMapper, candidateService, new CandidateDetailsMapperImpl(), mock(CandidateUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        candidateUserController.deletePhotoFile(model, new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link CandidateUserController#deletePhotoFile(Model, Authentication)}
     */
    @Test
    void testDeletePhotoFile3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:596)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.CandidateUserController.lambda$deletePhotoFile$5(CandidateUserController.java:121)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.CandidateUserController.deletePhotoFile(CandidateUserController.java:121)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:596)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        when(userJpaRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.empty());
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        CandidateRepository candidateDAO = new CandidateRepository(candidateJpaRepository,
                new CandidateEntityMapperImpl());

        CandidateCriteriaRepository candidateCriteriaRepository = mock(CandidateCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        SkillService skillService2 = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        CandidateSkillJpaRepository candidateSkillJpaRepository = mock(CandidateSkillJpaRepository.class);
        CandidateSkillEntityMapperImpl candidateSkillEntityMapper = new CandidateSkillEntityMapperImpl();
        CandidateSkillService candidateSkillService = new CandidateSkillService(new CandidateSkillRepository(
                candidateSkillJpaRepository, candidateSkillEntityMapper, new CandidateEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService2 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService3 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService3,
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

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService2,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl())));

        FileUploadService fileUploadService2 = new FileUploadService(new StandardReactiveWebEnvironment());
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository3 = mock(CandidateJpaRepository.class);
        CandidateService candidateService = new CandidateService(candidateDAO, candidateCriteriaRepository, cityService,
                skillService2, candidateSkillService, employerService, fileUploadService2, new DataService(employerDAO3,
                offerDAO3, new CandidateRepository(candidateJpaRepository3, new CandidateEntityMapperImpl())));

        CandidateUserController candidateUserController = new CandidateUserController(userService, skillService,
                skillMapper, candidateService, new CandidateDetailsMapperImpl(), mock(CandidateUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        assertThrows(NotFoundException.class, () -> candidateUserController.deletePhotoFile(model,
                new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userJpaRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CandidateUserController#deletePhotoFile(Model, Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeletePhotoFile4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:596)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.CandidateUserController.lambda$deletePhotoFile$5(CandidateUserController.java:121)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.CandidateUserController.deletePhotoFile(CandidateUserController.java:121)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:596)
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
        //       at pl.devfinder.api.controller.CandidateUserController.deletePhotoFile(CandidateUserController.java:120)
        //   See https://diff.blue/R013 to resolve this issue.

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
        UserRepository userDAO = new UserRepository(userJpaRepository, null);

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        CityEntity cityEntity = new CityEntity();
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());

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
        CandidateEntity candidateEntity = mock(CandidateEntity.class);
        when(candidateEntity.getOpenToRemoteJob()).thenReturn(true);
        when(candidateEntity.getYearsOfExperience()).thenReturn(1);
        when(candidateEntity.getCandidateId()).thenReturn(1L);
        when(candidateEntity.getCandidateUuid()).thenReturn("2020-03-01");
        when(candidateEntity.getCvFilename()).thenReturn("foo.txt");
        when(candidateEntity.getEducation()).thenReturn("Education");
        when(candidateEntity.getEmailContact()).thenReturn("jane.doe@example.org");
        when(candidateEntity.getExperienceLevel()).thenReturn("Experience Level");
        when(candidateEntity.getFirstName()).thenReturn("Jane");
        when(candidateEntity.getForeignLanguage()).thenReturn("en");
        when(candidateEntity.getGithubLink()).thenReturn("Github Link");
        when(candidateEntity.getHobby()).thenReturn("Hobby");
        when(candidateEntity.getLastName()).thenReturn("Doe");
        when(candidateEntity.getLinkedinLink()).thenReturn("Linkedin Link");
        when(candidateEntity.getOtherSkills()).thenReturn("Other Skills");
        when(candidateEntity.getPhoneNumber()).thenReturn("6625550144");
        when(candidateEntity.getPhotoFilename()).thenReturn("foo.txt");
        when(candidateEntity.getStatus()).thenReturn("Status");
        when(candidateEntity.getSalaryMin()).thenReturn(BigDecimal.valueOf(1L));
        when(candidateEntity.getCreatedAt())
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        when(candidateEntity.getCandidateSkills()).thenReturn(new HashSet<>());
        when(candidateEntity.getResidenceCityId()).thenReturn(cityEntity);
        when(candidateEntity.getEmployerId()).thenReturn(employerEntity);
        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        when(candidateJpaRepository.findByCandidateUuid(Mockito.<String>any())).thenReturn(Optional.of(candidateEntity));
        CandidateRepository candidateDAO = new CandidateRepository(candidateJpaRepository,
                new CandidateEntityMapperImpl());

        CandidateCriteriaRepository candidateCriteriaRepository = mock(CandidateCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        CandidateSkillJpaRepository candidateSkillJpaRepository = mock(CandidateSkillJpaRepository.class);
        CandidateSkillEntityMapperImpl candidateSkillEntityMapper = new CandidateSkillEntityMapperImpl();
        CandidateSkillService candidateSkillService = new CandidateSkillService(new CandidateSkillRepository(
                candidateSkillJpaRepository, candidateSkillEntityMapper, new CandidateEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService2 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService3 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService3,
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

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService2,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl())));

        FileUploadService fileUploadService2 = new FileUploadService(new StandardReactiveWebEnvironment());
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository3 = mock(CandidateJpaRepository.class);
        CandidateService candidateService = new CandidateService(candidateDAO, candidateCriteriaRepository, cityService,
                skillService, candidateSkillService, employerService, fileUploadService2, new DataService(employerDAO3,
                offerDAO3, new CandidateRepository(candidateJpaRepository3, new CandidateEntityMapperImpl())));

        SkillService skillService2 = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        CandidateUserController candidateUserController = new CandidateUserController(userService, skillService2,
                skillMapper, candidateService, new CandidateDetailsMapperImpl(), mock(CandidateUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        candidateUserController.deletePhotoFile(model, new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link CandidateUserController#deletePhotoFile(Model, Authentication)}
     */
    @Test
    void testDeletePhotoFile5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:596)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.CandidateUserController.lambda$deletePhotoFile$5(CandidateUserController.java:121)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.CandidateUserController.deletePhotoFile(CandidateUserController.java:121)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:596)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        UserService userService = mock(UserService.class);
        when(userService.findByEmail(Mockito.<String>any())).thenReturn(Optional.empty());
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        CandidateRepository candidateDAO = new CandidateRepository(candidateJpaRepository,
                new CandidateEntityMapperImpl());

        CandidateCriteriaRepository candidateCriteriaRepository = mock(CandidateCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        SkillService skillService2 = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        CandidateSkillJpaRepository candidateSkillJpaRepository = mock(CandidateSkillJpaRepository.class);
        CandidateSkillEntityMapperImpl candidateSkillEntityMapper = new CandidateSkillEntityMapperImpl();
        CandidateSkillService candidateSkillService = new CandidateSkillService(new CandidateSkillRepository(
                candidateSkillJpaRepository, candidateSkillEntityMapper, new CandidateEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService2 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService3 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService3,
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

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService2,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl())));

        FileUploadService fileUploadService2 = new FileUploadService(new StandardReactiveWebEnvironment());
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository3 = mock(CandidateJpaRepository.class);
        CandidateService candidateService = new CandidateService(candidateDAO, candidateCriteriaRepository, cityService,
                skillService2, candidateSkillService, employerService, fileUploadService2, new DataService(employerDAO3,
                offerDAO3, new CandidateRepository(candidateJpaRepository3, new CandidateEntityMapperImpl())));

        CandidateUserController candidateUserController = new CandidateUserController(userService, skillService,
                skillMapper, candidateService, new CandidateDetailsMapperImpl(), mock(CandidateUpdateRequestMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        assertThrows(NotFoundException.class, () -> candidateUserController.deletePhotoFile(model,
                new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userService).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CandidateUserController#updateCandidateProfile(CandidateUpdateRequestDTO, BindingResult, Model, Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateCandidateProfile() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.CandidateUserController.lambda$updateCandidateProfile$7(CandidateUserController.java:138)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.CandidateUserController.updateCandidateProfile(CandidateUserController.java:138)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "pl.devfinder.domain.CandidateUpdateRequest.getResidenceCityName()" because "candidateUpdateRequest" is null
        //       at pl.devfinder.business.CandidateService.updateCandidateProfile(CandidateService.java:51)
        //       at pl.devfinder.api.controller.CandidateUserController.updateCandidateProfile(CandidateUserController.java:148)
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

        CityEntity cityEntity = new CityEntity();
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());

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
        CandidateEntity candidateEntity = mock(CandidateEntity.class);
        when(candidateEntity.getOpenToRemoteJob()).thenReturn(true);
        when(candidateEntity.getYearsOfExperience()).thenReturn(1);
        when(candidateEntity.getCandidateId()).thenReturn(1L);
        when(candidateEntity.getCandidateUuid()).thenReturn("2020-03-01");
        when(candidateEntity.getCvFilename()).thenReturn("foo.txt");
        when(candidateEntity.getEducation()).thenReturn("Education");
        when(candidateEntity.getEmailContact()).thenReturn("jane.doe@example.org");
        when(candidateEntity.getExperienceLevel()).thenReturn("Experience Level");
        when(candidateEntity.getFirstName()).thenReturn("Jane");
        when(candidateEntity.getForeignLanguage()).thenReturn("en");
        when(candidateEntity.getGithubLink()).thenReturn("Github Link");
        when(candidateEntity.getHobby()).thenReturn("Hobby");
        when(candidateEntity.getLastName()).thenReturn("Doe");
        when(candidateEntity.getLinkedinLink()).thenReturn("Linkedin Link");
        when(candidateEntity.getOtherSkills()).thenReturn("Other Skills");
        when(candidateEntity.getPhoneNumber()).thenReturn("6625550144");
        when(candidateEntity.getPhotoFilename()).thenReturn("foo.txt");
        when(candidateEntity.getStatus()).thenReturn("Status");
        when(candidateEntity.getSalaryMin()).thenReturn(BigDecimal.valueOf(1L));
        when(candidateEntity.getCreatedAt())
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        when(candidateEntity.getCandidateSkills()).thenReturn(new HashSet<>());
        when(candidateEntity.getResidenceCityId()).thenReturn(cityEntity);
        when(candidateEntity.getEmployerId()).thenReturn(employerEntity);
        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        when(candidateJpaRepository.findByCandidateUuid(Mockito.<String>any())).thenReturn(Optional.of(candidateEntity));
        CandidateRepository candidateDAO = new CandidateRepository(candidateJpaRepository,
                new CandidateEntityMapperImpl());

        CandidateCriteriaRepository candidateCriteriaRepository = mock(CandidateCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        CandidateSkillJpaRepository candidateSkillJpaRepository = mock(CandidateSkillJpaRepository.class);
        CandidateSkillEntityMapperImpl candidateSkillEntityMapper = new CandidateSkillEntityMapperImpl();
        CandidateSkillService candidateSkillService = new CandidateSkillService(new CandidateSkillRepository(
                candidateSkillJpaRepository, candidateSkillEntityMapper, new CandidateEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService2 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService3 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService3,
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

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService2,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl())));

        FileUploadService fileUploadService2 = new FileUploadService(new StandardReactiveWebEnvironment());
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository3 = mock(CandidateJpaRepository.class);
        CandidateService candidateService = new CandidateService(candidateDAO, candidateCriteriaRepository, cityService,
                skillService, candidateSkillService, employerService, fileUploadService2, new DataService(employerDAO3,
                offerDAO3, new CandidateRepository(candidateJpaRepository3, new CandidateEntityMapperImpl())));

        CandidateUpdateRequestMapper candidateUpdateRequestMapper = mock(CandidateUpdateRequestMapper.class);
        when(candidateUpdateRequestMapper.map(Mockito.<CandidateUpdateRequestDTO>any())).thenReturn(null);
        SkillService skillService2 = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        CandidateUserController candidateUserController = new CandidateUserController(userService, skillService2,
                skillMapper, candidateService, new CandidateDetailsMapperImpl(), candidateUpdateRequestMapper);
        CandidateUpdateRequestDTO candidateUpdateRequestDTO = new CandidateUpdateRequestDTO();
        BindException bindingResult = new BindException("Target", "Object Name");

        ConcurrentModel model = new ConcurrentModel();
        candidateUserController.updateCandidateProfile(candidateUpdateRequestDTO, bindingResult, model,
                new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link CandidateUserController#updateCandidateProfile(CandidateUpdateRequestDTO, BindingResult, Model, Authentication)}
     */
    @Test
    void testUpdateCandidateProfile2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.CandidateUserController.lambda$updateCandidateProfile$7(CandidateUserController.java:138)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.CandidateUserController.updateCandidateProfile(CandidateUserController.java:138)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)
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

        CityEntity cityEntity = new CityEntity();
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());

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
        CandidateEntity candidateEntity = mock(CandidateEntity.class);
        when(candidateEntity.getOpenToRemoteJob()).thenReturn(true);
        when(candidateEntity.getYearsOfExperience()).thenReturn(1);
        when(candidateEntity.getCandidateId()).thenReturn(1L);
        when(candidateEntity.getCandidateUuid()).thenReturn("2020-03-01");
        when(candidateEntity.getCvFilename()).thenReturn("foo.txt");
        when(candidateEntity.getEducation()).thenReturn("Education");
        when(candidateEntity.getEmailContact()).thenReturn("jane.doe@example.org");
        when(candidateEntity.getExperienceLevel()).thenReturn("Experience Level");
        when(candidateEntity.getFirstName()).thenReturn("Jane");
        when(candidateEntity.getForeignLanguage()).thenReturn("en");
        when(candidateEntity.getGithubLink()).thenReturn("Github Link");
        when(candidateEntity.getHobby()).thenReturn("Hobby");
        when(candidateEntity.getLastName()).thenReturn("Doe");
        when(candidateEntity.getLinkedinLink()).thenReturn("Linkedin Link");
        when(candidateEntity.getOtherSkills()).thenReturn("Other Skills");
        when(candidateEntity.getPhoneNumber()).thenReturn("6625550144");
        when(candidateEntity.getPhotoFilename()).thenReturn("foo.txt");
        when(candidateEntity.getStatus()).thenReturn("Status");
        when(candidateEntity.getSalaryMin()).thenReturn(BigDecimal.valueOf(1L));
        when(candidateEntity.getCreatedAt())
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        when(candidateEntity.getCandidateSkills()).thenReturn(new HashSet<>());
        when(candidateEntity.getResidenceCityId()).thenReturn(cityEntity);
        when(candidateEntity.getEmployerId()).thenReturn(employerEntity);
        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        when(candidateJpaRepository.findByCandidateUuid(Mockito.<String>any())).thenReturn(Optional.of(candidateEntity));
        CandidateRepository candidateDAO = new CandidateRepository(candidateJpaRepository,
                new CandidateEntityMapperImpl());

        CandidateCriteriaRepository candidateCriteriaRepository = mock(CandidateCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        CandidateSkillJpaRepository candidateSkillJpaRepository = mock(CandidateSkillJpaRepository.class);
        CandidateSkillEntityMapperImpl candidateSkillEntityMapper = new CandidateSkillEntityMapperImpl();
        CandidateSkillService candidateSkillService = new CandidateSkillService(new CandidateSkillRepository(
                candidateSkillJpaRepository, candidateSkillEntityMapper, new CandidateEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService2 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService3 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService3,
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

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService2,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl())));

        FileUploadService fileUploadService2 = new FileUploadService(new StandardReactiveWebEnvironment());
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository3 = mock(CandidateJpaRepository.class);
        CandidateService candidateService = new CandidateService(candidateDAO, candidateCriteriaRepository, cityService,
                skillService, candidateSkillService, employerService, fileUploadService2, new DataService(employerDAO3,
                offerDAO3, new CandidateRepository(candidateJpaRepository3, new CandidateEntityMapperImpl())));

        CandidateUpdateRequestMapper candidateUpdateRequestMapper = mock(CandidateUpdateRequestMapper.class);
        when(candidateUpdateRequestMapper.map(Mockito.<CandidateUpdateRequestDTO>any()))
                .thenThrow(new NotFoundException("An error occurred"));
        SkillService skillService2 = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        CandidateUserController candidateUserController = new CandidateUserController(userService, skillService2,
                skillMapper, candidateService, new CandidateDetailsMapperImpl(), candidateUpdateRequestMapper);
        CandidateUpdateRequestDTO candidateUpdateRequestDTO = new CandidateUpdateRequestDTO();
        BindException bindingResult = new BindException("Target", "Object Name");

        ConcurrentModel model = new ConcurrentModel();
        assertThrows(NotFoundException.class,
                () -> candidateUserController.updateCandidateProfile(candidateUpdateRequestDTO, bindingResult, model,
                        new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userJpaRepository).findByEmail(Mockito.<String>any());
        verify(userEntity).getIsEnabled();
        verify(userEntity).getId();
        verify(userEntity).getEmail();
        verify(userEntity).getPassword();
        verify(userEntity).getUserName();
        verify(userEntity).getUserUuid();
        verify(userEntity).getRoleId();
        verify(candidateJpaRepository).findByCandidateUuid(Mockito.<String>any());
        verify(candidateEntity).getOpenToRemoteJob();
        verify(candidateEntity).getYearsOfExperience();
        verify(candidateEntity).getCandidateId();
        verify(candidateEntity).getCandidateUuid();
        verify(candidateEntity).getCvFilename();
        verify(candidateEntity).getEducation();
        verify(candidateEntity).getEmailContact();
        verify(candidateEntity).getExperienceLevel();
        verify(candidateEntity).getFirstName();
        verify(candidateEntity).getForeignLanguage();
        verify(candidateEntity).getGithubLink();
        verify(candidateEntity).getHobby();
        verify(candidateEntity).getLastName();
        verify(candidateEntity).getLinkedinLink();
        verify(candidateEntity).getOtherSkills();
        verify(candidateEntity).getPhoneNumber();
        verify(candidateEntity).getPhotoFilename();
        verify(candidateEntity).getStatus();
        verify(candidateEntity).getSalaryMin();
        verify(candidateEntity).getCreatedAt();
        verify(candidateEntity).getCandidateSkills();
        verify(candidateEntity).getResidenceCityId();
        verify(candidateEntity).getEmployerId();
        verify(candidateUpdateRequestMapper).map(Mockito.<CandidateUpdateRequestDTO>any());
    }

    /**
     * Method under test: {@link CandidateUserController#updateCandidateProfile(CandidateUpdateRequestDTO, BindingResult, Model, Authentication)}
     */
    @Test
    void testUpdateCandidateProfile3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.CandidateUserController.lambda$updateCandidateProfile$7(CandidateUserController.java:138)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.CandidateUserController.updateCandidateProfile(CandidateUserController.java:138)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        UserJpaRepository userJpaRepository = mock(UserJpaRepository.class);
        when(userJpaRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.empty());
        UserRepository userDAO = new UserRepository(userJpaRepository, new UserEntityMapperImpl());

        EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository = mock(
                EmailVerificationTokenJpaRepository.class);
        UserService userService = new UserService(userDAO, new EmailVerificationTokenRepository(
                emailVerificationTokenJpaRepository, new EmailVerificationTokenMapperImpl()));

        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        CandidateRepository candidateDAO = new CandidateRepository(candidateJpaRepository,
                new CandidateEntityMapperImpl());

        CandidateCriteriaRepository candidateCriteriaRepository = mock(CandidateCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        SkillService skillService2 = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        CandidateSkillJpaRepository candidateSkillJpaRepository = mock(CandidateSkillJpaRepository.class);
        CandidateSkillEntityMapperImpl candidateSkillEntityMapper = new CandidateSkillEntityMapperImpl();
        CandidateSkillService candidateSkillService = new CandidateSkillService(new CandidateSkillRepository(
                candidateSkillJpaRepository, candidateSkillEntityMapper, new CandidateEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService2 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService3 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService3,
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

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService2,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl())));

        FileUploadService fileUploadService2 = new FileUploadService(new StandardReactiveWebEnvironment());
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository3 = mock(CandidateJpaRepository.class);
        CandidateService candidateService = new CandidateService(candidateDAO, candidateCriteriaRepository, cityService,
                skillService2, candidateSkillService, employerService, fileUploadService2, new DataService(employerDAO3,
                offerDAO3, new CandidateRepository(candidateJpaRepository3, new CandidateEntityMapperImpl())));

        CandidateUserController candidateUserController = new CandidateUserController(userService, skillService,
                skillMapper, candidateService, new CandidateDetailsMapperImpl(), mock(CandidateUpdateRequestMapper.class));
        CandidateUpdateRequestDTO candidateUpdateRequestDTO = new CandidateUpdateRequestDTO();
        BindException bindingResult = new BindException("Target", "Object Name");

        ConcurrentModel model = new ConcurrentModel();
        assertThrows(NotFoundException.class,
                () -> candidateUserController.updateCandidateProfile(candidateUpdateRequestDTO, bindingResult, model,
                        new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userJpaRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CandidateUserController#updateCandidateProfile(CandidateUpdateRequestDTO, BindingResult, Model, Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateCandidateProfile4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.CandidateUserController.lambda$updateCandidateProfile$7(CandidateUserController.java:138)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.CandidateUserController.updateCandidateProfile(CandidateUserController.java:138)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)
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
        //       at pl.devfinder.api.controller.CandidateUserController.updateCandidateProfile(CandidateUserController.java:137)
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

        CityEntity cityEntity = new CityEntity();
        cityEntity.setCandidateResidenceCities(new HashSet<>());
        cityEntity.setCityId(1L);
        cityEntity.setCityName("Oxford");
        cityEntity.setEmployerCities(new HashSet<>());
        cityEntity.setOfferCities(new HashSet<>());

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
        CandidateEntity candidateEntity = mock(CandidateEntity.class);
        when(candidateEntity.getOpenToRemoteJob()).thenReturn(true);
        when(candidateEntity.getYearsOfExperience()).thenReturn(1);
        when(candidateEntity.getCandidateId()).thenReturn(1L);
        when(candidateEntity.getCandidateUuid()).thenReturn("2020-03-01");
        when(candidateEntity.getCvFilename()).thenReturn("foo.txt");
        when(candidateEntity.getEducation()).thenReturn("Education");
        when(candidateEntity.getEmailContact()).thenReturn("jane.doe@example.org");
        when(candidateEntity.getExperienceLevel()).thenReturn("Experience Level");
        when(candidateEntity.getFirstName()).thenReturn("Jane");
        when(candidateEntity.getForeignLanguage()).thenReturn("en");
        when(candidateEntity.getGithubLink()).thenReturn("Github Link");
        when(candidateEntity.getHobby()).thenReturn("Hobby");
        when(candidateEntity.getLastName()).thenReturn("Doe");
        when(candidateEntity.getLinkedinLink()).thenReturn("Linkedin Link");
        when(candidateEntity.getOtherSkills()).thenReturn("Other Skills");
        when(candidateEntity.getPhoneNumber()).thenReturn("6625550144");
        when(candidateEntity.getPhotoFilename()).thenReturn("foo.txt");
        when(candidateEntity.getStatus()).thenReturn("Status");
        when(candidateEntity.getSalaryMin()).thenReturn(BigDecimal.valueOf(1L));
        when(candidateEntity.getCreatedAt())
                .thenReturn(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        when(candidateEntity.getCandidateSkills()).thenReturn(new HashSet<>());
        when(candidateEntity.getResidenceCityId()).thenReturn(cityEntity);
        when(candidateEntity.getEmployerId()).thenReturn(employerEntity);
        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        when(candidateJpaRepository.findByCandidateUuid(Mockito.<String>any())).thenReturn(Optional.of(candidateEntity));
        CandidateRepository candidateDAO = new CandidateRepository(candidateJpaRepository,
                new CandidateEntityMapperImpl());

        CandidateCriteriaRepository candidateCriteriaRepository = mock(CandidateCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        CandidateSkillJpaRepository candidateSkillJpaRepository = mock(CandidateSkillJpaRepository.class);
        CandidateSkillEntityMapperImpl candidateSkillEntityMapper = new CandidateSkillEntityMapperImpl();
        CandidateSkillService candidateSkillService = new CandidateSkillService(new CandidateSkillRepository(
                candidateSkillJpaRepository, candidateSkillEntityMapper, new CandidateEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService2 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService3 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService3,
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

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService2,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl())));

        FileUploadService fileUploadService2 = new FileUploadService(new StandardReactiveWebEnvironment());
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository3 = mock(CandidateJpaRepository.class);
        CandidateService candidateService = new CandidateService(candidateDAO, candidateCriteriaRepository, cityService,
                skillService, candidateSkillService, employerService, fileUploadService2, new DataService(employerDAO3,
                offerDAO3, new CandidateRepository(candidateJpaRepository3, new CandidateEntityMapperImpl())));

        CandidateUpdateRequestMapper candidateUpdateRequestMapper = mock(CandidateUpdateRequestMapper.class);
        when(candidateUpdateRequestMapper.map(Mockito.<CandidateUpdateRequestDTO>any())).thenReturn(null);
        SkillService skillService2 = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        CandidateUserController candidateUserController = new CandidateUserController(userService, skillService2,
                skillMapper, candidateService, new CandidateDetailsMapperImpl(), candidateUpdateRequestMapper);
        CandidateUpdateRequestDTO candidateUpdateRequestDTO = new CandidateUpdateRequestDTO();
        BindException bindingResult = new BindException("Target", "Object Name");

        ConcurrentModel model = new ConcurrentModel();
        candidateUserController.updateCandidateProfile(candidateUpdateRequestDTO, bindingResult, model,
                new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link CandidateUserController#updateCandidateProfile(CandidateUpdateRequestDTO, BindingResult, Model, Authentication)}
     */
    @Test
    void testUpdateCandidateProfile5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.CandidateUserController.lambda$updateCandidateProfile$7(CandidateUserController.java:138)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.CandidateUserController.updateCandidateProfile(CandidateUserController.java:138)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        UserService userService = mock(UserService.class);
        when(userService.findByEmail(Mockito.<String>any())).thenReturn(Optional.empty());
        SkillService skillService = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        SkillMapper skillMapper = mock(SkillMapper.class);
        CandidateJpaRepository candidateJpaRepository = mock(CandidateJpaRepository.class);
        CandidateRepository candidateDAO = new CandidateRepository(candidateJpaRepository,
                new CandidateEntityMapperImpl());

        CandidateCriteriaRepository candidateCriteriaRepository = mock(CandidateCriteriaRepository.class);
        CityJpaRepository cityJpaRepository = mock(CityJpaRepository.class);
        CityService cityService = new CityService(new CityRepository(cityJpaRepository, new CityEntityMapperImpl()));
        SkillService skillService2 = new SkillService(
                new SkillRepository(mock(SkillJpaRepository.class), mock(SkillEntityMapper.class)));
        CandidateSkillJpaRepository candidateSkillJpaRepository = mock(CandidateSkillJpaRepository.class);
        CandidateSkillEntityMapperImpl candidateSkillEntityMapper = new CandidateSkillEntityMapperImpl();
        CandidateSkillService candidateSkillService = new CandidateSkillService(new CandidateSkillRepository(
                candidateSkillJpaRepository, candidateSkillEntityMapper, new CandidateEntityMapperImpl()));
        EmployerJpaRepository employerJpaRepository = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO = new EmployerRepository(employerJpaRepository, new EmployerEntityMapperImpl());

        EmployerCriteriaRepository employerCriteriaRepository = mock(EmployerCriteriaRepository.class);
        CityJpaRepository cityJpaRepository2 = mock(CityJpaRepository.class);
        CityService cityService2 = new CityService(new CityRepository(cityJpaRepository2, new CityEntityMapperImpl()));
        OfferJpaRepository offerJpaRepository = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper = new OfferEntityMapperImpl();
        OfferRepository offerDAO = new OfferRepository(offerJpaRepository, offerEntityMapper,
                new EmployerEntityMapperImpl());

        OfferCriteriaRepository offerCriteriaRepository = mock(OfferCriteriaRepository.class);
        DataService dataService = new DataService(null, null, null);

        CityService cityService3 = new CityService(null);
        OfferSkillService offerSkillService = new OfferSkillService(null);
        OfferService offerService = new OfferService(offerDAO, offerCriteriaRepository, dataService, cityService3,
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

        CandidateJpaRepository candidateJpaRepository2 = mock(CandidateJpaRepository.class);
        EmployerService employerService = new EmployerService(employerDAO, employerCriteriaRepository, cityService2,
                offerService, fileUploadService, offerSkillService2, new DataService(employerDAO2, offerDAO2,
                new CandidateRepository(candidateJpaRepository2, new CandidateEntityMapperImpl())));

        FileUploadService fileUploadService2 = new FileUploadService(new StandardReactiveWebEnvironment());
        EmployerJpaRepository employerJpaRepository3 = mock(EmployerJpaRepository.class);
        EmployerRepository employerDAO3 = new EmployerRepository(employerJpaRepository3, new EmployerEntityMapperImpl());

        OfferJpaRepository offerJpaRepository3 = mock(OfferJpaRepository.class);
        OfferEntityMapperImpl offerEntityMapper3 = new OfferEntityMapperImpl();
        OfferRepository offerDAO3 = new OfferRepository(offerJpaRepository3, offerEntityMapper3,
                new EmployerEntityMapperImpl());

        CandidateJpaRepository candidateJpaRepository3 = mock(CandidateJpaRepository.class);
        CandidateService candidateService = new CandidateService(candidateDAO, candidateCriteriaRepository, cityService,
                skillService2, candidateSkillService, employerService, fileUploadService2, new DataService(employerDAO3,
                offerDAO3, new CandidateRepository(candidateJpaRepository3, new CandidateEntityMapperImpl())));

        CandidateUserController candidateUserController = new CandidateUserController(userService, skillService,
                skillMapper, candidateService, new CandidateDetailsMapperImpl(), mock(CandidateUpdateRequestMapper.class));
        CandidateUpdateRequestDTO candidateUpdateRequestDTO = new CandidateUpdateRequestDTO();
        BindException bindingResult = new BindException("Target", "Object Name");

        ConcurrentModel model = new ConcurrentModel();
        assertThrows(NotFoundException.class,
                () -> candidateUserController.updateCandidateProfile(candidateUpdateRequestDTO, bindingResult, model,
                        new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userService).findByEmail(Mockito.<String>any());
    }
}

