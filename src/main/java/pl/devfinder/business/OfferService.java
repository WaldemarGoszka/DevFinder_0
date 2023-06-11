package pl.devfinder.business;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.devfinder.business.dao.OfferDAO;

@Slf4j
@Service
@AllArgsConstructor
public class OfferService {
    private final OfferDAO offerDAO;

    public Integer offerCountByEmployerId(Integer employerId) {
        Integer offerCount = offerDAO.offerCountByEmployerId(employerId);
        log.info("Count offer: [{}]", offerCount);
        return offerCount;
    }
}
