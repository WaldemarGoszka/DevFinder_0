package pl.devfinder.infrastructure.database.repository.criteria;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.Employer;
import pl.devfinder.domain.search.EmployerSearchCriteria;
import pl.devfinder.infrastructure.database.entity.EmployerEntity;
import pl.devfinder.infrastructure.database.entity.OfferEntity;
import pl.devfinder.infrastructure.database.entity.SkillEntity;
import pl.devfinder.infrastructure.database.repository.mapper.EmployerEntityMapper;

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
        log.info("Process find employer By Criteria: [{}]",employerSearchCriteria);
        CriteriaQuery<EmployerEntity> criteriaQuery = criteriaBuilder.createQuery(EmployerEntity.class);
        Root<EmployerEntity> employerEntityRoot = criteriaQuery.from(EmployerEntity.class);
        Predicate predicate = getPredicate(employerSearchCriteria, employerEntityRoot, criteriaQuery);

        criteriaQuery.where(predicate);
        setOrder(employerSearchCriteria, criteriaQuery, employerEntityRoot);

        TypedQuery<EmployerEntity> typedQuery = entityManager.createQuery(criteriaQuery);

        long employerCount = getEmployerCount(employerSearchCriteria, typedQuery);

        typedQuery.setFirstResult((employerSearchCriteria.getPageNumber() - 1) * employerSearchCriteria.getPageSize());
        typedQuery.setMaxResults(employerSearchCriteria.getPageSize());

        Pageable pageable = getPageable(employerSearchCriteria);

        PageImpl<EmployerEntity> employerEntities = new PageImpl<>(typedQuery.getResultList(), pageable, employerCount);

        return employerEntities.map(employerEntityMapper::mapFromEntity);
    }

    private static long getEmployerCount(EmployerSearchCriteria employerSearchCriteria, TypedQuery<EmployerEntity> typedQuery) {
        PageImpl<EmployerEntity> employerEntitiesToCountItems = new PageImpl<>(typedQuery.getResultList(),
                PageRequest.of(0, Integer.MAX_VALUE,
                        Sort.by(employerSearchCriteria.getSortDirection(), employerSearchCriteria.getSortBy())), 500);
        return employerEntitiesToCountItems.getContent().size();
    }

    private Predicate getPredicate(EmployerSearchCriteria employerSearchCriteria,
                                   Root<EmployerEntity> employerRoot, CriteriaQuery criteriaQuery) {
        List<Predicate> predicates = new ArrayList<>();

        amountJobOffersPredicate(employerSearchCriteria, employerRoot, predicates);
        cityPredicate(employerSearchCriteria, employerRoot, predicates);
        skillInEmployerOffersPredicate(employerSearchCriteria, employerRoot, criteriaQuery, predicates);

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void skillInEmployerOffersPredicate(EmployerSearchCriteria employerSearchCriteria, Root<EmployerEntity> employerRoot, CriteriaQuery criteriaQuery, List<Predicate> predicates) {
        List<String> skillsInOffers = employerSearchCriteria.getSkillsInOffers();
        if (Objects.nonNull(skillsInOffers) && !skillsInOffers.isEmpty()) {

            Subquery<Long> offerSubquery = criteriaQuery.subquery(Long.class);
            Root<OfferEntity> offerRoot = offerSubquery.from(OfferEntity.class);
            Join<OfferEntity, SkillEntity> offerSkillJoin = offerRoot.join("offerSkills").join("skillId");

            List<Predicate> skillPredicates = skillsInOffers.stream()
                    .map(skillName -> criteriaBuilder.equal(offerSkillJoin.get("skillName"), skillName))
                    .toList();

            Predicate activeStatusPredicate = criteriaBuilder.equal(offerRoot.get("status"), "ACTIVE");

            Expression<Long> employerIdExpression = offerRoot.get("employerId").get("employerId");
            offerSubquery.select(employerIdExpression)
                    .where(criteriaBuilder.and(activeStatusPredicate, criteriaBuilder.and(skillPredicates.toArray(new Predicate[0]))))
                    .groupBy(employerIdExpression)
                    .having(criteriaBuilder.equal(criteriaBuilder.countDistinct(offerSkillJoin), skillsInOffers.size()));

            predicates.add(criteriaBuilder.in(employerRoot.get("employerId")).value(offerSubquery));
        }
    }

    private void cityPredicate(EmployerSearchCriteria employerSearchCriteria, Root<EmployerEntity> employerRoot, List<Predicate> predicates) {
        String city = employerSearchCriteria.getCity();
        if (Objects.nonNull(city) && !city.isBlank()) {
            predicates.add(criteriaBuilder.equal(employerRoot.get(Keys.EmployerFilterBy.cityId.getName())
                    .get(Keys.EmployerFilterBy.cityName.getName()), city));
        }
    }

    private void amountJobOffersPredicate(EmployerSearchCriteria employerSearchCriteria, Root<EmployerEntity> employerRoot, List<Predicate> predicates) {
        List<String> jobOffersStatus = employerSearchCriteria.getJobOffersStatus();
        if (Objects.nonNull(jobOffersStatus) && !jobOffersStatus.isEmpty()) {
            Predicate jobOffersStatusPredicate = jobOffersStatus.stream()
                    .filter(Objects::nonNull)
                    .filter(value -> !value.isBlank())
                    .flatMap(value -> {
                        if (Keys.EmployerFilterBy.hasJobOffers.getName().equals(value)) {
                            return Stream.of(criteriaBuilder.greaterThan(employerRoot.get(Keys.EmployerFilterBy.amountOfAvailableOffers.getName()), 0));
                        } else if (Keys.EmployerFilterBy.hasNoJobOffers.getName().equals(value)) {
                            return Stream.of(criteriaBuilder.equal(employerRoot.get(Keys.EmployerFilterBy.amountOfAvailableOffers.getName()), 0));
                        } else {
                            return Stream.empty();
                        }
                    })
                    .reduce(criteriaBuilder::or)
                    .orElse(criteriaBuilder.conjunction());

            predicates.add(jobOffersStatusPredicate);
        }
    }


    private void setOrder(EmployerSearchCriteria employerSearchCriteria,
                          CriteriaQuery<EmployerEntity> criteriaQuery,
                          Root<EmployerEntity> employerEntityRoot) {

        if (employerSearchCriteria.getSortBy().contains(".")) {
            String[] split = employerSearchCriteria.getSortBy().split("[.]");
            if (employerSearchCriteria.getSortDirection().equals(Sort.Direction.ASC)) {
                criteriaQuery.orderBy(criteriaBuilder.asc(employerEntityRoot.get(split[0]).get(split[1])));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.desc(employerEntityRoot.get(split[0]).get(split[1])));
            }
        } else {
            if (employerSearchCriteria.getSortDirection().equals(Sort.Direction.ASC)) {
                criteriaQuery.orderBy(criteriaBuilder.asc(employerEntityRoot.get(employerSearchCriteria.getSortBy())));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.desc(employerEntityRoot.get(employerSearchCriteria.getSortBy())));
            }
        }
    }

    private Pageable getPageable(EmployerSearchCriteria employerSearchCriteria) {
        Sort sort = Sort.by(employerSearchCriteria.getSortDirection(), employerSearchCriteria.getSortBy());
        return PageRequest.of(employerSearchCriteria.getPageNumber() - 1, employerSearchCriteria.getPageSize(), sort);
    }
}
