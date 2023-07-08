package pl.devfinder.business;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.devfinder.api.dto.OfferDetailsDTO;
import pl.devfinder.business.dao.OfferDAO;
import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.Offer;
import pl.devfinder.domain.exception.NotFoundException;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class OfferService {
    private final OfferDAO offerDAO;

    @Transactional
    public Long getNumberOfOffersByEmployerAndByState(Long employerId, Keys.OfferState state) {
        return offerDAO.getNumberOfOffersByEmployerAndByState(employerId, state);
    }

    @Transactional
    public List<Offer> findAllByState(Keys.OfferState state) {
        List<Offer> allOffers = offerDAO.findAllByState(state);
        log.info("Offers ammount: [{}]", allOffers.size());
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
        return offerDAO.findById(offerId).orElseThrow(() -> new NotFoundException("Could not find user by userName: [%s]".formatted(offerId)));
    }

    Page<Offer> findAllByStatePaginated(Integer pageNumber, Integer pageSize, Keys.OfferState state) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
//        List<Offer> allOffers = offerDAO.findAllByState(state);
//        log.info("Offers ammount: [{}]", allOffers.size());
        return offerDAO.findAllByState(pageable,state);
    }
}
