package com.storage.controller;


import com.storage.model.Request;
import com.storage.service.group.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/request")
@AllArgsConstructor
public class RestRequest {
    private final GroupService groupService;

    @PostMapping("/accept/{id}")
    public void accept(@PathVariable Long id) {
        groupService.accept(id);
    }

    @PostMapping("/reject/{id}")
    public void reject(@PathVariable Long id) {
        groupService.reject(id);
    }

    @PutMapping("/list")
    public List<Request> list() {
        return groupService.requestList();
    }

    @PutMapping("/list/{string}")
    public List<Request> list(@PathVariable String string) {
        return groupService.requestList(string);
    }
}
