package pl.devfinder.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.devfinder.api.dto.CandidateDetailsDTO;
import pl.devfinder.api.dto.CandidateUpdateRequestDTO;
import pl.devfinder.api.dto.SkillDTO;
import pl.devfinder.api.dto.mapper.*;
import pl.devfinder.business.*;
import pl.devfinder.business.management.Keys;
import pl.devfinder.business.management.Utility;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.CandidateUpdateRequest;
import pl.devfinder.domain.User;
import pl.devfinder.domain.exception.NotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/candidate_portal")
public class CandidateUserController {
    public static final String CANDIDATE_PROFILE = "/profile";
    public static final String CANDIDATE_EDIT_PROFILE = "/edit_profile";
    public static final String CANDIDATE_UPDATE_PROFILE = "/update";
    public static final String CANDIDATE_DELETE_PROFILE = "/delete_profile";
    public static final String CANDIDATE_DELETE_CV_FILE = "/edit_profile/delete_cvFile";
    public static final String CANDIDATE_DELETE_PHOTO_FILE = "/edit_profile/delete_photoFile";


    private final UserService userService;
    private final SkillService skillService;
    private final SkillMapper skillMapper;
    private final CandidateService candidateService;
    private final CandidateDetailsMapper candidateDetailsMapper;
    private final CandidateUpdateRequestMapper candidateUpdateRequestMapper;

    @GetMapping(value = CANDIDATE_PROFILE)
    public String getCandidateProfile(Model model, Authentication authentication) {
        User user = Utility.putUserDataToModel(authentication, userService, model)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        Optional<Candidate> candidate = candidateService.findByCandidateUuid(user.getUserUuid());
        setCandidatePhotoToModel(model, candidate);

        if (candidate.isEmpty()) {
            return "redirect:edit_profile?new";
        }
        CandidateDetailsDTO candidateDetailsDTO = candidate.map(candidateDetailsMapper::map).orElseThrow();

        model.addAttribute("candidateDetailsDTO", candidateDetailsDTO);
        model.addAttribute("downloadCvFilePath", Utility.getUserPhotoPath(candidate.get().getCandidateUuid(),candidate.get().getCvFilename()));
        return "candidate/profile";
    }


    @GetMapping(value = CANDIDATE_EDIT_PROFILE)
    public String getCandidateEditProfile(Model model, Authentication authentication) {
        User user = Utility.putUserDataToModel(authentication, userService, model)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        Optional<Candidate> candidate = candidateService.findByCandidateUuid(user.getUserUuid());
        setCandidatePhotoToModel(model, candidate);

        CandidateDetailsDTO candidateDetailsDTO = new CandidateDetailsDTO().withStatus(Keys.CandidateState.ACTIVE.getName());
        if (candidate.isPresent()) {
            log.info("Edit profile existing candidate id: [{}]", candidate.get().getCandidateId());
            candidateDetailsDTO = candidateDetailsMapper.map(candidate.get());
            model.addAttribute("candidateDetailsDTO", candidateDetailsDTO);
        } else {
            log.info("Edit profile new candidate");
            model.addAttribute("candidateDetailsDTO", candidateDetailsDTO);
        }

        model.addAttribute("statusChecked", candidateDetailsDTO.getStatus());


        model.addAttribute("statusEnumActive", Keys.CandidateState.ACTIVE.getName());
        model.addAttribute("statusEnumInactive", Keys.CandidateState.INACTIVE.getName());
        model.addAttribute("statusEnumEmployed", Keys.CandidateState.EMPLOYED.getName());
        model.addAttribute("expLevelEnumJunior", Keys.Experience.JUNIOR.getName());
        model.addAttribute("expLevelEnumMid", Keys.Experience.MID.getName());
        model.addAttribute("expLevelEnumSenior", Keys.Experience.SENIOR.getName());

        model.addAttribute("cvFileLink", candidateDetailsDTO.getCvFilename());
        model.addAttribute("photoFileLink", candidateDetailsDTO.getPhotoFilename());
        if (candidateDetailsDTO.getCandidateSkills() != null) {

            model.addAttribute("skillChecked", candidateDetailsDTO.getCandidateSkills()
                    .stream().map(skill -> skill.getSkillId().getSkillName()).toList());
        }

        List<SkillDTO> allSkills = skillService.findAll().stream().map(skillMapper::map).toList();
        model.addAttribute("allSkillsDTOs", allSkills);

        return "candidate/edit_profile";
    }

    @DeleteMapping(value = CANDIDATE_DELETE_CV_FILE)
    public String deleteCvFile(Model model, Authentication authentication) {
        User user = Utility.putUserDataToModel(authentication, userService, model)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        Candidate candidate = candidateService.findByCandidateUuid(user.getUserUuid())
                .orElseThrow(() -> new NotFoundException("Could not find candidate by uuid"));

        candidateService.deleteCvFile(candidate);
        return "redirect:../edit_profile?delete_cv_file";
    }

    @DeleteMapping(value = CANDIDATE_DELETE_PHOTO_FILE)
    public String deletePhotoFile(Model model, Authentication authentication) {
        User user = Utility.putUserDataToModel(authentication, userService, model)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        Candidate candidate = candidateService.findByCandidateUuid(user.getUserUuid())
                .orElseThrow(() -> new NotFoundException("Could not find candidate by uuid"));

        candidateService.deletePhotoFile(candidate);
        return "redirect:../edit_profile?delete_photo_file";
    }

    @PostMapping(value = CANDIDATE_UPDATE_PROFILE)
    public String updateCandidateProfile(@Valid @ModelAttribute("candidateRequestDTO") CandidateUpdateRequestDTO candidateUpdateRequestDTO,
                                         Model model,
                                         Authentication authentication) {
        User user = Utility.putUserDataToModel(authentication, userService, model)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        Optional<Candidate> candidate = candidateService.findByCandidateUuid(user.getUserUuid());

        CandidateUpdateRequest candidateUpdateRequest = candidateUpdateRequestMapper.map(candidateUpdateRequestDTO);
        if (candidate.isEmpty()) {
            log.info("Process new candidate profile");
            candidateService.newCandidateProfile(candidateUpdateRequest, user);
            return "redirect:profile?created";
        } else {
            log.info("Process update candidate profile");
            candidateService.updateCandidateProfile(candidateUpdateRequest, candidate.get());
            return "redirect:profile?updated";
        }

    }

    @DeleteMapping(value = CANDIDATE_DELETE_PROFILE)
    public String deleteCandidateProfile(Model model, Authentication authentication) {
        User user = Utility.putUserDataToModel(authentication, userService, model)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        Candidate candidate = candidateService.findByCandidateUuid(user.getUserUuid())
                .orElseThrow(() -> new NotFoundException("Could not find candidate by uuid"));
        log.info("Processing delete profile for candidate nr :" + candidate.getCandidateId());
        candidateService.deleteCandidateProfile(candidate.getCandidateId());
        return "redirect:edit_profile?delete";
    }

    private void setCandidatePhotoToModel(Model model, Optional<Candidate> candidate) {
        if (candidate.isPresent() && Objects.nonNull(candidate.get().getPhotoFilename())) {
            String photoPath = Utility.getUserPhotoPath(candidate.get().getCandidateUuid(),candidate.get().getPhotoFilename());
            model.addAttribute("photoDir", photoPath);
        } else {
            model.addAttribute("photoDir", "/img/user.jpg");
        }
    }
}
