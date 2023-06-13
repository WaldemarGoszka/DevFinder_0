package pl.devfinder.infrastructure.database.repository;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.devfinder.business.dao.OfferDAO;
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
    public Integer offerCountByEmployerId(Integer employerId) {
        return null;
    }

    @Override
    public List<Offer> findAll() {
        return offerJpaRepository.findAll().stream()
                .map(offerEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public Integer getNumberOfAvailableOffers(Long employerId) {
        return offerJpaRepository.getNumberOfAvailableOffers(employerId);
    }
}
