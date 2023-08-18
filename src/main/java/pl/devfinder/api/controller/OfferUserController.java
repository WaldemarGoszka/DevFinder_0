package pl.devfinder.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.devfinder.api.dto.*;
import pl.devfinder.api.dto.mapper.*;
import pl.devfinder.business.*;
import pl.devfinder.business.management.Keys;
import pl.devfinder.business.management.Utility;
import pl.devfinder.domain.Employer;
import pl.devfinder.domain.Offer;
import pl.devfinder.domain.OfferUpdateRequest;
import pl.devfinder.domain.User;
import pl.devfinder.domain.exception.NotFoundException;
import pl.devfinder.domain.search.OfferSearchCriteria;

import java.util.*;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping(OfferUserController.BASE_PATH)
public class OfferUserController {
    public static final String BASE_PATH = "/offers_of_employer";

    public static final String OFFERS_LIST = "/offers";
    public static final String OFFER_NEW_FORM = "/new_form";
    public static final String OFFER_CREATE_NEW = "/new";
    public static final String OFFER_DETAILS = "/offer/{offerId}";
    public static final String OFFER_EDIT_FORM = "/edit/{offerId}";
    public static final String OFFER_UPDATE = "/update";
    public static final String OFFER_DELETE = "/delete/{offerId}";

    private final UserService userService;
    private final EmployerService employerService;
    private final OfferService offerService;
    private final CityService cityService;
    private final CityMapper cityMapper;
    private final SkillService skillService;
    private final SkillMapper skillMapper;
    private final OfferDetailsMapper offerDetailsMapper;
    private final OfferRowMapper offerRowMapper;
    private final OfferUpdateRequestMapper offerUpdateRequestMapper;

    @GetMapping(value = OFFERS_LIST)
    public ModelAndView getOffersListToEmployer(
            @ModelAttribute OfferSearchCriteria offerSearchCriteria,
            Model model,
            Authentication authentication) {
        User user = Utility.putUserDataToModel(authentication, userService, model)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        Optional<Employer> employerOptional = employerService.findByEmployerUuid(user.getUserUuid());
        setEmployerLogoToModel(model, employerOptional);
        Employer employer = employerOptional.orElseThrow(() -> new NotFoundException("Could not find employer by uuid"));

        offerSearchCriteria.setEmployer(employer.getCompanyName());
        //offerSearchCriteria.setStatus(new ArrayList<>());

        Map<String, ?> offersListData = prepareOffersListData(offerSearchCriteria);

        return new ModelAndView("employer/offers", offersListData);
    }

    @GetMapping(value = OFFER_DETAILS)
    public String getOfferDetailsToEmployer(@PathVariable Long offerId, Model model, Authentication authentication) {
        User user = Utility.putUserDataToModel(authentication, userService, model)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        Optional<Employer> employerOptional = employerService.findByEmployerUuid(user.getUserUuid());
        setEmployerLogoToModel(model, employerOptional);
        Employer employer = employerOptional.orElseThrow(() -> new NotFoundException("Could not find employer by uuid"));


        OfferDetailsDTO offerDetailsDTO = offerDetailsMapper
                .map(offerService.findByOfferIdAndEmployerId(offerId, employer));
        model.addAttribute("offerDetailsDTO", offerDetailsDTO);

        return "employer/offer_details";
    }

    @GetMapping(value = OFFER_EDIT_FORM)
    public String getOfferEditForm(@PathVariable Long offerId, Model model, Authentication authentication) {
        User user = Utility.putUserDataToModel(authentication, userService, model)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        Optional<Employer> employerOptional = employerService.findByEmployerUuid(user.getUserUuid());
        setEmployerLogoToModel(model, employerOptional);
        Employer employer = employerOptional.orElseThrow(() -> new NotFoundException("Could not find employer by uuid"));

        OfferDetailsDTO offerDetailsDTO = offerDetailsMapper.map(offerService.findByOfferIdAndEmployerId(offerId, employer));
        prepareModelToEditOrNewOffer(model, offerDetailsDTO);

        return "employer/edit_offer";
    }

    @GetMapping(value = OFFER_NEW_FORM)
    public String getOfferNewForm(Model model, Authentication authentication) {
        User user = Utility.putUserDataToModel(authentication, userService, model)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        Optional<Employer> employerOptional = employerService.findByEmployerUuid(user.getUserUuid());
        if (employerOptional.isEmpty()) {
            return "redirect:../employer_portal/edit_profile?first_create_profile";
        }
        setEmployerLogoToModel(model, employerOptional);
//        Employer employer = employerOptional.orElseThrow(() -> new NotFoundException("Could not find employer by uuid"));

        OfferDetailsDTO offerDetailsDTO = new OfferDetailsDTO();
        prepareModelToEditOrNewOffer(model, offerDetailsDTO);

        return "employer/new_offer";
    }


    @PutMapping(value = OFFER_UPDATE)
    public String updateOfferRequest(@Valid @ModelAttribute OfferUpdateRequestDTO offerUpdateRequestDTO,
                                     Model model,
                                     Authentication authentication) {
        User user = Utility.putUserDataToModel(authentication, userService, model)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        Optional<Employer> employerOptional = employerService.findByEmployerUuid(user.getUserUuid());
        setEmployerLogoToModel(model, employerOptional);
        Employer employer = employerOptional.orElseThrow(() -> new NotFoundException("Could not find employer by uuid"));

        log.info("Process update existing offer");

        OfferUpdateRequest offerUpdateRequest = offerUpdateRequestMapper.map(offerUpdateRequestDTO);
        Offer offer = offerService.findById(offerUpdateRequestDTO.getOfferId());
        offerService.updateOffer(offerUpdateRequest, employer, offer);
        return "redirect:offers?updated";
    }

