package com.example.demo.service.download;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

public interface Download {
    ResponseEntity<Resource> download(String id);
}
