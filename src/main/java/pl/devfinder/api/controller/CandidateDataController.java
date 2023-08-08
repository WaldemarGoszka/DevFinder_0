package pl.devfinder.api.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.devfinder.api.dto.*;
import pl.devfinder.api.dto.mapper.*;
import pl.devfinder.business.*;
import pl.devfinder.business.management.Keys;
import pl.devfinder.business.management.Utility;
import pl.devfinder.domain.Employer;
import pl.devfinder.domain.User;
import pl.devfinder.domain.search.CandidateSearchCriteria;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Controller
@AllArgsConstructor
//@RequestMapping("/candidate")
public class CandidateDataController {

    public static final String CANDIDATES_LIST = "/candidates";
    public static final String CANDIDATE_DETAIL = "/candidate";

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


    @GetMapping()
    public String homePage(Model model) {
        return "candidate/portal";
    }

    @GetMapping(value = CANDIDATE_DETAIL + "/{candidateId}")
    public String getCandidateDetails(@PathVariable Long candidateId, Model model, Authentication authentication) {
        Optional<User> user = Utility.putUserDataToModel(authentication, userService, model);
        userController.setUserPhotoToModel(model, user);

        CandidateDetailsDTO candidateDetailsDTO = candidateDetailsMapper.map(candidateService.findById(candidateId));
        if (Objects.nonNull(candidateDetailsDTO.getPhotoFilename())) {
            String photoPath = "/user_data/" + candidateDetailsDTO.getCandidateUuid() + candidateDetailsDTO.getPhotoFilename();
            model.addAttribute("photoDir", photoPath);
        } else {
            model.addAttribute("photoDir", "/img/user.jpg");
        }
        checkToShowFireCandidateButton(model, user, candidateDetailsDTO);

        model.addAttribute("downloadCvFilePath", "/user_data/" + candidateDetailsDTO.getCandidateUuid() + candidateDetailsDTO.getCvFilename());

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

    @GetMapping(value = CANDIDATES_LIST)
    public String getCandidatesList(
            CandidateSearchCriteria candidateSearchCriteria,
            Model model,
            Authentication authentication) {
        Optional<User> user = Utility.putUserDataToModel(authentication, userService, model);
        userController.setUserPhotoToModel(model, user);


        Page<CandidateRowDTO> page = candidateService.findAllByCriteria(candidateSearchCriteria).map(candidateRowMapper::map);
        List<CandidateRowDTO> allCandidates = page.getContent();

//Pagination Bar
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

//Check selected for filters
        model.addAttribute("experienceLevelChecked", candidateSearchCriteria.getExperienceLevels());
        model.addAttribute("skillChecked", candidateSearchCriteria.getSkills());
        model.addAttribute("cityChecked", candidateSearchCriteria.getCity());
        model.addAttribute("minYearsOfExperienceChecked", candidateSearchCriteria.getMinYearsOfExperience());
        model.addAttribute("openToRemoteJobChecked", candidateSearchCriteria.getOpenToRemoteJob());
        model.addAttribute("statusChecked", candidateSearchCriteria.getStatus());

//Enums for filters
        model.addAttribute("expLevelEnumJunior", Keys.Experience.JUNIOR.getName());
        model.addAttribute("expLevelEnumMid", Keys.Experience.MID.getName());
        model.addAttribute("expLevelEnumSenior", Keys.Experience.SENIOR.getName());
        model.addAttribute("statusEnumActive", Keys.CandidateState.ACTIVE.getName());
        model.addAttribute("statusEnumInactive", Keys.CandidateState.INACTIVE.getName());
        model.addAttribute("statusEnumEmployed", Keys.CandidateState.EMPLOYED.getName());
        model.addAttribute("remoteEnumYes", Keys.CandidateFilterBy.YES.getName());
        model.addAttribute("remoteEnumNo", Keys.CandidateFilterBy.NO.getName());

//List for filters
        List<SkillDTO> allSkills = skillService.findAll().stream().map(skillMapper::map).toList();
        List<CityDTO> allCity = cityService.findAll().stream().map(cityMapper::map).toList();
        List<EmployerRowDTO> allEmployer = employerService.findAll().stream().map(employerRowMapper::map).toList();
        model.addAttribute("allSkillsDTOs", allSkills);
        model.addAttribute("allCityDTOs", allCity);
        model.addAttribute("allEmployerDTOs", allEmployer);
//Sorting
        model.addAttribute("sortBy", candidateSearchCriteria.getSortBy());
        model.addAttribute("sortDirection", candidateSearchCriteria.getSortDirection());
        model.addAttribute("reverseSortDirection", candidateSearchCriteria.getSortDirection()
                .equals(Sort.Direction.ASC) ? Sort.Direction.DESC : Sort.Direction.ASC);

        model.addAttribute("allCandidatesDTOs", allCandidates);
        return "candidates";
    }


}



