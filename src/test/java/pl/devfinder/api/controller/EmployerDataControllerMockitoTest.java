package pl.devfinder.api.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import pl.devfinder.api.dto.EmployerDetailsDTO;
import pl.devfinder.api.dto.mapper.CityMapper;
import pl.devfinder.api.dto.mapper.EmployerDetailsMapper;
import pl.devfinder.api.dto.mapper.EmployerRowMapper;
import pl.devfinder.api.dto.mapper.SkillMapper;
import pl.devfinder.business.*;
import pl.devfinder.domain.Employer;
import pl.devfinder.domain.User;
import pl.devfinder.domain.search.EmployerSearchCriteria;
import pl.devfinder.util.EmployerDetailsDTOFixtures;
import pl.devfinder.util.EmployerFixtures;
import pl.devfinder.util.UserFixtures;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployerDataControllerMockitoTest {
    @Mock
    private UserService userService;
    @Mock
    private CityService cityService;
    @Mock
    private CityMapper cityMapper;
    @Mock
    private SkillService skillService;
    @Mock
    private SkillMapper skillMapper;
    @Mock
    private Authentication authentication;
    @Mock
    private EmployerService employerService;
    @Mock
    private EmployerRowMapper employerRowMapper;
    @Mock
    private EmployerDetailsMapper employerDetailsMapper;
    @Mock
    private UserController userController;
    @Mock
    private FileService fileService;
    @Mock
    private Model model;

    @InjectMocks
    private EmployerDataController employerDataController;

    @Test
    public void getEmployersListTest() {
        // given
        User user = UserFixtures.someUserCandidate1();
        EmployerSearchCriteria employerSearchCriteria = new EmployerSearchCriteria();
        Page<Employer> page = new PageImpl<>(List.of(EmployerFixtures.someEmployer1()));

        // when
        when(employerService.findAllByCriteria(employerSearchCriteria)).thenReturn(page);
        when(cityService.findAll()).thenReturn(new ArrayList<>());
        when(skillService.findAll()).thenReturn(new ArrayList<>());


        ModelAndView response = employerDataController.getEmployersList(employerSearchCriteria, model, authentication);

        // then
        assertEquals("employers", response.getViewName());

        verify(employerService).findAllByCriteria(employerSearchCriteria);
        verify(skillService).findAll();
        verify(cityService).findAll();
    }

    @Test
    public void getEmployerDetailsTest() {
        // given
        User user = UserFixtures.someUserCandidate1();
        String userEmail = user.getEmail();
        Employer employer = EmployerFixtures.someEmployer1();
        Long employerId = employer.getEmployerId();
        EmployerDetailsDTO employerDetailsDTO = EmployerDetailsDTOFixtures.someEmployerDetailsDTO1()
                .withLogoFilename("logo.jpg")
                .withEmployerUuid("uuid");

        // when
        when(employerService.findById(employerId)).thenReturn(employer);
        when(employerDetailsMapper.map(employer)).thenReturn(employerDetailsDTO);
        when(fileService.getUserPhotoPath(employerDetailsDTO.getEmployerUuid(),
                employerDetailsDTO.getLogoFilename())).thenReturn("/user_data/uuid_logo.jpg");

        String response = employerDataController.getEmployerDetails(employerId, model, authentication);

        // then
        assertEquals("employer_details", response);

        verify(employerService).findById(employerId);
        verify(employerDetailsMapper).map(employer);
        verify(fileService).getUserPhotoPath(employerDetailsDTO.getEmployerUuid(), employerDetailsDTO.getLogoFilename());
    }

}