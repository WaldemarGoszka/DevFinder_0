package pl.devfinder.business;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.devfinder.business.dao.ResetPasswordTokenDAO;
import pl.devfinder.business.management.Keys;
import pl.devfinder.business.management.TokenExpirationTime;
import pl.devfinder.business.management.Utility;
import pl.devfinder.domain.ResetPasswordToken;
import pl.devfinder.domain.User;
import pl.devfinder.domain.exception.NotFoundException;

import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class ResetPasswordTokenService {
    private final UserService userService;
    private final ResetPasswordTokenDAO resetPasswordTokenDAO;


    public String validatePasswordResetToken(String token) {
        log.info("Trying validate reset password token: [{}]", token);
        Optional<ResetPasswordToken> resetPasswordToken = resetPasswordTokenDAO.findByToken(token);
        if (resetPasswordToken.isEmpty()){
            return Keys.TokenStatus.INVALID.getName();
        }
        Calendar calendar = Calendar.getInstance();
        if (resetPasswordToken.get().getExpirationTime().isBefore(OffsetDateTime.now())){
            return Keys.TokenStatus.EXPIRED.getName();
        }
        return Keys.TokenStatus.VALID.getName();
    }

    public Optional<User> findUserByResetPasswordToken(String token) {
        log.info("Trying find user by reset password token: [{}]", token);
        ResetPasswordToken resetPasswordToken = resetPasswordTokenDAO.findByToken(token)
                .orElseThrow(() -> new NotFoundException("Could not find reset password token by token: [%s]"
                        .formatted(token)));
        return Optional.ofNullable(resetPasswordToken.getUser());
    }

    public void resetPassword(User user, String newPassword) {
        log.info("Trying reset password for user: [{}]", user);
        User userWithNewPassword = user.withPassword(Utility.encodePassword(newPassword));
        userService.save(userWithNewPassword);
    }
    public void createPasswordResetTokenForUser(User user, String token) {
        log.info("Trying create reset password token for user: [{}]", user);
        ResetPasswordToken resetToken = ResetPasswordToken.builder()
                .token(token)
                .user(user)
                .expirationTime(TokenExpirationTime.getExpirationTime())
                .build();
        resetPasswordTokenDAO.save(resetToken);
    }
}
