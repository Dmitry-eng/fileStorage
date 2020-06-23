package com.example.demo.service.registration;

import org.springframework.stereotype.Component;

@Component
public interface RegistrationService {

    boolean loginUnique(String email);

    boolean emailUnique(String login);

    boolean send(String email);

    boolean completionRegistration(String name, String login, String password, String email, String strNumber);
}
