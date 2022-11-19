package com.storage.service.account;

import com.storage.model.Account;

public interface AccountService {
    void create(Account account);

    boolean loginUnique(String login);

    boolean emailUnique(String email);
}
