package pl.com.tegess.RetrospectionSystem.model;


import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by Szymek.
 */
public class Mail {

    private MailSender mailSender;

    public void sendMail(String from, String to, String subject, String message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }
}
