package pl.devfinder.business;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pl.devfinder.business.dao.CandidateDAO;
import pl.devfinder.domain.*;
import pl.devfinder.domain.exception.FileUploadToProfileException;
import pl.devfinder.domain.exception.NotFoundException;
import pl.devfinder.domain.search.CandidateSearchCriteria;
import pl.devfinder.infrastructure.database.repository.criteria.CandidateCriteriaRepository;

import java.io.IOException;
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
    private final FileUploadService fileUploadService;
    private final DataService dataService;


    public Page<Candidate> findAllByCriteria(CandidateSearchCriteria candidateSearchCriteria) {
        return candidateCriteriaRepository.findAllByCriteria(candidateSearchCriteria);
    }


    public Candidate findById(Long candidateId) {
        log.info("Process find candidateById, id: [{}]", candidateId);
        return candidateDAO.findById(candidateId).orElseThrow(() -> new NotFoundException(
                "Could not find candidate by Id [%s]".formatted(candidateId)));
    }

    public Optional<Candidate> findByCandidateUuid(String uuid) {
        log.info("Process finding candidate by uuid: [{}]", uuid);
        return candidateDAO.findByCandidateUuid(uuid);
    }

    @Transactional
    public Candidate updateCandidateProfile(CandidateUpdateRequest candidateUpdateRequest, Candidate candidate) {
        if (cityService.nonCityExist(candidateUpdateRequest.getResidenceCityName())) {
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
        if (dataService.countUsesOfCityName(candidate.getResidenceCityId().getCityName()) <= 1) {
            log.info("City No Longer needed. Delete city : [{}]", candidate.getResidenceCityId().getCityName());
            cityService.deleteByCityName(candidate.getResidenceCityId().getCityName());
        }
        return candidateDAO.save(updateCandidate);
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
                .employerId(buildEmployer(candidateUpdateRequest.getEmployerName()))
                .residenceCityId(city)
                .build();
    }

    @Transactional
    public Candidate newCandidateProfile(CandidateUpdateRequest candidateUpdateRequest, User user) {
        if (cityService.nonCityExist(candidateUpdateRequest.getResidenceCityName())) {
            cityService.save(candidateUpdateRequest.getResidenceCityName());
        }
        City city = cityService.findByCityName(candidateUpdateRequest.getResidenceCityName()).orElseThrow();

        validateIfSkillsExist(candidateUpdateRequest.getCandidateSkillsNames());
        Candidate createNewCandidate = buildNewCandidate(candidateUpdateRequest, user, city);

        Candidate savedCandidate = candidateDAO.save(createNewCandidate);
        if (Objects.nonNull(candidateUpdateRequest.getCandidateSkillsNames())) {
            Set<CandidateSkill> candidateSkillToSave = buildCandidateSkills(savedCandidate, candidateUpdateRequest);
            candidateSkillService.saveAll(candidateSkillToSave);
        }
        return savedCandidate;
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
                .employerId(buildEmployer(candidateUpdateRequest.getEmployerName()))
                .residenceCityId(city)
                .build();
    }

    private String processNewPhotoFile(CandidateUpdateRequest candidateUpdateRequest, User user) {
        if (Objects.nonNull(candidateUpdateRequest.getFilePhoto()) && !candidateUpdateRequest.getFilePhoto().isEmpty()) {
            log.info("Process upload new photo file : [{}]", candidateUpdateRequest.getFilePhoto().getOriginalFilename());
            return fileUploadService.saveFileToDisc(user.getUserUuid(), candidateUpdateRequest.getFilePhoto());
        } else {
            return null;
        }

    }

    private String processNewCvFile(CandidateUpdateRequest candidateUpdateRequest, User user) {
        if (Objects.nonNull(candidateUpdateRequest.getFileCv()) && !candidateUpdateRequest.getFileCv().isEmpty()) {
            log.info("Process upload new cv file : [{}]", candidateUpdateRequest.getFileCv().getOriginalFilename());
            return fileUploadService.saveFileToDisc(user.getUserUuid(), candidateUpdateRequest.getFileCv());
        } else {
            return null;
        }
    }

    private Employer buildEmployer(String employerCompanyName) {
        if (Objects.nonNull(employerCompanyName)) {
            return employerService.findByCompanyName(employerCompanyName);
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

    private String processUpdateCvFile(CandidateUpdateRequest candidateUpdateRequest, Candidate candidate) {
        if (Objects.nonNull(candidateUpdateRequest.getFileCv()) && !candidateUpdateRequest.getFileCv().isEmpty()) {
            log.info("Process upload update cv file : [{}]", candidateUpdateRequest.getFileCv().getOriginalFilename());
            try {
                if (fileUploadService.oldFileExist(candidate.getCandidateUuid(), candidate.getCvFilename())) {
                    fileUploadService.deleteFileFromDisc(candidate.getCandidateUuid(), candidate.getCvFilename());
                }
            } catch (IOException e) {
                log.error("Could not delete file: [{}]", candidate.getCvFilename());
                throw new FileUploadToProfileException("Could not delete file: " + candidate.getCvFilename());
            }
            return fileUploadService.saveFileToDisc(candidate.getCandidateUuid(), candidateUpdateRequest.getFileCv());
        } else {
            return candidate.getCvFilename();
        }
    }

    private String processUpdatePhotoFile(CandidateUpdateRequest candidateUpdateRequest, Candidate candidate) {
        if (Objects.nonNull(candidateUpdateRequest.getFilePhoto()) && !candidateUpdateRequest.getFilePhoto().isEmpty()) {
            log.info("Process upload update photo file : [{}]", candidateUpdateRequest.getFilePhoto().getOriginalFilename());
            try {
                if (fileUploadService.oldFileExist(candidate.getCandidateUuid(), candidate.getPhotoFilename())) {
                    fileUploadService.deleteFileFromDisc(candidate.getCandidateUuid(), candidate.getPhotoFilename());
                }
            } catch (IOException e) {
                log.error("Could not delete file: [{}]", candidate.getPhotoFilename());
                throw new FileUploadToProfileException("Could not delete file: " + candidate.getPhotoFilename());
            }
            return fileUploadService.saveFileToDisc(candidate.getCandidateUuid(), candidateUpdateRequest.getFilePhoto());
        } else {
            return candidate.getPhotoFilename();
        }
    }


    @Transactional
    public void deleteCandidateProfile(Long candidateId) {
        log.info("Process delete candidate profile, candidateId: [{}]", candidateId);
        Candidate candidate = this.findById(candidateId);

        candidateSkillService.deleteAllByCandidate(candidate);

        if (Objects.nonNull(candidate.getCvFilename())) {
            fileUploadService.deleteFileFromDisc(candidate.getCandidateUuid(), candidate.getCvFilename());
        }
        if (Objects.nonNull(candidate.getPhotoFilename())) {
            fileUploadService.deleteFileFromDisc(candidate.getCandidateUuid(), candidate.getPhotoFilename());
        }

        candidateDAO.deleteById(candidateId);

        if (dataService.countUsesOfCityName(candidate.getResidenceCityId().getCityName()) <= 1) {
            log.info("City No Longer needed. Delete city : [{}]", candidate.getResidenceCityId().getCityName());
            cityService.deleteByCityName(candidate.getResidenceCityId().getCityName());
        }
    }


    public long countByCityName(String cityName) {
        return candidateDAO.countByCityName(cityName);
    }

    public void deleteCvFile(Candidate candidate) {
        log.info("Process delete cv file");
        fileUploadService.deleteFileFromDisc(candidate.getCandidateUuid(), candidate.getCvFilename());
        Candidate updateCandidate = candidate.withCvFilename(null);
        candidateDAO.save(updateCandidate);
    }

    public void deletePhotoFile(Candidate candidate) {
        log.info("Process delete photo file");
        fileUploadService.deleteFileFromDisc(candidate.getCandidateUuid(), candidate.getPhotoFilename());
        Candidate updateCandidate = candidate.withPhotoFilename(null);
        candidateDAO.save(updateCandidate);
    }
}
