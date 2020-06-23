package com.example.demo.service.items;

import com.example.demo.model.Files;
import com.example.demo.model.Group;
import com.example.demo.model.Request;
import com.example.demo.model.User;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Refactor {

    public static List<Files> getFiles(String str, List<Files> files) {
        List<Files> tmp = new ArrayList<>();
        for (Files file : files) {
            if (file.getName().toLowerCase().contains(str.toLowerCase()) || file.getInfo().toLowerCase().contains(str.toLowerCase()) || file.getId().toString().equals(str) || file.getData().toString().contains(str) || file.getUser().getLogin().toLowerCase().contains(str.toLowerCase())) {
                tmp.add(file);
            }
        }
        return tmp;
    }

    public static List<User> getUser(String str, List<User> user) {
        List<User> tmp = new ArrayList<>();
        for (User u : user) {
            if (u.getLogin().toLowerCase().contains(str.toLowerCase()) || u.getId().toString().equals(str)) {
                tmp.add(u);
            }
        }
        return tmp;
    }

    public static List<Group> getGroup(String string, List<Group> gr) {
        List<Group> tmp = new ArrayList<>();
        for (Group group : gr) {
            if (group.getUser().getLogin().toLowerCase().contains(string) || group.getName().toLowerCase().contains(string) || group.getId().toString().equals(string))
                tmp.add(group);
        }
        return tmp;
    }
     public static List<Request> getRequest(String string, List<Request> requests) {
        List<Request> tmp = new ArrayList<>();
        for (Request request : requests) {
            if (request.getGroup().getName().contains(string) || request.getUser().getName().contains(string))
                tmp.add(request);
        }
        return tmp;
    }

}
