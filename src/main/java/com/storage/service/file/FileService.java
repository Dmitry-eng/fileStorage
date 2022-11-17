package com.storage.service.file;

import com.storage.model.File;
import com.storage.dto.FileDto;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    List<File> search();
    List<File> search(String value);
    List<File> findFileByAccount();
    List<File> findFileByAccount(String value);
    void delete(Long id);
    void load(MultipartFile multipartFile, FileDto fileDto);
    ResponseEntity<Resource> download(Long id);
}
