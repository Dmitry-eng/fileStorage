package com.storage.service;

import com.storage.model.Account;
import com.storage.model.Role;
import com.storage.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

@Service
@AllArgsConstructor
public class AccountSession {
    private final AccountRepository accountRepository;
    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    public final String getAccountLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public final void removeSession(){
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
    }

    public final Account getAccount() {
        return accountRepository.findByLogin(getAccountLogin());
    }

    public final boolean isAdmin(){
        return getAccount().getRoles().stream().map(Role::getName).anyMatch(ROLE_ADMIN::equals);
    }

    public final String getSessionId() {
        return RequestContextHolder.currentRequestAttributes().getSessionId();
    }
}
