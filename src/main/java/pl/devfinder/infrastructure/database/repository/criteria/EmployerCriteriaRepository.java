package pl.devfinder.infrastructure.database.repository.criteria;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.Employer;
import pl.devfinder.domain.EmployerSearchCriteria;
import pl.devfinder.infrastructure.database.entity.EmployerEntity;
import pl.devfinder.infrastructure.database.repository.mapper.EmployerEntityMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Repository
@Slf4j
public class EmployerCriteriaRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;
    private final EmployerEntityMapper employerEntityMapper;

    public EmployerCriteriaRepository(EntityManager entityManager, EmployerEntityMapper employerEntityMapper) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
        this.employerEntityMapper = employerEntityMapper;
    }

    public Page<Employer> findAllByCriteria(EmployerSearchCriteria employerSearchCriteria) {
        //TODO tu wstawić CriteriaBuilder builder = entityManager.getCriteriaBuilder(); zamiast w konktruktorze
        log.info("Trying Find By Criteria");
        CriteriaQuery<EmployerEntity> criteriaQuery = criteriaBuilder.createQuery(EmployerEntity.class);
        Root<EmployerEntity> employerEntityRoot = criteriaQuery.from(EmployerEntity.class);
        Predicate predicate = getPredicate(employerSearchCriteria, employerEntityRoot, criteriaQuery);

        criteriaQuery.where(predicate);
        setOrder(employerSearchCriteria, criteriaQuery, employerEntityRoot);

        TypedQuery<EmployerEntity> typedQuery = entityManager.createQuery(criteriaQuery);
//////////////// Obejście problermu wyrzucającego błąd z metody getEmployerCount(predicate)iczanie ilości wszystkich
//        elementów uwzględniając predykaty
        PageImpl<EmployerEntity> employerEntitiesToCountItems = new PageImpl<>(typedQuery.getResultList(),
                PageRequest.of(0, 500,
                        Sort.by(employerSearchCriteria.getSortDirection(), employerSearchCriteria.getSortBy())), 500);
        long employerCount = employerEntitiesToCountItems.getContent().size();
///////////////
        typedQuery.setFirstResult((employerSearchCriteria.getPageNumber() - 1) * employerSearchCriteria.getPageSize());
        typedQuery.setMaxResults(employerSearchCriteria.getPageSize());

        Pageable pageable = getPageable(employerSearchCriteria);

//        long employerCount = 25; //getEmployerCount(predicate);
        PageImpl<EmployerEntity> employerEntities = new PageImpl<>(typedQuery.getResultList(), pageable, employerCount);
        log.info("->> CA count PageImpl TotalElements : " + employerEntities.getTotalElements());
        log.info("->> CA count PageImpl employerCount : " + employerCount);

        return employerEntities.map(employerEntityMapper::mapFromEntity);
    }

    private Predicate getPredicate(EmployerSearchCriteria employerSearchCriteria,
                                   Root<EmployerEntity> employerRoot, CriteriaQuery criteriaQuery) {
        List<Predicate> predicates = new ArrayList<>();


////         Warunek dla listy experienceLevels
//        List<String> experienceLevels = employerSearchCriteria.getExperienceLevels();
//        if (Objects.nonNull(experienceLevels) && !experienceLevels.isEmpty()) {
//            Predicate experienceLevelPredicates = experienceLevels.stream()
//                    .filter(Objects::nonNull)
//                    .filter(value -> !value.isBlank())
//                    .flatMap(value -> {
//                        log.info("->> CA find value in experienceLevels list: " + value);
//                        if (Keys.Experience.JUNIOR.getName().equals(value)) {
//                            log.info("->> CA return experienceLevels JUNIOR");
//                            return Stream.of(criteriaBuilder.equal(employerRoot.get(
//                                    Keys.EmployerFilterBy.experienceLevel.getName()), Keys.Experience.JUNIOR.getName()));
//                        } else if (Keys.Experience.MID.getName().equals(value)) {
//                            log.info("->> CA return experienceLevels MID");
//                            return Stream.of(criteriaBuilder.equal(employerRoot.get(
//                                    Keys.EmployerFilterBy.experienceLevel.getName()), Keys.Experience.MID.getName()));
//                        } else if (Keys.Experience.SENIOR.getName().equals(value)) {
//                            log.info("->> CA return experienceLevels SENIOR");
//                            return Stream.of(criteriaBuilder.equal(employerRoot.get(
//                                    Keys.EmployerFilterBy.experienceLevel.getName()), Keys.Experience.SENIOR.getName()));
//                        } else {
//                            log.info("->> CA return experienceLevels NoFilter");
//                            return Stream.empty();
//                        }
//                    })
//                    .reduce(criteriaBuilder::or)
//                    .orElse(criteriaBuilder.conjunction());
//            predicates.add(experienceLevelPredicates);
//        }
//
//        // Warunek dla listy remoteWork
//        List<String> remoteWork = employerSearchCriteria.getRemoteWork();
//        if (Objects.nonNull(remoteWork) && !remoteWork.isEmpty()) {
//            Predicate remoteWorkPredicates = remoteWork.stream()
//                    .filter(Objects::nonNull)
//                    .filter(value -> !value.isBlank())
//                    .flatMap(value -> {
//                        log.error("->> CA find value in remoteWork list: " + value);
//                        if (Keys.RemoteWork.OFFICE.getName().equals(value)) {
//                            log.error("->> CA return remoteWork OFFICE");
//                            return Stream.of(criteriaBuilder.equal(employerRoot.get(Keys.EmployerFilterBy.remoteWork.getName()), 0));
//                        } else if (Keys.RemoteWork.PARTLY.getName().equals(value)) {
//                            log.error("->> CA return remoteWork PARTLY");
//                            return Stream.of(criteriaBuilder.between(employerRoot.get(Keys.EmployerFilterBy.remoteWork.getName()), 1, 99));
//                        } else if (Keys.RemoteWork.FULL.getName().equals(value)) {
//                            log.error("->> CA return remoteWork FULL");
//                            return Stream.of(criteriaBuilder.equal(employerRoot.get(Keys.EmployerFilterBy.remoteWork.getName()), 100));
//                        } else {
//                            log.error("->> CA return remoteWork NoFilter");
//                            return Stream.empty();
//                        }
//                    })
//                    .reduce(criteriaBuilder::or)
//                    .orElse(criteriaBuilder.conjunction());
//            predicates.add(remoteWorkPredicates);
//        }
//
//        // Warunek dla salaryMin
//        BigDecimal salaryMin = employerSearchCriteria.getSalaryMin();
//        if (Objects.nonNull(salaryMin) && !salaryMin.equals(BigDecimal.ZERO)) {
//            log.info("->> CA return salaryMin: " + salaryMin);
//            predicates.add(criteriaBuilder.greaterThanOrEqualTo(employerRoot.get(Keys.EmployerFilterBy.salaryMin.getName()), salaryMin));
//        }
//
//        // Warunek dla listy salary
//        List<String> salary = employerSearchCriteria.getSalary();
//        if (Objects.nonNull(salary) && !salary.isEmpty()) {
//            Predicate salaryPredicates = salary.stream()
//                    .filter(Objects::nonNull)
//                    .filter(value -> !value.isBlank())
//                    .flatMap(value -> {
//                        log.info("->> CA find value in salary list: " + value);
//                        if (Keys.Salary.WITH.getName().equals(value)) {
//                            log.info("->> CA return salary WITH");
//                            return Stream.of(criteriaBuilder.isNotNull(employerRoot.get(Keys.EmployerFilterBy.salaryMin.getName())), criteriaBuilder.isNotNull(employerRoot.get(Keys.EmployerFilterBy.salaryMax.getName())));
//                        } else if (Keys.Salary.UNDISCLOSED.getName().equals(value)) {
//                            log.info("->> CA return salary UNDISCLOSED");
//                            return Stream.of(criteriaBuilder.isNull(employerRoot.get(Keys.EmployerFilterBy.salaryMin.getName())), criteriaBuilder.isNull(employerRoot.get(Keys.EmployerFilterBy.salaryMax.getName())));
//                        } else {
//                            log.info("->> CA return salary NoFilter");
//                            return Stream.empty();
//                        }
//                    })
//                    .reduce(criteriaBuilder::or)
//                    .orElse(criteriaBuilder.conjunction());
//            predicates.add(salaryPredicates);
//        }
//
////         Warunek dla listy status
//
//        List<String> status = employerSearchCriteria.getStatus();
//        if (Objects.nonNull(status) && !status.isEmpty()) {
//            List<Predicate> statusPredicates = status.stream()
//                    .filter(Objects::nonNull)
//                    .filter(value -> !value.isBlank())
//                    .map(value -> {
//                        log.info("->> CA find value in status list: " + value);
//                        if (Keys.EmployerState.ACTIVE.getName().equals(value)) {
//                            log.info("->> CA return status ACTIVE");
//                            return criteriaBuilder.like(employerRoot.get(Keys.EmployerFilterBy.status.getName()), Keys.EmployerState.ACTIVE.getName());
//                        } else if (Keys.EmployerState.EXPIRED.getName().equals(value)) {
//                            log.info("->> CA return status EXPIRED");
//                            return criteriaBuilder.equal(employerRoot.get(Keys.EmployerFilterBy.status.getName()), Keys.EmployerState.EXPIRED.getName());
//                        } else {
//                            log.info("->> CA return status NoFilter");
//                            return null;
//                        }
//                    })
//                    .filter(Objects::nonNull)
//                    .toList();
//            if (!statusPredicates.isEmpty()) {
//                Predicate combinedStatusPredicate = criteriaBuilder.or(statusPredicates.toArray(new Predicate[0]));
//                predicates.add(combinedStatusPredicate);
//            }
//        }
//
//        // Warunek dla city
//        String city = employerSearchCriteria.getCity();
//        if (Objects.nonNull(city) && !city.isBlank()) {
//            log.info("->> CA return city: " + city);
//            predicates.add(criteriaBuilder.equal(employerRoot.get(Keys.EmployerFilterBy.cityId.getName())
//                    .get(Keys.EmployerFilterBy.cityName.getName()), city));
//        }
//
//        // Warunek dla employer
//        String employer = employerSearchCriteria.getEmployer();
//        if (Objects.nonNull(employer) && !employer.isBlank()) {
//            log.info("->> CA return employer: " + employer);
//            predicates.add(criteriaBuilder.equal(employerRoot.get(Keys.EmployerFilterBy.employerId.getName())
//                    .get(Keys.EmployerFilterBy.companyName.getName()), employer));
//        }
//
//        // Warunek dla listy skills
//        List<String> skills = employerSearchCriteria.getSkills();
//        if (Objects.nonNull(skills) && !skills.isEmpty()) {
//            Predicate skillPredicates =
//                    skills.stream()
//                    .filter(Objects::nonNull)
//                    .filter(value -> !value.isBlank())
//                    .filter(value -> Keys.LIST_OF_SKILLS.LIST_OF_SKILLS.getFields()
//                            .contains(value))
//                            .map(value -> {
//                                Subquery<EmployerSkillEntity> subquery = criteriaQuery.subquery(EmployerSkillEntity.class);
//                                Root<EmployerSkillEntity> employerSkillRoot = subquery.from(EmployerSkillEntity.class);
//                                subquery.select(employerSkillRoot);
//
//                                Predicate employerIdPredicate = criteriaBuilder.equal(employerSkillRoot.get(Keys.EmployerFilterBy.employerId.getName()), employerRoot);
//                                Predicate skillNamePredicate = criteriaBuilder.equal(employerSkillRoot.get(Keys.EmployerFilterBy.skillId.getName()).get(Keys.EmployerFilterBy.skillName.getName()), value);
//
//                                return criteriaBuilder.exists(subquery.where(criteriaBuilder.and(employerIdPredicate, skillNamePredicate)));
//                            })
//                            .reduce(criteriaBuilder::and)
//                            .orElse(criteriaBuilder.conjunction());
//
//            predicates.add(skillPredicates);
//        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(EmployerSearchCriteria employerSearchCriteria,
                          CriteriaQuery<EmployerEntity> criteriaQuery,
                          Root<EmployerEntity> employerEntityRoot) {

        if (employerSearchCriteria.getSortBy().contains(".")) {
            String[] split = employerSearchCriteria.getSortBy().split("[.]");
            if (employerSearchCriteria.getSortDirection().equals(Sort.Direction.ASC)) {
                log.info("->> CA Sorting ASC with: " + split[0] + " and " + split[1]);
                criteriaQuery.orderBy(criteriaBuilder.asc(employerEntityRoot.get(split[0]).get(split[1])));
            } else {
                log.info("->> CA Sorting DESC with: " + split[0] + " and " + split[1]);
                criteriaQuery.orderBy(criteriaBuilder.desc(employerEntityRoot.get(split[0]).get(split[1])));
            }
        } else {
            if (employerSearchCriteria.getSortDirection().equals(Sort.Direction.ASC)) {
                log.info("->> CA Sorting ASC with: " + employerSearchCriteria.getSortBy());
                criteriaQuery.orderBy(criteriaBuilder.asc(employerEntityRoot.get(employerSearchCriteria.getSortBy())));
            } else {
                log.info("->> CA Sorting DESC with: " + employerSearchCriteria.getSortBy());
                criteriaQuery.orderBy(criteriaBuilder.desc(employerEntityRoot.get(employerSearchCriteria.getSortBy())));
            }
        }
    }

    private Pageable getPageable(EmployerSearchCriteria employerSearchCriteria) {
        log.info("->> CA Pageable pageNumber: " + (employerSearchCriteria.getPageNumber() - 1));
        Sort sort = Sort.by(employerSearchCriteria.getSortDirection(), employerSearchCriteria.getSortBy());
        return PageRequest.of(employerSearchCriteria.getPageNumber() - 1, employerSearchCriteria.getPageSize(), sort);
    }

    private long getEmployerCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<EmployerEntity> countRoot = countQuery.from(EmployerEntity.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        TypedQuery<Long> typedQuery = entityManager.createQuery(countQuery);
        return typedQuery.getSingleResult();

    }


}
