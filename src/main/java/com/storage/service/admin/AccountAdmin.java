package com.storage.service.admin;

import com.storage.model.Account;

import java.util.List;

public interface AccountAdmin {
    List<Account> list();
    List<Account> list(String id);
    void delete(Long id);
}
