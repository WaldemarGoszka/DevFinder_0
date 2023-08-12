package pl.devfinder.infrastructure.database.repository.criteria;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.search.CandidateSearchCriteria;
import pl.devfinder.infrastructure.database.entity.CandidateEntity;
import pl.devfinder.infrastructure.database.entity.CandidateSkillEntity;
import pl.devfinder.infrastructure.database.repository.mapper.CandidateEntityMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Repository
@Slf4j
public class CandidateCriteriaRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;
    private final CandidateEntityMapper candidateEntityMapper;

    public CandidateCriteriaRepository(EntityManager entityManager, CandidateEntityMapper candidateEntityMapper) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
        this.candidateEntityMapper = candidateEntityMapper;
    }

    public Page<Candidate> findAllByCriteria(CandidateSearchCriteria candidateSearchCriteria) {
        log.info("Process Find Candidate By Criteria: [{}]",candidateSearchCriteria);
        CriteriaQuery<CandidateEntity> criteriaQuery = criteriaBuilder.createQuery(CandidateEntity.class);
        Root<CandidateEntity> candidateEntityRoot = criteriaQuery.from(CandidateEntity.class);
        Predicate predicate = getPredicate(candidateSearchCriteria, candidateEntityRoot, criteriaQuery);

        criteriaQuery.where(predicate);
        setOrder(candidateSearchCriteria, criteriaQuery, candidateEntityRoot);

        TypedQuery<CandidateEntity> typedQuery = entityManager.createQuery(criteriaQuery);

        long candidateCount = getCandidateCount(candidateSearchCriteria, typedQuery);
        typedQuery.setFirstResult((candidateSearchCriteria.getPageNumber() - 1) * candidateSearchCriteria.getPageSize());
        typedQuery.setMaxResults(candidateSearchCriteria.getPageSize());

        Pageable pageable = getPageable(candidateSearchCriteria);

        PageImpl<CandidateEntity> candidateEntities = new PageImpl<>(typedQuery.getResultList(), pageable, candidateCount);

        return candidateEntities.map(candidateEntityMapper::mapFromEntity);
    }

    private static long getCandidateCount(CandidateSearchCriteria candidateSearchCriteria, TypedQuery<CandidateEntity> typedQuery) {
        PageImpl<CandidateEntity> candidateEntitiesToCountItems = new PageImpl<>(typedQuery.getResultList(),
                PageRequest.of(0, Integer.MAX_VALUE,
                        Sort.by(candidateSearchCriteria.getSortDirection(), candidateSearchCriteria.getSortBy())), 500);
        return candidateEntitiesToCountItems.getContent().size();
    }

    private Predicate getPredicate(CandidateSearchCriteria candidateSearchCriteria,
                                   Root<CandidateEntity> candidateRoot, CriteriaQuery criteriaQuery) {
        List<Predicate> predicates = new ArrayList<>();

        experienceLevelPredicate(candidateSearchCriteria, candidateRoot, predicates);
        minimumPayoutPredicate(candidateSearchCriteria, candidateRoot, predicates);
        minimumYearsOfExperiencePredicate(candidateSearchCriteria, candidateRoot, predicates);
        remoteWorkPredicate(candidateSearchCriteria, candidateRoot, predicates);
        employerCompanyNamePredicate(candidateSearchCriteria, candidateRoot, predicates);
        statusPredicate(candidateSearchCriteria, candidateRoot, predicates);
        cityPredicate(candidateSearchCriteria, candidateRoot, predicates);
        skillsPredicate(candidateSearchCriteria, candidateRoot, criteriaQuery, predicates);

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void skillsPredicate(CandidateSearchCriteria candidateSearchCriteria, Root<CandidateEntity> candidateRoot, CriteriaQuery criteriaQuery, List<Predicate> predicates) {
        List<String> skills = candidateSearchCriteria.getSkills();
        if (Objects.nonNull(skills) && !skills.isEmpty()) {
            Predicate skillPredicates =
                    skills.stream()
                    .filter(Objects::nonNull)
                    .filter(value -> !value.isBlank())
                    .filter(value -> Keys.LIST_OF_SKILLS.LIST_OF_SKILLS.getFields()
                            .contains(value))
                            .map(value -> {
                                Subquery<CandidateSkillEntity> subquery = criteriaQuery.subquery(CandidateSkillEntity.class);
                                Root<CandidateSkillEntity> candidateSkillRoot = subquery.from(CandidateSkillEntity.class);
                                subquery.select(candidateSkillRoot);

                                Predicate candidateIdPredicate = criteriaBuilder.equal(candidateSkillRoot.get(Keys.CandidateFilterBy.candidateId.getName()), candidateRoot);
                                Predicate skillNamePredicate = criteriaBuilder.equal(candidateSkillRoot.get(Keys.CandidateFilterBy.skillId.getName()).get(Keys.CandidateFilterBy.skillName.getName()), value);

                                return criteriaBuilder.exists(subquery.where(criteriaBuilder.and(candidateIdPredicate, skillNamePredicate)));
                            })
                            .reduce(criteriaBuilder::and)
                            .orElse(criteriaBuilder.conjunction());

            predicates.add(skillPredicates);
        }
    }

    private void cityPredicate(CandidateSearchCriteria candidateSearchCriteria, Root<CandidateEntity> candidateRoot, List<Predicate> predicates) {
        String city = candidateSearchCriteria.getCity();
        if (Objects.nonNull(city) && !city.isBlank()) {
            predicates.add(criteriaBuilder.equal(candidateRoot.get(Keys.CandidateFilterBy.residenceCityId.getName())
                    .get(Keys.CandidateFilterBy.cityName.getName()), city));
        }
    }

    private void statusPredicate(CandidateSearchCriteria candidateSearchCriteria, Root<CandidateEntity> candidateRoot, List<Predicate> predicates) {
        List<String> status = candidateSearchCriteria.getStatus();
        if (Objects.nonNull(status) && !status.isEmpty()) {
            List<Predicate> statusPredicates = status.stream()
                    .filter(Objects::nonNull)
                    .filter(value -> !value.isBlank())
                    .map(value -> {
                        if (Keys.CandidateState.ACTIVE.getName().equals(value)) {
                            return criteriaBuilder.equal(candidateRoot.get(Keys.CandidateFilterBy.status.getName()), Keys.OfferState.ACTIVE.getName());
                        } else if (Keys.CandidateState.INACTIVE.getName().equals(value)) {
                            return criteriaBuilder.equal(candidateRoot.get(Keys.CandidateFilterBy.status.getName()), Keys.CandidateState.INACTIVE.getName());
                        } else if (Keys.CandidateState.EMPLOYED.getName().equals(value)) {
                            return criteriaBuilder.equal(candidateRoot.get(Keys.CandidateFilterBy.status.getName()), Keys.CandidateState.EMPLOYED.getName());
                        } else {
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .toList();
            if (!statusPredicates.isEmpty()) {
                Predicate combinedStatusPredicate = criteriaBuilder.or(statusPredicates.toArray(new Predicate[0]));
                predicates.add(combinedStatusPredicate);
            }
        }
    }

    private void employerCompanyNamePredicate(CandidateSearchCriteria candidateSearchCriteria, Root<CandidateEntity> candidateRoot, List<Predicate> predicates) {
        String employer = candidateSearchCriteria.getEmployer();
        if (Objects.nonNull(employer) && !employer.isBlank()) {
            predicates.add(criteriaBuilder.equal(candidateRoot.get(Keys.OfferFilterBy.employerId.getName())
                    .get(Keys.OfferFilterBy.companyName.getName()), employer));
        }
    }

    private void remoteWorkPredicate(CandidateSearchCriteria candidateSearchCriteria, Root<CandidateEntity> candidateRoot, List<Predicate> predicates) {
        List<String> openToRemoteJob = candidateSearchCriteria.getOpenToRemoteJob();
        if (Objects.nonNull(openToRemoteJob) && !openToRemoteJob.isEmpty()) {
            List<Predicate> openToRemoteJobPredicates = openToRemoteJob.stream()
                    .filter(Objects::nonNull)
                    .filter(value -> !value.isBlank())
                    .map(value -> {
                        if (Keys.CandidateFilterBy.YES.getName().equals(value)) {
                            return criteriaBuilder.equal(candidateRoot.get(Keys.CandidateFilterBy.openToRemoteJob.getName()),true);
                        } else if (Keys.CandidateFilterBy.NO.getName().equals(value)) {
                            return criteriaBuilder.equal(candidateRoot.get(Keys.CandidateFilterBy.openToRemoteJob.getName()),false);
                        } else {
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .toList();
            if (!openToRemoteJobPredicates.isEmpty()) {
                Predicate combinedStatusPredicate = criteriaBuilder.or(openToRemoteJobPredicates.toArray(new Predicate[0]));
                predicates.add(combinedStatusPredicate);
            }
        }
    }

    private void minimumYearsOfExperiencePredicate(CandidateSearchCriteria candidateSearchCriteria, Root<CandidateEntity> candidateRoot, List<Predicate> predicates) {
        Integer minYearsOfExperience = candidateSearchCriteria.getMinYearsOfExperience();
        if (Objects.nonNull(minYearsOfExperience) && !minYearsOfExperience.equals(0)) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(candidateRoot.get(Keys.CandidateFilterBy.yearsOfExperience.getName()), minYearsOfExperience));
        }
    }

    private void minimumPayoutPredicate(CandidateSearchCriteria candidateSearchCriteria, Root<CandidateEntity> candidateRoot, List<Predicate> predicates) {
        BigDecimal salaryMax = candidateSearchCriteria.getSalaryMax();
        if (Objects.nonNull(salaryMax) && !salaryMax.equals(BigDecimal.ZERO)) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(candidateRoot.get(Keys.CandidateFilterBy.salaryMin.getName()), salaryMax));
        }
    }

    private void experienceLevelPredicate(CandidateSearchCriteria candidateSearchCriteria, Root<CandidateEntity> candidateRoot, List<Predicate> predicates) {
        List<String> experienceLevels = candidateSearchCriteria.getExperienceLevels();
        if (Objects.nonNull(experienceLevels) && !experienceLevels.isEmpty()) {
            Predicate experienceLevelPredicates = experienceLevels.stream()
                    .filter(Objects::nonNull)
                    .filter(value -> !value.isBlank())
                    .flatMap(value -> {
                        if (Keys.Experience.JUNIOR.getName().equals(value)) {
                            return Stream.of(criteriaBuilder.equal(candidateRoot.get(
                                    Keys.CandidateFilterBy.experienceLevel.getName()), Keys.Experience.JUNIOR.getName()));
                        } else if (Keys.Experience.MID.getName().equals(value)) {
                            return Stream.of(criteriaBuilder.equal(candidateRoot.get(
                                    Keys.CandidateFilterBy.experienceLevel.getName()), Keys.Experience.MID.getName()));
                        } else if (Keys.Experience.SENIOR.getName().equals(value)) {
                            return Stream.of(criteriaBuilder.equal(candidateRoot.get(
                                    Keys.CandidateFilterBy.experienceLevel.getName()), Keys.Experience.SENIOR.getName()));
                        } else {
                            return Stream.empty();
                        }
                    })
                    .reduce(criteriaBuilder::or)
                    .orElse(criteriaBuilder.conjunction());
            predicates.add(experienceLevelPredicates);
        }
    }

    private void setOrder(CandidateSearchCriteria candidateSearchCriteria,
                          CriteriaQuery<CandidateEntity> criteriaQuery,
                          Root<CandidateEntity> candidateEntityRoot) {

        if (candidateSearchCriteria.getSortBy().contains(".")) {
            String[] split = candidateSearchCriteria.getSortBy().split("[.]");
            if (candidateSearchCriteria.getSortDirection().equals(Sort.Direction.ASC)) {
                criteriaQuery.orderBy(criteriaBuilder.asc(candidateEntityRoot.get(split[0]).get(split[1])));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.desc(candidateEntityRoot.get(split[0]).get(split[1])));
            }
        } else {
            if (candidateSearchCriteria.getSortDirection().equals(Sort.Direction.ASC)) {
                criteriaQuery.orderBy(criteriaBuilder.asc(candidateEntityRoot.get(candidateSearchCriteria.getSortBy())));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.desc(candidateEntityRoot.get(candidateSearchCriteria.getSortBy())));
            }
        }
    }

    private Pageable getPageable(CandidateSearchCriteria candidateSearchCriteria) {
        Sort sort = Sort.by(candidateSearchCriteria.getSortDirection(), candidateSearchCriteria.getSortBy());
        return PageRequest.of(candidateSearchCriteria.getPageNumber() - 1, candidateSearchCriteria.getPageSize(), sort);
    }

}
