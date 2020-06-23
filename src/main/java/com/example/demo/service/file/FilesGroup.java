package com.example.demo.service.file;

import com.example.demo.model.Files;
import com.example.demo.model.Group;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FilesGroup {
    List<Files> show(String id, String text);
    List<Files> show(String id);
    Group group(String id);
    boolean leave(String id);
    boolean addFileGroup(String strFileId, String strGroupId);
    List<User> showUserGroup(String groupId);
    List<User> showUserGroup(String groupId, String text);
    boolean deleteUserGroup(String groupId, String userId);
    boolean deleteFileGroup(String groupId, String fileId);
}
