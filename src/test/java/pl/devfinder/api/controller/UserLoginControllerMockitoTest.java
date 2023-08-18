package pl.devfinder.api.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserLoginControllerMockitoTest {

    @InjectMocks
    private IndexController indexController;

    @Test
    public void testGetLoginPage() {
        // Given

        // When
        String viewName = indexController.getLoginPage();

        // Then
        assertEquals("login", viewName);
    }


}