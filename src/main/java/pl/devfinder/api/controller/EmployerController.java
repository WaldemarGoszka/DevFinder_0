package pl.devfinder.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.devfinder.api.dto.*;
import pl.devfinder.api.dto.mapper.*;
import pl.devfinder.business.*;
import pl.devfinder.business.management.Keys;
import pl.devfinder.business.management.Utility;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.Employer;
import pl.devfinder.domain.User;
import pl.devfinder.domain.exception.NotFoundException;
import pl.devfinder.domain.search.CandidateSearchCriteria;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/employer")
public class EmployerController {

    public static final String EMPLOYER_PROFILE = "/profile";
    public static final String EMPLOYER_EDIT_PROFILE = "/edit_profile";
    public static final String EMPLOYER_UPDATE_PROFILE = "/update";
    public static final String EMPLOYER_NEW_PROFILE = "/new";
    public static final String EMPLOYER_DELETE_PROFILE = "/delete";
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
    private final EmployerDetailsMapper employerDetailsMapper;

    @GetMapping()
    public String homePage(Model model) {

        return "employer/portal";
    }

//    @GetMapping(value = EMPLOYER_PROFILE)
//    public String getProfile(Model model) {
//// getEmpoyerProfile
//        return "employer/profile";
//    }

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

    @GetMapping(value = EMPLOYER_PROFILE)
    public String getEmployerProfile(Model model, Authentication authentication) {
        Optional<User> user = Utility.putUserDataToModel(authentication, userService, model);
        String userUuid = user.orElseThrow(() -> new NotFoundException("Could not find user by UUID")).getUserUuid();
        Optional<Employer> employer = employerService.findByEmployerUuid(userUuid);
        if (employer.isEmpty()) {
            return "redirect:employer/profile?not_exist";
        }
        EmployerDetailsDTO employerDetailsDTO = employer.map(employerDetailsMapper::map).orElseThrow();

        model.addAttribute("employerDetailsDTO", employerDetailsDTO);
        return "employer/profile";
    }
    @GetMapping(value = EMPLOYER_EDIT_PROFILE)
    public String getEmployerEditProfile(Model model, Authentication authentication) {
        User user = Utility.putUserDataToModel(authentication, userService, model)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
//        if (user.getRole().getRole().equals(Keys.Role.EMPLOYER.getName())) {
//            employerService.save(user);
//        }
        return "employer/edit_profile";
    }
    @PutMapping(value = EMPLOYER_UPDATE_PROFILE)
    public String updateEmployerProfile(@ModelAttribute EmployerDetailsDTO candidateDetailsDTO,
                                         BindingResult bindingResult,
                                         Model model,
                                         Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "error";
        }

        return "employer/profile?update";
    }

    @PostMapping(value = EMPLOYER_NEW_PROFILE)
    public String newEmployerProfile(@ModelAttribute EmployerDetailsDTO candidateDetailsDTO,
                                      BindingResult bindingResult,
                                      Model model,
                                      Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "error";
        }

        return "employer/profile?new";
    }

    @DeleteMapping(value = EMPLOYER_DELETE_PROFILE)
    public String deleteEmployerProfile() {

        return "employer/profile?delete";
    }
}
