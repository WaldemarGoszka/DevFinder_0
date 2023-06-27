package pl.devfinder.business.dao;


import pl.devfinder.domain.ResetPasswordToken;

import java.util.Optional;

public interface ResetPasswordTokenDAO {
    Optional<ResetPasswordToken> findByToken(String theToken);


    void save(ResetPasswordToken resetPasswordToken);
}
