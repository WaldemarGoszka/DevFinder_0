package pl.devfinder.business.dao;


import pl.devfinder.domain.EmailVerificationToken;
import pl.devfinder.domain.ResetPasswordToken;

public interface ResetPasswordTokenRepositoryDAO {

    void save(ResetPasswordToken resetPasswordToken);
}
