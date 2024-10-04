package mosbach.dhbw.de.smarthome.service.impl;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

import mosbach.dhbw.de.smarthome.service.api.VerificationService;

@Service
public class VerificationServiceImpl implements VerificationService {
    
    public void sendVerificationEmail(String to, String verificationLink) {
        String smtpHost = System.getenv("SMTP_HOST");
        int smtpPort = Integer.parseInt(System.getenv("SMTP_PORT"));
        String smtpUsername = System.getenv("SMTP_USERNAME");
        String smtpPassword = System.getenv("SMTP_PASSWORD");
        String appName = "Smart Home Dashboard";

        String body = "<html>" +
                    "<p>Hi there,</p>" +
                    "<p>Thank you for registering with <strong>"+appName+"</strong>. To complete your registration, please verify your email by clicking the link below:</p>" +
                    "<p><a href=\"" + verificationLink + "\">Verify Email</a></p>" +
                    "<p>If you didn't request this email, you can safely ignore it.</p>" +
                    "<br/>" +
                    "<p>Best regards,</p>" +
                    "<p>Your App Support Team</p>" +
                    "</html>";

        String msgBody = "Hi there,\n\n" +
                    "Thank you for registering with Your App. To complete your registration, please verify your email by clicking the link below:\n" +
                    verificationLink + "\n\n" +
                    "If you didn't request this email, you can safely ignore it.\n\n" +
                    "Best regards,\n" +
                    "Your App Support Team\n";

        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName(smtpHost);
            email.setSmtpPort(smtpPort);
            email.setAuthentication(smtpUsername, smtpPassword);
            email.setStartTLSEnabled(true);
            email.setFrom(smtpUsername, appName);
            email.setSubject("Verify your email address for Your Smart Home Dashboard");
            email.setHtmlMsg(body);
            email.setTextMsg(msgBody);
            email.addTo(to);
            email.send();
            System.out.println("E-Mail erfolgreich gesendet!");
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}



