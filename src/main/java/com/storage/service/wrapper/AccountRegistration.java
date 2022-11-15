package com.storage.service.wrapper;

import com.storage.model.Account;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AccountRegistration {
    private final Account account;
    private final int code;
}
