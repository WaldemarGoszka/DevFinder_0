package pl.devfinder.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.devfinder.infrastructure.database.entity.EmployerEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployerJpaRepository extends JpaRepository<EmployerEntity, Long> {


    Optional<EmployerEntity> findByEmployerUuid(String uuid);

    @Query("""
            SELECT COUNT(o) FROM EmployerEntity o JOIN o.cityId c WHERE c.cityName = :cityName
            """)
    long countByCityName(String cityName);

    @Modifying
    @Query("""
            UPDATE CandidateEntity c SET c.employerId = null, c.status = 'ACTIVE' WHERE c.employerId = :employerEntity
            """)
    void deleteEmployerFromAllCandidatesAndChangeStatus(EmployerEntity employerEntity);

    @Modifying
    @Query("""
            UPDATE CandidateEntity c SET c.employerId = :employer, c.status = 'EMPLOYED' WHERE c.candidateId = :candidateId
            """)
    void assignEmployerToCandidateAndChangeStatus(EmployerEntity employer, Long candidateId);

    @Modifying
    @Query("""
            UPDATE CandidateEntity c SET c.employerId = null, c.status = 'ACTIVE' WHERE c.candidateId = :candidateId
            """)
    void deleteEmployerFromCandidateAndChangeStatus(Long candidateId);

    Optional<EmployerEntity> findByCompanyName(String employerCompanyName);

    List<EmployerEntity> findAllByOrderByCompanyName();

}
