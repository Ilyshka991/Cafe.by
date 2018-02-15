package bsuir.pechuro.utils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;


public class EmailSender {

    static Properties mailServerProperties = new Properties();
    static Session getMailSession;
    static MimeMessage generateMailMessage;


    public static Integer generateAndSendEmail(String locale, String email) throws MessagingException, IOException {
        mailServerProperties.load(EmailSender.class.getClassLoader().getResourceAsStream("mail.properties"));

        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

        Integer code = rnd();
        String emailBody;
        if (locale.equals("ru")) {
            generateMailMessage.setSubject("Восстановление пароля от Уютного Уголка");
            emailBody = "Code for change password: " + code;
        } else {
            generateMailMessage.setSubject("Reset password from COZY CORNER");
            emailBody = "Code for change password: " + code;
        }
        generateMailMessage.setContent(emailBody, "text/html");

        Transport transport = getMailSession.getTransport("smtp");
        transport.connect("smtp.gmail.com", "forventseducation@gmail.com", "Qweqwe123");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
        return code;
    }


    public static int rnd() {
        return (int) (Math.random() * 8999) + 1000;
    }
}