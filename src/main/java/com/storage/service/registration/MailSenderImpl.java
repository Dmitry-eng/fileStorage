package com.storage.service.registration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class MailSenderImpl implements MailSender {
    private final JavaMailSender javaMailSender;

    public MailSenderImpl(@Autowired JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public boolean send(String email, int number) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Регистрация");
            message.setText("Здравствуйте. Для завершения регистрации введите код:" + number);
            this.javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}
