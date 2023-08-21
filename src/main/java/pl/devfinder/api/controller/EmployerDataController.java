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
import pl.devfinder.api.dto.CityDTO;
import pl.devfinder.api.dto.EmployerDetailsDTO;
import pl.devfinder.api.dto.EmployerRowDTO;
import pl.devfinder.api.dto.SkillDTO;
import pl.devfinder.api.dto.mapper.CityMapper;
import pl.devfinder.api.dto.mapper.EmployerDetailsMapper;
import pl.devfinder.api.dto.mapper.EmployerRowMapper;
import pl.devfinder.api.dto.mapper.SkillMapper;
import pl.devfinder.business.*;
import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.Employer;
import pl.devfinder.domain.User;
import pl.devfinder.domain.search.EmployerSearchCriteria;

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
    private final FileService fileService;


    @GetMapping(value = EMPLOYERS_LIST)
    public ModelAndView getEmployersList(@ModelAttribute EmployerSearchCriteria employerSearchCriteria,
                                         Model model,
                                         Authentication authentication) {
        Optional<User> user = userController.putUserDataToModel(authentication, userService, model);
        userController.setUserPhotoToModel(model, user);

        Map<String, ?> employerListData = prepareEmployerListData(employerSearchCriteria);

        return new ModelAndView("employers", employerListData);
    }

    @GetMapping(value = EMPLOYER_DETAILS)
    public String getEmployerDetails(@PathVariable Long employerId, Model model, Authentication authentication) {
        Optional<User> user = userController.putUserDataToModel(authentication, userService, model);
        userController.setUserPhotoToModel(model, user);

        Employer employer = employerService.findById(employerId);
        EmployerDetailsDTO employerDetailsDTO = employerDetailsMapper.map(employer);
        String logoFilename = employerDetailsDTO.getLogoFilename();
        if (Objects.nonNull(logoFilename)) {
            String employerUuid = employerDetailsDTO.getEmployerUuid();
            String photoPath = fileService.getUserPhotoPath(employerUuid, logoFilename);
            model.addAttribute("photoProfileDir", photoPath);
        } else {
            model.addAttribute("photoProfileDir", UserController.DEFAULT_PHOTO_PATH);
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
