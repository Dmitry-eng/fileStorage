package com.storage.service.admin;

import com.storage.model.File;
import com.storage.repository.FileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FileAdminImpl implements FileAdmin {
    private final FileRepository fileRepository;

    @Override
    public List<File> list() {
        return fileRepository.findAll();
    }

    @Override
    public List<File> list(String value) {
        return fileRepository.findAll(value);
    }
}
