package pl.devfinder.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.devfinder.api.dto.EmployerRowDTO;
import pl.devfinder.api.dto.OfferRowDTO;
import pl.devfinder.api.dto.mapper.EmployerRowMapper;
import pl.devfinder.api.dto.mapper.OfferRowMapper;
import pl.devfinder.business.EmployerService;
import pl.devfinder.business.OfferService;
import pl.devfinder.business.management.Keys;

import java.util.List;

@Controller
@AllArgsConstructor
public class CandidateController {

    public static final String CANDIDATE = "/candidate";
    public static final String CANDIDATE_PROFILE = "/candidate/profile";
    public static final String CANDIDATE_EDIT_PROFILE = "/candidate/edit_profile";
    public static final String OFFERS_LIST = "/candidate/offers";
    public static final String EMPLOYERS_LIST = "/candidate/employers";
    public static final String MY_EMPLOYER = "/candidate/my_employer";
    public static final String CANDIDATE_SETTINGS = "/candidate/settings";
    public static final String CANDIDATE_MATCHED_OFFERS = "/candidate/matched_offers";
    public static final String CANDIDATE_APPLICATIONS = "/candidate/applications";
    public static final String EMPLOYER_OFFERS = "/candidate/employer_offers";

    private final EmployerService employerService;
    private final EmployerRowMapper employerRowMapper;
    private final OfferService offerService;
    private final OfferRowMapper offerRowMapper;

    @GetMapping(value = CANDIDATE)
    public String homePage(Model model) {

        return "candidate/portal";
    }

    @GetMapping(value = CANDIDATE_PROFILE)
    public String getProfile(Model model) {
//
        return "candidate/profile";
    }

    @GetMapping(value = EMPLOYERS_LIST)
    public String getEmployersList(Model model) {
        List<EmployerRowDTO> allEmployers = employerService.findAllEmployers().stream()
                .map(employerRowMapper::map)
                .toList();
//todo przyciks Details odnoszący się do profilu firmy
        model.addAttribute("allEmployersDTOs", allEmployers);
        return "candidate/find_employer";
    }

    @GetMapping(value = OFFERS_LIST)
    public String getOffersList(Model model) {
        List<OfferRowDTO> allOffers = offerService.findAllByState(Keys.OfferState.OPEN).stream()
                .map(offerRowMapper::map)
                .toList();
        model.addAttribute("allOffersDTOs", allOffers);
        return "candidate/offers";
    }

}
