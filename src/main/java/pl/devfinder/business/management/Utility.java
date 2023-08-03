package pl.devfinder.business.management;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import pl.devfinder.business.CandidateService;
import pl.devfinder.business.EmployerService;
import pl.devfinder.business.UserService;
import pl.devfinder.domain.Candidate;
import pl.devfinder.domain.User;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@UtilityClass
public final class Utility {
    public static final String USER_DATA_DIR = "\"static/user_data\"";

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

    public static Optional<User> putUserDataToModel(Authentication authentication, UserService userService, Model model) {
        if(authentication != null){
            Optional<User> user = userService.findByEmail(authentication.getName());
            if(user.isEmpty()){
                return Optional.empty();
            }
            model.addAttribute("user", user.get());
            return user;
        }
        return Optional.empty();
    }

    public static void getUserPhotoPath(User user, CandidateService candidateService, EmployerService employerService, Model model) {
//        if(user.getRole().getRole().equals(Keys.Role.CANDIDATE.getName())){
//            Optional <Candidate> candidate = candidateService.findByCandidateUuid(user.getUserUuid());
//            if(candidate.isPresent() && Objects.nonNull(candidate.get().getPhotoFilename())){
//                model.addAttribute("photo",candidate.get().getPhotoFilename());
//            }
//
//        }
//        if(user.getRole().getRole().equals(Keys.Role.EMPLOYER.getName())){
////                model.addAttribute picture from server
//        }
    }
}
