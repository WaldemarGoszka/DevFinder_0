package pl.devfinder.business;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.devfinder.business.dao.CandidateDAO;
import pl.devfinder.business.management.Keys;
import pl.devfinder.business.management.Utility;
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

    public List<Candidate> findAllByState(Keys.CandidateState state) {
        List<Candidate> allAvailableCandidates = candidateDAO.findAllByState(state);
        log.info("Available Candidate amount: [{}]", allAvailableCandidates.size());
        return allAvailableCandidates;
    }

//    public void save(User user) {
//        Candidate candidate = Candidate.builder()
//                .candidateUuid(user.getUserUuid())
//                .createdAt(OffsetDateTime.now())
//                .status(Keys.CandidateState.ACTIVE.getName())
//                .build();
//        candidateDAO.save(candidate);
//    }

    public Page<Candidate> findAllByCriteria(CandidateSearchCriteria candidateSearchCriteria) {
        return candidateCriteriaRepository.findAllByCriteria(candidateSearchCriteria);
    }


    public Candidate findById(Long candidateId) {
        log.info("Trying find candidateById, id: [{}]", candidateId);
        return candidateDAO.findById(candidateId).orElseThrow(() -> new NotFoundException(
                "Could not find candidate by Id [%s]".formatted(candidateId)));
    }

    public Optional<Candidate> findByCandidateUuid(String uuid) {
        return candidateDAO.findByCandidateUuid(uuid);
    }


    public void updateCustomer(Candidate candidate) {
        log.info("Update candidate nr: [{}]", candidate.getCandidateId());
        candidateDAO.save(candidate);
    }

    @Transactional
    public void updateCandidateProfile(CandidateUpdateRequest candidateUpdateRequest, Candidate candidate) {
        if(nonCityExist(candidateUpdateRequest.getResidenceCityName())) {
            cityService.save(candidateUpdateRequest.getResidenceCityName());
        }
        City city = cityService.findByCityName(candidateUpdateRequest.getResidenceCityName()).orElseThrow();

        validateIfSkillsExist(candidateUpdateRequest.getNameCandidateSkills());
        candidateSkillService.deleteAllByCandidate(candidate);

        Candidate updateCandidate = buildUpdatedCandidate(candidateUpdateRequest, candidate, city);

        Set<CandidateSkill> candidateSkillToSave = buildCandidateSkills(updateCandidate, candidateUpdateRequest);
        candidateSkillService.saveAll(candidateSkillToSave);
        candidateDAO.save(updateCandidate);
    }

    private Candidate buildUpdatedCandidate(CandidateUpdateRequest candidateUpdateRequest, Candidate candidate, City city) {
        return Candidate.builder()
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
                .cvFilename(processCvFile(candidateUpdateRequest, candidate))
                .photoFilename(processPhotoFile(candidateUpdateRequest, candidate))
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
    public void newCandidateProfile(CandidateUpdateRequest candidateUpdateRequest, Candidate candidate, User user) {
        if(nonCityExist(candidateUpdateRequest.getResidenceCityName())) {
            cityService.save(candidateUpdateRequest.getResidenceCityName());
        }
        City city = cityService.findByCityName(candidateUpdateRequest.getResidenceCityName()).orElseThrow();

        validateIfSkillsExist(candidateUpdateRequest.getNameCandidateSkills());
        candidateSkillService.deleteAllByCandidate(candidate);

        Candidate createNewCandidate = buildNewCandidate(candidateUpdateRequest, candidate, user, city);

        Set<CandidateSkill> candidateSkillToSave = buildCandidateSkills(createNewCandidate, candidateUpdateRequest);
        candidateSkillService.saveAll(candidateSkillToSave);
        candidateDAO.save(createNewCandidate);

    }

    private Candidate buildNewCandidate(CandidateUpdateRequest candidateUpdateRequest, Candidate candidate, User user, City city) {
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
                .cvFilename(processCvFile(candidateUpdateRequest, candidate))
                .photoFilename(processPhotoFile(candidateUpdateRequest, candidate))
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

    private Employer buildEmployer(Long employerId) {

        if (Objects.nonNull(employerId)) {
            return employerService.findById(employerId);
        }
        return null;
    }

    private void validateIfSkillsExist(Collection<String> skills) {
        skills.forEach(skillName -> skillService.findBySkillName(skillName)
                .orElseThrow(() -> new NotFoundException("Could not find skill, skillName: " + skillName)));
    }

    private Set<CandidateSkill> buildCandidateSkills(Candidate candidateToSave, CandidateUpdateRequest candidateUpdateRequest) {
        Set<CandidateSkill> candidateSkillToSave = new HashSet<>();
        candidateUpdateRequest.getNameCandidateSkills()
                .forEach(skillName -> candidateSkillToSave.add(CandidateSkill.builder()
                        .candidateId(candidateToSave)
                        .skillId(Skill.builder()
                                .skillName(skillName)
                                .build())
                        .build()));
        return candidateSkillToSave;
    }


    private Boolean nonCityExist(String cityName) {
        Optional<City> city = cityService.findByCityName(cityName);
        return city.isEmpty();
    }


    private static String processCvFile(CandidateUpdateRequest candidateUpdateRequest, Candidate candidate) {
        if (Objects.nonNull(candidateUpdateRequest.getFileCv())) {
            try {
                if (oldFileExist(candidate.getCandidateUuid(), candidate.getCvFilename())) {
                    deleteFileFromDisc(candidate.getCandidateUuid(), candidate.getCvFilename());
                }
            } catch (IOException e) {
                throw new FileUploadToProfileException("Could not delete file: " + candidate.getCvFilename());
            }
            return saveFileToDisc(candidate.getCandidateUuid(), candidateUpdateRequest.getFileCv());
        } else {
            return candidate.getCvFilename();
        }
    }

    private static String processPhotoFile(CandidateUpdateRequest candidateUpdateRequest, Candidate candidate) {
        if (Objects.nonNull(candidateUpdateRequest.getFilePhoto())) {
            try {
                if (oldFileExist(candidate.getCandidateUuid(), candidate.getPhotoFilename())) {
                    deleteFileFromDisc(candidate.getCandidateUuid(), candidate.getPhotoFilename());
                }
            } catch (IOException e) {
                throw new FileUploadToProfileException("Could not delete file: " + candidate.getPhotoFilename());
            }
            return saveFileToDisc(candidate.getCandidateUuid(), candidateUpdateRequest.getFilePhoto());
        } else {
            return candidate.getPhotoFilename();
        }
    }

    private static String saveFileToDisc(String candidateUuid, MultipartFile file) {

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

    private static String getUploadDir(String candidateUuid, String filename) throws IOException {
        return new ClassPathResource(Utility.USER_DATA_DIR).getFile().toPath() +
                File.separator +
                candidateUuid +
                filename;
    }

    private static void deleteFileFromDisc(String uuid, String filename) throws IOException {
        Files.deleteIfExists(Paths.get(getUploadDir(uuid, filename)));
    }

    private static boolean oldFileExist(String uuid, String filename) throws IOException {
        System.out.println("***********************upload dir " + getUploadDir(uuid, filename));
        return Files.exists(Paths.get(getUploadDir(uuid, filename)));
    }

}
