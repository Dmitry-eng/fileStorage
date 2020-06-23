package com.example.demo.service.load;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface LoadFile {
    String load(MultipartFile loadFile, String name, String info, String checkbox);
}
