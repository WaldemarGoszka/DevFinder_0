package pl.devfinder.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class EmployerController {

    public static final String EMPLOYER = "/employer";
    public static final String EMPLOYER_PROFILE = "/employer/profile";
    public static final String EMPLOYER_EDIT_PROFILE = "/employer/edit_profile";
    public static final String MATCHED_DEVELOPERS = "/employer/matched_developers";
    public static final String SEARCH_DEVELOPER =  "/employer/find_developer";
    public static final String POST_A_JOB =  "/employer/post_job";
    public static final String EMPLOYER_SETTINGS =  "/employer/settings";
    public static final String EMPLOYEE = "/employer/employee";

    @GetMapping(value = EMPLOYER)
    public String homePage(Model model) {

        return "employer_portal";
    }

    @GetMapping(value = EMPLOYER_PROFILE)
    public String getProfile(Model model) {
// getEmpoyerProfile
        return "employer_profile";
    }


}
