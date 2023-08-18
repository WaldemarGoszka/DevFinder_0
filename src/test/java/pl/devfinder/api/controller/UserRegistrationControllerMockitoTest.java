package pl.devfinder.api.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import pl.devfinder.api.dto.UserDTO;
import pl.devfinder.business.management.Keys;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class UserRegistrationControllerMockitoTest {
@Mock
Environment environment;

    @InjectMocks
    private UserRegistrationController userRegistrationController;


    @Test
    public void testGetRegistrationPage() {
        // Given
        Model model = mock(Model.class);

        // When
        String viewName = userRegistrationController.getRegistrationPage(model);

        // Then
        assertEquals("register", viewName);
        verify(model).addAttribute(eq("user"), any(UserDTO.class));
        verify(model).addAttribute(eq("candidateEnum"), eq(Keys.Role.CANDIDATE.getName()));
        verify(model).addAttribute(eq("employerEnum"), eq(Keys.Role.EMPLOYER.getName()));
    }
    @Test
    public void testForgotPasswordForm() {
        // When
        String viewName = userRegistrationController.forgotPasswordForm();

        // Then
        assertEquals("forgot_password_form", viewName);
    }
    @Test
    public void testPasswordResetForm() {
        Model model = mock(Model.class);

        // When
        String viewName = userRegistrationController.passwordResetForm("",model);

        // Then
        assertEquals("password_reset_form", viewName);
    }

}