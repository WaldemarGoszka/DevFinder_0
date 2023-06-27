package pl.devfinder.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.devfinder.domain.EmailVerificationToken;
import pl.devfinder.infrastructure.database.entity.EmailVerificationTokenEntity;

import java.util.Optional;

@Repository
public interface EmailVerificationTokenJpaRepository extends JpaRepository<EmailVerificationTokenEntity, Long> {

    void deleteByUserId(Long userId);

        Optional<EmailVerificationTokenEntity> findByToken (String token);
}
