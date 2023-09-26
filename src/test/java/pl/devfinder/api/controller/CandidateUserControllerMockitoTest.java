package pl.devfinder.api.controller;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import pl.devfinder.api.dto.CandidateDetailsDTO;
import pl.devfinder.api.dto.mapper.CandidateDetailsMapper;
import pl.devfinder.api.dto.mapper.CandidateUpdateRequestMapper;
import pl.devfinder.api.dto.mapper.SkillMapper;
import pl.devfinder.business.CandidateService;
import pl.devfinder.business.FileService;
import pl.devfinder.business.SkillService;
import pl.devfinder.business.UserService;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.User;
import pl.devfinder.domain.exception.NotFoundException;
import pl.devfinder.util.CandidateDetailsDTOFixtures;
import pl.devfinder.util.CandidateFixtures;
import pl.devfinder.util.UserFixtures;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CandidateUserControllerMockitoTest {

    @Mock
    private UserService userService;

    @Mock
    private SkillService skillService;

    @Mock
    private SkillMapper skillMapper;

    @Mock
    private CandidateService candidateService;

    @Mock
    private CandidateDetailsMapper candidateDetailsMapper;

    @Mock
    private CandidateUpdateRequestMapper candidateUpdateRequestMapper;

    @Mock
    private UserController userController;

    @Mock
    private FileService fileService;

    @InjectMocks
    private CandidateUserController candidateUserController;

    @Test
    void testGetCandidateProfile() {
        // given
        Model model = new ConcurrentModel();
        Authentication authentication = Mockito.mock(Authentication.class);
        User user = UserFixtures.someUserCandidate1();
        Candidate candidate = CandidateFixtures.someCandidate1();
        CandidateDetailsDTO candidateDetailsDTO = CandidateDetailsDTOFixtures.someCandidateDetailsDTO1();

        // when
        Mockito.when(userController.putUserDataToModel(Mockito.eq(authentication), Mockito.eq(userService), Mockito.eq(model)))
                .thenReturn(Optional.of(user));
        Mockito.when(candidateService.findByCandidateUuid(Mockito.anyString())).thenReturn(Optional.of(candidate));
        Mockito.when(candidateDetailsMapper.map(Mockito.eq(candidate))).thenReturn(candidateDetailsDTO);
        Mockito.when(fileService.getUserPhotoPath(Mockito.anyString(), Mockito.anyString())).thenReturn("/path/to/file");

        String viewName = candidateUserController.getCandidateProfile(model, authentication);

        // then
        Assertions.assertEquals("candidate/profile", viewName);
        Assertions.assertEquals(candidateDetailsDTO, model.getAttribute("candidateDetailsDTO"));
        Assertions.assertEquals("/path/to/file", model.getAttribute("downloadCvFilePath"));
        Mockito.verify(candidateService, Mockito.times(1)).findByCandidateUuid(Mockito.anyString());
        Mockito.verify(candidateDetailsMapper, Mockito.times(1)).map(Mockito.eq(candidate));
        Mockito.verify(fileService, Mockito.times(2)).getUserPhotoPath(Mockito.anyString(), Mockito.anyString());

    }
    @Test
    void testGetCandidateProfileWhenCandidateNotExist() {
        // given
        Model model = new ConcurrentModel();
        Authentication authentication = Mockito.mock(Authentication.class);
        User user = UserFixtures.someUserCandidate1();

        // when
        Mockito.when(userController.putUserDataToModel(Mockito.eq(authentication), Mockito.eq(userService), Mockito.eq(model)))
                .thenReturn(Optional.of(user));
        Mockito.when(candidateService.findByCandidateUuid(Mockito.anyString())).thenReturn(Optional.empty());

        String viewName = candidateUserController.getCandidateProfile(model, authentication);

        // then
        Assertions.assertEquals("redirect:edit_profile?new", viewName);
        Mockito.verify(candidateService, Mockito.times(1)).findByCandidateUuid(Mockito.anyString());
    }

    @Test
    void testGetCandidateProfileWhenUserNotFind() {
        // given
        Model model = new ConcurrentModel();
        Authentication authentication = Mockito.mock(Authentication.class);

        // when then
        Mockito.when(userController.putUserDataToModel(Mockito.eq(authentication), Mockito.eq(userService), Mockito.eq(model)))
                .thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> candidateUserController.getCandidateProfile(model, authentication));
    }

    @Test
    public void testGetCandidateEditProfile() {
        Model model = new ConcurrentModel();
        Authentication authentication = Mockito.mock(Authentication.class);

        // Mock userController.putUserDataToModel method
        User user = new User(); // create a mock user
        Mockito.when(userController.putUserDataToModel(Mockito.eq(authentication), Mockito.eq(userService), Mockito.eq(model)))
                .thenReturn(Optional.of(user));

        // Mock candidateService.findByCandidateUuid method
        Candidate candidate = new Candidate(); // create a mock candidate
        Mockito.when(candidateService.findByCandidateUuid(Mockito.anyString())).thenReturn(Optional.of(candidate));

        // Mock candidateDetailsMapper.map method
        CandidateDetailsDTO candidateDetailsDTO = new CandidateDetailsDTO(); // create a mock DTO
        Mockito.when(candidateDetailsMapper.map(Mockito.eq(candidate))).thenReturn(candidateDetailsDTO);

        // Mock skillService.findAll method
        Skill skill1 = new Skill();
        Skill skill2 = new Skill();
        List<Skill> allSkills = Arrays.asList(skill1, skill2);
        Mockito.when(skillService.findAll()).thenReturn(allSkills);

        // Perform the controller method invocation
        String viewName = candidateUserController.getCandidateEditProfile(model, authentication);

        // Verify the interactions and assertions
        Assert.assertEquals("candidate/edit_profile", viewName);
        Assert.assertEquals(candidateDetailsDTO, model.getAttribute("candidateDetailsDTO"));
        Assert.assertEquals(candidateDetailsDTO.getStatus(), model.getAttribute("statusChecked"));
        // ... verify other attributes as needed ...

        Mockito.verify(userService, Mockito.times(1)).findByUserUuid(Mockito.anyString());
        Mockito.verify(candidateService, Mockito.times(1)).findByCandidateUuid(Mockito.anyString());
        Mockito.verify(candidateDetailsMapper, Mockito.times(1)).map(Mockito.eq(candidate));
        Mockito.verify(skillService, Mockito.times(1)).findAll();
        // ... verify other interactions as needed ...
    }

    @Test
    void deleteCvFile() {
    }

    @Test
    void deletePhotoFile() {
    }

    @Test
    void updateCandidateProfile() {
    }

    @Test
    void deleteCandidateProfile() {
    }
}