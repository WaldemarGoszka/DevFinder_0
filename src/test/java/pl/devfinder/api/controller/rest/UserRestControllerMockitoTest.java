package pl.devfinder.api.controller.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.devfinder.business.UserService;
import pl.devfinder.domain.User;
import pl.devfinder.domain.exception.NotFoundException;
import pl.devfinder.util.UserFixtures;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRestControllerMockitoTest {
    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private UserRestController userRestController;

    @Test
    public void deleteUserTest() {
        // given
        User user = UserFixtures.someUserCandidate1();
        String userEmail = user.getEmail();


        // when
        when(authentication.getName()).thenReturn(userEmail);
        when(userService.findByEmail(userEmail)).thenReturn(Optional.of(user));

        ResponseEntity<?> response = userRestController.deleteUser(authentication);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(userService).findByEmail(userEmail);
        verify(userService).deleteUser(user);
    }

    @Test
    public void deleteUserTest_UserNotFound() {
        // given
        User user = UserFixtures.someUserCandidate1();
        String userEmail = user.getEmail();

        // when
        when(authentication.getName()).thenReturn(userEmail);
        when(userService.findByEmail(userEmail)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            userRestController.deleteUser(authentication);
        });

        verify(userService).findByEmail(userEmail);
    }

    @Test
    public void changePasswordTest() {
        // given
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";
        User user = UserFixtures.someUserCandidate1().withPassword(oldPassword);
        String userEmail = user.getEmail();

        // when
        when(authentication.getName()).thenReturn(userEmail);
        when(userService.findByEmail(userEmail)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(oldPassword, user.getPassword())).thenReturn(true);
        when(passwordEncoder.matches(newPassword, oldPassword)).thenReturn(false);
        when(passwordEncoder.matches(newPassword, user.getPassword())).thenReturn(false);


        ResponseEntity<?> response = userRestController.getChangePassword(oldPassword, newPassword, newPassword, authentication);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(userService).findByEmail(userEmail);
        verify(passwordEncoder).matches(oldPassword, user.getPassword());

    }

    @Test
    public void changePasswordTest_UserNotFound() {
        // given
        User user = UserFixtures.someUserCandidate1();
        String userEmail = user.getEmail();
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";

        // when
        when(authentication.getName()).thenReturn(userEmail);
        when(userService.findByEmail(userEmail)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            userRestController.getChangePassword(oldPassword, newPassword, newPassword, authentication);
        });

        verify(userService).findByEmail(userEmail);
    }
    @Test
    public void changePasswordTest_OldPasswordDoesNotMatch() {
        // given
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";
        User user = UserFixtures.someUserCandidate1().withPassword(passwordEncoder.encode("differentOldPassword"));
        String userEmail = user.getEmail();

        // when
        when(authentication.getName()).thenReturn(userEmail);
        when(userService.findByEmail(userEmail)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(oldPassword, user.getPassword())).thenReturn(false);

        ResponseEntity<?> response = userRestController.getChangePassword(oldPassword, newPassword, newPassword, authentication);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        verify(userService).findByEmail(userEmail);
    }

}