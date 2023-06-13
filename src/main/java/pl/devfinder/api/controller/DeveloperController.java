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

import java.util.List;

@Controller
@AllArgsConstructor
public class DeveloperController {

    public static final String DEVELOPER = "/developer";
    public static final String DEVELOPER_PROFILE = "/developer/profile";
    public static final String DEVELOPER_EDIT_PROFILE = "/developer/edit_profile";
    public static final String OFFERS_LIST = "/developer/offers";
    public static final String EMPLOYERS_LIST = "/developer/employers";
    public static final String DEVELOPER_SETTINGS = "/developer/settings";
    public static final String DEVELOPER_MATCHED_OFFERS = "/developer/matched_offers";
    public static final String DEVELOPER_APPLICATIONS = "/developer/applications";

    private final EmployerService employerService;
    private final EmployerRowMapper employerRowMapper;
    private final OfferService offerService;
    private final OfferRowMapper offerRowMapper;

    @GetMapping(value = DEVELOPER)
    public String homePage(Model model) {

        return "developer/portal";
    }

    @GetMapping(value = DEVELOPER_PROFILE)
    public String getProfile(Model model) {
// getDeveloperProfile
        return "developer/profile";
    }

    @GetMapping(value = EMPLOYERS_LIST)
    public String getEmployersList(Model model) {
        List<EmployerRowDTO> allEmployers = employerService.findAllEmployers().stream()
                .map(employerRowMapper::map)
                .toList();
//TODO dodać liczbę ofert w ostatnim wierszu
//todo przyciks Details odnoszący się do profilu firmy
        model.addAttribute("allEmployersDTOs", allEmployers);
        return "developer/find_employer";
    }

    @GetMapping(value = OFFERS_LIST)
    public String getOffersList(Model model) {
        //todo
        List<OfferRowDTO> allOffers = offerService.findAllOffers().stream()
                .map(offerRowMapper::map)
                .toList();
        model.addAttribute("allOffersDTOs", allOffers);
        return "developer/offers";
    }

}
