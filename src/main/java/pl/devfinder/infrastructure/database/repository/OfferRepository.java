package pl.devfinder.infrastructure.database.repository;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.devfinder.business.dao.OfferDAO;
import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.Offer;
import pl.devfinder.infrastructure.database.repository.jpa.OfferJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.OfferEntityMapper;

import java.util.List;

@Repository
@AllArgsConstructor
public class OfferRepository implements OfferDAO {
    private final OfferJpaRepository offerJpaRepository;
    private final OfferEntityMapper offerEntityMapper;


    @Override
    public List<Offer> findAllByState(Keys.OfferState state) {
        return offerJpaRepository.findAllByState(state.getState()).stream()
                .map(offerEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public Long getNumberOfOffersByEmployerAndByState(Long employerId, Keys.OfferState offerState) {
        return offerJpaRepository.getNumberOfOffersByEmployerAndByState(employerId, offerState.getState());
    }




}
