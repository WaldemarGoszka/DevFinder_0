package pl.devfinder.infrastructure.database.repository.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.devfinder.infrastructure.database.entity.OfferEntity;

import java.util.List;

@Repository
public interface OfferJpaRepository extends JpaRepository<OfferEntity, Long> {

    @Query("""
        SELECT COUNT(*) FROM OfferEntity offer WHERE offer.employerId.employerId = :employerId AND offer.status LIKE :state
        """)
    Long getNumberOfOffersByEmployerAndByState(Long employerId, String state);
    @Query("""
        SELECT offer FROM OfferEntity offer WHERE offer.status LIKE :state
""")
    List<OfferEntity> findAllByState(String state);


}
