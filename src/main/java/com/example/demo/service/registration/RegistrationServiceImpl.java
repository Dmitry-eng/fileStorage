package com.example.demo.service.registration;


import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleRepository roleRepository;
    private MailSender mailSender;
    private final int number;

    public RegistrationServiceImpl(@Autowired RoleRepository roleRepository,@Autowired UserRepository userRepository, @Autowired BCryptPasswordEncoder bCryptPasswordEncoder, @Autowired MailSender mailSender) {
        this.userRepository = userRepository;
        this.roleRepository=roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mailSender=mailSender;
        double tmp = Math.random()*10000;
        this.number = (int) tmp;
    }

    @Override
    public final boolean send(String email) {
        return mailSender.send(email, this.number);
    }

    @Override
    public final boolean loginUnique(String login) {
        if (login == null) return false;
        List<User> users = userRepository.findAllByLoginContains(login);
        for (User user : users) {
            if (user.getLogin().equals(login)) return false;
        }
        return true;
    }

    @Override
    public final boolean emailUnique(String email) {
        if (email == null) return false;
        List<User> users = userRepository.findAllByEmailContains(email);
        for (User user : users) {
            if (user.getEmail().equals(email)) return false;
        }
        return true;
    }

    @Override
    public final boolean completionRegistration(String name, String login, String password, String email, String strNumber) {
        int number;
        try {
            number = Integer.parseInt(strNumber);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }

        if (this.number == number) {
            User user = new User(name, login, password, email);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setRoles(Collections.singleton(roleRepository.getOne(1L)));
            userRepository.save(user);
            return true;
        }
        return false;
    }
}