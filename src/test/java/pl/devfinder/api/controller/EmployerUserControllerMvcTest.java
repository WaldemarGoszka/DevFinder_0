package pl.devfinder.api.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import pl.devfinder.api.dto.mapper.*;
import pl.devfinder.business.*;
import pl.devfinder.domain.User;
import pl.devfinder.infrastructure.security.configuration.UserDetailsCustom;
import pl.devfinder.util.UserFixtures;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EmployerUserController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class EmployerUserControllerMvcTest {

    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @MockBean
    private EmployerService employerService;
    @MockBean
    private EmployerDetailsMapper employerDetailsMapper;
    @MockBean
    private EmployerUpdateRequestMapper employerUpdateRequestMapper;
    @MockBean
    private CandidateService candidateService;
    @MockBean
    private CandidateRowMapper candidateRowMapper;
    @MockBean
    private SkillService skillService;
    @MockBean
    private CityService cityService;
    @MockBean
    private CityMapper cityMapper;
    @MockBean
    private SkillMapper skillMapper;

//    @BeforeEach
//    void logInEmployer() {
//        UserDetails userDetails = new UserDetailsCustom(
//                "kontakt@abccorp.com",
//                "test",
//                true,
//                List.of(new SimpleGrantedAuthority(Keys.Role.EMPLOYER.getName())));
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//    }

    @Test
    void getEmployeesList() throws Exception {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("EMPLOYER"));
        //given
        UserDetails userDetails = new UserDetailsCustom(
                "kontakt@abccorp.com",
                "test",
                true,
                authorities);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,authorities);
        System.out.println(authentication.isAuthenticated());
//        authentication.setAuthenticated(true);

        User user = UserFixtures.someUserEmployer1();
        Mockito.when(userService.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        mockMvc.perform(get(EmployerUserController.BASE_PATH + EmployerUserController.EMPLOYER_PROFILE))
                .andExpect(status().isOk());
//                .andExpect(model().attributeExists("invoiceNumber"))
//                .andExpect(model().attributeExists("customerName"))
//                .andExpect(model().attributeExists("customerSurname"))
//                .andExpect(view().name("car_purchase_done"));
    }

    @Test
    void hireCandidate() {
    }

    @Test
    void fireCandidate() {
    }

    @Test
    void getEmployerProfile() {
    }

    @Test
    void getEmployerEditProfile() {
    }

    @Test
    void updateEmployerProfile() {
    }

    @Test
    void deleteEmployerProfile() {
    }

    @Test
    void deleteLogoFile() {
    }
}