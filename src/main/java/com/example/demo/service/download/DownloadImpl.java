package com.example.demo.service.download;

import com.example.demo.model.Files;
import com.example.demo.model.Group;
import com.example.demo.repository.FileRepository;
import com.example.demo.repository.GroupRepository;
import com.example.demo.service.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class DownloadImpl implements Download {
    private final String tmp;
    private Resource file;
    private UserSession userSession;
    private FileRepository fileRepository;
    private GroupRepository groupRepository;

    private final Path root = Paths.get("uploads");

    public DownloadImpl(@Value("${address.file}") String tmp, @Autowired UserSession userSession, @Autowired FileRepository fileRepository, @Autowired GroupRepository groupRepository) {
        this.tmp = tmp;
        this.userSession = userSession;
        this.fileRepository = fileRepository;
        this.groupRepository=groupRepository;
    }

    @Override
    public final ResponseEntity<Resource> download(String id) {
        try {
           Files files= accessOpen(Long.parseLong(id));
            if(files==null) return null;
            Path pathFile = root.resolve(tmp + files.getLocation());
            file = new UrlResource(pathFile.toUri());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
                        + file.getFilename() + "\"").body(file);
    }


// прошу прощения за данный хардкод, в данном методе мы возвращаем класс файла, если он доступен текущему пользователю
    public Files accessOpen(Long id) {
Files f=fileRepository.getOne(id);
if(f.isOpen() || f.getUser().equals(userSession.getUser())) return f;
        List<Group> groupList = groupRepository.findAllMyGroup(userSession.getUser());
        for (Group group : groupList) {
            List<Files> files = fileRepository.findAllByGroup(group);
            for (Files files1 : files) {
                if (files1.getId().equals(f.getId())) {
                   return f;
                }
            }
        }
        return null;
    }
}

