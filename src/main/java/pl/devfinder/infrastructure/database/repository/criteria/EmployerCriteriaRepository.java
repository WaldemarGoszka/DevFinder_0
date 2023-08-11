package pl.devfinder.infrastructure.database.repository.criteria;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
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
import pl.devfinder.infrastructure.database.entity.OfferSkillEntity;
import pl.devfinder.infrastructure.database.entity.SkillEntity;
import pl.devfinder.infrastructure.database.repository.mapper.EmployerEntityMapper;

import java.util.*;
import java.util.stream.Collectors;
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
        CriteriaQuery<EmployerEntity> criteriaQuery = criteriaBuilder.createQuery(EmployerEntity.class);
        Root<EmployerEntity> employerEntityRoot = criteriaQuery.from(EmployerEntity.class);
        Predicate predicate = getPredicate(employerSearchCriteria, employerEntityRoot, criteriaQuery);

        criteriaQuery.where(predicate);
        setOrder(employerSearchCriteria, criteriaQuery, employerEntityRoot);

        TypedQuery<EmployerEntity> typedQuery = entityManager.createQuery(criteriaQuery);
//////////////// Obejście problermu wyrzucającego błąd z metody getEmployerCount(predicate)iczanie ilości wszystkich
//        elementów uwzględniając predykaty
        PageImpl<EmployerEntity> employerEntitiesToCountItems = new PageImpl<>(typedQuery.getResultList(),
                PageRequest.of(0, Integer.MAX_VALUE,
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

//         Warunek dla listy jobOffersStatus
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

        // Warunek dla city
        String city = employerSearchCriteria.getCity();
        if (Objects.nonNull(city) && !city.isBlank()) {
            log.info("->> CA return city: " + city);
            predicates.add(criteriaBuilder.equal(employerRoot.get(Keys.EmployerFilterBy.cityId.getName())
                    .get(Keys.EmployerFilterBy.cityName.getName()), city));
        }

        // Warunek dla skilli w ofertach employera
        List<String> skillsInOffers = employerSearchCriteria.getSkillsInOffers();
        if (Objects.nonNull(skillsInOffers) && !skillsInOffers.isEmpty()) {

            Subquery<Long> offerSubquery = criteriaQuery.subquery(Long.class);
            Root<OfferEntity> offerRoot = offerSubquery.from(OfferEntity.class);
            Join<OfferEntity, SkillEntity> offerSkillJoin = offerRoot.join("offerSkills").join("skillId");

            List<Predicate> skillPredicates = skillsInOffers.stream()
                    .map(skillName -> criteriaBuilder.equal(offerSkillJoin.get("skillName"), skillName))
                    .collect(Collectors.toList());

            Predicate activeStatusPredicate = criteriaBuilder.equal(offerRoot.get("status"), "ACTIVE");

            Expression<Long> employerIdExpression = offerRoot.get("employerId").get("employerId");
            offerSubquery.select(employerIdExpression)
                    .where(criteriaBuilder.and(activeStatusPredicate, criteriaBuilder.and(skillPredicates.toArray(new Predicate[0]))))
                    .groupBy(employerIdExpression)
                    .having(criteriaBuilder.equal(criteriaBuilder.countDistinct(offerSkillJoin), skillsInOffers.size()));

            predicates.add(criteriaBuilder.in(employerRoot.get("employerId")).value(offerSubquery));
        }


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
