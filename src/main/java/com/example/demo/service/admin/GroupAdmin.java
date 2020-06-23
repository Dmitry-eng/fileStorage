package com.example.demo.service.admin;

import com.example.demo.model.Group;

import java.util.List;

public interface GroupAdmin {
    List<Group> list();
    List<Group> list(String id);
    boolean delete(String id);
}
