package com.example.demo.controller;

import com.example.demo.model.Group;
import com.example.demo.service.UserSession;
import com.example.demo.service.file.FilesGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class ControllerPages {
    private UserSession userSession;
    private FilesGroup filesGroup;

    public ControllerPages(@Autowired FilesGroup filesGroup, @Autowired UserSession userSession) {
        this.filesGroup = filesGroup;
        this.userSession = userSession;
    }

    @RequestMapping(value = "/registration")
    public String registrationController() {
        return "registration";
    }

    @RequestMapping(value = "/")
    public String fileList() {
        return "file/fileList";
    }

    @RequestMapping(value = "/myFile")
    public String myFile() {
        return "file/myFile";
    }

    @RequestMapping(value = "/load")
    public String loadFile() {
        return "file/loadFile";
    }

    @RequestMapping(value = "/listGroup")
    public String group() {
        return "group/listGroup";
    }

    @RequestMapping(value = "/myGroup")
    public String myGroup(Model model) {
        model.addAttribute("id", userSession.getUser().getId());
        return "group/myGroup";
    }

    @RequestMapping(value = "/add")
    public String addGroup() {
        return "group/addGroup";
    }

    @RequestMapping(value = "/request")
    public String request() {
        return "group/requestGroup";
    }

    @RequestMapping(value = "/show/{id}")
    public String show(@PathVariable String id, Model model) {
        Group group = filesGroup.group(id);
        model.addAttribute("groupLead", group.getUser().equals(userSession.getUser()));
        model.addAttribute("groupId", group.getId());
        model.addAttribute("groupName", group.getName());
        return "group/showGroup";
    }

    @RequestMapping(value = "/addFileGroup/{id}")
    public String addFileGroup(@PathVariable String id, Model model) {
        Group group = filesGroup.group(id);
        model.addAttribute("groupId", group.getId());
        model.addAttribute("groupName", group.getName());
        return "group/addFileGroup";
    }

    @RequestMapping(value = "/showUserGroup/{id}")
    public String showUserGroup(@PathVariable String id, Model model) {
        Group group = filesGroup.group(id);
        model.addAttribute("groupLead", group.getUser().equals(userSession.getUser()));
        model.addAttribute("groupId", group.getId());
        model.addAttribute("groupName", group.getName());
        return "group/showUserGroup";
    }

    @RequestMapping(value = "/admin")
    public String admin() {
        return "admin/admin";
    }

    @RequestMapping(value = "/adminGroup")
    public String adminGroup() {
        return "admin/adminGroup";
    }

    @RequestMapping(value = "/adminUser")
    public String adminUser() {
        return "admin/adminUser";
    }
}

