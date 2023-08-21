package pl.devfinder.business;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.devfinder.business.dao.OfferDAO;
import pl.devfinder.business.management.Keys;
import pl.devfinder.business.management.Utility;
import pl.devfinder.domain.*;
import pl.devfinder.domain.exception.NotFoundException;
import pl.devfinder.domain.search.OfferSearchCriteria;
import pl.devfinder.infrastructure.database.repository.criteria.OfferCriteriaRepository;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class OfferService {
    private final OfferDAO offerDAO;
    private final OfferCriteriaRepository offerCriteriaRepository;
    private final DataService dataService;
    private final CityService cityService;
    private final OfferSkillService offerSkillService;
    private final SkillService skillService;

    public long countByCityName(String cityName) {
        return offerDAO.countByCityName(cityName);
    }


    public Page<Offer> findAllByCriteria(OfferSearchCriteria offerSearchCriteria) {
        return offerCriteriaRepository.findAllByCriteria(offerSearchCriteria);
    }


    public String getDaysSinceDate(OffsetDateTime createdAt) {
        long days = Duration.between(createdAt, OffsetDateTime.now()).toDays();
        return String.valueOf(days);
    }

    public String formatSalaryRange(BigDecimal salaryMin, BigDecimal salaryMax) {
        if (Objects.nonNull(salaryMin) && Objects.nonNull(salaryMax)) {
            return salaryMin.toBigInteger() + " - " + salaryMax.toBigInteger();
        }
        return Keys.Salary.UNDISCLOSED.getName();
    }

    public String formatRemoteWork(Integer remote) {
        if (remote == null || remote == 0) {
            return "No";
        }
        return remote + " % time";
    }

    public Offer findById(Long offerId) {
        log.info("Process find offerById, id: [{}]", offerId);
        return offerDAO.findById(offerId).orElseThrow(() -> new NotFoundException(
                "Could not find offer by Id [%s]".formatted(offerId)));
    }


    public Offer findByOfferIdAndEmployerId(Long offerId, Employer employer) {
        log.info("Process finding offer id: [{}] where employer id is: [{}]", offerId, employer.getEmployerId());
        return offerDAO.findByOfferIdAndEmployerId(offerId, employer).orElseThrow(() -> new NotFoundException(
                "Could not find offer by Id or you dont have permissions to see this offer[%s]".formatted(offerId)));
    }

    @Transactional
    public void updateOffer(OfferUpdateRequest offerUpdateRequest, Employer employer, Offer offer) {
        if (cityService.nonCityExist(offerUpdateRequest.getCityName())) {
            cityService.save(offerUpdateRequest.getCityName());
        }
        City city = cityService.findByCityName(offerUpdateRequest.getCityName()).orElseThrow();

        offerSkillService.deleteAllByOffer(offer);

        Offer updateOffer = buildUpdatedOffer(offerUpdateRequest, city, offer);
        if (Objects.nonNull(offerUpdateRequest.getOfferSkillsNames())) {
            Set<OfferSkill> offerSkillToSave = buildOfferSkills(updateOffer, offerUpdateRequest);
            offerSkillService.saveAll(offerSkillToSave);
        }

        if (dataService.countUsesOfCityName(offer.getCityId().getCityName()) <= 1) {
            log.info("City No Longer needed. Delete city : [{}]", offer.getCityId().getCityName());
            cityService.deleteByCityName(offer.getCityId().getCityName());
        }
        offerDAO.save(updateOffer);
    }

    private Offer buildUpdatedOffer(OfferUpdateRequest offerUpdateRequest, City city, Offer offer) {
        return Offer.builder()
                .offerId(offer.getOfferId())
                .offerUuid(offer.getOfferUuid())
                .createdAt(offer.getCreatedAt())
                .title(offerUpdateRequest.getTitle())
                .description(offerUpdateRequest.getDescription())
                .otherSkills(offerUpdateRequest.getOtherSkills())
                .remoteWork(offerUpdateRequest.getRemoteWork())
                .experienceLevel(offerUpdateRequest.getExperienceLevel())
                .yearsOfExperience(offerUpdateRequest.getYearsOfExperience())
                .salaryMin(offerUpdateRequest.getSalaryMin())
                .salaryMax(offerUpdateRequest.getSalaryMax())
                .benefits(offerUpdateRequest.getBenefits())
                .status(offerUpdateRequest.getStatus())
                .cityId(city)
                .employerId(offer.getEmployerId())
                .build();
    }

    private Set<OfferSkill> buildOfferSkills(Offer offerToSave, OfferUpdateRequest offerUpdateRequest) {
        Set<OfferSkill> offerSkillToSave = new HashSet<>();
        offerUpdateRequest.getOfferSkillsNames()
                .forEach(skillName -> offerSkillToSave.add(OfferSkill.builder()
                        .offerId(offerToSave)
                        .skillId(skillService.buildSkill(skillName))
                        .build()));
        return offerSkillToSave;

    }

    @Transactional
    public void createNewOffer(OfferUpdateRequest offerUpdateRequest, Employer employer) {
        if (cityService.nonCityExist(offerUpdateRequest.getCityName())) {
            cityService.save(offerUpdateRequest.getCityName());
        }
        City city = cityService.findByCityName(offerUpdateRequest.getCityName()).orElseThrow();

        Offer createNewOffer = buildNewOffer(offerUpdateRequest, employer, city);
        Offer savedOffer = offerDAO.save(createNewOffer);
        if (Objects.nonNull(offerUpdateRequest.getOfferSkillsNames())) {
            Set<OfferSkill> offerSkillToSave = buildOfferSkills(savedOffer, offerUpdateRequest);
            offerSkillService.saveAll(offerSkillToSave);
        }
    }

    private Offer buildNewOffer(OfferUpdateRequest offerUpdateRequest, Employer employer, City city) {
        return Offer.builder()
                .offerUuid(Utility.generateUUID())
                .createdAt(OffsetDateTime.now())
                .title(offerUpdateRequest.getTitle())
                .description(offerUpdateRequest.getDescription())
                .otherSkills(offerUpdateRequest.getOtherSkills())
                .remoteWork(offerUpdateRequest.getRemoteWork())
                .experienceLevel(offerUpdateRequest.getExperienceLevel())
                .yearsOfExperience(offerUpdateRequest.getYearsOfExperience())
                .salaryMin(offerUpdateRequest.getSalaryMin())
                .salaryMax(offerUpdateRequest.getSalaryMax())
                .benefits(offerUpdateRequest.getBenefits())
                .status(offerUpdateRequest.getStatus())
                .cityId(city)
                .employerId(employer)
                .build();
    }

    public void deleteAllByEmployerId(Employer employer) {
        log.info("Process delete all offers belongs to employer id : [{}]", employer.getEmployerId());
        offerDAO.deleteAllByEmployerId(employer);
    }

    public List<Offer> findAllByEmployer(Employer employer) {
        return offerDAO.findAllByEmployer(employer);
    }

    @Transactional
    public void deleteOffer(Long offerId) {
        log.info("Process delete offer, id : [{}]", offerId);

        Offer offer = this.findById(offerId);

        offerSkillService.deleteAllByOffer(offer);
        offerDAO.deleteById(offerId);
        if (dataService.countUsesOfCityName(offer.getCityId().getCityName()) <= 1) {
            log.info("City No Longer needed. Delete city : [{}]", offer.getCityId().getCityName());
            cityService.deleteByCityName(offer.getCityId().getCityName());
        }
    }
}
