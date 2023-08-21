package pl.devfinder.api.controller.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.devfinder.api.controller.exception.ProfileAlreadyExistException;
import pl.devfinder.api.controller.exception.ProfileNotExistException;
import pl.devfinder.api.dto.CandidateDetailsDTO;
import pl.devfinder.api.dto.CandidateUpdateRequestDTO;
import pl.devfinder.api.dto.mapper.CandidateDetailsMapper;
import pl.devfinder.api.dto.mapper.CandidateUpdateRequestMapper;
import pl.devfinder.business.CandidateService;
import pl.devfinder.business.UserService;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.CandidateUpdateRequest;
import pl.devfinder.domain.User;
import pl.devfinder.domain.exception.NotFoundException;

import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(CandidateUserRestController.BASE_PATH)
public class CandidateUserRestController {
    public static final String BASE_PATH = "/api/candidate_portal";
    public static final String CANDIDATE_PROFILE = "/profile";
    public static final String CANDIDATE_UPDATE_PROFILE = "/update_profile";
    public static final String CANDIDATE_NEW_PROFILE = "/new_profile";
    public static final String CANDIDATE_DELETE_PROFILE = "/delete_profile";
    public static final String CANDIDATE_DELETE_CV_FILE = "/edit_profile/delete_cvFile";
    public static final String CANDIDATE_DELETE_PHOTO_FILE = "/edit_profile/delete_photoFile";


    private final UserService userService;
    private final CandidateService candidateService;
    private final CandidateDetailsMapper candidateDetailsMapper;
    private final CandidateUpdateRequestMapper candidateUpdateRequestMapper;

    @GetMapping(value = CANDIDATE_PROFILE)
    public ResponseEntity<CandidateDetailsDTO> getCandidateProfile(Authentication authentication) {
        User user = userService.findByEmail(authentication.getName())
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        Candidate candidate = candidateService.findByCandidateUuid(user.getUserUuid())
                .orElseThrow(() -> new ProfileNotExistException("Profile not exist"));
        CandidateDetailsDTO candidateDetailsDTO = candidateDetailsMapper.map(candidate);
        return ResponseEntity.ok(candidateDetailsDTO);
    }


    @PostMapping(value = CANDIDATE_NEW_PROFILE)
    public ResponseEntity<CandidateDetailsDTO> createCandidateProfile(
            @Valid @RequestBody CandidateUpdateRequestDTO candidateUpdateRequestDTO,
            Authentication authentication) {
        log.info("Process create candidate profile");
        User user = userService.findByEmail(authentication.getName())
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        Optional<Candidate> candidate = candidateService.findByCandidateUuid(user.getUserUuid());
        if (candidate.isPresent()) {
            throw new ProfileAlreadyExistException("Profile candidate already exist");
        } else {
            CandidateUpdateRequest candidateUpdateRequest = candidateUpdateRequestMapper.map(candidateUpdateRequestDTO);
            Candidate candidateSaved = candidateService.newCandidateProfile(candidateUpdateRequest, user);
            CandidateDetailsDTO candidateDetailsDTO = candidateDetailsMapper.map(candidateSaved);
            return ResponseEntity.status(HttpStatus.CREATED).body(candidateDetailsDTO);
        }
    }

    @PutMapping(value = CANDIDATE_UPDATE_PROFILE)
    public ResponseEntity<CandidateDetailsDTO> updateCandidateProfile(
            @Valid @RequestBody CandidateUpdateRequestDTO candidateUpdateRequestDTO,
            Authentication authentication) {
        log.info("Process update candidate profile");
        User user = userService.findByEmail(authentication.getName())
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        Candidate candidate = candidateService.findByCandidateUuid(user.getUserUuid())
                .orElseThrow(() -> new NotFoundException("Could not find candidate by uuid"));
        CandidateUpdateRequest candidateUpdateRequest = candidateUpdateRequestMapper.map(candidateUpdateRequestDTO);
        Candidate candidateSaved = candidateService.updateCandidateProfile(candidateUpdateRequest, candidate);
        CandidateDetailsDTO candidateDetailsDTO = candidateDetailsMapper.map(candidateSaved);
        return ResponseEntity.status(HttpStatus.OK).body(candidateDetailsDTO);
    }

    @DeleteMapping(value = CANDIDATE_DELETE_PROFILE)
    public ResponseEntity<?> deleteCandidateProfile(Authentication authentication) {
        User user = userService.findByEmail(authentication.getName())
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        Candidate candidate = candidateService.findByCandidateUuid(user.getUserUuid())
                .orElseThrow(() -> new NotFoundException("Profile not exist"));
        log.info("Processing delete profile for candidate nr :" + candidate.getCandidateId());
        return ResponseEntity.ok().build();
    }

}
