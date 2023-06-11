package pl.devfinder.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.devfinder.domain.Employer;
import pl.devfinder.infrastructure.database.entity.EmployerEntity;

import java.util.Arrays;
import java.util.List;

@Repository
public interface EmployerJpaRepository extends JpaRepository<EmployerEntity, Integer> {


}
