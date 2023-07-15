package pl.devfinder.infrastructure.database.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.CandidateSearchCriteria;
import pl.devfinder.infrastructure.database.entity.CandidateEntity;
import pl.devfinder.infrastructure.database.repository.mapper.CandidateEntityMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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
        log.info("Trying Find By Criteria");
        CriteriaQuery<CandidateEntity> criteriaQuery = criteriaBuilder.createQuery(CandidateEntity.class);
        Root<CandidateEntity> candidateEntityRoot = criteriaQuery.from(CandidateEntity.class);
        Predicate predicate = getPredicate(candidateSearchCriteria, candidateEntityRoot);
        criteriaQuery.where(predicate);
        setOrder(candidateSearchCriteria, criteriaQuery, candidateEntityRoot);

        TypedQuery<CandidateEntity> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(candidateSearchCriteria.getPageNumber() * candidateSearchCriteria.getPageSize());
        typedQuery.setMaxResults(candidateSearchCriteria.getPageSize());

        Pageable pageable = getPageable(candidateSearchCriteria);

        long candidateCount = getCandidateCount(predicate);
        PageImpl<CandidateEntity> candidateEntities = new PageImpl<>(typedQuery.getResultList(), pageable, candidateCount);

        return candidateEntities.map(candidateEntityMapper::mapFromEntity);
    }

    private Predicate getPredicate(CandidateSearchCriteria candidateSearchCriteria,
                                   Root<CandidateEntity> candidateRoot) {
        List<Predicate> predicates = new ArrayList<>();

//        if (Objects.nonNull(candidateSearchCriteria.getExperienceLevels())
//                && !candidateSearchCriteria.getExperienceLevels().isEmpty()) {
//            predicates.add(
//                    criteriaBuilder.like(candidateRoot.get("experienceLevel"),
//                            Keys.Experience.JUNIOR.getName())
//            );
//        }

        // Warunek dla listy experienceLevels
        List<String> experienceLevels = candidateSearchCriteria.getExperienceLevels();
        if (Objects.nonNull(experienceLevels) && !experienceLevels.isEmpty()) {
            List<Predicate> experienceLevelPredicates = experienceLevels.stream()
                    .filter(Objects::nonNull)
                    .filter(value -> !value.isBlank())
                    .flatMap(value -> {
                        if (Keys.Experience.JUNIOR.getName().equals(value)) {
                            log.info("->> CA experienceLevels JUNIOR");
                            return Stream.of(criteriaBuilder.equal(candidateRoot.get(
                                    Keys.CandidateFilterBy.experienceLevel.getName()), Keys.Experience.JUNIOR.getName()));
                        } else if (Keys.Experience.MID.getName().equals(value)) {
                            log.info("->> CA experienceLevels MID");
                            return Stream.of(criteriaBuilder.equal(candidateRoot.get(
                                    Keys.CandidateFilterBy.experienceLevel.getName()), Keys.Experience.MID.getName()));
                        } else if (Keys.Experience.SENIOR.getName().equals(value)) {
                            log.info("->> CA experienceLevels SENIOR");
                            return Stream.of(criteriaBuilder.equal(candidateRoot.get(
                                    Keys.CandidateFilterBy.experienceLevel.getName()), Keys.Experience.SENIOR.getName()));
                        } else {
                            log.info("->> CA experienceLevels NoFilter");
                            return Stream.empty();
                        }
                    })
                    .toList();
            predicates.addAll(experienceLevelPredicates);
        }

        // Warunek dla listy remoteWork
        List<String> remoteWork = candidateSearchCriteria.getRemoteWork();
        if (Objects.nonNull(remoteWork) && !remoteWork.isEmpty()) {
            List<Predicate> remoteWorkPredicates = remoteWork.stream()
                    .filter(Objects::nonNull)
                    .filter(value -> !value.isBlank())
                    .flatMap(value -> {
                        if (Keys.RemoteWork.OFFICE.getName().equals(value)) {
                            log.info("->> CA remoteWork OFFICE");
                            return Stream.of(criteriaBuilder.equal(candidateRoot.get(Keys.CandidateFilterBy.remoteWork.getName()), 0));
                        } else if (Keys.RemoteWork.PARTLY.getName().equals(value)) {
                            log.info("->> CA remoteWork PARTLY");
                            return Stream.of(criteriaBuilder.between(candidateRoot.get(Keys.CandidateFilterBy.remoteWork.getName()), 1, 99));
                        } else if (Keys.RemoteWork.FULL.getName().equals(value)) {
                            log.info("->> CA remoteWork FULL");
                            return Stream.of(criteriaBuilder.equal(candidateRoot.get(Keys.CandidateFilterBy.remoteWork.getName()), 100));
                        } else {
                            log.info("->> CA remoteWork NoFilter");
                            return Stream.empty();
                        }
                    })
                    .toList();
            predicates.addAll(remoteWorkPredicates);
        }

        // Warunek dla salaryMin
        BigDecimal salaryMin = candidateSearchCriteria.getSalaryMin();
        if (Objects.nonNull(salaryMin) && salaryMin.equals(BigDecimal.ZERO)) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(candidateRoot.get(Keys.CandidateFilterBy.salaryMin.getName()), salaryMin));
        }

        // Warunek dla listy salary
        List<String> salary = candidateSearchCriteria.getSalary();
        if (Objects.nonNull(salary) && !salary.isEmpty()) {
            List<Predicate> salaryPredicates = salary.stream()
                    .filter(Objects::nonNull)
                    .filter(value -> !value.isBlank())
                    .flatMap(value -> {
                        if (Keys.Salary.WITH.getName().equals(value)) {
                            log.info("->> CA salary WITH");
                            return Stream.of(criteriaBuilder.isNotNull(candidateRoot.get(Keys.CandidateFilterBy.salaryMin.getName())), criteriaBuilder.isNotNull(candidateRoot.get(Keys.CandidateFilterBy.salaryMax.getName())));
                        } else if (Keys.Salary.UNDISCLOSED.getName().equals(value)) {
                            log.info("->> CA salary UNDISCLOSED");
                            return Stream.of(criteriaBuilder.isNull(candidateRoot.get(Keys.CandidateFilterBy.salaryMin.getName())), criteriaBuilder.isNull(candidateRoot.get(Keys.CandidateFilterBy.salaryMax.getName())));
                        }
//                        else {
//                            return Stream.of(criteriaBuilder.equal(candidateRoot.get(Keys.CandidateFilterBy.salary.getName()), value));
//                        }
                        else {
                            log.info("->> CA salary NoFilter");
                            return Stream.empty();
                        }
                    })
                    .toList();
            predicates.addAll(salaryPredicates);
        }

