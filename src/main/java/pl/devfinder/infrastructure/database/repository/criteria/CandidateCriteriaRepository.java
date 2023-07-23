package pl.devfinder.infrastructure.database.repository.criteria;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.search.CandidateSearchCriteria;
import pl.devfinder.infrastructure.database.entity.CandidateEntity;
import pl.devfinder.infrastructure.database.repository.mapper.CandidateEntityMapper;

import java.util.ArrayList;
import java.util.List;

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
        //TODO tu wstawić CriteriaBuilder builder = entityManager.getCriteriaBuilder(); zamiast w konktruktorze
        log.info("Trying Find Candidate By Criteria");
        CriteriaQuery<CandidateEntity> criteriaQuery = criteriaBuilder.createQuery(CandidateEntity.class);
        Root<CandidateEntity> candidateEntityRoot = criteriaQuery.from(CandidateEntity.class);
        Predicate predicate = getPredicate(candidateSearchCriteria, candidateEntityRoot, criteriaQuery);

        criteriaQuery.where(predicate);
        setOrder(candidateSearchCriteria, criteriaQuery, candidateEntityRoot);

        TypedQuery<CandidateEntity> typedQuery = entityManager.createQuery(criteriaQuery);
//////////////// Obejście problermu wyrzucającego błąd z metody getCandidateCount(predicate)iczanie ilości wszystkich
//        elementów uwzględniając predykaty
        PageImpl<CandidateEntity> candidateEntitiesToCountItems = new PageImpl<>(typedQuery.getResultList(),
                PageRequest.of(0, Integer.MAX_VALUE,
                        Sort.by(candidateSearchCriteria.getSortDirection(), candidateSearchCriteria.getSortBy())), 500);
        long candidateCount = candidateEntitiesToCountItems.getContent().size();
///////////////
        typedQuery.setFirstResult((candidateSearchCriteria.getPageNumber() - 1) * candidateSearchCriteria.getPageSize());
        typedQuery.setMaxResults(candidateSearchCriteria.getPageSize());

        Pageable pageable = getPageable(candidateSearchCriteria);

