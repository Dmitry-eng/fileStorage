package com.example.demo.service.admin;

import com.example.demo.model.Files;
import com.example.demo.repository.FileRepository;
import com.example.demo.service.file.FileDelete;
import com.example.demo.service.items.Refactor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileAdminImpl  implements FileAdmin {
    private FileRepository fileRepository;
    private FileDelete fileDelete;
    public FileAdminImpl(@Autowired FileDelete fileDelete,@Autowired FileRepository fileRepository){
        this.fileRepository=fileRepository;
        this.fileDelete=fileDelete;
    }

    @Override
    public List<Files> list() {
        return fileRepository.findAll();
    }

    @Override
    public List<Files> list(String id) {
        return Refactor.getFiles(id,list());
    }

    @Override
    public boolean delete(String id) {
        try {
            Files files=fileRepository.getOne(Long.parseLong(id));
            fileDelete.delete(files);
            fileRepository.delete(files);
            return true;
        }catch (NumberFormatException e){
            e.printStackTrace();
            return false;
        }
    }
}
