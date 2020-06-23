package com.example.demo.service.admin;

import com.example.demo.model.Files;

import java.util.List;

public interface FileAdmin {
    List<Files> list();
    List<Files> list(String id);
    boolean delete(String id);
}
