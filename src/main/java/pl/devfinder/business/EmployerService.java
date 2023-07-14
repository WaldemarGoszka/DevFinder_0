package pl.devfinder.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.devfinder.business.dao.EmployerDAO;
import pl.devfinder.domain.Employer;
import pl.devfinder.domain.User;

import java.time.OffsetDateTime;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class EmployerService {

    private final EmployerDAO employerDAO;

    @Transactional
    public List<Employer> findAll() {
        List<Employer> allEmployers = employerDAO.findAll();
        log.info("Employers ammount: [{}]", allEmployers.size());
        return allEmployers;
    }

    public void save(User user) {
        //TODO
        Employer employer = Employer.builder()
                .employerUUId(user.getUserUuid())
                .createdAt(OffsetDateTime.now())
                .build();
        employerDAO.save(employer);
    }
}
