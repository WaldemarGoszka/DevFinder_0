package pl.devfinder.api.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class DeveloperController {

    public static final String DEVELOPER = "/developer";

    @GetMapping(value = DEVELOPER)
    public String homePage(Model model) {

        return "developer_portal";
    }

}
