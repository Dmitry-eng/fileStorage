package com.storage.config.auth;

import com.storage.model.Account;
import com.storage.repository.AccountRepository;
import lombok.AllArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AccountService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Account account = accountRepository.findByLogin(s);
        if (account == null) {
            throw new UsernameNotFoundException("Пользователь " + s + "не найден.");
        }
        return account;
    }
}
