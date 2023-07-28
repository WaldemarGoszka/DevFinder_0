package pl.devfinder.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.devfinder.business.dao.EmployerDAO;
import pl.devfinder.domain.Employer;
import pl.devfinder.domain.Offer;
import pl.devfinder.domain.exception.NotFoundException;
import pl.devfinder.domain.search.EmployerSearchCriteria;
import pl.devfinder.domain.User;
import pl.devfinder.infrastructure.database.repository.criteria.EmployerCriteriaRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class EmployerService {

    private final EmployerDAO employerDAO;
    private final EmployerCriteriaRepository employerCriteriaRepository;

    public Employer findById(Long employerId) {
        log.info("Trying find employerById, id: [{}]", employerId);
        return employerDAO.findById(employerId).orElseThrow(() -> new NotFoundException(
                "Could not find employer by Id [%s]".formatted(employerId)));
    }

    @Transactional
    public List<Employer> findAll() {
        List<Employer> allEmployers = employerDAO.findAll();
        log.info("Employers ammount: [{}]", allEmployers.size());
        return allEmployers;
    }

    public void save(User user) {
        //TODO
        Employer employer = Employer.builder()
                .employerUuid(user.getUserUuid())
                .createdAt(OffsetDateTime.now())
                .build();
        employerDAO.save(employer);
    }

    public Page<Employer> findAllByCriteria(EmployerSearchCriteria employerSearchCriteria) {
        return employerCriteriaRepository.findAllByCriteria(employerSearchCriteria);
    }

    public Optional<Employer> findByEmployerUuid(String uuid) {
        return employerDAO.findByEmployerUuid(uuid);
    }
}
