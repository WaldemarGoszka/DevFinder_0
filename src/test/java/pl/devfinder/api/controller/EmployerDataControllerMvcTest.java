package pl.devfinder.api.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.devfinder.api.dto.mapper.CityMapper;
import pl.devfinder.api.dto.mapper.EmployerDetailsMapper;
import pl.devfinder.api.dto.mapper.EmployerRowMapper;
import pl.devfinder.api.dto.mapper.SkillMapper;
import pl.devfinder.business.CityService;
import pl.devfinder.business.EmployerService;
import pl.devfinder.business.SkillService;
import pl.devfinder.business.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EmployerDataController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class EmployerDataControllerMvcTest {

    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private CityService cityService;

    @MockBean
    private CityMapper cityMapper;

    @MockBean
    private SkillService skillService;

    @MockBean
    private SkillMapper skillMapper;

    @MockBean
    private EmployerService employerService;

    @MockBean
    private EmployerRowMapper employerRowMapper;

    @MockBean
    private EmployerDetailsMapper employerDetailsMapper;

    @MockBean
    private UserController userController;

    @Test
    void getEmployersList() throws Exception {
        //given

        mockMvc.perform(get(EmployerDataController.EMPLOYERS_LIST))
                .andExpect(status().isOk());
//                .andExpect(model().attributeExists("invoiceNumber"))
//                .andExpect(model().attributeExists("customerName"))
//                .andExpect(model().attributeExists("customerSurname"))
//                .andExpect(view().name("car_purchase_done"));
    }


}