package pl.devfinder.infrastructure.database.repository;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.devfinder.business.dao.OfferDAO;
import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.Offer;
import pl.devfinder.infrastructure.database.repository.jpa.OfferJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.OfferEntityMapper;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class OfferRepository implements OfferDAO {
    private final OfferJpaRepository offerJpaRepository;
    private final OfferEntityMapper offerEntityMapper;


    @Override
    public List<Offer> findAllByState(Keys.OfferState state) {
        return offerJpaRepository.findAllByState(state.getName()).stream()
                .map(offerEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public Page<Offer> findAllByState(Keys.OfferState state, Pageable pageable) {
        return offerJpaRepository.findAllByState(state.getName(),pageable).map(offerEntityMapper::mapFromEntity);
    }

    @Override
    public Long getNumberOfOffersByEmployerAndByState(Long employerId, Keys.OfferState offerState) {
        return offerJpaRepository.getNumberOfOffersByEmployerAndByState(employerId, offerState.getName());
    }

    @Override
    public Optional<Offer> findById(Long offerId) {
        return offerJpaRepository.findById(offerId).map(offerEntityMapper::mapFromEntity);
    }

//    @Override
//    public Page<Offer> findAllByStatePaginated(Integer pageNumber, Integer pageSize, Keys.OfferState state) {
//        return offerJpaRepository.findAll();
//    }


}
