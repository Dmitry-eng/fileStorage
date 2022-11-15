package com.storage.controller;

import com.storage.model.Group;
import com.storage.model.Role;
import com.storage.repository.AccountRepository;
import com.storage.service.AccountSession;
import com.storage.service.file.FilesGroup;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@AllArgsConstructor
public class ControllerPages {
    private final AccountSession accountSession;
    private final FilesGroup filesGroup;
    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String ADMIN = "admin";
    private static final String ID = "id";

    @RequestMapping(value = "/registration")
    public String registrationController() {
        return "registration";
    }

    @RequestMapping(value = "/")
    public String fileList(Model model) {
        model.addAttribute(ADMIN, isAdmin());
        return "file/fileList";
    }

    @RequestMapping(value = "/myFile")
    public String myFile(Model model) {
        model.addAttribute(ADMIN, isAdmin());
        return "file/myFile";
    }

    @RequestMapping(value = "/load")
    public String loadFile() {
        return "file/loadFile";
    }

    @RequestMapping(value = "/listGroup")
    public String group(Model model) {
        model.addAttribute(ADMIN, isAdmin());
        return "group/listGroup";
    }

    @RequestMapping(value = "/myGroup")
    public String myGroup(Model model) {
        model.addAttribute(ADMIN, isAdmin());
        model.addAttribute(ID, accountSession.getAccount().getId());
        return "group/myGroup";
    }

    @RequestMapping(value = "/add")
    public String addGroup() {
        return "group/addGroup";
    }

    @RequestMapping(value = "/request")
    public String request(Model model) {
        model.addAttribute(ADMIN, isAdmin());
        return "group/requestGroup";
    }

    @RequestMapping(value = "/show/{id}")
    public String show(@PathVariable String id, Model model) {
        Group group = filesGroup.group(id);
        model.addAttribute("groupLead", group.getAccount().equals(accountSession.getAccount()));
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

    @RequestMapping(value = "/showAccountGroup/{id}")
    public String showAccountGroup(@PathVariable String id, Model model) {
        Group group = filesGroup.group(id);
        model.addAttribute("groupLead", group.getAccount().equals(accountSession.getAccount()));
        model.addAttribute("groupId", group.getId());
        model.addAttribute("groupName", group.getName());
        return "group/showAccountGroup";
    }

    @RequestMapping(value = "/admin/files")
    public String admin() {
        return "admin/files";
    }

    @RequestMapping(value = "/admin/groups")
    public String adminGroup() {
        return "admin/groups";
    }

    @RequestMapping(value = "/admin/accounts")
    public String adminUser() {
        return "admin/accounts";
    }

    private boolean isAdmin() {
        return accountSession.getAccount().getRoles().stream().map(Role::getName).anyMatch(ROLE_ADMIN::equals);
    }
}

