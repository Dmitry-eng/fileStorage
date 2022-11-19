package com.storage.service.account;

import com.storage.model.Account;
import com.storage.repository.AccountRepository;
import com.storage.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public void create(Account account) {
        account.setPassword(encoder.encode(account.getPasswordConfirm()));
        account.setRoles(Collections.singleton(roleRepository.getOne(1L)));
        account.setActivated(true);
        accountRepository.save(account);
    }

    @Override
    public final boolean loginUnique(String login) {
        List<Account> accounts = accountRepository.findAllByLoginContains(login);
        return accounts.stream().noneMatch(account -> account.getLogin().equals(login));
    }

    @Override
    public final boolean emailUnique(String email) {
        List<Account> accounts = accountRepository.findAllByEmailContains(email);
        return accounts.stream().noneMatch(account -> account.getEmail().equals(email));
    }
}
