package com.storage.service.group;

import com.storage.model.Group;
import com.storage.model.Request;

import java.util.List;

public interface GroupService {
    List<Group> findAll();

    List<Group> findAllGroupAccessForAccount();

    List<Group> findAll(String string);

    List<Group> findAllGroupAccessForAccount(String string);

    void add(String string);

    void delete(Long id);

    Group getById(Long id);

    void sendRequestToJoinInGroup(Long id);

    List<Request> requestList();

    List<Request> requestList(String string);

    void reject(Long id);

    void accept(Long id);
}
