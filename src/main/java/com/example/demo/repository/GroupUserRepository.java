package com.example.demo.repository;

import com.example.demo.model.Group;
import com.example.demo.model.GroupUser;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


public interface GroupUserRepository extends JpaRepository<GroupUser, Long> {

    int countByGroupAndUser(Group group, User user);

    GroupUser findByGroupAndUser(Group group, User user);

    @Transactional
    void deleteByGroupAndUser(Group group, User user);
}
