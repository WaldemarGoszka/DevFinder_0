package pl.devfinder.infrastructure.database.repository;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.devfinder.business.dao.OfferDAO;
import pl.devfinder.business.management.Keys;
import pl.devfinder.domain.Employer;
import pl.devfinder.domain.Offer;
import pl.devfinder.infrastructure.database.entity.EmployerEntity;
import pl.devfinder.infrastructure.database.repository.jpa.OfferJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.EmployerEntityMapper;
import pl.devfinder.infrastructure.database.repository.mapper.OfferEntityMapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;
@Slf4j
@Repository
@AllArgsConstructor
public class OfferRepository implements OfferDAO {
    private final OfferJpaRepository offerJpaRepository;
    private final OfferEntityMapper offerEntityMapper;
    private final EmployerEntityMapper employerEntityMapper;


    @Override
    public List<Offer> findAllByState(Keys.OfferState state) {
        return offerJpaRepository.findAllByState(state.getName()).stream()
                .map(offerEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public Page<Offer> findAllByState(Keys.OfferState state, Pageable pageable) {
        return offerJpaRepository.findAllByState(state.getName(), pageable).map(offerEntityMapper::mapFromEntity);
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
    @Override
    public long countByCityName(String cityName) {
        return offerJpaRepository.countByCityName(cityName);
    }

    @Override
    public Optional<Offer>  findByOfferIdAndEmployerId(Long offerId, Employer employer) {
        return offerJpaRepository.findByOfferIdAndEmployerId(offerId,employerEntityMapper
                .mapToEntity(employer)).map(offerEntityMapper::mapFromEntity);
    }

    @Override
    public void deleteAllByEmployerId(Employer employer) {
        offerJpaRepository.deleteAllByEmployerId(employerEntityMapper.mapToEntity(employer));
    }

    @Override
    public List<Offer> findAllByEmployer(Employer employer) {
        return offerJpaRepository.findAllByEmployerId(employerEntityMapper.mapToEntity(employer)).stream().map(offerEntityMapper::mapFromEntity).toList();
    }

    @Override
    public void deleteById(Long offerId) {
        offerJpaRepository.deleteById(offerId);
    }

    @Override
    public Offer save(Offer offer) {
        log.info("Process save offer : [{}]",offer);
        return offerEntityMapper.mapFromEntity(offerJpaRepository.saveAndFlush(offerEntityMapper.mapToEntity(offer)));
    }


}
