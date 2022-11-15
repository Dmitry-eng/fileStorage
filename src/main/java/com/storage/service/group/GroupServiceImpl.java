package com.storage.service.group;

import com.storage.model.Account;
import com.storage.model.Group;
import com.storage.model.GroupAccount;
import com.storage.model.Request;
import com.storage.repository.GroupRepository;
import com.storage.repository.GroupAccountRepository;
import com.storage.repository.RequestRepository;
import com.storage.service.AccountSession;
import com.storage.service.exception.GroupException;
import com.storage.service.exception.Security;
import com.storage.service.exception.SecurityException;
import com.storage.service.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.storage.service.exception.Group.NAME_EMPTY;
import static com.storage.service.exception.Group.NAME_EXISTS;
import static com.storage.service.exception.Group.ACCOUNT_IN_GROUP;
import static com.storage.service.exception.Group.REQUEST_SEND_BEFORE;

@Component
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final AccountSession accountSession;
    private final GroupRepository groupRepository;
    private final GroupAccountRepository groupAccountRepository;
    private final RequestRepository requestRepository;

    @Override
    public final List<Group> findAll() {
        List<Group> groups = groupRepository.findAll();
        List<Group> groupAccounts = groupRepository.findAllMyGroup(accountSession.getAccount());
        for (Group group : groupAccounts) {
            group.setAccess(true);
            groups.set(groups.indexOf(group), group);
        }
        return groups;
    }


    @Override
    public final List<Group> findAllByAccount() {
        return groupRepository.findAllMyGroup(accountSession.getAccount());
    }

    @Override
    public final List<Group> findAll(String string) {
        return Util.getGroup(string.toLowerCase(), findAll());
    }

    @Override
    public final List<Group> findAllByAccount(String string) {
        return Util.getGroup(string.toLowerCase(), findAllByAccount());
    }

    //    @Override
//    public final String add(String name) {
//        if (string.replace(" ", "").length() == 0
//                || groupRepository.countByNameEquals(string) != 0) return "Не удалось создать группу.";
//        groupRepository.save(new Group(string, accountSession.getAccount()));
//        return "Группа " + string + " создана успешно.";
//    }
//
    @Override
    public void add(String name) {
        checkCreateGroup(name);
        groupRepository.save(new Group(name, accountSession.getAccount()));
    }

    @Override
    public final void delete(Long id) {
        Group group = groupRepository.getOne(id);
        checkAccessForDeleteGroup(group);
        groupRepository.delete(group);
    }

    @Override
    public final void sendRequestToJoinInGroup(Long id) {
        Account account = accountSession.getAccount();
        Group group = groupRepository.getOne(id);
        checkJoinInGroup(group, account);
        requestRepository.save(new Request(account, group));
    }

    private void checkJoinInGroup(Group group, Account account) {
        if (requestRepository.findByAccountAndGroup(account, group).size() != 0) {
            throw new GroupException(REQUEST_SEND_BEFORE);
        }

        if (groupAccountRepository.findByGroupAndAccount(group, account) != null) {
            throw new GroupException(ACCOUNT_IN_GROUP);
        }
    }

    @Override
    public final void accept(Long id) {
        Request request = requestRepository.getOne(id);
        checkAccessAccount(request);
        groupAccountRepository.save(new GroupAccount(request.getAccount(), request.getGroup()));
        requestRepository.delete(request);
    }

    private void checkAccessAccount(Request request) {
        if (request.getGroup().getAccount() != accountSession.getAccount()) {
            throw new SecurityException(Security.ACCESS_DENIED);
        }
    }

    @Override
    public final List<Request> requestList() {
        return requestRepository.findAllByAccount(accountSession.getAccount());
    }

    @Override
    public final List<Request> requestList(String string) {
        return Util.getRequest(string, requestList());
    }

    @Override
    public final void reject(Long id) {
        Request request = requestRepository.getOne(id);
        checkAccessAccount(request);
        requestRepository.delete(request);
    }

    private void checkAccessForDeleteGroup(Group group) {
        if (!group.getAccount().equals(accountSession.getAccount()) && !accountSession.isAdmin()) {
            throw new SecurityException(Security.ACCESS_DENIED);
        }
    }

    private void checkCreateGroup(String name) {
        if (name == null || name.replace(" ", "").length() == 0) {
            throw new GroupException(NAME_EMPTY);
        }
        if (groupRepository.countByNameEquals(name) != 0) {
            throw new GroupException(NAME_EXISTS);
        }
    }

}