package com.example.demo.service.registration;

import org.springframework.stereotype.Component;

@Component
public interface MailSender {
    boolean send(String email, int number);
}
