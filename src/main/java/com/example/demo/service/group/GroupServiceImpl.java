package com.example.demo.service.group;

import com.example.demo.model.Group;
import com.example.demo.model.GroupUser;
import com.example.demo.model.Request;
import com.example.demo.model.User;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.GroupUserRepository;
import com.example.demo.repository.RequestRepository;
import com.example.demo.service.UserSession;
import com.example.demo.service.items.Refactor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GroupServiceImpl implements GroupService {
    private UserSession userSession;
    private GroupRepository groupRepository;
    private GroupUserRepository groupUserRepository;
    private RequestRepository requestRepository;

    public GroupServiceImpl(@Autowired UserSession userSession, @Autowired GroupRepository groupRepository, @Autowired GroupUserRepository groupUserRepository, @Autowired RequestRepository requestRepository) {
        this.userSession = userSession;
        this.groupRepository = groupRepository;
        this.groupUserRepository = groupUserRepository;
        this.requestRepository = requestRepository;
    }

    @Override
    public final List<Group> listGroup() {
        List<Group> groups = groupRepository.findAll();
        List<Group> groupUsers = groupRepository.findAllMyGroup(userSession.getUser());
        for (Group group : groupUsers) {
            group.setAccess(true);
            groups.set(groups.indexOf(group), group);
        }
        return groups;
    }


    @Override
    public final List<Group> myGroup() {
        return groupRepository.findAllMyGroup(userSession.getUser());
    }

    @Override
    public final List<Group> listGroup(String string) {
        return getGroup(string.toLowerCase(), listGroup());
    }

    @Override
    public final List<Group> myGroup(String string) {
        return getGroup(string.toLowerCase(), myGroup());
    }

    private List<Group> getGroup(String string, List<Group> gr) {
        List<Group> tmp = new ArrayList<>();
        for (Group group : gr) {
            if (group.getUser().getLogin().toLowerCase().contains(string) || group.getName().toLowerCase().contains(string) || group.getId().toString().equals(string))
                tmp.add(group);
        }
        return tmp;
    }

    @Override
    public final String add(String string) {
        if (string.replace(" ", "").length() == 0
                || groupRepository.countByNameEquals(string) != 0) return "Не удалось создать группу.";
        groupRepository.save(new Group(string, userSession.getUser()));
        return "Группа " + string + " создана успешно.";
    }

    @Override
    public final boolean remove(String strId) {
        long id;
        try {
            id = Long.parseLong(strId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
        Group group = groupRepository.getOne(id);
        if (group.getUser().equals(userSession.getUser())) {
            groupRepository.delete(group);
            return true;
        }
        return false;
    }

    @Override
    public final boolean request(String strId) {
        long id;
        try {
            id = Long.parseLong(strId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
        User user = userSession.getUser();
        Group group = groupRepository.getOne(id);
        if (requestRepository.findByUserAndGroup(user, group).size() != 0) return false;
        requestRepository.save(new Request(user, group));
        return true;
    }

    @Override
    public final boolean accept(String strId) {
        try {
            long id = Long.parseLong(strId);
            Request request = requestRepository.getOne(id);
            if (request.getGroup().getUser() != userSession.getUser()) return false;
            groupUserRepository.save(new GroupUser(request.getUser(), request.getGroup()));
            requestRepository.delete(request);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public final List<Request> requestList() {
        return requestRepository.findAllByUser(userSession.getUser());
    }

    @Override
    public final List<Request> requestList(String string) {
        return Refactor.getRequest(string, requestList());
    }

    @Override
    public final boolean reject(String strId) {
        try {
            long id = Long.parseLong(strId);
            Request request = requestRepository.getOne(id);
            if (request.getGroup().getUser() != userSession.getUser()) return false;
            requestRepository.delete(request);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }
}