package com.storage.service.registration;

import com.storage.model.Account;
import com.storage.service.AccountSession;
import com.storage.service.account.AccountService;
import com.storage.service.cache.Cache;
import com.storage.service.exception.registration.Registration;
import com.storage.service.exception.registration.RegistrationException;
import com.storage.service.wrapper.AccountRegistration;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final AccountService accountService;
    private final MailSender mailSender;
    private final Cache<AccountRegistration> accountRegistrationCache;
    private final AccountSession accountSession;

    @Override
    public final boolean send(Account account) {
        AccountRegistration registration = new AccountRegistration(account, (int) (Math.random() * 10000));
        accountRegistrationCache.add(accountSession.getSessionId(), registration);
        return mailSender.send(account.getEmail(), registration.getCode());
    }

    @Override
    public final void completionRegistration(int code) {
        String sessionId = accountSession.getSessionId();
        AccountRegistration accountRegistration = accountRegistrationCache.get(sessionId);
        if (code == accountRegistration.getCode()) {
            accountRegistrationCache.remove(sessionId);
            accountService.create(accountRegistration.getAccount());
        } else {
            throw new RegistrationException(Registration.INCORRECT_CODE);
        }
    }
}