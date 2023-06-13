package pl.devfinder.infrastructure.database.repository.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.devfinder.infrastructure.database.entity.OfferEntity;

@Repository
public interface OfferJpaRepository extends JpaRepository<OfferEntity, Integer> {

    @Query("""
        SELECT COUNT(*) FROM OfferEntity offer WHERE offer.employerId = :employerId
        """)
    Integer getNumberOfAvailableOffers(Long employerId);
}
