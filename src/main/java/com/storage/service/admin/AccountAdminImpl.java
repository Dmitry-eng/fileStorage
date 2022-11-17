package com.storage.service.admin;

import com.storage.model.Account;
import com.storage.model.File;
import com.storage.repository.FileRepository;
import com.storage.repository.AccountRepository;
import com.storage.service.file.FileService;
import com.storage.service.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountAdminImpl implements AccountAdmin {
    private final AccountRepository accountRepository;
    private final FileRepository fileRepository;


    @Override
    public List<Account> list() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> list(String id) {
        return Util.getUser(id, list());
    }

    @Override
    public void delete(Long id) {
        Account account = accountRepository.getOne(id);
        fileRepository.deleteByAccount(account);
        accountRepository.delete(account);
    }
}
