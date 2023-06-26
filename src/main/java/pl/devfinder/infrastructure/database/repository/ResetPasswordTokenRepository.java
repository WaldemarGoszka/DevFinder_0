package pl.devfinder.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.devfinder.business.dao.ResetPasswordTokenRepositoryDAO;
import pl.devfinder.domain.ResetPasswordToken;
import pl.devfinder.infrastructure.database.repository.jpa.ResetPasswordTokenJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.ResetPasswordTokenMapper;

@Repository
@AllArgsConstructor
public class ResetPasswordTokenRepository implements ResetPasswordTokenRepositoryDAO {
    private final ResetPasswordTokenJpaRepository resetPasswordTokenJpaRepository;
    private final ResetPasswordTokenMapper resetPasswordTokenMapper;

    @Override
    public void save(ResetPasswordToken resetPasswordToken) {
        resetPasswordTokenJpaRepository.save(resetPasswordTokenMapper.mapToEntity(resetPasswordToken));
    }
}
