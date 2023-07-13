package pl.devfinder.business;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.devfinder.api.dto.OfferDetailsDTO;
import pl.devfinder.business.dao.OfferDAO;
import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.Offer;
import pl.devfinder.domain.OfferPage;
import pl.devfinder.domain.OfferSearchCriteria;
import pl.devfinder.domain.exception.NotFoundException;
import pl.devfinder.infrastructure.database.entity.OfferEntity;
import pl.devfinder.infrastructure.database.repository.OfferCriteriaRepository;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class OfferService {
    private final OfferDAO offerDAO;
    private final OfferCriteriaRepository offerCriteriaRepository;


    public Page<OfferEntity> getOffersFiltering(OfferPage offerPage,
                                                OfferSearchCriteria offerSearchCriteria) {
        return offerCriteriaRepository.findAllWithFilters(offerPage, offerSearchCriteria);
    }

    @Transactional
    public Long getNumberOfOffersByEmployerAndByState(Long employerId, Keys.OfferState state) {
        return offerDAO.getNumberOfOffersByEmployerAndByState(employerId, state);
    }

    @Transactional
    public List<Offer> findAllByState(Keys.OfferState state) {
        List<Offer> allOffers = offerDAO.findAllByState(state);
        log.info("Offers amount: [{}]", allOffers.size());
        return allOffers;
    }

    public String getDaysSinceDate(OffsetDateTime createdAt) {
        long days = Duration.between(createdAt, OffsetDateTime.now()).toDays();
        return String.valueOf(days);
    }

    public String formatSalaryRange(BigDecimal salaryMin, BigDecimal salaryMax) {
        return String.valueOf(salaryMin.toBigInteger()) + " - " + String.valueOf(salaryMax.toBigInteger());
    }

    public String formatRemoteWork(Integer remote) {
        if (remote == null || remote == 0) {
            return "No";
        }
        return String.valueOf(remote) + " % time";
    }

    public Offer findById(Long offerId) {
        log.info("Trying find offerById, id: [{}]", offerId);
        return offerDAO.findById(offerId).orElseThrow(() -> new NotFoundException(
                "Could not find user by userName: [%s]".formatted(offerId)));
    }

    public Page<Offer> findAllByStatePaginated(
            OfferSearchCriteria offerSearchCriteria,
            Keys.OfferState state) {
        Sort sort = Sort.by(offerSearchCriteria.getSortDirection(), offerSearchCriteria.getSortBy());
        Pageable pageable = PageRequest.of(offerSearchCriteria.getPageNumber() - 1, offerSearchCriteria.getPageSize(), sort);
        Page<Offer> all = offerDAO.findAllByState(state, pageable);
        log.info("Offers paginated pageNumber: [{}], page size [{}], totalPages: [{}],  total elements: [{}]",
                offerSearchCriteria.getPageNumber(), offerSearchCriteria.getPageSize(), all.getTotalPages(), all.getTotalElements());
        return all;
    }
}
