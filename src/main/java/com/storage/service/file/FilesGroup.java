package com.storage.service.file;

import com.storage.model.Account;
import com.storage.model.File;
import com.storage.model.Group;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FilesGroup {
    List<File> show(Long id, String text);

    boolean leave(Long id);

    List<Account> showAccountGroup(Long groupId);

    List<Account> showAccountGroup(Long groupId, String text);

    boolean deleteAccountGroup(String groupId, String accountId);

    boolean deleteFileGroup(String groupId, String fileId);
}
