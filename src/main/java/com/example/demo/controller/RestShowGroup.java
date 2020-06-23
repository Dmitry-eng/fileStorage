package com.example.demo.controller;

import com.example.demo.model.Files;
import com.example.demo.model.Group;
import com.example.demo.model.User;
import com.example.demo.service.file.FilesGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/show")
public class RestShowGroup {
    private FilesGroup filesGroup;

    public RestShowGroup(@Autowired FilesGroup filesGroup) {
        this.filesGroup = filesGroup;
    }

    @PutMapping("/{id}")
    public List<Files> show(@PathVariable String id) {
        return filesGroup.show(id);
    }

    @PutMapping("/{id}/{text}")
    public List<Files> show(@PathVariable String id, @PathVariable String text) {
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

    @PutMapping("/UserGroup/{groupId}")
    public List<User> showUserGroup(@PathVariable String groupId) {
        return filesGroup.showUserGroup(groupId);
    }

    @PutMapping("/UserGroup/{groupId}/{text}")
    public List<User> showUserGroup(@PathVariable String groupId, @PathVariable String text) {
        return filesGroup.showUserGroup(groupId, text);
    }

    @DeleteMapping("/deleteUserGroup/{groupId}/{userId}")
    public boolean deleteUserGroup(@PathVariable String groupId, @PathVariable String userId) {
        return filesGroup.deleteUserGroup(groupId, userId);
    }
    @DeleteMapping("/deleteFileGroup/{groupId}/{fileId}")
    public boolean deleteFileGroup(@PathVariable String groupId, @PathVariable String fileId) {
        return filesGroup.deleteFileGroup(groupId,fileId);
    }
}
