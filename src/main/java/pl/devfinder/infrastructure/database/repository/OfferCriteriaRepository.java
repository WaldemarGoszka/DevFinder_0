package pl.devfinder.infrastructure.database.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.OfferSearchCriteria;
import pl.devfinder.infrastructure.database.entity.OfferEntity;
import pl.devfinder.infrastructure.database.repository.mapper.OfferEntityMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository

public class OfferCriteriaRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public OfferCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<OfferEntity> findAllByCriteria(OfferSearchCriteria offerSearchCriteria) {
        CriteriaQuery<OfferEntity> criteriaQuery = criteriaBuilder.createQuery(OfferEntity.class);
        Root<OfferEntity> offerEntityRoot = criteriaQuery.from(OfferEntity.class);
        Predicate predicate = getPredicate(offerSearchCriteria, offerEntityRoot);
        criteriaQuery.where(predicate);
        setOrder(offerSearchCriteria, criteriaQuery, offerEntityRoot);

        TypedQuery<OfferEntity> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(offerSearchCriteria.getPageNumber() * offerSearchCriteria.getPageSize());
        typedQuery.setMaxResults(offerSearchCriteria.getPageSize());

        Pageable pageable = getPageable(offerSearchCriteria);

        long offerCount = getOfferCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, offerCount);
    }

    private Predicate getPredicate(OfferSearchCriteria offerSearchCriteria,
                                   Root<OfferEntity> offerRoot) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(offerSearchCriteria.getExperienceLevels())
        && !offerSearchCriteria.getExperienceLevels().isEmpty()) {
            predicates.add(
                    criteriaBuilder.like(offerRoot.get("experienceLevel"),
                            Keys.Experience.JUNIOR.getName())
            );
        }


        // Warunek dla listy remoteWork
        List<String> remoteWork = offerSearchCriteria.getRemoteWork();
        if (Objects.nonNull(remoteWork) && !remoteWork.isEmpty()) {
            List<Predicate> remoteWorkPredicates = remoteWork.stream()
                    .map(value -> criteriaBuilder.equal(offerRoot.get("remoteWork"), value))
                    .toList();
            predicates.addAll(remoteWorkPredicates);
        }

        // Warunek dla salaryMin
        BigDecimal salaryMin = offerSearchCriteria.getSalaryMin();
        if (Objects.nonNull(salaryMin) && salaryMin.equals(BigDecimal.ZERO)) {
            predicates.add(criteriaBuilder.greaterThan(offerRoot.get("salary"), salaryMin));
        }

        // Warunek dla listy salary
        List<String> salary = offerSearchCriteria.getSalary();
        if (Objects.nonNull(salary) && !salary.isEmpty()) {
            List<Predicate> salaryPredicates = salary.stream()
                    .map(value -> criteriaBuilder.equal(offerRoot.get("salary"), value))
                    .toList();
            predicates.addAll(salaryPredicates);
        }

        // Warunek dla listy status
        List<String> status = offerSearchCriteria.getStatus();
        if (Objects.nonNull(status) && !status.isEmpty()) {
            List<Predicate> statusPredicates = status.stream()
                    .map(value -> criteriaBuilder.equal(offerRoot.get("status"), value))
                    .toList();
            predicates.addAll(statusPredicates);
        }

        // Warunek dla city
        String city = offerSearchCriteria.getCity();
        if (Objects.nonNull(city) && city.isBlank()) {
            predicates.add(criteriaBuilder.equal(offerRoot.get("city"), city));
        }

        // Warunek dla employer
        String employer = offerSearchCriteria.getEmployer();
        if (Objects.nonNull(employer) && employer.isBlank()) {
            predicates.add(criteriaBuilder.equal(offerRoot.get("employer"), employer));
        }



        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(OfferSearchCriteria offerSearchCriteria,
                          CriteriaQuery<OfferEntity> criteriaQuery,
                          Root<OfferEntity> offerEntityRoot) {
        if (offerSearchCriteria.getSortDirection().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(offerEntityRoot.get(offerSearchCriteria.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(offerEntityRoot.get(offerSearchCriteria.getSortBy())));
        }
    }

    private Pageable getPageable(OfferSearchCriteria offerSearchCriteria) {
        Sort sort = Sort.by(offerSearchCriteria.getSortDirection(), offerSearchCriteria.getSortBy());
        return PageRequest.of(offerSearchCriteria.getPageNumber(), offerSearchCriteria.getPageSize(), sort);
    }

    private long getOfferCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<OfferEntity> countRoot = countQuery.from(OfferEntity.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

}
