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
import java.util.Arrays;
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
        return "register";
    }

    @PostMapping("/save")
    public String postRegistrationPage(@Valid @ModelAttribute("user") UserDTO userDTO,
                                       BindingResult result,
                                       Model model, HttpServletRequest request) {
        try {
            Optional<User> existing = userService.findByEmail(userDTO.getEmail());
            if (existing.isPresent()) {
                result.rejectValue("email"
                        , null
                        , "There is already an account registered with that email");

                //TODO tutaj może zamiat komunikatu to przekierować na stosowny endpoint i pokaać fragment html
                // np taki return "redirect:/register/register?email_registered" i komunikat Email has been register!
                return "redirect:/register/register?email_already_registered";
            }
            if (result.hasErrors()) {
                log.error("result.hasErrors() userDTO: [{}]", userDTO);
//                model.addAttribute("user", userDTO);
//                return "register";
            }

            Boolean disableEmailVerification = environment.getProperty("devfinder-conf.disable-email-verification", Boolean.class);
//            log.info("Trying register userDTO: [{}]", userDTO.withIsEnabled(disableEmailVerification));
            User user = userService.save(userMapper.mapFromDTO(userDTO.withIsEnabled(disableEmailVerification)));
            //TODO dodać parametr w application.yaml wyłączający email validation albo dodać że jak jest uruchomuiony lokalnie to validate jest wyłączone
            // send verification email:
            if(disableEmailVerification) {
                applicationEventPublisher.publishEvent(new RegistrationCompleteEvent(user, Utility.getApplicationUrl(request)));
            }
            return "redirect:/register/register?success";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Server is error, try again later!");
        }
        return "register";
    }

    @GetMapping("/verify_email")
    public String verifyEmail(@RequestParam("token") String token) {
        Optional<EmailVerificationToken> theToken = emailVerificationTokenService.findByToken(token);
        if (theToken.isPresent() && theToken.get().getUser().getIsEnabled()) {
            return "redirect:/login?verified";
        }
        String verificationResult = emailVerificationTokenService.validateToken(token);
        switch (verificationResult.toLowerCase()) {
            //TODO zamienić na keys INVALID
            case "expired":
                return "redirect:/error?expired";
            case "valid":
                return "redirect:/login?valid";
            default:
                return "redirect:/error?invalid";
        }
    }

    @GetMapping("/forgot-password-request")
    public String forgotPasswordForm() {
        return "forgot-password-form";
    }

    @PostMapping("/forgot-password")
    public String resetPasswordRequest(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        Optional<User> user = userService.findByEmail(email);
        if (user.isEmpty()) {
            //TODO to przetestować bo zwracało optionala a teraz sprawdzam czy jest null
            return "redirect:/register/forgot-password-request?not_fond";
        }
        String resetPasswordToken = Utility.generateUUID();
        resetPasswordTokenService.createPasswordResetTokenForUser(user.get(), resetPasswordToken);
        //send password reset verification email to the user
        String url = Utility.getApplicationUrl(request) + "/register/password-reset-form?token=" + resetPasswordToken;
        try {
            registrationCompleteEventListener.sendPasswordResetVerificationEmail(url);
        } catch (MessagingException | UnsupportedEncodingException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/register/forgot-password-request?success";
    }

    @GetMapping("/password-reset-form")
    public String passwordResetForm(@RequestParam("token") String token, Model model) {
        model.addAttribute("token", token);
        return "password-reset-form";
    }

    @PostMapping("/reset-password")
    public String resetPassword(HttpServletRequest request) {
        String theToken = request.getParameter("token");
        String password = request.getParameter("password");
        String tokenVerificationResult = resetPasswordTokenService.validatePasswordResetToken(theToken);
        if (!tokenVerificationResult.equalsIgnoreCase("valid")) {
            return "redirect:/error?invalid_token";
        }
        Optional<User> user = resetPasswordTokenService.findUserByResetPasswordToken(theToken);
        if (user.isPresent()) {
            resetPasswordTokenService.resetPassword(user.get(), password);
            return "redirect:/login?reset_success";
        }
        return "redirect:/error?not_found";
    }
}
