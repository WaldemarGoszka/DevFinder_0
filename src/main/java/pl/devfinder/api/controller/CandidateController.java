package pl.devfinder.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.devfinder.api.dto.*;
import pl.devfinder.api.dto.mapper.*;
import pl.devfinder.business.*;
import pl.devfinder.business.management.Keys;
import pl.devfinder.business.management.Utility;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.CandidateUpdateRequest;
import pl.devfinder.domain.User;
import pl.devfinder.domain.exception.NotFoundException;
import pl.devfinder.domain.search.EmployerSearchCriteria;
import pl.devfinder.domain.search.OfferSearchCriteria;

import java.util.*;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/candidate")
public class CandidateController {

    public static final String CANDIDATE_PROFILE = "/profile";
    public static final String CANDIDATE_EDIT_PROFILE = "/edit_profile";
    public static final String CANDIDATE_DELETE_PROFILE = "/delete";
    public static final String CANDIDATE_DELETE_CV_FILE = "/edit_profile/delete_cvFile";
    public static final String CANDIDATE_DELETE_PHOTO_FILE = "/edit_profile/delete_photoFile";
    public static final String CANDIDATE_UPDATE_PROFILE = "/update";
    public static final String CANDIDATE_NEW_PROFILE = "/new";

    public static final String OFFERS_LIST = "/offers";
    public static final String OFFER_DETAILS = "/offer";

    public static final String EMPLOYER_DETAILS = "/employer";
    public static final String EMPLOYERS_LIST = "/employers";


    public static final String MY_EMPLOYER = "/my_employer";
    public static final String CANDIDATE_SETTINGS = "/settings";
    public static final String CANDIDATE_MATCHED_OFFERS = "/matched_offers";
    public static final String CANDIDATE_APPLICATIONS = "/applications";

    private final EmployerService employerService;
    private final EmployerRowMapper employerRowMapper;
    private final OfferService offerService;
    private final OfferRowMapper offerRowMapper;
    private final OfferDetailsMapper offerDetailsMapper;
    private final UserService userService;
    private final CityService cityService;
    private final CityMapper cityMapper;
    private final SkillService skillService;
    private final SkillMapper skillMapper;
    private final EmployerDetailsMapper employerDetailsMapper;
    private final CandidateService candidateService;
    private final CandidateDetailsMapper candidateDetailsMapper;
    private final CandidateRequestMapper candidateRequestMapper;


    @GetMapping()
    public String homePage(Model model) {

        // to uzyć do sprawdzania autentykatora na każdej stronie z ecommerce project
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
//            return "redirect:/login";
//        }
//        return "index";

//        albo
//
//        if (principal == null) {
//            return "redirect:/login";

        return "candidate/portal";
    }

//    @GetMapping(value = CANDIDATE_PROFILE)
//    public String getProfile(Model model, Authentication authentication) {
//        Utility.putUserDataToModel(authentication, userService, model);
////
//        return "candidate/profile";
//    }

    @GetMapping(value = EMPLOYERS_LIST)
    public String getEmployersList(EmployerSearchCriteria employerSearchCriteria,
                                   Model model,
                                   Authentication authentication) {
        User user = Utility.putUserDataToModel(authentication, userService, model)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        ;
        Optional<Candidate> candidate = candidateService.findByCandidateUuid(user.getUserUuid());
        setCandidatePhotoToModel(model, candidate);

        Page<EmployerRowDTO> page = employerService.findAllByCriteria(employerSearchCriteria).map(employerRowMapper::map);
        List<EmployerRowDTO> allEmployers = page.getContent();
        model.addAttribute("allEmployersDTOs", allEmployers);

        model.addAttribute("skillChecked", employerSearchCriteria.getSkillsInOffers());
        model.addAttribute("cityChecked", employerSearchCriteria.getCity());
        model.addAttribute("offersChecked", employerSearchCriteria.getJobOffersStatus());

        model.addAttribute("hasNoJobOffersEnum", Keys.EmployerFilterBy.hasNoJobOffers.getName());
        model.addAttribute("hasJobOffersEnum", Keys.EmployerFilterBy.hasJobOffers.getName());

//Pagination Bar
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

//Sorting
        model.addAttribute("sortBy", employerSearchCriteria.getSortBy());
        model.addAttribute("sortDirection", employerSearchCriteria.getSortDirection());
        model.addAttribute("reverseSortDirection", employerSearchCriteria.getSortDirection()
                .equals(Sort.Direction.ASC) ? Sort.Direction.DESC : Sort.Direction.ASC);

        List<SkillDTO> allSkills = skillService.findAll().stream().map(skillMapper::map).toList();
        List<CityDTO> allCity = cityService.findAll().stream().map(cityMapper::map).toList();
        model.addAttribute("allSkillsDTOs", allSkills);
        model.addAttribute("allCityDTOs", allCity);

        return "candidate/employers";
    }


