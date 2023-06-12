package pl.devfinder.infrastructure.database.repository;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.devfinder.business.dao.OfferDAO;

@Repository
@AllArgsConstructor
public class OfferRepository implements OfferDAO {

    @Override
    public Integer offerCountByEmployerId(Integer employerId) {
        return null;
    }
}
