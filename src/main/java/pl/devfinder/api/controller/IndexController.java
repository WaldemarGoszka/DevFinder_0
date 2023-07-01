package pl.devfinder.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class IndexController {

    static final String INDEX = "/";

    @GetMapping(INDEX)
    public String homePage(Authentication authentication) {
        if(authentication != null) {
            System.out.println(authentication.getName());
        }
        else{
            System.out.println(authentication);
        }
        return "index";
    }
    @GetMapping("/index")
    public String homePage2() {
        return "index";
    }
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
