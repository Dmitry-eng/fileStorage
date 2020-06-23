package com.example.demo.controller;

import com.example.demo.service.download.Download;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RestFileDownload {
    private Download download;
    public RestFileDownload(@Autowired Download download){
        this.download=download;
    }
    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable String id)  {
        return download.download(id);
    }

}
