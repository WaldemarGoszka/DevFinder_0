package pl.devfinder.business.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.Employer;
import pl.devfinder.domain.Offer;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface OfferDAO {
    List<Offer> findAllByState(Keys.OfferState state);

    Page<Offer> findAllByState(Keys.OfferState state, Pageable pageable);

    Long getNumberOfOffersByEmployerAndByState(Long employerId, Keys.OfferState state);

    Optional<Offer> findById(Long offerId);

    //    Page<Offer> findAllByStatePaginated(Integer pageNumber, Integer pageSize, Keys.OfferState state);
    long countByCityName(String cityName);

    Optional<Offer> findByOfferIdAndEmployerId(Long offerId, Employer employer);

    void deleteAllByEmployerId(Employer employer);

    List<Offer> findAllByEmployer(Employer employer);

    void deleteById(Long offerId);

    Offer save(Offer offer);
}
