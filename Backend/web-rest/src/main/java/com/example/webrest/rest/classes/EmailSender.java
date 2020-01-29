package com.example.webrest.rest.classes;

import javax.enterprise.context.ApplicationScoped;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@ApplicationScoped
public class EmailSender{
    private String FROM = "emailsenderdmark@gmail.com";
    private String PASSWORD = "emailsender";

    public void SendMail(String to, String subject, String username, String body) throws Exception {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PASSWORD);
            }
        });

        Message message = prepareMessage(session, FROM, to, subject, username, body);

        Transport.send(message);
    }

    private Message prepareMessage(Session session, String from, String to, String subject, String username, String body) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(to), new InternetAddress("danisovszkyerik7@gmail.com")});
            message.setSubject(subject);
            String htmlCode = "<h1> Kedves " + username + "! </h1> <br/> <h2><b>" + body + " </b></h2> <br/> <h2><b> Üdvözlettel, </h2><b> <br/> <h3> Cég neve </h3>";
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {
            //...
        }
        return null;
    }
}
