package pl.devfinder.business;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.devfinder.business.dao.EmailVerificationTokenDAO;
import pl.devfinder.business.management.Keys;
import pl.devfinder.business.management.TokenExpirationTime;
import pl.devfinder.domain.EmailVerificationToken;
import pl.devfinder.domain.User;

import java.time.OffsetDateTime;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class EmailVerificationTokenService {
    private final EmailVerificationTokenDAO emailVerificationTokenDAO;
    private final UserService userService;

    public String validateToken(String token) {
        log.info("Trying validate email verification token: [{}]", token);
        Optional<EmailVerificationToken> emailVerificationToken = emailVerificationTokenDAO.findByToken(token);
        if (emailVerificationToken.isEmpty()){
            return Keys.TokenStatus.INVALID.getName();
        }
        User user = emailVerificationToken.get().getUser();
        if (emailVerificationToken.get().getExpirationTime().isBefore(OffsetDateTime.now())){
            return Keys.TokenStatus.EXPIRED.getName();
        }
        userService.save(user.withIsEnabled(true));
        return Keys.TokenStatus.VALID.getName();
    }

    public void saveVerificationTokenForUser(User user, String token) {
        log.info("Trying save to DB email verification token: [{}] , for user [{}]", token,user);
        EmailVerificationToken emailVerificationToken = EmailVerificationToken.builder()
                .token(token)
                .user(user)
                .expirationTime(TokenExpirationTime.getExpirationTime())
                .build();
        emailVerificationTokenDAO.save(emailVerificationToken);
    }

    public Optional<EmailVerificationToken> findByToken(String token) {
        log.info("Trying find email verification token: [{}]", token);
        return emailVerificationTokenDAO.findByToken(token);
    }

    public void deleteUserEmailVerificationToken(Long tokenId) {
        log.info("Trying delete email verification token Id: [{}]", tokenId);
        emailVerificationTokenDAO.deleteByUserId(tokenId);
    }
}
