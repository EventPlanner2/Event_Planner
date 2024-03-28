package com.example.services;

import com.example.data.Config;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {

    private String senderEmail;
    private String password;
    private Properties props;

    public EmailSender() {
        this.senderEmail = Config.email;
        this.password = Config.password;


        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.port", "587");

    }

    public boolean sendEmail(String recipientEmail, String subject, String content) {

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, password);
            }
        });

        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(senderEmail));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));

            message.setSubject(subject);

            message.setText(content);


            Transport.send(message);

            return true;

        } catch (MessagingException e) {
            // do nothing
        }
        return false;
    }


}
