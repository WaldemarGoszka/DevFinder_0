package pl.devfinder.api.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.devfinder.api.dto.*;
import pl.devfinder.api.dto.mapper.*;
import pl.devfinder.business.*;
import pl.devfinder.business.management.Keys;
import pl.devfinder.business.management.Utility;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.User;
import pl.devfinder.domain.exception.NotFoundException;
import pl.devfinder.domain.search.EmployerSearchCriteria;
import pl.devfinder.domain.search.OfferSearchCriteria;

import java.util.*;

@Slf4j
@Controller
@AllArgsConstructor
public class EmployerDataController {
    public static final String EMPLOYER_DETAILS = "/employer/{employerId}";
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


    @GetMapping(value = EMPLOYERS_LIST)
    public ModelAndView getEmployersList(@ModelAttribute EmployerSearchCriteria employerSearchCriteria,
                                         Model model,
                                         Authentication authentication) {
        Optional<User> user = Utility.putUserDataToModel(authentication, userService, model);
        userController.setUserPhotoToModel(model, user);

        Map<String, ?> employerListData = prepareEmployerListData(employerSearchCriteria);

        return new ModelAndView("employers",employerListData);
    }

    @GetMapping(value = EMPLOYER_DETAILS)
    public String getEmployerDetails(@PathVariable Long employerId, Model model, Authentication authentication) {
        Optional<User> user = Utility.putUserDataToModel(authentication, userService, model);
        userController.setUserPhotoToModel(model, user);

        EmployerDetailsDTO employerDetailsDTO = employerDetailsMapper.map(employerService.findById(employerId));
        if (Objects.nonNull(employerDetailsDTO.getLogoFilename())) {
            String photoPath = Utility.getUserPhotoPath(employerDetailsDTO.getEmployerUuid(),employerDetailsDTO.getLogoFilename());
            model.addAttribute("photoProfileDir", photoPath);
        } else {
            model.addAttribute("photoProfileDir", "/img/user.jpg");
        }
        model.addAttribute("employerDetailsDTO", employerDetailsDTO);
        return "employer_details";

    }

    private Map<String, ?> prepareEmployerListData(EmployerSearchCriteria employerSearchCriteria) {
        Map<String, Object> model = new HashMap<>();

        Page<EmployerRowDTO> page = employerService.findAllByCriteria(employerSearchCriteria).map(employerRowMapper::map);
        List<EmployerRowDTO> allEmployers = page.getContent();
        model.put("allEmployersDTOs", allEmployers);

        model.put("skillChecked", employerSearchCriteria.getSkillsInOffers());
        model.put("cityChecked", employerSearchCriteria.getCity());
        model.put("offersChecked", employerSearchCriteria.getJobOffersStatus());

        model.put("hasNoJobOffersEnum", Keys.EmployerFilterBy.hasNoJobOffers.getName());
        model.put("hasJobOffersEnum", Keys.EmployerFilterBy.hasJobOffers.getName());

        model.put("totalPages", page.getTotalPages());
        model.put("totalItems", page.getTotalElements());

        model.put("sortBy", employerSearchCriteria.getSortBy());
        model.put("sortDirection", employerSearchCriteria.getSortDirection());
        model.put("reverseSortDirection", employerSearchCriteria.getSortDirection()
                .equals(Sort.Direction.ASC) ? Sort.Direction.DESC : Sort.Direction.ASC);

        List<SkillDTO> allSkills = skillService.findAll().stream().map(skillMapper::map).toList();
        List<CityDTO> allCity = cityService.findAll().stream().map(cityMapper::map).toList();
        model.put("allSkillsDTOs", allSkills);
        model.put("allCityDTOs", allCity);

        return model;
    }

}
