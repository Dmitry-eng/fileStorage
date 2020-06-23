package com.example.demo.service.file;


import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.UserSession;
import com.example.demo.service.items.Refactor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class FilesGroupImpl implements FilesGroup {
    private GroupUserRepository groupUserRepository;
    private UserSession userSession;
    private GroupRepository groupRepository;
    private FileRepository fileRepository;
    private FileGroupRepository fileGroupRepository;
    private UserRepository userRepository;


    public FilesGroupImpl(@Autowired FileRepository fileRepository, @Autowired GroupUserRepository groupUserRepository, @Autowired UserSession userSession, @Autowired GroupRepository groupRepository, @Autowired FileGroupRepository fileGroupRepository, @Autowired UserRepository userRepository) {
        this.fileRepository = fileRepository;
        this.groupUserRepository = groupUserRepository;
        this.userSession = userSession;
        this.groupRepository = groupRepository;
        this.fileGroupRepository = fileGroupRepository;
        this.userRepository = userRepository;

    }

    @Override
    public List<Files> show(String strId) {
        try {
            long id = Long.parseLong(strId);
            User user = userSession.getUser();
            Group group = groupRepository.getOne(id);
            if (!group.getUser().equals(user)
                    && groupUserRepository.countByGroupAndUser(group, user) == 0) return null;
            return fileRepository.findAllByGroup(group);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Files> show(String strId, String text) {
        return Refactor.getFiles(text, show(strId));
    }

    @Override
    public final Group group(String id) {
        try {
            return groupRepository.getOne(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public final boolean leave(String strId) {
        try {
            groupUserRepository.deleteByGroupAndUser(
                    groupRepository.getOne(Long.parseLong(strId)),
                    userSession.getUser());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public final boolean addFileGroup(String fileId, String groupId) {
        Group group = groupRepository.getOne(Long.parseLong(groupId));
        User user = userSession.getUser();
        Files file = fileRepository.getOne(Long.parseLong(fileId));
        GroupUser groupUser = groupUserRepository.findByGroupAndUser(group, user);
        if (group.getUser().equals(user) || groupUser != null) {
            if (fileGroupRepository.findAllByFileAndGroup(file, group).size() != 0) return false;
            FileGroup fileGroup = new FileGroup(file, group);
            fileGroupRepository.save(fileGroup);
            return true;
        }
        return false;
    }

    public List<User> showUserGroup(String groupId) {
        User user = userSession.getUser();
        Group group = groupRepository.getOne(Long.parseLong(groupId));
        GroupUser groupUser = groupUserRepository.findByGroupAndUser(group, user);
        if (group.getUser().equals(user) || groupUser != null) {
            return userRepository.pFindByGroup(group);
        }
        return null;
    }

    public final List<User> showUserGroup(String groupId, String text) {
        return Refactor.getUser(text, showUserGroup(groupId));
    }

    public final boolean deleteUserGroup(String groupId, String userId) {
        Group group = groupRepository.getOne(Long.parseLong(groupId));
        User user = userRepository.getOne(Long.parseLong(userId));
        if (group.getUser().equals(userSession.getUser())) {
            groupUserRepository.deleteByGroupAndUser(group, user);
            return true;
        }
        return false;
    }

    @Override
    public final boolean deleteFileGroup(String groupId, String fileId) {
        Group group=groupRepository.getOne(Long.parseLong(groupId));
        Files files=fileRepository.getOne(Long.parseLong(fileId));
        if(group.getUser().equals(userSession.getUser())){
            fileGroupRepository.deleteByFileAndGroup(files,group);
            return true;
        }
        return false;
    }
}
