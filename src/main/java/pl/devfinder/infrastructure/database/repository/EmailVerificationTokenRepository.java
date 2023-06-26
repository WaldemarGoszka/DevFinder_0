package pl.devfinder.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.devfinder.business.dao.EmailVerificationTokenDAO;
import pl.devfinder.domain.EmailVerificationToken;
import pl.devfinder.infrastructure.database.repository.jpa.EmailVerificationTokenJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.EmailVerificationTokenMapper;

@Repository
@AllArgsConstructor
public class EmailVerificationTokenRepository implements EmailVerificationTokenDAO {
    private final EmailVerificationTokenJpaRepository emailVerificationTokenJpaRepository;
    private final EmailVerificationTokenMapper emailVerificationTokenMapper;

    @Override
    public void save(EmailVerificationToken emailVerificationToken) {
        emailVerificationTokenJpaRepository.save(emailVerificationTokenMapper.mapToEntity(emailVerificationToken));
    }
}
