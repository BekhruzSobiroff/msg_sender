package src.uz.pdp.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.logging.Logger;

public class MailUtils {
    private static final Logger log = Logger.getLogger(MailUtils.class.getName());
    public static void sendMessage(Session session,
                                   String subject,
                                   String from,
                                   String to,
                                   String text)
    {
        MimeMessage message=new MimeMessage(session);
        try {
            message.addHeader("Content-Type","text/html");
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,to);
            message.setSubject(subject);
            message.setText(text);
        } catch (MessagingException e) {
            log.severe("Creating MimeMessage error: "+e.getMessage());
        }
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            log.severe("Sending message error: "+e.getMessage());
        }
    }
}