    @GetMapping(value = OFFERS_LIST)
    public ModelAndView getOffersList(
            @ModelAttribute OfferSearchCriteria offerSearchCriteria,
            Model model,
            Authentication authentication) {
        User user = Utility.putUserDataToModel(authentication, userService, model)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        ;
        Optional<Candidate> candidate = candidateService.findByCandidateUuid(user.getUserUuid());
        setCandidatePhotoToModel(model, candidate);

        Map<String, ?> offersListData = prepareOffersListData(offerSearchCriteria);

        return new ModelAndView("candidate/offers", offersListData);
    }

    private Map<String, ?> prepareOffersListData(OfferSearchCriteria offerSearchCriteria) {
        Map<String, Object> model = new HashMap<>();

        Page<OfferRowDTO> page = offerService.findAllByCriteria(offerSearchCriteria).map(offerRowMapper::map);
        List<OfferRowDTO> allOffers = page.getContent();
        model.put("allOffersDTOs", allOffers);

//Pagination Bar
        model.put("totalPages", page.getTotalPages());
        model.put("totalItems", page.getTotalElements());

//Check selected for filters
        model.put("experienceLevelChecked", offerSearchCriteria.getExperienceLevels());
        model.put("skillChecked", offerSearchCriteria.getSkills());
        model.put("cityChecked", offerSearchCriteria.getCity());
        model.put("remoteChecked", offerSearchCriteria.getRemoteWork());
        model.put("salaryChecked", offerSearchCriteria.getSalary());
        model.put("statusChecked", offerSearchCriteria.getStatus());
        model.put("employerChecked", offerSearchCriteria.getEmployer());

//Enums for filters
        model.put("remoteEnumFull", Keys.RemoteWork.FULL.getName());
        model.put("remoteEnumOffice", Keys.RemoteWork.OFFICE.getName());
        model.put("remoteEnumPartly", Keys.RemoteWork.PARTLY.getName());
        model.put("expLevelEnumJunior", Keys.Experience.JUNIOR.getName());
        model.put("expLevelEnumMid", Keys.Experience.MID.getName());
        model.put("expLevelEnumSenior", Keys.Experience.SENIOR.getName());
        model.put("salaryEnumWith", Keys.Salary.WITH.getName());
        model.put("salaryEnumUndisclosed", Keys.Salary.UNDISCLOSED.getName());
        model.put("statusEnumActive", Keys.OfferState.ACTIVE.getName());
        model.put("statusEnumExpired", Keys.OfferState.EXPIRED.getName());

//List for filters
        List<SkillDTO> allSkills = skillService.findAll().stream().map(skillMapper::map).toList();
        List<CityDTO> allCity = cityService.findAll().stream().map(cityMapper::map).toList();
        List<EmployerRowDTO> allEmployer = employerService.findAll().stream().map(employerRowMapper::map).toList();
        model.put("allSkillsDTOs", allSkills);
        model.put("allCityDTOs", allCity);
        model.put("allEmployerDTOs", allEmployer);

//Sorting
        model.put("sortBy", offerSearchCriteria.getSortBy());
        model.put("sortDirection", offerSearchCriteria.getSortDirection());
        model.put("reverseSortDirection", offerSearchCriteria.getSortDirection()
                .equals(Sort.Direction.ASC) ? Sort.Direction.DESC : Sort.Direction.ASC);

        return model;
    }

