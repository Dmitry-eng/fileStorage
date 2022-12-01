package com.storage.service.file;


import com.storage.model.Account;
import com.storage.model.File;
//import com.storage.model.FileGroup;
import com.storage.model.Group;
//import com.storage.model.GroupAccount;
import com.storage.repository.AccountRepository;
//import com.storage.repository.FileGroupRepository;
import com.storage.repository.FileRepository;
import com.storage.repository.GroupRepository;
//import com.storage.repository.GroupAccountRepository;
import com.storage.service.AccountSession;
import com.storage.service.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FilesGroupImpl implements FilesGroup {
//    private final GroupAccountRepository groupAccountRepository;
    private final AccountSession accountSession;
    private final GroupRepository groupRepository;
    private final FileRepository fileRepository;
//    private final FileGroupRepository fileGroupRepository;
    private final AccountRepository accountRepository;

    @Override
    public List<File> show(Long id, String text) {
        return null;
//        return Util.getFiles(text, show(strId));
    }

    @Override
    public final boolean leave(Long strId) {
        return true;
//        try {
//            groupAccountRepository.deleteByGroupAndAccount(
//                    groupRepository.getOne(Long.parseLong(strId)),
//                    accountSession.getAccount());
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
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
//            groupAccountRepository.deleteByGroupAndAccount(group, account);
            return true;
        }
        return false;
    }

    @Override
    public final boolean deleteFileGroup(String groupId, String fileId) {
        Group group = groupRepository.getOne(Long.parseLong(groupId));
        File file = fileRepository.getOne(Long.parseLong(fileId));
        if (group.getAccount().equals(accountSession.getAccount())) {
//            fileGroupRepository.deleteByFileAndGroup(file, group);
            return true;
        }
        return false;
    }
}
