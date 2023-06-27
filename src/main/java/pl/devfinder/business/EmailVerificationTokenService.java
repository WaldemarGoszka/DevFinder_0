package pl.devfinder.business;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.devfinder.business.dao.EmailVerificationTokenDAO;
import pl.devfinder.business.management.TokenExpirationTime;
import pl.devfinder.domain.EmailVerificationToken;
import pl.devfinder.domain.User;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailVerificationTokenService {
    private final EmailVerificationTokenDAO emailVerificationTokenDAO;
    private final UserService userService;

    public String validateToken(String token) {
        Optional<EmailVerificationToken> emailVerificationToken = emailVerificationTokenDAO.findByToken(token);
        if (emailVerificationToken.isEmpty()){
            return "INVALID";
            //TODO zamienić na keys INVALID

        }
        User user = emailVerificationToken.get().getUser();
        if (emailVerificationToken.get().getExpirationTime().isBefore(OffsetDateTime.now())){
            return "EXPIRED";
            //TODO zamienić na keys EXPIRED

        }
        user.withIsEnabled(true); //setEnabled(true);
        userService.save(user);
        return "VALID";
        //TODO zamienić na keys INVALID
    }

    public void saveVerificationTokenForUser(User user, String token) {
        EmailVerificationToken emailVerificationToken = EmailVerificationToken.builder()
                .token(token)
                .user(user)
                .expirationTime(TokenExpirationTime.getExpirationTime())
                .build();
        emailVerificationTokenDAO.save(emailVerificationToken);
    }

    public Optional<EmailVerificationToken> findByToken(String token) {
        return emailVerificationTokenDAO.findByToken(token);
    }

    public void deleteUserEmailVerificationToken(Long tokenId) {
        emailVerificationTokenDAO.deleteByUserId(tokenId);
    }
}
