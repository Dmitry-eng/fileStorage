package com.storage.service.file;


import com.storage.model.Account;
import com.storage.model.File;
import com.storage.model.FileGroup;
import com.storage.model.Group;
import com.storage.model.GroupAccount;
import com.storage.repository.AccountRepository;
import com.storage.repository.FileGroupRepository;
import com.storage.repository.FileRepository;
import com.storage.repository.GroupRepository;
import com.storage.repository.GroupAccountRepository;
import com.storage.service.AccountSession;
import com.storage.service.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FilesGroupImpl implements FilesGroup {
    private final GroupAccountRepository groupAccountRepository;
    private final AccountSession accountSession;
    private final GroupRepository groupRepository;
    private final FileRepository fileRepository;
    private final FileGroupRepository fileGroupRepository;
    private final AccountRepository accountRepository;

    @Override
    public List<File> show(String strId) {
        try {
            long id = Long.parseLong(strId);
            Account account = accountSession.getAccount();
            Group group = groupRepository.getOne(id);
            if (!group.getAccount().equals(account)
                    && groupAccountRepository.countByGroupAndAccount(group, account) == 0) return null;
            return fileRepository.findAllByGroup(group);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<File> show(String strId, String text) {
        return Util.getFiles(text, show(strId));
    }

    @Override
    public final Group group(String id) {
        try {
            return groupRepository.getOne(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public final boolean leave(String strId) {
        try {
            groupAccountRepository.deleteByGroupAndAccount(
                    groupRepository.getOne(Long.parseLong(strId)),
                    accountSession.getAccount());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public final boolean addFileGroup(String fileId, String groupId) {
        Group group = groupRepository.getOne(Long.parseLong(groupId));
        Account account = accountSession.getAccount();
        File file = fileRepository.getOne(Long.parseLong(fileId));
        GroupAccount groupAccount = groupAccountRepository.findByGroupAndAccount(group, account);
        if (group.getAccount().equals(account) || groupAccount != null) {
            if (fileGroupRepository.findAllByFileAndGroup(file, group).size() != 0) return false;
            FileGroup fileGroup = new FileGroup(file, group);
            fileGroupRepository.save(fileGroup);
            return true;
        }
        return false;
    }

    public List<Account> showAccountGroup(Long groupId) {
       return accountRepository.pFindByGroupId(groupId);
    }

    public final List<Account> showAccountGroup(Long groupId, String text) {
        return Util.getUser(text, showAccountGroup(groupId));
    }

    public final boolean deleteAccountGroup(String groupId, String userId) {
        Group group = groupRepository.getOne(Long.parseLong(groupId));
        Account account = accountRepository.getOne(Long.parseLong(userId));
        if (group.getAccount().equals(accountSession.getAccount())) {
            groupAccountRepository.deleteByGroupAndAccount(group, account);
            return true;
        }
        return false;
    }

    @Override
    public final boolean deleteFileGroup(String groupId, String fileId) {
        Group group = groupRepository.getOne(Long.parseLong(groupId));
        File file = fileRepository.getOne(Long.parseLong(fileId));
        if (group.getAccount().equals(accountSession.getAccount())) {
            fileGroupRepository.deleteByFileAndGroup(file, group);
            return true;
        }
        return false;
    }
}
