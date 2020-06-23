package com.example.demo.service.load;


import com.example.demo.model.User;
import com.example.demo.repository.FileRepository;
import com.example.demo.service.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.Calendar;


@Component
public class LoadFileImpl implements LoadFile {
    private FileRepository fileRepository;
    private UserSession userSession;
    private final String address;

    public LoadFileImpl(@Autowired FileRepository fileRepository, @Autowired UserSession userSession, @Value("${address.file}") String address) {
        this.userSession = userSession;
        this.fileRepository = fileRepository;
        this.address = address;
    }

    @Override
    public final String load(MultipartFile loadFile, String name, String info, String checkbox) {
        if (loadFile.isEmpty()) {
            return "Файл не может быть пустым";
        }
        try {
            User user = userSession.getUser();
            String location = saveLoadFiles(loadFile, user);
            if (name == null || name.replace(" ", "").length() == 0) {
                name = loadFile.getOriginalFilename();
            }
            if (info == null) info = "-";
            boolean bool = false;
            if (checkbox != null && checkbox.equals("on")) bool = true;
            Date date = new Date(Calendar.getInstance().getTime().getTime());
            com.example.demo.model.Files files = new com.example.demo.model.Files(name, info, date, user, location, bool);
            fileRepository.save(files);
            return "Файл загружен успешно.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Не удалось загрузить файл.";
        }
    }

    private String saveLoadFiles(MultipartFile file, User user) throws IOException {
        String location = user.getId() + "\\" + new java.util.Date().toString().replace(" ", "").replace(":", "");
        byte[] bytes = file.getBytes();
        new File(address + location).mkdirs();
        Path path = Paths.get(address + location, file.getOriginalFilename());
        Files.write(path, bytes);
        return location + "\\" + file.getOriginalFilename();
    }
}