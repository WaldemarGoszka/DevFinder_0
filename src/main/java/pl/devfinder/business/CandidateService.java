package pl.devfinder.business;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.devfinder.business.dao.CandidateDAO;
import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.*;
import pl.devfinder.domain.exception.FileUploadToProfileException;
import pl.devfinder.domain.exception.NotFoundException;
import pl.devfinder.domain.search.CandidateSearchCriteria;
import pl.devfinder.infrastructure.database.repository.criteria.CandidateCriteriaRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.OffsetDateTime;
import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
public class CandidateService {
    private final CandidateDAO candidateDAO;
    private final CandidateCriteriaRepository candidateCriteriaRepository;
    private final CityService cityService;
    private final SkillService skillService;
    private final CandidateSkillService candidateSkillService;
    private final EmployerService employerService;
    private final OfferService offerService;

    public List<Candidate> findAllByState(Keys.CandidateState state) {
        List<Candidate> allAvailableCandidates = candidateDAO.findAllByState(state);
        log.info("Available Candidate amount: [{}]", allAvailableCandidates.size());
        return allAvailableCandidates;
    }


    public Page<Candidate> findAllByCriteria(CandidateSearchCriteria candidateSearchCriteria) {
        return candidateCriteriaRepository.findAllByCriteria(candidateSearchCriteria);
    }


    public Candidate findById(Long candidateId) {
        log.info("Trying find candidateById, id: [{}]", candidateId);
        return candidateDAO.findById(candidateId).orElseThrow(() -> new NotFoundException(
                "Could not find candidate by Id [%s]".formatted(candidateId)));
    }

    public Optional<Candidate> findByCandidateUuid(String uuid) {
        log.info("Process finding candidate by uuid: [{}]", uuid);
        return candidateDAO.findByCandidateUuid(uuid);
    }


//    public void updateCustomer(Candidate candidate) {
//        log.info("Update candidate nr: [{}]", candidate.getCandidateId());
//        candidateDAO.save(candidate);
//    }

    @Transactional
    public void updateCandidateProfile(CandidateUpdateRequest candidateUpdateRequest, Candidate candidate) {
        if (nonCityExist(candidateUpdateRequest.getResidenceCityName())) {
            cityService.save(candidateUpdateRequest.getResidenceCityName());
        }
        City city = cityService.findByCityName(candidateUpdateRequest.getResidenceCityName()).orElseThrow();

        validateIfSkillsExist(candidateUpdateRequest.getCandidateSkillsNames());
        candidateSkillService.deleteAllByCandidate(candidate);

        Candidate updateCandidate = buildUpdatedCandidate(candidateUpdateRequest, candidate, city);
        if (Objects.nonNull(candidateUpdateRequest.getCandidateSkillsNames())) {
            Set<CandidateSkill> candidateSkillToSave = buildCandidateSkills(updateCandidate, candidateUpdateRequest);
            candidateSkillService.saveAll(candidateSkillToSave);
        }
        candidateDAO.save(updateCandidate);
    }

    private Candidate buildUpdatedCandidate(CandidateUpdateRequest candidateUpdateRequest, Candidate candidate, City city) {
        return Candidate.builder()
                .candidateId(candidate.getCandidateId())
                .candidateUuid(candidate.getCandidateUuid())
                .createdAt(candidate.getCreatedAt())
                .firstName(candidateUpdateRequest.getFirstName())
                .lastName(candidateUpdateRequest.getLastName())
                .emailContact(candidateUpdateRequest.getEmailContact())
                .phoneNumber(candidateUpdateRequest.getPhoneNumber())
                .status(candidateUpdateRequest.getStatus())
                .education(candidateUpdateRequest.getEducation())
                .otherSkills(candidateUpdateRequest.getOtherSkills())
                .hobby(candidateUpdateRequest.getHobby())
                .foreignLanguage(candidateUpdateRequest.getForeignLanguage())
                .cvFilename(processUpdateCvFile(candidateUpdateRequest, candidate))
                .photoFilename(processUpdatePhotoFile(candidateUpdateRequest, candidate))
                .githubLink(candidateUpdateRequest.getGithubLink())
                .linkedinLink(candidateUpdateRequest.getLinkedinLink())
                .experienceLevel(candidateUpdateRequest.getExperienceLevel())
                .yearsOfExperience(candidateUpdateRequest.getYearsOfExperience())
                .salaryMin(candidateUpdateRequest.getSalaryMin())
                .openToRemoteJob(candidateUpdateRequest.getOpenToRemoteJob())
                .employer(buildEmployer(candidateUpdateRequest.getCandidateId()))
                .residenceCityId(city)
                .build();
    }

