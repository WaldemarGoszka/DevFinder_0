package pl.devfinder.infrastructure.security.event;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import pl.devfinder.business.EmailVerificationTokenService;
import pl.devfinder.business.management.Utility;
import pl.devfinder.domain.User;

import java.io.UnsupportedEncodingException;

@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    public static final String APPLICATION_MAIL = "devfinder.service@gmail.com";
    private final EmailVerificationTokenService emailVerificationTokenService;
    private final JavaMailSender javaMailSender;
    private User user;

    private static void emailMessage(String subject, String senderName,
                                     String mailContent, JavaMailSender mailSender, User user)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom(APPLICATION_MAIL, senderName);
        messageHelper.setTo(user.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent registrationCompleteEvent) {
        user = registrationCompleteEvent.getUser();
        String token = Utility.generateUUID();
        emailVerificationTokenService.saveVerificationTokenForUser(user, token);
        String url = registrationCompleteEvent.getConfirmationUrl() + "/register/verify_email?token=" + token;
        try {
            sendVerificationEmail(url);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "Email Verification";
        String senderName = "Users Verification Service";
        String mailContent = "<p> Hi, " + user.getUserName() + ", </p>" +
                "<p>Thank you for registering with us. " +
                "Please, follow the link below to complete your registration.</p>" +
                "<a href=\"" + url + "\">Verify your email to activate your account</a>" +
                "<p> Thank you <br> DevFinder";
        emailMessage(subject, senderName, mailContent, javaMailSender, user);
    }

    public void sendPasswordResetVerificationEmail(String url, User user) throws MessagingException, UnsupportedEncodingException {
        String subject = "Password Reset Request Verification";
        String senderName = "Users Verification Service";
        String mailContent = "<p> Hi, " + user.getUserName() + ", </p>" +
                "<p><b>You recently requested to reset your password,</b>" +
                "Please, follow the link below to complete the action.</p>" +
                "<a href=\"" + url + "\">Reset password</a>" +
                "<p> DevFinder";
        emailMessage(subject, senderName, mailContent, javaMailSender, user);
    }


}
