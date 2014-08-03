package pl.com.tegess.RetrospectionSystem.model;


import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * Creates easy way to send mails using MailSender.
 *@see org.springframework.mail.MailSender
 *
 * @author Szymek
 *
 * @version 1.0
 */
public class Mail {

    /**
     * The MailSender.
     */
    private MailSender mailSender;

    /**
     * Sends mail using MailSender. Required params are recipient,
     * message topic and message content.
     * @see org.springframework.mail.MailSender
     * @see org.springframework.mail.SimpleMailMessage
     *
     * @param to the recipient.
     * @param subject the topic.
     * @param message the content.
     */
    public void sendMail(String to, String subject, String message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }

    /**
     * Sets the MailSender.
     * @see org.springframework.mail.MailSender
     *
     * @param mailSender the MailSender.
     */
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }
}