    @Transactional
    public void newCandidateProfile(CandidateUpdateRequest candidateUpdateRequest, User user) {
        if (nonCityExist(candidateUpdateRequest.getResidenceCityName())) {
            cityService.save(candidateUpdateRequest.getResidenceCityName());
        }
        City city = cityService.findByCityName(candidateUpdateRequest.getResidenceCityName()).orElseThrow();

        validateIfSkillsExist(candidateUpdateRequest.getCandidateSkillsNames());
        Candidate createNewCandidate = buildNewCandidate(candidateUpdateRequest, user, city);

        if (Objects.nonNull(candidateUpdateRequest.getCandidateSkillsNames())) {
            Set<CandidateSkill> candidateSkillToSave = buildCandidateSkills(createNewCandidate, candidateUpdateRequest);
            candidateSkillService.saveAll(candidateSkillToSave);
        }
        candidateDAO.save(createNewCandidate);

    }

    private Candidate buildNewCandidate(CandidateUpdateRequest candidateUpdateRequest, User user, City city) {
        return Candidate.builder()
                .candidateUuid(user.getUserUuid())
                .createdAt(OffsetDateTime.now())
                .firstName(candidateUpdateRequest.getFirstName())
                .lastName(candidateUpdateRequest.getLastName())
                .emailContact(candidateUpdateRequest.getEmailContact())
                .phoneNumber(candidateUpdateRequest.getPhoneNumber())
                .status(candidateUpdateRequest.getStatus())
                .education(candidateUpdateRequest.getEducation())
                .otherSkills(candidateUpdateRequest.getOtherSkills())
                .hobby(candidateUpdateRequest.getHobby())
                .foreignLanguage(candidateUpdateRequest.getForeignLanguage())
                .cvFilename(processNewCvFile(candidateUpdateRequest, user))
                .photoFilename(processNewPhotoFile(candidateUpdateRequest, user))
                .githubLink(candidateUpdateRequest.getGithubLink())
                .linkedinLink(candidateUpdateRequest.getLinkedinLink())
                .experienceLevel(candidateUpdateRequest.getExperienceLevel())
                .yearsOfExperience(candidateUpdateRequest.getYearsOfExperience())
                .salaryMin(candidateUpdateRequest.getSalaryMin())
                .openToRemoteJob(candidateUpdateRequest.getOpenToRemoteJob())
                .employer(buildEmployer(candidateUpdateRequest.getCandidateId()))
                .residenceCityId(city)
                .build();
    }

    private String processNewPhotoFile(CandidateUpdateRequest candidateUpdateRequest, User user) {
        if (!candidateUpdateRequest.getFileCv().isEmpty()) {
            log.info("Process update cv file : [{}]", candidateUpdateRequest.getFilePhoto().getOriginalFilename());
            return saveFileToDisc(user.getUserUuid(), candidateUpdateRequest.getFilePhoto());
        } else {
            return null;
        }

    }

    private String processNewCvFile(CandidateUpdateRequest candidateUpdateRequest, User user) {
        if (!candidateUpdateRequest.getFileCv().isEmpty()) {
            log.info("Process new cv file : [{}]", candidateUpdateRequest.getFileCv().getOriginalFilename());
            return saveFileToDisc(user.getUserUuid(), candidateUpdateRequest.getFileCv());
        } else {
            return null;
        }

    }

    private Employer buildEmployer(Long employerId) {

        if (Objects.nonNull(employerId)) {
            return employerService.findById(employerId);
        }
        return null;
    }

    private void validateIfSkillsExist(Collection<String> skills) {
        if (Objects.nonNull(skills)) {
            skills.forEach(skillName -> skillService.findBySkillName(skillName)
                    .orElseThrow(() -> new NotFoundException("Could not find skill, skillName: " + skillName)));
        }
    }

    private Set<CandidateSkill> buildCandidateSkills(Candidate candidateToSave, CandidateUpdateRequest candidateUpdateRequest) {
        Set<CandidateSkill> candidateSkillToSave = new HashSet<>();
        candidateUpdateRequest.getCandidateSkillsNames()
                .forEach(skillName -> candidateSkillToSave.add(CandidateSkill.builder()
                        .candidateId(candidateToSave)
                        .skillId(skillService.buildSkill(skillName))
                        .build()));
        return candidateSkillToSave;
    }


    private Boolean nonCityExist(String cityName) {
        Optional<City> city = cityService.findByCityName(cityName);
        return city.isEmpty();
    }


