package com.dailycodework.sbend2endapplication.registration;
import com.dailycodework.sbend2endapplication.event.RegistrationCompleteEvent;
import com.dailycodework.sbend2endapplication.event.listener.RegistrationCompleteEventListener;
import com.dailycodework.sbend2endapplication.registration.password.IPasswordResetTokenService;
import com.dailycodework.sbend2endapplication.registration.token.VerificationToken;
import com.dailycodework.sbend2endapplication.registration.token.VerificationTokenService;
import com.dailycodework.sbend2endapplication.user.IUserService;
import com.dailycodework.sbend2endapplication.user.User;
import com.dailycodework.sbend2endapplication.utility.UrlUtil;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.devfinder.business.EmailVerificationTokenService;
import pl.devfinder.business.ResetPasswordTokenService;
import pl.devfinder.business.UserService;
import pl.devfinder.domain.User;
import pl.devfinder.infrastructure.security.event.RegistrationCompleteEventListener;


import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.UUID;


@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {
    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final EmailVerificationTokenService emailVerificationTokenService;
    private final ResetPasswordTokenService resetPasswordTokenService;
    private final RegistrationCompleteEventListener registrationCompleteEventListener;




    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam("token") String token) {
        Optional<VerificationToken> theToken = emailVerificationTokenService.findByToken(token);
        if (theToken.isPresent() && theToken.get().getUser().isEnabled()) {
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
    public String forgotPasswordForm(){
        return "forgot-password-form";
    }

    @PostMapping("/forgot-password")
    public String resetPasswordRequest(HttpServletRequest request, Model model){
        String email = request.getParameter("email");
        Optional<User> user= userService.findByEmail(email);
        if (user.isEmpty()){
            return  "redirect:/registration/forgot-password-request?not_fond";
        }
        String passwordResetToken = UUID.randomUUID().toString();
        resetPasswordTokenService.createPasswordResetTokenForUser(user.get(), passwordResetToken);
        //send password reset verification email to the user
        String url = UrlUtil.getApplicationUrl(request)+"/registration/password-reset-form?token="+passwordResetToken;
        try {
            registrationCompleteEventListener.sendPasswordResetVerificationEmail(url);
        } catch (MessagingException | UnsupportedEncodingException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/registration/forgot-password-request?success";
    }
    @GetMapping("/password-reset-form")
    public String passwordResetForm(@RequestParam("token") String token, Model model){
        model.addAttribute("token", token);
        return "password-reset-form";
    }
    @PostMapping("/reset-password")
    public String resetPassword(HttpServletRequest request){
        String theToken = request.getParameter("token");
        String password = request.getParameter("password");
        String tokenVerificationResult = resetPasswordTokenService.validatePasswordResetToken(theToken);
        if (!tokenVerificationResult.equalsIgnoreCase("valid")){
            return "redirect:/error?invalid_token";
        }
        Optional<User> user = resetPasswordTokenService.findUserByResetPasswordToken(theToken);
        if (user.isPresent()){
            resetPasswordTokenService.resetPassword(user.get(), password);
            return "redirect:/login?reset_success";
        }
        return "redirect:/error?not_found";
    }
}
