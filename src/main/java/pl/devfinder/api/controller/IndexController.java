package pl.devfinder.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.devfinder.business.UserService;
import pl.devfinder.business.management.Utility;
import pl.devfinder.domain.User;
import pl.devfinder.infrastructure.security.configuration.UserDetailsCustom;

import java.security.Principal;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class IndexController {

    static final String INDEX = "/";

    private final UserService userService;

    @GetMapping(INDEX)
    public String homePage(Model model, Authentication authentication) {
        Utility.getUserToPage(authentication, userService, model);


        return "index";
    }
//    @GetMapping("/index")
//    public String homePage2() {
//        return "index";
//    }
//    @GetMapping("/logout")
//    public String logout() {
//        return "redirect:/login?logout";
//    }
    @GetMapping("/403")
    public String error403(){
        return "403";
    }
    @GetMapping("/404")
    public String error404(){
        return "404";
    }

}
