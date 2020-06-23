package com.example.demo.service.admin;

import com.example.demo.model.User;

import java.util.List;

public interface UserAdmin {
    List<User> list();
    List<User> list(String id);
    boolean delete(String id);
}
