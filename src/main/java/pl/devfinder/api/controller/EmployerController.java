package pl.devfinder.api.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class EmployerController {

    public static final String EMPLOYER = "/employer";

    @GetMapping(value = EMPLOYER)
    public String homePage(Model model) {

        return "employer_portal";
    }
}
