package pl.devfinder.infrastructure.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
//TODO przetestować
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_CANDIDATE"))) {
            // Jeśli użytkownik ma rolę "CANDIDATE", przekieruj na "/candidate/index"
            getRedirectStrategy().sendRedirect(request, response, "/candidate/index");
        } else if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_EMPLOYER"))) {
            // Jeśli użytkownik ma rolę "EMPLOYER", przekieruj na "/employer/index"
            getRedirectStrategy().sendRedirect(request, response, "/employer/index");
        } else {
            // Domyślny przypadek - przekieruj na "/index"
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
