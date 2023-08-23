package src.uz.pdp.config;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class MailConfig {

    private static final String NO_AUTH_HOST_SERVER = "pdp.uz.home";
    private static final String EMAIL="bsobirov8027@gmail.com";
    private static final String EMAIL_PASSWORD="quojmpcukwelhwic";
    //    private static final String NO_EMAIL_ID = "some.@gmail.com";

    public static Session initAuthSession(){
        Properties properties=new Properties();
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port",587);
        properties.put("mail.smtp.auth",true);
        properties.put("mail.smtp.starttls.enable",true);

        Authenticator authenticator=new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL,EMAIL_PASSWORD);
            }
        };
        return  Session.getDefaultInstance(properties,authenticator);
    }
}
