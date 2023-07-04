package pl.devfinder.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.devfinder.api.dto.EmployerRowDTO;
import pl.devfinder.api.dto.OfferRowDTO;
import pl.devfinder.api.dto.mapper.EmployerRowMapper;
import pl.devfinder.api.dto.mapper.OfferRowMapper;
import pl.devfinder.business.EmployerService;
import pl.devfinder.business.OfferService;
import pl.devfinder.business.UserService;
import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.User;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/candidate")
public class CandidateController {

    public static final String CANDIDATE_PROFILE = "/profile";
    public static final String CANDIDATE_EDIT_PROFILE = "/edit_profile";
    public static final String OFFERS_LIST = "/offers";
    public static final String EMPLOYERS_LIST = "/employers";
    public static final String MY_EMPLOYER = "/my_employer";
    public static final String CANDIDATE_SETTINGS = "/settings";
    public static final String CANDIDATE_MATCHED_OFFERS = "/matched_offers";
    public static final String CANDIDATE_APPLICATIONS = "/applications";
    public static final String EMPLOYER_OFFERS = "/employer_offers";

    private final EmployerService employerService;
    private final EmployerRowMapper employerRowMapper;
    private final OfferService offerService;
    private final OfferRowMapper offerRowMapper;
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
        if(authentication != null){
            Optional<User> user = userService.findByEmail(authentication.getName());
            if(user.isPresent()){
                model.addAttribute("user", user.get());
            }
        }
//
        return "candidate/profile";
    }

    @GetMapping(value = EMPLOYERS_LIST)
    public String getEmployersList(Model model, Authentication authentication) {
        if(authentication != null){
            Optional<User> user = userService.findByEmail(authentication.getName());
            if(user.isPresent()){
                model.addAttribute("user", user.get());
            }
        }
        List<EmployerRowDTO> allEmployers = employerService.findAllEmployers().stream()
                .map(employerRowMapper::map)
                .toList();
//todo przyciks Details odnoszący się do profilu firmy
        model.addAttribute("allEmployersDTOs", allEmployers);
        return "candidate/find_employer";
    }

    @GetMapping(value = OFFERS_LIST)
    public String getOffersList(Model model, Authentication authentication) {
        if(authentication != null){
            Optional<User> user = userService.findByEmail(authentication.getName());
            if(user.isPresent()){
                model.addAttribute("user", user.get());
            }
        }
        List<OfferRowDTO> allOffers = offerService.findAllByState(Keys.OfferState.OPEN).stream()
                .map(offerRowMapper::map)
                .toList();
        model.addAttribute("allOffersDTOs", allOffers);
        return "candidate/_offers";
    }

}
