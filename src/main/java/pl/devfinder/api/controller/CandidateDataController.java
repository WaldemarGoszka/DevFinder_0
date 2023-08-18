package pl.devfinder.api.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import pl.devfinder.api.dto.*;
import pl.devfinder.api.dto.mapper.*;
import pl.devfinder.business.*;
import pl.devfinder.business.management.Keys;
import pl.devfinder.business.management.Utility;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.Employer;
import pl.devfinder.domain.User;
import pl.devfinder.domain.search.CandidateSearchCriteria;

import java.util.*;

@Slf4j
@Controller
@AllArgsConstructor
public class CandidateDataController {

    public static final String CANDIDATES_LIST = "/candidates";
    public static final String CANDIDATE_DETAIL = "/candidate/{candidateId}";

    private final EmployerService employerService;
    private final EmployerRowMapper employerRowMapper;
    private final UserService userService;
    private final CityService cityService;
    private final CityMapper cityMapper;
    private final SkillService skillService;
    private final SkillMapper skillMapper;
    private final CandidateService candidateService;
    private final CandidateDetailsMapper candidateDetailsMapper;
    private final UserController userController;
    private final CandidateRowMapper candidateRowMapper;
    private final FileUploadService fileUploadService;


    @GetMapping(value = CANDIDATES_LIST)
    public ModelAndView getCandidatesList(@ModelAttribute
                                    CandidateSearchCriteria candidateSearchCriteria,
                                          Model model,
                                          Authentication authentication) {
        Optional<User> user = userController.putUserDataToModel(authentication, userService, model);
        userController.setUserPhotoToModel(model, user);

        Map<String, ?> candidateListData = prepareCandidateListData(candidateSearchCriteria);

        return new ModelAndView("candidates",candidateListData);
    }

    @GetMapping(value = CANDIDATE_DETAIL)
    public String getCandidateDetails(
            @PathVariable Long candidateId,
            Model model,
            Authentication authentication) {
        Optional<User> user = userController.putUserDataToModel(authentication, userService, model);
        userController.setUserPhotoToModel(model, user);

        Candidate candidate = candidateService.findById(candidateId);
        CandidateDetailsDTO candidateDetailsDTO = candidateDetailsMapper.map(candidate);
        String photoFilename = candidateDetailsDTO.getPhotoFilename();
        String candidateUuid = candidateDetailsDTO.getCandidateUuid();
        if (Objects.nonNull(photoFilename)) {
            String photoPath = fileUploadService.getUserPhotoPath(candidateUuid, photoFilename);
            model.addAttribute("photoProfileDir", photoPath);
        } else {
            model.addAttribute("photoProfileDir", UserController.DEFAULT_PHOTO_PATH);
        }
        checkToShowFireCandidateButton(model, user, candidateDetailsDTO);

        String userPhotoPath = fileUploadService.getUserPhotoPath(candidateUuid, candidateDetailsDTO.getCvFilename());
        model.addAttribute("downloadCvFilePath", userPhotoPath);

        model.addAttribute("candidateDetailsDTO", candidateDetailsDTO);
        return "candidate_details";

    }

    private void checkToShowFireCandidateButton(Model model,
                                                Optional<User> user,
                                                CandidateDetailsDTO candidateDetailsDTO) {
        if (user.isPresent() && Objects.nonNull(candidateDetailsDTO.getEmployerId())) {
            if (user.get().getRole().getRole().equals(Keys.Role.EMPLOYER.getName())) {
                Optional<Employer> employer = employerService.findByEmployerUuid(user.get().getUserUuid());
                if (employer.isPresent() &&
                        candidateDetailsDTO.getEmployerId().getCompanyName().equals(employer.get().getCompanyName())) {
                    model.addAttribute("isShowFireEmployeeButton", true);
                } else {
                    model.addAttribute("isShowFireEmployeeButton", false);
                }
            }
        }
    }

    private Map<String, ?> prepareCandidateListData(CandidateSearchCriteria candidateSearchCriteria) {
        Map<String, Object> model = new HashMap<>();
        Page<Candidate> allByCriteria = candidateService.findAllByCriteria(candidateSearchCriteria);
        Page<CandidateRowDTO> page = allByCriteria.map(candidateRowMapper::map);
        List<CandidateRowDTO> allCandidates = page.getContent();


        model.put("totalPages", page.getTotalPages());
        model.put("totalItems", page.getTotalElements());


        model.put("experienceLevelChecked", candidateSearchCriteria.getExperienceLevels());
        model.put("skillChecked", candidateSearchCriteria.getSkills());
        model.put("cityChecked", candidateSearchCriteria.getCity());
        model.put("minYearsOfExperienceChecked", candidateSearchCriteria.getMinYearsOfExperience());
        model.put("openToRemoteJobChecked", candidateSearchCriteria.getOpenToRemoteJob());
        model.put("statusChecked", candidateSearchCriteria.getStatus());


        model.put("expLevelEnumJunior", Keys.Experience.JUNIOR.getName());
        model.put("expLevelEnumMid", Keys.Experience.MID.getName());
        model.put("expLevelEnumSenior", Keys.Experience.SENIOR.getName());
        model.put("statusEnumActive", Keys.CandidateState.ACTIVE.getName());
        model.put("statusEnumInactive", Keys.CandidateState.INACTIVE.getName());
        model.put("statusEnumEmployed", Keys.CandidateState.EMPLOYED.getName());
        model.put("remoteEnumYes", Keys.CandidateFilterBy.YES.getName());
        model.put("remoteEnumNo", Keys.CandidateFilterBy.NO.getName());


        List<SkillDTO> allSkills = skillService.findAll().stream().map(skillMapper::map).toList();
        List<CityDTO> allCity = cityService.findAll().stream().map(cityMapper::map).toList();
        List<EmployerRowDTO> allEmployer = employerService.findAll().stream().map(employerRowMapper::map).toList();
        model.put("allSkillsDTOs", allSkills);
        model.put("allCityDTOs", allCity);
        model.put("allEmployerDTOs", allEmployer);

        model.put("sortBy", candidateSearchCriteria.getSortBy());
        model.put("sortDirection", candidateSearchCriteria.getSortDirection());
        model.put("reverseSortDirection", candidateSearchCriteria.getSortDirection()
                .equals(Sort.Direction.ASC) ? Sort.Direction.DESC : Sort.Direction.ASC);

        model.put("allCandidatesDTOs", allCandidates);

        return model;
    }


}



