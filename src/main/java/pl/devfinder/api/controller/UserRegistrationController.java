package pl.devfinder.api.controller;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.devfinder.api.dto.UserDTO;
import pl.devfinder.api.dto.mapper.UserMapper;
import pl.devfinder.business.EmailVerificationTokenService;
import pl.devfinder.business.ResetPasswordTokenService;
import pl.devfinder.business.UserService;
import pl.devfinder.business.management.Keys;
import pl.devfinder.business.management.Utility;
import pl.devfinder.domain.EmailVerificationToken;
import pl.devfinder.domain.User;
import pl.devfinder.infrastructure.security.event.RegistrationCompleteEvent;
import pl.devfinder.infrastructure.security.event.RegistrationCompleteEventListener;

import java.io.UnsupportedEncodingException;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping(UserRegistrationController.BASE_PATH)
public class UserRegistrationController {

    public static final String BASE_PATH = "/register";
    public static final String REGISTER_PAGE = "/register_page";
    public static final String SAVE = "/save";
    public static final String VERIFY_EMAIL = "/verify_email";
    public static final String FORGOT_PASSWORD_FORM = "/forgot_password_form";
    public static final String FORGOT_PASSWORD_REQUEST = "/forgot-password";
    public static final String PASSWORD_RESET_FORM = "/password_reset_form";
    public static final String RESET_PASSWORD_REQUEST = "/reset_password";
    private final UserService userService;
    private final UserMapper userMapper;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final RegistrationCompleteEventListener registrationCompleteEventListener;
    private final EmailVerificationTokenService emailVerificationTokenService;
    private final ResetPasswordTokenService resetPasswordTokenService;
    private final Environment environment;


    @GetMapping(REGISTER_PAGE)
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new UserDTO());
        model.addAttribute("candidateEnum", Keys.Role.CANDIDATE.getName());
        model.addAttribute("employerEnum", Keys.Role.EMPLOYER.getName());
        Boolean enableEmailVerification = environment.getProperty("devfinder-conf.enable-email-verification", Boolean.class);

        model.addAttribute("enableEmailVerification", enableEmailVerification);

        return "register";
    }

    @PostMapping(SAVE)
    public String userRegister(@Valid @ModelAttribute("user") UserDTO userDTO,
                               Model model, HttpServletRequest request) {
        Boolean enableEmailVerification = environment.getProperty("devfinder-conf.enable-email-verification", Boolean.class);
        model.addAttribute("enableEmailVerification", enableEmailVerification);
        try {
            Optional<User> existing = userService.findByEmail(userDTO.getEmail());
            if (existing.isPresent()) {
                log.warn("User already registered with this email, userDTO: [{}]", userDTO);
                return "redirect:/register/register_page?email_already_registered";
            }
            if (checkIfUserSelectedRole(userDTO)) {
                log.error("The user has not selected a role");
                return "redirect:/register/register_page?select_role";
            }

            UserDTO userDtoOnCondition = userDTO.withIsEnabled(Boolean.FALSE.equals(enableEmailVerification));
            log.info("Process register user, userDTO: [{}]", userDtoOnCondition);
            User user = userService.save(userMapper.mapFromDTO(userDtoOnCondition));
            if (Boolean.TRUE.equals(enableEmailVerification)) {
                log.info("Send verification email to [{}]", userDTO.getEmail());
                applicationEventPublisher.publishEvent(new RegistrationCompleteEvent(user, Utility.getApplicationUrl(request)));
            }
            log.info("User is registered userDTO: [{}]", userDtoOnCondition);
            return "redirect:/register/register_page?success";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/register/register_page?server_error";
        }
    }

    private static boolean checkIfUserSelectedRole(UserDTO userDTO) {
        return Objects.isNull(userDTO.getRole());
    }

    @GetMapping(VERIFY_EMAIL)
    public String verifyEmail(@RequestParam("token") String token) {
        Optional<EmailVerificationToken> emailVerificationToken = emailVerificationTokenService.findByToken(token);
        if (emailVerificationToken.isPresent() && emailVerificationToken.get().getUser().getIsEnabled()) {
            return "redirect:/login?verified";
        }
        String verificationResult = emailVerificationTokenService.validateToken(token);
        if (Keys.TokenStatus.EXPIRED.getName().equals(verificationResult)) {
            return "redirect:/error?expired";
        } else if (Keys.TokenStatus.VALID.getName().equals(verificationResult)) {
            return "redirect:/login?valid";
        } else if (Keys.TokenStatus.INVALID.getName().equals(verificationResult)) {
            return "redirect:/error?invalid";
        }
        return "error";
    }

    @GetMapping(FORGOT_PASSWORD_FORM)
    public String forgotPasswordForm() {
        return "forgot_password_form";
    }

    @PostMapping(FORGOT_PASSWORD_REQUEST)
    public String resetPasswordRequest(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        log.info("Forgot password request from [{}]", email);
        Optional<User> userOptional = userService.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String resetPasswordToken = Utility.generateUUID();
            resetPasswordTokenService.createPasswordResetTokenForUser(user, resetPasswordToken);
            log.info("Reset password token created for [{}]", email);
            sendPasswordResetVerificationEmail(request, model, email, user, resetPasswordToken);
            return "redirect:/register/forgot_password_form?success";
        }
        return "redirect:/register/forgot_password_form?not_fond";
    }

    @GetMapping(PASSWORD_RESET_FORM)
    public String passwordResetForm(@RequestParam("token") String token, Model model) {
        model.addAttribute("token", token);
        return "password_reset_form";
    }

    @PostMapping(RESET_PASSWORD_REQUEST)
    public String resetPassword(HttpServletRequest request) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");
        String tokenVerificationResult = resetPasswordTokenService.validatePasswordResetToken(token);
        if (!tokenVerificationResult.equals(Keys.TokenStatus.VALID.getName())) {
            return "redirect:/error?invalid_token";
        }
        Optional<User> user = resetPasswordTokenService.findUserByResetPasswordToken(token);
        if (user.isPresent()) {
            log.info("Process reset password for [{}]", user.get().getEmail());
            resetPasswordTokenService.resetPassword(user.get(), password);
            return "redirect:/login?reset_success";
        }
        return "redirect:/error?not_found";
    }

    private void sendPasswordResetVerificationEmail(HttpServletRequest request, Model model, String email, User user, String resetPasswordToken) {
        String url = Utility.getApplicationUrl(request) + "/register/password_reset_form?token=" + resetPasswordToken;
        try {
            log.info("Process send reset password verification email to [{}]", email);
            registrationCompleteEventListener.sendPasswordResetVerificationEmail(url, user);
        } catch (MessagingException | UnsupportedEncodingException e) {
            model.addAttribute("error", e.getMessage());
        }
    }
}
