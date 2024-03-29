package com.storage.service.group;

import com.storage.model.Account;
import com.storage.model.Group;
import com.storage.model.GroupAccount;
import com.storage.model.Request;
import com.storage.repository.GroupRepository;
import com.storage.repository.GroupAccountRepository;
import com.storage.repository.RequestRepository;
import com.storage.service.AccountSession;
import com.storage.service.exception.group.GroupException;
import com.storage.service.exception.security.Security;
import com.storage.service.exception.security.SecurityException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.storage.service.exception.group.Group.NAME_EMPTY;
import static com.storage.service.exception.group.Group.NAME_EXISTS;
import static com.storage.service.exception.group.Group.ACCOUNT_IN_GROUP;
import static com.storage.service.exception.group.Group.REQUEST_SEND_BEFORE;

@Component
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {
    private static final String LIKE = "%%%s%%";
    private final AccountSession accountSession;
    private final GroupRepository groupRepository;
    private final GroupAccountRepository groupAccountRepository;
    private final RequestRepository requestRepository;

    @Override
    public final List<Group> findAll() {
        List<Group> groupAccounts = groupRepository.findAllGroupAccessForAccount(accountSession.getAccount());
        return groupRepository.findAll().stream().peek(group -> {
            if (groupAccounts.contains(group)) {
                group.setAccess(true);
            }
        }).collect(Collectors.toList());
    }

    public final List<Group> findAllGroupAccessForAccount() {
        return groupRepository.findAllGroupAccessForAccount(accountSession.getAccount());
    }

    @Override
    public final List<Group> findAll(String value) {
        return groupRepository.findAll(like(value));
    }

    @Override
    public final List<Group> findAllGroupAccessForAccount(String value) {
        return groupRepository.findAllGroupAccessForAccount(accountSession.getAccount(), value);
    }

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

    @Override
    public Group getById(Long id) {
        Group group = groupRepository.getOne(id);
        checkAccessGetGroup(group);
        return groupRepository.getOne(id);
    }

    @Override
    public final void accept(Long id) {
        Request request = requestRepository.getOne(id);
        checkAccessAccount(request);
        groupAccountRepository.save(new GroupAccount(request.getGroup(), request.getAccount()));
        requestRepository.delete(request);
    }

    @Override
    public final List<Request> requestList() {
        return requestRepository.findAllByAccount(accountSession.getAccount());
    }

    @Override
    public final List<Request> requestList(String value) {
        return requestRepository.findAllByAccount(accountSession.getAccount(), value);
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

    private void checkAccessAccount(Request request) {
        if (request.getGroup().getAccount().equals(accountSession.getAccount())) {
            throw new SecurityException(Security.ACCESS_DENIED);
        }
    }

    private void checkAccessGetGroup(Group group) {
        Account account = accountSession.getAccount();
        if (!group.getAccount().equals(accountSession.getAccount()) && group.getGroupAccounts().stream().map(GroupAccount::getAccount)
                .noneMatch(account::equals)) {
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

    private void checkJoinInGroup(Group group, Account account) {
        if (requestRepository.findByAccountAndGroup(account, group).size() != 0) {
            throw new GroupException(REQUEST_SEND_BEFORE);
        }

        if (groupAccountRepository.findByGroupAndAccount(group, account) != null) {
            throw new GroupException(ACCOUNT_IN_GROUP);
        }
    }

    private String like(String value) {
        return String.format(LIKE, value);
    }

}