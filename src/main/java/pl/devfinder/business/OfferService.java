package pl.devfinder.business;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.devfinder.business.dao.OfferDAO;
import pl.devfinder.domain.Employer;
import pl.devfinder.domain.Offer;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class OfferService {
    private final OfferDAO offerDAO;

@Transactional
    public Long getNumberOfAvailableOffers(Long employerId) {
        return offerDAO.getNumberOfAvailableOffers(employerId);
    }

    //    public Integer offerCountByEmployerId(Integer employerId) {
//        Integer offerCount = offerDAO.offerCountByEmployerId(employerId);
//        log.info("Count offer: [{}]", offerCount);
//        return offerCount;
//    }
    @Transactional
    public List<Offer> findAllOffers() {
        List<Offer> allOffers = offerDAO.findAll();
        log.info("Count Offers: [{}]", allOffers.size());
        return allOffers;
    }
}
