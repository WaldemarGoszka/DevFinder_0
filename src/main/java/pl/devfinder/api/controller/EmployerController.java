package pl.devfinder.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.devfinder.api.dto.CandidateRowDTO;
import pl.devfinder.api.dto.mapper.CandidateRowMapper;
import pl.devfinder.business.CandidateService;
import pl.devfinder.business.management.Keys;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/employer")
public class EmployerController {

    public static final String EMPLOYER_PROFILE = "/profile";
    public static final String EMPLOYER_EDIT_PROFILE = "/edit_profile";
    public static final String MATCHED_CANDIDATE = "/matched_candidate";
    public static final String SEARCH_CANDIDATE = "/find_candidate";
    public static final String POST_A_JOB = "/post_job";

    public static final String MY_JOB_OFFERS = "/posted_offers";
    public static final String EMPLOYER_SETTINGS = "/settings";
    public static final String EMPLOYEE = "/employee";

    private final CandidateService candidateService;
    private final CandidateRowMapper candidateRowMapper;

    @GetMapping()
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