//        // Warunek dla listy status
//        List<String> status = candidateSearchCriteria.getStatus();
//        if (Objects.nonNull(status) && !status.isEmpty()) {
//            List<Predicate> statusPredicates = status.stream()
//                    .filter(Objects::nonNull)
//                    .filter(value -> !value.isBlank())
//                    .flatMap(value -> {
//                        if (Keys.CandidateState.ACTIVE.getName().equals(value)) {
//                            log.info("->> CA status ACTIVE");
//                            return Stream.of(criteriaBuilder.equal(candidateRoot.get(Keys.CandidateFilterBy.status.getName()), Keys.CandidateState.ACTIVE.getName()));
//                        } else if (Keys.CandidateState.EXPIRED.getName().equals(value)) {
//                            log.info("->> CA status EXPIRED");
//                            return Stream.of(criteriaBuilder.equal(candidateRoot.get(Keys.CandidateFilterBy.status.getName()), Keys.CandidateState.EXPIRED.getName()));
//                        } else {
//                            log.info("->> CA status NoFilter");
//                            return Stream.empty();
//                        }
//                    })
//                    .toList();
//            predicates.addAll(statusPredicates);
//        }

        // Warunek dla city
        String city = candidateSearchCriteria.getCity();
        if (Objects.nonNull(city) && !city.isBlank()) {
            log.info("->> CA city");
            predicates.add(criteriaBuilder.equal(candidateRoot.get(Keys.CandidateFilterBy.cityId.getName())
                    .get(Keys.CandidateFilterBy.cityName.getName()), city));
        }

        // Warunek dla employer
        String employer = candidateSearchCriteria.getEmployer();
        if (Objects.nonNull(employer) && !employer.isBlank()) {
            log.info("->> CA employer");
            predicates.add(criteriaBuilder.equal(candidateRoot.get(Keys.CandidateFilterBy.employerId.getName())
                    .get(Keys.CandidateFilterBy.companyName.getName()), employer));
        }

        // Warunek dla listy skills
        List<String> skills = candidateSearchCriteria.getSkills();
        if (Objects.nonNull(skills) && !skills.isEmpty()) {
            List<Predicate> skillPredicates = skills.stream()
                    .filter(Objects::nonNull)
                    .filter(value -> !value.isBlank())
                    .flatMap(value -> {
                        if (Arrays.asList(
                                "Python",
                                "JavaScript",
                                "C++",
                                "SQL",
                                "HTML",
                                "CSS",
                                "PHP",
                                "Ruby",
                                "Swift",
                                "Go",
                                "Kotlin",
                                "React",
                                "Angular",
                                "Vue.js",
                                "Node.js",
                                "Docker",
                                "Git",
                                "AWS",
                                "Machine Learning",
                                "Data Science",
                                "Ruby on Rails",
                                "ASP.NET",
                                "Laravel",
                                "Spring Framework",
                                "Express.js",
                                "MongoDB",
                                "PostgreSQL",
                                "Firebase",
                                "Elasticsearch",
                                "GraphQL",
                                "AWS Lambda",
                                "Azure",
                                "Google Cloud Platform",
                                "DevOps",
                                "Scrum",
                                "Kubernetes",
                                "Microservices",
                                "RESTful API",
                                "Test Driven Development",
                                "Agile Project Management",
                                "Spring Boot",
                                "Hibernate",
                                "JavaFX",
                                "JPA",
                                "JUnit",
                                "Maven",
                                "Gradle"
                        ).contains(value)) {
                            log.info("->> CA skills");
                            return Stream.of(criteriaBuilder.equal(candidateRoot.get(
                                            Keys.CandidateFilterBy.candidateSkills.getName())
                                    .get(Keys.CandidateFilterBy.skillId.getName())
                                    .get(Keys.CandidateFilterBy.skillName.getName()), value));
                        } else {
                            return Stream.empty();
                        }
                    })
                    .toList();
            predicates.addAll(skillPredicates);
        }


        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(CandidateSearchCriteria candidateSearchCriteria,
                          CriteriaQuery<CandidateEntity> criteriaQuery,
                          Root<CandidateEntity> candidateEntityRoot) {
        if (candidateSearchCriteria.getSortDirection().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(candidateEntityRoot.get(candidateSearchCriteria.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(candidateEntityRoot.get(candidateSearchCriteria.getSortBy())));
        }
    }

    private Pageable getPageable(CandidateSearchCriteria candidateSearchCriteria) {
        Sort sort = Sort.by(candidateSearchCriteria.getSortDirection(), candidateSearchCriteria.getSortBy());
        return PageRequest.of(candidateSearchCriteria.getPageNumber(), candidateSearchCriteria.getPageSize(), sort);
    }

    private long getCandidateCount(Predicate predicate) {
//        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
//        Root<CandidateEntity> countRoot = countQuery.from(CandidateEntity.class);
//        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
//        return entityManager.createQuery(countQuery).getSingleResult();

        // z bing
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
//        Root<CandidateEntity> countRoot = countQuery.from(CandidateEntity.class);
//        countQuery.select(builder.count(countRoot)).where(predicate);
//        return entityManager.createQuery(countQuery).getSingleResult();

        //z gpt
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<CandidateEntity> countRoot = countQuery.from(CandidateEntity.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        TypedQuery<Long> typedQuery = entityManager.createQuery(countQuery);
        return typedQuery.getSingleResult();
    }

}
