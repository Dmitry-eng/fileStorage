package com.storage.service.util;


import com.storage.model.Account;
import com.storage.model.File;
import com.storage.model.Group;
import com.storage.model.Request;

import java.util.ArrayList;
import java.util.List;


//TODO then delete
@Deprecated
public class Util {

    public static List<File> getFiles(String str, List<File> files) {
        List<File> tmp = new ArrayList<>();
        for (File file : files) {
            if (file.getName().toLowerCase().contains(str.toLowerCase()) || file.getInfo().toLowerCase().contains(str.toLowerCase()) || file.getId().toString().equals(str) || file.getDate().toString().contains(str) || file.getAccount().getLogin().toLowerCase().contains(str.toLowerCase())) {
                tmp.add(file);
            }
        }
        return tmp;
    }

    public static List<Account> getUser(String str, List<Account> account) {
        List<Account> tmp = new ArrayList<>();
        for (Account u : account) {
            if (u.getLogin().toLowerCase().contains(str.toLowerCase()) || u.getId().toString().equals(str)) {
                tmp.add(u);
            }
        }
        return tmp;
    }

    public static List<Group> getGroup(String string, List<Group> gr) {
        List<Group> tmp = new ArrayList<>();
        for (Group group : gr) {
            if (group.getAccount().getLogin().toLowerCase().contains(string) || group.getName().toLowerCase().contains(string) || group.getId().toString().equals(string))
                tmp.add(group);
        }
        return tmp;
    }
     public static List<Request> getRequest(String string, List<Request> requests) {
        List<Request> tmp = new ArrayList<>();
        for (Request request : requests) {
            if (request.getGroup().getName().contains(string) || request.getAccount().getName().contains(string))
                tmp.add(request);
        }
        return tmp;
    }

}
