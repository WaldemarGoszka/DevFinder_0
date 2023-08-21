package pl.devfinder.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.business.dao.ResetPasswordTokenDAO;
import pl.devfinder.domain.User;
import pl.devfinder.domain.exception.NotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ResetPasswordTokenService.class})
@ExtendWith(SpringExtension.class)
class ResetPasswordTokenServiceDiffBlueTest {
    @MockBean
    private ResetPasswordTokenDAO resetPasswordTokenDAO;

    @Autowired
    private ResetPasswordTokenService resetPasswordTokenService;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link ResetPasswordTokenService#validatePasswordResetToken(String)}
     */
    @Test
    void testValidatePasswordResetToken() {
        when(resetPasswordTokenDAO.findByToken(Mockito.any())).thenReturn(Optional.empty());
        assertEquals("INVALID", resetPasswordTokenService.validatePasswordResetToken("ABC123"));
        verify(resetPasswordTokenDAO).findByToken(Mockito.any());
    }

    /**
     * Method under test: {@link ResetPasswordTokenService#validatePasswordResetToken(String)}
     */
    @Test
    void testValidatePasswordResetToken2() {
        when(resetPasswordTokenDAO.findByToken(Mockito.any()))
                .thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> resetPasswordTokenService.validatePasswordResetToken("ABC123"));
        verify(resetPasswordTokenDAO).findByToken(Mockito.any());
    }

    /**
     * Method under test: {@link ResetPasswordTokenService#findUserByResetPasswordToken(String)}
     */
    @Test
    void testFindUserByResetPasswordToken() {
        when(resetPasswordTokenDAO.findByToken(Mockito.any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> resetPasswordTokenService.findUserByResetPasswordToken("ABC123"));
        verify(resetPasswordTokenDAO).findByToken(Mockito.any());
    }

    /**
     * Method under test: {@link ResetPasswordTokenService#findUserByResetPasswordToken(String)}
     */
    @Test
    void testFindUserByResetPasswordToken2() {
        when(resetPasswordTokenDAO.findByToken(Mockito.any()))
                .thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> resetPasswordTokenService.findUserByResetPasswordToken("ABC123"));
        verify(resetPasswordTokenDAO).findByToken(Mockito.any());
    }

    /**
     * Method under test: {@link ResetPasswordTokenService#resetPassword(User, String)}
     */


    /**
     * Method under test: {@link ResetPasswordTokenService#createPasswordResetTokenForUser(User, String)}
     */
    @Test
    void testCreatePasswordResetTokenForUser() {
        doNothing().when(resetPasswordTokenDAO).save(Mockito.any());
        resetPasswordTokenService.createPasswordResetTokenForUser(null, "ABC123");
        verify(resetPasswordTokenDAO).save(Mockito.any());
    }

    /**
     * Method under test: {@link ResetPasswordTokenService#createPasswordResetTokenForUser(User, String)}
     */
    @Test
    void testCreatePasswordResetTokenForUser2() {
        doThrow(new NotFoundException("An error occurred")).when(resetPasswordTokenDAO)
                .save(Mockito.any());
        assertThrows(NotFoundException.class,
                () -> resetPasswordTokenService.createPasswordResetTokenForUser(null, "ABC123"));
        verify(resetPasswordTokenDAO).save(Mockito.any());
    }
}

