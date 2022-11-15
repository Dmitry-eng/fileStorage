package com.storage.controller;

import com.storage.model.Account;
import com.storage.model.File;
import com.storage.model.Group;
import com.storage.service.file.FilesGroup;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/show")
@AllArgsConstructor
public class RestShowGroup {
    private final FilesGroup filesGroup;

    @PutMapping("/{id}")
    public List<File> show(@PathVariable String id) {
        return filesGroup.show(id);
    }

    @PutMapping("/{id}/{text}")
    public List<File> show(@PathVariable String id, @PathVariable String text) {
        return filesGroup.show(id, text);
    }

    @PutMapping("/group/{id}")
    public Group group(@PathVariable String id) {
        return filesGroup.group(id);
    }

    @DeleteMapping("/leave/{id}")
    public boolean leave(@PathVariable String id) {
        return filesGroup.leave(id);
    }

    @PostMapping("/addFileGroup/{fileId}/{groupId}")
    public boolean addFileGroup(@PathVariable String fileId, @PathVariable String groupId) {
        return filesGroup.addFileGroup(fileId, groupId);
    }

    @PutMapping("/AccountGroup/{groupId}")
    public List<Account> showAccountGroup(@PathVariable Long groupId) {
        return filesGroup.showAccountGroup(groupId);
    }

    @PutMapping("/AccountGroup/{groupId}/{text}")
    public List<Account> showAccountGroup(@PathVariable Long groupId, @PathVariable String text) {
        return filesGroup.showAccountGroup(groupId, text);
    }

    @DeleteMapping("/deleteAccountGroup/{groupId}/{accountId}")
    public boolean deleteAccountGroup(@PathVariable String groupId, @PathVariable String accountId) {
        return filesGroup.deleteAccountGroup(groupId, accountId);
    }

    @DeleteMapping("/deleteFileGroup/{groupId}/{fileId}")
    public boolean deleteFileGroup(@PathVariable String groupId, @PathVariable String fileId) {
        return filesGroup.deleteFileGroup(groupId, fileId);
    }
}
