package src.uz.pdp.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.logging.Logger;
import java.util.Properties;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.MimeMultipart;

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
    public static void sendFile(Session session, String subject, String from, String to,String text, File file){
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(text);
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            DataSource source = new FileDataSource(file);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(source.getName());
            multipart.addBodyPart(messageBodyPart);


            message.setContent(multipart);
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
