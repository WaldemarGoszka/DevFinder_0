package pl.devfinder.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.devfinder.business.dao.CandidateDAO;
import pl.devfinder.business.dao.EmployerDAO;
import pl.devfinder.business.dao.OfferDAO;

@Service
@AllArgsConstructor
public class DataService {
    private EmployerDAO employerDAO;
    private OfferDAO offerDAO;
    private CandidateDAO candidateDAO;

    public long countUsesOfCityName(String cityName) {
        long counter = candidateDAO.countByCityName(cityName);
        counter += employerDAO.countByCityName(cityName);
        counter += offerDAO.countByCityName(cityName);
        return counter;
    }
}
