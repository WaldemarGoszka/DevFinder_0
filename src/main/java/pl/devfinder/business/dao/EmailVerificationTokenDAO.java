package pl.devfinder.business.dao;


import pl.devfinder.domain.EmailVerificationToken;

import java.util.Optional;

public interface EmailVerificationTokenDAO {
    void deleteByUserId(Long userId);

    Optional<EmailVerificationToken> findByToken(String token);

    void save(EmailVerificationToken emailVerificationToken);

}
