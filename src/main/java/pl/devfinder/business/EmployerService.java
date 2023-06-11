package pl.devfinder.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.devfinder.business.dao.EmployerDAO;
import pl.devfinder.domain.Employer;

import java.util.List;
@Slf4j
@Service
@AllArgsConstructor
public class EmployerService {

    private final EmployerDAO employerDAO;

    public List<Employer> findAllEmployers() {
        List<Employer> allEmployers = employerDAO.findAll();
        log.info("Count Employers: [{}]",allEmployers.size());
        return allEmployers;
    }
}
