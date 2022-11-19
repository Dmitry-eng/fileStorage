package com.storage.controller;

import com.storage.model.Group;
import com.storage.service.group.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/group")
@AllArgsConstructor
public class RestGroup {
    private final GroupService groupService;

    @GetMapping("search")
    public List<Group> findAll() {
        return groupService.findAll();
    }

    @GetMapping("search/{string}")
    public List<Group> findAll(@PathVariable String string) {
        return groupService.findAll(string);
    }

    @GetMapping("search/account")
    public List<Group> findAllGroupAccessForAccount() {
        return groupService.findAllGroupAccessForAccount();
    }

    @GetMapping("search/account/{string}")
    public List<Group> findAllGroupAccessForAccount(@PathVariable String string) {
        return groupService.findAllGroupAccessForAccount(string);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(String name) {
        groupService.add(name);
    }

    @DeleteMapping("delete/{id}")
    public void remove(@PathVariable Long id) {
        groupService.delete(id);
    }

    @PostMapping("/request/{id}")
    public void sendRequestToJoinInGroup(@PathVariable Long id) {
        groupService.sendRequestToJoinInGroup(id);
    }
}
