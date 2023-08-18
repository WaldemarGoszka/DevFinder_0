package pl.devfinder.api.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
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
import pl.devfinder.api.dto.mapper.*;
import pl.devfinder.business.*;
import pl.devfinder.domain.exception.NotFoundException;
import pl.devfinder.domain.search.CandidateSearchCriteria;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {EmployerUserController.class})
@ExtendWith(SpringExtension.class)
class EmployerUserControllerDiffBlueTest {
    @MockBean
    private CandidateRowMapper candidateRowMapper;

    @MockBean
    private CandidateService candidateService;

    @MockBean
    private CityMapper cityMapper;

    @MockBean
    private CityService cityService;

    @MockBean
    private EmployerDetailsMapper employerDetailsMapper;

    @MockBean
    private EmployerService employerService;

    @MockBean
    private EmployerUpdateRequestMapper employerUpdateRequestMapper;

    @Autowired
    private EmployerUserController employerUserController;

    @MockBean
    private SkillMapper skillMapper;

    @MockBean
    private SkillService skillService;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link EmployerUserController#getEmployeesList(CandidateSearchCriteria, Model, Authentication)}
     */
    @Test
    void testGetEmployeesList() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.EmployerUserController.lambda$getEmployeesList$0(EmployerUserController.java:60)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.EmployerUserController.getEmployeesList(EmployerUserController.java:60)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        EmployerUserController employerUserController = new EmployerUserController(mock(UserService.class),
                mock(EmployerService.class), mock(EmployerDetailsMapper.class), mock(EmployerUpdateRequestMapper.class),
                mock(CandidateService.class), mock(CandidateRowMapper.class), mock(SkillService.class),
                mock(CityService.class), mock(CityMapper.class), mock(SkillMapper.class));

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
        assertThrows(NotFoundException.class,
                () -> employerUserController.getEmployeesList(candidateSearchCriteria, new ConcurrentModel(), null));
    }

    /**
     * Method under test: {@link EmployerUserController#getEmployeesList(CandidateSearchCriteria, Model, Authentication)}
     */
    @Test
    void testGetEmployeesList2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.EmployerUserController.lambda$getEmployeesList$0(EmployerUserController.java:60)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.EmployerUserController.getEmployeesList(EmployerUserController.java:60)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        UserService userService = mock(UserService.class);
        when(userService.findByEmail(Mockito.<String>any())).thenReturn(Optional.empty());
        EmployerUserController employerUserController = new EmployerUserController(userService,
                mock(EmployerService.class), mock(EmployerDetailsMapper.class), mock(EmployerUpdateRequestMapper.class),
                mock(CandidateService.class), mock(CandidateRowMapper.class), mock(SkillService.class),
                mock(CityService.class), mock(CityMapper.class), mock(SkillMapper.class));

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
        ConcurrentModel model = new ConcurrentModel();
        assertThrows(NotFoundException.class, () -> employerUserController.getEmployeesList(candidateSearchCriteria,
                model, new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userService).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link EmployerUserController#getEmployeesList(CandidateSearchCriteria, Model, Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetEmployeesList3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.EmployerUserController.lambda$getEmployeesList$0(EmployerUserController.java:60)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.EmployerUserController.getEmployeesList(EmployerUserController.java:60)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   pl.devfinder.domain.exception.NotFoundException: An error occurred
        //       at pl.devfinder.business.management.Utility.putUserDataToModel(Utility.java:34)
        //       at pl.devfinder.api.controller.EmployerUserController.getEmployeesList(EmployerUserController.java:59)
        //   See https://diff.blue/R013 to resolve this issue.

        UserService userService = mock(UserService.class);
        when(userService.findByEmail(Mockito.<String>any())).thenThrow(new NotFoundException("An error occurred"));
        EmployerUserController employerUserController = new EmployerUserController(userService,
                mock(EmployerService.class), mock(EmployerDetailsMapper.class), mock(EmployerUpdateRequestMapper.class),
                mock(CandidateService.class), mock(CandidateRowMapper.class), mock(SkillService.class),
                mock(CityService.class), mock(CityMapper.class), mock(SkillMapper.class));

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
        ConcurrentModel model = new ConcurrentModel();
        employerUserController.getEmployeesList(candidateSearchCriteria, model,
                new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link EmployerUserController#hireCandidate(String, Model, Authentication)}
     */
    @Test
    void testHireCandidate() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.EmployerUserController.lambda$hireCandidate$2(EmployerUserController.java:115)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.EmployerUserController.hireCandidate(EmployerUserController.java:115)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        EmployerUserController employerUserController = new EmployerUserController(mock(UserService.class),
                mock(EmployerService.class), mock(EmployerDetailsMapper.class), mock(EmployerUpdateRequestMapper.class),
                mock(CandidateService.class), mock(CandidateRowMapper.class), mock(SkillService.class),
                mock(CityService.class), mock(CityMapper.class), mock(SkillMapper.class));
        assertThrows(NotFoundException.class,
                () -> employerUserController.hireCandidate("foo", new ConcurrentModel(), null));
    }

    /**
     * Method under test: {@link EmployerUserController#hireCandidate(String, Model, Authentication)}
     */
    @Test
    void testHireCandidate2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.EmployerUserController.lambda$hireCandidate$2(EmployerUserController.java:115)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.EmployerUserController.hireCandidate(EmployerUserController.java:115)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        UserService userService = mock(UserService.class);
        when(userService.findByEmail(Mockito.<String>any())).thenReturn(Optional.empty());
        EmployerUserController employerUserController = new EmployerUserController(userService,
                mock(EmployerService.class), mock(EmployerDetailsMapper.class), mock(EmployerUpdateRequestMapper.class),
                mock(CandidateService.class), mock(CandidateRowMapper.class), mock(SkillService.class),
                mock(CityService.class), mock(CityMapper.class), mock(SkillMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        assertThrows(NotFoundException.class, () -> employerUserController.hireCandidate("2020-03-01", model,
                new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userService).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link EmployerUserController#hireCandidate(String, Model, Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHireCandidate3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.EmployerUserController.lambda$hireCandidate$2(EmployerUserController.java:115)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.EmployerUserController.hireCandidate(EmployerUserController.java:115)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   pl.devfinder.domain.exception.NotFoundException: An error occurred
        //       at pl.devfinder.business.management.Utility.putUserDataToModel(Utility.java:34)
        //       at pl.devfinder.api.controller.EmployerUserController.hireCandidate(EmployerUserController.java:114)
        //   See https://diff.blue/R013 to resolve this issue.

        UserService userService = mock(UserService.class);
        when(userService.findByEmail(Mockito.<String>any())).thenThrow(new NotFoundException("An error occurred"));
        EmployerUserController employerUserController = new EmployerUserController(userService,
                mock(EmployerService.class), mock(EmployerDetailsMapper.class), mock(EmployerUpdateRequestMapper.class),
                mock(CandidateService.class), mock(CandidateRowMapper.class), mock(SkillService.class),
                mock(CityService.class), mock(CityMapper.class), mock(SkillMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        employerUserController.hireCandidate("2020-03-01", model,
                new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link EmployerUserController#fireCandidate(String, Model, Authentication)}
     */
    @Test
    void testFireCandidate() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.EmployerUserController.lambda$fireCandidate$4(EmployerUserController.java:127)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.EmployerUserController.fireCandidate(EmployerUserController.java:127)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        EmployerUserController employerUserController = new EmployerUserController(mock(UserService.class),
                mock(EmployerService.class), mock(EmployerDetailsMapper.class), mock(EmployerUpdateRequestMapper.class),
                mock(CandidateService.class), mock(CandidateRowMapper.class), mock(SkillService.class),
                mock(CityService.class), mock(CityMapper.class), mock(SkillMapper.class));
        assertThrows(NotFoundException.class,
                () -> employerUserController.fireCandidate("foo", new ConcurrentModel(), null));
    }

    /**
     * Method under test: {@link EmployerUserController#fireCandidate(String, Model, Authentication)}
     */
    @Test
    void testFireCandidate2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.EmployerUserController.lambda$fireCandidate$4(EmployerUserController.java:127)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.EmployerUserController.fireCandidate(EmployerUserController.java:127)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        UserService userService = mock(UserService.class);
        when(userService.findByEmail(Mockito.<String>any())).thenReturn(Optional.empty());
        EmployerUserController employerUserController = new EmployerUserController(userService,
                mock(EmployerService.class), mock(EmployerDetailsMapper.class), mock(EmployerUpdateRequestMapper.class),
                mock(CandidateService.class), mock(CandidateRowMapper.class), mock(SkillService.class),
                mock(CityService.class), mock(CityMapper.class), mock(SkillMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        assertThrows(NotFoundException.class, () -> employerUserController.fireCandidate("2020-03-01", model,
                new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userService).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link EmployerUserController#fireCandidate(String, Model, Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFireCandidate3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.EmployerUserController.lambda$fireCandidate$4(EmployerUserController.java:127)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.EmployerUserController.fireCandidate(EmployerUserController.java:127)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   pl.devfinder.domain.exception.NotFoundException: An error occurred
        //       at pl.devfinder.business.management.Utility.putUserDataToModel(Utility.java:34)
        //       at pl.devfinder.api.controller.EmployerUserController.fireCandidate(EmployerUserController.java:126)
        //   See https://diff.blue/R013 to resolve this issue.

        UserService userService = mock(UserService.class);
        when(userService.findByEmail(Mockito.<String>any())).thenThrow(new NotFoundException("An error occurred"));
        EmployerUserController employerUserController = new EmployerUserController(userService,
                mock(EmployerService.class), mock(EmployerDetailsMapper.class), mock(EmployerUpdateRequestMapper.class),
                mock(CandidateService.class), mock(CandidateRowMapper.class), mock(SkillService.class),
                mock(CityService.class), mock(CityMapper.class), mock(SkillMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        employerUserController.fireCandidate("2020-03-01", model,
                new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link EmployerUserController#getEmployerProfile(Model, Authentication)}
     */
    @Test
    void testGetEmployerProfile() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.EmployerUserController.lambda$getEmployerProfile$6(EmployerUserController.java:137)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.EmployerUserController.getEmployerProfile(EmployerUserController.java:137)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        EmployerUserController employerUserController = new EmployerUserController(mock(UserService.class),
                mock(EmployerService.class), mock(EmployerDetailsMapper.class), mock(EmployerUpdateRequestMapper.class),
                mock(CandidateService.class), mock(CandidateRowMapper.class), mock(SkillService.class),
                mock(CityService.class), mock(CityMapper.class), mock(SkillMapper.class));
        assertThrows(NotFoundException.class,
                () -> employerUserController.getEmployerProfile(new ConcurrentModel(), null));
    }

    /**
     * Method under test: {@link EmployerUserController#getEmployerProfile(Model, Authentication)}
     */
    @Test
    void testGetEmployerProfile2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.EmployerUserController.lambda$getEmployerProfile$6(EmployerUserController.java:137)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.EmployerUserController.getEmployerProfile(EmployerUserController.java:137)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        UserService userService = mock(UserService.class);
        when(userService.findByEmail(Mockito.<String>any())).thenReturn(Optional.empty());
        EmployerUserController employerUserController = new EmployerUserController(userService,
                mock(EmployerService.class), mock(EmployerDetailsMapper.class), mock(EmployerUpdateRequestMapper.class),
                mock(CandidateService.class), mock(CandidateRowMapper.class), mock(SkillService.class),
                mock(CityService.class), mock(CityMapper.class), mock(SkillMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        assertThrows(NotFoundException.class, () -> employerUserController.getEmployerProfile(model,
                new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userService).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link EmployerUserController#getEmployerProfile(Model, Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetEmployerProfile3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.EmployerUserController.lambda$getEmployerProfile$6(EmployerUserController.java:137)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.EmployerUserController.getEmployerProfile(EmployerUserController.java:137)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   pl.devfinder.domain.exception.NotFoundException: An error occurred
        //       at pl.devfinder.business.management.Utility.putUserDataToModel(Utility.java:34)
        //       at pl.devfinder.api.controller.EmployerUserController.getEmployerProfile(EmployerUserController.java:136)
        //   See https://diff.blue/R013 to resolve this issue.

        UserService userService = mock(UserService.class);
        when(userService.findByEmail(Mockito.<String>any())).thenThrow(new NotFoundException("An error occurred"));
        EmployerUserController employerUserController = new EmployerUserController(userService,
                mock(EmployerService.class), mock(EmployerDetailsMapper.class), mock(EmployerUpdateRequestMapper.class),
                mock(CandidateService.class), mock(CandidateRowMapper.class), mock(SkillService.class),
                mock(CityService.class), mock(CityMapper.class), mock(SkillMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        employerUserController.getEmployerProfile(model, new TestingAuthenticationToken("Principal", "Credentials"));
    }

    /**
     * Method under test: {@link EmployerUserController#getEmployerEditProfile(Model, Authentication)}
     */
    @Test
    void testGetEmployerEditProfile() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.EmployerUserController.lambda$getEmployerEditProfile$7(EmployerUserController.java:153)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.EmployerUserController.getEmployerEditProfile(EmployerUserController.java:153)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        EmployerUserController employerUserController = new EmployerUserController(mock(UserService.class),
                mock(EmployerService.class), mock(EmployerDetailsMapper.class), mock(EmployerUpdateRequestMapper.class),
                mock(CandidateService.class), mock(CandidateRowMapper.class), mock(SkillService.class),
                mock(CityService.class), mock(CityMapper.class), mock(SkillMapper.class));
        assertThrows(NotFoundException.class,
                () -> employerUserController.getEmployerEditProfile(new ConcurrentModel(), null));
    }

    /**
     * Method under test: {@link EmployerUserController#getEmployerEditProfile(Model, Authentication)}
     */
    @Test
    void testGetEmployerEditProfile2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.EmployerUserController.lambda$getEmployerEditProfile$7(EmployerUserController.java:153)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.EmployerUserController.getEmployerEditProfile(EmployerUserController.java:153)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        UserService userService = mock(UserService.class);
        when(userService.findByEmail(Mockito.<String>any())).thenReturn(Optional.empty());
        EmployerUserController employerUserController = new EmployerUserController(userService,
                mock(EmployerService.class), mock(EmployerDetailsMapper.class), mock(EmployerUpdateRequestMapper.class),
                mock(CandidateService.class), mock(CandidateRowMapper.class), mock(SkillService.class),
                mock(CityService.class), mock(CityMapper.class), mock(SkillMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        assertThrows(NotFoundException.class, () -> employerUserController.getEmployerEditProfile(model,
                new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userService).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link EmployerUserController#getEmployerEditProfile(Model, Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetEmployerEditProfile3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.EmployerUserController.lambda$getEmployerEditProfile$7(EmployerUserController.java:153)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.EmployerUserController.getEmployerEditProfile(EmployerUserController.java:153)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   pl.devfinder.domain.exception.NotFoundException: An error occurred
        //       at pl.devfinder.business.management.Utility.putUserDataToModel(Utility.java:34)
        //       at pl.devfinder.api.controller.EmployerUserController.getEmployerEditProfile(EmployerUserController.java:152)
        //   See https://diff.blue/R013 to resolve this issue.

        UserService userService = mock(UserService.class);
        when(userService.findByEmail(Mockito.<String>any())).thenThrow(new NotFoundException("An error occurred"));
        EmployerUserController employerUserController = new EmployerUserController(userService,
                mock(EmployerService.class), mock(EmployerDetailsMapper.class), mock(EmployerUpdateRequestMapper.class),
                mock(CandidateService.class), mock(CandidateRowMapper.class), mock(SkillService.class),
                mock(CityService.class), mock(CityMapper.class), mock(SkillMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        employerUserController.getEmployerEditProfile(model, new TestingAuthenticationToken("Principal", "Credentials"));
    }


    /**
     * Method under test: {@link EmployerUserController#deleteEmployerProfile(Model, Authentication)}
     */
    @Test
    void testDeleteEmployerProfile() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(employerUserController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link EmployerUserController#deleteLogoFile(Model, Authentication)}
     */
    @Test
    void testDeleteLogoFile() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:596)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.EmployerUserController.lambda$deleteLogoFile$11(EmployerUserController.java:212)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.EmployerUserController.deleteLogoFile(EmployerUserController.java:212)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:596)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        EmployerUserController employerUserController = new EmployerUserController(mock(UserService.class),
                mock(EmployerService.class), mock(EmployerDetailsMapper.class), mock(EmployerUpdateRequestMapper.class),
                mock(CandidateService.class), mock(CandidateRowMapper.class), mock(SkillService.class),
                mock(CityService.class), mock(CityMapper.class), mock(SkillMapper.class));
        assertThrows(NotFoundException.class, () -> employerUserController.deleteLogoFile(new ConcurrentModel(), null));
    }

    /**
     * Method under test: {@link EmployerUserController#deleteLogoFile(Model, Authentication)}
     */
    @Test
    void testDeleteLogoFile2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:596)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.EmployerUserController.lambda$deleteLogoFile$11(EmployerUserController.java:212)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.EmployerUserController.deleteLogoFile(EmployerUserController.java:212)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:596)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        UserService userService = mock(UserService.class);
        when(userService.findByEmail(Mockito.<String>any())).thenReturn(Optional.empty());
        EmployerUserController employerUserController = new EmployerUserController(userService,
                mock(EmployerService.class), mock(EmployerDetailsMapper.class), mock(EmployerUpdateRequestMapper.class),
                mock(CandidateService.class), mock(CandidateRowMapper.class), mock(SkillService.class),
                mock(CityService.class), mock(CityMapper.class), mock(SkillMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        assertThrows(NotFoundException.class, () -> employerUserController.deleteLogoFile(model,
                new TestingAuthenticationToken("Principal", "Credentials")));
        verify(userService).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link EmployerUserController#deleteLogoFile(Model, Authentication)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteLogoFile3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:596)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   pl.devfinder.domain.exception.NotFoundException: Could not find user or authentication failed
        //       at pl.devfinder.api.controller.EmployerUserController.lambda$deleteLogoFile$11(EmployerUserController.java:212)
        //       at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        //       at pl.devfinder.api.controller.EmployerUserController.deleteLogoFile(EmployerUserController.java:212)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:596)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   pl.devfinder.domain.exception.NotFoundException: An error occurred
        //       at pl.devfinder.business.management.Utility.putUserDataToModel(Utility.java:34)
        //       at pl.devfinder.api.controller.EmployerUserController.deleteLogoFile(EmployerUserController.java:211)
        //   See https://diff.blue/R013 to resolve this issue.

        UserService userService = mock(UserService.class);
        when(userService.findByEmail(Mockito.<String>any())).thenThrow(new NotFoundException("An error occurred"));
        EmployerUserController employerUserController = new EmployerUserController(userService,
                mock(EmployerService.class), mock(EmployerDetailsMapper.class), mock(EmployerUpdateRequestMapper.class),
                mock(CandidateService.class), mock(CandidateRowMapper.class), mock(SkillService.class),
                mock(CityService.class), mock(CityMapper.class), mock(SkillMapper.class));
        ConcurrentModel model = new ConcurrentModel();
        employerUserController.deleteLogoFile(model, new TestingAuthenticationToken("Principal", "Credentials"));
    }
}

