package com.storage.service.registration;

public interface MailSender {
    boolean send(String email, int number);
}
