package pl.devfinder.infrastructure.database.repository.criteria;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.Offer;
import pl.devfinder.domain.search.OfferSearchCriteria;
import pl.devfinder.infrastructure.database.entity.OfferEntity;
import pl.devfinder.infrastructure.database.entity.OfferSkillEntity;
import pl.devfinder.infrastructure.database.repository.mapper.OfferEntityMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Repository
@Slf4j
public class OfferCriteriaRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;
    private final OfferEntityMapper offerEntityMapper;

    public OfferCriteriaRepository(EntityManager entityManager, OfferEntityMapper offerEntityMapper) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
        this.offerEntityMapper = offerEntityMapper;
    }

    private static long getOfferCount(OfferSearchCriteria offerSearchCriteria, TypedQuery<OfferEntity> typedQuery) {
        PageImpl<OfferEntity> offerEntitiesToCheck = new PageImpl<>(typedQuery.getResultList(),
                PageRequest.of(0, Integer.MAX_VALUE,
                        Sort.by(offerSearchCriteria.getSortDirection(), offerSearchCriteria.getSortBy())), 500);
        return offerEntitiesToCheck.getContent().size();
    }

    public Page<Offer> findAllByCriteria(OfferSearchCriteria offerSearchCriteria) {
        log.info("Process find offer By Criteria: [{}]", offerSearchCriteria);
        CriteriaQuery<OfferEntity> criteriaQuery = criteriaBuilder.createQuery(OfferEntity.class);
        Root<OfferEntity> offerEntityRoot = criteriaQuery.from(OfferEntity.class);
        Predicate predicate = getPredicate(offerSearchCriteria, offerEntityRoot, criteriaQuery);

        criteriaQuery.where(predicate);
        setOrder(offerSearchCriteria, criteriaQuery, offerEntityRoot);

        TypedQuery<OfferEntity> typedQuery = entityManager.createQuery(criteriaQuery);

        long offerCount = getOfferCount(offerSearchCriteria, typedQuery);

        typedQuery.setFirstResult((offerSearchCriteria.getPageNumber() - 1) * offerSearchCriteria.getPageSize());
        typedQuery.setMaxResults(offerSearchCriteria.getPageSize());

        Pageable pageable = getPageable(offerSearchCriteria);

        PageImpl<OfferEntity> offerEntities = new PageImpl<>(typedQuery.getResultList(), pageable, offerCount);

        return offerEntities.map(offerEntityMapper::mapFromEntity);
    }

    private Predicate getPredicate(OfferSearchCriteria offerSearchCriteria,
                                   Root<OfferEntity> offerRoot, CriteriaQuery criteriaQuery) {
        List<Predicate> predicates = new ArrayList<>();

        experienceLevelPredicate(offerSearchCriteria, offerRoot, predicates);
        remoteWorkPredicate(offerSearchCriteria, offerRoot, predicates);
        minimumPayoutPredicate(offerSearchCriteria, offerRoot, predicates);
        withOrWithoutDeclaredSalaryPredicate(offerSearchCriteria, offerRoot, predicates);
        statusPredicate(offerSearchCriteria, offerRoot, predicates);
        cityPredicate(offerSearchCriteria, offerRoot, predicates);
        employerCompanyNamePredicate(offerSearchCriteria, offerRoot, predicates);
        skillsPredicate(offerSearchCriteria, offerRoot, criteriaQuery, predicates);

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void skillsPredicate(OfferSearchCriteria offerSearchCriteria, Root<OfferEntity> offerRoot, CriteriaQuery criteriaQuery, List<Predicate> predicates) {
        List<String> skills = offerSearchCriteria.getSkills();
        if (Objects.nonNull(skills) && !skills.isEmpty()) {
            Predicate skillPredicates =
                    skills.stream()
                            .filter(Objects::nonNull)
                            .filter(value -> !value.isBlank())
                            .filter(value -> Keys.LIST_OF_SKILLS.LIST_OF_SKILLS.getFields()
                                    .contains(value))
                            .map(value -> {
                                Subquery<OfferSkillEntity> subquery = criteriaQuery.subquery(OfferSkillEntity.class);
                                Root<OfferSkillEntity> offerSkillRoot = subquery.from(OfferSkillEntity.class);
                                subquery.select(offerSkillRoot);

                                Predicate offerIdPredicate = criteriaBuilder.equal(offerSkillRoot.get(Keys.OfferFilterBy.offerId.getName()), offerRoot);
                                Predicate skillNamePredicate = criteriaBuilder.equal(offerSkillRoot.get(Keys.OfferFilterBy.skillId.getName()).get(Keys.OfferFilterBy.skillName.getName()), value);

                                return criteriaBuilder.exists(subquery.where(criteriaBuilder.and(offerIdPredicate, skillNamePredicate)));
                            })
                            .reduce(criteriaBuilder::and)
                            .orElse(criteriaBuilder.conjunction());

            predicates.add(skillPredicates);
        }
    }

    private void employerCompanyNamePredicate(OfferSearchCriteria offerSearchCriteria, Root<OfferEntity> offerRoot, List<Predicate> predicates) {
        String employer = offerSearchCriteria.getEmployer();
        if (Objects.nonNull(employer) && !employer.isBlank()) {
            predicates.add(criteriaBuilder.equal(offerRoot.get(Keys.OfferFilterBy.employerId.getName())
                    .get(Keys.OfferFilterBy.companyName.getName()), employer));
        }
    }

    private void cityPredicate(OfferSearchCriteria offerSearchCriteria, Root<OfferEntity> offerRoot, List<Predicate> predicates) {
        String city = offerSearchCriteria.getCity();
        if (Objects.nonNull(city) && !city.isBlank()) {
            predicates.add(criteriaBuilder.equal(offerRoot.get(Keys.OfferFilterBy.cityId.getName())
                    .get(Keys.OfferFilterBy.cityName.getName()), city));
        }
    }

    private void statusPredicate(OfferSearchCriteria offerSearchCriteria, Root<OfferEntity> offerRoot, List<Predicate> predicates) {
        List<String> status = offerSearchCriteria.getStatus();
        if (Objects.nonNull(status) && !status.isEmpty()) {
            List<Predicate> statusPredicates = status.stream()
                    .filter(Objects::nonNull)
                    .filter(value -> !value.isBlank())
                    .map(value -> {
                        if (Keys.OfferState.ACTIVE.getName().equals(value)) {
                            return criteriaBuilder.like(offerRoot.get(Keys.OfferFilterBy.status.getName()), Keys.OfferState.ACTIVE.getName());
                        } else if (Keys.OfferState.EXPIRED.getName().equals(value)) {
                            return criteriaBuilder.equal(offerRoot.get(Keys.OfferFilterBy.status.getName()), Keys.OfferState.EXPIRED.getName());
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

    private void withOrWithoutDeclaredSalaryPredicate(OfferSearchCriteria offerSearchCriteria, Root<OfferEntity> offerRoot, List<Predicate> predicates) {
        List<String> salary = offerSearchCriteria.getSalary();
        if (Objects.nonNull(salary) && !salary.isEmpty()) {
            Predicate salaryPredicates = salary.stream()
                    .filter(Objects::nonNull)
                    .filter(value -> !value.isBlank())
                    .flatMap(value -> {
                        if (Keys.Salary.WITH.getName().equals(value)) {
                            return Stream.of(criteriaBuilder.isNotNull(offerRoot.get(Keys.OfferFilterBy.salaryMin.getName())), criteriaBuilder.isNotNull(offerRoot.get(Keys.OfferFilterBy.salaryMax.getName())));
                        } else if (Keys.Salary.UNDISCLOSED.getName().equals(value)) {
                            return Stream.of(criteriaBuilder.isNull(offerRoot.get(Keys.OfferFilterBy.salaryMin.getName())), criteriaBuilder.isNull(offerRoot.get(Keys.OfferFilterBy.salaryMax.getName())));
                        } else {
                            return Stream.empty();
                        }
                    })
                    .reduce(criteriaBuilder::or)
                    .orElse(criteriaBuilder.conjunction());
            predicates.add(salaryPredicates);
        }
    }

    private void minimumPayoutPredicate(OfferSearchCriteria offerSearchCriteria, Root<OfferEntity> offerRoot, List<Predicate> predicates) {
        BigDecimal salaryMin = offerSearchCriteria.getSalaryMin();
        if (Objects.nonNull(salaryMin) && !salaryMin.equals(BigDecimal.ZERO)) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(offerRoot.get(Keys.OfferFilterBy.salaryMin.getName()), salaryMin));
        }
    }

    private void remoteWorkPredicate(OfferSearchCriteria offerSearchCriteria, Root<OfferEntity> offerRoot, List<Predicate> predicates) {
        List<String> remoteWork = offerSearchCriteria.getRemoteWork();
        if (Objects.nonNull(remoteWork) && !remoteWork.isEmpty()) {
            Predicate remoteWorkPredicates = remoteWork.stream()
                    .filter(Objects::nonNull)
                    .filter(value -> !value.isBlank())
                    .flatMap(value -> {
                        if (Keys.RemoteWork.OFFICE.getName().equals(value)) {
                            return Stream.of(criteriaBuilder.equal(offerRoot.get(Keys.OfferFilterBy.remoteWork.getName()), 0));
                        } else if (Keys.RemoteWork.PARTLY.getName().equals(value)) {
                            return Stream.of(criteriaBuilder.between(offerRoot.get(Keys.OfferFilterBy.remoteWork.getName()), 1, 99));
                        } else if (Keys.RemoteWork.FULL.getName().equals(value)) {
                            return Stream.of(criteriaBuilder.equal(offerRoot.get(Keys.OfferFilterBy.remoteWork.getName()), 100));
                        } else {
                            return Stream.empty();
                        }
                    })
                    .reduce(criteriaBuilder::or)
                    .orElse(criteriaBuilder.conjunction());
            predicates.add(remoteWorkPredicates);
        }
    }

    private void experienceLevelPredicate(OfferSearchCriteria offerSearchCriteria, Root<OfferEntity> offerRoot, List<Predicate> predicates) {
        List<String> experienceLevels = offerSearchCriteria.getExperienceLevels();
        if (Objects.nonNull(experienceLevels) && !experienceLevels.isEmpty()) {
            Predicate experienceLevelPredicates = experienceLevels.stream()
                    .filter(Objects::nonNull)
                    .filter(value -> !value.isBlank())
                    .flatMap(value -> {
                        if (Keys.Experience.JUNIOR.getName().equals(value)) {
                            return Stream.of(criteriaBuilder.equal(offerRoot.get(
                                    Keys.OfferFilterBy.experienceLevel.getName()), Keys.Experience.JUNIOR.getName()));
                        } else if (Keys.Experience.MID.getName().equals(value)) {
                            return Stream.of(criteriaBuilder.equal(offerRoot.get(
                                    Keys.OfferFilterBy.experienceLevel.getName()), Keys.Experience.MID.getName()));
                        } else if (Keys.Experience.SENIOR.getName().equals(value)) {
                            return Stream.of(criteriaBuilder.equal(offerRoot.get(
                                    Keys.OfferFilterBy.experienceLevel.getName()), Keys.Experience.SENIOR.getName()));
                        } else {
                            return Stream.empty();
                        }
                    })
                    .reduce(criteriaBuilder::or)
                    .orElse(criteriaBuilder.conjunction());
            predicates.add(experienceLevelPredicates);
        }
    }

    private void setOrder(OfferSearchCriteria offerSearchCriteria,
                          CriteriaQuery<OfferEntity> criteriaQuery,
                          Root<OfferEntity> offerEntityRoot) {

        if (offerSearchCriteria.getSortBy().contains(".")) {
            String[] split = offerSearchCriteria.getSortBy().split("[.]");
            if (offerSearchCriteria.getSortDirection().equals(Sort.Direction.ASC)) {
                criteriaQuery.orderBy(criteriaBuilder.asc(offerEntityRoot.get(split[0]).get(split[1])));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.desc(offerEntityRoot.get(split[0]).get(split[1])));
            }
        } else {
            if (offerSearchCriteria.getSortDirection().equals(Sort.Direction.ASC)) {
                criteriaQuery.orderBy(criteriaBuilder.asc(offerEntityRoot.get(offerSearchCriteria.getSortBy())));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.desc(offerEntityRoot.get(offerSearchCriteria.getSortBy())));
            }
        }
    }

    private Pageable getPageable(OfferSearchCriteria offerSearchCriteria) {
        Sort sort = Sort.by(offerSearchCriteria.getSortDirection(), offerSearchCriteria.getSortBy());
        return PageRequest.of(offerSearchCriteria.getPageNumber() - 1, offerSearchCriteria.getPageSize(), sort);
    }
}
