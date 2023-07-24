package pl.devfinder.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.devfinder.api.dto.*;
import pl.devfinder.api.dto.mapper.*;
import pl.devfinder.business.*;
import pl.devfinder.business.management.Keys;
import pl.devfinder.business.management.Utility;
import pl.devfinder.domain.search.CandidateSearchCriteria;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/employer")
public class EmployerController {

    public static final String EMPLOYER_PROFILE = "/profile";
    public static final String EMPLOYER_EDIT_PROFILE = "/edit_profile";
    public static final String MATCHED_CANDIDATE = "/matched_candidate";
    public static final String CANDIDATES_LIST = "/candidates";
    public static final String CANDIDATE_DETAIL = "/candidate";
    public static final String POST_A_JOB = "/post_job";

    public static final String MY_JOB_OFFERS = "/posted_offers";
    public static final String EMPLOYER_SETTINGS = "/settings";
    public static final String EMPLOYEE = "/employee";

    private final CandidateService candidateService;
    private final CandidateRowMapper candidateRowMapper;
    private final CandidateDetailsMapper candidateDetailsMapper;
    private final UserService userService;
    private final CityService cityService;
    private final CityMapper cityMapper;
    private final SkillService skillService;
    private final SkillMapper skillMapper;
    private final EmployerService employerService;
    private final EmployerRowMapper employerRowMapper;

    @GetMapping()
    public String homePage(Model model) {

        return "employer/portal";
    }

    @GetMapping(value = EMPLOYER_PROFILE)
    public String getProfile(Model model) {
// getEmpoyerProfile
        return "employer/profile";
    }

    @GetMapping(value = CANDIDATE_DETAIL+"/{candidateId}")
    public String getCandidateDetails(@PathVariable Long candidateId, Model model, Authentication authentication) {
        Utility.putUserDataToModel(authentication, userService, model);
        CandidateDetailsDTO candidateDetailsDTO = candidateDetailsMapper.map(candidateService.findById(candidateId));
        model.addAttribute("candidateDetailsDTO", candidateDetailsDTO);
        return "employer/candidate_details";

    }

    @GetMapping(value = CANDIDATES_LIST)
    public String getCandidatesList(
            CandidateSearchCriteria candidateSearchCriteria,
            Model model,
            Authentication authentication) {
        Utility.putUserDataToModel(authentication, userService, model);

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
        return "employer/candidates";
    }


}
