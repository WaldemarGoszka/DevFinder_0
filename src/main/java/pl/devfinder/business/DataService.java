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
        //TODO przetestować dodanie miasta i usunięcie gdy nie jesyt już wużywane
    }

//    public void setEmployerService(EmployerService employerService) {
//        this.employerService = employerService;
//    }
//
//    public void setOfferService(OfferService offerService) {
//        this.offerService = offerService;
//    }
//
//    public void setCandidateService(CandidateService candidateService) {
//        this.candidateService = candidateService;
//    }
//
//    public DataService(EmployerService employerService, OfferService offerService, CandidateService candidateService) {
//        setEmployerService(employerService);
//        setOfferService(offerService);
//        setCandidateService(candidateService);
//    }
}