    @PostMapping(value = OFFER_CREATE_NEW)
    public String createNewOfferRequest(@Valid @ModelAttribute OfferUpdateRequestDTO offerUpdateRequestDTO,
                                        Model model,
                                        Authentication authentication) {
        User user = Utility.putUserDataToModel(authentication, userService, model)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        Optional<Employer> employerOptional = employerService.findByEmployerUuid(user.getUserUuid());
        setEmployerLogoToModel(model, employerOptional);
        Employer employer = employerOptional.orElseThrow(() -> new NotFoundException("Could not find employer by uuid"));

        log.info("Process create new offer");
        setDefaultStatusToNewOffer(offerUpdateRequestDTO);
        OfferUpdateRequest offerUpdateRequest = offerUpdateRequestMapper.map(offerUpdateRequestDTO);
        offerService.createNewOffer(offerUpdateRequest, employer);
        return "redirect:offers?created";
    }

    @DeleteMapping(value = OFFER_DELETE)
    public String deleteCandidateProfile(@PathVariable Long offerId, Model model, Authentication authentication) {
        User user = Utility.putUserDataToModel(authentication, userService, model)
                .orElseThrow(() -> new NotFoundException("Could not find user or authentication failed"));
        Optional<Employer> employerOptional = employerService.findByEmployerUuid(user.getUserUuid());
        setEmployerLogoToModel(model, employerOptional);
        Employer employer = employerOptional.orElseThrow(() -> new NotFoundException("Could not find employer by uuid"));

        log.info("Processing delete offer id :" + offerId);

        OfferDetailsDTO offerDetailsDTO = offerDetailsMapper.map(offerService.findByOfferIdAndEmployerId(offerId, employer));
        offerService.deleteOffer(offerId);
        return "redirect:../offers?delete";
    }


    private void setEmployerLogoToModel(Model model, Optional<Employer> employer) {
        if (employer.isPresent() && Objects.nonNull(employer.get().getLogoFilename())) {
            String logoPath = Utility.getUserPhotoPath(employer.get().getEmployerUuid(),employer.get().getLogoFilename());
            model.addAttribute("photoDir", logoPath);
        } else {
            model.addAttribute("photoDir", UserController.DEFAULT_PHOTO_PATH);
        }
    }

    private void prepareModelToEditOrNewOffer(Model model, OfferDetailsDTO offerDetailsDTO) {
        model.addAttribute("offerDetailsDTO", offerDetailsDTO);


        model.addAttribute("statusChecked", offerDetailsDTO.getStatus());

        model.addAttribute("statusEnumActive", Keys.OfferState.ACTIVE.getName());
        model.addAttribute("statusEnumExpired", Keys.OfferState.EXPIRED.getName());

        model.addAttribute("expLevelEnumJunior", Keys.Experience.JUNIOR.getName());
        model.addAttribute("expLevelEnumMid", Keys.Experience.MID.getName());
        model.addAttribute("expLevelEnumSenior", Keys.Experience.SENIOR.getName());

        if (offerDetailsDTO.getOfferSkills() != null) {
            model.addAttribute("skillChecked", offerDetailsDTO.getOfferSkills()
                    .stream().map(skill -> skill.getSkillId().getSkillName()).toList());
        }

        List<SkillDTO> allSkills = skillService.findAll().stream().map(skillMapper::map).toList();
        model.addAttribute("allSkillsDTOs", allSkills);
    }

    private Map<String, ?> prepareOffersListData(OfferSearchCriteria offerSearchCriteria) {
        Map<String, Object> model = new HashMap<>();

        Page<OfferRowDTO> page = offerService.findAllByCriteria(offerSearchCriteria).map(offerRowMapper::map);
        List<OfferRowDTO> allOffers = page.getContent();
        model.put("allOffersDTOs", allOffers);

        model.put("totalPages", page.getTotalPages());
        model.put("totalItems", page.getTotalElements());

        model.put("experienceLevelChecked", offerSearchCriteria.getExperienceLevels());
        model.put("skillChecked", offerSearchCriteria.getSkills());
        model.put("cityChecked", offerSearchCriteria.getCity());
        model.put("remoteChecked", offerSearchCriteria.getRemoteWork());
        model.put("salaryChecked", offerSearchCriteria.getSalary());
        model.put("statusChecked", offerSearchCriteria.getStatus());
        model.put("employerChecked", offerSearchCriteria.getEmployer());

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

        List<SkillDTO> allSkills = skillService.findAll().stream().map(skillMapper::map).toList();
        List<CityDTO> allCity = cityService.findAll().stream().map(cityMapper::map).toList();
        model.put("allSkillsDTOs", allSkills);
        model.put("allCityDTOs", allCity);

        model.put("sortBy", offerSearchCriteria.getSortBy());
        model.put("sortDirection", offerSearchCriteria.getSortDirection());
        model.put("reverseSortDirection", offerSearchCriteria.getSortDirection()
                .equals(Sort.Direction.ASC) ? Sort.Direction.DESC : Sort.Direction.ASC);

        return model;
    }

    private static void setDefaultStatusToNewOffer(OfferUpdateRequestDTO offerUpdateRequestDTO) {
        offerUpdateRequestDTO.setStatus(Keys.OfferState.ACTIVE.getName());
    }
}
