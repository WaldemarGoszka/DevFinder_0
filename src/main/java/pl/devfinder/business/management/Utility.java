package pl.devfinder.business.management;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import pl.devfinder.business.UserService;
import pl.devfinder.domain.User;

import java.util.Optional;
import java.util.UUID;

@UtilityClass
public final class Utility {

    public static String generateUUID(){
        return UUID.randomUUID().toString();
    }

    public static String encodePassword(String password){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }
    public static String getApplicationUrl(HttpServletRequest request){
        String appUrl = request.getRequestURL().toString();
        return appUrl.replace(request.getServletPath(), "");

    }

    public static Optional<User> getUserToPage(Authentication authentication, UserService userService, Model model) {
        if(authentication != null){
            Optional<User> user = userService.findByEmail(authentication.getName());
            if(user.isPresent()){
                model.addAttribute("user", user.orElse(User.builder().userName("").build()));
            }
        }
        return Optional.empty();
    }
}
