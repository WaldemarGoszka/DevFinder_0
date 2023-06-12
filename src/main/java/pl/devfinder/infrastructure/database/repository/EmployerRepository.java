package pl.devfinder.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.devfinder.business.dao.EmployerDAO;
import pl.devfinder.domain.Employer;
import pl.devfinder.infrastructure.database.repository.jpa.EmployerJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.EmployerEntityMapper;

import java.util.List;

@Repository
@AllArgsConstructor
public class EmployerRepository implements EmployerDAO {
    private final EmployerJpaRepository employerJpaRepository;
    private final EmployerEntityMapper employerEntityMapper;

    @Override
    public List<Employer> findAll() {
        return employerJpaRepository.findAll().stream()
                .map(employerEntityMapper::mapFromEntity)
                .toList();
    }
}
