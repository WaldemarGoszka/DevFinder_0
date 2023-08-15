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
import pl.devfinder.domain.User;
import pl.devfinder.domain.exception.NotFoundException;
import pl.devfinder.domain.search.OfferSearchCriteria;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Slf4j
@Controller
@AllArgsConstructor
public class OfferDataController {
    public static final String OFFERS_LIST = "/offers";
    public static final String OFFER_DETAILS = "/offer/{offerId}";


    private final UserService userService;
    private final CityService cityService;
    private final CityMapper cityMapper;
    private final SkillService skillService;
    private final SkillMapper skillMapper;
    private final EmployerService employerService;
    private final EmployerRowMapper employerRowMapper;
    private final OfferService offerService;
    private final OfferDetailsMapper offerDetailsMapper;
    private final OfferRowMapper offerRowMapper;
    private final UserController userController;

    @GetMapping(value = OFFERS_LIST)
    public ModelAndView getOffersList(
            @ModelAttribute OfferSearchCriteria offerSearchCriteria,
            Model model,
            Authentication authentication) {
        Optional<User> user = Utility.putUserDataToModel(authentication, userService, model);
        userController.setUserPhotoToModel(model, user);

        Map<String, ?> offersListData = prepareOffersListData(offerSearchCriteria);

        return new ModelAndView("offers", offersListData);
    }
    @GetMapping(value = OFFER_DETAILS)
    public String getOfferDetails(@PathVariable Long offerId, Model model, Authentication authentication) {
        Optional<User> user = Utility.putUserDataToModel(authentication, userService, model);
        userController.setUserPhotoToModel(model, user);

        OfferDetailsDTO offerDetailsDTO = offerDetailsMapper.map(offerService.findById(offerId));
        model.addAttribute("offerDetailsDTO", offerDetailsDTO);
        return "offer_details";

    }

    private Map<String, ?> prepareOffersListData(OfferSearchCriteria offerSearchCriteria) {
        Map<String, Object> model = new HashMap<>();

        Page<OfferRowDTO> page = offerService.findAllByCriteria(offerSearchCriteria).map(offerRowMapper::map);
        List<OfferRowDTO> allOffers = page.getContent();
        model.put("allOffersDTOs", allOffers);

//Pagination Bar
        model.put("totalPages", page.getTotalPages());
        model.put("totalItems", page.getTotalElements());

//Check selected for filters
        model.put("experienceLevelChecked", offerSearchCriteria.getExperienceLevels());
        model.put("skillChecked", offerSearchCriteria.getSkills());
        model.put("cityChecked", offerSearchCriteria.getCity());
        model.put("remoteChecked", offerSearchCriteria.getRemoteWork());
        model.put("salaryChecked", offerSearchCriteria.getSalary());
        model.put("statusChecked", offerSearchCriteria.getStatus());
        model.put("employerChecked", offerSearchCriteria.getEmployer());

//Enums for filters
        model.put("remoteEnumFull", Keys.RemoteWork.FULL.getName());
        model.put("remoteEnumOffice", Keys.RemoteWork.OFFICE.getName());
        model.put("remoteEnumPartly", Keys.RemoteWork.PARTLY.getName());
        model.put("expLevelEnumJunior", Keys.Experience.JUNIOR.getName());
        model.put("expLevelEnumMid", Keys.Experience.MID.getName());
        model.put("expLevelEnumSenior", Keys.Experience.SENIOR.getName());
        model.put("salaryEnumWith", Keys.Salary.WITH.getName());
        model.put("salaryEnumUndisclosed", Keys.Salary.UNDISCLOSED.getName());
        model.put("statusEnumActive", Keys.OfferState.ACTIVE.getName());
        model.put("statusEnumExpired", Keys.OfferState.EXPIRED.getName());

//List for filters
        List<SkillDTO> allSkills = skillService.findAll().stream().map(skillMapper::map).toList();
        List<CityDTO> allCity = cityService.findAll().stream().map(cityMapper::map).toList();
        List<EmployerRowDTO> allEmployer = employerService.findAll().stream().map(employerRowMapper::map).toList();
        model.put("allSkillsDTOs", allSkills);
        model.put("allCityDTOs", allCity);
        model.put("allEmployerDTOs", allEmployer);

//Sorting
        model.put("sortBy", offerSearchCriteria.getSortBy());
        model.put("sortDirection", offerSearchCriteria.getSortDirection());
        model.put("reverseSortDirection", offerSearchCriteria.getSortDirection()
                .equals(Sort.Direction.ASC) ? Sort.Direction.DESC : Sort.Direction.ASC);

        return model;
    }
}
