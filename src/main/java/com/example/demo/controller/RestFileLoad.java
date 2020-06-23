package com.example.demo.controller;


import com.example.demo.service.load.LoadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class RestFileLoad {
    private final LoadFile loadFile;

    public RestFileLoad(@Autowired LoadFile loadFile) {
        this.loadFile = loadFile;
    }


    @PostMapping("/file")
    public String loadFile(@RequestParam("file") MultipartFile loadFile, String name, String info, String checkbox) {
     return this.loadFile.load(loadFile, name, info, checkbox);
    }
}
