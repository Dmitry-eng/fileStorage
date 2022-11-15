package com.storage.controller;

import com.storage.model.File;
import com.storage.service.dto.FileDto;
import com.storage.service.file.FileService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/file")
@AllArgsConstructor
public class RestFile {
    private final FileService fileService;

    @GetMapping("/search/{value}")
    public List<File> search(@PathVariable String value) {
        return fileService.search(value);
    }

    @GetMapping("/search")
    public List<File> search() {
        return fileService.search();
    }

    @GetMapping("/search/account")
    public List<File> searchByAccount() {
        return fileService.findFileByAccount();
    }

    @GetMapping("/search/account/{string}")
    public List<File> searchByAccount(@PathVariable String string) {
        return fileService.findFileByAccount(string);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        fileService.delete(id);
    }


    @PostMapping("/load")
    @ResponseStatus(HttpStatus.CREATED)
    public void loadFile(@RequestParam("file") MultipartFile multipartFile, @ModelAttribute  FileDto fileDto) {
        fileService.load(multipartFile, fileDto);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable Long id) {
        return fileService.download(id);
    }
}
