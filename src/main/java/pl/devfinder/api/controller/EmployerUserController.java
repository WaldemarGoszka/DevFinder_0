package pl.devfinder.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.devfinder.api.dto.*;
import pl.devfinder.api.dto.mapper.*;
import pl.devfinder.business.*;
import pl.devfinder.business.management.Keys;
import pl.devfinder.business.management.Utility;
import pl.devfinder.domain.Employer;
import pl.devfinder.domain.EmployerUpdateRequest;
import pl.devfinder.domain.User;
import pl.devfinder.domain.exception.NotFoundException;
import pl.devfinder.domain.search.CandidateSearchCriteria;

import java.util.*;

import static pl.devfinder.api.controller.EmployerUserController.EMPLOYER_BASE_PATH;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping(EMPLOYER_BASE_PATH)
public class EmployerUserController {

    public static final String EMPLOYER_BASE_PATH = "/employer_portal";
    public static final String EMPLOYER_PROFILE = "/profile";
    public static final String EMPLOYER_EDIT_PROFILE = "/edit_profile";
    public static final String EMPLOYER_UPDATE_PROFILE = "/update";
    public static final String EMPLOYER_DELETE_PROFILE = "/delete_profile";
    public static final String EMPLOYER_DELETE_LOGO_FILE = "/edit_profile/delete_logoFile";
    public static final String EMPLOYER_EMPLOYEES = "/employee";
    public static final String EMPLOYER_HIRE_CANDIDATE = "/hire/{candidateId}";
    public static final String EMPLOYER_FIRE_CANDIDATE = "/fire/{candidateId}";

    private final UserService userService;
    private final EmployerService employerService;
    private final EmployerDetailsMapper employerDetailsMapper;
    private final EmployerUpdateRequestMapper employerUpdateRequestMapper;
    private final CandidateService candidateService;
    private final CandidateRowMapper candidateRowMapper;
    private final SkillService skillService;
    private final CityService cityService;
    private final CityMapper cityMapper;
    private final SkillMapper skillMapper;

    @GetMapping(value = EMPLOYER_EMPLOYEES)
    public ModelAndView getEmployeesList(
            CandidateSearchCriteria candidateSearchCriteria,
            Model model,
            Authentication authentication) {
        User user = Utility.putUserDataToModel(authentication, userService, model)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        Optional<Employer> employerOptional = employerService.findByEmployerUuid(user.getUserUuid());
        if (employerOptional.isEmpty()) {
            return new ModelAndView("redirect:../employer_portal/edit_profile?first_create_profile");
        }
        Employer employer = employerOptional.orElseThrow(() -> new NotFoundException("Could not find employer by uuid"));
        setEmployerLogoToModel(model, employerOptional);

        setFilterToGetEmployedCandidateInThatCompany(candidateSearchCriteria, employer);

        Map<String, ?> employeeListData = prepareEmployeeListData(candidateSearchCriteria);

        return new ModelAndView("employer/employees", employeeListData);
    }

    private static void setFilterToGetEmployedCandidateInThatCompany(CandidateSearchCriteria candidateSearchCriteria, Employer employer) {
        candidateSearchCriteria.setEmployer(employer.getCompanyName());
    }

    @PutMapping(value = EMPLOYER_HIRE_CANDIDATE)
    public String hireCandidate(@PathVariable String candidateId,
                                Model model,
                                Authentication authentication) {
        User user = Utility.putUserDataToModel(authentication, userService, model)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        Employer employer = employerService.findByEmployerUuid(user.getUserUuid())
                .orElseThrow(() -> new NotFoundException("Could not find employer by uuid"));

        employerService.hireCandidate(employer, Long.valueOf(candidateId));
        return "redirect:../employee?hire";
    }

    @PutMapping(value = EMPLOYER_FIRE_CANDIDATE)
    public String fireCandidate(@PathVariable String candidateId,
                                Model model,
                                Authentication authentication) {
        User user = Utility.putUserDataToModel(authentication, userService, model)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        Employer employer = employerService.findByEmployerUuid(user.getUserUuid())
                .orElseThrow(() -> new NotFoundException("Could not find employer by uuid"));
        employerService.fireCandidate(Long.valueOf(candidateId));
        return "redirect:../employee?fire";
    }

