package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserSession {
    private UserRepository userRepository;

    public UserSession(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public final String getUserLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public final User getUser() {

        return userRepository.findByLogin(getUserLogin());
    }
}