//        long candidateCount = 25; //getCandidateCount(predicate);
        PageImpl<CandidateEntity> candidateEntities = new PageImpl<>(typedQuery.getResultList(), pageable, candidateCount);
        log.info("->> CA count PageImpl TotalElements : " + candidateEntities.getTotalElements());
        log.info("->> CA count PageImpl candidateCount : " + candidateCount);

        return candidateEntities.map(candidateEntityMapper::mapFromEntity);
    }

    private Predicate getPredicate(CandidateSearchCriteria candidateSearchCriteria,
                                   Root<CandidateEntity> candidateRoot, CriteriaQuery criteriaQuery) {
        List<Predicate> predicates = new ArrayList<>();


////         Warunek dla listy experienceLevels
//        List<String> experienceLevels = candidateSearchCriteria.getExperienceLevels();
//        if (Objects.nonNull(experienceLevels) && !experienceLevels.isEmpty()) {
//            Predicate experienceLevelPredicates = experienceLevels.stream()
//                    .filter(Objects::nonNull)
//                    .filter(value -> !value.isBlank())
//                    .flatMap(value -> {
//                        log.info("->> CA find value in experienceLevels list: " + value);
//                        if (Keys.Experience.JUNIOR.getName().equals(value)) {
//                            log.info("->> CA return experienceLevels JUNIOR");
//                            return Stream.of(criteriaBuilder.equal(candidateRoot.get(
//                                    Keys.CandidateFilterBy.experienceLevel.getName()), Keys.Experience.JUNIOR.getName()));
//                        } else if (Keys.Experience.MID.getName().equals(value)) {
//                            log.info("->> CA return experienceLevels MID");
//                            return Stream.of(criteriaBuilder.equal(candidateRoot.get(
//                                    Keys.CandidateFilterBy.experienceLevel.getName()), Keys.Experience.MID.getName()));
//                        } else if (Keys.Experience.SENIOR.getName().equals(value)) {
//                            log.info("->> CA return experienceLevels SENIOR");
//                            return Stream.of(criteriaBuilder.equal(candidateRoot.get(
//                                    Keys.CandidateFilterBy.experienceLevel.getName()), Keys.Experience.SENIOR.getName()));
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
//        List<String> remoteWork = candidateSearchCriteria.getRemoteWork();
//        if (Objects.nonNull(remoteWork) && !remoteWork.isEmpty()) {
//            Predicate remoteWorkPredicates = remoteWork.stream()
//                    .filter(Objects::nonNull)
//                    .filter(value -> !value.isBlank())
//                    .flatMap(value -> {
//                        log.error("->> CA find value in remoteWork list: " + value);
//                        if (Keys.RemoteWork.OFFICE.getName().equals(value)) {
//                            log.error("->> CA return remoteWork OFFICE");
//                            return Stream.of(criteriaBuilder.equal(candidateRoot.get(Keys.CandidateFilterBy.remoteWork.getName()), 0));
//                        } else if (Keys.RemoteWork.PARTLY.getName().equals(value)) {
//                            log.error("->> CA return remoteWork PARTLY");
//                            return Stream.of(criteriaBuilder.between(candidateRoot.get(Keys.CandidateFilterBy.remoteWork.getName()), 1, 99));
//                        } else if (Keys.RemoteWork.FULL.getName().equals(value)) {
//                            log.error("->> CA return remoteWork FULL");
//                            return Stream.of(criteriaBuilder.equal(candidateRoot.get(Keys.CandidateFilterBy.remoteWork.getName()), 100));
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
//        BigDecimal salaryMin = candidateSearchCriteria.getSalaryMin();
//        if (Objects.nonNull(salaryMin) && !salaryMin.equals(BigDecimal.ZERO)) {
//            log.info("->> CA return salaryMin: " + salaryMin);
//            predicates.add(criteriaBuilder.greaterThanOrEqualTo(candidateRoot.get(Keys.CandidateFilterBy.salaryMin.getName()), salaryMin));
//        }
//
//        // Warunek dla listy salary
//        List<String> salary = candidateSearchCriteria.getSalary();
//        if (Objects.nonNull(salary) && !salary.isEmpty()) {
//            Predicate salaryPredicates = salary.stream()
//                    .filter(Objects::nonNull)
//                    .filter(value -> !value.isBlank())
//                    .flatMap(value -> {
//                        log.info("->> CA find value in salary list: " + value);
//                        if (Keys.Salary.WITH.getName().equals(value)) {
//                            log.info("->> CA return salary WITH");
//                            return Stream.of(criteriaBuilder.isNotNull(candidateRoot.get(Keys.CandidateFilterBy.salaryMin.getName())), criteriaBuilder.isNotNull(candidateRoot.get(Keys.CandidateFilterBy.salaryMax.getName())));
//                        } else if (Keys.Salary.UNDISCLOSED.getName().equals(value)) {
//                            log.info("->> CA return salary UNDISCLOSED");
//                            return Stream.of(criteriaBuilder.isNull(candidateRoot.get(Keys.CandidateFilterBy.salaryMin.getName())), criteriaBuilder.isNull(candidateRoot.get(Keys.CandidateFilterBy.salaryMax.getName())));
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
//        List<String> status = candidateSearchCriteria.getStatus();
//        if (Objects.nonNull(status) && !status.isEmpty()) {
//            List<Predicate> statusPredicates = status.stream()
//                    .filter(Objects::nonNull)
//                    .filter(value -> !value.isBlank())
//                    .map(value -> {
//                        log.info("->> CA find value in status list: " + value);
//                        if (Keys.CandidateState.ACTIVE.getName().equals(value)) {
//                            log.info("->> CA return status ACTIVE");
//                            return criteriaBuilder.like(candidateRoot.get(Keys.CandidateFilterBy.status.getName()), Keys.CandidateState.ACTIVE.getName());
//                        } else if (Keys.CandidateState.EXPIRED.getName().equals(value)) {
//                            log.info("->> CA return status EXPIRED");
//                            return criteriaBuilder.equal(candidateRoot.get(Keys.CandidateFilterBy.status.getName()), Keys.CandidateState.EXPIRED.getName());
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
//        String city = candidateSearchCriteria.getCity();
//        if (Objects.nonNull(city) && !city.isBlank()) {
//            log.info("->> CA return city: " + city);
//            predicates.add(criteriaBuilder.equal(candidateRoot.get(Keys.CandidateFilterBy.cityId.getName())
//                    .get(Keys.CandidateFilterBy.cityName.getName()), city));
//        }
//
//        // Warunek dla candidate
//        String candidate = candidateSearchCriteria.getCandidate();
//        if (Objects.nonNull(candidate) && !candidate.isBlank()) {
//            log.info("->> CA return candidate: " + candidate);
//            predicates.add(criteriaBuilder.equal(candidateRoot.get(Keys.CandidateFilterBy.candidateId.getName())
//                    .get(Keys.CandidateFilterBy.companyName.getName()), candidate));
//        }
//
//        // Warunek dla listy skills
//        List<String> skills = candidateSearchCriteria.getSkills();
//        if (Objects.nonNull(skills) && !skills.isEmpty()) {
//            Predicate skillPredicates =
//                    skills.stream()
//                    .filter(Objects::nonNull)
//                    .filter(value -> !value.isBlank())
//                    .filter(value -> Keys.LIST_OF_SKILLS.LIST_OF_SKILLS.getFields()
//                            .contains(value))
//                            .map(value -> {
//                                Subquery<CandidateSkillEntity> subquery = criteriaQuery.subquery(CandidateSkillEntity.class);
//                                Root<CandidateSkillEntity> candidateSkillRoot = subquery.from(CandidateSkillEntity.class);
//                                subquery.select(candidateSkillRoot);
//
//                                Predicate candidateIdPredicate = criteriaBuilder.equal(candidateSkillRoot.get(Keys.CandidateFilterBy.candidateId.getName()), candidateRoot);
//                                Predicate skillNamePredicate = criteriaBuilder.equal(candidateSkillRoot.get(Keys.CandidateFilterBy.skillId.getName()).get(Keys.CandidateFilterBy.skillName.getName()), value);
//
//                                return criteriaBuilder.exists(subquery.where(criteriaBuilder.and(candidateIdPredicate, skillNamePredicate)));
//                            })
//                            .reduce(criteriaBuilder::and)
//                            .orElse(criteriaBuilder.conjunction());
//
//            predicates.add(skillPredicates);
//        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(CandidateSearchCriteria candidateSearchCriteria,
                          CriteriaQuery<CandidateEntity> criteriaQuery,
                          Root<CandidateEntity> candidateEntityRoot) {

        if (candidateSearchCriteria.getSortBy().contains(".")) {
            String[] split = candidateSearchCriteria.getSortBy().split("[.]");
            if (candidateSearchCriteria.getSortDirection().equals(Sort.Direction.ASC)) {
                log.info("->> CA Sorting ASC with: " + split[0] + " and " + split[1]);
                criteriaQuery.orderBy(criteriaBuilder.asc(candidateEntityRoot.get(split[0]).get(split[1])));
            } else {
                log.info("->> CA Sorting DESC with: " + split[0] + " and " + split[1]);
                criteriaQuery.orderBy(criteriaBuilder.desc(candidateEntityRoot.get(split[0]).get(split[1])));
            }
        } else {
            if (candidateSearchCriteria.getSortDirection().equals(Sort.Direction.ASC)) {
                log.info("->> CA Sorting ASC with: " + candidateSearchCriteria.getSortBy());
                criteriaQuery.orderBy(criteriaBuilder.asc(candidateEntityRoot.get(candidateSearchCriteria.getSortBy())));
            } else {
                log.info("->> CA Sorting DESC with: " + candidateSearchCriteria.getSortBy());
                criteriaQuery.orderBy(criteriaBuilder.desc(candidateEntityRoot.get(candidateSearchCriteria.getSortBy())));
            }
        }
    }

    private Pageable getPageable(CandidateSearchCriteria candidateSearchCriteria) {
        log.info("->> CA Pageable pageNumber: " + (candidateSearchCriteria.getPageNumber() - 1));
        Sort sort = Sort.by(candidateSearchCriteria.getSortDirection(), candidateSearchCriteria.getSortBy());
        return PageRequest.of(candidateSearchCriteria.getPageNumber() - 1, candidateSearchCriteria.getPageSize(), sort);
    }

    private long getCandidateCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<CandidateEntity> countRoot = countQuery.from(CandidateEntity.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        TypedQuery<Long> typedQuery = entityManager.createQuery(countQuery);
        return typedQuery.getSingleResult();

    }


}
