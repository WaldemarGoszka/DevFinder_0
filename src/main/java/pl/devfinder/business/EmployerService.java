package pl.devfinder.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.devfinder.business.dao.CandidateDAO;
import pl.devfinder.business.dao.EmployerDAO;
import pl.devfinder.domain.*;
import pl.devfinder.domain.exception.FileUploadToProfileException;
import pl.devfinder.domain.exception.NotFoundException;
import pl.devfinder.domain.search.EmployerSearchCriteria;
import pl.devfinder.infrastructure.database.repository.criteria.EmployerCriteriaRepository;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class EmployerService {

    private final EmployerDAO employerDAO;
    private final EmployerCriteriaRepository employerCriteriaRepository;
    private final CityService cityService;
    private final OfferService offerService;
    private final FileUploadService fileUploadService;
    private final OfferSkillService offerSkillService;
    private final DataService dataService;
    private final CandidateDAO candidateDAO;

    public Employer findById(Long employerId) {
        log.info("Process find employerById, id: [{}]", employerId);
        return employerDAO.findById(employerId).orElseThrow(() -> new NotFoundException(
                "Could not find employer by Id [%s]".formatted(employerId)));
    }

    @Transactional
    public List<Employer> findAll() {
        List<Employer> allEmployers = employerDAO.findAll();
        log.info("Find all employers, ammount: [{}]", allEmployers.size());
        return allEmployers;
    }

//    public void save(User user) {
//        Employer employer = Employer.builder()
//                .employerUuid(user.getUserUuid())
//                .createdAt(OffsetDateTime.now())
//                .build();
//        employerDAO.save(employer);
//    }

    public Page<Employer> findAllByCriteria(EmployerSearchCriteria employerSearchCriteria) {
        return employerCriteriaRepository.findAllByCriteria(employerSearchCriteria);
    }

    public Optional<Employer> findByEmployerUuid(String uuid) {
        log.info("Process finding employer by uuid: [{}]", uuid);
        return employerDAO.findByEmployerUuid(uuid);
    }

    public long countByCityName(String cityName) {
        return employerDAO.countByCityName(cityName);
    }

    @Transactional
    public void newEmployerProfile(EmployerUpdateRequest employerUpdateRequest, User user) {
        log.info("Process create new employer profile");
        if (cityService.nonCityExist(employerUpdateRequest.getCityName())) {
            cityService.save(employerUpdateRequest.getCityName());
        }
        City city = cityService.findByCityName(employerUpdateRequest.getCityName()).orElseThrow();

        Employer createNewEmployer = buildNewEmployer(employerUpdateRequest, user, city);

        employerDAO.save(createNewEmployer);
    }

    private Employer buildNewEmployer(EmployerUpdateRequest employerUpdateRequest, User user, City city) {
        return Employer.builder()
                .employerUuid(user.getUserUuid())
                .createdAt(OffsetDateTime.now())
                .companyName(employerUpdateRequest.getCompanyName())
                .emailContact(employerUpdateRequest.getEmailContact())
                .phoneNumber(employerUpdateRequest.getPhoneNumber())
                .description(employerUpdateRequest.getDescription())
                .logoFilename(employerUpdateRequest.getLogoFilename())
                .website(employerUpdateRequest.getWebsite())
                .numberOfEmployees(employerUpdateRequest.getNumberOfEmployees())
                .amountOfAvailableOffers(employerUpdateRequest.getAmountOfAvailableOffers())
                .logoFilename(processNewLogoFile(employerUpdateRequest, user))
                .cityId(city)
                .build();
    }

    private String processNewLogoFile(EmployerUpdateRequest employerUpdateRequest, User user) {
        if (!employerUpdateRequest.getFileLogo().isEmpty()) {
            log.info("Process upload new logo file : [{}]", employerUpdateRequest.getFileLogo().getOriginalFilename());
            return fileUploadService.saveFileToDisc(user.getUserUuid(), employerUpdateRequest.getFileLogo());
        } else {
            return null;
        }

    }

    @Transactional
    public void updateEmployerProfile(EmployerUpdateRequest employerUpdateRequest, Employer employer) {
        log.info("Process update employer profile");
        if (cityService.nonCityExist(employerUpdateRequest.getCityName())) {
            cityService.save(employerUpdateRequest.getCityName());
        }
        City city = cityService.findByCityName(employerUpdateRequest.getCityName()).orElseThrow();

        Employer updateEmployer = buildUpdatedEmployer(employerUpdateRequest, employer, city);

        employerDAO.save(updateEmployer);
    }

    private Employer buildUpdatedEmployer(EmployerUpdateRequest employerUpdateRequest, Employer employer, City city) {
        return Employer.builder()
                .employerId(employer.getEmployerId())
                .employerUuid(employer.getEmployerUuid())
                .createdAt(employer.getCreatedAt())
                .companyName(employerUpdateRequest.getCompanyName())
                .emailContact(employerUpdateRequest.getEmailContact())
                .phoneNumber(employerUpdateRequest.getPhoneNumber())
                .description(employerUpdateRequest.getDescription())
                .logoFilename(employerUpdateRequest.getLogoFilename())
                .website(employerUpdateRequest.getWebsite())
                .numberOfEmployees(employerUpdateRequest.getNumberOfEmployees())
                .amountOfAvailableOffers(employer.getAmountOfAvailableOffers())
                .logoFilename(processUpdateLogoFile(employerUpdateRequest, employer))
                .cityId(city)
                .build();
    }

    private String processUpdateLogoFile(EmployerUpdateRequest employerUpdateRequest, Employer employer) {
        if (!employerUpdateRequest.getFileLogo().isEmpty()) {
            log.info("Process upload update photo file : [{}]", employerUpdateRequest.getFileLogo().getOriginalFilename());
            try {
                if (fileUploadService.oldFileExist(employer.getEmployerUuid(), employer.getLogoFilename())) {
                    fileUploadService.deleteFileFromDisc(employer.getEmployerUuid(), employer.getLogoFilename());
                }
            } catch (IOException e) {
                log.error("Could not delete file: [{}]", employer.getLogoFilename());
                throw new FileUploadToProfileException("Could not delete file: " + employer.getLogoFilename());
            }
            return fileUploadService.saveFileToDisc(employer.getEmployerUuid(), employerUpdateRequest.getFileLogo());
        } else {
            return employer.getLogoFilename();
        }

    }

    @Transactional
    public void deleteEmployerProfile(Long employerId) {
        log.info("Process delete employer profile, employerId: [{}]", employerId);
        Employer employer = this.findById(employerId);

        if (Objects.nonNull(employer.getLogoFilename())) {
            fileUploadService.deleteFileFromDisc(employer.getEmployerUuid(), employer.getLogoFilename());
        }

        List<Offer> offers = offerService.findAllByEmployer(employer);
        offers.forEach(offerSkillService::deleteAllByOffer);
        deleteEmployerFromAllCandidatesAndChangeStatus(employer);
        offerService.deleteAllByEmployerId(employer);
        employerDAO.deleteById(employerId);
        if (dataService.countUsesOfCityName(employer.getCityId().getCityName()) <= 1) {
            log.info("City No Longer needed. Delete city : [{}]", employer.getCityId().getCityName());
            cityService.deleteByCityName(employer.getCityId().getCityName());
        }
    }

    private void deleteEmployerFromAllCandidatesAndChangeStatus(Employer employer) {
        log.info("Process delete all employer from all candidates");
        employerDAO.deleteEmployerFromAllCandidatesAndChangeStatus(employer);
    }

    public void deleteLogoFile(Employer employer) {
        log.info("Process delete logo file");
        fileUploadService.deleteFileFromDisc(employer.getEmployerUuid(), employer.getLogoFilename());
        Employer updateEmployer = employer.withLogoFilename(null);
        employerDAO.save(updateEmployer);
    }

    @Transactional
    public void hireCandidate(Employer employer, Long candidateId) {
        log.info("Process hire candidate. Assign employer, id: [{}] to candidate, id: [{}] ", employer.getEmployerId(), candidateId);
        employerDAO.assignEmployerToCandidateAndChangeStatus(employer, candidateId);
    }

    @Transactional
    public void fireCandidate(Long candidateId) {
        log.info("Process fire employer. Delete assign employer to candidate, id: [{}] ", candidateId);
        employerDAO.deleteAssignEmployerToCandidateAndChangeStatus(candidateId);
    }
}
