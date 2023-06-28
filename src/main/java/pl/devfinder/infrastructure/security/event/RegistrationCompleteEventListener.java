package pl.devfinder.infrastructure.security.event;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
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
    private final EmailVerificationTokenService emailVerificationTokenService;
    private final JavaMailSender javaMailSender;
    private User user;



    @Override
    public void onApplicationEvent(RegistrationCompleteEvent registrationCompleteEvent) {
        //1. get the user
        user = registrationCompleteEvent.getUser();
        //2. generate a token for the user
        String token = Utility.generateUUID();
        //3. save the token for the user
        emailVerificationTokenService.saveVerificationTokenForUser(user, token);
        //4. Build the verification url
        String url = registrationCompleteEvent.getConfirmationUrl()+"/register/verify_email?token="+token;
        //5. send the email to the user
        try {
            sendVerificationEmail(url);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "Email Verification";
        String senderName = "Users Verification Service";
        String mailContent = "<p> Hi, "+ user.getUserName()+ ", </p>"+
                "<p>Thank you for registering with us. "+"" +
                "Please, follow the link below to complete your registration.</p>"+
                "<a href=\"" +url+ "\">Verify your email to activate your account</a>"+
                "<p> Thank you <br> DevFinder";
        emailMessage(subject, senderName, mailContent, javaMailSender, user);
    }


    public void sendPasswordResetVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "Password Reset Request Verification";
        String senderName = "Users Verification Service";
        String mailContent = "<p> Hi, "+ user.getUserName()+ ", </p>"+
                "<p><b>You recently requested to reset your password,</b>"+"" +
                "Please, follow the link below to complete the action.</p>"+
                "<a href=\"" +url+ "\">Reset password</a>"+
                "<p> DevFinder";
        emailMessage(subject, senderName, mailContent, javaMailSender, user);
    }

    private static void emailMessage(String subject, String senderName,
                                     String mailContent, JavaMailSender mailSender, User user)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);
        //TODO zamiast maila wykożystać klasę Environment i pobrać z konfiguracji maila
        //TODO zahasłować hasło do maila w application.yaml
//        environment.getProperty("devfinder-conf.disable-email-verification", Boolean.class)
        messageHelper.setFrom("devfinder.service@gmail.com", senderName);
        messageHelper.setTo(user.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }



}
