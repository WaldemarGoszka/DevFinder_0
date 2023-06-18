package pl.devfinder.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.devfinder.api.dto.CandidateRowDTO;
import pl.devfinder.api.dto.mapper.CandidateRowMapper;
import pl.devfinder.business.CandidateService;
import pl.devfinder.business.management.Keys;

import java.util.List;

@Controller
@AllArgsConstructor
public class EmployerController {

    public static final String EMPLOYER = "/employer";
    public static final String EMPLOYER_PROFILE = "/employer/profile";
    public static final String EMPLOYER_EDIT_PROFILE = "/employer/edit_profile";
    public static final String MATCHED_CANDIDATE = "/employer/matched_candidate";
    public static final String SEARCH_CANDIDATE = "/employer/find_candidate";
    public static final String POST_A_JOB = "/employer/post_job";

    public static final String MY_JOB_OFFERS = "/employer/posted_offers";
    public static final String EMPLOYER_SETTINGS = "/employer/settings";
    public static final String EMPLOYEE = "/employer/employee";

    private final CandidateService candidateService;
    private final CandidateRowMapper candidateRowMapper;

    @GetMapping(value = EMPLOYER)
    public String homePage(Model model) {

        return "employer/portal";
    }

    @GetMapping(value = EMPLOYER_PROFILE)
    public String getProfile(Model model) {
// getEmpoyerProfile
        return "employer/profile";
    }
    @GetMapping(value = SEARCH_CANDIDATE)
    public String getCandidatesList(Model model) {
        List<CandidateRowDTO> allCandidates = candidateService.findAllByState(Keys.CandidateState.ACTIVE).stream()
                .map(candidateRowMapper::map)
                .toList();
        model.addAttribute("allCandidatesDTOs", allCandidates);
        return "employer/find_candidate";
    }


}