    @GetMapping(value = OFFER_DETAILS + "/{offerId}")
    public String getOfferDetails(@PathVariable Long offerId, Model model, Authentication authentication) {
        User user = Utility.putUserDataToModel(authentication, userService, model)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        ;
        Optional<Candidate> candidate = candidateService.findByCandidateUuid(user.getUserUuid());
        setCandidatePhotoToModel(model, candidate);

        OfferDetailsDTO offerDetailsDTO = offerDetailsMapper.map(offerService.findById(offerId));
        model.addAttribute("offerDetailsDTO", offerDetailsDTO);
        return "candidate/offer_details";

    }

    @GetMapping(value = EMPLOYER_DETAILS + "/{employerId}")
    public String getEmployerDetails(@PathVariable Long employerId, Model model, Authentication authentication) {
        User user = Utility.putUserDataToModel(authentication, userService, model)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        ;
        Optional<Candidate> candidate = candidateService.findByCandidateUuid(user.getUserUuid());
        setCandidatePhotoToModel(model, candidate);

        EmployerDetailsDTO employerDetailsDTO = employerDetailsMapper.map(employerService.findById(employerId));
        model.addAttribute("employerDetailsDTO", employerDetailsDTO);
        return "candidate/employer_details";

    }

    @GetMapping(value = CANDIDATE_PROFILE)
    public String getCandidateProfile(Model model, Authentication authentication) {
        User user = Utility.putUserDataToModel(authentication, userService, model)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));

        Optional<Candidate> candidate = candidateService.findByCandidateUuid(user.getUserUuid());
        setCandidatePhotoToModel(model, candidate);
