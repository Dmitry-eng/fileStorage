package com.example.demo.controller;

import com.example.demo.model.Group;
import com.example.demo.service.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class RestGroup {
    private GroupService groupService;


    public RestGroup(@Autowired GroupService groupService) {
        this.groupService = groupService;
    }

    @PutMapping("list")
    public List<Group> groupList() {
        return groupService.listGroup();
    }

    @PutMapping("list/{string}")
    public List<Group> groupList(@PathVariable String string) {
        return groupService.listGroup(string);
    }

    @PutMapping("myGroup")
    public List<Group> myGroup() {
        return groupService.myGroup();
    }

    @PutMapping("myGroup/{string}")
    public List<Group> myGroup(@PathVariable String string) {
        return groupService.myGroup(string);
    }

    @PostMapping("/add")
    public String add(String name) {
        return groupService.add(name);
    }

    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return groupService.remove(id);
    }

    @PostMapping("/request/{id}")
    public boolean request(@PathVariable String id){
        return groupService.request(id);
    }
}
