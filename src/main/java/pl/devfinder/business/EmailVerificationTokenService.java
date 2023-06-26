package pl.devfinder.business;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.devfinder.business.management.TokenExpirationTime;
import pl.devfinder.domain.EmailVerificationToken;
import pl.devfinder.domain.User;
import pl.devfinder.infrastructure.database.repository.EmailVerificationTokenRepository;

import java.util.Calendar;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailVerificationTokenService {
    private final EmailVerificationTokenRepository emailVerificationTokenRepository;
    private final UserService userService;

    public String validateToken(String token) {
        Optional<EmailVerificationToken> theToken = emailVerificationTokenRepository.findByToken(token);
        if (theToken.isEmpty()){
            return "INVALID";
        }
        User user = theToken.get().getUser();
        Calendar calendar = Calendar.getInstance();
        if ((theToken.get().getExpirationTime().getTime()-calendar.getTime().getTime())<= 0){
            return "EXPIRED";
        }
        user.setEnabled(true);
        userService.save(user);
        return "VALID";
    }

    public void saveVerificationTokenForUser(User user, String token) {
        EmailVerificationToken emailVerificationToken = EmailVerificationToken.builder()
                .token(token)
                .user(user)
                .expirationTime(TokenExpirationTime.getExpirationTime())
                .build();
        emailVerificationTokenRepository.save(emailVerificationToken);
    }

    public Optional<EmailVerificationToken> findByToken(String token) {
        return emailVerificationTokenRepository.findByToken(token);
    }

    public void deleteUserToken(Long id) {
        emailVerificationTokenRepository.deleteByUserId(id);
    }
}
