package com.jec.ramenlog.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Created by @author 卞凌志
 * on 2022/10/13 11:26
 */
@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender sender;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("shimakaazee.gmail.com");
        message.setTo(to);
        message.setText(body);
        message.setSubject(subject);

        sender.send(message);

        System.out.println("Mail sent success");
    }
}