    private String processUpdateCvFile(CandidateUpdateRequest candidateUpdateRequest, Candidate candidate) {
        if (!candidateUpdateRequest.getFileCv().isEmpty()) {
            log.info("Process update cv file : [{}]", candidateUpdateRequest.getFileCv().getOriginalFilename());
            try {
                if (oldFileExist(candidate.getCandidateUuid(), candidate.getCvFilename())) {
                    deleteFileFromDisc(candidate.getCandidateUuid(), candidate.getCvFilename());
                }
            } catch (IOException e) {
                log.error("Could not delete file: [{}]", candidate.getCvFilename());
                //throw new FileUploadToProfileException("Could not delete file: " + candidate.getCvFilename());
            }
            return saveFileToDisc(candidate.getCandidateUuid(), candidateUpdateRequest.getFileCv());
        } else {
            return candidate.getCvFilename();
        }
    }

    private  String processUpdatePhotoFile(CandidateUpdateRequest candidateUpdateRequest, Candidate candidate) {
        if (!candidateUpdateRequest.getFilePhoto().isEmpty()) {
            log.info("Process update photo file : [{}]", candidateUpdateRequest.getFilePhoto().getOriginalFilename());
            try {
                if (oldFileExist(candidate.getCandidateUuid(), candidate.getPhotoFilename())) {
                    deleteFileFromDisc(candidate.getCandidateUuid(), candidate.getPhotoFilename());
                }
            } catch (IOException e) {
                log.error("Could not delete file: [{}]", candidate.getCvFilename());
                //throw new FileUploadToProfileException("Could not delete file: " + candidate.getPhotoFilename());
            }
            return saveFileToDisc(candidate.getCandidateUuid(), candidateUpdateRequest.getFilePhoto());
        } else {
            return candidate.getPhotoFilename();
        }
    }

    private  String saveFileToDisc(String candidateUuid, MultipartFile file) {

        try {
            String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

            System.out.println("saveFileToDisc " + filename);
            System.out.println("saveFileToDisc 2 " + file.getOriginalFilename());

            String uploadDir = getUploadDir(candidateUuid, filename);
            log.info("Trying copy file to [{}] ", uploadDir);

            Files.copy(file.getInputStream(), Paths.get(uploadDir), StandardCopyOption.REPLACE_EXISTING);

            return filename;

        } catch (IOException e) {
            throw new FileUploadToProfileException(
                    "Could not save file to disc: [%s]".formatted(file.getOriginalFilename()));
        }
    }

    private String getUploadDir(String candidateUuid, String filename) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();

        File userDataDirectory = new File(Objects.requireNonNull(classLoader.getResource("user_data")).getFile());

        if (!userDataDirectory.exists()) {
            userDataDirectory.mkdir();
        }
        String userDataFolderPath = userDataDirectory.getAbsolutePath();

        String filePath = userDataFolderPath + File.separator + candidateUuid + filename;
        return filePath;
//                return createDirectoryAndReturnPath(filePath);

    }

    private  void deleteFileFromDisc(String uuid, String filename) throws IOException {
        log.info("Process delete file from disc, direction: [{}]", getUploadDir(uuid, filename));
        Files.deleteIfExists(Paths.get(getUploadDir(uuid, filename)));
    }

    private  boolean oldFileExist(String uuid, String filename) throws IOException {
        log.info("Checked if file exist in diresction: [{}]", getUploadDir(uuid, filename));
        return Files.exists(Paths.get(getUploadDir(uuid, filename)));
    }

    @Transactional
    public void deleteCandidateProfile(String candidateId) {
        Candidate candidate = this.findById(Long.valueOf(candidateId));
        if (countUsesOfCityName(candidate) <= 1) {
            log.info("City No Longer nessesery. Delete city : [{}]", candidate.getResidenceCityId().getCityName());
            cityService.deleteByCityName(candidate.getResidenceCityId().getCityName());
        }
        candidateSkillService.deleteAllByCandidate(candidate);
        log.info("Delete candidate profile, candidateId: [{}]", candidate.getCandidateId());
        candidateDAO.deleteById(Long.valueOf(candidateId));
    }

    private long countUsesOfCityName(Candidate candidate) {
        long counter = this.countByCityName(candidate.getResidenceCityId().getCityName());
        counter += offerService.countByCityName(candidate.getResidenceCityId().getCityName());
        counter += employerService.countByCityName(candidate.getResidenceCityId().getCityName());
        return counter;
        //TODO przetestować dodanie miasta i usunięcie gdy nie jesyt już wużywane
    }

    public long countByCityName(String cityName) {
        return candidateDAO.countByCityName(cityName);
    }
}
