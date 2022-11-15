package com.storage.service.registration;

import com.storage.model.Account;

public interface RegistrationService {
    boolean send(Account account);

    void completionRegistration(int code);
}
