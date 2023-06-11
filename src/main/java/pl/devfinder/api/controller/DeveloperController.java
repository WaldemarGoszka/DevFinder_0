package pl.devfinder.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping(value = DEVELOPER)
    public String homePage(Model model) {

        return "developer_portal";
    }
    @GetMapping(value = DEVELOPER_PROFILE)
    public String getProfile(Model model) {
// getDeveloperProfile
        return "developer_profile";
    }

}
