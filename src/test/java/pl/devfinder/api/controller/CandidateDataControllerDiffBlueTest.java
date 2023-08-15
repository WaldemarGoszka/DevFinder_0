//package pl.devfinder.api.controller;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.core.Authentication;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.ui.Model;
//import pl.devfinder.api.dto.mapper.CandidateDetailsMapper;
//import pl.devfinder.api.dto.mapper.CandidateRowMapper;
//import pl.devfinder.api.dto.mapper.CityMapper;
//import pl.devfinder.api.dto.mapper.EmployerRowMapper;
//import pl.devfinder.api.dto.mapper.SkillMapper;
//import pl.devfinder.business.CandidateService;
//import pl.devfinder.business.CityService;
//import pl.devfinder.business.EmployerService;
//import pl.devfinder.business.SkillService;
//import pl.devfinder.business.UserService;
//import pl.devfinder.domain.search.CandidateSearchCriteria;
//
//@ContextConfiguration(classes = {CandidateDataController.class})
//@ExtendWith(SpringExtension.class)
//class CandidateDataControllerDiffBlueTest {
//    @Autowired
//    private CandidateDataController candidateDataController;
//
//    @MockBean
//    private CandidateDetailsMapper candidateDetailsMapper;
//
//    @MockBean
//    private CandidateRowMapper candidateRowMapper;
//
//    @MockBean
//    private CandidateService candidateService;
//
//    @MockBean
//    private CityMapper cityMapper;
//
//    @MockBean
//    private CityService cityService;
//
//    @MockBean
//    private EmployerRowMapper employerRowMapper;
//
//    @MockBean
//    private EmployerService employerService;
//
//    @MockBean
//    private SkillMapper skillMapper;
//
//    @MockBean
//    private SkillService skillService;
//
//    @MockBean
//    private UserController userController;
//
//    @MockBean
//    private UserService userService;
//
//    /**
//     * Method under test: {@link CandidateDataController#getCandidateDetails(Long, Model, Authentication)}
//     */
//    @Test
//    void testGetCandidateDetails() throws Exception {
//        // Arrange
//        // TODO: Populate arranged inputs
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/candidate/{candidateId}", 1L);
//        MockMvc buildResult = MockMvcBuilders.standaloneSetup(candidateDataController).build();
//
//        // Act
//        ResultActions actualPerformResult = buildResult.perform(requestBuilder);
//
//        // Assert
//        // TODO: Add assertions on result
//    }
//
//    /**
//     * Method under test: {@link CandidateDataController#getCandidatesList(CandidateSearchCriteria, Model, Authentication)}
//     */
//    @Test
//    void testGetCandidatesList() throws Exception {
//        // Arrange
//        // TODO: Populate arranged inputs
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/candidates");
//        MockMvc buildResult = MockMvcBuilders.standaloneSetup(candidateDataController).build();
//
//        // Act
//        ResultActions actualPerformResult = buildResult.perform(requestBuilder);
//
//        // Assert
//        // TODO: Add assertions on result
//    }
//
//    /**
//     * Method under test: {@link CandidateDataController#homePage()}
//     */
//    @Test
//    void testHomePage() throws Exception {
//        // Arrange
//        // TODO: Populate arranged inputs
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
//        MockMvc buildResult = MockMvcBuilders.standaloneSetup(candidateDataController).build();
//
//        // Act
//        ResultActions actualPerformResult = buildResult.perform(requestBuilder);
//
//        // Assert
//        // TODO: Add assertions on result
//    }
//}
//
