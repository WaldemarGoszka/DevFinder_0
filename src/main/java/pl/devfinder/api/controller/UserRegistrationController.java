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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
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
@RequestMapping("/register")
public class UserRegistrationController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final RegistrationCompleteEventListener registrationCompleteEventListener;
    private final EmailVerificationTokenService emailVerificationTokenService;
    private final ResetPasswordTokenService resetPasswordTokenService;
    private final Environment environment;


    @GetMapping("/register_page")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new UserDTO());
        model.addAttribute("candidateEnum", Keys.Role.CANDIDATE.getName());
        model.addAttribute("employerEnum", Keys.Role.EMPLOYER.getName());
        Boolean enableEmailVerification = environment.getProperty("devfinder-conf.enable-email-verification", Boolean.class);

        model.addAttribute("enableEmailVerification", enableEmailVerification);

        return "register";
    }

    @PostMapping("/save")
    public String postRegistrationPage(@Valid @ModelAttribute("user") UserDTO userDTO,
                                       BindingResult bindingResult,
                                       Model model, HttpServletRequest request) {
        Boolean enableEmailVerification = environment.getProperty("devfinder-conf.enable-email-verification", Boolean.class);
        model.addAttribute("enableEmailVerification", enableEmailVerification);
        try {
            Optional<User> existing = userService.findByEmail(userDTO.getEmail());
            if (existing.isPresent()) {
//                result.rejectValue("email"
//                        , null
//                        , "There is already an account registered with that email");
                log.warn("User already registered with this email, userDTO: [{}]", userDTO);
                return "redirect:/register/register_page?email_already_registered";
            }
            if (Objects.isNull(userDTO.getRole())) {
                log.error("The user has not selected a role");
                return "redirect:/register/register_page?select_role";
            }
//            if (bindingResult.hasErrors()) {
//                log.error("Error in register user, userDTO: [{}]", userDTO);
//                for (ObjectError error : bindingResult.getAllErrors()) {
//                    if (error instanceof FieldError fieldError) {
//                        String fieldName = fieldError.getField();
//                        String errorMessage = fieldError.getDefaultMessage();
//                        log.error("Error register form, field name: [{}], message: [{}]", fieldName, errorMessage);
//                    } else {
//                        String objectName = error.getObjectName();
//                        String errorMessage = error.getDefaultMessage();
//                        log.error("Error register form, object name: [{}], message: [{}]", objectName, errorMessage);
//                    }
//                }
//                System.out.println(bindingResult.toString());
//                model.addAttribute("user", userDTO);
//                return "register";
//            }

            UserDTO userDtoOnCondition = userDTO.withIsEnabled(!enableEmailVerification);
            log.info("Trying register user, userDTO: [{}]", userDtoOnCondition);
            User user = userService.save(userMapper.mapFromDTO(userDtoOnCondition));
            if (Boolean.TRUE.equals(enableEmailVerification)) {
                log.info("Send verification email to [{}]", userDTO.getEmail());
                applicationEventPublisher.publishEvent(new RegistrationCompleteEvent(user, Utility.getApplicationUrl(request)));
            }
            log.info("User is registered userDTO: [{}]", userDtoOnCondition);
            return "redirect:/register/register_page?success";
        } catch (Exception e) {
            e.printStackTrace();
//            model.addAttribute("error", "Server is error, try again later!");
            return "redirect:/register/register_page?server_error";
        }
//        return "register";
    }

    @GetMapping("/verify_email")
    public String verifyEmail(@RequestParam("token") String token) {
        Optional<EmailVerificationToken> emailVerificationToken = emailVerificationTokenService.findByToken(token);
        if (emailVerificationToken.isPresent() && emailVerificationToken.get().getUser().getIsEnabled()) {
            return "redirect:/login?verified";
        }
        String verificationResult = emailVerificationTokenService.validateToken(token);
        return switch (Keys.TokenStatus.INVALID) {
            case EXPIRED -> "redirect:/error?expired";
            case VALID -> "redirect:/login?valid";
            case INVALID -> "redirect:/error?invalid";
        };
    }

    @GetMapping("/forgot_password_form")
    public String forgotPasswordForm() {
        return "forgot_password_form";
    }

    @PostMapping("/forgot-password")
    public String resetPasswordRequest(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        log.info("Forgot password request from [{}]", email);
        Optional<User> user = userService.findByEmail(email);
        if (user.isEmpty()) {
            return "redirect:/register/forgot_password_form?not_fond";
        }
        String resetPasswordToken = Utility.generateUUID();
        resetPasswordTokenService.createPasswordResetTokenForUser(user.get(), resetPasswordToken);
        log.info("Reset password token created for [{}]", email);
        //send password reset verification email to the user
        String url = Utility.getApplicationUrl(request) + "/register/password_reset_form?token=" + resetPasswordToken;
        try {
            log.info("Trying send reset password verification email to [{}]", email);
            registrationCompleteEventListener.sendPasswordResetVerificationEmail(url, user.get());
        } catch (MessagingException | UnsupportedEncodingException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/register/forgot_password_form?success";
    }

    @GetMapping("/password_reset_form")
    public String passwordResetForm(@RequestParam("token") String token, Model model) {
        model.addAttribute("token", token);
        return "password_reset_form";
    }

    @PostMapping("/reset_password")
    public String resetPassword(HttpServletRequest request) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");
        String tokenVerificationResult = resetPasswordTokenService.validatePasswordResetToken(token);
        if (!tokenVerificationResult.equals(Keys.TokenStatus.VALID.getName())) {
            return "redirect:/error?invalid_token";
        }
        Optional<User> user = resetPasswordTokenService.findUserByResetPasswordToken(token);
        if (user.isPresent()) {
            log.info("Trying reset password for [{}]", user.get().getEmail());
            resetPasswordTokenService.resetPassword(user.get(), password);
            return "redirect:/login?reset_success";
        }
        return "redirect:/error?not_found";
    }
}
