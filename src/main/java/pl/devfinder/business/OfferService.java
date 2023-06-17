package pl.devfinder.business;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.devfinder.business.dao.OfferDAO;
import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.Employer;
import pl.devfinder.domain.Offer;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class OfferService {
    private final OfferDAO offerDAO;

@Transactional
    public Long getNumberOfOffersByEmployerAndByState(Long employerId, Keys.OfferState state) {
        return offerDAO.getNumberOfOffersByEmployerAndByState (employerId ,state);
    }

    @Transactional
    public List<Offer> findAllByState(Keys.OfferState state) {
        List<Offer> allOffers = offerDAO.findAllByState(state);
        log.info("Count Offers: [{}]", allOffers.size());
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
        if(remote == null || remote == 0) {
            return "No";
        }
        return String.valueOf(remote) + " % time";
    }
}
