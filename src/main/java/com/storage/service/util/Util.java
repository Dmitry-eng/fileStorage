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
}
