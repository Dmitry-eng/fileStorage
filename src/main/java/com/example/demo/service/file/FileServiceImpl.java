package com.example.demo.service.file;

import com.example.demo.model.Files;
import com.example.demo.repository.FileRepository;
import com.example.demo.service.UserSession;
import com.example.demo.service.items.Refactor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class FileServiceImpl implements FileService {
    private FileDelete fileDelete;
    private FileRepository fileRepository;
    private UserSession userSession;

    public FileServiceImpl(@Autowired FileRepository fileRepository, @Autowired UserSession userSession, @Autowired FileDelete fileDelete) {
        this.userSession = userSession;
        this.fileRepository = fileRepository;
        this.fileDelete = fileDelete;
    }

    @Override
    public final List<Files> list() {
        return fileRepository.findAllByOpenTrueOrUser(userSession.getUser());
    }

    @Override
    public final List<Files> list(String str) {
        return Refactor.getFiles(str, list());
    }

    @Override
    public final List<Files> myFile() {
        return fileRepository.findAllByUser(userSession.getUser());
    }

    @Override
    public final List<Files> myFile(String str) {
        return Refactor.getFiles(str, myFile());
    }

    @Override
    public final boolean delete(String strId) {
        long id;
        try {
            id = Long.parseLong(strId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
        Files files = fileRepository.findByIdAndUser(id, userSession.getUser());
        if (files == null) return false;
        fileRepository.delete(files);
        fileDelete.delete(files);
        return true;
    }
}
