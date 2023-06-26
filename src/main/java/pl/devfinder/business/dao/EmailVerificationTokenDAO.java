package pl.devfinder.business.dao;


import pl.devfinder.domain.EmailVerificationToken;

public interface EmailVerificationTokenDAO {
    public void save(EmailVerificationToken emailVerificationToken);

}
