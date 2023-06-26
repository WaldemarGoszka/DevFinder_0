package pl.devfinder.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.devfinder.infrastructure.database.entity.ResetPasswordTokenEntity;

@Repository
public interface ResetPasswordTokenJpaRepository extends JpaRepository<ResetPasswordTokenEntity, Long> {
}
