package pl.devfinder.business.dao;


import pl.devfinder.domain.Offer;
import pl.devfinder.domain.OfferSkill;

import java.util.Set;

public interface OfferSkillDAO {

    void saveAll(Set<OfferSkill> offerSkills);

    void deleteAllByOffer(Offer offer);
}
