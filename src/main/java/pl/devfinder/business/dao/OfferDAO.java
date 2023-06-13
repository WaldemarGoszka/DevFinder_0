package pl.devfinder.business.dao;


import pl.devfinder.domain.Offer;

import java.util.List;

public interface OfferDAO {

    Integer offerCountByEmployerId(Integer employerId);

    List<Offer> findAll();

    Integer getNumberOfAvailableOffers(Long employerId);
}
