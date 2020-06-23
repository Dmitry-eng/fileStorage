package com.example.demo.service.group;

import com.example.demo.model.Group;
import com.example.demo.model.Request;

import java.util.List;

public interface GroupService {
    List<Group> listGroup();

    List<Group> myGroup();

    List<Group> listGroup(String string);

    List<Group> myGroup(String string);

    String add(String string);

    boolean remove(String id);

    boolean request(String id);

    List<Request> requestList();

    List<Request> requestList(String string);

    boolean reject(String strId);

    boolean accept(String strId);
}
