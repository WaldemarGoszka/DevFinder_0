package pl.devfinder.api.controller.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.devfinder.api.controller.exception.UserAlreadyExistsException;
import pl.devfinder.api.dto.UserDTO;
import pl.devfinder.api.dto.mapper.UserMapper;
import pl.devfinder.business.EmailVerificationTokenService;
import pl.devfinder.business.ResetPasswordTokenService;
import pl.devfinder.business.UserService;
import pl.devfinder.domain.User;
import pl.devfinder.infrastructure.security.event.RegistrationCompleteEventListener;

import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/register")
public class UserRegistrationRestController {

    public static final String REGISTER_PAGE = "/register_page";
    public static final String SAVE = "/save";
    public static final String VERIFY_EMAIL = "/verify_email";
    public static final String FORGOT_PASSWORD_FORM = "/forgot_password_form";
    public static final String FORGOT_PASSWORD_REQUEST = "/forgot-password";
    public static final String PASSWORD_RESET_FORM = "/password_reset_form";
    public static final String RESET_PASSWORD_REQUEST = "/reset_password";
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping(REGISTER_PAGE)
    public String registerUser(@Valid @RequestBody UserDTO userDTO, final HttpServletRequest request){
        Optional<User> userOptional = userService.findByEmail(userDTO.getEmail());
        if (userOptional.isPresent()){
            throw new UserAlreadyExistsException(
                    "User with email "+userDTO.getEmail() + " already exists");
        }
        userService.save(userMapper.mapFromDTO(userDTO));
        return "Register Success! Now you can Log in";
    }
}
