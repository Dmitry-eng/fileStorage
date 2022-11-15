package com.storage.controller;

import com.storage.model.Account;
import com.storage.model.File;
import com.storage.model.Group;
import com.storage.service.admin.FileAdmin;
import com.storage.service.admin.GroupAdmin;
import com.storage.service.admin.AccountAdmin;
import com.storage.service.file.FileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class RestAdmin {
    private final FileAdmin fileAdmin;
    private final GroupAdmin groupAdmin;
    private final AccountAdmin accountAdmin;
    private final FileService fileService;

    @PutMapping("/file/")
    public List<File> fileList() {
        return fileAdmin.list();
    }

    @PutMapping("/file/{id}")
    public List<File> fileList(@PathVariable String id) {
        return fileAdmin.list(id);
    }

    @PutMapping("/group/")
    public List<Group> groupList() {
        return groupAdmin.list();
    }

    @PutMapping("/group/{id}")
    public List<Group> groupList(@PathVariable String id) {
        return groupAdmin.list(id);
    }

    @PutMapping("/accounts/")
    public List<Account> accountList() {
        return accountAdmin.list();
    }

    @PutMapping("/accounts/{id}")
    public List<Account> accountList(@PathVariable String id) {
        return accountAdmin.list(id);
    }

    @DeleteMapping("/account/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountAdmin.delete(id);
    }

    @DeleteMapping("/group/{id}")
    public void deleteGroup(@PathVariable Long id) {
        groupAdmin.delete(id);
    }

    @DeleteMapping("/file/{id}")
    public void deleteFile(@PathVariable Long id) {
        fileService.delete(id);
    }
}
