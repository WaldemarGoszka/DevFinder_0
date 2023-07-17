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
import pl.devfinder.domain.Offer;
import pl.devfinder.domain.OfferSearchCriteria;
import pl.devfinder.infrastructure.database.entity.OfferEntity;
import pl.devfinder.infrastructure.database.repository.mapper.OfferEntityMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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

    public Page<Offer> findAllByCriteria(OfferSearchCriteria offerSearchCriteria) {
        log.error("Trying Find By Criteria");
        CriteriaQuery<OfferEntity> criteriaQuery = criteriaBuilder.createQuery(OfferEntity.class);
        Root<OfferEntity> offerEntityRoot = criteriaQuery.from(OfferEntity.class);
        Predicate predicate = getPredicate(offerSearchCriteria, offerEntityRoot);
        criteriaQuery.where(predicate);
        setOrder(offerSearchCriteria, criteriaQuery, offerEntityRoot);

        TypedQuery<OfferEntity> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult((offerSearchCriteria.getPageNumber()-1) * offerSearchCriteria.getPageSize());
        typedQuery.setMaxResults(offerSearchCriteria.getPageSize());

        Pageable pageable = getPageable(offerSearchCriteria);

        long offerCount = 19; //getOfferCount(predicate);
        PageImpl<OfferEntity> offerEntities = new PageImpl<>(typedQuery.getResultList(), pageable, offerCount);
        log.error("->> CA count PageImpl size: " + offerEntities.getTotalElements());
        return offerEntities.map(offerEntityMapper::mapFromEntity);
    }

    private Predicate getPredicate(OfferSearchCriteria offerSearchCriteria,
                                   Root<OfferEntity> offerRoot) {
        List<Predicate> predicates = new ArrayList<>();

//        if (Objects.nonNull(offerSearchCriteria.getExperienceLevels())
//                && !offerSearchCriteria.getExperienceLevels().isEmpty()) {
//            predicates.add(
//                    criteriaBuilder.like(offerRoot.get("experienceLevel"),
//                            Keys.Experience.JUNIOR.getName())
//            );
//        }

        // Warunek dla listy experienceLevels
        List<String> experienceLevels = offerSearchCriteria.getExperienceLevels();
        if (Objects.nonNull(experienceLevels) && !experienceLevels.isEmpty()) {
            List<Predicate> experienceLevelPredicates = experienceLevels.stream()
                    .filter(Objects::nonNull)
                    .filter(value -> !value.isBlank())
                    .flatMap(value -> {
                        log.error("->> CA find value in experienceLevels list: " + value);
                        if (Keys.Experience.JUNIOR.getName().equals(value)) {
                            log.error("->> CA return experienceLevels JUNIOR");
                            return Stream.of(criteriaBuilder.equal(offerRoot.get(
                                    Keys.OfferFilterBy.experienceLevel.getName()), Keys.Experience.JUNIOR.getName()));
                        } else if (Keys.Experience.MID.getName().equals(value)) {
                            log.error("->> CA return experienceLevels MID");
                            return Stream.of(criteriaBuilder.equal(offerRoot.get(
                                    Keys.OfferFilterBy.experienceLevel.getName()), Keys.Experience.MID.getName()));
                        } else if (Keys.Experience.SENIOR.getName().equals(value)) {
                            log.error("->> CA return experienceLevels SENIOR");
                            return Stream.of(criteriaBuilder.equal(offerRoot.get(
                                    Keys.OfferFilterBy.experienceLevel.getName()), Keys.Experience.SENIOR.getName()));
                        } else {
                            log.error("->> CA return experienceLevels NoFilter");
                            return Stream.empty();
                        }
                    })
                    .toList();
            predicates.addAll(experienceLevelPredicates);
        }

        // Warunek dla listy remoteWork
        List<String> remoteWork = offerSearchCriteria.getRemoteWork();
        if (Objects.nonNull(remoteWork) && !remoteWork.isEmpty()) {
            List<Predicate> remoteWorkPredicates = remoteWork.stream()
                    .filter(Objects::nonNull)
                    .filter(value -> !value.isBlank())
                    .flatMap(value -> {
                        log.error("->> CA find value in remoteWork list: " + value);
                        if (Keys.RemoteWork.OFFICE.getName().equals(value)) {
                            log.error("->> CA return remoteWork OFFICE");
                            return Stream.of(criteriaBuilder.equal(offerRoot.get(Keys.OfferFilterBy.remoteWork.getName()), 0));
                        } else if (Keys.RemoteWork.PARTLY.getName().equals(value)) {
                            log.error("->> CA return remoteWork PARTLY");
                            return Stream.of(criteriaBuilder.between(offerRoot.get(Keys.OfferFilterBy.remoteWork.getName()), 1, 99));
                        } else if (Keys.RemoteWork.FULL.getName().equals(value)) {
                            log.error("->> CA return remoteWork FULL");
                            return Stream.of(criteriaBuilder.equal(offerRoot.get(Keys.OfferFilterBy.remoteWork.getName()), 100));
                        } else {
                            log.error("->> CA return remoteWork NoFilter");
                            return Stream.empty();
                        }
                    })
                    .toList();
            predicates.addAll(remoteWorkPredicates);
        }

        // Warunek dla salaryMin
        BigDecimal salaryMin = offerSearchCriteria.getSalaryMin();
        if (Objects.nonNull(salaryMin) && salaryMin.equals(BigDecimal.ZERO)) {
            log.error("->> CA return salaryMin: " + salaryMin);
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(offerRoot.get(Keys.OfferFilterBy.salaryMin.getName()), salaryMin));
        }

        // Warunek dla listy salary
        List<String> salary = offerSearchCriteria.getSalary();
        if (Objects.nonNull(salary) && !salary.isEmpty()) {
            List<Predicate> salaryPredicates = salary.stream()
                    .filter(Objects::nonNull)
                    .filter(value -> !value.isBlank())
                    .flatMap(value -> {
                        log.error("->> CA find value in salary list: " + value);
                        if (Keys.Salary.WITH.getName().equals(value)) {
                            log.error("->> CA return salary WITH");
                            return Stream.of(criteriaBuilder.isNotNull(offerRoot.get(Keys.OfferFilterBy.salaryMin.getName())), criteriaBuilder.isNotNull(offerRoot.get(Keys.OfferFilterBy.salaryMax.getName())));
                        } else if (Keys.Salary.UNDISCLOSED.getName().equals(value)) {
                            log.error("->> CA return salary UNDISCLOSED");
                            return Stream.of(criteriaBuilder.isNull(offerRoot.get(Keys.OfferFilterBy.salaryMin.getName())), criteriaBuilder.isNull(offerRoot.get(Keys.OfferFilterBy.salaryMax.getName())));
                        }
//                        else {
//                            return Stream.of(criteriaBuilder.equal(offerRoot.get(Keys.OfferFilterBy.salary.getName()), value));
//                        }
                        else {
                            log.error("->> CA return salary NoFilter");
                            return Stream.empty();
                        }
                    })
                    .toList();
            predicates.addAll(salaryPredicates);
        }

        // Warunek dla listy status
        List<String> status = offerSearchCriteria.getStatus();
        if (Objects.nonNull(status) && !status.isEmpty()) {
            List<Predicate> statusPredicates = status.stream()
                    .filter(Objects::nonNull)
                    .filter(value -> !value.isBlank())
                    .flatMap(value -> {
                        log.error("->> CA find value in status list: " + value);
                        if (Keys.OfferState.ACTIVE.getName().equals(value)) {
                            log.error("->> CA return status ACTIVE");
                            return Stream.of(criteriaBuilder.equal(offerRoot.get(Keys.OfferFilterBy.status.getName()), Keys.OfferState.ACTIVE.getName()));
                        } else if (Keys.OfferState.EXPIRED.getName().equals(value)) {
                            log.error("->> CA return status EXPIRED");
                            return Stream.of(criteriaBuilder.equal(offerRoot.get(Keys.OfferFilterBy.status.getName()), Keys.OfferState.EXPIRED.getName()));
                        } else {
                            log.error("->> CA return status NoFilter");
                            return Stream.empty();
                        }
                    })
                    .toList();
            predicates.addAll(statusPredicates);
        }

        // Warunek dla city
        String city = offerSearchCriteria.getCity();
        if (Objects.nonNull(city) && !city.isBlank()) {
            log.error("->> CA return city: " + city);
            predicates.add(criteriaBuilder.equal(offerRoot.get(Keys.OfferFilterBy.cityId.getName())
                    .get(Keys.OfferFilterBy.cityName.getName()), city));
        }

        // Warunek dla employer
        String employer = offerSearchCriteria.getEmployer();
        if (Objects.nonNull(employer) && !employer.isBlank()) {
            log.error("->> CA return employer: " + employer );
            predicates.add(criteriaBuilder.equal(offerRoot.get(Keys.OfferFilterBy.employerId.getName())
                    .get(Keys.OfferFilterBy.companyName.getName()), employer));
        }

        // Warunek dla listy skills
        List<String> skills = offerSearchCriteria.getSkills();
        if (Objects.nonNull(skills) && !skills.isEmpty()) {
            List<Predicate> skillPredicates = skills.stream()
                    .filter(Objects::nonNull)
                    .filter(value -> !value.isBlank())
                    .flatMap(value -> {
                        log.error("->> CA find value in skills list: " + value);
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
                            log.error("->> CA return skills: " + value);
                            return Stream.of(criteriaBuilder.equal(offerRoot.get(
                                            Keys.OfferFilterBy.offerSkills.getName())
                                    .get(Keys.OfferFilterBy.skillId.getName())
                                    .get(Keys.OfferFilterBy.skillName.getName()), value));
                        } else {
                            return Stream.empty();
                        }
                    })
                    .toList();
            predicates.addAll(skillPredicates);
        }


        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(OfferSearchCriteria offerSearchCriteria,
                          CriteriaQuery<OfferEntity> criteriaQuery,
                          Root<OfferEntity> offerEntityRoot) {

        if(offerSearchCriteria.getSortBy().contains(".")) {
            String[] split = offerSearchCriteria.getSortBy().split("[.]");
            if (offerSearchCriteria.getSortDirection().equals(Sort.Direction.ASC)) {
                log.error("->> CA Sorting ASC with: " + split[0] + " and " + split[1]);
                criteriaQuery.orderBy(criteriaBuilder.asc(offerEntityRoot.get(split[0]).get(split[1])));
            } else {
                log.error("->> CA Sorting DESC with: " + split[0] + " and " + split[1]);
                criteriaQuery.orderBy(criteriaBuilder.desc(offerEntityRoot.get(split[0]).get(split[1])));
            }
        }
        else {
            if (offerSearchCriteria.getSortDirection().equals(Sort.Direction.ASC)) {
                log.error("->> CA Sorting ASC with: " + offerSearchCriteria.getSortBy());
                criteriaQuery.orderBy(criteriaBuilder.asc(offerEntityRoot.get(offerSearchCriteria.getSortBy())));
            } else {
                log.error("->> CA Sorting DESC with: " + offerSearchCriteria.getSortBy());
                criteriaQuery.orderBy(criteriaBuilder.desc(offerEntityRoot.get(offerSearchCriteria.getSortBy())));
            }
        }
    }

    private Pageable getPageable(OfferSearchCriteria offerSearchCriteria) {
        log.error("->> CA Pageable pageNumber: " + (offerSearchCriteria.getPageNumber()-1));
        Sort sort = Sort.by(offerSearchCriteria.getSortDirection(), offerSearchCriteria.getSortBy());
        return PageRequest.of(offerSearchCriteria.getPageNumber()-1, offerSearchCriteria.getPageSize(), sort);
    }

    private long getOfferCount(Predicate predicate) {
//        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
//        Root<OfferEntity> countRoot = countQuery.from(OfferEntity.class);
//        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
//        return entityManager.createQuery(countQuery).getSingleResult();

        // z bing
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
//        Root<OfferEntity> countRoot = countQuery.from(OfferEntity.class);
//        countQuery.select(builder.count(countRoot)).where(predicate);
//        return entityManager.createQuery(countQuery).getSingleResult();

        //z gpt
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<OfferEntity> countRoot = countQuery.from(OfferEntity.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        TypedQuery<Long> typedQuery = entityManager.createQuery(countQuery);
        return typedQuery.getSingleResult();
    }

}
