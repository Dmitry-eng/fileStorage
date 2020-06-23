package com.example.demo.controller;


import com.example.demo.service.registration.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;


@RestController
@Scope("session")
@RequestMapping("/reg")
public class RestRegistration {
    private RegistrationService registrationService;

    public RestRegistration(@Autowired RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PutMapping("/login/{login}")
    public boolean validLogin(@PathVariable String login) {
        return registrationService.loginUnique(login);
    }

    @PutMapping("/email/{email}")
    public boolean validEmail(@PathVariable String email) {
        return registrationService.emailUnique(email);
    }

    @PostMapping("send/{name}/{login}/{password}/{email}/{number}")
    public boolean send(@PathVariable String name, @PathVariable String login,
                        @PathVariable String password, @PathVariable String email, @PathVariable String number) {
        return registrationService.completionRegistration(name, login, password, email, number);
    }

    @PutMapping("/sendEmail/{email}")
    public boolean sendEmail(@PathVariable String email) {
        return registrationService.send(email);
    }

}