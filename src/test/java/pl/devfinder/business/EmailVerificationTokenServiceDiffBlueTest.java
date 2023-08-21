package pl.devfinder.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.business.dao.EmailVerificationTokenDAO;
import pl.devfinder.domain.EmailVerificationToken;
import pl.devfinder.domain.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {EmailVerificationTokenService.class})
@ExtendWith(SpringExtension.class)
class EmailVerificationTokenServiceDiffBlueTest {
    @MockBean
    private EmailVerificationTokenDAO emailVerificationTokenDAO;

    @Autowired
    private EmailVerificationTokenService emailVerificationTokenService;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link EmailVerificationTokenService#validateToken(String)}
     */
    @Test
    void testValidateToken() {
        when(emailVerificationTokenDAO.findByToken(Mockito.any())).thenReturn(Optional.empty());
        assertEquals("INVALID", emailVerificationTokenService.validateToken("ABC123"));
        verify(emailVerificationTokenDAO).findByToken(Mockito.any());
    }

    /**
     * Method under test: {@link EmailVerificationTokenService#saveVerificationTokenForUser(User, String)}
     */
    @Test
    void testSaveVerificationTokenForUser() {
        doNothing().when(emailVerificationTokenDAO).save(Mockito.any());
        emailVerificationTokenService.saveVerificationTokenForUser(null, "ABC123");
        verify(emailVerificationTokenDAO).save(Mockito.any());
    }

    /**
     * Method under test: {@link EmailVerificationTokenService#findByToken(String)}
     */
    @Test
    void testFindByToken() {
        Optional<EmailVerificationToken> emptyResult = Optional.empty();
        when(emailVerificationTokenDAO.findByToken(Mockito.any())).thenReturn(emptyResult);
        Optional<EmailVerificationToken> actualFindByTokenResult = emailVerificationTokenService.findByToken("ABC123");
        assertSame(emptyResult, actualFindByTokenResult);
        assertFalse(actualFindByTokenResult.isPresent());
        verify(emailVerificationTokenDAO).findByToken(Mockito.any());
    }

    /**
     * Method under test: {@link EmailVerificationTokenService#deleteUserEmailVerificationToken(Long)}
     */
    @Test
    void testDeleteUserEmailVerificationToken() {
        doNothing().when(emailVerificationTokenDAO).deleteByUserId(Mockito.<Long>any());
        emailVerificationTokenService.deleteUserEmailVerificationToken(1L);
        verify(emailVerificationTokenDAO).deleteByUserId(Mockito.<Long>any());
    }
}

