package com.storage.service.account;

import com.storage.model.Account;
import com.storage.model.Role;

import java.util.Set;


public interface AccountService {
    void create(Account account);
    boolean loginUnique(String login);
    boolean emailUnique(String email);
}
