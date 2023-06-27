package pl.devfinder.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.devfinder.business.dao.ResetPasswordTokenDAO;
import pl.devfinder.infrastructure.database.repository.jpa.ResetPasswordTokenJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.ResetPasswordTokenMapper;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class ResetPasswordToken implements ResetPasswordTokenDAO {
    private final ResetPasswordTokenJpaRepository resetPasswordTokenJpaRepository;
    private final ResetPasswordTokenMapper resetPasswordTokenMapper;

    @Override
    public Optional<pl.devfinder.domain.ResetPasswordToken> findByToken(String token) {
        return resetPasswordTokenJpaRepository.findByToken(token).map(resetPasswordTokenMapper::mapFromEntity);
    }

    @Override
    public void save(pl.devfinder.domain.ResetPasswordToken resetPasswordToken) {
        resetPasswordTokenJpaRepository.save(resetPasswordTokenMapper.mapToEntity(resetPasswordToken));
    }
}
