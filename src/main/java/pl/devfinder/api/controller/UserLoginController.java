package pl.devfinder.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserLoginController {
    public static final String LOGIN_PAGE = "/login";

    @GetMapping(LOGIN_PAGE)
    public String getLoginPage(){
        return "login";
    }
}
