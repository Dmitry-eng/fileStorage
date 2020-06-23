package com.example.demo.controller;

import com.example.demo.model.Files;
import com.example.demo.model.Group;
import com.example.demo.model.User;
import com.example.demo.service.admin.FileAdmin;
import com.example.demo.service.admin.GroupAdmin;
import com.example.demo.service.admin.UserAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class RestAdmin {
    private FileAdmin fileAdmin;
    private GroupAdmin groupAdmin;
    private UserAdmin userAdmin;
    public RestAdmin(@Autowired FileAdmin fileAdmin, @Autowired GroupAdmin groupAdmin, @Autowired UserAdmin userAdmin){
        this.fileAdmin=fileAdmin;
        this.groupAdmin=groupAdmin;
        this.userAdmin=userAdmin;
    }
    @PutMapping("/file/")
    public List<Files> fileList(){
        return fileAdmin.list();
    }
    @PutMapping("/file/{id}")
    public List<Files> fileList(@PathVariable String id){
        return fileAdmin.list(id);
    }
    @PutMapping("/group/")
    public List<Group> groupList(){
        return groupAdmin.list();
    }
    @PutMapping("/group/{id}")
    public List<Group> groupList(@PathVariable String id){
        return groupAdmin.list(id);
    }
    @PutMapping("/user/")
    public List<User> userList(){
        return userAdmin.list();
    }
    @PutMapping("/user/{id}")
    public List<User> userList(@PathVariable String id){
        return userAdmin.list(id);
    }
    @DeleteMapping("/deleteUser/{id}")
    public boolean deleteUser(@PathVariable String id){
        return userAdmin.delete(id);
    }
    @DeleteMapping("/deleteGroup/{id}")
    public boolean deleteGroup(@PathVariable String id){
        return groupAdmin.delete(id);
    }
    @DeleteMapping("/deleteFile/{id}")
    public boolean deleteFile(@PathVariable String id){
        return fileAdmin.delete(id);
    }
}
