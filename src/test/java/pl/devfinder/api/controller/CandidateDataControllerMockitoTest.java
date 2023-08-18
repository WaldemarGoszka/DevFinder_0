package pl.devfinder.api.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import pl.devfinder.api.dto.CandidateDetailsDTO;
import pl.devfinder.api.dto.mapper.*;
import pl.devfinder.business.*;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.User;
import pl.devfinder.domain.search.CandidateSearchCriteria;
import pl.devfinder.util.CandidateDetailsDTOFixtures;
import pl.devfinder.util.CandidateFixtures;
import pl.devfinder.util.UserFixtures;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CandidateDataControllerMockitoTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private Authentication authentication;

    @Mock
    private Model model;
    @Mock
    private EmployerService employerService;
    @Mock
    private EmployerRowMapper employerRowMapper;
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
    private CandidateService candidateService;
    @Mock
    private CandidateDetailsMapper candidateDetailsMapper;
    @Mock
    private UserController userController;
    @Mock
    private CandidateRowMapper candidateRowMapper;
    @Mock
    private FileUploadService fileUploadService;

    @InjectMocks
    private CandidateDataController candidateDataController;

    @Test
    public void getCandidatesListTest() {
        // given
        User user = UserFixtures.someUserCandidate1();
        CandidateSearchCriteria candidateSearchCriteria = new CandidateSearchCriteria();
        Page<Candidate> page = new PageImpl<>(List.of(CandidateFixtures.someCandidate1()));

        // when
        when(candidateService.findAllByCriteria(candidateSearchCriteria)).thenReturn(page);
        when(skillService.findAll()).thenReturn(new ArrayList<>());
        when(cityService.findAll()).thenReturn(new ArrayList<>());
        when(employerService.findAll()).thenReturn(new ArrayList<>());

        // then
        ModelAndView response = candidateDataController.getCandidatesList(candidateSearchCriteria, model, authentication);

        assertEquals("candidates", response.getViewName());

        verify(candidateService).findAllByCriteria(candidateSearchCriteria);
        verify(skillService).findAll();
        verify(cityService).findAll();
        verify(employerService).findAll();
    }

    @Test
    public void getCandidateDetailsTest() {
        // given
        User user = UserFixtures.someUserCandidate1();
        String userEmail = user.getEmail();
        Candidate candidate = CandidateFixtures.someCandidate1();
        Long candidateId = candidate.getCandidateId();
        CandidateDetailsDTO candidateDetailsDTO = CandidateDetailsDTOFixtures.someCandidateDetailsDTO1()
                .withPhotoFilename("photo.jpg")
                .withCandidateUuid("uuid");


        // when
        when(candidateService.findById(candidateId)).thenReturn(candidate);
        when(candidateDetailsMapper.map(any(Candidate.class))).thenReturn(candidateDetailsDTO);
        when(fileUploadService.getUserPhotoPath(candidateDetailsDTO.getCandidateUuid(),
                candidateDetailsDTO.getPhotoFilename())).thenReturn("/user_data/uuid_photo.jpg");


        String response = candidateDataController.getCandidateDetails(candidateId, model, authentication);

        // then
        assertEquals("candidate_details", response);

        verify(candidateService).findById(candidateId);
        verify(candidateDetailsMapper).map(any(Candidate.class));
        verify(fileUploadService).getUserPhotoPath(
                candidateDetailsDTO.getCandidateUuid(),candidateDetailsDTO.getPhotoFilename());
    }

}