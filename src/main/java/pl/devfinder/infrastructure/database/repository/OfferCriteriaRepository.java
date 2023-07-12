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
import pl.devfinder.domain.OfferPage;
import pl.devfinder.domain.OfferSearchCriteria;
import pl.devfinder.infrastructure.database.entity.OfferEntity;

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


    public Page<OfferEntity> findAllWithFilters(OfferPage offerPage,
                                                OfferSearchCriteria offerSearchCriteria) {
        CriteriaQuery<OfferEntity> criteriaQuery = criteriaBuilder.createQuery(OfferEntity.class);
        Root<OfferEntity> offerEntityRoot = criteriaQuery.from(OfferEntity.class);
        Predicate predicate = getPredicate(offerSearchCriteria, offerEntityRoot);
        criteriaQuery.where(predicate);
        setOrder(offerPage, criteriaQuery, offerEntityRoot);

        TypedQuery<OfferEntity> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(offerPage.getPageNumber() * offerPage.getPageSize());
        typedQuery.setMaxResults(offerPage.getPageSize());

        Pageable pageable = getPageable(offerPage);

        long offerCount = getOfferCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, offerCount);
    }

    private Predicate getPredicate(OfferSearchCriteria offerSearchCriteria,
                                   Root<OfferEntity> offerRoot) {
        List<Predicate> predicates = new ArrayList<>();
//        if (Objects.nonNull(offerSearchCriteria.getIsExperienceLevelIsJunior())) {
//            predicates.add(
//                    criteriaBuilder.like(offerRoot.get("experienceLevel"),
//                            Keys.Experience.JUNIOR.getLevel())
//            );
//        }
//        if (Objects.nonNull(offerSearchCriteria.getIsExperienceLevelIsMid())) {
//            predicates.add(
//                    criteriaBuilder.like(offerRoot.get("experienceLevel"),
//                            Keys.Experience.MID.getLevel())
//            );
//        }
//        if (Objects.nonNull(offerSearchCriteria.getIsExperienceLevelIsSenior())) {
//            predicates.add(
//                    criteriaBuilder.like(offerRoot.get("experienceLevel"),
//                            Keys.Experience.SENIOR.getLevel())
//            );
//        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(OfferPage offerPage,
                          CriteriaQuery<OfferEntity> criteriaQuery,
                          Root<OfferEntity> offerEntityRoot) {
        if (offerPage.getSortDirection().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(offerEntityRoot.get(offerPage.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(offerEntityRoot.get(offerPage.getSortBy())));
        }
    }

    private Pageable getPageable(OfferPage offerPage) {
        Sort sort = Sort.by(offerPage.getSortDirection(), offerPage.getSortBy());
        return PageRequest.of(offerPage.getPageNumber(), offerPage.getPageSize(), sort);
    }

    private long getOfferCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<OfferEntity> countRoot = countQuery.from(OfferEntity.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

}
