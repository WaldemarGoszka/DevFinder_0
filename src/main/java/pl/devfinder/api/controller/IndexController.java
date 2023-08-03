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

    private final UserService userService;
    private final CandidateService candidateService;
    private final EmployerService employerService;


    @GetMapping(INDEX)
    public String homePage(Model model, Authentication authentication) {
        Optional<User> user = Utility.putUserDataToModel(authentication, userService, model);
        if (user.isEmpty()) {
            return "/login";
        }
        if (user.get().getRole().getRole().equals(Keys.Role.CANDIDATE.getName())) {
            Optional<Candidate> candidate = candidateService.findByCandidateUuid(user.get().getUserUuid());
            if (candidate.isPresent() && Objects.nonNull(candidate.get().getPhotoFilename())) {
                model.addAttribute("photoDir", "/user_data/" + candidate.get().getCandidateUuid() + candidate.get().getPhotoFilename());
            } else {
                model.addAttribute("photoDir", "/img/user.jpg");
            }
        }
        if (user.get().getRole().getRole().equals(Keys.Role.EMPLOYER.getName())) {
            Optional<Employer> employer = employerService.findByEmployerUuid(user.get().getUserUuid());
            if (employer.isPresent() && Objects.nonNull(employer.get().getLogoFilename())) {
                model.addAttribute("photoDir", "/user_data/" + employer.get().getEmployerUuid() + employer.get().getLogoFilename());
            } else {
                model.addAttribute("photoDir", "/img/user.jpg");
            }
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
    public String error403() {
        return "403";
    }

    @GetMapping("/404")
    public String error404() {
        return "404";
    }

}
