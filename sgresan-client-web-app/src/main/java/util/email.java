/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Joel
 */
public class email {
     private final Properties properties = new Properties();
    private Session session;
    private void init() {
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.port", 568);
        properties.put("mail.smtp.mail.sender", "odontologospadent@gmail.com");
        properties.put("mail.smtp.password", "tp2014jp");
        properties.put("mail.smtp.user", "odontologospadent@gmail.com");
        properties.put("mail.smtp.auth", "true");
        session = Session.getDefaultInstance(properties);
    }
    public void send(String destino,String asunto, String mensaje) {
        init();
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
            message.setSubject(asunto);
            message.setText(mensaje);
            Transport t = session.getTransport("smtp");
            t.connect(properties.getProperty("mail.smtp.host"),properties.get("mail.smtp.user").toString(), 
                      properties.get("mail.smtp.password").toString());
            t.sendMessage(message, message.getAllRecipients());            
            t.close();
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
            return;
        }
    }
}