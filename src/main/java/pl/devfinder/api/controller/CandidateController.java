package pl.devfinder.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.devfinder.api.dto.EmployerRowDTO;
import pl.devfinder.api.dto.OfferDetailsDTO;
import pl.devfinder.api.dto.OfferRowDTO;
import pl.devfinder.api.dto.mapper.EmployerRowMapper;
import pl.devfinder.api.dto.mapper.OfferDetailsMapper;
import pl.devfinder.api.dto.mapper.OfferRowMapper;
import pl.devfinder.business.EmployerService;
import pl.devfinder.business.OfferService;
import pl.devfinder.business.UserService;
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

    @GetMapping(value = OFFERS_LIST)// + PAGE_NO_1)
    public String getOffersList(
//            @PathVariable(value = "pageNumber") Integer pageNumber,
            @ModelAttribute OfferSearchCriteria offerSearchCriteria,
            @ModelAttribute OfferPage offerPage,
            Model model,
            Authentication authentication) {
        Utility.putUserDataToModel(authentication, userService, model);

        System.out.println("START #############################");
//        System.out.println("PAGENUMBER: " + pageNumber);
        System.out.println("Filters:");
        System.out.println(offerSearchCriteria.getIsExperienceLevelIsJunior());
        System.out.println(offerSearchCriteria.getIsExperienceLevelIsSenior());
        System.out.println(offerSearchCriteria.getRemoteWork());
        System.out.println("---");
        System.out.println(offerPage.getPageNumber());
        System.out.println(offerPage.getPageSize());
        System.out.println(offerPage.getSortDirection());
        System.out.println(offerPage.getSortBy());
        System.out.println("END #############################");

        Page<OfferRowDTO> page = offerService.findAllByStatePaginated(offerPage.getPageNumber(), offerPage.getPageSize(), Keys.OfferState.OPEN).map(offerRowMapper::map);
        List<OfferRowDTO> allOffers = page.getContent();

        model.addAttribute("pageNumber", offerPage.getPageNumber());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortBy", offerPage.getSortBy());
        model.addAttribute("sortDirection", offerPage.getSortDirection());
        model.addAttribute("reverseSortDirection", offerPage.getSortDirection()
                .equals(Sort.Direction.ASC) ? Sort.Direction.DESC : Sort.Direction.ASC);
//
//        model.addAttribute("listEmployees", listEmployees);


//        List<OfferRowDTO> allOffers = offerService.findAllByState(Keys.OfferState.OPEN).stream()
//                .map(offerRowMapper::map)
//                .toList();
        model.addAttribute("allOffersDTOs", allOffers);

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
