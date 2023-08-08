package pl.devfinder.api.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.devfinder.api.dto.*;
import pl.devfinder.api.dto.mapper.*;
import pl.devfinder.business.*;
import pl.devfinder.business.management.Keys;
import pl.devfinder.business.management.Utility;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.User;
import pl.devfinder.domain.exception.NotFoundException;
import pl.devfinder.domain.search.EmployerSearchCriteria;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Slf4j
@Controller
@AllArgsConstructor
public class EmployerDataController {
    public static final String EMPLOYER_DETAILS = "/employer";
    public static final String EMPLOYERS_LIST = "/employers";

    private final UserService userService;
    private final CityService cityService;
    private final CityMapper cityMapper;
    private final SkillService skillService;
    private final SkillMapper skillMapper;
    private final EmployerService employerService;
    private final EmployerRowMapper employerRowMapper;
    private final EmployerDetailsMapper employerDetailsMapper;
    private final UserController userController;

//    @GetMapping()
//    public String homePage(Model model) {
//        return "employer/portal";
//    }
    @GetMapping(value = EMPLOYERS_LIST)
    public String getEmployersList(EmployerSearchCriteria employerSearchCriteria,
                                   Model model,
                                   Authentication authentication) {
        Optional<User> user = Utility.putUserDataToModel(authentication, userService, model);
        userController.setUserPhotoToModel(model, user);

        Page<EmployerRowDTO> page = employerService.findAllByCriteria(employerSearchCriteria).map(employerRowMapper::map);
        List<EmployerRowDTO> allEmployers = page.getContent();
        model.addAttribute("allEmployersDTOs", allEmployers);

        model.addAttribute("skillChecked", employerSearchCriteria.getSkillsInOffers());
        model.addAttribute("cityChecked", employerSearchCriteria.getCity());
        model.addAttribute("offersChecked", employerSearchCriteria.getJobOffersStatus());

        model.addAttribute("hasNoJobOffersEnum", Keys.EmployerFilterBy.hasNoJobOffers.getName());
        model.addAttribute("hasJobOffersEnum", Keys.EmployerFilterBy.hasJobOffers.getName());

//Pagination Bar
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

//Sorting
        model.addAttribute("sortBy", employerSearchCriteria.getSortBy());
        model.addAttribute("sortDirection", employerSearchCriteria.getSortDirection());
        model.addAttribute("reverseSortDirection", employerSearchCriteria.getSortDirection()
                .equals(Sort.Direction.ASC) ? Sort.Direction.DESC : Sort.Direction.ASC);

        List<SkillDTO> allSkills = skillService.findAll().stream().map(skillMapper::map).toList();
        List<CityDTO> allCity = cityService.findAll().stream().map(cityMapper::map).toList();
        model.addAttribute("allSkillsDTOs", allSkills);
        model.addAttribute("allCityDTOs", allCity);

        return "employers";
    }

    @GetMapping(value = EMPLOYER_DETAILS + "/{employerId}")
    public String getEmployerDetails(@PathVariable Long employerId, Model model, Authentication authentication) {
        Optional<User> user = Utility.putUserDataToModel(authentication, userService, model);
        userController.setUserPhotoToModel(model, user);

        EmployerDetailsDTO employerDetailsDTO = employerDetailsMapper.map(employerService.findById(employerId));
        if (Objects.nonNull(employerDetailsDTO.getLogoFilename())) {
            String photoPath = "/user_data/" + employerDetailsDTO.getEmployerUuid() + employerDetailsDTO.getLogoFilename();
            model.addAttribute("photoDir", photoPath);
        } else {
            model.addAttribute("photoDir", "/img/user.jpg");
        }
        model.addAttribute("employerDetailsDTO", employerDetailsDTO);
        return "employer_details";

    }

}
