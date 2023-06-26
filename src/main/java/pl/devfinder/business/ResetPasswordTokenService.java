package pl.devfinder.business;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.devfinder.business.management.TokenExpirationTime;
import pl.devfinder.domain.ResetPasswordToken;
import pl.devfinder.domain.User;
import pl.devfinder.infrastructure.database.repository.ResetPasswordTokenRepository;

import java.util.Calendar;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResetPasswordTokenService {
    private final ResetPasswordTokenRepository resetPasswordTokenRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    public String validatePasswordResetToken(String theToken) {
        Optional<ResetPasswordToken> passwordResetToken = resetPasswordTokenRepository.findByToken(theToken);
        if (passwordResetToken.isEmpty()){
            return "invalid";
        }
        Calendar calendar = Calendar.getInstance();
        if ((passwordResetToken.get().getExpirationTime().getTime()-calendar.getTime().getTime())<= 0){
            return "expired";
        }
        return "valid";
    }

    public Optional<User> findUserByPasswordResetToken(String theToken) {
        return Optional.ofNullable(resetPasswordTokenRepository.findByToken(theToken).get().getUser());
    }

    public void resetPassword(User theUser, String newPassword) {
        theUser.setPassword(passwordEncoder.encode(newPassword));
        userService.save(theUser);
    }
    public void createPasswordResetTokenForUser(User user, String token) {
        ResetPasswordToken resetToken = ResetPasswordToken.builder()
                .token(token)
                .user(user)
                .expirationTime(TokenExpirationTime.getExpirationTime())
                .build();
        resetPasswordTokenRepository.save(resetToken);
    }
}