//        if (user) {
//            return "redirect:/login?no_permissions";
//        }
//        String userUuid = user.orElseThrow(() -> new NotFoundException("Could not find user by or authorization failed")).getUserUuid();
        if (candidate.isEmpty()) {
            return "redirect:edit_profile?new";
        }
        CandidateDetailsDTO candidateDetailsDTO = candidate.map(candidateDetailsMapper::map).orElseThrow();

        model.addAttribute("candidateDetailsDTO", candidateDetailsDTO);
        model.addAttribute("downloadCvFilePath", "/user_data/" + candidate.get().getCandidateUuid() + candidate.get().getCvFilename());
        return "candidate/profile";
    }

    @GetMapping(value = CANDIDATE_EDIT_PROFILE)
    public String getCandidateEditProfile(Model model, Authentication authentication) {
        User user = Utility.putUserDataToModel(authentication, userService, model)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
//        Utility.getUserPhotoPath(user, candidateService, employerService, model);
        Optional<Candidate> candidate = candidateService.findByCandidateUuid(user.getUserUuid());
        setCandidatePhotoToModel(model, candidate);


        //TODO putUserPictureToModel do wszykich metod get
        CandidateDetailsDTO candidateDetailsDTO = new CandidateDetailsDTO().withStatus(Keys.CandidateState.ACTIVE.getName());
        if (candidate.isPresent()) {
            log.info("Edit profile existing candidate id: [{}]", candidate.get().getCandidateId());
            candidateDetailsDTO = candidate.map(candidateDetailsMapper::map).get();
            model.addAttribute("candidateDetailsDTO", candidateDetailsDTO);

        } else {
            log.info("Edit profile new candidate");
            model.addAttribute("candidateDetailsDTO", candidateDetailsDTO);
        }

//        model.addAttribute("candidateDetailsDTO", candidateDetailsDTO);

        model.addAttribute("statusChecked", candidateDetailsDTO.getStatus());

//Enums
        model.addAttribute("statusEnumActive", Keys.CandidateState.ACTIVE.getName());
        model.addAttribute("statusEnumInactive", Keys.CandidateState.INACTIVE.getName());
        model.addAttribute("statusEnumEmployed", Keys.CandidateState.EMPLOYED.getName());
        model.addAttribute("expLevelEnumJunior", Keys.Experience.JUNIOR.getName());
        model.addAttribute("expLevelEnumMid", Keys.Experience.MID.getName());
        model.addAttribute("expLevelEnumSenior", Keys.Experience.SENIOR.getName());

        model.addAttribute("cvFileLink", candidateDetailsDTO.getCvFilename());
        model.addAttribute("photoFileLink", candidateDetailsDTO.getPhotoFilename());
        if (candidateDetailsDTO.getCandidateSkills() != null) {

            model.addAttribute("skillChecked", candidateDetailsDTO.getCandidateSkills()
                    .stream().map(skill -> skill.getSkillId().getSkillName()).toList());
        }

        List<SkillDTO> allSkills = skillService.findAll().stream().map(skillMapper::map).toList();
        model.addAttribute("allSkillsDTOs", allSkills);

        return "candidate/edit_profile";
    }

    private void setCandidatePhotoToModel(Model model, Optional<Candidate> candidate) {
        if (candidate.isPresent() && Objects.nonNull(candidate.get().getPhotoFilename())) {
            String photoPath = "/user_data/" + candidate.get().getCandidateUuid() + candidate.get().getPhotoFilename();
            model.addAttribute("photoDir", photoPath);
        } else {
            model.addAttribute("photoDir", "/img/user.jpg");
        }
    }

    @DeleteMapping(value = CANDIDATE_DELETE_CV_FILE)
    public String deleteCvFile(Model model, Authentication authentication) {
        User user = Utility.putUserDataToModel(authentication, userService, model)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        Candidate candidate = candidateService.findByCandidateUuid(user.getUserUuid())
                .orElseThrow(() -> new NotFoundException("Could not find candidate by uuid"));

        candidateService.deleteCvFile(candidate);
        return "redirect:../edit_profile?delete_cv_file";
    }

    @DeleteMapping(value = CANDIDATE_DELETE_PHOTO_FILE)
    public String deletePhotoFile(Model model, Authentication authentication) {
        User user = Utility.putUserDataToModel(authentication, userService, model)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        Candidate candidate = candidateService.findByCandidateUuid(user.getUserUuid())
                .orElseThrow(() -> new NotFoundException("Could not find candidate by uuid"));

        candidateService.deletePhotoFile(candidate);
        return "redirect:../edit_profile?delete_photo_file";
    }

    @PostMapping(value = CANDIDATE_UPDATE_PROFILE)
    public String updateCandidateProfile(@Valid @ModelAttribute("candidateRequestDTO") CandidateUpdateRequestDTO candidateUpdateRequestDTO,
                                         BindingResult bindingResult,
                                         Model model,
                                         Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "error";
        }
        User user = Utility.putUserDataToModel(authentication, userService, model)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        ;
        Optional<Candidate> candidate = candidateService.findByCandidateUuid(user.getUserUuid());

        CandidateUpdateRequest candidateUpdateRequest = candidateRequestMapper.map(candidateUpdateRequestDTO);
        if (candidate.isEmpty()) {
            log.info("Process new candidate profile");
            candidateService.newCandidateProfile(candidateUpdateRequest, user);
            return "redirect:profile?created";
        } else {
            log.info("Process update candidate profile");
            candidateService.updateCandidateProfile(candidateUpdateRequest, candidate.get());
            return "redirect:profile?updated";
        }

    }

    @DeleteMapping(value = CANDIDATE_DELETE_PROFILE + "/{candidateId}")
    public String deleteCandidateProfile(@PathVariable String candidateId) {
        log.info("Processing delete profile for candidate nr :" + candidateId);
        candidateService.deleteCandidateProfile(candidateId);
        return "redirect:../profile?delete";
    }

    @GetMapping("/download_cv_file")
    public ResponseEntity<Resource> downloadImage() {
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        ClassPathResource resource = new ClassPathResource("/user_data/5e8dd047-7b07-44fb-bbd6-1f15db0f6a8ccv.pdf");

        System.out.println(resource.getFilename());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"5e8dd047-7b07-44fb-bbd6-1f15db0f6a8ccv.pdf\"")
                .body(resource);
    }
}



