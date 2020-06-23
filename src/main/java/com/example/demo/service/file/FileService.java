package com.example.demo.service.file;

import com.example.demo.model.Files;


import java.util.List;

public interface FileService {
    List<Files> list();
    List<Files> list(String str);
    List<Files> myFile();
    List<Files> myFile(String str);
    boolean delete(String id);
}
