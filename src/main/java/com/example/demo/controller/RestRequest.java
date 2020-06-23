package com.example.demo.controller;


import com.example.demo.model.Request;
import com.example.demo.service.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/request")
public class RestRequest {
    private GroupService groupService;

    public RestRequest(@Autowired GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/accept/{id}")
    public boolean request(@PathVariable String id) {
        return groupService.accept(id);
    }
    @PostMapping("/reject/{id}")
    public boolean reject(@PathVariable String id) {
        return groupService.reject(id);
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
