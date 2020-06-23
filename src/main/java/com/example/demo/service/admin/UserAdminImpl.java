package com.example.demo.service.admin;

import com.example.demo.model.Files;
import com.example.demo.model.User;
import com.example.demo.repository.FileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.file.FileDelete;
import com.example.demo.service.items.Refactor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAdminImpl implements UserAdmin {
    private FileDelete fileDelete;
    private UserRepository userRepository;
    private FileRepository fileRepository;

    public UserAdminImpl(@Autowired FileRepository fileRepository, @Autowired UserRepository userRepository, @Autowired FileDelete fileDelete) {
        this.userRepository = userRepository;
        this.fileDelete = fileDelete;
        this.fileRepository=fileRepository;
    }

    @Override
    public List<User> list() {
        return userRepository.findAll();
    }

    @Override
    public List<User> list(String id) {
        return Refactor.getUser(id, list());
    }

    @Override
    public boolean delete(String id) {
        try {
            User user = userRepository.getOne(Long.parseLong(id));
            List<Files> files = fileRepository.findAllByUser(user);
            for (Files file : files) {
                fileDelete.delete(file);
            }
            userRepository.delete(user);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }
}
