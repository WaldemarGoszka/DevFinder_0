package pl.devfinder.infrastructure.database.repository.jpa;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.devfinder.infrastructure.database.entity.OfferEntity;

import java.util.List;

@Repository
public interface OfferJpaRepository extends JpaRepository<OfferEntity, Long> {
//TODO zamienić Count(*) na Count(offer) i sprawdzić różnice w zapytaniach
    @Query("""
            SELECT COUNT(*) FROM OfferEntity offer WHERE offer.employerId.employerId = :employerId AND offer.status LIKE :state
            """)
    Long getNumberOfOffersByEmployerAndByState(Long employerId, String state);

    @Query("""
            SELECT offer FROM OfferEntity offer WHERE offer.status LIKE :state
            """)
    List<OfferEntity> findAllByState(String state);

    @Query(""" 
            SELECT offer FROM OfferEntity offer WHERE offer.status LIKE :state
            """)
    Page<OfferEntity> findAllByState(String state, Pageable pageable);


}
