package pl.devfinder.api.controller.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import pl.devfinder.api.controller.exception.ProfileAlreadyExistException;
import pl.devfinder.api.controller.exception.ProfileNotExistException;
import pl.devfinder.api.dto.CandidateDetailsDTO;
import pl.devfinder.api.dto.CandidateUpdateRequestDTO;
import pl.devfinder.api.dto.mapper.CandidateDetailsMapper;
import pl.devfinder.api.dto.mapper.CandidateRowMapper;
import pl.devfinder.api.dto.mapper.CandidateUpdateRequestMapper;
import pl.devfinder.business.CandidateService;
import pl.devfinder.business.UserService;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.CandidateUpdateRequest;
import pl.devfinder.domain.User;
import pl.devfinder.domain.exception.NotFoundException;
import pl.devfinder.util.CandidateDetailsDTOFixtures;
import pl.devfinder.util.CandidateFixtures;
import pl.devfinder.util.CandidateUpdateRequestFixtures;
import pl.devfinder.util.UserFixtures;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CandidateUserRestControllerMockitoTest {
    @Mock
    private CandidateService candidateService;
    @Mock
    private CandidateDetailsMapper candidateDetailsMapper;
    @Mock
    private CandidateRowMapper candidateRowMapper;
    @Mock
    private UserService userService;
    @Mock
    private CandidateUpdateRequest candidateUpdateRequest;
    @Mock
    private CandidateUpdateRequestMapper candidateUpdateRequestMapper;
    @Mock
    private Authentication authentication;

    @InjectMocks
    private CandidateUserRestController candidateUserRestController;


    @Test
    void getCandidateProfile() {
        // given
        User user = UserFixtures.someUserCandidate1();
        String userEmail = user.getEmail();
        Candidate candidate = CandidateFixtures.someCandidate1();
        CandidateDetailsDTO expectedCandidateDetailsDTO = CandidateDetailsDTOFixtures.someCandidateDetailsDTO1();

        // when
        when(authentication.getName()).thenReturn(userEmail);
        when(userService.findByEmail(userEmail)).thenReturn(Optional.of(user));
        when(candidateService.findByCandidateUuid(user.getUserUuid())).thenReturn(Optional.of(candidate));
        when(candidateDetailsMapper.map(candidate)).thenReturn(expectedCandidateDetailsDTO);

        ResponseEntity<CandidateDetailsDTO> responseEntity = candidateUserRestController.getCandidateProfile(authentication);
        CandidateDetailsDTO actualCandidateDetailsDTO = responseEntity.getBody();

        // then
        verify(userService).findByEmail(userEmail);
        verify(candidateService).findByCandidateUuid(user.getUserUuid());
        verify(candidateDetailsMapper).map(candidate);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        assertNotNull(actualCandidateDetailsDTO);
        assertEquals(expectedCandidateDetailsDTO, actualCandidateDetailsDTO);
    }

    @Test
    void getCandidateProfileUserNotFound() {
        // given
        String userEmail = "not_found@example.com";

        // when
        when(authentication.getName()).thenReturn(userEmail);
        when(userService.findByEmail(userEmail)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> candidateUserRestController.getCandidateProfile(authentication));

        // then
        verify(userService).findByEmail(userEmail);
        verifyNoInteractions(candidateService);
        verifyNoInteractions(candidateDetailsMapper);
    }

        @Test
        void getCandidateProfileCandidateNotFound() {
            // given
            User user = UserFixtures.someUserCandidate1();
            String userEmail = user.getEmail();

            // when
            when(authentication.getName()).thenReturn(userEmail);
            when(userService.findByEmail(userEmail)).thenReturn(Optional.of(user));
            when(candidateService.findByCandidateUuid(user.getUserUuid())).thenReturn(Optional.empty());

            assertThrows(ProfileNotExistException.class, () -> candidateUserRestController.getCandidateProfile(authentication));

            // then
            verify(userService).findByEmail(userEmail);
            verify(candidateService).findByCandidateUuid(user.getUserUuid());
            verifyNoInteractions(candidateDetailsMapper);
        }

    @Test
    void createCandidateProfile() {
        // given
        CandidateUpdateRequestDTO candidateUpdateRequestDTO = new CandidateUpdateRequestDTO();
        User user = UserFixtures.someUserCandidate1();
        String userEmail = user.getEmail();
        CandidateUpdateRequest candidateUpdateRequest = CandidateUpdateRequestFixtures.someCandidateUpdateRequest1();
        Candidate candidateSaved = CandidateFixtures.someCandidate1();
        CandidateDetailsDTO expectedCandidateDetailsDTO = CandidateDetailsDTOFixtures.someCandidateDetailsDTO1();

        // when
        when(authentication.getName()).thenReturn(userEmail);
        when(userService.findByEmail(userEmail)).thenReturn(Optional.of(user));
        when(candidateService.findByCandidateUuid(user.getUserUuid())).thenReturn(Optional.empty());
        when(candidateUpdateRequestMapper.map(candidateUpdateRequestDTO)).thenReturn(candidateUpdateRequest);
        when(candidateService.newCandidateProfile(candidateUpdateRequest, user)).thenReturn(candidateSaved);
        when(candidateDetailsMapper.map(candidateSaved)).thenReturn(expectedCandidateDetailsDTO);

        ResponseEntity<CandidateDetailsDTO> responseEntity = candidateUserRestController.createCandidateProfile(candidateUpdateRequestDTO, authentication);
        CandidateDetailsDTO actualCandidateDetailsDTO = responseEntity.getBody();

        // then
        verify(userService).findByEmail(userEmail);
        verify(candidateService).findByCandidateUuid(user.getUserUuid());
        verify(candidateUpdateRequestMapper).map(candidateUpdateRequestDTO);
        verify(candidateService).newCandidateProfile(candidateUpdateRequest, user);
        verify(candidateDetailsMapper).map(candidateSaved);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        assertNotNull(actualCandidateDetailsDTO);
        assertEquals(expectedCandidateDetailsDTO, actualCandidateDetailsDTO);
    }

    @Test
    void createCandidateProfileProfileAlreadyExists() {
        // given
        CandidateUpdateRequestDTO candidateUpdateRequestDTO = new CandidateUpdateRequestDTO();
        User user = UserFixtures.someUserCandidate1();
        String userEmail = user.getEmail();
        Candidate candidate = CandidateFixtures.someCandidate1();

        // when
        when(authentication.getName()).thenReturn(userEmail);
        when(userService.findByEmail(userEmail)).thenReturn(Optional.of(user));
        when(candidateService.findByCandidateUuid(user.getUserUuid())).thenReturn(Optional.of(candidate));

        assertThrows(ProfileAlreadyExistException.class, () -> candidateUserRestController.createCandidateProfile(candidateUpdateRequestDTO, authentication));

        // then
        verify(candidateService).findByCandidateUuid(user.getUserUuid());
        verifyNoInteractions(candidateUpdateRequestMapper);
    }

    @Test
    void updateCandidateProfile() {
        // given
        CandidateUpdateRequestDTO candidateUpdateRequestDTO = new CandidateUpdateRequestDTO();
        User user = UserFixtures.someUserCandidate1();
        String userEmail = user.getEmail();
        Candidate candidate = CandidateFixtures.someCandidate1();
        CandidateUpdateRequest candidateUpdateRequest = CandidateUpdateRequestFixtures.someCandidateUpdateRequest1();
        Candidate candidateSaved = CandidateFixtures.someCandidate1();
        CandidateDetailsDTO expectedCandidateDetailsDTO = CandidateDetailsDTOFixtures.someCandidateDetailsDTO1();

        // when
        when(authentication.getName()).thenReturn(userEmail);
        when(userService.findByEmail(userEmail)).thenReturn(Optional.of(user));
        when(candidateService.findByCandidateUuid(user.getUserUuid())).thenReturn(Optional.of(candidate));
        when(candidateUpdateRequestMapper.map(candidateUpdateRequestDTO)).thenReturn(candidateUpdateRequest);
        when(candidateService.updateCandidateProfile(candidateUpdateRequest, candidate)).thenReturn(candidateSaved);
        when(candidateDetailsMapper.map(candidateSaved)).thenReturn(expectedCandidateDetailsDTO);

        ResponseEntity<CandidateDetailsDTO> responseEntity = candidateUserRestController.updateCandidateProfile(candidateUpdateRequestDTO, authentication);
        CandidateDetailsDTO actualCandidateDetailsDTO = responseEntity.getBody();

        // then
        verify(userService).findByEmail(userEmail);
        verify(candidateService).findByCandidateUuid(user.getUserUuid());
        verify(candidateUpdateRequestMapper).map(candidateUpdateRequestDTO);
        verify(candidateService).updateCandidateProfile(candidateUpdateRequest, candidate);
        verify(candidateDetailsMapper).map(candidateSaved);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        assertNotNull(actualCandidateDetailsDTO);
        assertEquals(expectedCandidateDetailsDTO, actualCandidateDetailsDTO);
    }

    @Test
    void updateCandidateProfileCandidateNotFound() {
        // given
        CandidateUpdateRequestDTO candidateUpdateRequestDTO = new CandidateUpdateRequestDTO();
        User user = UserFixtures.someUserCandidate1();
        String userEmail = user.getEmail();

        // when
        when(authentication.getName()).thenReturn(userEmail);
        when(userService.findByEmail(userEmail)).thenReturn(Optional.of(user));
        when(candidateService.findByCandidateUuid(user.getUserUuid())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> candidateUserRestController.updateCandidateProfile(candidateUpdateRequestDTO, authentication));

        // then
        verify(userService).findByEmail(userEmail);
        verify(candidateService).findByCandidateUuid(user.getUserUuid());
        verifyNoInteractions(candidateUpdateRequestMapper);
        verifyNoInteractions(candidateDetailsMapper);
    }

    @Test
    void deleteCandidateProfile() {
        // given
        User user = UserFixtures.someUserCandidate1();
        String userEmail = user.getEmail();
        Candidate candidate = CandidateFixtures.someCandidate1();

        // when
        when(authentication.getName()).thenReturn(userEmail);
        when(userService.findByEmail(userEmail)).thenReturn(Optional.of(user));
        when(candidateService.findByCandidateUuid(user.getUserUuid())).thenReturn(Optional.of(candidate));

        ResponseEntity<?> responseEntity = candidateUserRestController.deleteCandidateProfile(authentication);

        // then
        verify(userService).findByEmail(userEmail);
        verify(candidateService).findByCandidateUuid(user.getUserUuid());

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void deleteCandidateProfileCandidateNotFound() {
        // given
        User user = UserFixtures.someUserCandidate1();
        String userEmail = user.getEmail();

        // when
        when(authentication.getName()).thenReturn(userEmail);
        when(userService.findByEmail(userEmail)).thenReturn(Optional.of(user));
        when(candidateService.findByCandidateUuid(user.getUserUuid())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> candidateUserRestController.deleteCandidateProfile(authentication));

        // then
        verify(userService).findByEmail(userEmail);
        verify(candidateService).findByCandidateUuid(user.getUserUuid());
    }

}
