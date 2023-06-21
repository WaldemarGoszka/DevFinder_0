package pl.devfinder.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@AllArgsConstructor
public class IndexController {

    static final String INDEX = "/";

    @GetMapping(INDEX)
    public String homePage() {
        return "index";
    }

}
