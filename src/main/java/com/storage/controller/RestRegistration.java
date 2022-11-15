package com.storage.controller;


import com.storage.model.Account;
import com.storage.service.account.AccountService;
import com.storage.service.registration.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/reg")
@AllArgsConstructor
public class RestRegistration {
    private final RegistrationService registrationService;
    private final AccountService accountService;

    @GetMapping("/login/{login}")
    public boolean checkLogin(@PathVariable String login) {
        return accountService.loginUnique(login);
    }

    @GetMapping("/email/{email}")
    public boolean checkEmail(@PathVariable String email) {
        return accountService.emailUnique(email);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/code")
    public void code(@RequestBody Integer code) {
        registrationService.completionRegistration(code);
    }

    @PostMapping("/account")
    public ResponseEntity<Boolean> account(@RequestBody Account account) {
        return new ResponseEntity<>(registrationService.send(account), HttpStatus.OK);
    }
}  