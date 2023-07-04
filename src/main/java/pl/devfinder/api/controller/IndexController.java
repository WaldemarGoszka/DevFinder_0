package pl.devfinder.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.devfinder.business.UserService;
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
//        if (authentication.getPrincipal() instanceof UserDetailsCustom) {
//            UserDetailsCustom userDetails = (UserDetailsCustom) authentication.getPrincipal();
//            String constantValue = userDetails.getUsername();
//
//        }
        System.out.println("hey");
        System.out.println("hey");
        System.out.println("hey");

        System.out.println("hey");
        if(authentication != null){
            Optional<User> user = userService.findByEmail(authentication.getName());
            if(user.isPresent()){
                model.addAttribute("user", user.get());
            }
        }
        if(authentication != null) {
            System.out.println(authentication.getName());
        }
        else{
            System.out.println(authentication);
        }
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
