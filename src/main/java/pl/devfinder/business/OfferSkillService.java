package pl.devfinder.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.devfinder.business.dao.OfferSkillDAO;
import pl.devfinder.domain.Offer;
import pl.devfinder.domain.OfferSkill;

import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class OfferSkillService {
    private final OfferSkillDAO offerSkillDAO;

    public void saveAll(Set<OfferSkill> offerSkills) {
        log.info("Process save all offer skill: [{}]", offerSkills);
        offerSkillDAO.saveAll(offerSkills);
    }

    public void deleteAllByOffer(Offer offer) {
        log.info("Process delete all offer skill, offerId: [{}]", offer.getOfferId());
        offerSkillDAO.deleteAllByOffer(offer);
    }

}
