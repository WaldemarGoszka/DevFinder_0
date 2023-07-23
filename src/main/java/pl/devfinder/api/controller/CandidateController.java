package pl.devfinder.api.controller;

import lombok.AllArgsConstructor;
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
import pl.devfinder.domain.search.EmployerSearchCriteria;
import pl.devfinder.domain.search.OfferSearchCriteria;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/candidate")
public class CandidateController {

    public static final String CANDIDATE_PROFILE = "/profile";
    public static final String CANDIDATE_EDIT_PROFILE = "/edit_profile";

    public static final String OFFERS_LIST = "/offers";
    public static final String OFFER_DETAILS = "/offer";

    public static final String EMPLOYER_DETAILS = "/employer";
    public static final String EMPLOYERS_LIST = "/employers";


    public static final String MY_EMPLOYER = "/my_employer";
    public static final String CANDIDATE_SETTINGS = "/settings";
    public static final String CANDIDATE_MATCHED_OFFERS = "/matched_offers";
    public static final String CANDIDATE_APPLICATIONS = "/applications";

    private final EmployerService employerService;
    private final EmployerRowMapper employerRowMapper;
    private final OfferService offerService;
    private final OfferRowMapper offerRowMapper;
    private final OfferDetailsMapper offerDetailsMapper;
    private final UserService userService;
    private final CityService cityService;
    private final CityMapper cityMapper;
    private final SkillService skillService;
    private final SkillMapper skillMapper;
    private final EmployerDetailsMapper employerDetailsMapper;


    @GetMapping()
    public String homePage(Model model) {
        // to uzyć do sprawdzania autentykatora na każdej stronie z ecommerce project
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
//            return "redirect:/login";
//        }
//        return "index";

//        albo
//
//        if (principal == null) {
//            return "redirect:/login";

        return "candidate/portal";
    }

    @GetMapping(value = CANDIDATE_PROFILE)
    public String getProfile(Model model, Authentication authentication) {
        Utility.putUserDataToModel(authentication, userService, model);
//
        return "candidate/profile";
    }

    @GetMapping(value = EMPLOYERS_LIST)
    public String getEmployersList(EmployerSearchCriteria employerSearchCriteria,
                                   Model model,
                                   Authentication authentication) {
        Utility.putUserDataToModel(authentication, userService, model);

        Page<EmployerRowDTO> page = employerService.findAllByCriteria(employerSearchCriteria).map(employerRowMapper::map);
        List<EmployerRowDTO> allEmployers = page.getContent();
        model.addAttribute("allEmployersDTOs", allEmployers);

        model.addAttribute("skillChecked", employerSearchCriteria.getSkillsInOffers());
        model.addAttribute("cityChecked", employerSearchCriteria.getCity());
        model.addAttribute("offersChecked", employerSearchCriteria.getJobOffersStatus());

        model.addAttribute("hasNoJobOffersEnum", Keys.EmployerFilterBy.hasNoJobOffers.getName());
        model.addAttribute("hasJobOffersEnum", Keys.EmployerFilterBy.hasJobOffers.getName());

//ToolBar
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

        return "candidate/employers";
    }


    @GetMapping(value = OFFERS_LIST)
    public String getOffersList(
            @ModelAttribute OfferSearchCriteria offerSearchCriteria,
            Model model,
            Authentication authentication) {
        Utility.putUserDataToModel(authentication, userService, model);

        Page<OfferRowDTO> page = offerService.findAllByCriteria(offerSearchCriteria).map(offerRowMapper::map);
        List<OfferRowDTO> allOffers = page.getContent();
        model.addAttribute("allOffersDTOs", allOffers);


//Pagination Bar
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

//Check selected for filters
        model.addAttribute("experienceLevelChecked", offerSearchCriteria.getExperienceLevels());
        model.addAttribute("skillChecked", offerSearchCriteria.getSkills());
        model.addAttribute("cityChecked", offerSearchCriteria.getCity());
        model.addAttribute("remoteChecked", offerSearchCriteria.getRemoteWork());
        model.addAttribute("salaryChecked", offerSearchCriteria.getSalary());
        model.addAttribute("statusChecked", offerSearchCriteria.getStatus());
        model.addAttribute("employerChecked", offerSearchCriteria.getEmployer());

//Enums for filters
        model.addAttribute("remoteEnumFull", Keys.RemoteWork.FULL.getName());
        model.addAttribute("remoteEnumOffice", Keys.RemoteWork.OFFICE.getName());
        model.addAttribute("remoteEnumPartly", Keys.RemoteWork.PARTLY.getName());
        model.addAttribute("expLevelEnumJunior", Keys.Experience.JUNIOR.getName());
        model.addAttribute("expLevelEnumMid", Keys.Experience.MID.getName());
        model.addAttribute("expLevelEnumSenior", Keys.Experience.SENIOR.getName());
        model.addAttribute("salaryEnumWith", Keys.Salary.WITH.getName());
        model.addAttribute("salaryEnumUndisclosed", Keys.Salary.UNDISCLOSED.getName());
        model.addAttribute("statusEnumActive", Keys.OfferState.ACTIVE.getName());
        model.addAttribute("statusEnumExpired", Keys.OfferState.EXPIRED.getName());
//List for filters
        List<SkillDTO> allSkills = skillService.findAll().stream().map(skillMapper::map).toList();
        List<CityDTO> allCity = cityService.findAll().stream().map(cityMapper::map).toList();
        List<EmployerRowDTO> allEmployer = employerService.findAll().stream().map(employerRowMapper::map).toList();
        model.addAttribute("allSkillsDTOs", allSkills);
        model.addAttribute("allCityDTOs", allCity);
        model.addAttribute("allEmployerDTOs", allEmployer);

//Sorting
        model.addAttribute("sortBy", offerSearchCriteria.getSortBy());
        model.addAttribute("sortDirection", offerSearchCriteria.getSortDirection());
        model.addAttribute("reverseSortDirection", offerSearchCriteria.getSortDirection()
                .equals(Sort.Direction.ASC) ? Sort.Direction.DESC : Sort.Direction.ASC);


        return "candidate/offers";
    }

    @GetMapping(value = OFFER_DETAILS+"/{offerId}")
    public String getOfferDetails(@PathVariable Long offerId, Model model, Authentication authentication) {
        Utility.putUserDataToModel(authentication, userService, model);
        OfferDetailsDTO offerDetailsDTO = offerDetailsMapper.map(offerService.findById(offerId));
        model.addAttribute("offerDetailsDTO", offerDetailsDTO);
        return "candidate/offer_details";

    }
    @GetMapping(value = EMPLOYER_DETAILS+"/{employerId}")
    public String getEmployerDetails(@PathVariable Long employerId, Model model, Authentication authentication) {
        Utility.putUserDataToModel(authentication, userService, model);
        EmployerDetailsDTO employerDetailsDTO = employerDetailsMapper.map(employerService.findById(employerId));
        model.addAttribute("employerDetailsDTO", employerDetailsDTO);
        return "candidate/employer_details";

    }

}