    @GetMapping(value = EMPLOYER_PROFILE)
    public String getEmployerProfile(Model model, Authentication authentication) {
//        User user = Utility.putUserDataToModel(authentication, userService, model)
//                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));

        if (authentication != null) {
            User user = userService.findByEmail(authentication.getName())
                    .orElseThrow(() -> new NotFoundException("Could not find user by email"+ authentication.getName()));
            model.addAttribute("user", user);
            Optional<Employer> employer = employerService.findByEmployerUuid(user.getUserUuid());
            setEmployerLogoToModel(model, employer);
            if (employer.isEmpty()) {
                return "redirect:edit_profile?new";
            }
            EmployerDetailsDTO employerDetailsDTO = employer.map(employerDetailsMapper::map).orElseThrow();
            model.addAttribute("employerDetailsDTO", employerDetailsDTO);
            return "employer/profile";
        } else {
            throw new NotFoundException("Authentication failed");
        }
    }

    @GetMapping(value = EMPLOYER_EDIT_PROFILE)
    public String getEmployerEditProfile(Model model, Authentication authentication) {
        User user = Utility.putUserDataToModel(authentication, userService, model)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        Optional<Employer> employer = employerService.findByEmployerUuid(user.getUserUuid());
        setEmployerLogoToModel(model, employer);

        EmployerDetailsDTO employerDetailsDTO = new EmployerDetailsDTO();
        if (employer.isPresent()) {
            log.info("Edit profile existing employer id: [{}]", employer.get().getEmployerId());
            employerDetailsDTO = employer.map(employerDetailsMapper::map).get();
            model.addAttribute("employerDetailsDTO", employerDetailsDTO);

        } else {
            log.info("Edit profile new employer");
            model.addAttribute("employerDetailsDTO", employerDetailsDTO);
        }

        model.addAttribute("logoFileLink", employerDetailsDTO.getLogoFilename());

        return "employer/edit_profile";
    }

    @PostMapping(value = EMPLOYER_UPDATE_PROFILE)
    public String updateEmployerProfile(@Valid @ModelAttribute EmployerUpdateRequestDTO employerUpdateRequestDTO,
                                        Model model,
                                        Authentication authentication) {
        User user = Utility.putUserDataToModel(authentication, userService, model)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        Optional<Employer> employer = employerService.findByEmployerUuid(user.getUserUuid());

        EmployerUpdateRequest candidateUpdateRequest = employerUpdateRequestMapper.map(employerUpdateRequestDTO);
        if (employer.isEmpty()) {
            log.info("Process new employer profile");
            employerService.newEmployerProfile(candidateUpdateRequest, user);
            return "redirect:profile?created";
        } else {
            log.info("Process update employer profile");
            employerService.updateEmployerProfile(candidateUpdateRequest, employer.get());
            return "redirect:profile?updated";
        }
    }

    @DeleteMapping(value = EMPLOYER_DELETE_PROFILE)
    public String deleteEmployerProfile(Model model, Authentication authentication) {
        User user = Utility.putUserDataToModel(authentication, userService, model)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        Employer employer = employerService.findByEmployerUuid(user.getUserUuid())
                .orElseThrow(() -> new NotFoundException("Could not find employer by uuid"));

        log.info("Processing delete profile for employer nr :" + employer.getEmployerId());
        employerService.deleteEmployerProfile(employer.getEmployerId());
        return "redirect:edit_profile?delete";
    }

    @DeleteMapping(value = EMPLOYER_DELETE_LOGO_FILE)
    public String deleteLogoFile(Model model, Authentication authentication) {
        User user = Utility.putUserDataToModel(authentication, userService, model)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        Employer employer = employerService.findByEmployerUuid(user.getUserUuid())
                .orElseThrow(() -> new NotFoundException("Could not find employer by uuid"));

        employerService.deleteLogoFile(employer);
        return "redirect:../edit_profile?delete_logo_file";
    }


    private void setEmployerLogoToModel(Model model, Optional<Employer> employer) {
        if (employer.isPresent() && Objects.nonNull(employer.get().getLogoFilename())) {
            String logoPath = Utility.getUserPhotoPath(employer.get().getEmployerUuid(), employer.get().getLogoFilename());
            model.addAttribute("photoDir", logoPath);
        } else {
            model.addAttribute("photoDir", "/img/user.jpg");
        }
    }


    private Map<String, ?> prepareEmployeeListData(CandidateSearchCriteria candidateSearchCriteria) {
        Map<String, Object> model = new HashMap<>();
        Page<CandidateRowDTO> page = candidateService.findAllByCriteria(candidateSearchCriteria).map(candidateRowMapper::map);
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
        model.put("allSkillsDTOs", allSkills);
        model.put("allCityDTOs", allCity);

        model.put("sortBy", candidateSearchCriteria.getSortBy());
        model.put("sortDirection", candidateSearchCriteria.getSortDirection());
        model.put("reverseSortDirection", candidateSearchCriteria.getSortDirection()
                .equals(Sort.Direction.ASC) ? Sort.Direction.DESC : Sort.Direction.ASC);

        model.put("allCandidatesDTOs", allCandidates);

        return model;
    }
}
