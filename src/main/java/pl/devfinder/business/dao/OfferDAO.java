package pl.devfinder.business.dao;


import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.Offer;

import java.util.List;

public interface OfferDAO {
    List<Offer> findAllByState(Keys.OfferState state);
    Long getNumberOfOffersByEmployerAndByState(Long employerId, Keys.OfferState state);

}
