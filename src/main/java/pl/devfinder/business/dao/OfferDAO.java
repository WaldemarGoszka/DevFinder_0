package pl.devfinder.business.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.Offer;

import java.util.List;
import java.util.Optional;

public interface OfferDAO {
    List<Offer> findAllByState(Keys.OfferState state);
    Page<Offer> findAllByState(Pageable pageable, Keys.OfferState state);
    Long getNumberOfOffersByEmployerAndByState(Long employerId, Keys.OfferState state);

    Optional<Offer> findById(Long offerId);
//    Page<Offer> findAllByStatePaginated(Integer pageNumber, Integer pageSize, Keys.OfferState state);

}
