package pl.devfinder.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.devfinder.business.CandidateService;
import pl.devfinder.business.EmployerService;
import pl.devfinder.business.UserService;
import pl.devfinder.business.management.Keys;
import pl.devfinder.business.management.Utility;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.Employer;
import pl.devfinder.domain.User;

import java.util.Objects;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class IndexController {

    static final String INDEX = "/";
    public static final String ERROR_403 = "/403";
    public static final String ERROR_404 = "/404";
    public static final String LOGIN_PAGE = "/login";


    private final UserService userService;
    private final UserController userController;


    @GetMapping(INDEX)
    public String homePage(Model model, Authentication authentication) {
        Optional<User> user = Utility.putUserDataToModel(authentication, userService, model);
        userController.setUserPhotoToModel(model, user);
        return "index";
    }

    @GetMapping(ERROR_403)
    public String error403() {
        return "403";
    }

    @GetMapping(ERROR_404)
    public String error404() {
        return "404";
    }

    @GetMapping(LOGIN_PAGE)
    public String getLoginPage(){
        return "login";
    }

}
