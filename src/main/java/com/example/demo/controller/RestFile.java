package com.example.demo.controller;

import com.example.demo.model.Files;
import com.example.demo.service.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/list")
public class RestFile {
    private FileService fileService;

    public RestFile(@Autowired FileService fileService) {
        this.fileService = fileService;
    }

    @PutMapping("/search/{string}")
    public List<Files> search(@PathVariable String string) {
        return fileService.list(string);
    }

    @PutMapping("/search")
    public List<Files> search() {
        return fileService.list();
    }

    @PutMapping("/search/myFile")
    public List<Files> myFile() {
        return fileService.myFile();
    }

    @PutMapping("/search/myFile/{string}")
    public List<Files> myFile(@PathVariable String string) {
        return fileService.myFile(string);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable String id) {
        return fileService.delete(id);
    }
}
