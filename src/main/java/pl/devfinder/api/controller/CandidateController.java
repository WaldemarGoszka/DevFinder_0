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
import pl.devfinder.domain.OfferPage;
import pl.devfinder.domain.OfferSearchCriteria;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/candidate")
public class CandidateController {

    public static final String CANDIDATE_PROFILE = "/profile";
    public static final String CANDIDATE_EDIT_PROFILE = "/edit_profile";
    public static final String PAGE_NO_1 = "?pageNumber=1";
    public static final String OFFERS_LIST = "/offers";
    public static final String OFFER_DETAILS = "/offer/{offerId}";
    public static final String EMPLOYERS = "/employers";
    public static final String MY_EMPLOYER = "/my_employer";
    public static final String CANDIDATE_SETTINGS = "/settings";
    public static final String CANDIDATE_MATCHED_OFFERS = "/matched_offers";
    public static final String CANDIDATE_APPLICATIONS = "/applications";
    public static final String EMPLOYER_OFFERS = "/employer_offers";

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

    @GetMapping(value = EMPLOYERS)
    public String getEmployersList(Model model, Authentication authentication) {
        Utility.putUserDataToModel(authentication, userService, model);

        List<EmployerRowDTO> allEmployers = employerService.findAllEmployers().stream()
                .map(employerRowMapper::map)
                .toList();
//todo przyciks Details odnoszący się do profilu firmy
        model.addAttribute("allEmployersDTOs", allEmployers);
        return "candidate/employers";
    }

//    @GetMapping(value = OFFERS_LIST)
//    public String redirectOffersList() {
//        return "redirect:" + OFFERS_LIST + PAGE_NO_1;
//    }

    @GetMapping(value = OFFERS_LIST)
    public String getOffersList(
//            @PathVariable(value = "pageNumber") Integer pageNumber,
            @ModelAttribute OfferSearchCriteria offerSearchCriteria,
//            @ModelAttribute OfferPage offerPage,
            Model model,
            Authentication authentication) {
        Utility.putUserDataToModel(authentication, userService, model);

        System.out.println("START #############################");
//        System.out.println("PAGENUMBER: " + pageNumber);
        System.out.println("Filters:");
//        System.out.println(offerPage.getPageNumber());
        System.out.println("---");
        System.out.println(offerSearchCriteria.getExperienceLevels());
        System.out.println(offerSearchCriteria.getSkills());
        System.out.println(offerSearchCriteria.getCity());
        System.out.println(offerSearchCriteria.getRemoteWork());
        System.out.println(offerSearchCriteria.getSalary());
        System.out.println(offerSearchCriteria.getSalaryMin());
        System.out.println("END #############################");

        Page<OfferRowDTO> page = offerService.findAllByStatePaginated(offerSearchCriteria, Keys.OfferState.OPEN).map(offerRowMapper::map);
        List<OfferRowDTO> allOffers = page.getContent();
//ToolBar
//        model.addAttribute("pageNumber", offerPage.getPageNumber());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

//Check selected filters
        model.addAttribute("experienceLevelChecked", offerSearchCriteria.getExperienceLevels());
        model.addAttribute("skillChecked", offerSearchCriteria.getSkills());
        model.addAttribute("cityChecked", offerSearchCriteria.getCity());
        model.addAttribute("remoteChecked", offerSearchCriteria.getRemoteWork());
        model.addAttribute("salaryChecked", offerSearchCriteria.getSalary());
//Enums for filters
        model.addAttribute("remoteEnumFull", Keys.RemoteWork.FULL.getName());
        model.addAttribute("remoteEnumOffice", Keys.RemoteWork.OFFICE.getName());
        model.addAttribute("remoteEnumPartly", Keys.RemoteWork.PARTLY.getName());
        model.addAttribute("expLevelEnumJunior", Keys.Experience.JUNIOR.getName());
        model.addAttribute("expLevelEnumMid", Keys.Experience.MID.getName());
        model.addAttribute("expLevelEnumSenior", Keys.Experience.SENIOR.getName());
        model.addAttribute("salaryEnumWith", Keys.Salary.WITH.getName());
        model.addAttribute("salaryEnumUndisclosed", Keys.Salary.UNDISCLOSED.getName());


//Sorting
        model.addAttribute("sortBy", offerSearchCriteria.getSortBy());
        model.addAttribute("sortDirection", offerSearchCriteria.getSortDirection());
        model.addAttribute("reverseSortDirection", offerSearchCriteria.getSortDirection()
                .equals(Sort.Direction.ASC) ? Sort.Direction.DESC : Sort.Direction.ASC);
//
//        model.addAttribute("listEmployees", listEmployees);


//        List<OfferRowDTO> allOffers = offerService.findAllByState(Keys.OfferState.OPEN).stream()
//                .map(offerRowMapper::map)
//                .toList();
        List<SkillDTO> allSkills= skillService.findAll().stream().map(skillMapper::map).toList();
        List<CityDTO> allCity = cityService.findAll().stream().map(cityMapper::map).toList();
        model.addAttribute("allOffersDTOs", allOffers);
        model.addAttribute("allSkillsDTOs", allSkills);
        model.addAttribute("allCityDTOs", allCity);

        return "candidate/offers";
    }

    @GetMapping(value = OFFER_DETAILS)
    public String getOfferDetails(@PathVariable Long offerId, Model model, Authentication authentication) {
        Utility.putUserDataToModel(authentication, userService, model);
        OfferDetailsDTO offerDetailsDTO = offerDetailsMapper.map(offerService.findById(offerId));
        model.addAttribute("offerDetailsDTO", offerDetailsDTO);
        return "candidate/offer_details";

    }

}
